package study.neetcode.interview.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LRUCacheTest {

    @Test
    void followsGivenExample() {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));

        cache.put(3, 3);
        assertEquals(-1, cache.get(2));

        cache.put(4, 4);
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    void getRefreshesRecency() {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);
        assertEquals(10, cache.get(1));

        cache.put(3, 30);

        assertEquals(-1, cache.get(2));
        assertEquals(10, cache.get(1));
        assertEquals(30, cache.get(3));
    }

    @Test
    void putExistingKeyUpdatesValueAndRefreshesRecency() {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(1, 100);
        cache.put(3, 30);

        assertEquals(100, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(30, cache.get(3));
    }

    @Test
    void capacityOneAlwaysKeepsMostRecentKey() {
        LRUCache cache = new LRUCache(1);

        cache.put(1, 10);
        assertEquals(10, cache.get(1));

        cache.put(2, 20);
        assertEquals(-1, cache.get(1));
        assertEquals(20, cache.get(2));

        cache.put(2, 200);
        assertEquals(200, cache.get(2));

        cache.put(3, 30);
        assertEquals(-1, cache.get(2));
        assertEquals(30, cache.get(3));
    }

    @Test
    void missingKeyDoesNotChangeRecency() {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);
        assertEquals(-1, cache.get(99));
        cache.put(3, 30);

        assertEquals(-1, cache.get(1));
        assertEquals(20, cache.get(2));
        assertEquals(30, cache.get(3));
    }

    @Test
    void updatingExistingKeyDoesNotIncreaseSize() {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 10);
        cache.put(1, 100);
        cache.put(2, 20);

        assertEquals(100, cache.get(1));
        assertEquals(20, cache.get(2));
    }
}
