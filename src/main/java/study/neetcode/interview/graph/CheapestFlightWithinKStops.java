package study.neetcode.interview.graph;

import java.util.*;

/*
You are given n cities labeled:

0 to n - 1

You are given flights, where:

flights[i] = [from, to, price]

means there is a directed flight from from to to with cost price.

You are also given:

src
dst
k

Return the cheapest price from src to dst with at most k stops.

If there is no such route, return:

-1

Method signature:

public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k)

Example:

n = 4
flights = [
  [0, 1, 100],
  [1, 2, 100],
  [2, 0, 100],
  [1, 3, 600],
  [2, 3, 200]
]
src = 0
dst = 3
k = 1

Output:

700

Because:

0 -> 1 -> 3
cost = 100 + 600
stops = 1
 */
public class CheapestFlightWithinKStops {

    private Map<Integer, Node> nodes;
    private List<List<Edge>> paths;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        nodes = new HashMap<>();
        Set<Edge> edges = new HashSet<>();
        for (int[] edge : flights) {
            Node u = getOrCreate(edge[0]);
            Node v = getOrCreate(edge[1]);
            Integer weight = edge[2];
            Edge e = new Edge(u, v, weight);
            u.adjList.add(e);
            edges.add(e);
        }

        paths = new ArrayList<>();

        backtrack(nodes.get(src), nodes.get(dst), new ArrayList<>());

        int min = Integer.MAX_VALUE;
        for (var path : paths) {
            if (path.size() - 1 <= k) {
                min = Math.min(min, calculateCost(path));
            }
        }
        if (min == Integer.MAX_VALUE) return -1;
        return min;
    }

    private int calculateCost(List<Edge> path) {
        int cost = 0;
        for (var edge : path) {
            cost += edge.weight;
        }
        return cost;
    }

    private void backtrack(Node node, Node target, ArrayList<Edge> path) {
        System.out.println(node.value);
        if (node.equals(target)) {
            paths.add(new ArrayList<>(path));
            return;
        }

        for (var edge : node.adjList) {
            Node v = edge.v;
            if (!path.contains(edge)) {
                path.add(edge);
                backtrack(v, target, path);
                path.remove(edge);
            }
        }
    }

    private Node getOrCreate(int i) {
        if (!nodes.containsKey(i)) nodes.put(i, new Node(i));
        return nodes.get(i);
    }

    public static class Node {
        Integer value;
        List<Edge> adjList = new ArrayList<>();

        public Node(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node node) {
                return node.value.equals(this.value);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.value.hashCode();
        }
    }

    public static class Edge {
        Node u;
        Node v;
        Integer weight;

        Edge(Node u, Node v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Edge edge) {
                return edge.u.equals(this.u)
                        && edge.v.equals(this.v)
                        && edge.weight.equals(this.weight);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.u.hashCode() + this.v.hashCode() + this.weight.hashCode();
        }
    }
}
