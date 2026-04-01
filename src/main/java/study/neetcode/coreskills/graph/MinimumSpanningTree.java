package study.neetcode.coreskills.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import study.model.Edge;
import study.model.GraphNode;
import study.neetcode.coreskills.sets.forest.DisjointSets;
import study.neetcode.coreskills.sets.forest.ForestDisjointSets;

public class MinimumSpanningTree {
    Set<Edge> minimumSpanningTree = new HashSet<>();

    public <T extends Comparable<T>> void kruskalMst(Graph<T> graph) {
        minimumSpanningTree.clear();
        DisjointSets<GraphNode<T>> disjointSets = new ForestDisjointSets<>();

        for (var node : graph.getGraphNodes()) disjointSets.makeSet(node);

        Comparator<Edge<T>> weightComparator = Comparator.comparingInt(Edge::getWeight);
        List<Edge> edgesSortedByWeight =
                graph.getEdges().stream().sorted(weightComparator).collect(Collectors.toList());

        for (var edge : edgesSortedByWeight) {
            if (!disjointSets.findSet(edge.vertice1).equals(disjointSets.findSet(edge.vertice2))) {
                disjointSets.union(edge.vertice1, edge.vertice2);
                minimumSpanningTree.add(edge);
            }
        }
    }
}
