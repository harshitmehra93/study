package study.interview.tries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddAndSearchWordTest {

    @Test
    void emptyDictionary_searchReturnsFalse() {
        AddAndSearchWord dictionary = new AddAndSearchWord();

        assertFalse(dictionary.search("bad"));
        assertFalse(dictionary.search("."));
    }

    @Test
    void searchExactWord() {
        AddAndSearchWord dictionary = new AddAndSearchWord();

        dictionary.addWord("bad");

        assertTrue(dictionary.search("bad"));
        assertFalse(dictionary.search("pad"));
    }

    @Test
    void searchDoesNotMatchPrefixOnly() {
        AddAndSearchWord dictionary = new AddAndSearchWord();

        dictionary.addWord("bad");

        assertFalse(dictionary.search("b"));
        assertFalse(dictionary.search("ba"));
    }

    @Test
    void searchDoesNotMatchLongerPattern() {
        AddAndSearchWord dictionary = new AddAndSearchWord();

        dictionary.addWord("bad");

        assertFalse(dictionary.search("bads"));
        assertFalse(dictionary.search("bad."));
    }

    @Test
    void dotMatchesSingleCharacter() {
        AddAndSearchWord dictionary = new AddAndSearchWord();

        dictionary.addWord("bad");
        dictionary.addWord("dad");
        dictionary.addWord("mad");

        assertTrue(dictionary.search(".ad"));
        assertTrue(dictionary.search("b.d"));
        assertTrue(dictionary.search("ba."));
        assertTrue(dictionary.search("..."));
    }

    @Test
    void dotDoesNotMatchMissingCharacter() {
        AddAndSearchWord dictionary = new AddAndSearchWord();

        dictionary.addWord("bad");

        assertFalse(dictionary.search(".."));
        assertFalse(dictionary.search("...."));
    }

    @Test
    void wildcardSearchBacktracksAcrossBranches() {
        AddAndSearchWord dictionary = new AddAndSearchWord();

        dictionary.addWord("bad");
        dictionary.addWord("bag");
        dictionary.addWord("cat");

        assertTrue(dictionary.search("ba."));
        assertTrue(dictionary.search(".a."));
        assertTrue(dictionary.search("..t"));
        assertFalse(dictionary.search("..z"));
    }

    @Test
    void duplicateInsertStillSearchesSuccessfully() {
        AddAndSearchWord dictionary = new AddAndSearchWord();

        dictionary.addWord("bad");
        dictionary.addWord("bad");

        assertTrue(dictionary.search("bad"));
        assertTrue(dictionary.search("b.d"));
    }

    @Test
    void emptyStringIsNotStored() {
        AddAndSearchWord dictionary = new AddAndSearchWord();

        dictionary.addWord("");

        assertFalse(dictionary.search(""));
    }
}
