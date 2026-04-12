package study.neetcode.coreskills.dynamicprogramming;

import study.neetcode.coreskills.combinatorial.Combination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RodCutting {
    public List<Integer> giveBestSplit(int lengthOfRod, Map<Integer, Integer> priceIndex) {
        Combination combination = new Combination();
        List<Integer> arrayRepOfRod = new ArrayList<>();
        for(int i=0;i<lengthOfRod;i++) arrayRepOfRod.add(0);
        List<List<Integer>> result = new ArrayList<>();
        for(int i : priceIndex.keySet()){
            result.addAll(combination.getAllCombinations(arrayRepOfRod,i));
        }
        List<List<Integer>> bestSplit = new ArrayList<>();

    }
}
