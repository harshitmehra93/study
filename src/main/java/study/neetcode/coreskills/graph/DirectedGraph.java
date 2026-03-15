package study.neetcode.coreskills.graph;

import java.util.*;
import study.model.Edge;
import study.model.GraphNode;

public class DirectedGraph<T extends Comparable> extends Graph<T> {
    HashMap<T, GraphNode<T>> nodesMap = new HashMap<>();
    Set<Edge<T>> edges = new HashSet<>();

    @Override
    public Set<GraphNode<T>> getGraphNodes() {
        return new HashSet<>(nodesMap.values());
    }

    @Override
    public int getSize() {
        return nodesMap.size();
    }

    @Override
    public void addEdge(T a, T b) {}

    @Override
    public void addEdge(GraphNode<T> a, GraphNode<T> b) {}

    @Override
    public void addNode(T o) {}

    @Override
    public GraphNode<T> getNode(T node) {
        return null;
    }

    @Override
    public void removeEdge(T nodeA, T nodeB) {}

    @Override
    public Map<T, GraphNode<T>> getNodesMap() {
        return nodesMap;
    }

    @Override
    public Optional<Edge<T>> getEdge(T node1, T node2) {
        return Optional.empty();
    }

    @Override
    public Set<Edge<T>> getEdges() {
        return null;
    }
}
