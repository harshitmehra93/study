package study.contest.codeforces;

import java.io.*;
import java.util.*;

public class Dictionary {

    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static final long INF = Long.MAX_VALUE / 4;
    static final int MOD = 1_000_000_007;

    long[][] nc2;

    public static void main(String[] args) {
        Dictionary main = new Dictionary();
        main.solve();
        out.flush();
    }

    void solve() {
        int T = in.nextInt();
        //        int T=1;
        for (int t = 0; t < T; t++) {
            String word = in.nextLine();
            //            String word = "zx";
            char first = word.charAt(0);
            char second = word.charAt(1);

            int indexOne = first - 'a' + 1; // a = 0
            int indexTwo = second - 'a' + 1; // z = 26

            int fullFamilyCount = 25 * (indexOne - 1);

            int currentFamilyRank = indexTwo;
            if (indexOne < indexTwo) currentFamilyRank--;

            out.println(fullFamilyCount + currentFamilyRank);
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
