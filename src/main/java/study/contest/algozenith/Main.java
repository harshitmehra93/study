package study.contest.algozenith;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;

public class Main {
    static final SupportQueriesII.FastScanner fs = new SupportQueriesII.FastScanner(System.in);
    static final PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {}

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
