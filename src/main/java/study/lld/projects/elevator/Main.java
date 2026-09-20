package study.lld.projects.elevator;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/*
Learnings
-> For a state transition heavy problem, draw state transitions during the planning phase
-> When condition tree starts exploding, stop coding and ask what simple invariants exist. In this case I built a huge decision tree. As I didnot settle on invariants
-> time - ~2hours
 */
public class Main {
    public static void main(String[] args) {

        /*

        LobbyService
        -> requestElevator(int callFloor, Direction direction)

        ElevatorService
        -> requestFloor(int elevatorId, int floor)


        Elevator
        -> int currentFloor
        -> State : GoingUp, GoingDown, TakingAStop, Free
        -> requestFloor(int floor): void
        -> addCall(int callFloor, direction): void
        -> stopAtCurrentFloor(): void
        -> openDoor()
        -> closeDoor()

        Use case 1
        -> Elevator 1 is in state Free on floor 1
        -> User calls for an elevator on floor 1 to go up
        -> Elevator 1 is selected to take the call as its free
        -> Elevator 1 takes a stop at the floor
        -> User requests floor to be 2.
        -> Elevator state is changed to Moving Up.
            -> At floor 2 there is no call on floor 2 to go up,But there is no requested Stop, so it takes a stop.
            -> Now there is no call and there is no requested stop. So elevator changes to free state.

        Use case 1.5
        -> Elevator 1 is in state Free on floor 1
        -> User calls for an elevator on floor 2 to go down
        -> Elevator 1 is selected to take the call as its free
            -> Elevator 1 has no requestedFloors and it has a call on floor 2 to go down. So its state is set to movingUp
            -> Elevator 1 is no floor 2, it has a call for this floor to go down but it has no other requested stops in the direction its moving. So it takes a stop and moves to Free state.

        Use Case 2
        -> Elevator 1 is in free state on floor 1
        -> User calls for an elevator on floor 3 to go up
        -> Elevator 1 is selected to take the call as its free and nearest
        -> Elevator 1 state is set to MovingUp and floor 3 is added to its requestFloor.
            -> At floor 1 there is no call on floor 1 to go up,And there is no requested Stop, so it passes it.
            -> At floor 2 there is no call on floor 2 to go up,And there is no requested Stop, so it passes it.
            -> At floor 3 there is a call for this floor and its in the same direction that its moving so it takes a stop.
            -> Now there is no call and there is no requested stop. So elevator changes to free state.

        Use Case 3
        -> Elevator 1 is in movingUp state on floor 1. It has a request floor of floor 3.
        -> User calls for an elevator on floor 4 to go up
        -> Elevator 1 is selected to take the call as its moving in the same direction of call and nearest
        -> A call to go up on floor 4 is added to Elevator 1.
            -> At floor 1 there is no call on floor 1 to go up,And there is no requested Stop, so it passes it.
            -> At floor 2 there is no call on floor 2 to go up,And there is no requested Stop, so it passes it.
            -> At floor 3 there is no call on floor 3 to go up, But there is a requested stop at floor 3. So it takes a stop.
            -> Now it sees, that there is a call on floor 4 to go up, so its state is kept as MovingUp
            -> At floor 4 there is a call for this floor and its in the same direction that its moving, so it takes a stop.
            -> Now there is no input of floor, and no calls so elevator is moved to Free state.


        Use Case 4
        -> Elevator 1 is in movingUp state on floor 1. It has a request floor of floor 5.
        -> User calls for an elevator on floor 4 to go up
        -> Elevator 1 is selected to take the call as its free and nearest
            -> At floor 1 there is no call on floor 1 to go up, and there is no requested Stop, so it passes it.
            -> At floor 2 there is no call on floor 2 to go up, and there is no requested Stop, so it passes it.
            -> At floor 3 there is no call on floor 3 to go up, and there is no requested Stop, so it passes it.
            -> At floor 4 there is a call on floor 4 to go up its moving in the same direction, so it takes a stop.
            -> Now it sees that there is already a requested stop at floor 5, so its kept in Moving up
            -> At floor 5 there is no call on floor 5,But there is requested Stop, so it stops.
            -> Now there is no input of floor, and no calls so elevator is moved to Free state.

        Use Case 5
        -> Elevator 1 is in movingUp state on floor 1. It has a request floor of floor 5.
        -> User calls for an elevator on floor 4 to go down
        -> Elevator 1 is selected to take the call as its free and nearest
            -> At floor 1 there is no call on floor 1 to go up, and there is no requested Stop, so it passes it.
            -> At floor 2 there is no call on floor 2 to go up, and there is no requested Stop, so it passes it.
            -> At floor 3 there is no call on floor 3 to go up, and there is no requested Stop, so it passes it.
            -> At floor 4 there is a call on floor 4 but to go down and its moving in the up direction, so it passes it.
            -> At floor 5 there is no call on floor 5, But there is requested Stop, so it stops.
            -> Now there is no input of floor, and 1 call to elevator on floor 4 to go down. So it changes state to go down.
            -> At floor 4 there is a call on floor 4 to go down and its moving in the same direction, so it takes a stop.
            -> Now there is no input of floor, and no calls so elevator is moved to Free state.

        Use Case 6
        -> Elevator 1 is in movingUp state on floor 1. It has a request floor of floor 5.
        -> User calls for an elevator on floor 2 to go down
        -> Elevator 1 is selected to take the call as its free and nearest
            -> At floor 1 there is no call on floor 1 to go up, and there is no requested Stop, and there is a request floor, so it passes it.
            -> At floor 2 there is a call on floor 2 to go down but elevator is not moving in this direction, and there is no requested Stop at this floor, and there is a request floor, so it passes it.
            -> At floor 3 there is no call on floor 3 to go up, and there is no requested Stop,  and there is a request floor, so it passes it.
            -> At floor 4 there is a call on floor 4 but to go down and its moving in the up direction,  and there is a request floor, so it passes it.
            -> At floor 5 there is no call on floor 5, But there is requested Stop, so it stops.
            -> Now there is no input of floor, and 1 call to elevator on floor 4 to go down. So it changes state to go down.
            -> At floor 4 there is a call on floor 4 to go down and its moving in the same direction, and there is a request floor, so it takes a stop.
            -> Now there is no input of floor, and no calls so elevator is moved to Free state.
        */

        InMemoryElevatorDao elevatorDao = new InMemoryElevatorDao();
        ElevatorService elevatorService = new ElevatorService(elevatorDao);
        LobbyService lobbyService = new LobbyService(new SimpleElevatorFinder(), elevatorDao);
        elevatorDao.save(new Elevator());
        elevatorDao.save(new Elevator());
        lobbyService.requestElevator(1, Direction.UP);
        elevatorService.requestFloor(0, 5);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        elevatorService.requestFloor(0, 2);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        elevatorService.requestFloor(0, 9);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        lobbyService.requestElevator(6, Direction.DOWN);
        lobbyService.requestElevator(6, Direction.UP);
        elevatorService.requestFloor(0, 2);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        lobbyService.requestElevator(2, Direction.DOWN);
        elevatorService.requestFloor(0, 1);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
        stepAll(elevatorDao);
    }

