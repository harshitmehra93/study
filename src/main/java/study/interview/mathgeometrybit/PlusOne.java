package study.interview.mathgeometrybit;

/*
You are given a large integer represented as an array of digits, where each digits[i] is one digit and the digits are ordered from most significant to least significant.
Increment the integer by one and return the resulting array.
Examples:
digits = [1,2,3]
output = [1,2,4]
digits = [4,3,2,1]
output = [4,3,2,2]
digits = [9,9,9]
output = [1,0,0,0]
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        if (digits[digits.length - 1] != 9) {
            digits[digits.length - 1] = digits[digits.length - 1] + 1;
            return digits;
        }
        int[] result;
        int len = digits.length;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 9) {
                break;
            }
            if (i == digits.length - 1) {
                len++;
            }
        }

        result = new int[len];
        int i = digits.length - 1;
        int j = result.length - 1;
        int carry = 1;
        result[j] = 0;
        j--;
        i--;
        for (; i >= 0; i--) {
            int sum = digits[i] + carry;
            if (sum == 10) {
                result[j] = 0;
                carry = 1;
            } else {
                result[j] = sum;
                carry = 0;
            }
            j--;
        }
        if (carry == 1) {
            result[0] = 1;
        }
        return result;
    }
}
