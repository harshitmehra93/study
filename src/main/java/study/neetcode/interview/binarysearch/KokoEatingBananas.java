package study.neetcode.interview.binarysearch;

/*
Koko has piles of bananas. Each hour, she chooses one pile and eats k bananas from it. If the pile has fewer than k, she eats the whole pile and stops for that hour.
Given piles and h, return the minimum integer eating speed k such that she can eat all bananas within h hours.
Example:
piles = [3,6,7,11], h = 8
output = 4
 */
public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int minHours) {
        int max = Integer.MIN_VALUE;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }

        int low = 1;
        int high = max;
        int answer = max;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long hours = computeHours(piles, mid);
            if (hours <= minHours) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    private long computeHours(int[] piles, int k) {
        long hours = 0;
        for (int pile : piles) {
            hours += pile / k;
            if (pile % k != 0) {
                hours++;
            }
        }
        return hours;
    }
}
