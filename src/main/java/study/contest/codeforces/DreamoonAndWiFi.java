package study.contest.codeforces;

import java.io.*;
import java.util.StringTokenizer;

// https://codeforces.com/problemset/problem/476/B
public class DreamoonAndWiFi {

    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static final long INF = Long.MAX_VALUE / 4;
    static final int MOD = 1_000_000_007;

    long[][] nc2;

    public static void main(String[] args) {
        DreamoonAndWiFi main = new DreamoonAndWiFi();
        main.solve();
        out.flush();
    }

    void solve() {
        String drazilsCommands = in.nextLine();
        String draemoonsCommands = in.nextLine();

        int targetPlus = 0;
        int targetMinus = 0;
        for (char c : drazilsCommands.toCharArray()) {
            if (c == '+') targetPlus++;
            else targetMinus++;
        }

        int actualPlus = 0;
        int actualMinus = 0;
        int unknown = 0;
        for (char c : draemoonsCommands.toCharArray()) {
            if (c == '+') actualPlus++;
            else if (c == '-') actualMinus++;
            else unknown++;
        }

        //        System.out.println("targetPlus = "+targetPlus);
        //        System.out.println("targetMinus = "+targetMinus);
        //        System.out.println("actualPlus = "+actualPlus);
        //        System.out.println("actualMinus = "+actualMinus);

        int targetPosition = targetPlus - targetMinus;
        int currentPosition = actualPlus - actualMinus;
        int adjustmentRequired = Math.abs(targetPosition - currentPosition);
        double answer = 0.0;
        if (unknown == 0 && targetPosition == currentPosition) {
            answer = 1.0;
        } else if (targetPosition == currentPosition && unknown % 2 != 0) {
            answer = 0.0;
        } else if (targetPosition != currentPosition && unknown < adjustmentRequired) {
            answer = 0.0;
        } else {
            double totalPossibilities = pow(2, unknown);
            //            System.out.println(totalPossibilities);
            int plusRequired = 0;
            int minusRequired = 0;
            if (targetPosition < currentPosition) {
                minusRequired = Math.abs(targetPosition - currentPosition);
                int leftoverPositions = unknown - minusRequired;
                plusRequired = leftoverPositions / 2;
                minusRequired += leftoverPositions / 2;
            } else {
                plusRequired = Math.abs(targetPosition - currentPosition);
                int leftoverPositions = unknown - plusRequired;
                minusRequired = leftoverPositions / 2;
                plusRequired += leftoverPositions / 2;
            }

            //            System.out.println("plus required = "+plusRequired);
            //            System.out.println("minus required = "+minusRequired);
            if (plusRequired == unknown || plusRequired == 0) {
                answer = 1.0 / totalPossibilities;
            } else {
                double permutationsOfAnswer =
                        fact(unknown) / (fact(plusRequired) * fact(minusRequired));
                //                System.out.println("permutations of answer =
                // "+permutationsOfAnswer);
                answer = permutationsOfAnswer / totalPossibilities;
            }
        }
        out.println(answer);
    }

    private double fact(double n) {
        if (n == 0) return 1;
        //        System.out.println("fact = "+n);
        return n * fact(n - 1);
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
