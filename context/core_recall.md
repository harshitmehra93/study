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
| 1 | Graph |  | BFS traversal / unweighted shortest path | Queue frontier, visited timing, parent map, distance levels, `O(V + E)`. |
| 2 | Graph |  | DFS recursive traversal | White/grey/black states, discovery/finish time, recursion stack, connected components, `O(V + E)`. |
| 3 | Graph |  | DFS iterative traversal | Stack simulation, neighbor ordering, visited timing, difference from recursive DFS. |
| 4 | Graph |  | Edge classification | Tree, back, forward, and cross edges using DFS colors and discovery times. |
| 5 | Graph |  | Topological sort | DAG requirement, DFS finish-time ordering, cycle implication via back edge. |
| 6 | Graph |  | Undirected cycle detection | DFS with parent exclusion; visited neighbor that is not parent means cycle. |
| 7 | Graph |  | Count simple paths in DAG | DFS + memoization, path set for cycle guard, recurrence over outgoing neighbors. |
| 8 | Shortest Path |  | Relaxation primitive | `dist[v] > dist[u] + w(u,v)` update, parent update, unreachable guard. |
| 9 | Shortest Path |  | BFS shortest path | Works for unweighted graphs; parent reconstruction; level-distance invariant. |
| 10 | Shortest Path |  | Dijkstra | Non-negative weights, min-priority frontier, settled shortest-distance invariant. |
| 11 | Shortest Path |  | Bellman-Ford | Repeated edge relaxation, negative-edge support, negative-cycle check concept. |
| 12 | MST |  | Kruskal | Sort edges by weight, add safe edges that connect different DSU components. |
| 13 | MST |  | Prim | Grow one tree from a start node using cheapest crossing edge / min-priority frontier. |
| 14 | DSU |  | Forest disjoint set | `makeSet`, `findSet`, `union`, parent pointers, rank/size idea, path compression. |
| 15 | DSU |  | Linked-list disjoint set | Representative pointer, union cost tradeoff, why forest DSU is preferred. |
| 16 | Heap |  | Min-heap operations | Shape invariant, heap-order invariant, sift-up/sift-down, push/pop complexity. |
| 17 | Hash Table |  | Hash table model | Bucket array, collision handling, load factor, resizing, average vs worst-case complexity. |
| 18 | BST |  | BST traversal/search/min/max | Ordering invariant, inorder sorted order, height-dependent complexity. |
| 19 | BST |  | Successor / predecessor | Right-subtree min case; ancestor walk case. |
| 20 | BST |  | BST insertion/deletion | Leaf, one-child, two-child deletion with successor/predecessor transplant. |
| 21 | Trie |  | Trie insert/search/prefix | Character path invariant, terminal word marker, prefix vs word distinction. |
| 22 | Trie |  | Trie keys/delete | DFS collection, delete pruning only when node has no children and is not terminal. |
| 23 | Dynamic Array |  | Amortized resizing | Capacity vs size, doubling, copy cost, why append is amortized `O(1)`. |
| 24 | Linked List |  | Singly linked list operations | Head/tail handling, insertion/deletion edge cases, pointer mutation order. |
| 25 | Deque |  | Doubly linked deque operations | Head/tail sentinels or null cases, add/remove at both ends, pointer rewiring. |
| 26 | Combinatorial |  | Combinations generation | Backtracking choice order, start index, include/exclude or loop recursion. |
