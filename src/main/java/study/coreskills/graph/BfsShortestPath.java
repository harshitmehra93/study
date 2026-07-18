package study.coreskills.graph;

import java.util.*;

/*
Unweighted Shortest Paths with BFS

The graph is represented as an adjacency list. Vertices are numbered from
0 to graph.size() - 1.

Starting from source:

1. Compute the shortest number of edges from source to every reachable vertex.
2. Record one parent per discovered vertex so that a shortest path can be reconstructed.
3. Reconstruct the path from source to a requested target.

Example:

Input adjacency list:

0: [1, 2]
1: [0, 3]
2: [0, 3, 4]
3: [1, 2, 5]
4: [2, 5]
5: [3, 4]

source = 0
target = 5

Output:

distances = [0, 1, 1, 2, 2, 3]
parents   = [-1, 0, 0, 1, 2, 3]
path      = [0, 1, 3, 5]

The exact parent can depend on adjacency-list order when multiple shortest
paths exist. With the adjacency order above, vertex 3 is first discovered
from vertex 1.

Conventions:

- distance[source] is 0.
- parent[source] is -1.
- An unreachable vertex has distance -1 and parent -1.
- Reconstructing a path to an unreachable target returns an empty list.
- Reconstructing the path from source to itself returns a singleton list.

Target complexity: O(V + E) time and O(V) extra space.
*/
public class BfsShortestPath {

    public SearchResult search(List<List<Integer>> graph, int source) {
        int[] distances = new int[graph.size()];
        Arrays.fill(distances, -1);
        distances[source] = 0;

        int[] parents = new int[graph.size()];
        Arrays.fill(parents, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(source);
        HashSet<Integer> visited = new HashSet<>();
        visited.add(source);
        while (!q.isEmpty()) {
            var node = q.poll();

            for (int nei : graph.get(node)) {
                if (visited.add(nei)) {
                    q.offer(nei);
                    parents[nei] = node;
                    distances[nei] = distances[node] + 1;
                }
            }
        }
        return new SearchResult(distances, parents);
    }

    public List<Integer> reconstructPath(SearchResult result, int source, int target) {
        if (source == target) {
            List<Integer> list = new LinkedList<>();
            list.add(source);
            return list;
        }
        LinkedList<Integer> path = new LinkedList<>();
        int parent = target;
        while (parent != source) {
            if (parent == -1) break;
            path.add(0, parent);
            parent = result.parents[parent];
            ;
        }
        if (parent == source) {
            path.add(0, source);
            return path;
        }
        return new ArrayList<>();
    }

    public record SearchResult(int[] distances, int[] parents) {}
}
