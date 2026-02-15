package study.neetcode.coreskills.graph;

import static java.util.Objects.isNull;

import java.util.*;
import study.model.Graph;
import study.model.GraphException;
import study.model.GraphNodeBase;

public class UndirectedIntegerGraph implements Graph<Integer> {
    HashMap<Integer,IntegerGraphNode> nodesMap;

    UndirectedIntegerGraph(int numOfNodes) {
        nodesMap = new HashMap<>();
        for (int i = 0; i < numOfNodes; i++) {
            nodesMap.put(i,new IntegerGraphNode(i));
        }
    }

    UndirectedIntegerGraph() {
        nodesMap = new HashMap<>();
    }

    @Override
    public Set<IntegerGraphNode> getGraphNodes() {
        return nodesMap.entrySet().stream()
                .collect(
                        HashSet::new,
                        (set,e)->set.add(e.getValue()),
                        (s1,s2)->s1.addAll(s2));
    }

    @Override
    public int getSize() {
        return nodesMap.size();
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
        nodesMap.put(node,new IntegerGraphNode(node));
    }

    @Override
    public IntegerGraphNode getNode(Integer node) {
        if(nodesMap.containsKey(node))
            return nodesMap.get(node);
        throw getNodeDoesNotExistException();
    }

    boolean isNodePresent(Integer node) {
        return nodesMap.containsKey(node);
    }

    boolean isNodePresent(IntegerGraphNode node) {
        return nodesMap.containsKey(node.getValue());
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
