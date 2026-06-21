package study.interview.mathgeometrybit;

/*
Given two integers x and y, return the number of bit positions where they differ.
public int hammingDistance(int x, int y)
Examples:
x = 1, y = 4

1 = 0001
4 = 0100

differ at two positions
output = 2
x = 3, y = 1

3 = 0011
1 = 0001

differ at one position
output = 1
 */
public class HammingDistance {
    public int hammingDistance(int a, int b) {
        int xor = a ^ b;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            int rightmostBit = xor & 1;
            if (rightmostBit == 1) count++;
            xor = xor >>> 1;
        }
        return count;
    }
}
