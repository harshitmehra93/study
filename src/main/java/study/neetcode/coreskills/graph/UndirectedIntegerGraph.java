package study.neetcode.coreskills.graph;

import static java.util.Objects.isNull;

import java.util.HashSet;
import java.util.Set;
import study.model.Graph;
import study.model.GraphException;
import study.model.GraphNodeBase;

public class UndirectedIntegerGraph implements Graph<Integer> {
    Set<GraphNodeBase<Integer>> nodes;

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
    public Set<GraphNodeBase<Integer>> getGraphNodes() {
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

        nodeA.getAdjacencyList().add(nodeB);
        nodeB.getAdjacencyList().add(nodeA);
    }

    @Override
    public void addNode(Integer node) {
        if (isNull(node)) {
            throw new GraphException("Node cannot be null");
        }
        nodes.add(new IntegerGraphNode(node));
    }

    @Override
    public GraphNodeBase<Integer> getNode(Integer node) {
        return nodes.stream()
                .filter(n -> n.getValue().equals(node))
                .findFirst()
                .orElseThrow(UndirectedIntegerGraph::getNodeDoesNotExistException);
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

    private static GraphException getNodeDoesNotExistException() {
        return new GraphException("Node does not exist");
    }
}
