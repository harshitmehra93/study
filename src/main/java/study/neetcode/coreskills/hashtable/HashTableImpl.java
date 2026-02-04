package study.neetcode.coreskills.hashtable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class HashTableImpl implements HashTable {
    int size;
    int capacity;
    FixedArray<List<Entry>> buckets;

    public HashTableImpl() {
        capacity = 8;
        buckets = createBucketsContainer(capacity);
    }

    public HashTableImpl(int capacity) {
        this.capacity = capacity;
        buckets = createBucketsContainer(capacity);
    }

    private FixedArray<List<Entry>> createBucketsContainer(int capacity) {
        return new FixedArrayImpl<>(capacity);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int get(int key) {
        List<Entry> list = buckets.get(hash(key));
        if (list != null) {
            return list.stream()
                    .filter(e -> e.getKey() == key)
                    .map(Entry::getValue)
                    .findFirst()
                    .orElse(-1);
        }
        return -1;
    }

    @Override
    public Boolean put(int key, int value) {
        int index = hash(key);
        List<Entry> existingList = buckets.get(index);

        if (existingList == null) {
            var newList = new LinkedList<Entry>();
            newList.add(new Entry(key, value));
            buckets.set(index, newList);
            incrementSize();
        } else {
            Optional<Entry> entry =
                    existingList.stream().filter(e -> e.getKey() == key).findFirst();
            entry.ifPresent(entry1 -> entry1.setValue(value));

            if (entry.isEmpty()) {
                existingList.add(new Entry(key, value));
                incrementSize();
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean remove(int key) {
        int index = hash(key);
        var list = buckets.get(index);
        if (list == null) return false;

        for (var it = list.iterator(); it.hasNext(); ) {
            if (it.next().getKey() == key) {
                it.remove();
                size--;
                if (list.isEmpty()) buckets.set(index, null);
                return true;
            }
        }
        return false;
    }

    private int hash(int key) {
        return ((key % capacity) + capacity) % capacity;
    }

    private void incrementSize() {
        size++;
        if (needsRehash()) {
            rehash();
        }
    }

    private void rehash() {
        var oldBuckets = buckets;
        capacity = capacity * 2;
        size = 0;
        buckets = createBucketsContainer(capacity);
        for (int i = 0; i < oldBuckets.length(); i++) {
            var list = oldBuckets.get(i);
            if (list != null) {
                list.forEach(e -> put(e.getKey(), e.getValue()));
            }
        }
    }

    boolean needsRehash() {
        return (double) size / capacity >= 0.75;
    }

    static class Entry {
        private final int key;
        private int value;

        Entry(int k, int v) {
            key = k;
            value = v;
        }

        int getKey() {
            return key;
        }

        void setValue(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }
    }
}
