package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ClimbingStairsTest {
    @ParameterizedTest
    @CsvSource({
        "3, 3",
        "1, 1",
        "2, 2",
        "4, 5",
        "5, 8",
        "6, 13",
        "7, 21",
        "10, 89",
        "20, 10946",
        "25, 121393",
        "30, 1346269",
        "35, 14930352",
        "40, 165580141",
        "45, 1836311903",
        "46, 2971215073",
        "47, 4807526976",
        "48, 7778742049",
        "49, 12586269025"
    })
    void testClimbingStairs(int n, long result) {
        ClimbingStairs test = new ClimbingStairs();
        assertEquals(result, test.climbStairs(n));
    }
}
