package study.coreskills.fenwick;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Counts inversions in an integer array.
 *
 * <p>An inversion is a pair of indices {@code (i, j)} such that {@code i < j} and {@code nums[i] >
 * nums[j]}. Equal values do not form an inversion.
 *
 * <h2>Examples</h2>
 *
 * <pre>{@code
 * Input:  [5, 2, 6, 1]
 * Output: 4
 * Explanation: The inversions are (5, 2), (5, 1), (2, 1), and (6, 1).
 *
 * Input:  [1, 2, 3, 4]
 * Output: 0
 * Explanation: The array is already sorted.
 *
 * Input:  [2, 1, 3, 1, 2]
 * Output: 4
 * Explanation: Equal values are not inversions.
 * }</pre>
 *
 * <p>Implement this with a Fenwick tree in {@code O(n log n)} time and {@code O(n)} auxiliary
 * space. Do not modify the supplied array. The result is a {@code long} because an array can have
 * more than {@link Integer#MAX_VALUE} inversions.
 */
public class CountInversions {

    public long countInversions(int[] nums) {
        if (nums.length == 0) return 0L;
        int[] copy = nums.clone();
        Arrays.sort(copy);
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 1;
        for (int i = 0; i < copy.length; i++) {
            if (ranks.containsKey(copy[i])) continue;
            ranks.put(copy[i], rank);
            rank++;
        }
        int[] rankedArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            rankedArr[i] = ranks.get(nums[i]);
        }
        Fenwick ft = new Fenwick(ranks.size());
        long inversions = 0;

        for (int i = rankedArr.length - 1; i >= 0; i--) {
            int currentRank = rankedArr[i];

            inversions += ft.sum(currentRank - 1);
            ft.insert(currentRank, 1);
        }

        return inversions;
    }

    static class Fenwick {
        long[] ft;
        int size;

        Fenwick(int size) {
            this.ft = new long[size + 1];
            this.size = size;
        }

        void insert(int index, int value) {
            int next = index;
            while (next <= size) {
                ft[next] += value;
                next = next + (next & -next);
            }
        }

        long sum(int index) {
            int parent = index;
            long sum = 0;
            while (parent > 0) {
                sum += ft[parent];
                parent = parent - (parent & -parent);
            }
            return sum;
        }
    }
}
