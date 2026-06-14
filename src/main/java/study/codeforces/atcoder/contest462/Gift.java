package study.codeforces.atcoder.contest462;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
Problem Statement
N people, numbered
1 through
N, exchanged gifts with each other.

Person
i sent gifts to
K
i
​
  people: persons
A
i,1
​
 ,A
i,2
​
 ,…,A
i,K
i
​

​
 .

For each
i=1,2,…,N, find all people who sent a gift to person
i.

Constraints
2≤N≤100
1≤K
i
​
 ≤N−1
1≤A
i,1
​
 <A
i,2
​
 <⋯<A
i,K
i
​

​
 ≤N
A
i,j
​


=i
All input values are integers.
Input
The input is given from Standard Input in the following format:

N
K
1
​

A
1,1
​

A
1,2
​

…
A
1,K
1
​

​

K
2
​

A
2,1
​

A
2,2
​

…
A
2,K
2
​

​

⋮
K
N
​

A
N,1
​

A
N,2
​

…
A
N,K
N
​

​

Output
Output
N lines.

For the
i-th line, let
B
1
​
 ,B
2
​
 ,…,B
X
​
  be the numbers of the people who sent a gift to person
i, listed in ascending order (where
X is the count of people who sent a gift to person
i), and output in the following format:

X
B
1
​

B
2
​

…
B
X
​

Sample Input 1
Copy
4
1 2
1 3
1 2
3 1 2 3
Sample Output 1
Copy
1 4
3 1 3 4
2 2 4
0
 */
public class Gift {
    static Gift.FastScanner fs = new Gift.FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int N = fs.nextInt();

        int[][] persons = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            int numOfNeighborus = fs.nextInt();
            persons[i] = new int[numOfNeighborus];
            for (int j = 0; j < numOfNeighborus; j++) {
                int neighbour = fs.nextInt();
                persons[i][j] = neighbour;
            }
        }

        List<List<Integer>> received = new ArrayList<>();
        for (int i = 1; i < persons.length; i++) {
            for (int j = 0; j < persons[i].length; j++) {}
        }

        out.println(N);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() {
            if (ptr >= len) {
                try {
                    len = in.read(buffer);
                    ptr = 0;
                } catch (IOException e) {
                    return -1;
                }
                if (len <= 0) return -1;
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
    }
}
