package study.contest.codeforces;

import java.io.*;
import java.util.*;

public class PeshmakAndFlowers {

    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static final long INF = Long.MAX_VALUE / 4;
    static final int MOD = 1_000_000_007;

    long[][] nc2;

    public static void main(String[] args) {
        PeshmakAndFlowers main = new PeshmakAndFlowers();
        main.solve();
        out.flush();
    }

    void solve() {
        int n = in.nextInt();
        //        int n=2_00_000;
        long[] beauty = new long[n];
        buildNc2(n);
        long minBeauty = Long.MAX_VALUE;
        long maxBeauty = Long.MIN_VALUE;
        Map<Long, Long> scoreFrequency = new HashMap<>();
        for (int i = 0; i < n; i++) {
            beauty[i] = in.nextLong();
            minBeauty = Math.min(minBeauty, beauty[i]);
            maxBeauty = Math.max(maxBeauty, beauty[i]);
            long currentScoreFrequency = scoreFrequency.getOrDefault(beauty[i], 0L);
            scoreFrequency.put(beauty[i], currentScoreFrequency + 1);
        }

        long maxDiff = maxBeauty - minBeauty;

        long answer = 0;
        if (maxBeauty == minBeauty) {
            long freq = scoreFrequency.get(maxBeauty);
            if (freq > 1) {
                System.out.println("n=" + freq + " C r=" + 2);
                long contribution = nCr((int) freq, 2);
                answer += contribution;
            }
        } else {
            long minFreq = scoreFrequency.get(minBeauty);
            long maxFreq = scoreFrequency.get(maxBeauty);
            answer = minFreq * maxFreq;
        }
        out.println(maxDiff + " " + answer);
    }

    long nCr(int n, int r) {
        if (r == n || r == 0) return 1;
        return nc2[n][r];
    }

    void buildNc2(int N) {
        nc2 = new long[N + 1][3];
        for (int n = 0; n <= N; n++) {
            for (int r = 0; r <= 2 && r <= n; r++) {
                if (n == 0 || r == 0) {
                    nc2[n][r] = 1;
                    continue;
                }
                nc2[n][r] = nc2[n - 1][r - 1] + nc2[n - 1][r];
            }
        }
    }

    long multiply(long n, long r) {
        if (n >= r) return n * multiply(n - 1, r);
        else return 1;
    }

    long pow(long a, long b) {
        if (a == 0 && b == 0) return 1;
        if (b == 0) return 1;
        if (b == 1) return a;
        long factor = 1;
        if (b % 2 != 0) factor = a;
        long answer = pow(a, b / 2);
        answer = (((answer * answer)) * factor);
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
