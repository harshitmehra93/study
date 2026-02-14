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

        Set<IntegerGraphNode> adjacencyListA = nodeA.getAdjacencyList();
        Set<IntegerGraphNode> adjacencyListB = nodeB.getAdjacencyList();

        if(adjacencyListA.contains(nodeB)&&adjacencyListB.contains(nodeA))
            throw new GraphException("Edge already exists");

        adjacencyListA.add(nodeB);
        adjacencyListB.add(nodeA);
    }

    @Override
    public void addEdge(GraphNodeBase<Integer> a, GraphNodeBase<Integer> b) {
        if(isNull(a)||isNull(b))
            throw getNodeDoesNotExistException();
        addEdge(a.getValue(),b.getValue());
    }

    @Override
    public void addNode(Integer node) {
        if (isNull(node)) {
            throw new GraphException("Node cannot be null");
        }
        if(isNodePresent(node))
            throw new GraphException("Node already exists");
        nodes.add(new IntegerGraphNode(node));
    }

    @Override
    public IntegerGraphNode getNode(Integer node) {
        return nodes.stream()
                .filter(n -> n.getValue().equals(node))
                .findFirst()
                .orElseThrow(UndirectedIntegerGraph::getNodeDoesNotExistException);
    }

    boolean isNodePresent(Integer node){
        return nodes.stream().anyMatch(n->n.getValue().equals(node));
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
    public List<IntegerGraphNode> getNeighbours(Integer node) {
        if(isNull(node) || !isNodePresent(node)){
            throw getNodeDoesNotExistException();
        }
        List<IntegerGraphNode> adjacencyList = new ArrayList<>(getNode(node).getAdjacencyList());
        Collections.sort(adjacencyList);
        return adjacencyList;
    }

    private static GraphException getNodeDoesNotExistException() {
        return new GraphException("Node does not exist");
    }
}
