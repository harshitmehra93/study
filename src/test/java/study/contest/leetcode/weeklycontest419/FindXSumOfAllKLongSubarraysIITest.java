package study.contest.leetcode.weeklycontest419;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.Test;

class FindXSumOfAllKLongSubarraysIITest {

    private final FindXSumOfAllKLongSubarraysII solver = new FindXSumOfAllKLongSubarraysII();

    @Test
    void matchesOfficialExamples() {
        assertArrayEquals(
                new long[] {6, 10, 12}, solver.findXSum(new int[] {1, 1, 2, 2, 3, 4, 2, 3}, 6, 2));
        assertArrayEquals(
                new long[] {11, 15, 15, 15, 12},
                solver.findXSum(new int[] {3, 8, 7, 8, 7, 5}, 2, 2));
    }

    @Test
    void movesAnEntireFrequencyGroupAcrossTheBoundary() {
        assertArrayEquals(new long[] {4}, solver.findXSum(new int[] {1, 1, 2, 2}, 4, 1));
    }

    @Test
    void usesLongForTheSelectedContribution() {
        int[] nums = new int[100_000];
        Arrays.fill(nums, 1_000_000_000);

        assertArrayEquals(new long[] {100_000_000_000_000L}, solver.findXSum(nums, nums.length, 1));
    }

    @Test
    void matchesBruteForceAcrossRandomWindows() {
        Random random = new Random(3_321L);

        for (int test = 0; test < 10_000; test++) {
            int length = random.nextInt(10) + 1;
            int k = random.nextInt(length) + 1;
            int x = random.nextInt(k) + 1;
            int[] nums = new int[length];
            for (int index = 0; index < length; index++) {
                nums[index] = random.nextInt(7) + 1;
            }

            assertArrayEquals(bruteForce(nums, k, x), solver.findXSum(nums, k, x));
        }
    }

    private static long[] bruteForce(int[] nums, int k, int x) {
        long[] answer = new long[nums.length - k + 1];

        for (int left = 0; left + k <= nums.length; left++) {
            Map<Integer, Integer> frequencies = new HashMap<>();
            for (int index = left; index < left + k; index++) {
                frequencies.merge(nums[index], 1, Integer::sum);
            }

            List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequencies.entrySet());
            entries.sort(
                    (first, second) -> {
                        if (!first.getValue().equals(second.getValue())) {
                            return Integer.compare(second.getValue(), first.getValue());
                        }
                        return Integer.compare(second.getKey(), first.getKey());
                    });

            for (int index = 0; index < Math.min(x, entries.size()); index++) {
                Map.Entry<Integer, Integer> entry = entries.get(index);
                answer[left] += (long) entry.getKey() * entry.getValue();
            }
        }

        return answer;
    }
}
