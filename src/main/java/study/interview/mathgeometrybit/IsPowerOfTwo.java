package study.interview.mathgeometrybit;

/*
Power of Two
Problem:
Given an integer n, return true if it is a power of two. Otherwise return false.
public boolean isPowerOfTwo(int n)
Examples:
n = 1  -> true   // 2^0
n = 16 -> true   // 2^4
n = 3  -> false
n = 0  -> false
n = -16 -> false
 */
public class IsPowerOfTwo {
    public boolean isPowerOfTwo(int num) {
        return num > 0 && (num & (num - 1)) == 0;
    }
}
