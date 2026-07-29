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
| 3 | Graph | L2 review 2026-07-27 | DFS iterative traversal | Redo ordinary iterative preorder and visited/ordering tradeoffs. Exact frame-based finish simulation was newly taught, not recalled; defer as optional later learning. |
| 4 | Graph | L1 review 2026-07-27 | Edge classification | Classification model was recalled; redo one complete timestamped graph trace independently. |
| 5 | Graph | L1 review 2026-07-21 | Topological sort | Redo independently; load the matching history section after selection. |
| 6 | Graph | L1 ✅ 2026-07-27 | Undirected cycle detection | Latest recall passed using shared undirected-edge identity; parent exclusion is the simpler standard variant. |
| 7 | Graph | L1 review 2026-07-27 | Count simple paths in DAG | Redo the node-suffix recurrence, memo placement, and `O(V + E)` accounting independently. |
| 8 | Shortest Path | L1 ✅ 2026-07-28 | Relaxation primitive | Latest recall passed; update, parent, unreachable guard, tentative-distance invariant, and complexity were sound. |
| 9 | Shortest Path | L2 review 2026-06-26 | Dijkstra | Redo independently; load the matching history section after selection. |
| 10 | Shortest Path | L1 review 2026-07-25 | Bellman-Ford | Redo independently; load the matching history section after selection. |
| 11 | MST | L1 review 2026-07-21 | Kruskal | Redo independently; load the matching history section after selection. |
| 12 | MST | L1 review 2026-07-25 | Prim | Redo independently; load the matching history section after selection. |
| 13 | DSU | L1 review 2026-07-29 | Forest disjoint set | Redo root-only union and `O(α(n))` amortized analysis independently. |
| 15 | Heap | L1 review 2026-07-19 | Min-heap operations | Redo independently; load the matching history section after selection. |
| 16 | Hash Table | L1 review 2026-07-21 | Hash table model | Redo independently; load the matching history section after selection. |
| 17 | BST | L1 review 2026-07-26 | BST traversal/search/min/max | Redo independently; load the matching history section after selection. |
| 18 | BST | L1 review 2026-07-29 | Successor / predecessor | Redo both ancestor cases and iterative `O(1)` auxiliary-space analysis independently. |
| 19 | BST | L1 review 2026-07-24 | BST insertion/deletion | Redo independently; load the matching history section after selection. |
| 20 | Trie | L2 review 2026-07-24 | Trie insert/search/prefix | Redo independently; load the matching history section after selection. |
| 21 | Trie | L1 review 2026-07-29 | Trie keys/delete | Redo safe deletion and recursive collection/deletion complexity independently. |
| 22 | Dynamic Array | L1 review 2026-07-21 | Amortized resizing | Redo independently; load the matching history section after selection. |
| 23 | Linked List | L1 ✅ 2026-07-29 | Singly linked list operations | First verified recall passed; endpoint mutations, deletion cases, and bounds were independently sound. |
| 24 | Deque | L2 review 2026-07-25 | Doubly linked deque operations | Redo independently; load the matching history section after selection. |
| 25 | Combinatorial | L1 review 2026-07-29 | Combinations generation | Redo `i + 1` ownership, zero/impossible cases, pruning, and output-sensitive complexity independently. |

## Reference-only topics

These are retained for conceptual context but are excluded from recall rotation.

| Area | Topic | Note |
| --- | --- | --- |
| DSU | Linked-list disjoint set | Historical contrast for representative-pointer and union-cost tradeoffs; forest DSU is the retained interview implementation. |
