# Advanced DSA Topic Audit And Queue

This file preserves bounded advanced-topic and missing-foundation audits for
future DSA learning sessions. It is not evidence that a technique has been
learned, recalled, or independently implemented.

## Ownership And Safety

This file owns:

* dated curriculum-gap audits
* candidate techniques for a future advanced-topic or targeted-installation
  session
* the questions that motivated each audit finding
* diagnostic questions to use after a topic has been selected
* audit disposition: cold-check, install, repair, or defer

It does not own:

* learned-problem status or recall results — use `questions.md`
* core-skill recall results — use `core_recall.md`
* mixed-attempt results or problem-level holding queues — use
  `mixed_practice.md`
* phase/readiness claims — use `roadmap.md`

An audit finding is a hypothesis about curriculum coverage, not a judgment
about Harshit's knowledge. Before teaching a topic marked `Cold check`, require
an independent model or implementation check. Never infer ownership from an
old source file alone.

Do not expose the title, expected technique, or solution direction of a
`Ready` mixed-practice candidate. If a `Ready` candidate primarily depends on
an uninstalled technique:

1. privately re-audit the overlap
2. move the problem to the Technique Installation Queue in
   `mixed_practice.md` when the conflict is confirmed
3. replenish the `Ready` bank
4. install the technique with a different representative
5. retain the held Google question as a later unseen transfer check

## Status Vocabulary

| State | Meaning |
| --- | --- |
| `Cold check` | Prior code or adjacent experience exists, but independent ownership is unverified. |
| `Install` | The audit found a reusable technique not adequately represented in the learned/core banks. |
| `Repair` | The family is installed; current evidence points to recall, proof, transfer, or implementation weakness. |
| `Hold` | Keep the motivating Google question unseen while installing the technique elsewhere. |
| `Defer` | Real or plausible technique, but currently too specialized or low-leverage to justify installation. |
| `Installed` | A learning/implementation session occurred; this state alone does not claim independent ownership. |
| `Verified` | The owning tracker records the required independent evidence and this queue needs no immediate follow-up. |

## Google Catalog Audit Snapshot

Audit performed from the authenticated LeetCode Premium Google catalog on
2026-07-29. Company tags are survey-derived and change over time, so reverify a
problem's current tag before using recency or frequency for selection.

Audit ID: `G30-2026-07-29`.

Treat this snapshot as immutable. A later catalog refresh should add a new
dated snapshot or decision-log entry rather than rewriting these observations.

Observed 30-day catalog:

* 193 questions: 78 Easy, 93 Medium, and 22 Hard
* 115 Medium/Hard questions
* 38 exact learned-bank matches
  * 7 had a current recall pass
  * 31 had a current `review`
* 2 exact optional learned-bank matches
* 17 exact matches in the then-current 30-day `Ready` inventory
* 58 Medium/Hard titles without an exact learned, optional, or `Ready` match

The 58 unmatched titles were not treated as 58 missing topics. Most classified
as one of:

* transfer from an installed family
* targeted repair of a review-level foundation
* implementation or simulation practice rather than a new algorithm
* a specialized contest problem that should not be a readiness gate
* a title-normalization false negative

The 22 observed 30-day Hards included:

* 4 exact learned matches, all currently `review`
* 2 optional learned-bank matches
* 1 exact `Ready` match
* 15 untracked titles

This distribution does not imply that all 22 Hards should be installed.

### Audit Conclusion

The broad syllabus is sufficient to begin Phase 4 Google-style mixed practice.
The audit did not support another block-by-block learning phase.

Readiness remained unproven at the snapshot:

* required learned coverage was 127/127, but 41 latest recalls passed and 86
  remained `review`
* active core coverage was 24/24, but 4 latest recalls passed and 20 remained
  `review`
* no fresh mixed attempts were logged
* both existing coding mocks were still uncalibrated

The dominant risk is independent retrieval and transfer. Missing techniques
should therefore be installed in bounded 30% repair/installation work while
the 70% fresh-practice track continues.

## Missing Or Underinstalled Foundations

These are the highest-value audit findings. The order is a default after the
precommitted first-ten mixed block; an explicit user request or new attempt
evidence may change it.

