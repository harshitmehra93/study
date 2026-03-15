package study.neetcode.coreskills.graph;

import static java.util.Objects.isNull;

import java.util.*;
import study.model.Edge;
import study.model.GraphException;
import study.model.GraphNode;

public abstract class Graph<T extends Comparable> {
    public Set<GraphNode<T>> getGraphNodes() {
        return new HashSet<>(getNodesMap().values());
    }

    public int getSize() {
        return getNodesMap().size();
    }

    public abstract void addEdge(T a, T b);

    public abstract void addEdge(GraphNode<T> a, GraphNode<T> b);

    public void addNode(T node) {
        if (isNull(node)) {
            throw new GraphException("Node cannot be null");
        }
        if (isNodePresent(node)) throw new GraphException("Node already exists");
        getNodesMap().put(node, new GraphNode<T>(node));
    }

    public GraphNode<T> getNode(T node) {
        if (getNodesMap().containsKey(node)) return getNodesMap().get(node);
        throw new GraphException("node does not exist");
    }

    public abstract void removeEdge(T nodeA, T nodeB);

    public abstract Map<T, GraphNode<T>> getNodesMap();

    public boolean isNodePresent(T node) {
        return getNodesMap().containsKey(node);
    }

    public Set<GraphNode<T>> getNeighbours(T node) {
        if (isNull(node) || !isNodePresent(node)) {
            throw new GraphException("node does not exist");
        }
        return Collections.unmodifiableSet(getNode(node).getAdjacencyList());
    }

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

            for (var neighbour : getNeighbours(node.getValue())) {
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

    public List<GraphNode<T>> dfs(T node) {
        GraphNode start = getNode(node);
        List<GraphNode<T>> result = new ArrayList<>();
        HashSet<GraphNode<T>> visited = new HashSet<>();
        dfs(start, result, visited);
        return result;
    }

    // create an iterative dfs
    private void dfs(GraphNode<T> node, List<GraphNode<T>> result, Set<GraphNode<T>> visited) {
        if (isNull(node)) {
            return;
        }
        visited.add(node);
        result.add(node);
        for (var neighbour : getNeighbours(node.getValue())) {
            if (!visited.contains(neighbour)) dfs(neighbour, result, visited);
        }
    }

    public void clear() {
        getNodesMap().clear();
    }

    public abstract Optional<Edge<T>> getEdge(T node1, T node2);

    public abstract Set<Edge<T>> getEdges();
}
