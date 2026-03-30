package study.neetcode.coreskills.graph;

import java.util.*;
import study.model.*;
import study.neetcode.coreskills.sets.forest.ForestDisjointSets;

public class GraphTraversal<T extends Comparable> {
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

    // Need revision of Bipartite
    //    boolean isBipartiteGraph(Graph<T> graph, GraphNode<T> start){
    //        init();
    //        initColorDiscoveryAndFinishTime(graph);
    //
    //        Queue<GraphNode<T>> q = new ArrayDeque<>();
    //        q.offer(start);
    //        counter++;
    //        discoveryTime.put(start,counter);
    //        nodeColorMap.put(start,NodeColor.GREY);
    //        while(!q.isEmpty()){
    //            var node = q.poll();
    //            for(var nei : graph.getNeighbours(node.getValue())){
    //                if(nodeColorMap.get(nei)==NodeColor.WHITE){
    //                    q.offer(nei);
    //                    counter++;
    //                    discoveryTime.put(nei,counter);
    //                    nodeColorMap.put(nei,NodeColor.GREY);
    //                }
    //            }
    //            nodeColorMap.put(node,NodeColor.BLACK);
    //        }
    //
    //    }

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

    private void topologicalSortVisit(
            Graph<T> graph, HashSet<GraphNode<T>> visited, GraphNode<T> node) {
        counter++;
        discoveryTime.put(node, counter);
        visited.add(node);
        for (var nei : graph.getNeighbours(node.getValue())) {
            if (!visited.contains(nei)) topologicalSortVisit(graph, visited, nei);
        }
        counter++;
        finishTime.put(node, counter);
        topologicalSortResult.addFirst(node);
    }

    public int countSimplePaths(Graph<T> graph, GraphNode<T> start, GraphNode<T> end) {
        return countSimplePath(graph, start, end, new HashMap<>(), new HashSet<>());
    }

    // assuming no back edges
    public int countSimplePath(
            Graph<T> graph,
            GraphNode<T> start,
            GraphNode<T> end,
            Map<GraphNode<T>, Integer> memo,
            Set<GraphNode<T>> path) {
        System.out.println("visiting " + start.getValue());
        if (path.contains(start)) return 0;
        if (memo.containsKey(start)) {
            System.out.println("returning cached node " + start.getValue() + " " + memo.get(start));
            return memo.get(start);
        }
        if (start.equals(end)) {
            return 1;
        }
        path.add(start);
        int count = 0;
        for (var node : graph.getNeighbours(start.getValue())) {
            System.out.println("exploring neighbours of " + start.getValue());
            count += countSimplePath(graph, node, end, memo, path);
        }
        memo.put(start, count);
        path.remove(start);
        System.out.println("count of " + start.getValue() + " is " + count);
        return count;
    }

    public boolean undirectedGraphHasCycles(UndirectedGraph<T> graph) {
        boolean containsBackEdge = false;
        HashSet<GraphNode<T>> visited = new HashSet<>();
        for (var node : graph.getGraphNodes()) {
            if (visited.add(node)) containsBackEdge = dfsVisitHasCycle(graph, node, null, visited);
            if (containsBackEdge) break;
        }
        return containsBackEdge;
    }

    private boolean dfsVisitHasCycle(
            UndirectedGraph<T> graph,
            GraphNode<T> node,
            GraphNode<T> parent,
            Set<GraphNode<T>> visited) {
        visited.add(node);

        boolean hasCycles = false;

        for (var nei : graph.getNeighbours(node.getValue())) {
            if (nei.equals(parent)) continue;

            if (visited.add(nei)) {
                hasCycles = dfsVisitHasCycle(graph, nei, node, visited);
                if (hasCycles) return true;
            } else return true;
        }

        return hasCycles;
    }

    public ForestDisjointSets<GraphNode<T>> computeConnectedComponents(Graph<T> graph) {
        ForestDisjointSets<GraphNode<T>> disjointSets = new ForestDisjointSets<>();
        for (var node : graph.getGraphNodes()) disjointSets.makeSet(node);
        for (var edge : graph.getEdges()) {
            var aRepresentative = disjointSets.findSet(edge.vertice1).get();
            var bRepresentative = disjointSets.findSet(edge.vertice2).get();
            if (!aRepresentative.equals(bRepresentative)) {
                disjointSets.union(aRepresentative, bRepresentative);
            }
        }
        return disjointSets;
    }
}

enum NodeColor {
    WHITE,
    GREY,
    BLACK;
}