| ID | Priority | Technique | Audit finding and next action | Motivating questions | State |
| --- | --- | --- | --- | --- | --- |
| AT01 | High | Linear adjacency-exclusion DP and rolling compression | A correct top-down `HouseRobber` implementation and tests exist, but no learned-bank or recall-history row establishes ownership. Run a cold model plus `O(1)` rolling-state implementation. Promote only if independently owned. | [House Robber](https://leetcode.com/problems/house-robber/) | `Cold check` |
| AT02 | High | Checked signed decimal accumulation | The bank lacks whitespace/sign/digit scanning, stop conditions, and overflow checks performed before multiply/add. Use one focused parser/accumulator installation and the other problem as transfer. | [Reverse Integer](https://leetcode.com/problems/reverse-integer/), [String to Integer (`atoi`)](https://leetcode.com/problems/string-to-integer-atoi/) | `Install` |
| AT03 | High | Sign-sensitive one-dimensional DP | Maximum Subarray retains one scalar ending state; Maximum Product Cutting is an unrelated integer-partition problem. Install simultaneous maximum/minimum ending states and their sign-flip invariant. | [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) | `Install` |
| AT04 | High | Floyd cycle-entry recovery and functional-graph reduction | Linked List Cycle covers meeting detection but not the entry phase or reducing an array to a successor graph. Use Linked List Cycle II as the smaller representative, then retain the array problem as transfer. | Linked List Cycle II, [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) | `Install` |
| AT05 | High | Bipartite/two-colour traversal | The only repository attempt is commented out as needing revision, and there is no learned/core ownership row. Cover disconnected components, opposite-colour assignment, and conflict detection. | [Is Graph Bipartite?](https://leetcode.com/problems/is-graph-bipartite/) | `Install` |
| AT06 | Medium | In-place index placement / array-as-hash | Existing Missing Number evidence is XOR-specific. Install the invariant that places or marks each in-range value at its owned index while preserving linear total work. | [First Missing Positive](https://leetcode.com/problems/first-missing-positive/) | `Install` |
| AT07 | High | Prefix-state frequency counting | Exact-sum and remainder-class subarray counting are not represented in the learned/core banks. One `Ready` candidate appears to overlap this finding; keep its identity and direction protected, resolve the bank conflict privately, and use a different installation representative. | [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/) as later transfer | `Install` / protected |
| AT08 | Medium | Counting all valid subarrays/windows | Current windows optimize length or test existence; they do not establish the reusable “number of valid suffixes/endpoints” invariant or exact-`K` via at-most counts. Use one compact non-held representative. | [Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays/), [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/), [Number of Substrings Containing All Three Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/) | `Install` |

## Advanced Google-Coverage Queue

These are genuine reusable families absent from the current inventory. They
improve coverage of the full tagged catalog but are not prerequisites for
starting mixed practice.

| ID | Priority | Technique | Smallest installation | Preserve as transfer | State |
| --- | --- | --- | --- | --- | --- |
| AT09 | Medium | Merge-sort cross-pair / inversion counting | A generic inversion-count drill with a proof of cross-half counting. Do not require Fenwick or segment trees initially. | [Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/), [Reverse Pairs](https://leetcode.com/problems/reverse-pairs/) | `Install` after first-ten calibration |
| AT10 | Medium | Eulerian trail / Hierholzer | A tiny directed-multigraph drill covering degree conditions, consuming every edge once, and reverse postorder. | [Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/) | `Install` after first-ten calibration |
| AT11 | Medium | Binary lifting / doubling | A generic successor-jump table before a full composite problem. Cover table meaning, preprocessing, bit decomposition, and query bounds. | [Kth Ancestor of a Tree Node](https://leetcode.com/problems/kth-ancestor-of-a-tree-node/), [Path Existence Queries in a Graph II](https://leetcode.com/problems/path-existence-queries-in-a-graph-ii/) | `Install` after first-ten calibration |
| AT12 | Medium-low | Prefix function / KMP | Install ordinary deterministic substring search using a small representative. A separate rolling-hash installation is not required yet. | [Shortest Palindrome](https://leetcode.com/problems/shortest-palindrome/) | `Install` after higher-priority gaps |
| AT13 | Low | Difference array / signed-event accumulation | Use a short range-delta drill. Existing Meeting Rooms II supplies interval-overlap intuition, and a heap solution remains interview-valid for the motivating problem. | [Car Pooling](https://leetcode.com/problems/car-pooling/) | `Defer` / micro-install |
| AT14 | Low | Per-bit counts modulo `k` | Fixed-width scanning and XOR are installed; use one focused transfer drill. Treat the compact `ones/twos` finite-state formula as an optional follow-up rather than required magic. | [Single Number II](https://leetcode.com/problems/single-number-ii/) | `Cold check` |
| AT15 | Low | Partition-based selection / Quickselect | Heap/sort solutions already cover selection outcomes. Add only as an optimization follow-up when partition invariants are a current goal. | Kth Largest Element in an Array | `Defer` |
| AT16 | Low | Combinatorial counting and modular `nCr` | Current combinatorial coverage is generation-oriented. Stars-and-bars/parity/complement counting is real but lower leverage for the present Google plan. | [Count Valid Sequences](https://leetcode.com/problems/count-valid-sequences/) | `Defer` |

## Repair Queue — Do Not Reinstall

These Google mismatches are primarily transfer or reliability gaps in already
installed families.

| Area | Current repair target | Representative Google transfers |
| --- | --- | --- |
| Binary search | Equality-first control flow, lower/upper-bound contracts, minimum-feasible search, `[max,sum]` bounds, and feasibility monotonicity. | Split Array Largest Sum; Find First and Last Position; Minimum Time to Complete Trips; Search in Rotated Sorted Array II |
| Sliding window | Correct shrink condition, restored invariant, fixed versus variable windows, and counting versus optimizing. | Fruit Into Baskets; Minimum Size Subarray Sum; Max Consecutive Ones III |
| Monotonic stack/deque | Pop-time ownership, correct height/value use, deque-end discipline, stale-index expiry, and amortized proof. | Largest Rectangle in Histogram; Sliding Window Maximum |
| Linked lists | Uniform three-pointer reversal, cycle-safe rewiring, segment boundaries, and reconnecting predecessor/tail/suffix. | Reverse Linked List II; Reverse Nodes in k-Group |
| Heap/list composition | Independently recover the `k`-head heap invariant and Java skeleton. | Merge K Sorted Lists |
| Weighted graphs and DSU | Lazy Dijkstra state, minimax relaxation, root-only union, path compression/rank, and MST cut invariants. | Find the Safest Path; Minimum Score of a Path; Second Minimum Time |
| Two pointers / `k`-sum | Sorted anchors, duplicate ownership, overflow-safe sums, and pointer elimination proof. | 4Sum; 3Sum Closest |
| Intervals / greedy | Tie ordering, scalar overlap boundary, earliest-end commitment, and proof rather than heap-only simulation. | Remove Covered Intervals; interval variants |
| Ordinary DP transfer | State meaning, base cases, combinations-versus-permutations, tie-aware aggregation, and bottom-up/space-compressed translation. | Coin Change II; Decode Ways; Number of Paths With Max Score |
| Trees / matrices | Carrying coordinates/path state, postorder return contracts, comparators, and membership bookkeeping. | Vertical Order Traversal; Step-By-Step Directions; Find Leaves; Valid Sudoku |

Exact learned Google Hards needing repair before claiming Hard readiness:

* Trapping Rain Water
* Largest Rectangle in Histogram
* Sliding Window Maximum
* Merge K Sorted Lists

Foundation repairs with direct Hard transfer:

* Capacity To Ship Packages Within D Days before Split Array Largest Sum
* Reverse Linked List before Reverse Nodes in k-Group

## Implementation-Audit Follow-Ups

These are source-quality findings, not proof that the conceptual topic is
unknown:

* `LongestCommonSubsequence.lcs` is a legacy greedy implementation that is not
  a general LCS algorithm; `lcs2` contains the intended recurrence.
* `DistinctSubsequences.distinctSubsequences` initializes a memo but calls an
  unmemoized recursive helper.
* `LongestPalindromicSubsequence` uses the correct interval recurrence without
  memoization and is exponential.
* `PalindromicSubstrings` allocates a memo that its palindrome check never
  reads or writes.
* `ClimbingStairs` initializes instance memo storage only once, so reusing one
  object for a later larger `n` can index past the old array.

Treat these as implementation/complexity repairs when their family is selected.
Do not record a new learning result merely for cleaning old source.

## False Gaps And Tracker Hygiene

* `Minimum Path Sum` is already tracked as `Minimum Path Sum Grid`.
* `Lowest Common Ancestor of a Binary Tree` is already the learned LCA entry.
* The optional heap entry for Meeting Rooms II duplicates the installed
  interval entry.
* Vertical Order Traversal, Step-By-Step Directions, Find Leaves, Valid Sudoku,
  Rotate Array, Roman conversion, and Zigzag Conversion do not establish broad
  missing families by themselves.

## Deliberately Deferred Topics

Do not expand the roadmap solely because a tagged catalog contains:

* Fenwick trees or segment trees when static merge-counting solves the current
  motivating family
* SCC, bridges/articulation points, max flow, digit DP, bitmask DP, Manacher,
  advanced geometry, or advanced range-query combinations without repeated
  evidence
* specialized contest questions such as Sorted GCD Pair Queries, Maximize
  Active Section With Trade II, Unique XOR Triplets I, Super Pow, or bespoke
  sequence-counting variants

Reconsider a deferred family when:

* two or more strong current interview questions share it
* a fresh attempt records a verified `NEW_TECHNIQUE`
* an interviewer target explicitly calls for it
* it becomes a high-leverage follow-up to an already-owned foundation

## Future Diagnostic Questions

Use these only after selecting the corresponding advanced topic. They are not a
leading checklist for an H0 mixed attempt.

1. Can Harshit cold-derive House Robber's suffix state, prove the take/skip
   recurrence, and compress it to two prior values?
2. Can he accumulate a signed decimal value without relying on a wider type,
   detecting overflow before `value * 10 + digit`?
3. Why must Maximum Product Subarray retain both a maximum and a minimum product
   ending at the previous index?
4. Can he derive Floyd's cycle-entry phase from distances modulo the cycle
   length and explain why an array can be viewed as a functional graph?
5. Can he two-colour every disconnected component and identify exactly what an
   equal-colour edge proves?
6. For in-place index placement, what does each index own, and why can the total
   number of swaps remain linear?
7. What does a prefix-frequency map count before processing the current prefix,
   and how are negative remainder classes normalized?
8. When a window ending at `right` is valid, how many valid starts or suffixes
   does that fact contribute?
9. During merge-sort counting, which cross-half pairs are committed by one
   pointer movement, and why are none counted twice?
10. Why does Hierholzer append vertices on backtracking rather than when first
    traversing an edge?
11. What does `jump[p][v]` mean, and how does the binary representation of a
    query distance compose those jumps?
12. What is the semantic meaning of a KMP prefix-function value, and after a
    mismatch which border remains a viable candidate?

## Pickup And Update Procedure

When Harshit explicitly asks for `advanced topics`, `advanced DSA`, or a
specific entry from this queue:

1. load `student_profile.md`, `roadmap.md`, this file, `session_workflow.md`,
   and only the relevant ownership trackers
2. preserve the precommitted mixed schedule unless the explicit request
   overrides it
3. select the highest-priority eligible item, considering spacing, protected
   candidates, and recent evidence
4. for `Cold check`, assess before teaching
5. for `Install`, use a non-held canonical representative when practical
6. record meaningful assistance accurately
7. update this file's state and notes after the session
8. promote to `questions.md` or `core_recall.md` only when the owning protocol
   and actual evidence justify it
9. re-screen any structurally overlapping `Ready` candidates after an
   installation

Do not mark the whole queue complete because one representative was explained.
Each `Installed` transition requires its own session evidence, and each
`Verified` transition requires independent evidence in the owning tracker.

## Decision Log

Append material queue, priority, or disposition changes. Do not use this as an
attempt transcript.

| Date | Audit / topic | Decision | Evidence / handoff |
| --- | --- | --- | --- |
| 2026-07-30 | `G30-2026-07-29` | Preserved the authenticated Google catalog audit and created the bounded advanced-topic queue. | Source audit plus `questions.md`, `core_recall.md`, `mixed_practice.md`, and repository implementation inspection. |
