package study.interview.tries;

/*
Design Add and Search Words Data Structure.
This is the natural follow-up because it adds one twist to Trie:
. can match any single character.

Problem shape:
addWord("bad")
addWord("dad")
addWord("mad")

search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
 */
public class AddAndSearchWord {

    TrieNode root;

    public AddAndSearchWord() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        if (word.isEmpty()) return;
        add(root, word, 0);
    }

    private void add(TrieNode node, String word, int index) {
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

    public boolean search(String pattern) {
        return searchMatchingWord(root, pattern, 0);
    }

    private boolean searchMatchingWord(TrieNode node, String pattern, int index) {
        if (pattern.length() == index) {
            return node.isWord;
        }
        char c = pattern.charAt(index);
        if (c == '.') {
            for (int i = 0; i < node.nodes.length; i++) {
                if (node.nodes[i] != null) {
                    if (searchMatchingWord(node.nodes[i], pattern, index + 1)) return true;
                }
            }
            return false;
        } else {
            if (node.nodes[c] == null) return false;
            return searchMatchingWord(node.nodes[c], pattern, index + 1);
        }
    }

    private static class TrieNode {
        boolean isWord;
        TrieNode[] nodes = new TrieNode[256];
    }
}
