package study.contest.leetcode.weeklycontest420;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 3324 — Find the Sequence of Strings Appeared on the Screen
 *
 * <p>A special keyboard can append {@code 'a'} or advance the last character to the next lowercase
 * letter. Starting from the empty string, return every screen state on a minimum-press path to
 * {@code target}.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: target = "abc"
 * Output: ["a","aa","ab","aba","abb","abc"]
 *
 * Input: target = "he"
 * Output: ["a","b","c","d","e","f","g","h","ha","hb","hc","hd","he"]
 * </pre>
 *
 * <p>Constraints: {@code 1 <= target.length() <= 400}; {@code target} contains only lowercase
 * English letters.
 *
 * @see <a
 *     href="https://leetcode.com/problems/find-the-sequence-of-strings-appeared-on-the-screen/">Problem</a>
 */
public class FindTheSequenceOfStringsAppearedOnTheScreen {
    public List<String> stringSequence(String target) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < target.length()) {
            if (sb.length() != index + 1) {
                sb.append("a");
            } else {
                if (sb.charAt(index) != target.charAt(index)) {
                    char c = sb.charAt(index);
                    sb.deleteCharAt(index);
                    char newChar = (char) (c + 1);
                    sb.append(newChar);
                } else {
                    index++;
                    continue;
                }
            }
            result.add(sb.toString());
        }
        return result;
    }
}
