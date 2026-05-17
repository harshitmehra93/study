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

    private Map<Integer, Node> nodes;
    private Map<Node, Node> parentMap;

    public List<Integer> getRedundantConnection(List<List<Integer>> edges) {
        parentMap = new HashMap<>();
        nodes = new HashMap<>();
        for (var edge : edges) {
            createIfNotExist(edge.get(0));
            createIfNotExist(edge.get(1));
        }
        for (var edge : edges) {
            Node u = nodes.get(edge.get(0));
            Node v = nodes.get(edge.get(1));
            Node rootOfU = findRoot(u);
            Node rootOfV = findRoot(v);
            if (isSameSet(rootOfU, rootOfV)) {
                return List.of(u.value, v.value);
            }
            union(rootOfU, rootOfV);
        }
        return List.of();
    }

    private static boolean isSameSet(Node rootOfU, Node rootOfV) {
        return rootOfU.equals(rootOfV);
    }

    private void union(Node rootOfU, Node rootOfV) {
        parentMap.put(rootOfU, rootOfV);
    }

    Node findRoot(Node node) {
        Node parent = parentMap.get(node);
        if (parent == null) return node;

        Node root = findRoot(parent);

        parentMap.put(node, root);
        return root;
    }

    private void createIfNotExist(Integer nodeVal) {
        if (nodes.containsKey(nodeVal)) return;
        Node node = new Node(nodeVal);
        nodes.put(nodeVal, node);
        parentMap.put(node, null);
    }

    public static class Node {
        Integer value;

        Node(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node node) {
                return value.equals(node.value);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }
}
