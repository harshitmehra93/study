package study.neetcode.interview.mathgeometrybit;

/*
Counting Bits
Given an integer n, return an array ans of length n + 1 where ans[i] is the number of 1 bits in the binary representation of i.
Examples:
n = 2
output = [0,1,1]

0 -> 0
1 -> 1
2 -> 10 -> 1
n = 5
output = [0,1,1,2,1,2]

0 -> 0
1 -> 1
2 -> 10 -> 1
3 -> 11 -> 2
4 -> 100 -> 1
5 -> 101 -> 2
 */
public class CountingBits {
    public int[] countBits(int length) {
        int[] result = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }
}
