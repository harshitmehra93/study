package study.contest.codeforces;

import java.io.*;
import java.util.StringTokenizer;

// https://codeforces.com/problemset/problem/1272/C
public class YetAnotherBrokenKeyboard {

    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static final long INF = Long.MAX_VALUE / 4;
    static final int MOD = 1_000_000_007;

    long[][] nc2;

    public static void main(String[] args) {
        YetAnotherBrokenKeyboard main = new YetAnotherBrokenKeyboard();
        main.solve();
        out.flush();
    }

    void solve() {
        long n = in.nextInt();
        long k = in.nextInt();
        String s = in.nextLine();

        char[] alphabet = new char[26];
        for (int i = 0; i < k; i++) {
            char c = in.nextChar();
            alphabet[c - 'a'] += 1;
        }

        int right = 0;
        long count = 0;
        long validBlockSize = 0;
        while (right < n) {
            while (right < n && isAllowed(alphabet, s.charAt(right))) {
                validBlockSize++;
                right++;
            }

            count += countSubstrings(validBlockSize);
            validBlockSize = 0;
            right++;
        }
        out.println(count);
    }

    private long countSubstrings(long validBlockSize) {
        return validBlockSize * (validBlockSize + 1) / 2;
    }

    private boolean isAllowed(char[] alphabet, char r) {
        return alphabet[r - 'a'] > 0;
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
