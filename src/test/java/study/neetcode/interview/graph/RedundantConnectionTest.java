package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class RedundantConnectionTest {

    @Test
    void test_simpleTriangle() {
        RedundantConnection test = new RedundantConnection();

        var graph = new ArrayList<List<Integer>>();
        graph.add(List.of(1, 2));
        graph.add(List.of(1, 3));
        graph.add(List.of(2, 3));

        List<Integer> redundantConnection = test.getRedundantConnection(graph);

        assertEquals(List.of(2, 3), redundantConnection);
    }

    @Test
    void test_cycleInLongerGraph() {
        RedundantConnection test = new RedundantConnection();

        var graph = new ArrayList<List<Integer>>();
        graph.add(List.of(1, 2));
        graph.add(List.of(2, 3));
        graph.add(List.of(3, 4));
        graph.add(List.of(1, 4));
        graph.add(List.of(1, 5));

        List<Integer> redundantConnection = test.getRedundantConnection(graph);

        assertEquals(List.of(1, 4), redundantConnection);
    }

    @Test
    void test_redundantEdgeAtEnd() {
        RedundantConnection test = new RedundantConnection();

        var graph = new ArrayList<List<Integer>>();
        graph.add(List.of(1, 2));
        graph.add(List.of(2, 3));
        graph.add(List.of(3, 4));
        graph.add(List.of(4, 5));
        graph.add(List.of(5, 1));

        List<Integer> redundantConnection = test.getRedundantConnection(graph);

        assertEquals(List.of(5, 1), redundantConnection);
    }

    @Test
    void test_cycleNotStartingAtOne() {
        RedundantConnection test = new RedundantConnection();

        var graph = new ArrayList<List<Integer>>();
        graph.add(List.of(1, 2));
        graph.add(List.of(2, 3));
        graph.add(List.of(3, 4));
        graph.add(List.of(4, 2));

        List<Integer> redundantConnection = test.getRedundantConnection(graph);

        assertEquals(List.of(4, 2), redundantConnection);
    }

    @Test
    void test_branchThenCycle() {
        RedundantConnection test = new RedundantConnection();

        var graph = new ArrayList<List<Integer>>();
        graph.add(List.of(1, 2));
        graph.add(List.of(1, 3));
        graph.add(List.of(3, 4));
        graph.add(List.of(2, 4));
        graph.add(List.of(4, 5));

        List<Integer> redundantConnection = test.getRedundantConnection(graph);

        assertEquals(List.of(2, 4), redundantConnection);
    }

    @Test
    void test_minimumGraph() {
        RedundantConnection test = new RedundantConnection();

        var graph = new ArrayList<List<Integer>>();
        graph.add(List.of(1, 2));
        graph.add(List.of(1, 3));
        graph.add(List.of(2, 3));

        List<Integer> redundantConnection = test.getRedundantConnection(graph);

        assertEquals(List.of(2, 3), redundantConnection);
    }
}