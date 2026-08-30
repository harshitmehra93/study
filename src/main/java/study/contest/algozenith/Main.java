package study.contest.algozenith;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static final long INF = Long.MAX_VALUE / 4;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Main main = new Main();
        precompute();
        main.solve();
        out.flush();
    }

    static long[] spf;

    private static void precompute() {
        int MAX = 1000001;
        spf = new long[MAX];
        for (int i = 0; i < spf.length; i++) {
            spf[i] = i;
        }
        for (int i = 2; i < spf.length; i++) {
            if (spf[i] != i) continue;

            int multiple = i * 2;
            while (multiple < MAX) {
                if (spf[multiple] != multiple) {
                    multiple += i;
                    continue;
                }
                spf[multiple] = i;
                multiple += i;
            }
        }
    }

    void solve() {
        int n = in.nextInt();

        int count = 0;
        boolean isThereAOne = false;

        Set<Integer> perfectSquares = new HashSet<>();
        Set<Integer> cubes = new HashSet<>();
        Set<Integer> primes = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int num = in.nextInt();

            if (num == 1) {
                isThereAOne = true;
            } else if (isPerfectSquare(num)) {
                perfectSquares.add(num);
            } else if (isCube(num)) {
                cubes.add(num);
            } else if (isPrime(num)) {
                primes.add(num);
            }
        }

        for (var perfectSquare : perfectSquares) {
            int primeFactor = (int) Math.sqrt(perfectSquare);
            if (primes.contains(primeFactor)) {
                count++;
            }
        }

        if (isThereAOne) {
            count += cubes.size();
        }

        if (cubes.size() > 1) {
            int cubesPermutation = cubes.size() * (cubes.size() - 1) / 2;
            count += cubesPermutation;
        }

        out.println(count);
    }

    boolean isPrime(int num) {
        return spf[num] == num;
    }

    boolean isPerfectSquare(long num) {
        double factor = Math.sqrt(num);
        if (num % factor != 0) return false;
        return (factor * factor) == num;
    }

    boolean isCube(long num) {
        ArrayList<Long> list = new ArrayList<>();
        while (num > 1) {
            list.add(spf[(int) num]);
            num = num / spf[(int) num];
        }
        if (list.size() != 3) return false;

        if (list.get(0) != list.get(1)) return false;
        if (list.get(0) != list.get(2)) return false;
        if (list.get(1) != list.get(2)) return false;

        return true;
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
