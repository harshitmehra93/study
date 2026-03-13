package study.model;

import static java.util.Objects.isNull;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public interface Graph<T extends Comparable> {
    Set<GraphNode<T>> getGraphNodes();

    int getSize();

    void addEdge(T a, T b);

    void addEdge(GraphNode<T> a, GraphNode<T> b);

    void addNode(T o);

    GraphNode<T> getNode(T node);

    void removeEdge(T nodeA, T nodeB);

    Set<GraphNode<T>> getNeighbours(T node);

    public default List<GraphNode<T>> bfs(T node) {
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

    public default List<GraphNode<T>> findShortestPath(T a, T b) {
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

    public default List<GraphNode<T>> dfs(T node) {
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

    void clear();
}
