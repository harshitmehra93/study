package study.neetcode.coreskills.sets;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedListDisjointSetsTest {

    private DisjointSets<Integer> disjointSets;

    @BeforeEach
    void setup() {
        disjointSets = new LinkedListDisjointSets<>();
    }

    @Test
    void makeSet_happy() {
        disjointSets = new LinkedListDisjointSets<>();
        Set<Integer> result = disjointSets.makeSet(1);
        assertTrue(result instanceof LinkedListSet<Integer>);
        assertEquals(1, disjointSets.getSize());
    }

    @Test
    void makeSet_null() {
        assertThrows(IllegalArgumentException.class, () -> disjointSets.makeSet(null));
        assertEquals(0, disjointSets.getSize());
    }

    @Test
    void makeSet_addAlreadyExistingElementToANewSet() {
        disjointSets.makeSet(1);
        assertThrows(IllegalArgumentException.class, () -> disjointSets.makeSet(1));
        assertEquals(1, disjointSets.getSize());
    }

    @Test
    void findSet() {
        disjointSets.makeSet(1);
        disjointSets.makeSet(2);
        assertTrue(disjointSets.findSet(1).isPresent());
        assertTrue(disjointSets.findSet(2).isPresent());
        assertFalse(disjointSets.findSet(3).isPresent());
        assertEquals(2, disjointSets.getSize());
    }

    @Test
    void findSet_2() {
        assertTrue(disjointSets.findSet(1).isEmpty());
        assertEquals(0, disjointSets.getSize());
    }

    @Test
    void union() {
        var a = disjointSets.makeSet(1);
        var b = disjointSets.makeSet(2);
        var c = disjointSets.makeSet(3);
        var d = disjointSets.makeSet(4);
        assertEquals(4, disjointSets.getSize());

        // A U B = AB
        assertEquals(1, a.size());
        assertEquals(1, b.size());
        var result_1 = disjointSets.union(a, b);
        assertEquals(2, result_1.size());
        assertEquals(3, disjointSets.getSize());

        // C U D = CD
        assertEquals(1, c.size());
        assertEquals(1, d.size());
        var result_2 = disjointSets.union(c, d);
        assertEquals(2, result_2.size());
        assertEquals(2, disjointSets.getSize());

        // AB U CD = ABCD
        var e = disjointSets.findSet(1).get();
        var f = disjointSets.findSet(3).get();
        assertEquals(2, e.size());
        assertEquals(2, f.size());
        var result_3 = disjointSets.union(e, f);
        assertEquals(4, result_3.size());
        assertEquals(1, disjointSets.getSize());
        for (var expectedElement : List.of(1, 2, 3, 4)) {
            assertTrue(result_3.contains(expectedElement));
        }
    }

    @Test
    void union_nulls() {
        assertThrows(IllegalArgumentException.class, () -> disjointSets.union(null, null));
    }

    @Test
    void union_nonexistentset() {
        var a = disjointSets.makeSet(1);
        Set<Integer> nonExistentSet = new LinkedListSet<>();
        nonExistentSet.add(3);
        assertThrows(IllegalArgumentException.class, () -> disjointSets.union(a, nonExistentSet));
    }

    @Test
    void union_smallerAndLarger_1() {
        var a = disjointSets.makeSet(1);
        var b = disjointSets.makeSet(2);
        var c = disjointSets.makeSet(3);
        var d = disjointSets.makeSet(4);
        var e = disjointSets.makeSet(5);

        var smaller = disjointSets.union(a, b);

        var tmp = disjointSets.union(c, d);
        var larger = disjointSets.union(tmp, e);

        var resultant = disjointSets.union(larger, smaller);
        assertTrue(resultant == larger);
    }

    @Test
    void union_smallerAndLarger_2() {
        var a = disjointSets.makeSet(1);
        var b = disjointSets.makeSet(2);
        var c = disjointSets.makeSet(3);
        var d = disjointSets.makeSet(4);
        var e = disjointSets.makeSet(5);

        var smaller = disjointSets.union(a, b);

        var tmp = disjointSets.union(c, d);
        var larger = disjointSets.union(tmp, e);

        var resultant = disjointSets.union(smaller, larger);
        assertTrue(resultant == larger);
    }

    @Test
    void union_smallerAndLarger_3() {
        var a = disjointSets.makeSet(1);
        var b = disjointSets.makeSet(2);
        var c = disjointSets.makeSet(3);
        var d = disjointSets.makeSet(4);

        var sameSize1 = disjointSets.union(a, b);
        var sameSize2 = disjointSets.union(c, d);

        var resultant = disjointSets.union(sameSize1, sameSize2);
        assertTrue(sameSize1 == resultant); // equal to first argument in union
    }

    @Test
    void union_sameSets() {
        var a = disjointSets.makeSet(1);
        assertThrows(IllegalArgumentException.class, () -> disjointSets.union(a, a));
    }
}
