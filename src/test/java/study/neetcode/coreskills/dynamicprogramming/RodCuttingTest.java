package study.neetcode.coreskills.dynamicprogramming;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RodCuttingTest {
    @Test
    void test(){
        RodCutting rodCutting = new RodCutting();
        Map<Integer,Integer> priceIndex = new HashMap<>();
        priceIndex.put(5,5);
        var lengthOfRod = 5;
        List<Integer> result = rodCutting.giveMaxSplit(lengthOfRod, priceIndex);

        assertEquals(1, result.size());
        assertEquals(List.of(5), result);
    }

}