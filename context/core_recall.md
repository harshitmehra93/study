# Core Skills Recall

Track recall for algorithms and data structures practiced under `src/main/java/study/coreskills`.

Use this file for core algorithm recall. Use `questions.md` for LeetCode/problem recall.

Status format:

- Empty = not recalled yet.
- `L1 ✅ yyyy-mm-dd` = explained model, invariant, edge cases, and complexity.
- `L2 ✅ yyyy-mm-dd` = wrote code skeleton.
- `L3 ✅ yyyy-mm-dd` = full implementation.
- `Lx review yyyy-mm-dd` = attempted but needs another recall.

## Priority

Prioritize algorithms that show up in interviews as explainable building blocks:

1. Graph traversal and graph invariants.
2. Shortest paths and MST.
3. Disjoint set union.
4. Heap and priority queue mechanics.
5. BST operations.
6. Trie operations.
7. Hash table collision/resizing model.
8. Dynamic array amortized resizing.
9. Linked list and deque pointer mutation.

## Recall Table

| # | Area | Recall Status | Topic | What To Recall |
| ---: | --- | --- | --- | --- |
| 1 | Graph | L3 review 2026-07-19 | BFS traversal / unweighted shortest path | Correct full implementation using mark-on-enqueue, FIFO traversal, distance assignment, parent tracking, and backward path reconstruction. Recall initially marked visited on dequeue, described the frontier as an MST level, and gave `O(V)` time; corrected to the invariant that the queue holds vertices in nondecreasing shortest distance and to `O(V + E)` time with `O(V)` extra space. The first-discovery shortest-path proof required guidance, so redo that explanation independently. |
| 2 | Graph |  | DFS recursive traversal | White/grey/black states, discovery/finish time, recursion stack, connected components, `O(V + E)`. |
| 3 | Graph |  | DFS iterative traversal | Stack simulation, neighbor ordering, visited timing, difference from recursive DFS. |
| 4 | Graph |  | Edge classification | Tree, back, forward, and cross edges using DFS colors and discovery times. |
| 5 | Graph | L1 review 2026-07-21 | Topological sort | Correctly identified DFS topological sorting and cycle detection through an edge to a GREY vertex. Recall needed correction on traversal across a DFS forest: there is no special starting vertex, all roots share one color array and result list, and vertices must be ordered by decreasing—not increasing—finish time. Appending each vertex on finish and reversing once is equivalent. For every DAG edge `u -> v`, `finish(u) > finish(v)`, including when `v` was completed by an earlier DFS root. O(V + E) time; O(V) auxiliary space for colors, result, and worst-case recursion stack, or O(V + E) total when adjacency storage is included. |
| 6 | Graph |  | Undirected cycle detection | DFS with parent exclusion; visited neighbor that is not parent means cycle. |
| 7 | Graph |  | Count simple paths in DAG | DFS + memoization, path set for cycle guard, recurrence over outgoing neighbors. |
| 8 | Shortest Path |  | Relaxation primitive | `dist[v] > dist[u] + w(u,v)` update, parent update, unreachable guard. |
| 9 | Shortest Path |  | BFS shortest path | Works for unweighted graphs; parent reconstruction; level-distance invariant. |
| 10 | Shortest Path | L2 review 2026-06-26 | Dijkstra | Non-negative weights, min-priority frontier, settled shortest-distance invariant. Review exposed incorrect muscle memory: using a `PriorityQueue<Node>` whose comparator reads mutable external distances. Corrected toward Java lazy Dijkstra with immutable `(node, distance)` states, successful-relaxation reinsertion, and stale-entry skipping. Still rehearse textbook `decreaseKey` vs Java lazy variants until the distinction is automatic. |
| 11 | Shortest Path |  | Bellman-Ford | Repeated edge relaxation, negative-edge support, negative-cycle check concept. |
| 12 | MST | L1 review 2026-07-21 | Kruskal | Independently identified Kruskal's algorithm: process edges by increasing weight and accept an edge only when its endpoints are in different DSU components. Correctly selected `(0,1)`, `(2,3)`, and `(1,2)` for total weight 4. Completion detection needed correction: a visited-vertex set can contain every vertex while the selected forest is still disconnected; success requires exactly `V - 1` accepted edges or one remaining DSU component. Cycle safety comes from joining disjoint components, while minimum-weight optimality follows from the cut/exchange argument. Heap processing costs O(E log E) because up to E removals each cost O(log E); DSU operations are amortized O(alpha(V)), giving O(E log E) total time and O(E + V) auxiliary storage with a heap and DSU. |
| 13 | MST |  | Prim | Grow one tree from a start node using cheapest crossing edge / min-priority frontier. |
| 14 | DSU | L1 review 2026-07-19 | Forest disjoint set | Correctly recalled the forest model, representative lookup, path compression, same-component no-op, and union-by-size direction. Initial trace attached node `2` beneath non-root argument `1`; corrected to the key invariant that union links representative roots only. With root-as-null convention, the final example has `1,2,3 → 0`, `4 → 3`, and `5` separate; `find(4)` compresses `4 → 0`. Complexity needed correction: `union` and `connected` include two finds. With union-by-size/rank plus path compression, operations are amortized O(α(n)) and storage is O(n). |
| 15 | DSU |  | Linked-list disjoint set | Representative pointer, union cost tradeoff, why forest DSU is preferred. |
| 16 | Heap | L1 review 2026-07-19 | Min-heap operations | Correctly explained and traced insert, sift-up, extract-min, and sift-down after guidance. Heap-order knowledge was solid, but recall initially omitted the complete-tree shape invariant, did not state that sift-down must choose the smaller child, treated `heap[0] == null` as the emptiness source of truth instead of logical size, and gave `O(n log n)` for bottom-up construction. Rehearse: complete tree filled left-to-right; parent `(i - 1) / 2`, children `2i + 1` and `2i + 2`; `O(log n)` insert/extract, `O(1)` peek and iterative auxiliary space, `O(n)` bottom-up heapify, and `O(n)` backing storage. |
| 17 | Hash Table | L1 review 2026-07-21 | Hash table model | Correctly recalled bucket indexing, separate chaining, collision-list search, full rehashing into a doubled array, and average O(1) versus worst-case O(n) operations. The substantive correction was resize accounting: load factor is `entryCount / bucketCount`, not the proportion of non-null buckets; a table with many entries in one bucket can therefore reach its threshold while most buckets remain null. Normalize a possibly negative hash into `[0, capacity)` (for example with `Math.floorMod`), and recompute bucket indexes during resize because the capacity used by the index function changes. |
| 18 | BST |  | BST traversal/search/min/max | Ordering invariant, inorder sorted order, height-dependent complexity. |
| 19 | BST |  | Successor / predecessor | Right-subtree min case; ancestor walk case. |
| 20 | BST |  | BST insertion/deletion | Leaf, one-child, two-child deletion with successor/predecessor transplant. |
| 21 | Trie |  | Trie insert/search/prefix | Character path invariant, terminal word marker, prefix vs word distinction. |
| 22 | Trie |  | Trie keys/delete | DFS collection, delete pruning only when node has no children and is not terminal. |
| 23 | Dynamic Array | L1 review 2026-07-21 | Amortized resizing | Correctly distinguished logical size from backing-array capacity and recalled O(1) indexed get/set and O(n) storage. Recall initially mixed in the hash-table 75% load-factor threshold and then confused the new append with an existing-element copy. Correct trace from capacity one: appending `10,20,30,40,50` triggers resize-copy counts `0,1,2,0,4`, ending at capacity eight with seven existing elements copied. Across n appends, geometric resize copies `1 + 2 + 4 + ...` total O(n); adding n ordinary writes remains O(n), so append is amortized O(1) even though a resizing append is O(n). |
| 24 | Linked List |  | Singly linked list operations | Head/tail handling, insertion/deletion edge cases, pointer mutation order. |
| 25 | Deque |  | Doubly linked deque operations | Head/tail sentinels or null cases, add/remove at both ends, pointer rewiring. |
| 26 | Combinatorial |  | Combinations generation | Backtracking choice order, start index, include/exclude or loop recursion. |
