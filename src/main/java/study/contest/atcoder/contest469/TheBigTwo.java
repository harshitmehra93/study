package study.contest.atcoder.contest469;

import java.io.*;
import java.util.*;

/**
 * AtCoder Beginner Contest 469 — D: The Big Two
 *
 * <p>There are {@code N} players and {@code M} tournaments. The finalists in tournament
 * {@code m} are players {@code A[m]} and {@code B[m]}. Count the pairs of players {@code (x, y)}
 * with {@code x < y} such that every tournament final contains at least one of {@code x} or
 * {@code y}.
 *
 * <p>Input:
 *
 * <pre>
 * N M
 * A1 B1
 * A2 B2
 * ...
 * AM BM
 * </pre>
 *
 * <p>Output the number of qualifying player pairs.
 *
 * <p>Constraints: {@code 2 <= N <= 2 * 10^5}, {@code 1 <= M <= 2 * 10^5}, and
 * {@code 1 <= A[i] < B[i] <= N}.
 *
 * <p>Examples:
 *
 * <pre>
 * Input:
 * 5 5
 * 1 2
 * 3 4
 * 1 3
 * 2 3
 * 2 5
 * Output:
 * 1
 *
 * Input:
 * 7 8
 * 2 4
 * 1 3
 * 1 7
 * 1 3
 * 1 2
 * 1 6
 * 1 5
 * 1 3
 * Output:
 * 2
 *
 * Input:
 * 5 8
 * 1 2
 * 2 4
 * 1 3
 * 1 3
 * 1 2
 * 1 2
 * 1 5
 * 1 2
 * Output:
 * 2
 * </pre>
 *
 * @see <a href="https://atcoder.jp/contests/abc469/tasks/abc469_d?lang=en">Official problem</a>
 */
public class TheBigTwo {

    static final FastScanner fs = new FastScanner(System.in);
    static final PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int N = fs.nextInt();
        int M = fs.nextInt();
        int[][] finalists = new int[M][2];
        for (int i = 0; i < M; i++) {
            finalists[i][0] = fs.nextInt();
            finalists[i][1] = fs.nextInt();
        }

        int eligible = 0;
        for (var target : finalists) {
            if (target[0] < target[1]) {
                if (isInAllFinals(target, finalists)) eligible++;
            }
        }

        out.print(eligible);
    }

    static boolean isInAllFinals(int[] target, int[][] finalists) {
        for (var finalist : finalists) {
            if (target[0] != finalist[0]
                    && target[0] != finalist[1]
                    && target[1] != finalist[0]
                    && target[1] != finalist[1]) return false;
        }
        return true;
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
