package study.neetcode.interview.mathgeometrybit;
/*
Next: Sum of Two Integers
Problem:
Given two integers a and b, return their sum without using + or -.
public int getSum(int a, int b)
Examples:
a = 1, b = 2
output = 3
a = 2, b = 3
output = 5
a = -2, b = 3
output = 1
 */
public class SumOfTwoIntegers {
    public int getSum(int i, int j) {
        int a = i ^ j;
        int b = (i & j) << 1;
        while (b != 0) {
            int partial = a ^ b;
            int carry = (a & b) << 1;

            a = partial;
            b = carry;
        }

        return a;
    }
}
