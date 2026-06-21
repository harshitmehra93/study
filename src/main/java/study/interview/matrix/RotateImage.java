package study.interview.matrix;

/*
Rotate Image
You are given an n x n 2D matrix representing an image. Rotate the image by 90 degrees clockwise.
You must rotate the image in-place, which means you have to modify the input matrix directly. Do not allocate another 2D matrix.
Example:
Input:
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
]

Output:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        if (rowLen <= 1) return;

        for (int i = 0; i < rowLen; i++) {
            for (int j = i + 1; j < colLen; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }

        for (int row = 0; row < rowLen; row++) {
            int i = 0;
            int j = colLen - 1;
            while (i < j) {
                int tmp = matrix[row][i];
                matrix[row][i] = matrix[row][j];
                matrix[row][j] = tmp;

                i++;
                j--;
            }
        }
    }
}
