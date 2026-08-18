package study.coreskills.numbertheory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FactorScoreTest {
    Gcd gcd = new Gcd();
    Lcm lcm = new Lcm();

    @Test
    void test() {
        assertEquals(64, maxScore(new int[] {2, 4, 8, 16}));
    }

    public long maxScore(int[] nums) {
        if (nums.length == 0) return 0;
        long factorScore = gcd.gcd(nums) * lcm.lcm(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.println("index = " + i);
            int tmp = nums[i];

            nums[i] = findNumOtherThanI(nums, i);
            print(nums);
            long changedGcd = gcd.gcd(nums);
            long changedLcm = lcm.lcm(nums);
            System.out.println("changedGcd = " + changedGcd);
            System.out.println("changedLcm = " + changedLcm);

            nums[i] = tmp;

            factorScore = Math.max(factorScore, changedGcd * changedLcm);
        }

        return factorScore;
    }

    void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    int findNumOtherThanI(int[] nums, int i) {
        if (nums.length == 1 && i == 0) return -1;
        if (i < nums.length - 1) return nums[i + 1];
        if (i == nums.length - 1) return nums[i - 1];
        return -1;
    }
}
