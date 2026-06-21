package study.interview.mathgeometrybit;

/*
k = 0 means rightmost bit
k = 1 means second bit from right
Example:
n = 10
binary = 1010

isBitSet(10, 1) -> true
isBitSet(10, 0) -> false

setBit(10, 0) -> 11       // 1011
clearBit(10, 1) -> 8      // 1000
toggleBit(10, 3) -> 2     // 0010
 */
public class BitOperatorDrills {
    public boolean isBitSet(int n, int k) {
        int mask = 1 << k;
        return (n & mask) != 0;
    }

    public int setBit(int n, int k) {
        // turn kth bit to 1
        int mask = 1 << k;
        return n | mask;
    }

    public int clearBit(int n, int k) {
        // turn kth bit to 0
        int mask = 1 << k;
        return n & ~mask;
    }

    public int toggleBit(int n, int k) {
        int mask = 1 << k;
        return n ^ mask;
    }
}
