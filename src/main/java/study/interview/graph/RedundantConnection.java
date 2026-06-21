package study.interview.graph;

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
    Map<Integer, Node> nodes;
    Map<Node, Node> parents;

    public List<Integer> getRedundantConnection(List<List<Integer>> edges) {
        // build graph
        nodes = new HashMap<>();
        parents = new HashMap<>();
        for (var edge : edges) {
            Node u = getOrCreate(edge.get(0));
            Node v = getOrCreate(edge.get(1));
            parents.put(u, null);
            parents.put(v, null);
        }
        // take edge one at a time see if it points to same set then thats the answer
        for (var edge : edges) {
            Node u = getOrCreate(edge.get(0));
            Node v = getOrCreate(edge.get(1));
            Node repU = findSetRepresentative(u);
            Node repV = findSetRepresentative(v);
            if (repU.equals(repV)) {
                return List.of(u.value, v.value);
            }
            union(repU, repV);
        }
        return List.of();
    }

    private void union(Node repU, Node repV) {
        parents.put(repV, repU);
    }

    private Node findSetRepresentative(Node node) {
        Node parent = parents.get(node);
        if (parent == null) return node;
        var rep = findSetRepresentative(parent);
        parents.put(node, rep);
        return rep;
    }

    private Node getOrCreate(Integer integer) {
        if (!nodes.containsKey(integer)) nodes.put(integer, new Node(integer));
        return nodes.get(integer);
    }

    class Node {
        Integer value;

        Node(Integer value) {
            this.value = value;
        }
    }
}
