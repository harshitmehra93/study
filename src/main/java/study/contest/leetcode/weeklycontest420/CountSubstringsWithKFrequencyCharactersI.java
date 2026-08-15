package study.contest.leetcode.weeklycontest420;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * LeetCode 3325 — Count Substrings With K-Frequency Characters I
 *
 * <p>Given a lowercase string {@code s} and an integer {@code k}, count the substrings in which at
 * least one character occurs at least {@code k} times.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: s = "abacb", k = 2
 * Output: 4
 *
 * Input: s = "abcde", k = 1
 * Output: 15
 * </pre>
 *
 * <p>Constraints: {@code 1 <= s.length() <= 3000}, {@code 1 <= k <= s.length()}; {@code s} contains
 * only lowercase English letters.
 *
 * @see <a
 *     href="https://leetcode.com/problems/count-substrings-with-k-frequency-characters-i/">Problem</a>
 */
public class CountSubstringsWithKFrequencyCharactersI {
    public int numberOfSubstrings(String s, int k) {
        int count = 0;
        int windowSize = k;

        while (windowSize <= s.length()) {
            DynamicFrequencyMap map = new DynamicFrequencyMap();
            int left = 0;
            int right = 0;
            while (right < s.length()) {
                while (right < left + windowSize) {
                    map.increment(s.charAt(right));
                    right++;
                }

                if (map.getHighestFrequency() >= k) count++;

                map.decrement(s.charAt(left));
                left++;
            }
            windowSize++;
        }
        return count;
    }

    static class DynamicFrequencyMap {
        Map<Character, Integer> freqMap;
        TreeSet<Entry> reverse;

        DynamicFrequencyMap() {
            freqMap = new HashMap<>();
            reverse =
                    new TreeSet<>(
                            (a, b) -> {
                                if (a.freq() != b.freq()) {
                                    return Integer.compare(a.freq(), b.freq());
                                } else {
                                    return Character.compare(a.ch(), b.ch());
                                }
                            });
        }

        void increment(char ch) {
            int existing = freqMap.getOrDefault(ch, 0);
            freqMap.put(ch, existing + 1);
            if (existing != 0) {
                Entry oldEntry = new Entry(ch, existing);
                reverse.remove(oldEntry);
            }
            reverse.add(new Entry(ch, existing + 1));
        }

        void decrement(char ch) {
            int existing = freqMap.get(ch);
            if (existing == 1) {
                freqMap.remove(ch);
            } else {
                freqMap.put(ch, existing - 1);
            }

            Entry oldEntry = new Entry(ch, existing);
            reverse.remove(oldEntry);

            if (existing > 1) {
                reverse.add(new Entry(ch, existing - 1));
            }
        }

        int getHighestFrequency() {
            return reverse.last().freq();
        }
    }

    record Entry(Character ch, int freq) {}
}
