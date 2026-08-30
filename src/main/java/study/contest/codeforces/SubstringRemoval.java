package study.contest.codeforces;

import java.io.*;
import java.util.StringTokenizer;

// https://codeforces.com/problemset/problem/1096/B
public class SubstringRemoval {

    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static final long INF = Long.MAX_VALUE / 4;
    static final int MOD = 998244353;

    long[][] nc2;

    public static void main(String[] args) {
        SubstringRemoval main = new SubstringRemoval();
        main.solve();
        out.flush();
    }

    void solve() {
        int n = in.nextInt();
        String s = in.nextLine();

        int leftCount = 1;
        int left = 1;
        char c = s.charAt(0);
        while (left < n && c == s.charAt(left)) {
            left++;
            leftCount++;
        }

        int rightCount = 1;
        int right = n - 2;
        c = s.charAt(n - 1);
        while (right >= 0 && c == s.charAt(right)) {
            right--;
            rightCount++;
        }

        if (s.charAt(0) != s.charAt(n - 1)) {

            out.println(leftCount + rightCount + 1);

        } else {
            if (leftCount == n) {
                out.println(nc2(leftCount + 1));
            } else {
                long answer = (long) (leftCount + 1) * (rightCount + 1) % MOD;
                out.println(answer);
            }
        }
    }

    boolean areAllSame(int[] frequency) {
        int numberOfDistinctChars = 0;
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > 0) numberOfDistinctChars++;
            if (numberOfDistinctChars > 1) return false;
        }
        return true;
    }

    long nc2(long n) {
        if (n == 1) return 1;
        long answer = (n * (n - 1)) % MOD;
        answer = (answer * inv(2)) % MOD;
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
