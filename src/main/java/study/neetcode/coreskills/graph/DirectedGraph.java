package study.neetcode.coreskills.graph;

import static java.util.Objects.isNull;

import java.util.*;
import study.model.DirectedEdge;
import study.model.Edge;
import study.model.GraphNode;
import study.model.exceptions.GraphException;

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
    public void addEdge(T a, T b) {
        if (isNull(a) || isNull(b)) throw new GraphException("node cannot be null");
        GraphNode<T> nodeA = getNode(a);
        GraphNode<T> nodeB = getNode(b);
        if (nodeA.equals(nodeB)) throw new GraphException("self edge is not allowed");
        addEdge(nodeA, nodeB);
    }

    @Override
    public void addEdge(GraphNode<T> nodeA, GraphNode<T> nodeB) {
        if (isNull(nodeA) || isNull(nodeB)) throw new GraphException("node cannot be null");
        if (nodeA.equals(nodeB)) throw new GraphException("self edge is not allowed");
        if (!isNodePresent(nodeA.getValue()) || !isNodePresent(nodeB.getValue())) {
            throw new GraphException("node does not exist");
        }
        DirectedEdge<T> edge = new DirectedEdge<>(nodeA, nodeB);
        if (edges.contains(edge)) throw new GraphException("edge already exists");

        nodeA.getAdjacencyList().add(nodeB);
        edges.add(edge);
    }

    @Override
    public void addNode(T nodeValue) {
        if (isNull(nodeValue)) throw new GraphException("node cannot be null");
        if (isNodePresent(nodeValue)) throw new GraphException("node is already present");
        getNodesMap().put(nodeValue, new GraphNode<>(nodeValue));
    }

    @Override
    public GraphNode<T> getNode(T nodeValue) {
        if (!isNodePresent(nodeValue)) throw new GraphException("node does not exist");
        return getNodesMap().get(nodeValue);
    }

    @Override
    public void removeEdge(T a, T b) {
        GraphNode<T> nodeA = getNode(a);
        GraphNode<T> nodeB = getNode(b);
        if (nodeA.equals(nodeB)) throw new GraphException("self edges are not allowed");
        DirectedEdge<T> edge = new DirectedEdge<>(nodeA, nodeB);
        if (!edges.contains(edge)) throw new GraphException("edge does not exist");
        nodeA.getAdjacencyList().remove(nodeB);
        edges.remove(edge);
    }

    @Override
    public Map<T, GraphNode<T>> getNodesMap() {
        return nodesMap;
    }

    @Override
    public Optional<Edge<T>> getEdge(T node1, T node2) {
        GraphNode<T> nodeA = getNode(node1);
        GraphNode<T> nodeB = getNode(node2);

        return getEdges().stream()
                .filter(edge -> edge.vertice1.equals(nodeA) && edge.vertice2.equals(nodeB))
                .findFirst();
    }

    @Override
    public Set<Edge<T>> getEdges() {
        return edges;
    }
}
