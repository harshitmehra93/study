package study.neetcode.coreskills.hashtable;

public interface FixedArray<T> {
    int length();

    T get(int i);

    void set(int i, T value);
}
