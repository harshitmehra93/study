package study.model;

import java.util.List;
import java.util.Set;

public interface Graph<T extends Comparable> {
    Set<GraphNode<T>> getGraphNodes();

    int getSize();

    void addEdge(T a, T b);

    void addEdge(GraphNode<T> a, GraphNode<T> b);

    void addNode(T o);

    GraphNode<T> getNode(T node);

    void removeEdge(T nodeA, T nodeB);

    List<GraphNode<T>> getNeighbours(T node);

    List<GraphNode<T>> bfs(T node);

    List<GraphNode<T>> findShortestPath(T start, T finish);

    List<GraphNode<T>> dfs(T node);
}
