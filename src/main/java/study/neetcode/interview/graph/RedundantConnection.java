package study.neetcode.interview.graph;

import java.util.*;

/*
Problem: Redundant Connection

You are given an undirected graph that started as a tree with n nodes labeled from 1 to n.

Then one extra edge was added.

The graph is given as an array edges, where:

edges[i] = [a, b]

means there is an undirected edge between node a and node b.

Return the edge that can be removed so that the graph becomes a tree again.

If there are multiple answers, return the edge that appears last in the input.

Example 1
edges = [
  [1, 2],
  [1, 3],
  [2, 3]
]

Output:

[2, 3]
 */
public class RedundantConnection {

    private Map<Node, Node> nodeParentMap;

    public List<Integer> getRedundantConnection(List<List<Integer>> edges) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        List<UndirectedEdge> undirectedEdges = new ArrayList<>();
        for (var edge : edges) {
            Node u = getOrCreate(edge.get(0), nodes);
            Node v = getOrCreate(edge.get(1), nodes);
            UndirectedEdge undirectedEdge = new UndirectedEdge(u, v);
            undirectedEdges.add(undirectedEdge);
        }
        nodeParentMap = new HashMap<>();
        for (var node : nodes.values()) {
            nodeParentMap.put(node, null);
        }

        for (var undirectedEdge : undirectedEdges) {
            Node parentOfU = getParent(undirectedEdge.u);
            Node parentOfV = getParent(undirectedEdge.v);
            if (parentOfU == parentOfV) {
                return List.of(undirectedEdge.u.value, undirectedEdge.v.value);
            }
            nodeParentMap.put(parentOfV, parentOfU);
        }
        return List.of();
    }

    private Node getParent(Node node) {
        while (nodeParentMap.get(node) != null) node = nodeParentMap.get(node);
        return node;
    }

    private Node getOrCreate(Integer value, HashMap<Integer, Node> nodes) {
        if (nodes.containsKey(value)) return nodes.get(value);
        Node node = new Node(value);
        nodes.put(value, node);
        return node;
    }

    public static class Node {
        Integer value;

        Node(Integer value) {
            this.value = value;
        }
    }

    public static class UndirectedEdge {
        Node u;
        Node v;

        UndirectedEdge(Node u, Node v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof UndirectedEdge edge) {
                return edge.u == u && edge.v == v || edge.u == v && edge.v == u;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return u.value.hashCode() + v.value.hashCode();
        }
    }
}
