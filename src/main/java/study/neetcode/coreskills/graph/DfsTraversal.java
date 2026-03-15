package study.neetcode.coreskills.graph;

import static java.util.Objects.isNull;

import java.util.*;
import study.model.*;

public class DfsTraversal<T extends Comparable> {
    public List<GraphNode<T>> result = new ArrayList<>();
    public Map<GraphNode<T>, NodeColor> nodeColorMap = new HashMap<>();
    public Map<Edge<T>, EdgeType> classification = new HashMap<>();
    public Map<GraphNode<T>, GraphNode<T>> treeEdgeParents = new HashMap<>();
    public Map<GraphNode<T>, Integer> discoveryTime = new HashMap<>();
    public Map<GraphNode<T>, Integer> finishTime = new HashMap<>();
    public Set<Edge<T>> edgesVisited = new HashSet<>();

    void dfsTraversal(Graph<T> graph) {
        for (var node : graph.getGraphNodes()) {
            nodeColorMap.put(node, NodeColor.WHITE);
            discoveryTime.put(node, 0);
            finishTime.put(node, 0);
        }
        Integer counter = 0;
        for (var node : graph.getGraphNodes()) {
            dfsVisit(null, node, graph, counter);
        }
    }

    private void dfsVisit(GraphNode<T> parent, GraphNode<T> node, Graph<T> graph, Integer counter) {
        System.out.println("visiting: " + node.getValue());
        var nodeColor = nodeColorMap.get(node);
        var edge = isNull(parent) ? null : graph.getEdge(parent.getValue(), node.getValue());
        if (nodeColor.equals(NodeColor.BLACK)) {
            System.out.println("reached a black node " + node);
        }
        if (nodeColor.equals(NodeColor.GREY)) {
            System.out.println("reached a grey node " + node);
            if (!isImmediateParent(parent, node)) {
                System.out.println(treeEdgeParents);
                System.out.println(
                        parent.getValue() + " is not immediate parent " + node.getValue());
                classification.put(edge.get(), EdgeType.BACK_EDGE);
            }
        }
        if (nodeColor.equals(NodeColor.WHITE)) {
            counter++;
            discoveryTime.put(node, counter);
            nodeColorMap.put(node, NodeColor.GREY);
            result.add(node);
            if (edge != null && edge.isPresent()) {
                classification.put(edge.get(), EdgeType.TREE_EDGE);
                treeEdgeParents.put(node, parent);
            }

            for (var nei : graph.getNeighbours(node.getValue())) {
                dfsVisit(node, nei, graph, counter);
            }
            nodeColorMap.put(node, NodeColor.BLACK);
            counter++;
            finishTime.put(node, counter);
        }
    }

    private boolean isImmediateParent(GraphNode<T> parent, GraphNode<T> node) {
        return (treeEdgeParents.containsKey(node) && treeEdgeParents.get(node).equals(parent))
                || (treeEdgeParents.containsKey(parent)
                        && treeEdgeParents.get(parent).equals(node));
    }
}

enum NodeColor {
    WHITE,
    GREY,
    BLACK;
}
