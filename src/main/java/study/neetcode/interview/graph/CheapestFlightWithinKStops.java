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
    private Map<Node, Integer> distanceFromSource;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        nodes = new HashMap<>();
        distanceFromSource = new HashMap<>();
        for (int i = 0; i < n; i++) {
            distanceFromSource.put(getOrCreate(i), Integer.MAX_VALUE);
        }
        Set<Edge> edges = new HashSet<>();
        for (int[] edge : flights) {
            Node u = getOrCreate(edge[0]);
            Node v = getOrCreate(edge[1]);
            Integer weight = edge[2];
            Edge e = new Edge(u, v, weight);
            u.adjList.add(e);
            edges.add(e);
        }

        distanceFromSource.put(getOrCreate(src), 0);
        for (int i = 0; i < k + 1; i++) {
            Map<Node, Integer> current = new HashMap<>(distanceFromSource);
            for (var edge : edges) {
                relax(edge, current);
            }
            distanceFromSource = current;
        }
        Integer minDistanceOfTarget = distanceFromSource.get(getOrCreate(dst));
        if (minDistanceOfTarget == Integer.MAX_VALUE) return -1;
        return minDistanceOfTarget;
    }

    private void relax(Edge edge, Map<Node, Integer> newMap) {
        Integer distanceOfU = distanceFromSource.get(edge.u);
        if (distanceOfU == Integer.MAX_VALUE) return;

        Integer distanceOfV = newMap.get(edge.v);
        int currentDistance = distanceOfV;
        int newDistance = distanceOfU + edge.weight;

        if (newDistance < currentDistance) {
            newMap.put(edge.v, newDistance);
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
