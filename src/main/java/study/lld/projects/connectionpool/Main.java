package study.lld.projects.connectionpool;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
design time - 15m
implementation time 45m
Learnings
- major correctness bug bug where I tried to currentThread.name as an owner of lease. The reconciler was releasing
    expired leases, which cant be removed as the reconciler thread and owner thread is differed
    and releaseLeaseIfOwned was always a no op from reconciler
- I was modelling a lease based connection pool. Which released a connection back to pool once the lease expired.
    But we will let the requester hold the connection forever. Because  giving the connection to someone else if risky if
    its with someone else. We should just log and metric if a connection is in use state for too long.
- A connection is Closeable. So when the caller closes the connection at that point we actually do a release to pool.
- We use proxy pattern for connections.
- A real connection pool has the following requirements -
    - Pool owns physical DB connections.
    - A borrower receives a logical/proxy connection.
    - Calling close() on the logical connection returns the physical connection to the pool.
    - A logical connection cannot be used after close().
    - If a borrower holds a connection too long, the pool can detect/log a possible leak.
    - The pool does not normally forcibly reuse a connection that is still borrowed.
 */
public class Main {
    public static void main(String[] args) {
        /*
        Database Connection Pool
        - ability to pre create connections to a database and put it in a collection.
        - keep refreshing the individual connections with a scheduler reconcilation inside the connection pool
            this reconcilation will also check the health of the individual connection. If the connection is not healthy
            then we will remove the connection from pool and create a new connection.
            The reconcilation will also remove all failed connections from its pool.
            At every scan if the number of connection is not equal to desired then we will create the new connections
                and put them in pool
            If we implement leases then the reconcilation, will release connections which are IN_USE but expired
        - connection will have a status - INIT,AVAILABLE,FAILED,IN_USE
        - user requests a connection from the pool, if one is available then its returned, its status is marked as IN_USE, otherwise exception is thrown, which can then be gracefully handled by caller
        - user gets a connection and can hold it indefinitely, if we want to enforce a lease then every connection will have a user + connection lease
            when someone does any operation on the connection its leased is checked, and if its not active then exception is thrown
            relesing a connection from a user, will require user to handle lease exceptions at every operation on the connection.
        - After completing their work a connection is released back into the pool, and marked AVAILABLE.

        ConnectionPool
        - id
        - Set<LeasedConnection> connections;
        - ScheduledExecutionService reconciler;
        - getConnection()

        LeasedConnection extends JDBCConnection
        - id
        - leasedBy: String (Thread name)
        - leasedUntil: Instant
        - status: INIT,AVAILABLE,IN_USE,FAILED


         */
    }
}
// Singleton
class ConnectionPool {
    int id;
    Set<LeasedConnection> connections;
    private final ScheduledExecutorService reconciler;
    private final int POOL_SIZE;

    ConnectionPool(int poolSize) {
        this.POOL_SIZE = poolSize;
        this.reconciler = Executors.newSingleThreadScheduledExecutor();
        connections = new CopyOnWriteArraySet<>();
        reconciler.scheduleWithFixedDelay(
                () -> {
                    try {
                        reconcileConnections();
                    } catch (Exception e) {
                        System.out.println("unexpected error occured");
                    }
                },
                1,
                1,
                TimeUnit.MINUTES);
        reconcileConnections();
    }

    void release(LeasedConnection connection) {
        connection.removeLease();
    }

    void reconcileConnections() {
        long countOfAvailableAndInuseConnections =
                connections.stream()
                        .filter(
                                c ->
                                        c.getStatus() == LeasedConnectionStatus.AVAILABLE
                                                || c.getStatus() == LeasedConnectionStatus.IN_USE)
                        .count();
        if (countOfAvailableAndInuseConnections < POOL_SIZE) {
            createNewConnections(POOL_SIZE - countOfAvailableAndInuseConnections);
        } else if (countOfAvailableAndInuseConnections > POOL_SIZE) {
            removeAvailableConnections(countOfAvailableAndInuseConnections - POOL_SIZE);
        }

        for (LeasedConnection connection : connections) {
            if (connection.getStatus() == LeasedConnectionStatus.FAILED) {
                removeConnection(connection);
                continue;
            }
            releaseConnectionIfExpired(connection);
            heartbeatIfAvailable(connection);
        }
    }

