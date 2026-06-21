package study.interview.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Hand of Straights.
Problem

Alice has some cards. Each card has an integer value.

You are given:

int[] hand
int groupSize

Return true if you can rearrange the cards into groups of size groupSize, where each group contains consecutive cards.

Example 1
hand = [1,2,3,6,2,3,4,7,8]
groupSize = 3

Output:

true

Because we can form:

[1,2,3]
[2,3,4]
[6,7,8]
Example 2
hand = [1,2,3,4,5]
groupSize = 4

Output:

false
 */
public class HandOfStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        Map<Integer, Integer> freq = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i : hand) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
            minHeap.offer(i);
        }

        for (int group = 0; group < hand.length / groupSize; group++) {
            Integer minOfRemaining = minHeap.poll();
            while (minOfRemaining != null && freq.get(minOfRemaining) == 0) {
                minOfRemaining = minHeap.poll();
            }
            freq.put(minOfRemaining, freq.get(minOfRemaining) - 1);
            for (int groupMemberIndex = 1; groupMemberIndex < groupSize; groupMemberIndex++) {
                if (!freq.containsKey(minOfRemaining + groupMemberIndex)) return false;
                if (freq.get(minOfRemaining + groupMemberIndex) == 0) return false;
                freq.put(
                        minOfRemaining + groupMemberIndex,
                        freq.get(minOfRemaining + groupMemberIndex) - 1);
            }
        }
        return true;
    }
}
