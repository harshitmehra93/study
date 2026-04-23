package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LongestIncreasingSubsequenceTest {
    @Test
    void test() {
        LongestIncreasingSubsequence test = new LongestIncreasingSubsequence();
        ////    Input: [10,9,2,5,3,7,101,18]
        ////    Output: 4
        assertEquals(4, test.longestIncreasingSubsequence(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
