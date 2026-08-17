package study.coreskills.string;

public final class Manacher {
    private final String transformed;
    private final int[] radiiArr;

    public Manacher(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append("#");
            sb.append(c);
        }
        sb.append("#");
        transformed = sb.toString();
        radiiArr = new int[transformed.length()];
        build();
    }

    private void build() {
        int L = 0;
        int R = -1;

        for (int i = 0; i < transformed.length(); i++) {
            int radius;
            if (i >= R) {
                radius = 0;
            } else {
                int mirror = L + R - i;
                radius = Math.min(radiiArr[mirror], R - i);
            }

            while (i - radius >= 0
                    && i + radius < transformed.length()
                    && transformed.charAt(i - radius) == transformed.charAt(i + radius)) {
                radius++;
            }

            radiiArr[i] = radius;

            if (i + radius > R) {
                L = i - radius;
                R = i + radius;
            }
        }
    }

    public boolean isPalindrome(int leftInclusive, int rightExclusive) {
        int left = 2 * leftInclusive + 1;
        int right = 2 * (rightExclusive - 1) + 1;
        return isPalindromeOnTransformed(left, right);
    }

    private boolean isPalindromeOnTransformed(int left, int right) {
        int center = (left + right) / 2;
        int expectedSize = right - left + 1;
        return (radiiArr[center] - 1) * 2 >= expectedSize;
    }
}
