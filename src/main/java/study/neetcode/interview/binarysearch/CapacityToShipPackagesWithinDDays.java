package study.neetcode.interview.binarysearch;
/*
You have an array weights, where weights[i] is the weight of the ith package. Packages must be shipped in order.
You need to ship all packages within days days.
Return the minimum ship capacity needed.
Example:
weights = [1,2,3,4,5,6,7,8,9,10], days = 5
output = 15
Explanation for capacity 15:
day 1: 1,2,3,4,5
day 2: 6,7
day 3: 8
day 4: 9
day 5: 10
 */
public class CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int maxDays) {
        int low = 1;
        int high = 0;
        for (int i = 0; i < weights.length; i++) {
            high += weights[i];
            low = Math.max(weights[i], low);
        }
        int answer = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int days = calculateShippingDays(weights, mid);
            if (days <= maxDays) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }

    private int calculateShippingDays(int[] weights, int capacity) {
        int days = 1;
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (sum > capacity) {
                sum = weights[i];
                days++;
            }
        }
        return days;
    }
}
