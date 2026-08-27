package study.contest.codeforces;

import java.io.*;
import java.util.StringTokenizer;

// https://codeforces.com/problemset/problem/1328/B
public class KthBeautifulString {

    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static final long INF = Long.MAX_VALUE / 4;
    static final int MOD = 1_000_000_007;

    long[][] nc2;
    private long[] factorial;

    public static void main(String[] args) {
        KthBeautifulString main = new KthBeautifulString();
        main.solve();
        out.flush();
    }

    void solve() {

        factorial = new long[100001];
        factorial[0] = 1;
        factorial[1] = 1;
        for (int i = 2; i <= 100_000; i++) {
            factorial[i] = i * factorial[i - 1];
        }

        int T = in.nextInt();

        for (int i = 0; i < T; i++) {
            long n = in.nextInt();
            long k = in.nextInt();

            long noOfA = n - 2;
            long noOfB = 2;

            StringBuilder sb = new StringBuilder();
            long left = 1;
            long right = n * (n - 1) / 2;
            while (left <= right) {
                if (noOfA > 0) {
                    long wordsWithA = getPermutations(noOfA - 1, noOfB);
                    if (left + wordsWithA > k) {
                        right = left + wordsWithA - 1;
                        sb.append('a');
                        noOfA--;
                    } else {
                        left = left + wordsWithA;
                        sb.append('b');
                        noOfB--;
                    }
                } else {
                    for (int j = 0; j < noOfB; j++) {
                        sb.append('b');
                    }
                    break;
                }
            }
            out.println(sb);
        }
    }

    long getPermutations(long a, long b) {
        long n = a + b;

        if (b == 0) return 1;
        if (b == 1) return n;
        if (b == 2) return n * (n - 1) / 2;

        return 0;
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
