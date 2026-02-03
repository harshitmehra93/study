package study.neetcode.coreskills.hashtable;

import study.neetcode.coreskills.generic.dynamicarray.DynamicArray;
import study.neetcode.coreskills.generic.dynamicarray.DynamicArrayImpl;

import java.util.LinkedList;
import java.util.List;

public class HashTableImpl implements HashTable {
    float size;
    float capacity;
    DynamicArray<List<Entry>> dynamicArray;
    HashTableImpl(){
        capacity=8.0f;
        dynamicArray = new DynamicArrayImpl((int)capacity);
    }
    HashTableImpl(int capacity){
        this.capacity=capacity;
        dynamicArray = new DynamicArrayImpl((int)capacity);
    }

    @Override
    public float getSize() {
        return size;
    }

    @Override
    public float getCapacity() {
        return capacity;
    }

    @Override
    public int get(int i) {
        List<Entry> list = dynamicArray.get(hash(i));
        if(list!=null){
            return list.stream().filter(e->e.getKey()==i).findFirst().get().getValue();
        }
        return -1;
    }

    @Override
    public Boolean put(int key, int value) {
        if(get(key)==-1){
            int index = hash(key);
            var list = new LinkedList<Entry>();
            list.add(new Entry(key,value));
            dynamicArray.set(index,list);
        }else {
            List<Entry> list = dynamicArray.get(hash(key));
            var entry = list.stream().filter(e->e.getKey()==key).findFirst().get();
            entry.setValue(value);
        }
        incrementSize();
        return Boolean.TRUE;
    }

    private int hash(int key) {
        int cap = (int)capacity;
        return ((key%cap)+cap)%cap;
    }

    private void incrementSize() {
        size++;
        if(needsCapacityIncrease()){
            capacity=capacity*2;
        }
    }

    boolean needsCapacityIncrease(){
        return size / capacity > 0.75;
    }

    class Entry implements Comparable<Entry>{
        private int key;
        private int value;
        Entry(){}
        Entry(int k, int v){key=k;value=v;}
        int getKey(){return key;}
        int setValue(int value){return this.value=value;}
        int getValue(){return value;}
        @Override
        public int compareTo(Entry entry) {
            return this.key-entry.getKey();
        }
    }
}
