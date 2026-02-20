package study.neetcode.coreskills.graph;

import static java.util.Objects.isNull;

import java.util.*;
import study.model.Graph;
import study.model.GraphException;
import study.model.GraphNodeBase;

public class UndirectedIntegerGraph implements Graph<Integer> {
    HashMap<Integer, IntegerGraphNode> nodesMap;

    UndirectedIntegerGraph(int numOfNodes) {
        nodesMap = new HashMap<>();
        for (int i = 0; i < numOfNodes; i++) {
            nodesMap.put(i, new IntegerGraphNode(i));
        }
    }

    UndirectedIntegerGraph() {
        nodesMap = new HashMap<>();
    }

    @Override
    public Set<IntegerGraphNode> getGraphNodes() {
        return new HashSet<>(nodesMap.values());
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
        nodesMap.put(node, new IntegerGraphNode(node));
    }

    @Override
    public IntegerGraphNode getNode(Integer node) {
        if (nodesMap.containsKey(node)) return nodesMap.get(node);
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

        Set<IntegerGraphNode> adjacencyListA = nodeA.getAdjacencyList();
        Set<IntegerGraphNode> adjacencyListB = nodeB.getAdjacencyList();

        if (!adjacencyListA.contains(nodeB) && !adjacencyListB.contains(nodeA))
            throw new GraphException("Edge does not exist");

        adjacencyListA.remove(nodeB);
        adjacencyListB.remove(nodeA);
    }

    @Override
    public Set<IntegerGraphNode> getNeighbours(Integer node) {
        if (isNull(node) || !isNodePresent(node)) {
            throw getNodeDoesNotExistException();
        }
        return getNode(node).getAdjacencyList();
    }

    @Override
    public List<IntegerGraphNode> bfs(Integer node) {
        var start = getNode(node);

        List<IntegerGraphNode> result = new ArrayList<>();
        Set<IntegerGraphNode> visited = new HashSet<>();
        Queue<IntegerGraphNode> q = new ArrayDeque<>();

        visited.add(start);
        q.offer(start);

        while (!q.isEmpty()) {
            var n = q.poll();
            result.add(n);

            for (var nei : getNeighbours(n.getValue())) {
                if (visited.add(nei)) q.offer(nei);
            }
        }
        return result;
    }

    @Override
    public List<IntegerGraphNode> findShortestPath(Integer a, Integer b) {
        //        return findShortestPath(getNode(a), getNode(b), new HashSet<>());
        return findShortestPathWithBfs(getNode(a), getNode(b));
    }

    private List<IntegerGraphNode> findShortestPathWithBfs(
            IntegerGraphNode start, IntegerGraphNode finish) {
        if (start.equals(finish)) return List.of(start);
        Queue<IntegerGraphNode> q = new ArrayDeque<>();
        Set<IntegerGraphNode> visited = new HashSet<>();
        // node to parent mapping
        HashMap<IntegerGraphNode, IntegerGraphNode> parentMap = new HashMap<>();
        q.offer(start);
        visited.add(start);
        boolean isFound = false;
        while (!q.isEmpty() && !isFound) {
            var node = q.poll();

            for (var neighbour : node.getAdjacencyList()) {
                if (visited.add(neighbour)) {
                    parentMap.put(neighbour, node);
                    if (neighbour.equals(finish)) {
                        isFound = true;
                        break;
                    }
                    q.offer(neighbour);
                }
            }
        }
        if (isFound) {
            List<IntegerGraphNode> result = new ArrayList<>();
            IntegerGraphNode pointer = finish;
            result.add(finish);
            while (!pointer.equals(start)) {
                pointer = parentMap.get(pointer);
                result.add(pointer);
            }
            return result;
        }
        return null;
    }

    @Override
    public List<IntegerGraphNode> dfs(Integer node) {
        IntegerGraphNode start = getNode(node);
        List<IntegerGraphNode> result = new ArrayList<>();
        HashSet<IntegerGraphNode> visited = new HashSet<>();
        dfs(start, result, visited);
        return result;
    }

    // create an iterative dfs
    void dfs(IntegerGraphNode node, List<IntegerGraphNode> result, Set<IntegerGraphNode> visited) {
        if (isNull(node)) {
            return;
        }
        visited.add(node);
        result.add(node);
        for (var neighbour : node.getAdjacencyList()) {
            if (!visited.contains(neighbour)) dfs(neighbour, result, visited);
        }
    }

    // Find reason why this solution is in factorial runtime ?
    // create a better solution
    private List<IntegerGraphNode> findShortestPath(
            IntegerGraphNode current, IntegerGraphNode finish, HashSet<IntegerGraphNode> visited) {
        if (current == null) return null;
        if (current == finish) {
            var result = new ArrayList<IntegerGraphNode>();
            result.add(finish);
            return result;
        }
        visited.add(current);
        List<List<IntegerGraphNode>> possiblePaths = new ArrayList<>();
        for (var node : current.getAdjacencyList()) {
            if (!visited.contains(node))
                possiblePaths.add(findShortestPath(node, finish, new HashSet<>(visited)));
        }
        List<IntegerGraphNode> smallestPath = null;
        for (var path : possiblePaths) {
            if (isNull(smallestPath) && !isNull(path)) {
                smallestPath = path;
            } else if (!isNull(smallestPath) && !isNull(path)) {
                smallestPath = smallestPath.size() > path.size() ? path : smallestPath;
            }
        }

        if (smallestPath != null) smallestPath.add(current);
        return smallestPath;
    }

    private static GraphException getNodeDoesNotExistException() {
        return new GraphException("Node does not exist");
    }
}
