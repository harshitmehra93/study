package study.neetcode.interview.graph;

/*
Problem: Surrounded Regions

You are given an m x n matrix board containing only two characters:

'X'
'O'

A region is formed by connecting adjacent 'O' cells horizontally or vertically.

You need to modify the board in-place by capturing all regions of 'O' that are completely surrounded by 'X'.

Captured means converting those 'O' cells into 'X'.

An 'O' should not be captured if it is connected to the border of the board, directly or indirectly through other 'O' cells.

Example 1

Input:

board = [
  ['X','X','X','X'],
  ['X','O','O','X'],
  ['X','X','O','X'],
  ['X','O','X','X']
]

Output:

[
  ['X','X','X','X'],
  ['X','X','X','X'],
  ['X','X','X','X'],
  ['X','O','X','X']
]
 */
public class SurroundedRegions {
    boolean[][] visited;

    public void surroundingRegions(char[][] board) {
        visited = new boolean[board.length][board[0].length];
        // top row
        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] == 'O') dfsVisit(board, 0, j);
        }

        // right column
        for (int i = 0; i < board.length; i++) {
            if (board[i][board[0].length - 1] == 'O') dfsVisit(board, i, board[0].length - 1);
        }

        // bottom row
        for (int j = 0; j < board[0].length; j++) {
            if (board[board.length - 1][j] == 'O') dfsVisit(board, board.length - 1, j);
        }

        // left column
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') dfsVisit(board, i, 0);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) board[i][j] = 'X';
            }
        }
    }

    private void dfsVisit(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;
        if (board[i][j] == 'X') return;
        if (visited[i][j]) return;

        visited[i][j] = true;

        dfsVisit(board, i, j - 1);
        dfsVisit(board, i, j + 1);
        dfsVisit(board, i - 1, j);
        dfsVisit(board, i + 1, j);
    }
}
