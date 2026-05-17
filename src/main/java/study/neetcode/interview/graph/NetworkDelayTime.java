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

    private HashMap<Integer, Node> nodes;
    Map<Node, Integer> distanceFromSource;

    public int networkDelayTime(int[][] times, int totalNodes, int startingNode) {
        nodes = new HashMap<>();
        distanceFromSource = new HashMap<>();
        for (int i = 1; i <= totalNodes; i++) {
            Node node = new Node(i);
            nodes.put(i, node);
            distanceFromSource.put(node, Integer.MAX_VALUE);
        }
        for (int[] edge : times) {
            Node u = nodes.get(edge[0]);
            Node v = nodes.get(edge[1]);
            int w = edge[2];
            DirectedEdge directedEdge = new DirectedEdge(u, v, w);
            u.adjList.add(directedEdge);
        }

        PriorityQueue<Node> q =
                new PriorityQueue<>(
                        (n1, n2) -> distanceFromSource.get(n1) - distanceFromSource.get(n2));
        Node source = nodes.get(startingNode);
        distanceFromSource.put(source, 0);
        q.offer(source);
        HashSet<Node> visited = new HashSet<>();
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (visited.contains(node)) continue;

            visited.add(node);

            for (var edge : node.adjList) {
                Node v = edge.v;

                int weight = edge.weight;
                Integer currentDistance = distanceFromSource.get(v);
                int newDistance = distanceFromSource.get(node) + weight;

                if (currentDistance == Integer.MAX_VALUE || currentDistance > newDistance) {
                    distanceFromSource.put(v, newDistance);
                    q.offer(v);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (var val : distanceFromSource.values()) {
            if (val == Integer.MAX_VALUE) return -1;
            max = Math.max(max, val);
        }
        return max;
    }

    public static class DirectedEdge {
        Node u;
        Node v;
        int weight;

        DirectedEdge(Node u, Node v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public static class Node implements Comparable<Node> {
        Integer value;
        List<DirectedEdge> adjList = new ArrayList<>();

        Node(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Node node) {
            return value.compareTo(node.value);
        }
    }
}
