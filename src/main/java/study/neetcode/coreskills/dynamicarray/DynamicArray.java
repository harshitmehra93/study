package study.neetcode.coreskills.dynamicarray;

public interface DynamicArray {
    int DEFAULT_CAPACITY = 50;

    int getCapacity();

    int getSize();

    void pushback(int i);

    int get(int i);

    int popback();

    void set(int i, int i1);

    void resize();
}
