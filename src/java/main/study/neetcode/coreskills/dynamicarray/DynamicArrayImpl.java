package study.neetcode.coreskills.dynamicarray;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

import static java.util.Objects.isNull;

public class DynamicArrayImpl implements DynamicArray {
    private int capacity;
    private int size=0;
    private int[] array;
    public DynamicArrayImpl(Integer capacity){
        if(isNull(capacity)||capacity<=0)
            throw new InvalidParameterException("invalid capacity value");
        this.capacity = capacity;
        array = new int[capacity];
    }

    public DynamicArrayImpl(){
        capacity = DEFAULT_CAPACITY;
        array = new int[capacity];
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
    public void pushback(int i) {
        if(getSize()==getCapacity())
            resize();
        array[getSize()]=i;
        size++;
    }

    public void resize() {
        capacity=getCapacity()*2;
        int[] newArray = new int[capacity];
        for(int i=0;i<getSize();i++){
            newArray[i]=array[i];
        }
        array=newArray;
    }

    @Override
    public int get(int i) {
        if(i<0||i>=getSize())
            returnDynamicArrayIndexNotFoundException(i);
        return array[i];
    }

    @Override
    public int popback() {
        if(getSize()==0)
            returnDynamicArrayIndexNotFoundException(0);
        int lastElement = array[getSize()-1];
        size--;
        return lastElement;
    }

    @Override
    public void set(int i, int element) {
        if(i<0||i>=getSize())
            returnDynamicArrayIndexNotFoundException(i);
        array[i]=element;
    }

    private void returnDynamicArrayIndexNotFoundException(int i) {
        throw new DynamicArrayException("index i="+i+" does not exist for Dynamic Array");
    }

    class DynamicArrayException extends RuntimeException{
        DynamicArrayException(String msg){
            super(msg);
        }
    }
}