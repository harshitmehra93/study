# Core Skills Recall

Track recall for algorithms and data structures practiced under
`src/main/java/study/coreskills`; use `questions.md` for learned interview
problems.

Advanced-topic audit hypotheses and installation planning belong in
`advanced_topics.md`. Add a core topic here only after an actual session
establishes that it belongs in the active core rotation.

The Recall Table contains active topics; the separate reference table does not
participate in default selection. `Latest Recall` is the only recency field and
follows `context/recall.md`. Keep notes item-specific; detailed evidence lives
in `context/history/core_recall_history.md`.

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
| 1 | Graph | L3 review 2026-07-19 | BFS traversal and unweighted shortest-path proof |  |
| 2 | Graph | L1 ✅ 2026-07-24 | DFS recursive traversal |  |
| 3 | Graph | L2 review 2026-07-27 | DFS iterative traversal | Redo ordinary iterative preorder and visited/ordering tradeoffs. Exact frame-based finish simulation was newly taught, not recalled; defer as optional later learning. |
| 4 | Graph | L1 review 2026-07-27 | Edge classification | Classification model was recalled; redo one complete timestamped graph trace independently. |
| 5 | Graph | L1 review 2026-07-21 | Topological sort |  |
| 6 | Graph | L1 ✅ 2026-07-27 | Undirected cycle detection | Prefer the simpler parent-exclusion variant. |
| 7 | Graph | L1 review 2026-07-27 | Count simple paths in DAG | Redo the node-suffix recurrence, memo placement, and `O(V + E)` accounting independently. |
| 8 | Shortest Path | L1 ✅ 2026-07-28 | Relaxation primitive |  |
| 9 | Shortest Path | L2 review 2026-06-26 | Dijkstra |  |
| 10 | Shortest Path | L1 review 2026-07-25 | Bellman-Ford |  |
| 11 | MST | L1 review 2026-07-21 | Kruskal |  |
| 12 | MST | L1 review 2026-07-25 | Prim |  |
| 13 | DSU | L1 review 2026-07-29 | Forest disjoint set | Redo root-only union and `O(α(n))` amortized analysis independently. |
| 15 | Heap | L1 review 2026-07-19 | Min-heap operations |  |
| 16 | Hash Table | L1 review 2026-07-21 | Hash table model |  |
| 17 | BST | L1 review 2026-07-26 | BST traversal/search/min/max |  |
| 18 | BST | L1 review 2026-07-29 | Successor / predecessor | Redo both ancestor cases and iterative `O(1)` auxiliary-space analysis independently. |
| 19 | BST | L1 review 2026-07-24 | BST insertion/deletion |  |
| 20 | Trie | L2 review 2026-07-24 | Trie insert/search/prefix |  |
| 21 | Trie | L1 review 2026-07-29 | Trie keys/delete | Redo safe deletion and recursive collection/deletion complexity independently. |
| 22 | Dynamic Array | L1 review 2026-07-21 | Amortized resizing |  |
| 23 | Linked List | L1 ✅ 2026-07-29 | Singly linked list operations |  |
| 24 | Deque | L2 review 2026-07-25 | Doubly linked deque operations |  |
| 25 | Combinatorial | L1 review 2026-07-29 | Combinations generation | Redo `i + 1` ownership, zero/impossible cases, pruning, and output-sensitive complexity independently. |

## Reference-only topics

These are retained for conceptual context but are excluded from recall rotation.

| # | Area | Topic | Note |
| ---: | --- | --- | --- |
| 14 | DSU | Linked-list disjoint set | Historical contrast for representative-pointer and union-cost tradeoffs; forest DSU is the retained interview implementation. |
