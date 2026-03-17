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
    private Integer counter;

    void dfsTraversal(Graph<T> graph) {
        init();
        initColorDiscoveryAndFinishTime(graph);

        for (var node : graph.getGraphNodes()) {
            if (nodeColorMap.get(node) == NodeColor.WHITE) {
                dfsVisit(null, node, graph);
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

    private void dfsVisit(GraphNode<T> parent, GraphNode<T> node, Graph<T> graph) {
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

            for (var nei : graph.getNeighbours(node.getValue())) {
                dfsVisit(node, nei, graph);
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

    public void printResult() {
        for (var node : result) {
            System.out.println(
                    node.getValue() + "  " + discoveryTime.get(node) + "/" + finishTime.get(node));
        }
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
                var neighbours = getNeighboursInReverse(graph, item);
                for (var nei : neighbours) {
                    if (nodeColorMap.get(nei).equals(NodeColor.WHITE)) {
                        stack.push(nei);
                    }
                }
                nodeColorMap.put(item, NodeColor.BLACK);
            }
        }
    }

    private boolean allNeighboursAreBlackOrGreyOrThereAreNoNeighbours(
            Graph<T> graph, GraphNode<T> item) {
        Set<GraphNode<T>> neighbours = graph.getNeighbours(item.getValue());
        if (neighbours.size() == 0) return true;
        if (allNeighboursAreBlack(neighbours) || allNeighboursAreGrey(neighbours)) return true;
        return false;
    }

    private boolean allNeighboursAreBlack(Set<GraphNode<T>> neighbours) {
        return neighbours.stream().allMatch(nei -> nodeColorMap.get(nei).equals(NodeColor.BLACK));
    }

    private boolean allNeighboursAreGrey(Set<GraphNode<T>> neighbours) {
        return neighbours.stream().allMatch(nei -> nodeColorMap.get(nei).equals(NodeColor.GREY));
    }

    private static <T extends Comparable> List<GraphNode<T>> getNeighboursInReverse(
            Graph<T> graph, GraphNode<T> item) {
        return graph.getNeighbours(item.getValue()).stream()
                .sorted(Comparator.reverseOrder())
                .toList();
    }
}

enum NodeColor {
    WHITE,
    GREY,
    BLACK;
}
