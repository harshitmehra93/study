package study.contest.leetcode.weeklycontest420;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 3326 — Minimum Division Operations to Make Array Non Decreasing
 *
 * <p>In one operation, replace an array element by the result of dividing it by its greatest proper
 * divisor. Return the minimum operations needed to make the array non-decreasing, or {@code -1}
 * when this is impossible.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: nums = [25,7]
 * Output: 1
 *
 * Input: nums = [7,7,6]
 * Output: -1
 *
 * Input: nums = [1,1,1,1]
 * Output: 0
 * </pre>
 *
 * <p>Constraints: {@code 1 <= nums.length <= 100000}, {@code 1 <= nums[i] <= 1000000}.
 *
 * @see <a
 *     href="https://leetcode.com/problems/minimum-division-operations-to-make-array-non-decreasing/">Problem</a>
 */
public class MinimumDivisionOperationsToMakeArrayNonDecreasing {
    static {
        setup();
    }

    static List<Integer> primeNumbers;

    static void setup() {
        primeNumbers = new ArrayList<>();
        primeNumbers.add(2);
        primeNumbers.add(3);

        for (int num = 4; num < 1000000; num++) {
            boolean isPrime = true;
            int sq = (int) Math.sqrt(num);
            int lastPrime = 2;
            for (var prime : primeNumbers) {
                if (prime > sq) break;
                if (num % prime == 0) {
                    isPrime = false;
                    break;
                }
                lastPrime = prime;
            }
            if (!isPrime) continue;
            int divisor = lastPrime + 1;
            while (divisor <= sq) {
                if (num % divisor == 0) {
                    isPrime = false;
                    break;
                }
                divisor++;
            }
            if (isPrime) primeNumbers.add(num);
        }
    }

    public int minOperations(int[] nums) {
        int left = nums.length - 2;
        int right = nums.length - 1;
        int minOperations = 0;
        while (left >= 0) {
            if (nums[right] >= nums[left]) {
                left--;
                right--;
                continue;
            }

            while (nums[right] < nums[left]) {
                int perfectDivisor = nums[left] / findSmallestDivisiblePrime(nums[left]);
                if (perfectDivisor == 1) return -1;
                minOperations++;
                nums[left] = nums[left] / perfectDivisor;
            }

            left--;
            right--;
        }
        return minOperations;
    }

    public int findSmallestDivisiblePrime(int num) {
        for (var prime : primeNumbers) {
            if (prime > num) continue;
            if (num % prime == 0) return prime;
        }
        return num;
    }
}
