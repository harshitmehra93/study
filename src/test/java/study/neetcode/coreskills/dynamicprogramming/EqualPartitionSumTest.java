package study.neetcode.coreskills.dynamicprogramming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EqualPartitionSumTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(int[] nums, boolean expected){
        EqualPartitionSum solver = new EqualPartitionSum();
        assertEquals(expected, solver.canBePartitionedEqually(nums));
    }

    static Stream<Arguments> testCases(){
        return Stream.of(

                // Basic
                Arguments.of(new int[]{1,5,11,5}, true),
                Arguments.of(new int[]{1,2,3,5}, false),

                // Small edge cases
                Arguments.of(new int[]{2,2}, true),
                Arguments.of(new int[]{1}, false),

                // All same numbers
                Arguments.of(new int[]{2,2,2,2}, true),
                Arguments.of(new int[]{3,3,3}, false),

                // Larger valid partitions
                Arguments.of(new int[]{1,2,3,4,5,6,7}, true),
                Arguments.of(new int[]{2,3,7,8,10}, true),

                // Larger invalid
                Arguments.of(new int[]{2,3,5,9}, false),

                // Many small numbers
                Arguments.of(new int[]{1,1,1,1,1,1,1,1,1,1}, true),

                // Mixed tricky
                Arguments.of(new int[]{1,2,5,6,7,10}, false),
                Arguments.of(new int[]{1,2,3,4,6}, true),

                // Stress cases
                Arguments.of(new int[]{100,100,100,100,100,100}, true),
                Arguments.of(new int[]{100,200,300,400,500}, false),

                // Slightly larger
                Arguments.of(new int[]{3,34,4,12,5,2}, false),
                Arguments.of(new int[]{3,34,4,12,5,2,1}, false)
        );
    }
}