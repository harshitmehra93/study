package study.contest.atcoder.contest470;

import java.io.*;
import java.util.*;

/**
 * AtCoder Beginner Contest 470 — B: Monocolor
 *
 * <p>Given the colors of {@code N} balls, find the minimum number whose colors must be changed to
 * make every ball the same color.
 *
 * <p>Constraints: {@code 1 <= N <= 100}; {@code 1 <= C[i] <= N}.
 *
 * <p>Example: for {@code N = 4} and colors {@code 3 1 2 1}, the answer is {@code 2}.
 *
 * @see <a href="https://atcoder.jp/contests/abc470/tasks/abc470_b?lang=en">Official problem</a>
 */
public class Monocolor {

    static final FastScanner fs = new FastScanner(System.in);
    static final PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int N = fs.nextInt();

        if (N < 2) {
            out.print(0);
            return;
        }

        int[] C = new int[N + 1];

        for (int i = 0; i < N; i++) {
            C[i] = fs.nextInt();
        }

        int maxFrequencyOfAnyGivenColor = 0;
        int[] frequency = new int[N + 1];
        for (int i = 0; i < N; i++) {
            int color = C[i];
            frequency[color] = frequency[color] + 1;
            if (frequency[color] > maxFrequencyOfAnyGivenColor) {
                maxFrequencyOfAnyGivenColor = frequency[color];
            }
        }

        // we keep the most frequent color as the base and change everything else.
        int result = N - maxFrequencyOfAnyGivenColor;
        out.print(result);
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
