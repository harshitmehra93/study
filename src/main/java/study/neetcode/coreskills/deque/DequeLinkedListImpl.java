package study.neetcode.coreskills.deque;

public class DequeLinkedListImpl implements Deque {
    private final int MAX_CAPACITY;
    private final int DEFAULT_MAX_CAPACITY = 50;
    private int size;
    private DoubleLinkNode front;
    private DoubleLinkNode rear;

    DequeLinkedListImpl(int capacity) {
        if (capacity < 0) throw new DequeException("Capacity should not be negative");
        MAX_CAPACITY = capacity;
    }

    DequeLinkedListImpl() {
        MAX_CAPACITY = DEFAULT_MAX_CAPACITY;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean insertFront(int value) {
        if (size >= MAX_CAPACITY) throwDequeOverflowException();
        if (size == 0) {
            front = rear = new DoubleLinkNode(value);
        } else {
            var newFront = new DoubleLinkNode(null, value, front);
            front.prev = newFront;
            front = newFront;
        }
        size++;
        return true;
    }

    @Override
    public boolean insertLast(int value) {
        if (size >= MAX_CAPACITY) throwDequeOverflowException();
        if (size == 0) {
            front = rear = new DoubleLinkNode(value);
        } else {
            var newRear = new DoubleLinkNode(rear, value, null);
            rear.next = newRear;
            rear = newRear;
        }
        size++;
        return true;
    }

    private static void throwDequeOverflowException() {
        throw new DequeException("Dequeu overflow - no more capacity in queue");
    }

    @Override
    public boolean deleteFront() {
        if (size == 0) {
            throwEmptyDequeException();
        } else if (size == 1) {
            front = rear = null;
        } else {
            front.next.prev = null;
            front = front.next;
        }
        size--;
        return true;
    }

    @Override
    public boolean deleteLast() {
        if (size == 0) {
            throwEmptyDequeException();
        } else if (size == 1) {
            front = rear = null;
        } else {
            rear.prev.next = null;
            rear = rear.prev;
        }
        size--;
        return true;
    }

    @Override
    public int getFront() {
        if (size == 0) throwEmptyDequeException();
        return front.value;
    }

    @Override
    public int getRear() {
        if (size == 0) {
            throwEmptyDequeException();
        }
        return rear.value;
    }

    private static void throwEmptyDequeException() {
        throw new DequeException("No element in Deque");
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getCapacity() {
        return MAX_CAPACITY;
    }

    @Override
    public boolean isFull() {
        return size == MAX_CAPACITY;
    }
}
