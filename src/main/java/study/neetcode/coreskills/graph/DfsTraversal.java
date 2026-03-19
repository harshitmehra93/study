package study.neetcode.coreskills.graph;

import java.util.*;
import study.model.*;

public class DfsTraversal<T extends Comparable> {
    public List<GraphNode<T>> result = new ArrayList<>();
    public Map<GraphNode<T>, NodeColor> nodeColorMap = new HashMap<>();
    public Map<Edge<T>, EdgeType> classification = new HashMap<>();
    public Map<GraphNode<T>, GraphNode<T>> treeEdgeParents = new HashMap<>();
    public Map<GraphNode<T>, Integer> discoveryTime = new HashMap<>();
    public Map<GraphNode<T>, Integer> finishTime = new HashMap<>();
    public Map<GraphNode<T>, Integer> connectedComponents = new HashMap<>();
    private Integer counter;
    public LinkedList<GraphNode<T>> topologicalSortResult = new LinkedList<>();

    void dfsTraversal(Graph<T> graph) {
        init();
        initColorDiscoveryAndFinishTime(graph);

        int connectedComponentNumber = 0;
        for (var node : graph.getGraphNodes()) {
            if (nodeColorMap.get(node) == NodeColor.WHITE) {
                connectedComponentNumber++;
                dfsVisit(null, node, graph, connectedComponentNumber);
            }
        }
    }

    private void initColorDiscoveryAndFinishTime(Graph<T> graph) {
        for (var node : graph.getGraphNodes()) {
            nodeColorMap.put(node, NodeColor.WHITE);
            discoveryTime.put(node, 0);
            finishTime.put(node, 0);
        }
    }

    private void init() {
        result.clear();
        classification.clear();
        discoveryTime.clear();
        finishTime.clear();
        treeEdgeParents.clear();
        nodeColorMap.clear();
        counter = 0;
    }

    private void dfsVisit(
            GraphNode<T> parent,
            GraphNode<T> node,
            Graph<T> graph,
            Integer connectedComponentNumber) {
        Optional<Edge<T>> edge = Optional.empty();
        if (parent != null) {
            edge = graph.getEdge(parent.getValue(), node.getValue());
        }
        if (nodeColorMap.get(node).equals(NodeColor.WHITE)) {
            nodeColorMap.put(node, NodeColor.GREY);
            counter++;
            discoveryTime.put(node, counter);
            result.add(node);
            treeEdgeParents.put(node, parent);
            edge.ifPresent(e -> classification.put(e, EdgeType.TREE_EDGE));
            connectedComponents.put(node, connectedComponentNumber);

            for (var nei : graph.getNeighbours(node.getValue())) {
                dfsVisit(node, nei, graph, connectedComponentNumber);
            }
            counter++;
            finishTime.put(node, counter);
            nodeColorMap.put(node, NodeColor.BLACK);
        } else if (nodeColorMap.get(node).equals(NodeColor.GREY)) {
            edge.ifPresent(e -> classification.put(e, EdgeType.BACK_EDGE));
        } else if (nodeColorMap.get(node).equals(NodeColor.BLACK)) {
            if (edge.isPresent()) {
                if (discoveryTime.get(parent) < discoveryTime.get(node))
                    classification.put(edge.get(), EdgeType.FORWARD_EDGE);
                else classification.put(edge.get(), EdgeType.CROSS_EDGE);
            }
        }
    }

    public void printResult(List<GraphNode<T>> result) {
        for (var node : result) {
            System.out.println(
                    node.getValue() + "  " + discoveryTime.get(node) + "/" + finishTime.get(node));
        }
    }

    public void printResult() {
        printResult(result);
    }

    public void dfsTraversalIterative(Graph<T> graph) {
        init();
        initColorDiscoveryAndFinishTime(graph);

        for (var node : graph.getGraphNodes()) {
            if (nodeColorMap.get(node).equals(NodeColor.WHITE)) {
                dfsIterative(graph, node);
            }
        }
    }

    private void dfsIterative(Graph<T> graph, GraphNode<T> node) {
        Stack<GraphNode<T>> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()) {
            var item = stack.pop();
            if (nodeColorMap.get(item).equals(NodeColor.WHITE)) {
                nodeColorMap.put(item, NodeColor.GREY);
                result.add(item);
                List<GraphNode<T>> neighbours =
                        new ArrayList<>(graph.getNeighbours(item.getValue()));

                Collections.reverse(neighbours);
                for (var nei : neighbours) {
                    if (nodeColorMap.get(nei).equals(NodeColor.WHITE)) {
                        stack.push(nei);
                    }
                }
                nodeColorMap.put(item, NodeColor.BLACK);
            }
        }
    }

    public void topologicalSort(Graph<T> graph) {
        init();
        initColorDiscoveryAndFinishTime(graph);
        var visited = new HashSet<GraphNode<T>>();

        for (var node : graph.getGraphNodes()) {
            if (!visited.contains(node)) {
                topologicalSortVisit(graph, visited, node);
            }
        }
    }

    private void topologicalSortVisit(Graph<T> graph, HashSet<GraphNode<T>> visited,  GraphNode<T> node) {
            counter++;
            discoveryTime.put(node, counter);
            visited.add(node);
            for (var nei : graph.getNeighbours(node.getValue())) {
                if(!visited.contains(nei)) topologicalSortVisit(graph, visited, nei);
            }
            counter++;
            finishTime.put(node, counter);
            topologicalSortResult.addFirst(node);
    }
}

enum NodeColor {
    WHITE,
    GREY,
    BLACK;
}
