package study.contest.codeforces;

import java.io.*;
import java.util.StringTokenizer;

// https://codeforces.com/problemset/problem/1594/E1

public class RubiksCubeColoring {

    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static final long INF = Long.MAX_VALUE / 4;
    static final int MOD = 1_000_000_007;

    long[][] nc2;

    public static void main(String[] args) {
        RubiksCubeColoring main = new RubiksCubeColoring();
        main.solve();
        out.flush();
    }

    void solve() {
        int k = in.nextInt();
        long totalNodes = pow(2, k) - 1;
        long answer = (6 * pow(4, totalNodes - 1, MOD)) % MOD;
        out.println(answer);
    }

    long inv(long a) {
        return pow(a, MOD - 2, MOD) % MOD;
    }

    long pow(long a, long b, long MOD) {
        if (a == 0 && b == 0) return 1;
        if (a == 0) return 0;
        if (b == 0) return 1;
        if (b == 1) return a % MOD;
        long factor = 1;
        if (b % 2 != 0) factor = a % MOD;
        long answer = pow(a, b / 2, MOD);
        answer = ((answer % MOD) * (answer % MOD)) % MOD;
        answer = ((answer % MOD) * (factor % MOD)) % MOD;
        return answer;
    }

    long pow(long a, long b) {
        return pow(a, b, Long.MAX_VALUE);
    }

    static class FastScanner {
        private final BufferedReader br;
        private StringTokenizer st;

        FastScanner(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
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

        char nextChar() {
            return next().charAt(0);
        }

        String nextLine() {
            try {
                st = null;
                return br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextLong();
            }
            return a;
        }
    }
}
