package study.contest.leetcode.weeklycontest423;

/**
 * LeetCode 3352 — Count K-Reducible Numbers Less Than N
 *
 * <p>Given the binary representation {@code s} of {@code n}, count the positive integers less than
 * {@code n} that can be reduced to one in at most {@code k} operations, where one operation
 * replaces a number by its set-bit count. Return the answer modulo {@code 10^9 + 7}.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: s = "111", k = 1
 * Output: 3
 *
 * Input: s = "1000", k = 2
 * Output: 6
 *
 * Input: s = "1", k = 3
 * Output: 0
 * </pre>
 *
 * <p>Constraints: {@code 1 <= s.length() <= 800}; {@code s} has no leading zero and contains only
 * binary digits; {@code 1 <= k <= 5}.
 *
 * <p>The submitted source is preserved verbatim below. It is commented out because it does not
 * compile: {@code Integer.parseInt} is assigned to a {@code String}, and {@code binaryToDecimal}
 * references an undeclared variable and has no return statement.
 *
 * @see <a href="https://leetcode.com/problems/count-k-reducible-numbers-less-than-n/">Problem</a>
 */
public class CountKReducibleNumbersLessThanN {
    /*
    public int countKReducibleNumbers(String s, int k) {
        // reduction is replacing the number with number of its set bits.
        // we can do reduction at most k times
        String n = Integer.parseInt(s);
        n = binaryToDecimal(n);
        int count = 0;
        n--;
        for(;n>0;n--){
            if(isKReducable(n,k)){
                count++;
            }
        }
        return count;
    }
    private boolean isKReducable(int n,int k){
        if(hasOneBitSet(n)){
                return true;
        }
        for(int i=0;i<k;i++){
            n = reduce(n);
            if(n==1){
                return true;
            }
        }
        return false;
    }
    private int reduce(int n){
        return getCountOfOnes(n);
    }
    private int getCountOfOnes(int n){
        int count = 0;
        for(int i=0;i<32;i++){
            int lastBit = n & 1;
            if(lastBit==1) count++;
            n=n>>>1;
        }
        return count;
    }
    private boolean hasOneBitSet(int n){
        return getCountOfOnes(n)==1;
    }
    private int binaryToDecimal(int n){
        int number = 0;
        for(int i=0;i<32;i++){
            int lastBit = n & 1;
            if(lastBit==1) count++;
            n=n>>>1;
        }
    }
    */
}
