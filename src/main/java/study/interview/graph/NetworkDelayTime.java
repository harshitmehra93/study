package study.interview.graph;

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

    private Map<Node, Integer> distanceFromSource;

    public int networkDelayTime(int[][] times, int totalNodes, int startingNode) {
        distanceFromSource = new HashMap<>();
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 1; i <= totalNodes; i++) {
            var node = new Node(i);
            nodeMap.put(i, node);
            distanceFromSource.put(node, Integer.MAX_VALUE);
        }

        for (int[] time : times) {
            Node u = nodeMap.get(time[0]);
            Node v = nodeMap.get(time[1]);
            int w = time[2];
            Edge e = new Edge(u, v, w);
            u.adjList.add(e);
        }

        var source = nodeMap.get(startingNode);
        distanceFromSource.put(source, 0);

        PriorityQueue<Node> minHeap =
                new PriorityQueue<>(
                        (a, b) -> distanceFromSource.get(a) - distanceFromSource.get(b));
        minHeap.offer(source);
        HashSet<Node> visited = new HashSet<>();

        while (!minHeap.isEmpty()) {
            var node = minHeap.poll();

            if (!visited.add(node)) continue;

            for (Edge edge : node.adjList) {
                Node v = edge.v;
                if (!visited.contains(v)) {
                    if (relax(edge)) {
                        minHeap.offer(v);
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int distances : distanceFromSource.values()) {
            if (distances == Integer.MAX_VALUE) return -1;
            max = Math.max(max, distances);
        }
        return max;
    }

    private boolean relax(Edge edge) {
        Node u = edge.u;
        Node v = edge.v;
        int newDistance = distanceFromSource.get(u) + edge.weight;

        int oldDistance = distanceFromSource.get(v);
        if (oldDistance == Integer.MAX_VALUE || oldDistance > newDistance) {
            distanceFromSource.put(v, newDistance);
            return true;
        }
        return false;
    }

    public static class Node {
        Integer val;
        List<Edge> adjList = new ArrayList<>();

        Node(int val) {
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Node node) {
                return node.val.equals(val);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return val.hashCode();
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
        public boolean equals(Object o) {
            if (o instanceof Edge edge) {
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
