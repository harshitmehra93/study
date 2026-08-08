package study.contest.atcoder.contest470;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AtCoder Beginner Contest 470 — C: Inc, Dec, Xor
 *
 * <p>Maintain an initially zero sequence under point increments and operations that decrement every
 * positive element. Print the XOR of the sequence after every query.
 *
 * <p>Constraints: {@code 1 <= N,Q <= 5 * 10^5}; increment indices satisfy {@code 1 <= x <= N}.
 *
 * <p>Example: for {@code N = 2} and queries {@code (1,2), (1,2), (1,1), 2, 2}, the outputs are
 * {@code 1, 2, 3, 1, 0}.
 *
 * @see <a href="https://atcoder.jp/contests/abc470/tasks/abc470_c?lang=en">Official problem</a>
 */
public class IncDecXor {

    static final FastScanner fs = new FastScanner(System.in);
    static final PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int N = fs.nextInt();
        int Q = fs.nextInt();

        String[] queries = new String[Q];
        for (int i = 0; i < Q; i++) {
            queries[i] = fs.nextLine();
        }

        int currentXor = 0;
        Map<Integer, Integer> greaterThan0 = new ConcurrentHashMap<>();
        for (String query : queries) {
            if (query.startsWith("1")) {
                // 1 index
                String[] q = query.split(" ");
                int index = Integer.parseInt(q[1]) - 1;

                int num = greaterThan0.getOrDefault(index, 0);
                currentXor = currentXor ^ num; // removing old value
                num++;
                currentXor = currentXor ^ num; // add new value
                greaterThan0.put(index, num);
            } else {
                // 2
                // decrease 1 from all nums
                for (var entry : greaterThan0.entrySet()) {
                    int index = entry.getKey();
                    int num = entry.getValue();
                    currentXor = currentXor ^ num; // remove old
                    num--;
                    currentXor = currentXor ^ num; // add new
                    if (num == 0) greaterThan0.remove(index);
                    else greaterThan0.put(index, num);
                }
            }

            out.println(currentXor);
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
