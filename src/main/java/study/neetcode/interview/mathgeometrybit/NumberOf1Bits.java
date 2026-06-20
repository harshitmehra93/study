package study.neetcode.interview.mathgeometrybit;

/*
Given an integer n, return the number of 1 bits in its binary representation.
public int hammingWeight(int n)
Examples:
n = 11
binary: 1011
output: 3

n = 128
binary: 10000000
output: 1

n = 2147483645
binary: 1111111111111111111111111111101
output: 30
 */
public class NumberOf1Bits {
    public int hammingWeight(int n) {
        int count = 0;

        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >>> 1;
        }

        return count;
    }
}
