package study.lld.projects.parkinglot;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;

/*
Learnings
- Dont swallow exceptions always throw back
- Rolling back transactions in catch
 */
public class Main {
    public static void main(String[] args) {
        /*
        ParkingLot, Floors, Spots, Car, Size, Ticket,

        ParkingLotService
         - ParkingCostService
         - PaymentService
         - isParkingSpotAvailable(Car/Size): boolean
         - park(Car): Ticket
         - checkout(Car): Ticket

         ParkingCostService
         - costPerCarSize
         - calculateCost(Ticket)

         Ticket
         - id
         - carId
         - spotId
         - entryTime
         - existTime

         Car
         - id
         - size

         Spot
         - id
         - size
         - floorId
         - (should th cost be per spot ? Or common for all slots? because a smaller vehicle can park on a larger slot,
            we will keep it fixed for a car size)

         Floor
         - id
         - parkingLotId
         - List<Spot>

         ParkingLot
         - id
         - List<Floor>
         */
    }
}

class ParkingLot {
    int id;
    List<Floor> floors;
}

class Floor {
    int id;
    int parkingLotId;
    List<Spot> spots;
}

class Spot {
    int id;
    int floorId;
    Size size;
    int vehicleId;
    boolean isAvailable;
    ReentrantLock lock = new ReentrantLock();

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public void parkVehicle(int vehicleId) {
        lock();
        try {
            if (!isAvailable) throw new RuntimeException("spot not available");
            this.vehicleId = vehicleId;
            isAvailable = false;
        } finally {
            unlock();
        }
    }

    public void unparkVehicle() {
        lock();
        try {
            if (isAvailable) throw new RuntimeException("spot already available");
            this.vehicleId = -1;
            isAvailable = true;
        } finally {
            unlock();
        }
    }

    public boolean isAvailable() {
        lock();
        try {
            return isAvailable;
        } finally {
            unlock();
        }
    }

    boolean isCompatible(Size vehicleSize) {
        if (size == Size.TRUCK) {
            return true;
        } else if (size == Size.FOUR_WHEELER
                && (vehicleSize == Size.FOUR_WHEELER || vehicleSize == Size.BIKE)) {
            return true;
        } else if (size == Size.BIKE && vehicleSize == Size.BIKE) {
            return true;
        }
        return false;
    }
}

enum Size {
    BIKE,
    FOUR_WHEELER,
    TRUCK
}

class Vehicle {
    int id;
    int licencePlate;
    Size size;
}

class Ticket {
    int id;
    int carId;
    int spotId;
    Instant entryTime;
    Instant exitTime;
}

class ParkingLotService {
    ParkingCostService parkingCostService;
    PaymentService paymentService;
    SpotSelectionStrategy spotSelectionStrategy;
    SpotService spotService;
    TicketService ticketService;

    Ticket park(Vehicle vehicle) {
        if (!spotService.isParkingSpotAvailable(vehicle.size)) {
            throw new RuntimeException("parking not available");
        }
        List<Spot> availableSpots = spotService.listAvailableSpots();
        Spot selectedSpot = spotSelectionStrategy.selectSpot(availableSpots, vehicle.size);
        boolean parked = false;
        try {
            spotService.parkVehicleInSpot(vehicle.id, selectedSpot.id);
            parked = true;

            return ticketService.createTicket(selectedSpot.id, vehicle.id, Instant.now());
        } catch (Exception e) {
            System.out.println("cannot park vehicle on spot. Attempting rollback");
            e.printStackTrace();
            if (parked) {
                spotService.unparkVehicleFromSpot(selectedSpot.id);
            }
            throw new RuntimeException("cannot park vehicle on spot");
        }
    }

    Invoice checkout(Ticket ticket, PaymentStrategy paymentStrategy) {
        boolean updatedExitTime = false;
        boolean paid = false;
        boolean unparked = false;
        Invoice invoice = null;
        try {
            ticketService.updateExitTime(ticket, Instant.now());
            updatedExitTime = true;

            int total = parkingCostService.calculateCost(ticket);
            invoice = new Invoice(ticket, paymentStrategy, total); // invoice dao later
            paymentService.pay(invoice);
            paid = true;

            spotService.unparkVehicleFromSpot(ticket.spotId);
            unparked = true;
            // save invoice in dao
            return invoice;
        } catch (Exception e) {
            System.out.println("could not checkout. Attempting rollback");
            if (updatedExitTime) {
                ticketService.updateExitTime(ticket, null);
            }
            if (paid) {
                paymentService.reversePayment(invoice);
            }
            if (unparked) {
                spotService.parkVehicleInSpot(ticket.carId, ticket.spotId);
            }

            throw new RuntimeException("could not checkout.");
        }
    }

    //    ParkingLotService
    //         - ParkingCostService
    //         - PaymentService
    //         - isParkingSpotAvailable(Car/Size): boolean
    //         - park(Car): Ticket
    //         - checkout(Car): Ticket
}

interface TicketService {
    Ticket createTicket(int spotId, int vehicleId, Instant entryTime);

    Ticket updateExitTime(Ticket ticketId, Instant exitTime);
}

interface SpotService {
    SpotDao spotDao = new SpotDao();

    Spot get(int spotId);

    List<Spot> list();

    List<Spot> listAvailableSpots();

    List<Spot> listAvailableSpotsOnFloor(int floorId);

    boolean isParkingSpotAvailable(Size size);

    default void parkVehicleInSpot(int vehicleId, int spotId) {
        Spot spot = spotDao.get(spotId);
        spot.lock();
        try {
            spot.parkVehicle(vehicleId);
            spotDao.save(spot);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("cannot park on spot");
            throw e;
        } finally {
            spot.unlock();
        }
    }

    default void unparkVehicleFromSpot(int spotId) {
        Spot spot = spotDao.get(spotId);
        spot.lock();
        try {
            spot.unparkVehicle();
            spotDao.save(spot);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("cannot unpark from spot");
            throw e;
        } finally {
            spot.unlock();
        }
    }
}

class SpotDao {
    Map<Integer, Spot> spots = new HashMap<>();

    Spot get(int spotId) {
        if (!spots.containsKey(spotId)) throw new RuntimeException("spot not foind");
        return spots.get(spotId);
    }

    void save(Spot spot) {
        spots.put(spot.id, spot);
    }
}

interface SpotSelectionStrategy {
    Spot selectSpot(List<Spot> spots, Size vehicleSize);

    default List<Spot> getCompatibleSpots(List<Spot> spots, Size vehicleSize) {
        List<Spot> availableAndCompatibleSpots = new ArrayList<>();
        for (Spot spot : spots) {
            if (spot.isAvailable() && spot.isCompatible(vehicleSize)) {
                availableAndCompatibleSpots.add(spot);
            }
        }
        return availableAndCompatibleSpots;
    }
}

class GetFirstAvailableSpot implements SpotSelectionStrategy {

    @Override
    public Spot selectSpot(List<Spot> spots, Size vehicleSize) {
        if (spots.isEmpty()) throw new RuntimeException("no spots available");
        return spots.stream().filter(s -> s.isCompatible(vehicleSize)).findFirst().get();
    }
}

@AllArgsConstructor
class Invoice {
    Ticket ticket;
    PaymentStrategy paymentStrategy;
    int total;
}

enum PaymentStrategy {
    UPI,
    CASH
}

interface ParkingCostService {
    int calculateCost(Ticket ticket);
}

interface PaymentService {

    void pay(Invoice invoice);

    void reversePayment(Invoice invoice);
}
