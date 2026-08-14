package study.contest.cses;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * CSES Problem Set: Sliding Window Mode
 *
 * <p>For every window of {@code k} elements, print its most frequent value. If multiple values have
 * the same maximum frequency, print the smallest one.
 *
 * <p>Constraints: {@code 1 <= k <= n <= 2 * 10^5}, {@code 1 <= x[i] <= 10^9}.
 *
 * <p>Example: {@code [1, 2, 3, 2, 5, 2, 4, 4]}, {@code k = 3} produces {@code 1 2 2 2 2 4}.
 */
public class SlidingWindowMode {
    public int[] slidingWindowMode(int[] nums, int k) {
        DynamicMode dynamicMode = new DynamicMode();

        int left = 0;
        int right = 0;
        int[] result = new int[nums.length - k + 1];
        while (right < nums.length) {
            while (right < left + k) {
                dynamicMode.insert(nums[right]);
                right++;
            }

            result[left] = dynamicMode.getMode();

            dynamicMode.remove(nums[left]);
            left++;
        }
        return result;
    }

    static class DynamicMode {
        TreeSet<Entry> sortedFrequencies;
        Map<Integer, Integer> frequencies;

        DynamicMode() {
            sortedFrequencies =
                    new TreeSet<>(
                            (a, b) -> {
                                if (a.value() != b.value()) {
                                    return Integer.compare(a.value(), b.value());
                                } else {
                                    return Integer.compare(b.key(), a.key());
                                }
                            });
            frequencies = new HashMap<>();
        }

        void insert(int num) {
            int existing = frequencies.getOrDefault(num, 0);
            frequencies.put(num, existing + 1);

            Entry oldEntry = new Entry(num, existing);
            if (existing > 0) sortedFrequencies.remove(oldEntry);
            sortedFrequencies.add(new Entry(num, existing + 1));
        }

        void remove(int num) {
            int existing = frequencies.get(num);
            if (existing > 1) frequencies.put(num, existing - 1);
            else frequencies.remove(num);

            Entry oldEntry = new Entry(num, existing);
            sortedFrequencies.remove(oldEntry);
            if (existing > 1) sortedFrequencies.add(new Entry(num, existing - 1));
        }

        int getMode() {
            return sortedFrequencies.last().key();
        }

        static class Entry {
            int key, value;

            Entry(int key, int value) {
                this.key = key;
                this.value = value;
            }

            int key() {
                return key;
            }

            int value() {
                return value;
            }

            public boolean equals(Object o) {
                if (o instanceof Entry entry) {
                    return key == entry.key() && value == entry.value();
                }
                return false;
            }
        }
    }
}
