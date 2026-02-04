package study.neetcode.coreskills.generic.dynamicarray;

import static java.util.Objects.isNull;

import java.security.InvalidParameterException;

public class DynamicArrayImpl<U> implements DynamicArray<U> {
    private int capacity;
    private int size = 0;
    private Object[] array;
    private boolean resizeAllowed = true;

    public DynamicArrayImpl(int capacity, boolean resizeAllowed) {
        this.capacity = capacity;
        this.resizeAllowed = resizeAllowed;
        array = new Object[capacity];
    }

    public DynamicArrayImpl(Integer capacity) {
        if (isNull(capacity) || capacity <= 0)
            throw new InvalidParameterException("invalid capacity value");
        this.capacity = capacity;
        array = new Object[capacity];
    }

    public DynamicArrayImpl() {
        capacity = DEFAULT_CAPACITY;
        array = new Object[capacity];
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void pushback(U element) {
        if (getSize() == getCapacity()) resize();
        array[getSize()] = element;
        size++;
    }

    public void resize() {
        if (resizeAllowed) {
            capacity = getCapacity() * 2;
            Object[] newArray = new Object[capacity];
            for (int i = 0; i < getSize(); i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    @Override
    public U get(int i) {
        if (i < 0 || i >= getCapacity()) returnDynamicArrayIndexNotFoundException(i);
        return (U) array[i];
    }

    @Override
    public U popback() {
        if (getSize() == 0) returnDynamicArrayIndexNotFoundException(0);
        U lastElement = (U) array[getSize() - 1];
        size--;
        return lastElement;
    }

    @Override
    public void set(int i, U element) {
        if (i < 0 || i >= getCapacity()) returnDynamicArrayIndexNotFoundException(i);
        if (array[i] == null) {
            size++;
        }
        array[i] = element;
        if (getSize() == getCapacity()) resize();
    }

    private void returnDynamicArrayIndexNotFoundException(int i) {
        throw new DynamicArrayException("index i=" + i + " does not exist for Dynamic Array");
    }
}

class DynamicArrayException extends RuntimeException {
    DynamicArrayException(String msg) {
        super(msg);
    }
}
