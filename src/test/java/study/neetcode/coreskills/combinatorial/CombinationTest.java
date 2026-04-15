package study.neetcode.coreskills.combinatorial;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class CombinationTest {
    @Test
    void happy1() {
        Combination combination = new Combination();

        // this is to compute = nCr -> 1,2,3 => 3C2 => (1,2) (2,3) (1,3) => n!/(n-1)! => 3!/2! => 3
        List<List<Integer>> result = combination.getAllCombinations(List.of(1, 2, 3), 2);

        assertEquals(3, result.size());
        assertEquals(List.of(1, 2), result.get(0));
        assertEquals(List.of(1, 3), result.get(1));
        assertEquals(List.of(2, 3), result.get(2));
    }

    @Test
    void happy2() {
        Combination combination = new Combination();

        List<List<Integer>> result = combination.getAllCombinations(List.of(1), 1);

        assertEquals(1, result.size());
        assertEquals(List.of(1), result.get(0));
    }

    @Test
    void happy3() {
        Combination combination = new Combination();

        List<List<Integer>> result = combination.getAllCombinations(List.of(1, 2, 3, 4, 5), 4);

        assertEquals(5, result.size());
        assertEquals(List.of(1, 2, 3, 4), result.get(0));
        assertEquals(List.of(1, 2, 3, 5), result.get(1));
        assertEquals(List.of(1, 2, 4, 5), result.get(2));
        assertEquals(List.of(1, 3, 4, 5), result.get(3));
        assertEquals(List.of(2, 3, 4, 5), result.get(4));
    }
}
