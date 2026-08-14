package study.contest.algozenith;

import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * AlgoZenith AZ101: Running Mean, Median and Mode
 *
 * <p>Maintain a multiset under insertions and removals, and answer mean, median, and mode queries.
 * Fractional answers are represented modulo {@code 1_000_000_007}. For tied modes, return the
 * smallest value.
 */
public class RunningMeanMedianAndMode {
    static double MOD = 1000000007;
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
        PriorityQueue<Integer> leftHalf =
                new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> rightHalf = new PriorityQueue<>();

        void insert(int num) {
            if (leftHalf.size() == 0 && rightHalf.size() == 0) {
                rightHalf.offer(num);
                return;
            }

            if (leftHalf.size() > 0 && leftHalf.peek() >= num) {
                leftHalf.offer(num);
            } else {
                rightHalf.offer(num);
            }

            balance();
        }

        void remove(int num) {
            if (leftHalf.peek() <= num) {
                rightHalf.remove(num);
            } else {
                leftHalf.remove(num);
            }
            balance();
        }

        void balance() {
            if (rightHalf.size() > leftHalf.size() + 1) {
                leftHalf.offer(rightHalf.poll());
            } else if (leftHalf.size() > rightHalf.size()) {
                rightHalf.offer(leftHalf.poll());
            } else if (rightHalf.size() < leftHalf.size()) {
                rightHalf.offer(leftHalf.poll());
            }
        }

        int getMedian() {
            if (rightHalf.size() == 0) return -1;

            if (rightHalf.size() > leftHalf.size()) {
                return rightHalf.peek();
            } else {
                int left = leftHalf.peek();
                int right = rightHalf.peek();
                double result = (double) (left + right) / 2;
                result = (result / MOD) % MOD;
                return (int) ((long) result);
            }
        }
    }

    static class DynamicMean {
        int sum;
        int size;

        DynamicMean() {
            sum = 0;
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
            double mean = (double) sum / size;
            return (int) ((long) (mean / MOD) % MOD);
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
