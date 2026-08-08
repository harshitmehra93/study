package study.contest.atcoder.contest470;

import java.io.*;
import java.util.*;

/**
 * AtCoder Beginner Contest 470 — D: Inverse and Swap
 *
 * <p>Maintain a permutation under swaps of two positions and operations that replace it with its
 * inverse permutation. Output the final permutation.
 *
 * <p>Constraints: {@code 2 <= N <= 5 * 10^5}; {@code 1 <= Q <= 5 * 10^5}; type-one queries
 * satisfy {@code 1 <= x < y <= N}.
 *
 * <p>Example: starting with {@code 2 1 3 5 4} and applying {@code (1,2,4), 2, (1,2,3),
 * (1,3,4), 2} produces {@code 4 5 2 1 3}.
 *
 * @see <a href="https://atcoder.jp/contests/abc470/tasks/abc470_d?lang=en">Official problem</a>
 */
public class InverseAndSwap {

    static final FastScanner fs = new FastScanner(System.in);
    static final PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int N = fs.nextInt(); // 5
        int Q = fs.nextInt(); // 5

        int[] nums = new int[N + 1];
        int[] indices = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = fs.nextInt();
            indices[nums[i]] = i;
        }

        String[] queries = new String[Q];
        for (int i = 0; i < Q; i++) {
            queries[i] = fs.nextLine();
        }

        for (String query : queries) {
            if (query.startsWith("1")) {
                // 1 a b
                String[] q = query.split(" ");
                int a = Integer.parseInt(q[1]);
                int b = Integer.parseInt(q[2]);

                int tmp = nums[a];
                nums[a] = nums[b];
                nums[b] = tmp;

                indices[nums[a]] = a;
                indices[nums[b]] = b;
            } else {
                // 2
                var newIndices = nums;
                nums = indices;
                indices = newIndices;
            }
        }

        for (int i = 1; i <= N; i++) {
            out.print(nums[i]);
            if (i != N) out.print(" ");
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

        String nextLine() {
            StringBuilder sb = new StringBuilder();
            int c;

            while ((c = read()) != -1 && c != '\n') {
                if (c != '\r') { // handles Windows \r\n
                    sb.append((char) c);
                }
            }

            return sb.toString();
        }
    }
}
