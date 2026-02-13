package study.neetcode.coreskills.heap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HeapTest {
    @Test
    void createHeap() {
        Heap h = new HeapImpl();
        assertNotNull(h);
    }

    @Test
    void createHeap_size0() {
        Heap h = new HeapImpl();
        assertNotNull(h);
        assertEquals(0, h.getSize());
    }

    @Test
    void emptyHeap_get_returnsException() {
        Heap h = new HeapImpl();
        assertThrows(HeapException.class, () -> h.get());
    }

    @Test
    void addToHeap() {
        Heap h = new HeapImpl();
        h.push(1);
        assertEquals(1, h.getSize());
    }

    @Test
    void addAndGet() {
        Heap h = new HeapImpl();
        h.push(1);

        assertEquals(1, h.get());

        assertEquals(0, h.getSize());
    }

    @Test
    void addTwiceAndGetReturnsMinimum() {
        Heap h = new HeapImpl();
        h.push(1);
        h.push(2);

        assertEquals(1, h.get());
        assertEquals(2, h.get());
        assertEquals(0, h.getSize());
    }
}
