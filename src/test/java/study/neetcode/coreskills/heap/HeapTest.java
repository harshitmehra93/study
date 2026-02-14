package study.neetcode.coreskills.heap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeapTest {

    private Heap h;

    @BeforeEach
    private void setup() {
        h = new MinHeapArrayImpl();
    }

    @Test
    void createHeap_size0() {
        assertNotNull(h);
        assertEquals(0, h.getSize());
    }

    @Test
    void emptyHeap_get_returnsException() {
        assertThrows(HeapException.class, () -> h.get());
    }

    @Test
    void addToHeap() {
        h.push(1);
        assertEquals(1, h.getSize());
    }

    @Test
    void addAndGet() {
        h.push(1);

        assertEquals(1, h.get());
        assertEquals(0, h.getSize());
    }

    @Test
    void addTwiceAndGetReturnsMinimum() {
        h.push(1);
        h.push(2);

        assertEquals(1, h.get());
        assertEquals(2, h.get());
        assertEquals(0, h.getSize());
    }

    @Test
    void addThriceAndGetReturnsMinimum() {
        h.push(3);
        h.push(1);
        h.push(2);

        assertEquals(3, h.getSize());
        assertEquals(1, h.get());
        assertEquals(2, h.get());
        assertEquals(3, h.get());
        assertEquals(0, h.getSize());
    }

    @Test
    void addRandom_returnsSorted() {
        IntStream i = IntStream.generate(() -> (int) (Math.random() * 10000)).limit(10000);
        i.forEach(h::push);

        assertHeapIsSorted();
    }

    @Test
    void arrayConstructorTest() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        h = new MinHeapArrayImpl(arr);
        assertHeapIsSorted();
    }

    private void assertHeapIsSorted() {
        while (h.getSize() > 1) {
            Integer first = h.get();
            Integer second = h.get();
            assertTrue(first <= second);
        }
    }
}
