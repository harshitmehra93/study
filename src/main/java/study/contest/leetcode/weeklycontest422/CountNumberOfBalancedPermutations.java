package study.contest.leetcode.weeklycontest422;

import java.util.Arrays;

/**
 * LeetCode 3343 — Count Number of Balanced Permutations
 *
 * <p>Return, modulo {@code 10^9 + 7}, the number of distinct permutations of a digit string whose
 * even-index digit sum equals its odd-index digit sum.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: num = "123"
 * Output: 2
 *
 * Input: num = "112"
 * Output: 1
 *
 * Input: num = "12345"
 * Output: 0
 * </pre>
 *
 * <p>Constraints: {@code 2 <= num.length() <= 80}; {@code num} contains only digits.
 *
 * @see <a href="https://leetcode.com/problems/count-number-of-balanced-permutations/">Problem</a>
 */
public class CountNumberOfBalancedPermutations {
    int[] frequency;
    int[] frequencyValuePrefixSum;
    int[] frequencyPrefixSum;
    int N;
    static final int MOD = 1_000_000_007;
    long[][] nCr;
    int memo[][][];

    public int countBalancedPermutations(String str) {
        var chars = str.toCharArray();
        N = str.length();
        frequency = new int[10];
        for (int i = 0; i < N; i++) {
            int digit = Integer.parseInt(chars[i] + "");
            frequency[digit] += 1;
        }

        frequencyValuePrefixSum = new int[10];
        frequencyPrefixSum = new int[10];

        int frequencyValue = 0;
        int frequencySum = 0;
        for (int i = 0; i < 10; i++) {
            frequencySum += frequency[i];
            frequencyPrefixSum[i] = frequencySum;

            frequencyValue += (frequency[i] * i);
            frequencyValuePrefixSum[i] = frequencyValue;
        }

        buildNcr();

        int evenPlaces = N % 2 == 0 ? N / 2 : N / 2 + 1;

        memo = new int[10][frequencyValuePrefixSum[9] + 1][N + 1];
        for (int[][] twoD : memo) {
            for (int[] arr : twoD) {
                Arrays.fill(arr, -1);
            }
        }

        return placeDigits(0, 0, evenPlaces);
    }

    void buildNcr() {
        nCr = new long[N + 1][N + 1];

        for (int n = 0; n <= N; n++) {
            nCr[n][0] = 1;
            nCr[n][n] = 1;

            for (int r = 1; r < n; r++) {
                nCr[n][r] = (nCr[n - 1][r - 1] + nCr[n - 1][r]) % MOD;
            }
        }
    }

    int placeDigits(int digit, int evenSum, int evenPlacesLeft) {
        int oddSum = digit == 0 ? 0 : frequencyValuePrefixSum[digit - 1] - evenSum;
        int oddPlacesLeft =
                digit == 0
                        ? N - evenPlacesLeft
                        : N - frequencyPrefixSum[digit - 1] - evenPlacesLeft;
        if (digit == 10) {
            if (evenSum == oddSum) return 1;
            return 0;
        }
        if (memo[digit][evenSum][evenPlacesLeft] != -1) return memo[digit][evenSum][evenPlacesLeft];
        if (frequency[digit] == 0) {
            return memo[digit][evenSum][evenPlacesLeft] =
                    placeDigits(digit + 1, evenSum, evenPlacesLeft);
        }

        int count = 0;
        for (int i = 0; i <= frequency[digit]; i++) {
            int evenSelection = i;
            int oddSelection = frequency[digit] - i;

            if (evenSelection > evenPlacesLeft) continue;
            if (oddSelection > oddPlacesLeft) continue;

            int evenContribution = evenSelection * digit;
            int oddContribution = oddSelection * digit;

            int result =
                    placeDigits(
                            digit + 1, evenSum + evenContribution, evenPlacesLeft - evenSelection);

            long ways = nCr[evenPlacesLeft][evenSelection] * nCr[oddPlacesLeft][oddSelection] % MOD;

            count = (int) ((count + ways * result) % MOD);
        }
        return memo[digit][evenSum][evenPlacesLeft] = count;
    }

    int nCr(int n, int r) {
        return fact(n) / (fact(r) * fact(n - r));
    }

    int fact(int n) {
        if (n == 0) return 1;
        return n * fact(n - 1);
    }
}
