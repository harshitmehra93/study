package study.contest.leetcode.weeklycontest513;

/**
 * LeetCode 4013 — Count Subarrays With Even Odd Ratio II
 *
 * <p>You are given an integer array {@code nums} and two integers {@code a} and {@code b}. For a
 * subarray, let {@code x} be its number of even elements and {@code y} its number of odd elements.
 * The subarray is valid when {@code y > 0} and {@code x / y <= a / b}, comparing the ratios by
 * their exact rational values.
 *
 * <p>Return the number of valid subarrays.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: nums = [1, 2, 1, 2], a = 3, b = 2
 * Output: 7
 *
 * Input: nums = [2, 2, 1], a = 2, b = 1
 * Output: 3
 *
 * Input: nums = [2, 2, 2], a = 1, b = 1
 * Output: 0
 * </pre>
 *
 * <p>Constraints: {@code 1 <= nums.length <= 100000} and {@code 1 <= nums[i], a, b <= 1000000000}.
 *
 * @see <a href="https://leetcode.com/problems/count-subarrays-with-even-odd-ratio-ii/">Problem</a>
 */
public class CountSubarraysWithEvenOddRatioII {
    /*
     * Latest partial upsolve, preserved verbatim. It does not yet compile because the ordered
     * prefix-counting operation is unfinished.
     *
     * public long countRatioSubarrays(int[] nums, int a, int b) {
     *     for(int i=0;i<nums.length;i++){
     *         if(nums[i]%2==0){
     *             nums[i]=b;
     *         }else{
     *             nums[i]=-a;
     *         }
     *     }
     *
     *     // count all subarrays whose sum <= 0
     *     int answer = 0;
     *     long prefix = 0;
     *     Map<Long, Integer> map = new HashMap<>();
     *     map.put(0L,1);
     *     for(int i=0;i<nums.length;i++){
     *         prefix += nums[i];
     *
     *         count += map.getOrDefault()
     *
     *         frequency.put(prefix, frequency.getOrDefault(prefix,0)+1);
     *     }
     *     return answer;
     * }
     */
}
