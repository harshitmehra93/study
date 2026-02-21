package study.neetcode.coreskills.graph;

import static java.util.Objects.isNull;

import java.util.*;
import study.model.Graph;
import study.model.GraphException;
import study.model.GraphNode;

public class UndirectedGraph<T extends Comparable> implements Graph<T> {
    HashMap<T, GraphNode<T>> nodesMap;

    UndirectedGraph() {
        nodesMap = new HashMap<>();
    }

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
        if (isNull(a) || isNull(b)) {
            throw new GraphException("Node cannot be null");
        }
        var nodeA = getNode(a);
        var nodeB = getNode(b);
        if (nodeA == nodeB) {
            throw new GraphException("cannot create edge with one node");
        }

        Set<GraphNode<T>> adjacencyListA = nodeA.getAdjacencyList();
        Set<GraphNode<T>> adjacencyListB = nodeB.getAdjacencyList();

        if (adjacencyListA.contains(nodeB) && adjacencyListB.contains(nodeA))
            throw new GraphException("Edge already exists");

        adjacencyListA.add(nodeB);
        adjacencyListB.add(nodeA);
    }

    @Override
    public void addEdge(GraphNode<T> a, GraphNode<T> b) {
        if (isNull(a) || isNull(b)) throw getNodeDoesNotExistException();
        addEdge(a.getValue(), b.getValue());
    }

    @Override
    public void addNode(T node) {
        if (isNull(node)) {
            throw new GraphException("Node cannot be null");
        }
        if (isNodePresent(node)) throw new GraphException("Node already exists");
        nodesMap.put(node, new GraphNode<T>(node));
    }

    @Override
    public GraphNode<T> getNode(T node) {
        if (nodesMap.containsKey(node)) return nodesMap.get(node);
        throw getNodeDoesNotExistException();
    }

    boolean isNodePresent(T node) {
        return nodesMap.containsKey(node);
    }

    boolean isNodePresent(GraphNode node) {
        return nodesMap.containsKey(node.getValue());
    }

    @Override
    public void removeEdge(T a, T b) {
        if (isNull(a) || isNull(b)) {
            throw getNodeDoesNotExistException();
        }
        var nodeA = getNode(a);
        var nodeB = getNode(b);

        Set<GraphNode<T>> adjacencyListA = nodeA.getAdjacencyList();
        Set<GraphNode<T>> adjacencyListB = nodeB.getAdjacencyList();

        if (!adjacencyListA.contains(nodeB) && !adjacencyListB.contains(nodeA))
            throw new GraphException("Edge does not exist");

        adjacencyListA.remove(nodeB);
        adjacencyListB.remove(nodeA);
    }

    @Override
    public Set<GraphNode<T>> getNeighbours(T node) {
        if (isNull(node) || !isNodePresent(node)) {
            throw getNodeDoesNotExistException();
        }
        return Collections.unmodifiableSet(getNode(node).getAdjacencyList());
    }

    @Override
    public List<GraphNode<T>> bfs(T node) {
        var start = getNode(node);

        List<GraphNode<T>> result = new ArrayList<>();
        Set<GraphNode<T>> visited = new HashSet<>();
        Queue<GraphNode<T>> q = new ArrayDeque<>();

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
    public List<GraphNode<T>> findShortestPath(T a, T b) {
        //        return findShortestPath(getNode(a), getNode(b), new HashSet<>());
        return findShortestPathWithBfs(getNode(a), getNode(b));
    }

    private List<GraphNode<T>> findShortestPathWithBfs(GraphNode start, GraphNode finish) {
        if (start.equals(finish)) return List.of(start);
        Queue<GraphNode<T>> q = new ArrayDeque<>();
        Set<GraphNode<T>> visited = new HashSet<>();
        // node to parent mapping
        HashMap<GraphNode<T>, GraphNode<T>> parentMap = new HashMap<>();
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
            List<GraphNode<T>> result = new ArrayList<>();
            GraphNode pointer = finish;
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
    public List<GraphNode<T>> dfs(T node) {
        GraphNode start = getNode(node);
        List<GraphNode<T>> result = new ArrayList<>();
        HashSet<GraphNode<T>> visited = new HashSet<>();
        dfs(start, result, visited);
        return result;
    }

    // create an iterative dfs
    void dfs(GraphNode<T> node, List<GraphNode<T>> result, Set<GraphNode<T>> visited) {
        if (isNull(node)) {
            return;
        }
        visited.add(node);
        result.add(node);
        for (var neighbour : node.getAdjacencyList()) {
            if (!visited.contains(neighbour)) dfs(neighbour, result, visited);
        }
    }

    // This method explores all paths with back tracking
    // Find reason why this solution is in factorial runtime ?
    // For a 6 node complete graph
    // the number of path from a to b can be -
    // direct a to b = 1
    // with 1 intermediate = N-2 = 4
    // with 2 intermediate = (N-2)(N-3) = 4*3 = 12
    // with 3 intermediate = (N-2)(N-3)(N-4) = 4*3*2 = 24
    // with 4 intermediate = (N-2)(N-3)(N-4)(N-5) = 4*3*2*1 = 24
    // Total = 1+4+12+24+24 = 65 which is in factorial order
    // create a better solution - DONE
    private List<GraphNode> findShortestPath(
            GraphNode<T> current, GraphNode<T> finish, HashSet<GraphNode<T>> visited) {
        if (current == null) return null;
        if (current == finish) {
            var result = new ArrayList<GraphNode>();
            result.add(finish);
            return result;
        }
        visited.add(current);
        List<List<GraphNode>> possiblePaths = new ArrayList<>();
        for (var node : current.getAdjacencyList()) {
            if (!visited.contains(node))
                possiblePaths.add(findShortestPath(node, finish, new HashSet<>(visited)));
        }
        List<GraphNode> smallestPath = null;
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
