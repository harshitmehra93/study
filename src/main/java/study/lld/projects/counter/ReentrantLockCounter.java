package study.lld.projects.counter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements Counter {

    private final ReentrantLock lock = new ReentrantLock();
    private static final ReentrantLockCounter INSTANCE = new ReentrantLockCounter();
    int counter = 0;

    private ReentrantLockCounter() {}

    static ReentrantLockCounter getInstance() {
        return INSTANCE;
    }

    @Override
    public int get() {
        lock.lock();
        try {
            return counter;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void increment() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }
}
