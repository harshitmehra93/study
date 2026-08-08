package study.contest.atcoder.contest469;

import java.io.*;
import java.util.*;

/**
 * AtCoder Beginner Contest 469 — A: Train Car
 *
 * <p>A train has {@code N} cars. Find the position from the back of the car that is {@code K}-th
 * from the front.
 *
 * <p>Input:
 *
 * <pre>
 * N K
 * </pre>
 *
 * <p>Output the position of that car when counted from the back.
 *
 * <p>Constraints: {@code 1 <= K <= N <= 100}.
 *
 * <p>Examples:
 *
 * <pre>
 * Input:
 * 5 2
 * Output:
 * 4
 *
 * Input:
 * 1 1
 * Output:
 * 1
 *
 * Input:
 * 99 50
 * Output:
 * 50
 * </pre>
 *
 * @see <a href="https://atcoder.jp/contests/abc469/tasks/abc469_a?lang=en">Official problem</a>
 */
public class TrainCar {

    static final FastScanner fs = new FastScanner(System.in);
    static final PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int n = fs.nextInt();
        int k = fs.nextInt();

        // Your logic here

        out.println(n - k + 1);
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
