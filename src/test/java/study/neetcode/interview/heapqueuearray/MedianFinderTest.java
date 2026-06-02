package study.neetcode.interview.heapqueuearray;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MedianFinderTest {

    @Test
    void test_givenExample() {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(1);
        medianFinder.addNum(2);

        assertEquals(1.5, medianFinder.findMedian());

        medianFinder.addNum(3);

        assertEquals(2.0, medianFinder.findMedian());
    }

    @Test
    void test_singleElement() {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(10);

        assertEquals(10.0, medianFinder.findMedian());
    }

    @Test
    void test_oddNumberOfElements() {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(5);
        medianFinder.addNum(1);
        medianFinder.addNum(9);

        /*
         * Sorted: [1, 5, 9]
         * Median = 5
         */
        assertEquals(5.0, medianFinder.findMedian());
    }

    @Test
    void test_evenNumberOfElements() {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(5);
        medianFinder.addNum(1);
        medianFinder.addNum(9);
        medianFinder.addNum(3);

        /*
         * Sorted: [1, 3, 5, 9]
         * Median = (3 + 5) / 2 = 4
         */
        assertEquals(4.0, medianFinder.findMedian());
    }

    @Test
    void test_increasingOrderInsertions() {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(1);
        assertEquals(1.0, medianFinder.findMedian());

        medianFinder.addNum(2);
        assertEquals(1.5, medianFinder.findMedian());

        medianFinder.addNum(3);
        assertEquals(2.0, medianFinder.findMedian());

        medianFinder.addNum(4);
        assertEquals(2.5, medianFinder.findMedian());

        medianFinder.addNum(5);
        assertEquals(3.0, medianFinder.findMedian());
    }

    @Test
    void test_decreasingOrderInsertions() {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(5);
        assertEquals(5.0, medianFinder.findMedian());

        medianFinder.addNum(4);
        assertEquals(4.5, medianFinder.findMedian());

        medianFinder.addNum(3);
        assertEquals(4.0, medianFinder.findMedian());

        medianFinder.addNum(2);
        assertEquals(3.5, medianFinder.findMedian());

        medianFinder.addNum(1);
        assertEquals(3.0, medianFinder.findMedian());
    }

    @Test
    void test_negativeNumbers() {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(-1);
        medianFinder.addNum(-2);
        medianFinder.addNum(-3);

        /*
         * Sorted: [-3, -2, -1]
         */
        assertEquals(-2.0, medianFinder.findMedian());
    }

    @Test
    void test_mixedPositiveAndNegativeNumbers() {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(-5);
        medianFinder.addNum(10);
        medianFinder.addNum(0);
        medianFinder.addNum(3);

        /*
         * Sorted: [-5, 0, 3, 10]
         * Median = (0 + 3) / 2 = 1.5
         */
        assertEquals(1.5, medianFinder.findMedian());
    }

    @Test
    void test_duplicates() {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(2);
        medianFinder.addNum(2);
        medianFinder.addNum(2);
        medianFinder.addNum(2);

        assertEquals(2.0, medianFinder.findMedian());
    }

    @Test
    void test_largeValues_avoidIntegerOverflowInAverage() {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(Integer.MAX_VALUE);
        medianFinder.addNum(Integer.MAX_VALUE);

        /*
         * If implementation does:
         * (maxHeap.peek() + minHeap.peek()) / 2.0
         * using int addition first, it can overflow.
         *
         * Safer:
         * ((double) maxHeap.peek() + minHeap.peek()) / 2.0
         */
        assertEquals((double) Integer.MAX_VALUE, medianFinder.findMedian());
    }
}
