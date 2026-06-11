package study.neetcode.interview.greedy;

import java.util.*;

/*
Partition Labels

You are given a string s.

Split the string into as many parts as possible so that each letter appears in at most one part.

Return a list of the sizes of these parts.

Example
s = "ababcbacadefegdehijhklij"

Output:

[9, 7, 8]

Explanation:

"ababcbaca" | "defegde" | "hijhklij"
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        int[] lastIndexSeen = new int[26];
        Arrays.fill(lastIndexSeen, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int indexInMap = c - 'a';
            lastIndexSeen[indexInMap] = i;
        }

        HashSet<Character> charsSeen = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charsSeen.add(c);
            counter++;
            if (allCharsSeenHaveEnded(charsSeen, lastIndexSeen, i)) {
                result.add(counter);
                counter = 0;
            }
        }
        return result;
    }

    private boolean allCharsSeenHaveEnded(
            HashSet<Character> charsSeen, int[] lastIndexSeen, int currentIndex) {
        for (char c : charsSeen) {
            int index = c - 'a';
            int lastSeen = lastIndexSeen[index];
            if (lastSeen > currentIndex) return false;
        }
        return true;
    }
}
