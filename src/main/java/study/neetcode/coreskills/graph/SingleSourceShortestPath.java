package study.neetcode.coreskills.graph;

import java.util.*;
import study.model.GraphNode;

public class SingleSourceShortestPath<T extends Comparable<T>> {
    Map<GraphNode, GraphNode> parents = new HashMap<>();
    Map<GraphNode, Integer> distanceFromSource = new HashMap<>();
    Map<GraphNode, Integer> startingTime = new HashMap<>();
    Map<GraphNode, Integer> finishingTime = new HashMap<>();
    HashSet<GraphNode> visited = new HashSet<>();
    List<GraphNode<T>> topologicalSortResult = new ArrayList<>();

    public void bellmanFord(Graph<T> graph, GraphNode<T> s) {
        parents.clear();
        distanceFromSource.clear();
        for (var node : graph.getGraphNodes()) {
            distanceFromSource.put(node, Integer.MAX_VALUE);
            parents.put(node, null);
        }
        distanceFromSource.put(s, 0);
        for (int i = 0; i < graph.getSize(); ) {
            for (var edge : graph.getEdges()) {
                relax(edge.vertice1, edge.vertice2, graph);
            }
            i++;
        }
    }

    public void relax(GraphNode<T> u, GraphNode<T> v, Graph<T> graph) {
        int edgeWeight = graph.getEdge(u.getValue(), v.getValue()).get().getWeight();
        if (distanceFromSource.get(u) != Integer.MAX_VALUE
                && distanceFromSource.get(u) + edgeWeight < distanceFromSource.get(v)) {
            distanceFromSource.put(v, distanceFromSource.get(u) + edgeWeight);
            parents.put(v, u);
        }
    }

    public List<GraphNode<T>> getShortestPathWithBellman(
            GraphNode<T> s, GraphNode<T> t, Graph<T> graph) {
        bellmanFord(graph, s);
        List<GraphNode<T>> result = new ArrayList<>();
        GraphNode<T> pointer = t;
        getPath(s, t, result);
        if (result.get(0) != s && result.get(result.size() - 1) != t)
            throw new IllegalStateException("No path exists");
        return result;
    }

    public List<GraphNode<T>> getShortestPathWithDijskras(
            GraphNode<T> s, GraphNode<T> t, Graph<T> graph) {
        dijkstras(graph, s);
        List<GraphNode<T>> result = new ArrayList<>();
        GraphNode<T> pointer = t;
        getPath(s, t, result);
        if (result.get(0) != s && result.get(result.size() - 1) != t)
            throw new IllegalStateException("No path exists");
        return result;
    }

    private void getPath(GraphNode<T> s, GraphNode<T> t, List<GraphNode<T>> result) {
        if (t != s) {
            getPath(s, parents.get(t), result);
        }
        result.add(t);
    }

    public void topologicalSort(Graph<T> graph) {
        startingTime.clear();
        finishingTime.clear();
        visited.clear();

        int timer = 0;
        for (var node : graph.getGraphNodes()) {
            if (!visited.contains(node)) {
                dfsVisit(graph, node, timer);
            }
        }
    }

    private void dfsVisit(Graph<T> graph, GraphNode<T> node, int timer) {
        timer++;
        startingTime.put(node, timer);
        visited.add(node);
        for (var nei : graph.getNeighbours(node.getValue())) {
            if (!visited.contains(nei)) {
                dfsVisit(graph, nei, timer);
            }
        }
        timer++;
        finishingTime.put(node, timer);
        topologicalSortResult.add(node);
    }

    void dijkstras(Graph<T> graph, GraphNode<T> start) {
        parents.clear();
        distanceFromSource.clear();
        visited.clear();
        for (var node : graph.getGraphNodes()) {
            distanceFromSource.put(node, Integer.MAX_VALUE);
            parents.put(node, null);
        }
        distanceFromSource.put(start, 0);

        Comparator<GraphNode<T>> distanceFromSourceComp =
                (n1, n2) -> distanceFromSource.get(n1) - distanceFromSource.get(n2);
        PriorityQueue<GraphNode<T>> q = new PriorityQueue<>(distanceFromSourceComp);
        q.offer(start);
        while (!q.isEmpty()) {
            var node = q.poll();
            for (var nei : graph.getNeighbours(node.getValue())) {
                if (!visited.contains(nei)) {
                    relax(node, nei, graph);
                    q.offer(nei);
                }
            }
            visited.add(node);
        }
    }
}
