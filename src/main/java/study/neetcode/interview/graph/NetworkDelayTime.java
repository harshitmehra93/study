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

    private Map<Integer, Node> nodes;
    private HashMap<Node, Integer> distanceFromSource;

    public int networkDelayTime(int[][] times, int totalNodes, int startingNode) {
        nodes = new HashMap<>();
        distanceFromSource = new HashMap<>();
        for (int i = 1; i <= totalNodes; i++) {
            getOrCreate(i);
        }
        for (int[] edge : times) {
            Node u = getOrCreate(edge[0]);
            Node v = getOrCreate(edge[1]);
            Integer w = edge[2];
            Edge e = new Edge(u, v, w);
            u.adjList.add(e);
        }

        Comparator<Node> comp = Comparator.comparingInt(distanceFromSource::get);
        PriorityQueue<Node> q = new PriorityQueue<>(comp);
        Node source = nodes.get(startingNode);
        distanceFromSource.put(source, 0);
        q.add(source);
        Set<Node> visited = new HashSet<>();
        while (!q.isEmpty()) {
            var node = q.poll();

            if (visited.contains(node)) continue;
            visited.add(node);

            for (Edge edge : node.adjList) {
                if (relaxEdge(edge)) q.offer(edge.v);
            }
        }

        int max = 0;
        for (int value : distanceFromSource.values()) {
            if (value == Integer.MAX_VALUE) return -1;
            max = Math.max(max, value);
        }
        return max;
    }

    private boolean relaxEdge(Edge edge) {
        Integer distanceOfU = distanceFromSource.get(edge.u);
        Integer oldDistance = distanceFromSource.get(edge.v);
        int newDistance = distanceOfU + edge.weight;
        if (newDistance < oldDistance) {
            distanceFromSource.put(edge.v, newDistance);
            return true;
        }
        return false;
    }

    private Node getOrCreate(int value) {
        if (nodes.containsKey(value)) {
            return nodes.get(value);
        }
        Node node = new Node(value);
        nodes.put(value, node);
        distanceFromSource.put(node, Integer.MAX_VALUE);
        return node;
    }

    static class Node {
        Integer value;
        List<Edge> adjList = new ArrayList<>();

        Node(Integer value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node node) {
                return node.value.equals(value);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }

    static class Edge {
        Node u;
        Node v;
        Integer weight;

        Edge(Node u, Node v, Integer weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Edge edge) {
                return edge.u.equals(u) && edge.v.equals(v) && edge.weight.equals(weight);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return u.hashCode() + v.hashCode() + weight.hashCode();
        }
    }
}
