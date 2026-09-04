package study.ocp.concurrency;

import java.util.*;

public class ParallelStreamTiming {
    public static void main(String[] args) {
        long before = System.currentTimeMillis();

        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        // List<Integer> nums = List.of(1,2,3,4,5);
        nums.stream()
                .unordered()
                .parallel()
                .skip(10)
                .map(n -> job(n))
                .forEach(n -> System.out.println("Last of " + n));

        long after = System.currentTimeMillis();
        long result = (after - before) / 1000;
        System.out.println("Time taken: " + result);
    }

    static Integer job(Integer i) {
        System.out.println("Item " + i);
        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
        }
        return i;
    }
}
