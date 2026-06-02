package study.neetcode.interview.heapqueuearray;

import java.util.Comparator;
import java.util.PriorityQueue;

// COPIED FROM CHATGPT Do this again yourself
public class MedianFinder {
    private final PriorityQueue<Integer> maxHeap;
    private final PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // smaller half
        minHeap = new PriorityQueue<>(); // larger half
    }

    public void addNum(int num) {
        maxHeap.offer(num);

        minHeap.offer(maxHeap.poll());

        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }

        return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}
