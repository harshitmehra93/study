package study.neetcode.coreskills.singlylinkedlist;

public interface SinglyLinkedList {
    int get(int i);

    int getSize();

    void insertHead(int i);

    void insertTail(int i);

    boolean remove(int i);

    int[] getValues();
}
