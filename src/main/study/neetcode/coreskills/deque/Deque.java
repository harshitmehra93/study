package study.neetcode.coreskills.deque;

interface Deque {
    int getSize();

    boolean insertFront(int value);
    boolean insertLast(int value);
    boolean deleteFront();
    boolean deleteLast();
    int getFront();
    int getRear();
    boolean isEmpty();

    int getCapacity();
    boolean isFull();
}
