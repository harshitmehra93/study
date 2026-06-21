package study.coreskills.trie;

import java.util.ArrayList;
import java.util.List;

public class TrieImpl implements Trie {
    TrieNode root;
    int size;

    public TrieImpl() {
        root = new TrieNode();
    }

    @Override
    public void insert(String word) {
        if (word.isEmpty()) return;
        if (search(word)) return;

        insert(root, word, 0);
    }

    void insert(TrieNode node, String word, int index) {
        if (index == word.length()) {
            node.isWord = true;
            size++;
        } else {
            char c = word.charAt(index);
            if (node.nodes[c] == null) {
                node.nodes[c] = new TrieNode();
            }
            insert(node.nodes[c], word, index + 1);
        }
    }

    @Override
    public boolean search(String word) {
        return search(root, word, 0);
    }

    boolean search(TrieNode node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (node.nodes[c] == null) {
            return false;
        }
        return search(node.nodes[c], word, index + 1);
    }

    @Override
    public boolean startsWith(String prefix) {
        return startsWith(root, prefix, 0);
    }

    @Override
    public List<String> keys() {
        ArrayList<String> result = new ArrayList<>();
        collect(root, new StringBuilder(), result);
        return result;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = get(root, prefix, 0);
        if (node != null) {
            collect(node, new StringBuilder(prefix), result);
        }
        return result;
    }

    @Override
    public void delete(String word) {
        if (!search(word)) return;
        delete(root, word, 0);
    }

    TrieNode delete(TrieNode node, String word, int index) {
        if (word.length() == index) {
            node.isWord = false;
            size--;

            for (int i = 0; i < node.nodes.length; i++) {
                if (node.nodes[i] != null) return node;
            }
            return null;
        }
        char c = word.charAt(index);
        node.nodes[c] = delete(node.nodes[c], word, index + 1);

        for (int i = 0; i < node.nodes.length; i++) {
            if (node.nodes[i] != null) return node;
        }
        return node.isWord ? node : null;
    }

    private TrieNode get(TrieNode node, String prefix, int index) {
        if (prefix.length() == index) {
            return node;
        }
        char c = prefix.charAt(index);
        if (node.nodes[c] == null) return null;
        return get(node.nodes[c], prefix, index + 1);
    }

    private void collect(TrieNode node, StringBuilder word, List<String> result) {
        if (node.isWord) {
            result.add(word.toString());
        }

        for (int i = 0; i < node.nodes.length; i++) {
            var nextNode = node.nodes[i];
            if (nextNode != null) {
                char c = (char) i;
                word.append(c);
                collect(nextNode, word, result);
                word.deleteCharAt(word.length() - 1);
            }
        }
    }

    boolean startsWith(TrieNode node, String prefix, int index) {
        if (prefix.length() == index) {
            return true;
        }
        char c = prefix.charAt(index);
        if (node.nodes[c] == null) {
            return false;
        }
        return startsWith(node.nodes[c], prefix, index + 1);
    }

    @Override
    public int getSize() {
        return size;
    }
}
