package study.coreskills.trie;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class TrieTest {

    @Test
    void insertIntoTrieSucceeds() {
        Trie trie = createTrie();

        assertEquals(0, trie.getSize());
        trie.insert("word");
        assertEquals(1, trie.getSize());
        trie.insert("word");
        assertEquals(1, trie.getSize());
    }

    @Test
    void emptyStringAdditionFails() {
        Trie trie = createTrie();

        assertEquals(0, trie.getSize());
        trie.insert("");
        assertEquals(0, trie.getSize());
    }

    @Test
    void searchTrie() {
        Trie trie = createTrie();

        assertEquals(0, trie.getSize());
        trie.insert("harshit");
        assertEquals(1, trie.getSize());

        assertFalse(trie.search("har"));
        assertFalse(trie.search("hars"));
        assertFalse(trie.search("harh"));
        assertTrue(trie.search("harshit"));
    }

    @Test
    void startsWith() {
        Trie trie = createTrie();

        assertEquals(0, trie.getSize());
        trie.insert("harshit");
        assertEquals(1, trie.getSize());

        assertTrue(trie.startsWith("har"));
        assertTrue(trie.startsWith("hars"));
        assertFalse(trie.startsWith("harh"));
        assertTrue(trie.startsWith("harshit"));
    }

    @Test
    void keysTest() {
        Trie trie = createTrie();

        assertEquals(0, trie.getSize());
        List<String> list = List.of("she", "sells", "sea", "shells", "by", "the", "sea", "shore");
        list.forEach(s -> trie.insert(s));
        assertEquals(7, trie.getSize());

        Set<String> expected = Set.of("she", "sells", "sea", "shells", "by", "the", "shore");
        List<String> result = trie.keys();
        assertEquals(expected.size(), result.size());
        assertEquals(expected, Set.copyOf(result));
    }

    @Test
    void keysWithPrefixTest() {
        Trie trie = createTrie();

        assertEquals(0, trie.getSize());
        List<String> list = List.of("she", "sells", "sea", "shells", "by", "the", "sea", "shore");
        list.forEach(s -> trie.insert(s));
        assertEquals(7, trie.getSize());

        Set<String> expected = Set.of("sells", "sea");
        List<String> result = trie.keysWithPrefix("se");
        assertEquals(expected.size(), result.size());
        assertEquals(expected, Set.copyOf(result));
    }

    @Test
    void keysWithPrefix_noMatchingPrefix_returnsEmptyList() {
        Trie trie = createTrie();

        trie.insert("she");
        trie.insert("shells");
        trie.insert("shore");

        assertTrue(trie.keysWithPrefix("x").isEmpty());
    }

    @Test
    void keysWithPrefix_prefixIsWord_returnsPrefixAndLongerWords() {
        Trie trie = createTrie();

        trie.insert("app");
        trie.insert("apple");
        trie.insert("apply");
        trie.insert("apt");

        Set<String> expected = Set.of("app", "apple", "apply");
        List<String> result = trie.keysWithPrefix("app");

        assertEquals(expected.size(), result.size());
        assertEquals(expected, Set.copyOf(result));
    }

    @Test
    void delete_removesWordFromTrie() {
        Trie trie = createTrie();

        assertEquals(0, trie.getSize());
        List<String> list = List.of("she", "sells", "sea", "shells", "by", "the", "sea", "shore");
        list.forEach(s -> trie.insert(s));
        assertEquals(7, trie.getSize());

        trie.delete("she");
        assertEquals(6, trie.getSize());

        trie.delete("by");
        assertEquals(5, trie.getSize());

        Set<String> expected = Set.of("sells", "sea", "shells", "the", "shore");
        List<String> result = trie.keys();
        assertEquals(expected.size(), result.size());
        assertEquals(expected, Set.copyOf(result));
        assertFalse(trie.search("she"));
        assertFalse(trie.search("by"));
    }

    @Test
    void delete_prefixWord_keepsLongerWord() {
        Trie trie = createTrie();

        trie.insert("app");
        trie.insert("apple");

        trie.delete("app");

        assertEquals(1, trie.getSize());
        assertFalse(trie.search("app"));
        assertTrue(trie.search("apple"));
        assertTrue(trie.startsWith("app"));
    }

    @Test
    void delete_longerWord_keepsPrefixWord() {
        Trie trie = createTrie();

        trie.insert("app");
        trie.insert("apple");

        trie.delete("apple");

        assertEquals(1, trie.getSize());
        assertTrue(trie.search("app"));
        assertFalse(trie.search("apple"));
        assertFalse(trie.startsWith("appl"));
    }

    @Test
    void delete_wordThatDoesNotExist_keepsTrieUnchanged() {
        Trie trie = createTrie();

        trie.insert("she");
        trie.insert("shells");

        assertDoesNotThrow(() -> trie.delete("shore"));
        assertDoesNotThrow(() -> trie.delete("shell"));

        assertEquals(2, trie.getSize());
        assertTrue(trie.search("she"));
        assertTrue(trie.search("shells"));
        assertFalse(trie.search("shore"));
        assertFalse(trie.search("shell"));
    }

    @Test
    void delete_emptyWord_keepsTrieUnchanged() {
        Trie trie = createTrie();

        trie.insert("app");

        trie.delete("");

        assertEquals(1, trie.getSize());
        assertTrue(trie.search("app"));
    }

    private Trie createTrie() {
        return new TrieImpl();
    }
}
