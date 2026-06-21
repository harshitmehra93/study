package study.coreskills.trie;

import java.util.List;

public interface Trie {

    void insert(String word);

    boolean search(String word);

    boolean startsWith(String prefix);

    List<String> keys();

    List<String> keysWithPrefix(String prefix);

    void delete(String word);

    int getSize();
}