    static void stepAll(ElevatorDao elevatorDao) {
        elevatorDao.list().forEach(Elevator::step);
    }
}

class LobbyService {
    private final ElevatorFinderStrategy elevatorFinderStrategy;
    private final ElevatorDao elevatorDao;

    LobbyService(ElevatorFinderStrategy elevatorFinderStrategy, ElevatorDao elevatorDao) {
        this.elevatorFinderStrategy = elevatorFinderStrategy;
        this.elevatorDao = elevatorDao;
    }

    void requestElevator(int callFloor, Direction direction) {
        Call call = new Call(callFloor, direction);
        List<Elevator> elevators = elevatorDao.list();
        Elevator e = elevatorFinderStrategy.findElevator(elevators, call);
        e.addCall(call);
    }
}

interface ElevatorDao {
    List<Elevator> list();

    Elevator get(int id);

    void save(Elevator elevator);
}

class InMemoryElevatorDao implements ElevatorDao {
    Map<Integer, Elevator> elevators = new ConcurrentHashMap<>();

    @Override
    public List<Elevator> list() {
        return new ArrayList<>(elevators.values());
    }

    @Override
    public Elevator get(int id) {
        if (!elevators.containsKey(id)) throw new RuntimeException("elevator not found");
        return elevators.get(id);
    }

    @Override
    public void save(Elevator elevator) {
        elevators.put(elevator.id, elevator);
    }
}

class ElevatorService {
    private final ElevatorDao elevatorDao;

    ElevatorService(ElevatorDao elevatorDao) {
        this.elevatorDao = elevatorDao;
    }

    void requestFloor(int elevatorId, int floor) {
        Elevator elevator = elevatorDao.get(elevatorId);
        elevator.requestStop(floor);
    }
}

interface ElevatorFinderStrategy {
    Elevator findElevator(List<Elevator> elevators, Call call);
}

class SimpleElevatorFinder implements ElevatorFinderStrategy {

