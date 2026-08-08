package study.contest.atcoder.contest469;

import java.io.*;
import java.util.*;

/**
 * AtCoder Beginner Contest 469 — C: Cantrip
 *
 * <p>There are {@code N} bags of sweets in a row. Bag {@code i} is a hit when {@code S[i]} is
 * {@code o}, and a miss when it is {@code x}. For every {@code k} from {@code 1} to {@code N},
 * first take the first {@code k} bags and eat their sweets. Then, while an unclaimed bag remains
 * and at least one held bag is a hit, discard one held hit bag, take the next bag, and eat its
 * sweets. Find the total number of sweets eaten for every {@code k}.
 *
 * <p>Input:
 *
 * <pre>
 * N
 * S
 * </pre>
 *
 * <p>Output {@code N} lines; line {@code k} contains the answer for that value of {@code k}.
 *
 * <p>Constraints: {@code 1 <= N <= 8 * 10^5}; {@code S} has length {@code N} and consists of
 * {@code o} and {@code x}.
 *
 * <p>Examples:
 *
 * <pre>
 * Input:
 * 5
 * oxoxo
 * Output:
 * 2
 * 4
 * 5
 * 5
 * 5
 *
 * Input:
 * 3
 * ooo
 * Output:
 * 3
 * 3
 * 3
 *
 * Input:
 * 1
 * x
 * Output:
 * 1
 * </pre>
 *
 * @see <a href="https://atcoder.jp/contests/abc469/tasks/abc469_c?lang=en">Official problem</a>
 */
public class Cantrip {

    static final FastScanner fs = new FastScanner(System.in);
    static final PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int N = fs.nextInt();
        String S = fs.next();
        char[] arr = S.toCharArray();
        int[] hitsPrefixSum = new int[N];
        int hitsInBag = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] == 'o') hitsInBag++;
            hitsPrefixSum[i] = hitsInBag;
        }

        for (int k = 1; k <= N; k++) {
            int sweetsEaten = getSweetsEaten(arr, N, k, hitsPrefixSum);
            out.println(sweetsEaten);
        }
    }

    static int getSweetsEaten(char[] arr, int N, int k, int[] hitsPrefixSum) {
        int sweetsEaten = k;
        int hitsInBag = hitsPrefixSum[k - 1];

        int nextCandidateIndex = k;
        while (hitsInBag > 0 && nextCandidateIndex < N) {
            hitsInBag--;
            sweetsEaten++;

            char candidate = arr[nextCandidateIndex];
            if (candidate == 'o') {
                hitsInBag++;
            }
            nextCandidateIndex++;
        }
        return sweetsEaten;
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];

        private int ptr = 0;
        private int len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() {
            if (ptr >= len) {
                try {
                    len = in.read(buffer);
                    ptr = 0;
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }

                if (len <= 0) {
                    return -1;
                }
            }

            return buffer[ptr++];
        }

        String next() {
            StringBuilder sb = new StringBuilder();
            int c;

            do {
                c = read();
            } while (c <= ' ' && c != -1);

            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
