package study.interview.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given n nodes labeled 0 to n - 1 and a list of undirected edges.

Return true if the edges form a valid tree.

A valid tree must:
1. Have no cycle
2. Be fully connected
Example:

n = 5
edges = [
  [0,1],
  [0,2],
  [0,3],
  [1,4]
]

Output:

true
 */
public class GraphValidTree {
    Map<Integer, Node> nodes;
    Map<Node, Node> parents;
    List<Edge> edgeList;

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        // create all nodes
        // init DS for each node with null
        nodes = new HashMap<>();
        parents = new HashMap<>();
        for (int i = 0; i < n; i++) {
            parents.put(getOrCreate(i), null);
        }
        // add edges as adjList
        edgeList = new ArrayList<>();
        for (int[] edge : edges) {
            Node u = getOrCreate(edge[0]);
            Node v = getOrCreate(edge[1]);
            Edge e = new Edge(u, v);
            edgeList.add(e);
        }

        // for each edge see if the nodes belong to the same DSU if yes then return false
        // otherwise do a union
        for (var edge : edgeList) {
            Node repU = getRepresentative(edge.u);
            Node repV = getRepresentative(edge.v);

            if (repU.equals(repV)) return false;

            union(repU, repV);
        }

        return true;
    }

    private void union(Node repU, Node repV) {
        parents.put(repV, repU);
    }

    private Node getRepresentative(Node node) {
        var parent = parents.get(node);
        if (parent == null) return node;

        var root = getRepresentative(parent);
        parents.put(node, root);
        return root;
    }

    private Node getOrCreate(int i) {
        if (!nodes.containsKey(i)) nodes.put(i, new Node(i));
        return nodes.get(i);
    }

    public static class Node {
        Integer value;

        Node(Integer value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Node node) return value.equals(node.value);
            return false;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }

    public static class Edge {
        Node u;
        Node v;

        Edge(Node u, Node v) {
            this.u = u;
            this.v = v;
        }
    }
}
