package study.contest.atcoder.contest470;

import java.io.*;
import java.util.*;

/**
 * AtCoder Beginner Contest 470 — A: Fizz
 *
 * <p>For every integer from {@code 1} through {@code N}, print {@code Fizz} when it is a multiple
 * of {@code 3}; otherwise print the integer itself.
 *
 * <p>Constraints: {@code 1 <= N <= 100}.
 *
 * <p>Example: input {@code 4} produces {@code 1}, {@code 2}, {@code Fizz}, and {@code 4} on
 * separate lines.
 *
 * @see <a href="https://atcoder.jp/contests/abc470/tasks/abc470_a?lang=en">Official problem</a>
 */
public class Fizz {

    static final FastScanner fs = new FastScanner(System.in);
    static final PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int N = fs.nextInt();

        for (int i = 1; i <= N; i++) {
            if (i % 3 == 0) {
                out.println("Fizz");
            } else {
                out.println(i);
            }
        }
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
