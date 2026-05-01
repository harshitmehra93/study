package study.neetcode.interview.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HouseRobberTest {

    @ParameterizedTest
    @CsvSource({
        // Basic
        "'[1]', 1",
        "'[1,2]', 2",
        "'[2,1,1,2]', 4",
        "'[2,7,9,3,1]', 12",
        "'[1,2,3,1]', 4",

        // Medium
        "'[10,1,1,10]', 20",
        "'[6,6,4,8,4,3,3,10]', 27",

        // Larger cases
        "'[2,7,9,3,1,5,8,4,6,2]', 26",
        "'[10,1,1,10,1,1,10,1,1,10]', 40",
        "'[5,3,4,11,2,7,8,9,10,2,3,1,6,7,8]', 51",

        // Pattern tests
        // Alternating high-low
        "'[100,1,100,1,100,1,100,1,100,1]', 500",

        // All same values
        "'[5,5,5,5,5,5,5,5,5,5]', 25",

        // Increasing
        "'[1,2,3,4,5,6,7,8,9,10]', 30",

        // Decreasing
        "'[10,9,8,7,6,5,4,3,2,1]', 30"
    })
    void testHouseRobber(String input, int expected) {
        int[] nums = parseArray(input);
        HouseRobber test = new HouseRobber();
        assertEquals(expected, test.rob(nums));
    }

    private int[] parseArray(String input) {
        input = input.replaceAll("[\\[\\]\\s]", "");
        if (input.isEmpty()) return new int[0];

        String[] parts = input.split(",");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }
}
