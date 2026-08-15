package study.contest.algozenith;

import java.io.*;
import java.util.*;

public class SupportQueriesII {

    static final FastScanner fs = new FastScanner(System.in);
    static final PrintWriter out = new PrintWriter(System.out);

    static void solve(FastScanner fs, PrintWriter out) {
        int Q = fs.nextInt();
        int k = fs.nextInt();

        String[][] queries = new String[Q][2];
        for (int i = 0; i < Q; i++) {
            queries[i][0] = fs.next();
            queries[i][1] = fs.next();
        }

        TopKSum topKSum = new TopKSum(k);
        for (var query : queries) {
            String type = query[0];

            if (type.equals("1")) {
                Integer num = Integer.parseInt(query[1]);
                topKSum.insert(num);
            } else if (type.equals("2")) {
                Integer num = Integer.parseInt(query[1]);
                topKSum.remove(num);
            } else if (type.equals("3")) {
                out.println(topKSum.getSumOfTopK());
            }
        }
    }

    static class TopKSum {
        TreeMap<Integer, Integer> topK;
        TreeMap<Integer, Integer> rest;
        long topKsum;
        int totalSize;
        int topKSize;
        int k;

        TopKSum(int k) {
            topKsum = 0;
            totalSize = 0;
            topKSize = 0;
            this.k = k;
            topK = new TreeMap();
            rest = new TreeMap();
        }

        void insert(int num) {
            topK.put(num, topK.getOrDefault(num, 0) + 1);
            topKSize++;
            totalSize++;
            topKsum += num;

            if (topKSize > k) {
                int smallestInTopK = topK.firstKey();

                if (topK.get(smallestInTopK) == 1) {
                    topK.remove(smallestInTopK);
                } else {
                    topK.put(smallestInTopK, topK.get(smallestInTopK) - 1);
                }

                topKsum -= smallestInTopK;
                topKSize--;

                if (!rest.containsKey(smallestInTopK)) {
                    rest.put(smallestInTopK, 0);
                }
                rest.put(smallestInTopK, rest.get(smallestInTopK) + 1);
            }
        }

        void remove(int num) {
            if (totalSize == 0) return;
            if (topK.containsKey(num)) {
                int existing = topK.get(num);
                if (existing == 1) topK.remove(num);
                else topK.put(num, existing - 1);
                topKSize--;
                topKsum -= num;
                totalSize--;
            } else if (rest.containsKey(num)) {
                int existing = rest.get(num);
                if (existing == 1) rest.remove(num);
                else rest.put(num, existing - 1);
                totalSize--;
            }

            if (topKSize < k && rest.size() > 0) {
                int largestKeyInRest = rest.lastKey();
                int existingValueInRest = rest.get(largestKeyInRest);

                if (existingValueInRest == 1) rest.remove(largestKeyInRest);
                else rest.put(largestKeyInRest, existingValueInRest - 1);

                int existingValueInTopK = topK.getOrDefault(largestKeyInRest, 0);
                topK.put(largestKeyInRest, existingValueInTopK + 1);
                topKSize++;
                topKsum += largestKeyInRest;
            }
        }

        long getSumOfTopK() {
            return topKsum;
        }
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];

        private int ptr = 0;
        private int len = 0;

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

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            StringBuilder sb = new StringBuilder();
            int c;

            while ((c = read()) != -1 && c != '\n') {
                if (c != '\r') { // handles Windows \r\n
                    sb.append((char) c);
                }
            }

            return sb.toString();
        }
    }
}
