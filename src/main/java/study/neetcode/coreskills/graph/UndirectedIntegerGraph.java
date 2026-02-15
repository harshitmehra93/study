package study.neetcode.coreskills.graph;

import static java.util.Objects.isNull;

import java.util.*;
import study.model.Graph;
import study.model.GraphException;
import study.model.GraphNodeBase;

public class UndirectedIntegerGraph implements Graph<Integer> {
    Set<IntegerGraphNode> nodes;

    UndirectedIntegerGraph(int numOfNodes) {
        nodes = new HashSet<>();
        for (int i = 0; i < numOfNodes; i++) {
            nodes.add(new IntegerGraphNode(i));
        }
    }

    UndirectedIntegerGraph() {
        nodes = new HashSet<>();
    }

    @Override
    public Set<IntegerGraphNode> getGraphNodes() {
        return nodes;
    }

    @Override
    public int getSize() {
        return nodes.size();
    }

    @Override
    public void addEdge(Integer a, Integer b) {
        if (isNull(a) || isNull(b)) {
            throw new GraphException("Node cannot be null");
        }
        var nodeA = getNode(a);
        var nodeB = getNode(b);
        if (nodeA == nodeB) {
            throw new GraphException("cannot create edge with one node");
        }

        Set<IntegerGraphNode> adjacencyListA = nodeA.getAdjacencyList();
        Set<IntegerGraphNode> adjacencyListB = nodeB.getAdjacencyList();

        if (adjacencyListA.contains(nodeB) && adjacencyListB.contains(nodeA))
            throw new GraphException("Edge already exists");

        adjacencyListA.add(nodeB);
        adjacencyListB.add(nodeA);
    }

    @Override
    public void addEdge(GraphNodeBase<Integer> a, GraphNodeBase<Integer> b) {
        if (isNull(a) || isNull(b)) throw getNodeDoesNotExistException();
        addEdge(a.getValue(), b.getValue());
    }

    @Override
    public void addNode(Integer node) {
        if (isNull(node)) {
            throw new GraphException("Node cannot be null");
        }
        if (isNodePresent(node)) throw new GraphException("Node already exists");
        nodes.add(new IntegerGraphNode(node));
    }

    @Override
    public IntegerGraphNode getNode(Integer node) {
        return nodes.stream()
                .filter(n -> n.getValue().equals(node))
                .findFirst()
                .orElseThrow(UndirectedIntegerGraph::getNodeDoesNotExistException);
    }

    boolean isNodePresent(Integer node) {
        return nodes.stream().anyMatch(n -> n.getValue().equals(node));
    }

    boolean isNodePresent(IntegerGraphNode node) {
        return nodes.stream().anyMatch(n -> n.equals(node));
    }

    @Override
    public void removeEdge(Integer a, Integer b) {
        if (isNull(a) || isNull(b)) {
            throw getNodeDoesNotExistException();
        }
        var nodeA = getNode(a);
        var nodeB = getNode(b);

        nodeA.getAdjacencyList().remove(nodeB);
        nodeB.getAdjacencyList().remove(nodeA);
    }

    @Override
    public Set<IntegerGraphNode> getNeighbours(Integer node) {
        if (isNull(node) || !isNodePresent(node)) {
            throw getNodeDoesNotExistException();
        }
        return getNode(node).getAdjacencyList();
    }

    @Override
    public List<IntegerGraphNode> bfs(IntegerGraphNode node) {
        if (!isNodePresent(node)) {
            throw getNodeDoesNotExistException();
        }
        List<IntegerGraphNode> result = new ArrayList<>();
        Queue<IntegerGraphNode> q = new ArrayDeque<>();
        q.offer(node);
        while (!q.isEmpty()) {
            var n = q.poll();
            System.out.println(n.getValue());
            if (!result.contains(n)) {
                result.add(n);
                getNeighbours(n.getValue())
                        .forEach(
                                n1 -> {
                                    if ( !result.contains(n1) && !q.contains(n1) ) {
                                        q.offer(n1);
                                    }
                                });
            }
        }
        return result;
    }

    private static GraphException getNodeDoesNotExistException() {
        return new GraphException("Node does not exist");
    }
}
