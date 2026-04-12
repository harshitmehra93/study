package study.neetcode.coreskills.combinatorial;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    public List<List<Integer>> getAllCombinations(List<Integer> input, int r) {
        var result = new ArrayList<List<Integer>>();
        combination(input, result, r, 0, new ArrayList<>());
        return result;
    }

    void combination(List<Integer> input, List<List<Integer>> result, int r, int currentIndex, List<Integer> intermediateResult){
        if(intermediateResult.size()==r){
            result.add(new ArrayList<>(intermediateResult));
        }else{
            for(int i=currentIndex;i<input.size();i++){
                intermediateResult.add(input.get(i));
                combination(input, result, r, i+1, intermediateResult);
                intermediateResult.remove(input.get(i));
            }
        }
    }
}
