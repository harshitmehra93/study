package study.neetcode.coreskills.hashtable;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTableTest {
    @Test
    void createHashTable() {
        HashTable ht = new HashTableImpl();
        assertNotNull(ht);
        assertEquals(0, ht.getSize());
    }

    @Test
    void createHashTableWithCapacity() {
        HashTable ht = new HashTableImpl(2);
        assertNotNull(ht);
        assertEquals(0, ht.getSize());
        assertEquals(2, ht.getCapacity());
    }

    @Test
    void get_emptyTable_returnsMinus1() {
        HashTable ht = new HashTableImpl();
        assertEquals(-1, ht.get(1));
    }

    @Test
    void put_emptyTable_returnsTrue() {
        HashTable ht = new HashTableImpl();
        assertEquals(Boolean.TRUE, ht.put(1, 2));
    }

    @Test
    void put_emptyTable_returnsTrue_size1() {
        HashTable ht = new HashTableImpl();
        assertEquals(Boolean.TRUE, ht.put(1, 2));
        assertEquals(1, ht.getSize());
    }

    @Test
    void put_CapacityFull_IncreasesCapacity() {
        HashTable ht = new HashTableImpl(1);
        assertEquals(1, ht.getCapacity());
        assertEquals(Boolean.TRUE, ht.put(1, 2));
        assertEquals(1, ht.getSize());
        assertEquals(2, ht.getCapacity());
    }

    @Test
    void put_get_returnsElement() {
        HashTable ht = new HashTableImpl();
        ht.put(1, 2);

        assertEquals(2, ht.get(1));
    }

    @Test
    void put_put_get_returnsElement() {
        HashTable ht = new HashTableImpl();
        ht.put(1, 2);
        ht.put(2, 3);

        assertEquals(2, ht.get(1));
        assertEquals(3, ht.get(2));
        assertEquals(-1, ht.get(3));
    }

    @Test
    void put_get_overwrite_get_returnsElement() {
        HashTable ht = new HashTableImpl();
        ht.put(1, 2);
        ht.put(2, 3);

        assertEquals(2, ht.get(1));
        assertEquals(3, ht.get(2));

        ht.put(1, 5);

        assertEquals(5, ht.get(1));
    }

    @Test
    void put_KeysWithSameHash_getSucceeds() {
        HashTable ht = new HashTableImpl(7);
        ht.put(7, 1);
        ht.put(14, 2);
        ht.put(21, 3);
        ht.put(28, 4);

        assertEquals(1, ht.get(7));
        assertEquals(2, ht.get(14));
        assertEquals(3, ht.get(21));
        assertEquals(4, ht.get(28));

        ht.put(1, 5);

        assertEquals(5, ht.get(1));
    }

    @Test
    void put_KeysWithSameHash_getSucceeds_2() {
        HashTable ht = new HashTableImpl(7);
        ht.put(7, 7);
        ht.put(14, 14);
        ht.put(3, 3);
        ht.put(4, 4);
        ht.put(1, 1);

        assertEquals(7, ht.get(7));
        assertEquals(14, ht.get(14));
        assertEquals(3, ht.get(3));
        assertEquals(4, ht.get(4));
        assertEquals(1, ht.get(1));

        ht.put(1, 5);

        assertEquals(5, ht.get(1));
    }

    @Test
    void capacity4Table_AfterAdding3_CapacityDoubles() {
        HashTable ht = new HashTableImpl(4);
        assertEquals(4, ht.getCapacity());
        ht.put(1, 2);
        ht.put(2, 3);
        ht.put(3, 4);
        assertEquals(8, ht.getCapacity());
    }

    @Test
    void capacity8Table_AfterAdding6_CapacityDoubles_TableRehashes() {
        HashTable ht = new HashTableImpl(8);
        assertEquals(8, ht.getCapacity());
        ht.put(1, 1);
        ht.put(2, 2);
        ht.put(3, 3);
        ht.put(5, 5);
        ht.put(6, 6);
        ht.put(7, 7);

        assertEquals(16, ht.getCapacity());
        assertEquals(1, ht.get(1));
        assertEquals(2, ht.get(2));
        assertEquals(3, ht.get(3));
        assertEquals(5, ht.get(5));
        assertEquals(6, ht.get(6));
        assertEquals(7, ht.get(7));
    }

    @Test
    void capacity8Table_AfterAdding6_CapacityDoubles_TableRehashes_2() {
        HashTable ht = new HashTableImpl(8);
        assertEquals(8, ht.getCapacity());
        ht.put(1, 1);
        ht.put(9, 9);
        ht.put(17, 17);
        ht.put(25, 25);
        ht.put(33, 33);

        assertEquals(1, ht.get(1));
        assertEquals(9, ht.get(9));
        assertEquals(17, ht.get(17));
        assertEquals(25, ht.get(25));
        assertEquals(33, ht.get(33));

        ht.put(41, 41);

        assertEquals(16, ht.getCapacity());
        assertEquals(1, ht.get(1));
        assertEquals(9, ht.get(9));
        assertEquals(17, ht.get(17));
        assertEquals(25, ht.get(25));
        assertEquals(33, ht.get(33));
        assertEquals(41, ht.get(41));
    }

    @Test
    void removeKey_emptyTable_returnsFalse() {
        HashTable ht = new HashTableImpl();
        assertFalse(ht.remove(1));
    }

    @Test
    void addKey_removeKey_succeeds() {
        HashTable ht = new HashTableImpl();

        ht.put(1, 1);

        assertEquals(1, ht.get(1));
        assertEquals(1, ht.getSize());

        assertTrue(ht.remove(1));

        assertEquals(-1, ht.get(1));
        assertEquals(0, ht.getSize());
    }

    @Test
    void addKey_addKey_removeKey_succeeds() {
        HashTable ht = new HashTableImpl();

        ht.put(1, 1);
        ht.put(2, 2);

        assertEquals(1, ht.get(1));
        assertEquals(2, ht.get(2));
        assertEquals(2, ht.getSize());

        assertTrue(ht.remove(1));
        assertFalse(ht.remove(1));

        assertEquals(-1, ht.get(1));
        assertEquals(2, ht.get(2));
        assertEquals(1, ht.getSize());
    }

    @Test
    void addKeySameHash_removeKey_succeeds() {
        HashTable ht = new HashTableImpl(8);

        ht.put(1, 1);
        ht.put(9, 9);
        ht.put(17, 17);
        ht.put(25, 25);
        ht.put(33, 33);

        assertEquals(1, ht.get(1));
        assertEquals(9, ht.get(9));
        assertEquals(17, ht.get(17));
        assertEquals(25, ht.get(25));
        assertEquals(33, ht.get(33));
        assertEquals(5, ht.getSize());

        assertEquals(Boolean.TRUE, ht.remove(1));
        assertEquals(Boolean.FALSE, ht.remove(1));
        assertEquals(-1, ht.get(1));

        assertEquals(9, ht.get(9));
        assertEquals(17, ht.get(17));
        assertEquals(25, ht.get(25));
        assertEquals(33, ht.get(33));
        assertEquals(4, ht.getSize());
    }

    @Test
    void add_remove_addTillRehash() {
        HashTable ht = new HashTableImpl(8);

        ht.put(1, 1);
        ht.put(9, 9);
        ht.put(17, 17);
        ht.put(25, 25);
        ht.put(33, 33);

        assertEquals(1, ht.get(1));
        assertEquals(9, ht.get(9));
        assertEquals(17, ht.get(17));
        assertEquals(25, ht.get(25));
        assertEquals(33, ht.get(33));
        assertEquals(5, ht.getSize());
        assertEquals(8, ht.getCapacity());

        assertEquals(Boolean.TRUE, ht.remove(1));
        assertEquals(Boolean.FALSE, ht.remove(1));
        assertEquals(-1, ht.get(1));
        assertEquals(4, ht.getSize());

        ht.put(2, 2);
        ht.put(4, 4);

        assertEquals(16, ht.getCapacity());
        assertEquals(6, ht.getSize());

        assertEquals(9, ht.get(9));
        assertEquals(17, ht.get(17));
        assertEquals(25, ht.get(25));
        assertEquals(33, ht.get(33));
        assertEquals(2, ht.get(2));
        assertEquals(4, ht.get(4));
    }

    @Test
    void overwrite_doesNotIncreaseSize() {
        HashTable ht = new HashTableImpl();
        ht.put(1, 1);
        assertEquals(1, ht.getSize());
        ht.put(1, 9);
        assertEquals(1, ht.getSize());
    }
}
