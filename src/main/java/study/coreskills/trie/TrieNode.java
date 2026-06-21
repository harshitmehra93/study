package study.coreskills.trie;

public class TrieNode {
    boolean isWord;
    TrieNode[] nodes;

    public TrieNode() {
        nodes = new TrieNode[256];
    }
}
