package study.neetcode.interview.greedy;

/*
Problem

You are given:

int[][] triplets
int[] target

Each triplet has 3 values:

[a, b, c]

You can merge two triplets by taking max at each position:

[x1, y1, z1] merge [x2, y2, z2]
= [max(x1,x2), max(y1,y2), max(z1,z2)]

Return true if you can form exactly target.

Example
triplets = [[2,5,3], [1,8,4], [1,7,5]]
target   = [2,7,5]

Output:

true

Why?

Ignore [1,8,4] because 8 > target[1], so it can never be used.

Use:

[2,5,3]
[1,7,5]

Merge:

[max(2,1), max(5,7), max(3,5)]
= [2,7,5]
 */
public class MergeTripletsToFormTargetTriplet {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean firstFound = false;
        boolean secondFound = false;
        boolean thirdFound = false;
        for (int i = 0; i < triplets.length; i++) {
            int[] candidate = triplets[i];
            if (candidate[0] <= target[0]
                    && candidate[1] <= target[1]
                    && candidate[2] <= target[2]) {
                if (candidate[0] == target[0]) firstFound = true;
                if (candidate[1] == target[1]) secondFound = true;
                if (candidate[2] == target[2]) thirdFound = true;
            }
            if (firstFound && secondFound && thirdFound) return true;
        }
        return false;
    }
}
