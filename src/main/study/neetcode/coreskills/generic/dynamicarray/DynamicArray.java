package study.neetcode.coreskills.generic.dynamicarray;

public interface DynamicArray<U> {
    int DEFAULT_CAPACITY = 50;
    int getCapacity();

    int getSize();

    void pushback(U i);

    U get(int i);

    U popback();

    void set(int i, U i1);
    void resize();
}
