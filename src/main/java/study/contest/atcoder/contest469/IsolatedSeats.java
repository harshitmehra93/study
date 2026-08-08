package study.contest.atcoder.contest469;

import java.io.*;
import java.util.*;

/**
 * AtCoder Beginner Contest 469 — B: Isolated Seats
 *
 * <p>There are {@code N} seats in a row. The {@code i}-th character of {@code S} is {@code o}
 * when the seat is occupied and {@code x} when it is empty. Count the empty seats whose adjacent
 * seats, when they exist, are also empty.
 *
 * <p>Input:
 *
 * <pre>
 * N
 * S
 * </pre>
 *
 * <p>Output the number of empty seats with no occupied adjacent seat.
 *
 * <p>Constraints: {@code 1 <= N <= 100}; {@code S} has length {@code N} and consists of
 * {@code o} and {@code x}.
 *
 * <p>Examples:
 *
 * <pre>
 * Input:
 * 8
 * xxoxxxox
 * Output:
 * 2
 *
 * Input:
 * 5
 * ooooo
 * Output:
 * 0
 *
 * Input:
 * 1
 * x
 * Output:
 * 1
 * </pre>
 *
 * @see <a href="https://atcoder.jp/contests/abc469/tasks/abc469_b?lang=en">Official problem</a>
 */
public class IsolatedSeats {

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
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            char current = arr[i];
            if (current == 'x') {
                Character prev = i > 0 ? arr[i - 1] : null;
                Character next = i < arr.length - 1 ? arr[i + 1] : null;
                if ((prev == null || prev == 'x') && (next == null || next == 'x')) count++;
            }
        }

        out.print(count);
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
