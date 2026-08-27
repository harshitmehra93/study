package study.contest.cses;

import java.io.*;
import java.util.StringTokenizer;

// https://cses.fi/problemset/task/1079
public class BinomialCoefficient {

    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static final long INF = Long.MAX_VALUE / 4;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        BinomialCoefficient main = new BinomialCoefficient();
        main.solve();
        out.flush();
    }

    void solve() {

        int[][] ncr = new int[10_001][10_001];
        for (int n = 0; n <= 10_000; n++) {
            for (int r = 0; r <= 10_000; r++) {
                if (n == 0 && r == 0) {
                    ncr[n][r] = 1;
                } else if (r == 0) {
                    ncr[n][r] = 1;
                } else if (n == 0) {
                    ncr[n][r] = 0;
                } else {
                    ncr[n][r] = (ncr[n - 1][r] % MOD + ncr[n - 1][r - 1] % MOD) % MOD;
                }
            }
        }

        int T = in.nextInt();

        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            int r = in.nextInt();
            out.println(ncr[n][r]);
        }
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
