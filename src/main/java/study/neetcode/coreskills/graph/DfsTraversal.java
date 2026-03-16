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
        for (var node : graph.getGraphNodes()) {
            nodeColorMap.put(node, NodeColor.WHITE);
            discoveryTime.put(node, 0);
            finishTime.put(node, 0);
        }

        counter = 0;
        for (var node : graph.getGraphNodes()) {
            dfsVisit(null, node, graph);
        }
    }

    private void dfsVisit(GraphNode<T> parent, GraphNode<T> node, Graph<T> graph) {
        NodeColor nodeColor = nodeColorMap.get(node);
        Optional<Edge<T>> edge = Optional.empty();
        if (parent != null) {
            edge = graph.getEdge(parent.getValue(), node.getValue());
        }
        if (nodeColor.equals(NodeColor.WHITE)) {
            nodeColorMap.put(node, NodeColor.GREY);
            counter++;
            discoveryTime.put(node, counter);
            result.add(node);
            treeEdgeParents.put(node, parent);
            if (edge.isPresent()) {
                classification.put(edge.get(), EdgeType.TREE_EDGE);
            }
            for (var nei : graph.getNeighbours(node.getValue())) {
                dfsVisit(node, nei, graph);
            }
            counter++;
            finishTime.put(node, counter);
            nodeColorMap.put(node, NodeColor.BLACK);
        }
        if (nodeColor.equals(NodeColor.GREY)) {
            if (edge.isPresent()) {
                classification.put(edge.get(), EdgeType.BACK_EDGE);
            }
        }
        if (nodeColor.equals(NodeColor.BLACK)) {
            if (edge.isPresent()) {
                if (discoveryTime.get(parent) > finishTime.get(node))
                    classification.put(edge.get(), EdgeType.CROSS_EDGE);
                else classification.put(edge.get(), EdgeType.FORWARD_EDGE);
            }
        }
    }

    public void printResult() {
        for (var node : result) {
            System.out.println(
                    node.getValue() + "  " + discoveryTime.get(node) + "/" + finishTime.get(node));
        }
    }
}

enum NodeColor {
    WHITE,
    GREY,
    BLACK;
}
