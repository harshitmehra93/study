package study.coreskills.numbertheory;

import java.util.ArrayList;
import java.util.List;

public class Lcm {
    List<Integer> primes;

    long lcm(int[] nums) {
        Gcd gcd = new Gcd();
        long lcm = mod(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0 || lcm == 0) return 0;
            lcm = ((mod(nums[i]) * lcm) / (gcd.gcd(mod(nums[i]), lcm)));
        }
        return lcm;
    }

    long lcm2(int nums[]) {
        nums = nums.clone();
        long lcm = 1;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) return 0;
            if (nums[i] < 0) nums[i] = -nums[i];
            max = Math.max(max, nums[i]);
        }
        initPrimes(max);
        for (int prime : primes) {
            while (numDividesSomething(nums, prime)) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] % prime == 0) {
                        nums[i] = nums[i] / prime;
                    }
                }
                lcm = lcm * prime;
            }
        }

        return lcm;
    }

    private int mod(int num) {
        if (num < 0) return -num;
        return num;
    }

    boolean numDividesSomething(int[] nums, int num) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % num == 0) {
                return true;
            }
        }
        return false;
    }

    private void initPrimes(int max) {
        primes = new ArrayList<>();
        primes.add(2);
        primes.add(3);

        for (int num = 4; num <= max; num++) {
            int sq = (int) Math.sqrt(num);
            boolean isPrime = true;
            for (int prime : primes) {
                if (prime > sq) break;
                if (num % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime && primes.get(primes.size() - 1) < sq) {
                for (int divisor = primes.get(primes.size() - 1); divisor <= sq; divisor++) {
                    if (num % divisor == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }
            if (isPrime) {
                primes.add(num);
            }
        }
    }
}