    private void heartbeatIfAvailable(LeasedConnection connection) {
        connection.getLock();
        try {
            if (connection.getStatus() == LeasedConnectionStatus.AVAILABLE) {
                connection.heartbeat();
            }
        } finally {
            connection.releaseLock();
        }
    }

    private void releaseConnectionIfExpired(LeasedConnection connection) {
        connection.getLock();
        try {
            if (connection.getStatus() == LeasedConnectionStatus.IN_USE
                    && hasExpiredLease(connection)) {
                connection.removeLease();
            }
        } catch (Exception e) {
            System.out.println("unexpected error occured");
            throw e;
        } finally {
            connection.releaseLock();
        }
    }

    private boolean hasExpiredLease(LeasedConnection connection) {
        if (connection.leasedBy == null || connection.leasedUntil == null) {
            return true;
        }
        if (connection.leasedUntil.isBefore(Instant.now())) {
            return true;
        }
        return false;
    }

    private void removeConnection(LeasedConnection connection) {
        connection.getLock();
        try {
            connection.removeLease();
            connection.updateStatus(LeasedConnectionStatus.RETIRED);
            connections.remove(connection);
        } finally {
            connection.releaseLock();
        }
    }

    private void removeAvailableConnections(long count) {
        int removed = 0;
        for (LeasedConnection connection : connections) {
            if (removed == count) break;
            connection.getLock();
            try {
                if (connection.getStatus() == LeasedConnectionStatus.AVAILABLE) {
                    removeConnection(connection);
                    removed++;
                }
            } finally {
                connection.releaseLock();
            }
        }
    }

    private void createNewConnections(long count) {
        for (int i = 0; i < count; i++) {
            createConnection();
        }
    }

    private void createConnection() {
        connections.add(new LeasedConnection());
    }

    synchronized Optional<LeasedConnection> getConnection() {
        Optional<LeasedConnection> opt =
                connections.stream()
                        .filter(c -> c.getStatus() == LeasedConnectionStatus.AVAILABLE)
                        .findFirst();
        if (opt.isEmpty()) return Optional.empty();

        LeasedConnection connection = opt.get();
        connection.getLock();
        boolean wasConnectionAvailable = false;
        try {
            if (connection.getStatus() != LeasedConnectionStatus.AVAILABLE) {
                throw new RuntimeException("invalid connection state");
            }
            wasConnectionAvailable = true;
            connection.acquireLease(Thread.currentThread().getName());
        } catch (Exception e) {
            System.out.println("unexcpeted error occured");
            if (wasConnectionAvailable) {
                connection.removeLease();
            }
            throw e;
        } finally {
            connection.releaseLock();
        }
        return Optional.of(connection);
    }
}

class LeasedConnection {
    Integer id;
    String leasedBy;
    Instant leasedUntil;
    private LeasedConnectionStatus status;
    private final ReentrantLock LOCK = new ReentrantLock();

    public LeasedConnectionStatus getStatus() {
        getLock();
        try {
            return status;
        } finally {
            releaseLock();
        }
    }

    void heartbeat() {}

    void getLock() {
        LOCK.lock();
    }

    void releaseLock() {
        LOCK.unlock();
    }

    public void removeLease() {
        getLock();
        try {
            leasedBy = null;
            leasedUntil = null;
            updateStatus(LeasedConnectionStatus.AVAILABLE);
        } finally {
            releaseLock();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LeasedConnection connection) {
            return Objects.equals(connection.id, id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void updateStatus(LeasedConnectionStatus leasedConnectionStatus) {
        getLock();
        try {
            status = leasedConnectionStatus;
        } finally {
            releaseLock();
        }
    }

    public void acquireLease(String ownerName) {
        getLock();
        try {
            if (leasedBy != null && leasedUntil != null) {
                throw new RuntimeException("lease already acquired");
            }
            leasedBy = ownerName;
            leasedUntil = Instant.now().plus(Duration.ofMinutes(1));
            updateStatus(LeasedConnectionStatus.IN_USE);
        } finally {
            releaseLock();
        }
    }
}

enum LeasedConnectionStatus {
    AVAILABLE,
    IN_USE,
    FAILED,
    RETIRED
}
