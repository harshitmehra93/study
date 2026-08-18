package study.coreskills.numbertheory;

import java.util.List;

public class Gcd {
    List<Integer> primes;

    long gcd(int[] nums) {
        if (nums.length == 0) return 0;
        long a = mod(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            a = gcd(a, mod(nums[i]));
        }
        return a;
    }

    private int mod(int num) {
        if (num < 0) return -num;
        return num;
    }

    long gcd(long a, long b) {
        if (a < b) {
            long tmp = a;
            a = b;
            b = tmp;
        }
        if (b == 0) return a;
        if (a == 0 && b == 0) return 0;
        if (a % b == 0) return b;
        return gcd(b, a % b);
    }
}
