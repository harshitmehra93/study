# Advanced DSA Topic Queue

This queue stores disposition, not learning evidence. Cold-check before
teaching, using a representative absent from protected mixed/mock pools; if one
is exposed from the mixed pool, mark it `Excluded — advanced exposure` there
without an attempt row. The dated rationale is in
`history/audits/G30-2026-07-29.md`.

| ID | Priority | Technique | State | Next action |
| --- | --- | --- | --- | --- |
| AT01 | High | Linear adjacency-exclusion DP with rolling compression | Cold check | Ask for the House Robber state and recurrence, then require an independent `O(1)`-space implementation. |
| AT02 | High | Checked signed decimal accumulation | Install | Install whitespace/sign/digit scanning and pre-operation overflow checks; use a second parser problem as transfer. |
| AT03 | High | Sign-sensitive one-dimensional DP | Install | Install simultaneous maximum/minimum ending states and the sign-flip invariant. |
| AT04 | High | Floyd cycle-entry recovery and functional-graph reduction | Install | Derive the entry phase on Linked List Cycle II, then use an array-as-successor problem for transfer. |
| AT05 | High | Bipartite/two-colour traversal | Install | Implement colouring across disconnected components and explain why an equal-colour edge is a conflict. |
| AT06 | Medium | In-place index placement / array-as-hash | Install | Install index ownership and the linear-swap argument with First Missing Positive. |
| AT07 | High | Prefix-state frequency counting | Installed | Independently recall the model and implementation on a different pool-safe representative; `G12` is excluded after direct exposure. |
| AT08 | Medium | Counting all valid subarrays/windows | Install | Install valid-suffix counting and exact-`K` through at-most counts on one compact pool-safe example. |
| AT09 | Medium | Merge-sort cross-pair / inversion counting | Install after first ten | Use a generic inversion-count drill and prove how cross-half pairs are counted once. |
| AT10 | Medium | Eulerian trail / Hierholzer | Install after first ten | Use a tiny directed multigraph to cover degree conditions, edge consumption, and reverse postorder. |
| AT11 | Medium | Binary lifting / doubling | Install after first ten | Build a generic successor-jump table and derive queries from bit decomposition. |
| AT12 | Medium-low | Prefix function / KMP | Install after higher priorities | Install deterministic substring search and the viable-border fallback invariant. |
| AT13 | Low | Difference array / signed-event accumulation | Defer; micro-install | Add a short range-delta drill only when this technique becomes immediately useful. |
| AT14 | Low | Per-bit counts modulo `k` | Cold check | Ask for fixed-width modulo counting; treat the compact finite-state formula as optional. |
| AT15 | Low | Partition-based selection / Quickselect | Defer | Revisit only when partition invariants or selection optimization are a current goal. |
| AT16 | Low | Combinatorial counting and modular `nCr` | Defer | Revisit after repeated interview evidence or an explicit target requires it. |
| AT17 | High | Fenwick tree with coordinate-compressed order statistics | Installed | Guided LeetCode 307 and 315 transfers are complete, and the guided contest 4013 transfer is verified. Independently recover the frequency/compression invariant after spacing. |
| AT18 | Medium-low | Sliding-window mode with dynamically ordered frequencies | Retained — advanced recall candidate | After spacing, independently recover the ordered `(frequency, reverse value)` invariant, window updates, zero-frequency cleanup, and the Java performance tradeoff against a coordinate-compressed segment tree. |
| AT19 | Medium-low | Manacher palindrome-radius preprocessing | Installed | Apply the transformed-string implementation to CT005 3327 with iterative postorder intervals; after spacing, independently recover the exclusive-boundary invariant, mirror clipping, and exact interval-query mapping. |
| AT20 | Medium | Tree Euler tour / DFS flattening for subtree intervals | Promoted to core recall #29 | Recall and evidence are owned by `core_recall.md`; do not select or score this as a separate advanced attempt. |
| AT21 | Medium | Prefix/suffix exclusion aggregation for removing one element | Installed — contest exposure | After spacing, independently recover the associative-prefix/suffix model, the operation identities, and the `combine(prefix[i-1], suffix[i+1])` boundary cases on a different representative. Evidence remains in Contest Practice CT006. |

## Update Rule

Choose the highest-priority eligible entry without disturbing the first-ten
mixed block. `Installed` means a session occurred; `Verified` requires
independent evidence in the learned/core owner. Update only `State` and `Next
action`; mixed candidate state remains in `context/mixed_practice.md`.
