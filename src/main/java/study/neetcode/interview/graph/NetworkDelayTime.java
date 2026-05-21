package study.neetcode.interview.graph;

import java.util.*;

/*
## Problem: Network Delay Time

You are given a directed, weighted graph with `n` nodes labeled from:

```text
1 to n
```

You are also given an array `times`, where each entry is:

```java
times[i] = [u, v, w]
```

This means there is a directed edge from node `u` to node `v`, and it takes `w` time for a signal to travel from `u` to `v`.

A signal is sent from a starting node `k`.

Return the minimum time it takes for **all `n` nodes** to receive the signal.

If it is impossible for all nodes to receive the signal, return:

```java
-1
```

---

## Example 1

```java
times = [
  [2, 1, 1],
  [2, 3, 1],
  [3, 4, 1]
]

n = 4
k = 2
```

Output:

```java
2
```

Explanation:

Signal starts at node `2`.

```text
2 -> 1 takes 1
2 -> 3 takes 1
2 -> 3 -> 4 takes 2
```

The last node receives the signal at time `2`, so answer is `2`.

---

## Example 2

```java
times = [
  [1, 2, 1]
]

n = 2
k = 1
```

Output:

```java
1
```

---

## Example 3

```java
times = [
  [1, 2, 1]
]

n = 2
k = 2
```

Output:

```java
-1
```

Because node `1` cannot be reached from node `2`.

---

## Method signature

```java
public int networkDelayTime(int[][] times, int n, int k)
```

 */
public class NetworkDelayTime {
    Map<Integer, Node> nodes;
    Map<Node, Integer> distanceFromSource;

    public int networkDelayTime(int[][] times, int totalNodes, int startingNode) {
        // build graph with totalnodes add weighted directed edges with times
        nodes = new HashMap<>();
        for (int i = 1; i <= totalNodes; i++) getOrCreate(i);

        for (int[] edge : times) {
            Node u = getOrCreate(edge[0]);
            Node v = getOrCreate(edge[1]);
            int weight = edge[2];
            DirectedEdge directedEdge = new DirectedEdge(u, v, weight);
            u.adjList.add(directedEdge);
        }

        // djikstras
        // init all distance from source as INF
        // set source distance as 0
        // create a priority queue Q with comp as the distance from source
        // for each Node which is not visited already, get all neighbours and relax the edge
        // during relaxation if there is a reduction in node distance then add the node to Q
        distanceFromSource = new HashMap<>();
        for (int i = 1; i <= totalNodes; i++)
            distanceFromSource.put(getOrCreate(i), Integer.MAX_VALUE);

        Node start = getOrCreate(startingNode);
        distanceFromSource.put(start, 0);
        Comparator<Node> comp = (n1, n2) -> distanceFromSource.get(n1) - distanceFromSource.get(n2);
        PriorityQueue<Node> q = new PriorityQueue<>(comp);
        q.add(start);
        HashSet<Node> visited = new HashSet<>();

        while (!q.isEmpty()) {
            var node = q.poll();
            if (visited.contains(node)) continue;

            visited.add(node);

            for (var edge : node.adjList) {
                if (relax(edge)) {
                    q.offer(edge.v);
                }
            }
        }

        // get the max of distance from dource map
        int max = Integer.MIN_VALUE;
        for (var node : nodes.values()) {
            Integer dist = distanceFromSource.get(node);
            if (dist == Integer.MAX_VALUE) return -1;
            max = Math.max(max, dist);
        }
        return max;
    }

    private boolean relax(DirectedEdge edge) {
        int distU = distanceFromSource.get(edge.u);
        int distV = distanceFromSource.get(edge.v);
        if (distU == Integer.MAX_VALUE) return false;
        int newDistance = distU + edge.weight;
        if (distV > newDistance) {
            distanceFromSource.put(edge.v, newDistance);
            return true;
        }
        return false;
    }

    private Node getOrCreate(int i) {
        if (!nodes.containsKey(i)) nodes.put(i, new Node(i));
        return nodes.get(i);
    }

    public static class Node {
        Integer value;
        List<DirectedEdge> adjList = new ArrayList<>();

        Node(Integer value) {
            this.value = value;
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

        DirectedEdge(Node u, Node v, Integer weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DirectedEdge edge) {
                return u.equals(edge.u) && v.equals(edge.v) || v.equals(edge.u) && u.equals(edge.v);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return u.hashCode() + v.hashCode() + weight.hashCode();
        }
    }
}