    @Override
    public Elevator findElevator(List<Elevator> elevators, Call call) {
        List<Elevator> eligibleElevators = new ArrayList<>();
        for (Elevator elevator : elevators) {
            if (elevator.state == ElevatorState.FREE && elevator.currentFloor == call.callFloor()) {
                return elevator;
            }
            if (elevator.state == ElevatorState.GOING_UP
                    && elevator.currentFloor < call.callFloor()
                    && call.direction() == Direction.UP) {
                eligibleElevators.add(elevator);
                continue;
            }
            if (elevator.state == ElevatorState.FREE) {
                eligibleElevators.add(elevator);
                continue;
            }
            if (elevator.state == ElevatorState.GOING_DOWN
                    && elevator.currentFloor > call.callFloor()
                    && call.direction() == Direction.DOWN) {
                eligibleElevators.add(elevator);
                continue;
            }
        }
        if (eligibleElevators.isEmpty()) return elevators.get(0);
        int minDistance = Math.abs(eligibleElevators.get(0).currentFloor - call.callFloor());
        Elevator nearest = eligibleElevators.get(0);
        for (int i = 1; i < eligibleElevators.size(); i++) {
            int distance = Math.abs(eligibleElevators.get(i).currentFloor - call.callFloor());
            if (distance < minDistance) {
                nearest = eligibleElevators.get(i);
                minDistance = distance;
            }
        }
        return nearest;
    }
}

class Elevator {
    static final AtomicInteger counter = new AtomicInteger(0);
    int id;
    int currentFloor;
    ElevatorState state;
    Set<Integer> requestedStops;
    Set<Call> calls;

    public Elevator() {
        this.id = counter.getAndIncrement();
        this.currentFloor = 1;
        state = ElevatorState.FREE;
        requestedStops = new HashSet<>();
        calls = new HashSet<>();
    }

    void addCall(Call call) {
        if (call.callFloor() == currentFloor) {
            takeAStop();
            return;
        }
        calls.add(call);
        if (state == ElevatorState.FREE) {
            if (requestedCallBelowCurrentFloorExists()) {
                state = ElevatorState.GOING_DOWN;
            } else {
                state = ElevatorState.GOING_UP;
            }
        }
    }

    void requestStop(int floor) {
        if (floor == currentFloor) {
            takeAStop();
            return;
        }
        requestedStops.add(floor);
        if (state == ElevatorState.FREE) {
            if (requestedCallBelowCurrentFloorExists()) {
                state = ElevatorState.GOING_DOWN;
            } else {
                state = ElevatorState.GOING_UP;
            }
        }
    }

    void takeAStop() {
        System.out.println("taking a stop");
        openDoor();
        closeDoor();
    }

    void openDoor() {
        System.out.println("opening doors");
    }

    void closeDoor() {
        System.out.println("closing doors");
    }

