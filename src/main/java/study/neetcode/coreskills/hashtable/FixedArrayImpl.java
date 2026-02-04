package study.neetcode.coreskills.hashtable;

public class FixedArrayImpl<T> implements FixedArray<T> {
    private final Object[] arr;

    public FixedArrayImpl(int length) {
        if (length <= 0) throw new IllegalArgumentException("length must be > 0");
        this.arr = new Object[length];
    }

    public int length() {
        return arr.length;
    }

    @SuppressWarnings("unchecked")
    public T get(int i) {
        if (i < 0 || i >= arr.length) throw new IndexOutOfBoundsException();
        return (T) arr[i];
    }

    public void set(int i, T value) {
        if (i < 0 || i >= arr.length) throw new IndexOutOfBoundsException();
        arr[i] = value;
    }
}
