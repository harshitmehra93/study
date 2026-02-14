package study.model;

import java.util.List;
import java.util.Set;
import study.neetcode.coreskills.graph.IntegerGraphNode;

public interface Graph<T> {
    Set<? extends GraphNodeBase<T>> getGraphNodes();

    int getSize();

    void addEdge(T a, T b);

    void addEdge(GraphNodeBase<T> a, GraphNodeBase<T> b);

    void addNode(T o);

    GraphNodeBase<T> getNode(T node);

    void removeEdge(T nodeA, T nodeB);

    List<? extends GraphNodeBase<T>> getNeighbours(T node);

    List<IntegerGraphNode> bfs(IntegerGraphNode node);
}
