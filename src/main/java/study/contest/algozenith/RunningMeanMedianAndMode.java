package study.contest.algozenith;

import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/** AlgoZenith AZ101: Running Mean, Median and Mode. */
public class RunningMeanMedianAndMode {
    static final long MOD = 1_000_000_007L;
    static FastScanner fs = new FastScanner(System.in);
    static PrintWriter out =
            new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    DynamicMean dynamicMean;
    DynamicMedian dynamicMedian;
    DynamicMode dynamicMode;

    RunningMeanMedianAndMode() {
        dynamicMean = new DynamicMean();
        dynamicMedian = new DynamicMedian();
        dynamicMode = new DynamicMode();
    }

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int T = fs.nextInt();

        String[][] queries = new String[T][];
        for (int i = 0; i < T; i++) {
            int Q = fs.nextInt();
            queries[i] = new String[Q];
            for (int j = 0; j < Q; j++) {
                queries[i][j] = fs.nextLine();
            }
        }

        for (int test = 0; test < T; test++) {
            RunningMeanMedianAndMode main = new RunningMeanMedianAndMode();
            for (var query : queries[test]) {
                if (query.startsWith("i")) {
                    int num = Integer.parseInt(query.split(" ")[1]);

                    main.dynamicMean.insert(num);
                    main.dynamicMedian.insert(num);
                    main.dynamicMode.insert(num);
                } else if (query.startsWith("r")) {
                    int num = Integer.parseInt(query.split(" ")[1]);

                    main.dynamicMean.remove(num);
                    main.dynamicMedian.remove(num);
                    main.dynamicMode.remove(num);
                } else if (query.equals("getMean")) {
                    out.println(main.dynamicMean.getMean());
                } else if (query.equals("getMedian")) {
                    out.println(main.dynamicMedian.getMedian());
                } else if (query.equals("getMode")) {
                    out.println(main.dynamicMode.getMode());
                }
            }
        }
    }

    static long modInverse(long number) {
        long result = 1;
        long power = MOD - 2;
        number %= MOD;

        while (power > 0) {
            if ((power & 1) == 1) {
                result = (result * number) % MOD;
            }
            number = (number * number) % MOD;
            power >>= 1;
        }
        return result;
    }

    static class DynamicMode {
        TreeSet<Entry> sortedFrequencies;
        Map<Integer, Integer> frequencies;

        DynamicMode() {
            sortedFrequencies =
                    new TreeSet<>(
                            (a, b) -> {
                                if (a.value() != b.value()) {
                                    return Integer.compare(a.value(), b.value());
                                } else {
                                    return Integer.compare(b.key(), a.key());
                                }
                            });
            frequencies = new HashMap<>();
        }

        void insert(int num) {
            int existing = frequencies.getOrDefault(num, 0);
            frequencies.put(num, existing + 1);

            Entry oldEntry = new Entry(num, existing);
            if (existing > 0) sortedFrequencies.remove(oldEntry);
            sortedFrequencies.add(new Entry(num, existing + 1));
        }

        void remove(int num) {
            int existing = frequencies.get(num);
            if (existing > 1) frequencies.put(num, existing - 1);
            else frequencies.remove(num);

            Entry oldEntry = new Entry(num, existing);
            sortedFrequencies.remove(oldEntry);
            if (existing > 1) sortedFrequencies.add(new Entry(num, existing - 1));
        }

        int getMode() {
            if (sortedFrequencies.isEmpty()) return -1;
            return sortedFrequencies.last().key();
        }

        static class Entry {
            int key, value;

            Entry(int key, int value) {
                this.key = key;
                this.value = value;
            }

            int key() {
                return key;
            }

            int value() {
                return value;
            }

            public boolean equals(Object o) {
                if (o instanceof Entry) {
                    Entry entry = (Entry) o;
                    return key == entry.key() && value == entry.value();
                }
                return false;
            }
        }
    }

    static class DynamicMedian {
        TreeMap<Integer, Integer> leftHalf = new TreeMap<>(Collections.reverseOrder());
        TreeMap<Integer, Integer> rightHalf = new TreeMap<>();
        int leftSize;
        int rightSize;

        void insert(int num) {
            if (leftSize == 0 && rightSize == 0) {
                add(rightHalf, num);
                rightSize++;
                return;
            }

            if (leftSize > 0 && leftHalf.firstKey() >= num) {
                add(leftHalf, num);
                leftSize++;
            } else {
                add(rightHalf, num);
                rightSize++;
            }

            balance();
        }

        void remove(int num) {
            if (leftSize > 0 && leftHalf.firstKey() >= num) {
                removeOne(leftHalf, num);
                leftSize--;
            } else {
                removeOne(rightHalf, num);
                rightSize--;
            }
            balance();
        }

        void balance() {
            if (rightSize > leftSize + 1) {
                int num = rightHalf.firstKey();
                removeOne(rightHalf, num);
                rightSize--;
                add(leftHalf, num);
                leftSize++;
            } else if (leftSize > rightSize) {
                int num = leftHalf.firstKey();
                removeOne(leftHalf, num);
                leftSize--;
                add(rightHalf, num);
                rightSize++;
            }
        }

        int getMedian() {
            if (rightSize == 0) return -1;

            if (rightSize > leftSize) {
                return rightHalf.firstKey();
            } else {
                long left = leftHalf.firstKey();
                long right = rightHalf.firstKey();
                return (int) ((((left + right) % MOD) * modInverse(2)) % MOD);
            }
        }

        void add(TreeMap<Integer, Integer> half, int num) {
            half.put(num, half.getOrDefault(num, 0) + 1);
        }

        void removeOne(TreeMap<Integer, Integer> half, int num) {
            int count = half.get(num);
            if (count == 1) half.remove(num);
            else half.put(num, count - 1);
        }
    }

    static class DynamicMean {
        long sum;
        int size;

        DynamicMean() {
            sum = 0L;
            size = 0;
        }

        void insert(int num) {
            size++;
            sum += num;
        }

        void remove(int num) {
            size--;
            sum -= num;
        }

        int getMean() {
            if (size == 0) return -1;
            return (int) (((sum % MOD) * modInverse(size)) % MOD);
        }
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() {
            if (ptr >= len) {
                try {
                    len = in.read(buffer);
                    ptr = 0;
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }

                if (len <= 0) {
                    return -1;
                }
            }

            return buffer[ptr++];
        }

        String next() {
            StringBuilder sb = new StringBuilder();
            int c;

            do {
                c = read();
            } while (c <= ' ' && c != -1);

            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        String nextLine() {
            StringBuilder sb = new StringBuilder();
            int c;

            while (true) {
                c = read();

                if (c == -1 || c == '\n') {
                    break;
                }

                if (c != '\r') {
                    sb.append((char) c);
                }
            }

            return sb.toString();
        }
    }
}
