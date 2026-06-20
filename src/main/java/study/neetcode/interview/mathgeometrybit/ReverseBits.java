package study.neetcode.interview.mathgeometrybit;

/*
Reverse the bits of a 32-bit integer.
public int reverseBits(int n)
Example:
Input:
00000010100101000001111010011100

Output:
00111001011110000010100101000000
As an integer:
input  = 43261596
output = 964176192
 */
public class ReverseBits {
    public int reverseBits(int num) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int rightmostBit = num & 1;

            result = result << 1;
            result = result | rightmostBit;

            num = num >>> 1;
        }
        return result;
    }
}
