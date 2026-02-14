package study.model;

import java.util.Set;

public interface Graph<T> {
    Set<GraphNodeBase<T>> getGraphNodes();

    int getSize();

    void addEdge(T a, T b);

    void addNode(T o);

    GraphNodeBase<T> getNode(T node);

    void removeEdge(T nodeA, T nodeB);
}
