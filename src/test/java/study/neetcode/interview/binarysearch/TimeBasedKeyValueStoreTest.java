package study.neetcode.interview.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TimeBasedKeyValueStoreTest {

    @Test
    void returnsValueAtExactTimestamp() {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();

        store.set("foo", "bar", 1);

        assertEquals("bar", store.get("foo", 1));
    }

    @Test
    void returnsLatestValueBeforeRequestedTimestamp() {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();

        store.set("foo", "bar", 1);
        store.set("foo", "bar2", 4);

        assertEquals("bar", store.get("foo", 3));
        assertEquals("bar2", store.get("foo", 5));
    }

    @Test
    void returnsEmptyStringWhenKeyDoesNotExist() {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();

        assertEquals("", store.get("missing", 10));
    }

    @Test
    void returnsEmptyStringWhenTimestampIsBeforeFirstValue() {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();

        store.set("foo", "bar", 5);

        assertEquals("", store.get("foo", 4));
    }

    @Test
    void keepsIndependentTimestampHistoriesPerKey() {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();

        store.set("foo", "a", 1);
        store.set("foo", "b", 3);
        store.set("color", "red", 2);
        store.set("color", "blue", 6);

        assertEquals("a", store.get("foo", 2));
        assertEquals("b", store.get("foo", 4));
        assertEquals("red", store.get("color", 5));
        assertEquals("blue", store.get("color", 6));
    }

    @Test
    void handlesMultipleUpdatesForSameKey() {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();

        store.set("session", "draft", 1);
        store.set("session", "review", 2);
        store.set("session", "done", 10);

        assertEquals("draft", store.get("session", 1));
        assertEquals("review", store.get("session", 9));
        assertEquals("done", store.get("session", 10));
    }
}
