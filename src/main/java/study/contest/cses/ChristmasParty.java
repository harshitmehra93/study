package study.contest.cses;

import java.io.*;
import java.util.StringTokenizer;

// https://cses.fi/problemset/task/1717
public class ChristmasParty {

    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static final long INF = Long.MAX_VALUE / 4;
    static final int MOD = 1_000_000_007;
    private long[] factorial;

    public static void main(String[] args) {
        ChristmasParty main = new ChristmasParty();
        main.solve();
        out.flush();
    }

    void solve() {
        int n = in.nextInt();
        int[] derangement = new int[n + 1];
        derangement[0] = 1;
        derangement[1] = 0;
        for (int i = 2; i < derangement.length; i++) {
            derangement[i] = (i - 1) * (derangement[i - 1] + derangement[i - 2]);
        }
        out.println(derangement[n]);
    }

    private long ncr(int n, int r) {
        long answer = (factorial[n] * inv(factorial[n - r])) % MOD;
        answer = (answer * inv(factorial[r])) % MOD;
        return answer;
    }

    long inv(long a) {
        return pow(a, MOD - 2) % MOD;
    }

    long pow(long a, long b) {
        if (a == 0 && b == 0) return 1;
        if (a == 0) return 0;
        if (b == 0) return 1;
        if (b == 1) return a % MOD;
        long factor = 1;
        if (b % 2 != 0) factor = a % MOD;
        long answer = pow(a, b / 2);
        answer = ((answer % MOD) * (answer % MOD)) % MOD;
        answer = ((answer % MOD) * (factor % MOD)) % MOD;
        return answer;
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
