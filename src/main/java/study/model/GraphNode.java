package study.model;

import java.util.*;

public class GraphNode<T extends Comparable> implements Comparable<GraphNode<T>> {
    T value;
    Set<GraphNode<T>> adjacencyList;

    public GraphNode(T value) {
        this.value = value;
        adjacencyList = new TreeSet<>();
    }

    public T getValue() {
        return value;
    }

    public Set<GraphNode<T>> getAdjacencyList() {
        return adjacencyList;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GraphNode graphNode) {
            return graphNode.getValue().equals(value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int compareTo(GraphNode<T> o) {
        return this.getValue().compareTo(o.getValue());
    }
}
