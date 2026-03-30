package study.neetcode.coreskills.sets.forest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ForestDisjointSetTest {

    private ForestDisjointSets<Integer> disjointSets;

    @BeforeEach
    void setup() {
        disjointSets = new ForestDisjointSets<>();
    }

    @Test
    void makeSet_nullInput() {
        assertThrows(IllegalArgumentException.class, () -> disjointSets.makeSet(null));
    }

    @Test
    void makeSet_happy() {
        assertEquals(0, disjointSets.getSize());
        disjointSets.makeSet(1);
        assertEquals(1, disjointSets.getSize());
    }

    @Test
    void makeSet_setAlreadyExists() {
        assertEquals(0, disjointSets.getSize());
        disjointSets.makeSet(1);
        assertEquals(1, disjointSets.getSize());
        assertThrows(IllegalArgumentException.class, () -> disjointSets.makeSet(1));
    }

    @Test
    void findSet_nullNodeThrows() {
        assertThrows(IllegalArgumentException.class, () -> disjointSets.findSet(null));
    }

    @Test
    void findSet_nonExistentNode() {
        assertTrue(disjointSets.findSet(1).isEmpty());
    }

    @Test
    void findSet_happy() {
        disjointSets.makeSet(1);

        Optional<Integer> result = disjointSets.findSet(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get());
    }

    @Test
    void findSet_happy2() {
        //        disjointSets.makeSet(1);
        //        disjointSets.makeSet(1);
        //
        //        Optional<Integer> result = disjointSets.findSet(1);
        //
        //        assertTrue(result.isPresent());
        //        assertEquals(1, result.get());
    }

    @Test
    void union_null() {
        assertThrows(IllegalArgumentException.class, () -> disjointSets.union(null, null));
    }

    @Test
    void union_nonExistent() {
        assertThrows(IllegalArgumentException.class, () -> disjointSets.union(1, 2));
    }

    @Test
    void union_happy() {
        disjointSets.makeSet(1);
        disjointSets.makeSet(2);

        assertEquals(2, disjointSets.getSize());

        disjointSets.union(1, 2);

        assertEquals(1, disjointSets.getSize());
        assertEquals(1, disjointSets.findSet(1).get());
        assertEquals(1, disjointSets.findSet(2).get());
    }

    @Test
    void union_happy2() {
        disjointSets.makeSet(1);
        disjointSets.makeSet(2);
        disjointSets.makeSet(3);
        disjointSets.makeSet(4);
        assertEquals(4, disjointSets.getSize());

        // 1 U 2 = 12
        disjointSets.union(1, 2);
        assertEquals(3, disjointSets.getSize());
        assertEquals(1, disjointSets.findSet(1).get());
        assertEquals(1, disjointSets.findSet(2).get());
        assertEquals(3, disjointSets.findSet(3).get());
        assertEquals(4, disjointSets.findSet(4).get());

        // 3 U 4 = 34
        disjointSets.union(3, 4);
        assertEquals(2, disjointSets.getSize());
        assertEquals(1, disjointSets.findSet(1).get());
        assertEquals(1, disjointSets.findSet(2).get());
        assertEquals(3, disjointSets.findSet(3).get());
        assertEquals(3, disjointSets.findSet(4).get());

        // 12 U 34 = 1234
        disjointSets.union(2, 4);
        assertEquals(1, disjointSets.getSize());
        assertEquals(1, disjointSets.findSet(1).get());
        assertEquals(1, disjointSets.findSet(2).get());
        assertEquals(1, disjointSets.findSet(3).get());
        assertEquals(1, disjointSets.findSet(4).get());
    }

    @Test
    void getAllelementsOfSet_nonExistent() {
        assertThrows(
                IllegalArgumentException.class, () -> disjointSets.getAllElementsOfSet(1).size());
    }

    @Test
    void getAllelementsOfSet_null() {
        assertThrows(
                IllegalArgumentException.class,
                () -> disjointSets.getAllElementsOfSet(null).size());
    }

    @Test
    void getAllelementsOfSet_happy() {
        disjointSets.makeSet(1);
        disjointSets.makeSet(2);

        assertEquals(1, disjointSets.getAllElementsOfSet(1).size());
        assertContainsElements(disjointSets.getAllElementsOfSet(1), List.of(1));
        assertEquals(1, disjointSets.getAllElementsOfSet(2).size());
        assertContainsElements(disjointSets.getAllElementsOfSet(2), List.of(2));

        disjointSets.union(1, 2);

        assertEquals(2, disjointSets.getAllElementsOfSet(1).size());
        assertContainsElements(disjointSets.getAllElementsOfSet(1), List.of(1, 2));
    }

    @Test
    void findSet_deepChain_noPathCompression() {
        int n = 10000;
        for (int i = 1; i <= n; i++) {
            disjointSets.makeSet(i);
        }

        // Create chain: 1 <- 2 <- 3 <- ... <- n
        for (int i = 2; i <= n; i++) {
            disjointSets.union(i - 1, i);
        }

        // This will be very slow or may stack overflow
        assertEquals(1, disjointSets.findSet(1).get());
        assertEquals(1, disjointSets.roots.size());
        assertEquals(n, disjointSets.elementSet.size());
    }

    private void assertContainsElements(Set<Integer> allElementsOfSet, List<Integer> integers) {
        for (var i : integers) assertTrue(allElementsOfSet.contains(i));
    }
}
