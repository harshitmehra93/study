package study.lld.projects.cabbooking;

import java.time.Duration;
import java.time.Instant;

/*
time: 70 mins
- Learned about making leases
-
 */
public class Main {
    public static void main(String[] args) {
        /*
        Cab Booking system
        - User
            - ability for customer to find free cab in a particular area, going to a particular destination
            - ability to pay for the ride
            - ability to select cab type while booking a cab

        - Cab
            - ability of cab to accept a ride
            - ability to check earnings and rides completed


        User
        - id
        - name
        - email
        - currentLocation

        Cab
        - id
        - driver name
        - cabType:  GO,PLUS
        - currentLocation
        - List<ridesCompleted>
        - currentRideId
        - isAvailable

        Ride
        - id
        - userId
        - cabId
        - startLocation
        - endLocation
        - durationOfRide
        - paymentMethod - CASH,UPI,CARD
        - rideStatus - FINDING_CAB, COULD_NOT_FIND_CAB, ON_THE_WAY, DESTINATION_REACHED, PAYMENT_PENDING, RIDE_COMPLETED


        - A customer will book a ride on their app, and this ride object will get created and then put on a message bus
            a cabFinder service will get the Ride from the message queue, and based on its start location find all cabs (strategy pattern)
            within a 5 km radius which are free. The ride will be pushed to all the selected cab drivers in a selected strategy (broadcast, one at a time)
            The push need to be designed.
        CabFinder
            findAvailableCabs(Ride) : List<Cab>
            notifyCabsUntilRideAccepted(Ride): void <- async process
                The notification job will be put on a message queue. And it will be retried until a cab on the list accepts it

        UserInterface
        - bookCab(userId, currentLocation, destinationLocation, cabType): Ride

        CabInterface implements Listener
        - proposeRide(Ride): void
        - acceptRide(Ride, cabId)
         */
    }
}

class Cab {
    int id;
    String name;
    String email;
    Location currentLocation;
    boolean isAvailable;
    CabType cabType;
    Ride offeredRide;
}

enum CabType {
    GO,
    PREMIERE
}

class User {
    int id;
    String name;
    String email;
    Location currentLocation;
}

class Ride {
    int id;
    int userId;
    int cabId;
    Location start;
    Location end;
    Duration rideDuration;
    PaymentMethod paymentMethod;
    RideStatus status;
    Instant cabRequestTime;
}

enum RideStatus {
    FINDING_CAB,
    CAB_FOUND,
    RIDE_STARTED,
    DESTINATION_REACHED,
    RIDE_COMPLETED
}

enum PaymentMethod {
    UPI,
    CARD,
    CASH
}

class Location {
    double latitude;
    double longitude;
}

class UserInterface {
    CanFinderQueue canFinderQueue;
    RideService rideService;

    Ride bookCab(User user, Location start, Location end, CabType cabType) {
        Ride ride = rideService.createRide(user, start, end, cabType);
        canFinderQueue.offer(ride);
        rideService.updateStatusOfRide(ride, RideStatus.FINDING_CAB);
        return ride;
    }

    Ride cancelCab(User user, Ride ride) {
        return null;
    }
}

interface CanFinderQueue {
    void offer(Ride ride);

    Ride poll();
}

class CabQueueConsumer {
    CabRideLeaseService cabRideLeaseService;
    CabFinderStrategy cabFinderStrategy;
    RideService rideService;
    CabService cabService;
    CanFinderQueue canFinderQueue;

    Cab findAvailableCab(Ride ride) {
        return cabFinderStrategy.findCab(ride);
    }

    void pollQueue() {
        // transaction boundary
        Ride ride = canFinderQueue.poll();
        if (cabRideLeaseService.hasActiveCabRideLeaseForRide(ride.id)) {
            System.out.println("has an actice lease for the ride");
            return;
        }
        if (ride.status != RideStatus.FINDING_CAB) {
            // commit queue cursor to remove message from queue
            System.out.println("cab not in correct status");
        }
        Cab cab = cabFinderStrategy.findCab(ride);
        if (cabRideLeaseService.hasActiveCabRideLeaseForCab(cab.id)) {
            System.out.println("has an active lease for the cab");
            return;
        }
        cabRideLeaseService.createLease(cab.id, ride.id);
        cabService.updatedOfferedRideOnCab(cab, ride);
        // transaction boundary
    }
}

interface CabService {

    void updatedOfferedRideOnCab(Cab cab, Ride ride);

    void acceptOfferedRide(Cab cab);
}

class CabServiceImpl implements CabService {
    CabDao cabDao;
    RideService rideService;
    CabRideLeaseService cabRideLeaseService;

    @Override
    public void updatedOfferedRideOnCab(Cab cab, Ride ride) {
        if (!cab.isAvailable) {
            throw new RuntimeException("Cab is not available");
        }
        cabDao.updateOfferedRideOnCab(cab, ride);
    }

    @Override
    public void acceptOfferedRide(Cab cab) {

        CabRideLease cabLease = cabRideLeaseService.getActiveCabRideLeaseForCab(cab.id);
        CabRideLease rideLease =
                cabRideLeaseService.getActiveCabRideLeaseForRide(cab.offeredRide.id);
        if (!cabLease.equals(rideLease)) {
            throw new RuntimeException("lease mismatch");
        }
        if (cabLease.expiresAt.isBefore(Instant.now())) {
            throw new RuntimeException("expired car lease");
        }
        // transaction boundary
        rideService.updateStatusOfRide(cab.offeredRide, RideStatus.CAB_FOUND);
        cabDao.markCabAsBusy(cab);
        // transaction boundary
    }
}

class CabDao {

    public void updateOfferedRideOnCab(Cab cab, Ride ride) {}

    public void markCabAsBusy(Cab cab) {
        // mark is avialble false
        // make offered ride as the current ride
    }
}

interface CabRideLeaseService {
    boolean hasActiveCabRideLeaseForRide(int rideId);

    boolean hasActiveCabRideLeaseForCab(int cabId);

    void createLease(int cabId, int rideId);

    CabRideLease getActiveCabRideLeaseForCab(int id);

    CabRideLease getActiveCabRideLeaseForRide(int id);
}

class CabRideLease {
    int id;
    int cabId;
    int rideId;
    Instant expiresAt;
}

interface CabFinderStrategy {
    Cab findCab(Ride ride);
}

interface RideService {

    Ride createRide(User user, Location start, Location end, CabType cabType);

    void updateStatusOfRide(Ride ride, RideStatus rideStatus);
}
/*
 CabFinder
            addRideToCabFinderQueue(Ride): void <- sync process

CabFinderConsumer
    findAvailableCab(Ride) : Cab
    pollQueue() - This will get Rides from the queue. For each Ride we see if the status is already
        CAB assigned, if yes then we remove the message from queue. Otherwise we see if there is a
        lock on the ride, If yes we ignore it. Then we see if there is a cab available, if yes we
        notify the cab. And we also hold the cab lock.
 */
