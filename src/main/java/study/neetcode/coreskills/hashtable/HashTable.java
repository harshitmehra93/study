package study.neetcode.coreskills.hashtable;

public interface HashTable {
    int getSize();

    int getCapacity();

    int get(int key);

    Boolean put(int key,int value);

  boolean remove(int key);
}