    void evaluateDirection() {
        if (requestedStops.isEmpty() && calls.isEmpty()) {
            state = ElevatorState.FREE;
        } else if (!requestedStops.isEmpty() && calls.isEmpty()) {
            if (state == ElevatorState.GOING_UP) {
                if (requestedFloorGreaterThanCurrentExists()) {
                    state = ElevatorState.GOING_UP;
                } else {
                    state = ElevatorState.GOING_DOWN;
                }
            } else if (state == ElevatorState.GOING_DOWN) {
                if (requestedFloorLowerThanCurrentExists()) {
                    state = ElevatorState.GOING_DOWN;
                } else {
                    state = ElevatorState.GOING_UP;
                }
            } else if (state == ElevatorState.FREE) {
                if (requestedFloorLowerThanCurrentExists()) {
                    state = ElevatorState.GOING_DOWN;
                } else {
                    state = ElevatorState.GOING_UP;
                }
            }
        } else if (requestedStops.isEmpty() && !calls.isEmpty()) {
            if (state == ElevatorState.GOING_UP) {
                if (requestedCallAboveCurrentFloorExists()) {
                    state = ElevatorState.GOING_UP;
                } else {
                    state = ElevatorState.GOING_DOWN;
                }
            } else if (state == ElevatorState.GOING_DOWN) {
                if (requestedCallBelowCurrentFloorExists()) {
                    state = ElevatorState.GOING_DOWN;
                } else {
                    state = ElevatorState.GOING_UP;
                }
            } else if (state == ElevatorState.FREE) {
                if (requestedCallAboveCurrentFloorExists()) {
                    state = ElevatorState.GOING_UP;
                } else if (requestedCallBelowCurrentFloorExists()) {
                    state = ElevatorState.GOING_DOWN;
                }
            }
        } else if (!requestedStops.isEmpty() && !calls.isEmpty()) {
            if (state == ElevatorState.GOING_UP) {
                if (requestedFloorGreaterThanCurrentExists()
                        && requestedCallAboveCurrentFloorExists()) {
                    state = ElevatorState.GOING_UP;
                } else if (requestedFloorGreaterThanCurrentExists()
                        && !requestedCallAboveCurrentFloorExists()) {
                    state = ElevatorState.GOING_UP;
                } else if (!requestedFloorGreaterThanCurrentExists()
                        && requestedCallAboveCurrentFloorExists()) {
                    state = ElevatorState.GOING_UP;
                } else if (!requestedFloorGreaterThanCurrentExists()
                        && requestedCallBelowCurrentFloorExists()) {
                    state = ElevatorState.GOING_DOWN;
                }
            } else if (state == ElevatorState.GOING_DOWN) {
                if (requestedFloorLowerThanCurrentExists()
                        && requestedCallBelowCurrentFloorExists()) {
                    state = ElevatorState.GOING_DOWN;
                } else if (requestedFloorLowerThanCurrentExists()
                        && requestedCallAboveCurrentFloorExists()) {
                    state = ElevatorState.GOING_DOWN;
                } else if (!requestedFloorLowerThanCurrentExists()
                        && requestedCallBelowCurrentFloorExists()) {
                    state = ElevatorState.GOING_DOWN;
                } else if (!requestedFloorLowerThanCurrentExists()
                        && requestedCallAboveCurrentFloorExists()) {
                    state = ElevatorState.GOING_UP;
                }
            } else if (state == ElevatorState.FREE) {
                if (requestedFloorLowerThanCurrentExists()) {
                    state = ElevatorState.GOING_DOWN;
                } else if (requestedFloorGreaterThanCurrentExists()) {
                    state = ElevatorState.GOING_UP;
                } else if (requestedCallBelowCurrentFloorExists()) {
                    state = ElevatorState.GOING_DOWN;
                } else if (requestedCallAboveCurrentFloorExists()) {
                    state = ElevatorState.GOING_UP;
                }
            }
        }
    }

    private boolean requestedCallAboveCurrentFloorExists() {
        for (Call call : calls) {
            if (call.callFloor() > currentFloor) return true;
        }
        return false;
    }

    private boolean requestedCallBelowCurrentFloorExists() {
        for (Call call : calls) {
            if (call.callFloor() < currentFloor) return true;
        }
        return false;
    }

    private boolean requestedFloorLowerThanCurrentExists() {
        for (int floor : requestedStops) {
            if (floor < currentFloor) return true;
        }
        return false;
    }

    private boolean requestedFloorGreaterThanCurrentExists() {
        for (int floor : requestedStops) {
            if (floor > currentFloor) return true;
        }
        return false;
    }

    void step() {
        Call upCall = new Call(currentFloor, Direction.UP);
        Call downCall = new Call(currentFloor, Direction.DOWN);
        if (requestedStops.contains(currentFloor)) {
            takeAStop();
            requestedStops.remove(currentFloor);
            if (state == ElevatorState.GOING_UP && calls.contains(upCall)) {
                calls.remove(upCall);
            }
            if (state == ElevatorState.GOING_DOWN && calls.contains(downCall)) {
                calls.remove(downCall);
            }
            evaluateDirection();
        } else if (calls.contains(upCall) || calls.contains(downCall)) {
            if (state == ElevatorState.GOING_UP && calls.contains(upCall)) {
                takeAStop();
                calls.remove(upCall);
            }
            if (state == ElevatorState.GOING_DOWN
                    && calls.contains(upCall)
                    && requestedStops.isEmpty()) {
                takeAStop();
                calls.remove(upCall);
            }
            if (state == ElevatorState.GOING_DOWN && calls.contains(downCall)) {
                takeAStop();
                calls.remove(downCall);
            }
            if (state == ElevatorState.GOING_UP
                    && calls.contains(downCall)
                    && requestedStops.isEmpty()) {
                takeAStop();
                calls.remove(downCall);
            }
            evaluateDirection();
        }
        System.out.printf("Elevator %d is on floor %d and state %s\n", id, currentFloor, state);
        if (state == ElevatorState.GOING_UP) {
            currentFloor++;
        } else if (state == ElevatorState.GOING_DOWN) {
            currentFloor--;
        } else if (state == ElevatorState.FREE) {
            System.out.printf("elevator %d is free\n", id);
        }
    }
}

record Call(int callFloor, Direction direction) {}

enum Direction {
    UP,
    DOWN
}

enum ElevatorState {
    GOING_UP,
    GOING_DOWN,
    FREE
}
