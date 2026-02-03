package study.neetcode.coreskills.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    @Test
    void createHashTable(){
        HashTable ht = new HashTableImpl();
        assertNotNull(ht);
        assertEquals(0,ht.getSize());
    }

    @Test
    void createHashTableWithCapacity(){
        HashTable ht = new HashTableImpl(2);
        assertNotNull(ht);
        assertEquals(0,ht.getSize());
        assertEquals(2,ht.getCapacity());
    }

    @Test
    void get_emptyTable_returnsMinus1(){
        HashTable ht = new HashTableImpl();
        assertEquals(-1,ht.get(1));
    }

    @Test
    void put_emptyTable_returnsTrue(){
        HashTable ht = new HashTableImpl();
        assertEquals(Boolean.TRUE,ht.put(1,2));
    }

    @Test
    void put_emptyTable_returnsTrue_size1(){
        HashTable ht = new HashTableImpl();
        assertEquals(Boolean.TRUE,ht.put(1,2));
        assertEquals(1,ht.getSize());
    }

    @Test
    void put_CapacityFull_IncreasesCapacity(){
        HashTable ht = new HashTableImpl(1);
        assertEquals(1,ht.getCapacity());
        assertEquals(Boolean.TRUE,ht.put(1,2));
        assertEquals(1,ht.getSize());
        assertEquals(2,ht.getCapacity());
    }

    @Test
    void put_get_returnsElement(){
        HashTable ht = new HashTableImpl();
        ht.put(1,2);

        assertEquals(2,ht.get(1));
    }

    @Test
    void put_put_get_returnsElement(){
        HashTable ht = new HashTableImpl();
        ht.put(1,2);
        ht.put(2,3);

        assertEquals(2,ht.get(1));
        assertEquals(3,ht.get(2));
        assertEquals(-1,ht.get(3));
    }

    @Test
    void put_get_overwrite_get_returnsElement(){
        HashTable ht = new HashTableImpl();
        ht.put(1,2);
        ht.put(2,3);

        assertEquals(2,ht.get(1));
        assertEquals(3,ht.get(2));

        ht.put(1,5);

        assertEquals(5,ht.get(1));
    }
}