package study.contest.codeforces;

import java.io.*;
import java.util.StringTokenizer;

// https://codeforces.com/problemset/problem/478/B
public class RandomTeams {

    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static final long INF = Long.MAX_VALUE / 4;
    static final int MOD = 1_000_000_007;

    long[][] nc2;

    public static void main(String[] args) {
        RandomTeams main = new RandomTeams();
        main.solve();
        out.flush();
    }

    void solve() {
        long n = in.nextInt();
        long m = in.nextInt();

        if (n <= m) {
            out.println(0 + " " + 0);
            return;
        }

        long min = calculateMin(n, m);
        long max = calculateMax(n, m);

        out.println(min + " " + max);
    }

    private long calculateMax(long n, long m) {
        long biggestPossibleTeam = n - (m - 1);
        return nC2(biggestPossibleTeam);
    }

    private static long nC2(long n) {
        return n * (n - 1) / 2;
    }

    private long calculateMin(long n, long m) {
        long remainder = n % m;
        long totalFriends = remainder * nC2(n / m + 1) + (m - remainder) * nC2(n / m);
        return totalFriends;
    }

    private long pow(int a, long b) {
        if (a == 0 && b == 0) return 1;
        if (a == 0) return 0;
        if (b == 0) return 1;
        if (b == 1) return a % MOD;
        long factor = 1;
        if (b % 2 != 0) factor = a % MOD;
        long answer = pow(a, b / 2) % MOD;
        answer = (answer * answer) % MOD;
        answer = (answer * factor) % MOD;
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
