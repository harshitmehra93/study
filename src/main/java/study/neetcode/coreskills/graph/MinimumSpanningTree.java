package study.neetcode.coreskills.graph;

import static java.util.Objects.isNull;

import java.util.*;
import java.util.stream.Collectors;
import study.model.Edge;
import study.model.GraphNode;
import study.neetcode.coreskills.sets.forest.DisjointSets;
import study.neetcode.coreskills.sets.forest.ForestDisjointSets;

public class MinimumSpanningTree {
    Set<Edge> minimumSpanningTree = new HashSet<>();
    Map<GraphNode, Integer> nodeToWeight = new HashMap<>();

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

    public <T extends Comparable<T>> void primMst(Graph<T> graph, GraphNode<T> startingNode) {
        minimumSpanningTree.clear();
        for (var node : graph.getGraphNodes()) nodeToWeight.put(node, Integer.MAX_VALUE);
        Comparator<GraphNode<T>> comparatorForNodes =
                (n1, n2) -> nodeToWeight.get(n1) - nodeToWeight.get(n2);
        PriorityQueue<GraphNode<T>> q = new PriorityQueue<>(comparatorForNodes);

        nodeToWeight.put(startingNode, 0);
        Map<GraphNode<T>, GraphNode<T>> parentMap = new HashMap<>();
        q.offer(startingNode);
        parentMap.put(startingNode, null);
        Set<GraphNode<T>> visited = new HashSet<>();
        while (!q.isEmpty()) {
            var node = q.poll();
            var parent = parentMap.get(node);
            if (!visited.add(node)) continue;
            if (!isNull(parent)) {
                minimumSpanningTree.add(graph.getEdge(parent.getValue(), node.getValue()).get());
            }

            for (var nei : graph.getNeighbours(node.getValue())) {
                var edge = graph.getEdge(node.getValue(), nei.getValue()).get();
                if (!visited.contains(nei) && nodeToWeight.get(nei) > edge.getWeight()) {
                    nodeToWeight.put(nei, edge.getWeight());
                    parentMap.put(nei, node);
                    if (q.contains(nei)) q.remove(nei);
                    q.offer(nei);
                }
            }
        }
    }
}
