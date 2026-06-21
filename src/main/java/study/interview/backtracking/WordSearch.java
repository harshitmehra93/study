package study.interview.backtracking;
/*
Word Search

You are given an m x n grid of characters called board, and a string word.

Return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells.

Adjacent means:

up, down, left, right

The same cell may not be used more than once in a single word path.

Example 1
board = [
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

word = "ABCCED"

Output:

true

One valid path is:

A -> B -> C -> C -> E -> D
Example 2
board = [
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

word = "SEE"

Output:

true
Example 3
board = [
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

word = "ABCB"

Output:

false

Because you would need to reuse the same B cell, which is not allowed.

Method signature
public boolean exist(char[][] board, String word)
 */
public class WordSearch {

    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (board[i][j] == word.charAt(0)) {
                    initVisited(row, col);
                    if (wordExists(i, j, 0, word, board)) return true;
                }
            }
        }
        return false;
    }

    private boolean wordExists(int i, int j, int indexOfWordToMatch, String word, char[][] board) {
        if (indexOfWordToMatch == word.length()) return true;

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        if (visited[i][j]) return false;
        if (word.charAt(indexOfWordToMatch) != board[i][j]) return false;

        visited[i][j] = true;

        boolean found =
                wordExists(i + 1, j, indexOfWordToMatch + 1, word, board)
                        || wordExists(i - 1, j, indexOfWordToMatch + 1, word, board)
                        || wordExists(i, j + 1, indexOfWordToMatch + 1, word, board)
                        || wordExists(i, j - 1, indexOfWordToMatch + 1, word, board);

        visited[i][j] = false;

        return found;
    }

    private void initVisited(int row, int col) {
        visited = new boolean[row][col];
    }
}
