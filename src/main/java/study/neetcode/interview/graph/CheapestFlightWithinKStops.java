package study.neetcode.interview.graph;

import java.util.*;

/*
You are given n cities labeled:

0 to n - 1

You are given flights, where:

flights[i] = [from, to, price]

means there is a directed flight from to with cost price.

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
    Map<Integer, Node> nodes;
    List<DirectedEdge> edges;
    Map<Node, Integer> distanceFromSource;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // build graph with directional edges
        // init a distance from source map, with INF for each node
        nodes = new HashMap<>();
        edges = new ArrayList<>();
        distanceFromSource = new HashMap<>();
        for (int i = 0; i < n; i++) {
            var node = getOrCreate(i);
            distanceFromSource.put(node, Integer.MAX_VALUE);
        }
        for (int[] edge : flights) {
            Node u = getOrCreate(edge[0]);
            Node v = getOrCreate(edge[1]);
            int weight = edge[2];
            var de = new DirectedEdge(u, v, weight);
            edges.add(de);
        }

        // bellman
        // set source distance as 0
        // relax all edges k+1 times
        var source = getOrCreate(src);
        distanceFromSource.put(source, 0);
        for (int i = 0; i < k + 1; i++) {
            Map<Node, Integer> copyMap = new HashMap<>(distanceFromSource);
            for (var edge : edges) {
                relax(edge, copyMap);
            }
            distanceFromSource = copyMap;
        }

        // return distance of dst, if its INF return -1
        int result = distanceFromSource.get(getOrCreate(dst));
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private void relax(DirectedEdge edge, Map<Node, Integer> copyMap) {
        int distU = distanceFromSource.get(edge.u);
        if (distU == Integer.MAX_VALUE) return;

        int distV = copyMap.get(edge.v);
        int newDistance = distU + edge.weight;
        if (newDistance < distV) {
            copyMap.put(edge.v, newDistance);
        }
    }

    private Node getOrCreate(int i) {
        if (!nodes.containsKey(i)) nodes.put(i, new Node(i));
        return nodes.get(i);
    }

    public static class Node {
        Integer value;

        Node(Integer i) {
            value = i;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node node) return node.value.equals(value);
            return false;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }

    public static class DirectedEdge {
        Node u;
        Node v;
        Integer weight;

        DirectedEdge(Node u, Node v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DirectedEdge edge) {
                return edge.u.equals(u) && edge.v.equals(v) && edge.weight.equals(weight);
            }
            return false;
        }
    }
}
