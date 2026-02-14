package study.neetcode.coreskills.heap;

public interface Heap {
    int getSize();

    Integer get();

    void push(int i);
}

class HeapException extends RuntimeException {
    HeapException(String msg) {
        super(msg);
    }
}
