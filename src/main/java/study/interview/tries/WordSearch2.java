package study.interview.tries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given an m x n board of characters and a list of strings words, return all words on the board.
Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
Example:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

words = ["oath","pea","eat","rain"]

output = ["oath","eat"]
 */
public class WordSearch2 {

    TrieNode root;
    private Set<String> result;

    public List<String> findWords(char[][] board, String[] words) {
        result = new HashSet<>();
        if (board == null || words == null) return new ArrayList<>(result);
        if (words.length == 0) return new ArrayList<>(result);
        if (board.length == 0) return new ArrayList<>(result);

        root = new TrieNode();
        for (var s : words) {
            add(root, s, 0);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                HashSet<Cell> visited = new HashSet<>();
                search(root, board, i, j, visited, new StringBuilder());
            }
        }
        return new ArrayList<>(result);
    }

    private void search(
            TrieNode node, char[][] board, int i, int j, HashSet<Cell> visited, StringBuilder sb) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;
        Cell cell = new Cell(i, j);
        if (visited.contains(cell)) return;

        visited.add(cell);

        char c = board[i][j];
        if (node.nodes[c] != null) {
            sb.append(c);
            if (node.nodes[c].isWord) {
                result.add(sb.toString());
            }
            search(node.nodes[c], board, i + 1, j, visited, sb);
            search(node.nodes[c], board, i - 1, j, visited, sb);
            search(node.nodes[c], board, i, j - 1, visited, sb);
            search(node.nodes[c], board, i, j + 1, visited, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        visited.remove(cell);
    }

    void add(TrieNode node, String word, int index) {
        if (word.length() == index) {
            node.isWord = true;
            return;
        }
        char c = word.charAt(index);
        if (node.nodes[c] == null) {
            node.nodes[c] = new TrieNode();
        }
        add(node.nodes[c], word, index + 1);
    }

    record Cell(int i, int j) {}

    public static class TrieNode {
        boolean isWord;
        TrieNode[] nodes = new TrieNode[256];
    }
}
