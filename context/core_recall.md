# Core Skills Recall

Track recall for algorithms and data structures practiced under `src/main/java/study/coreskills`.

Use this file for core algorithm recall. Use `questions.md` for learned interview-problem recall.

`Latest Recall` uses the shared rules in `context/recall.md`. Detailed evidence lives in `context/history/core_recall_history.md`; keep `Current Target / Note` concise.

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

| # | Area | Latest Recall | Topic | Current Target / Note |
| ---: | --- | --- | --- | --- |
| 1 | Graph | L3 review 2026-07-19 | BFS traversal and unweighted shortest-path proof | Redo independently; load the matching history section after selection. |
| 2 | Graph | L1 ✅ 2026-07-24 | DFS recursive traversal | Latest recall passed; load detailed history only when needed. |
| 3 | Graph |  | DFS iterative traversal | Stack simulation, neighbor ordering, visited timing, difference from recursive DFS. |
| 4 | Graph |  | Edge classification | Tree, back, forward, and cross edges using DFS colors and discovery times. |
| 5 | Graph | L1 review 2026-07-21 | Topological sort | Redo independently; load the matching history section after selection. |
| 6 | Graph |  | Undirected cycle detection | DFS with parent exclusion; visited neighbor that is not parent means cycle. |
| 7 | Graph |  | Count simple paths in DAG | For a verified DAG and fixed destination, use base case `count(destination) = 1` and recurrence `count(u) = sum(count(v))` over outgoing neighbors, memoized by node. No path-set cycle guard is needed after the DAG precondition is established. |
| 8 | Shortest Path |  | Relaxation primitive | `dist[v] > dist[u] + w(u,v)` update, parent update, unreachable guard. |
| 9 | Shortest Path | L2 review 2026-06-26 | Dijkstra | Redo independently; load the matching history section after selection. |
| 10 | Shortest Path | L1 review 2026-07-25 | Bellman-Ford | Redo independently; load the matching history section after selection. |
| 11 | MST | L1 review 2026-07-21 | Kruskal | Redo independently; load the matching history section after selection. |
| 12 | MST | L1 review 2026-07-25 | Prim | Redo independently; load the matching history section after selection. |
| 13 | DSU | L1 review 2026-07-19 | Forest disjoint set | Redo independently; load the matching history section after selection. |
| 14 | DSU |  | Linked-list disjoint set | Representative pointer, union cost tradeoff, why forest DSU is preferred. |
| 15 | Heap | L1 review 2026-07-19 | Min-heap operations | Redo independently; load the matching history section after selection. |
| 16 | Hash Table | L1 review 2026-07-21 | Hash table model | Redo independently; load the matching history section after selection. |
| 17 | BST | L1 review 2026-07-26 | BST traversal/search/min/max | Redo independently; load the matching history section after selection. |
| 18 | BST |  | Successor / predecessor | Right-subtree min case; ancestor walk case. |
| 19 | BST | L1 review 2026-07-24 | BST insertion/deletion | Redo independently; load the matching history section after selection. |
| 20 | Trie | L2 review 2026-07-24 | Trie insert/search/prefix | Redo independently; load the matching history section after selection. |
| 21 | Trie |  | Trie keys/delete | DFS collection, delete pruning only when node has no children and is not terminal. |
| 22 | Dynamic Array | L1 review 2026-07-21 | Amortized resizing | Redo independently; load the matching history section after selection. |
| 23 | Linked List |  | Singly linked list operations | Head/tail handling, insertion/deletion edge cases, pointer mutation order. |
| 24 | Deque | L2 review 2026-07-25 | Doubly linked deque operations | Redo independently; load the matching history section after selection. |
| 25 | Combinatorial |  | Combinations generation | Backtracking choice order, start index, include/exclude or loop recursion. |
