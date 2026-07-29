# Advanced DSA Topic Queue

This is the live advanced-technique queue, not evidence of learning or independent ownership. Cold-check before teaching, install with a non-held representative, and never expose a held mixed candidate's technique during its live attempt. The dated rationale is preserved in `history/audits/G30-2026-07-29.md`.

| ID | Priority | Technique | State | Next action |
| --- | --- | --- | --- | --- |
| AT01 | High | Linear adjacency-exclusion DP with rolling compression | Cold check | Ask for the House Robber state and recurrence, then require an independent `O(1)`-space implementation. |
| AT02 | High | Checked signed decimal accumulation | Install | Install whitespace/sign/digit scanning and pre-operation overflow checks; use a second parser problem as transfer. |
| AT03 | High | Sign-sensitive one-dimensional DP | Install | Install simultaneous maximum/minimum ending states and the sign-flip invariant. |
| AT04 | High | Floyd cycle-entry recovery and functional-graph reduction | Install | Derive the entry phase on Linked List Cycle II, then use an array-as-successor problem for transfer. |
| AT05 | High | Bipartite/two-colour traversal | Install | Implement colouring across disconnected components and explain why an equal-colour edge is a conflict. |
| AT06 | Medium | In-place index placement / array-as-hash | Install | Install index ownership and the linear-swap argument with First Missing Positive. |
| AT07 | High | Prefix-state frequency counting | Install; hold G12 | Install exact-sum/remainder counting on a different representative, require independent recall, then re-screen held candidate `G12` for novelty and time fit. |
| AT08 | Medium | Counting all valid subarrays/windows | Install | Install valid-suffix counting and exact-`K` through at-most counts on one compact non-held example. |
| AT09 | Medium | Merge-sort cross-pair / inversion counting | Install after first ten | Use a generic inversion-count drill and prove how cross-half pairs are counted once. |
| AT10 | Medium | Eulerian trail / Hierholzer | Install after first ten | Use a tiny directed multigraph to cover degree conditions, edge consumption, and reverse postorder. |
| AT11 | Medium | Binary lifting / doubling | Install after first ten | Build a generic successor-jump table and derive queries from bit decomposition. |
| AT12 | Medium-low | Prefix function / KMP | Install after higher priorities | Install deterministic substring search and the viable-border fallback invariant. |
| AT13 | Low | Difference array / signed-event accumulation | Defer; micro-install | Add a short range-delta drill only when this technique becomes immediately useful. |
| AT14 | Low | Per-bit counts modulo `k` | Cold check | Ask for fixed-width modulo counting; treat the compact finite-state formula as optional. |
| AT15 | Low | Partition-based selection / Quickselect | Defer | Revisit only when partition invariants or selection optimization are a current goal. |
| AT16 | Low | Combinatorial counting and modular `nCr` | Defer | Revisit after repeated interview evidence or an explicit target requires it. |

## Repair, Do Not Reinstall

- Binary search and windows: equality/bound contracts, monotone feasibility, restored shrink invariants, and counting versus optimizing.
- Monotonic structures: pop-time ownership, correct stored value, deque-end discipline, stale-index expiry, and amortized proof.
- Linked lists and heap composition: uniform reversal/reconnection, cycle-safe rewiring, and the independently recovered `k`-head heap invariant.
- Weighted graphs and DSU: lazy Dijkstra state, minimax relaxation, root-only union, path compression/rank, and MST cut reasoning.
- Two pointers, `k`-sum, intervals, and greedy: duplicate ownership, overflow-safe sums, tie ordering, scalar overlap boundaries, and commitment proofs.
- DP, trees, and matrices: explicit state/return contracts, base cases, tie-aware aggregation, coordinates/path state, comparators, and membership bookkeeping.
- Prioritize learned Hard repairs for Trapping Rain Water, Largest Rectangle in Histogram, Sliding Window Maximum, and Merge K Sorted Lists; repair shipping-capacity search before Split Array Largest Sum and basic reversal before Reverse Nodes in k-Group.

## Update Rule

1. Unless an explicit request or new evidence overrides it, preserve the first-ten mixed block and choose the highest-priority eligible entry.
2. Record assistance honestly; `Installed` means a session occurred, while `Verified` requires independent evidence in the owning learned/core tracker.
3. Update only this disposition and its handoff. Keep `G12` held in `mixed_practice.md` until AT07 installation, independent recall, and the novelty/time re-screen are complete.
