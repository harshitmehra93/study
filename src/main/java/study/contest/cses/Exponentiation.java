import java.io.*;
import java.util.*;

public class Exponentiation {

    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static final long INF = Long.MAX_VALUE / 4;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Exponentiation main = new Exponentiation();
        main.solve();
        out.flush();
    }

    void solve() {
        int T = in.nextInt();

        for (int t = 0; t < T; t++) {
            long A = in.nextInt();
            long B = in.nextInt();
            out.println(pow(A, B));
        }
    }

    long pow(long a, long b) {
        if (b == 0) return 1;
        if (b == 1) return a;
        long factor = 1;
        if (b % 2 != 0) factor = a;
        long answer = pow(a, b / 2) % MOD;
        answer = (((answer * answer) % MOD) * factor) % MOD;
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
