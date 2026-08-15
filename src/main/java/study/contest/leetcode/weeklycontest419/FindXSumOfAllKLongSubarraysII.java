package study.contest.leetcode.weeklycontest419;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * LeetCode 3321 — Find X-Sum of All K-Long Subarrays II
 *
 * <p>The x-sum keeps all occurrences of the {@code x} most frequent distinct values. A frequency
 * tie favors the larger value. Return the x-sum of every length-{@code k} subarray.
 *
 * <p>Constraints: {@code 1 <= nums.length <= 10^5}, {@code 1 <= nums[i] <= 10^9}, and {@code 1 <= x
 * <= k <= nums.length}.
 *
 * <p>Example: {@code nums = [1,1,2,2,3,4,2,3]}, {@code k = 6}, {@code x = 2} produces {@code
 * [6,10,12]}.
 *
 * @see <a href="https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-ii/">Official
 *     problem</a>
 */
public class FindXSumOfAllKLongSubarraysII {
    public long[] findXSum(int[] nums, int k, int x) {
        int left = 0;
        int right = 0;
        TopX topX = new TopX(x);
        long[] answer = new long[nums.length - k + 1];
        while (right < nums.length) {
            while (right < left + k) {
                topX.insert(nums[right]);
                right++;
            }

            answer[left] = topX.getTopXSum();

            topX.remove(nums[left]);
            left++;
        }
        return answer;
    }

    static class TopX {
        DynamicFrequencyMap topX;
        DynamicFrequencyMap rest;
        Map<Integer, Integer> freqMap;
        int X;

        TopX(int x) {
            this.X = x;
            topX = new DynamicFrequencyMap();
            rest = new DynamicFrequencyMap();
            freqMap = new HashMap<>();
        }

        void insert(int num) {
            update(num, 1);
        }

        void remove(int num) {
            update(num, -1);
        }

        void update(int num, int change) {
            int existingFreq = freqMap.getOrDefault(num, 0);
            if (existingFreq > 0) {
                DynamicFrequencyMap.Entry existingEntry =
                        new DynamicFrequencyMap.Entry(num, existingFreq);
                if (topX.contains(existingEntry)) {
                    topX.remove(existingEntry);
                } else {
                    rest.remove(existingEntry);
                }
            }

            int newFreq = existingFreq + change;
            if (newFreq == 0) {
                freqMap.remove(num);
            } else {
                freqMap.put(num, newFreq);
                rest.insert(new DynamicFrequencyMap.Entry(num, newFreq));
            }

            balance();
        }

        void balance() {
            while (topX.getSize() < X && rest.getSize() > 0) {
                DynamicFrequencyMap.Entry mostFrequentInRest = rest.pollMostFrequent();
                topX.insert(mostFrequentInRest);
            }

            while (topX.getSize() > X) {
                DynamicFrequencyMap.Entry leastFrequentInTopX = topX.pollLeastFrequent();
                rest.insert(leastFrequentInTopX);
            }

            while (topX.getSize() > 0
                    && rest.getSize() > 0
                    && DynamicFrequencyMap.compare(topX.getLeastFrequent(), rest.getMostFrequent())
                            < 0) {
                DynamicFrequencyMap.Entry leastFrequentInTopX = topX.pollLeastFrequent();
                DynamicFrequencyMap.Entry mostFrequentInRest = rest.pollMostFrequent();
                topX.insert(mostFrequentInRest);
                rest.insert(leastFrequentInTopX);
            }
        }

        long getTopXSum() {
            return topX.getSum();
        }
    }

    static class DynamicFrequencyMap {
        TreeSet<Entry> reverseMap;
        long sum;

        record Entry(int num, int freq) {}

        DynamicFrequencyMap() {
            reverseMap = new TreeSet<>(DynamicFrequencyMap::compare);
            sum = 0;
        }

        static int compare(Entry a, Entry b) {
            if (a.freq() != b.freq()) {
                return Integer.compare(a.freq(), b.freq());
            } else {
                return Integer.compare(a.num(), b.num());
            }
        }

        void insert(Entry entry) {
            reverseMap.add(entry);
            sum += (long) entry.num() * entry.freq();
        }

        void remove(Entry entry) {
            reverseMap.remove(entry);
            sum -= (long) entry.num() * entry.freq();
        }

        Entry pollLeastFrequent() {
            Entry leastFrequent = reverseMap.first();
            remove(leastFrequent);
            return leastFrequent;
        }

        Entry pollMostFrequent() {
            Entry mostFrequent = reverseMap.last();
            remove(mostFrequent);
            return mostFrequent;
        }

        Entry getLeastFrequent() {
            return reverseMap.first();
        }

        Entry getMostFrequent() {
            return reverseMap.last();
        }

        boolean contains(Entry entry) {
            return reverseMap.contains(entry);
        }

        long getSum() {
            return sum;
        }

        int getSize() {
            return reverseMap.size();
        }
    }
}
