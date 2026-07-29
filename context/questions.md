# Questions

`questions.md` is the source of truth for current learned-problem ownership, latest recall, and selection data.

This is a permanent learned-problem bank. Table order is organizational; session selection follows the active protocol.

`Learning Status` records current ownership of the intended interview solution:

- Empty = not attempted.
- `🟡` = seen, guided, or partially understood; an independent full solution is not yet established.
- `✅` = independently solved in full once.
- `✅✅` = independently solved in full multiple times.

Keep `Learning Status` to one of those four values. Preserve detailed progression in `context/history/question_history.md`; keep `Current Note` concise.

`Latest Recall` uses the shared format and calibration rules in `context/recall.md` and contains only the latest verified recall attempt. When non-recall practice should affect the 21-day cooldown, add `Last practice: yyyy-mm-dd` to `Current Note` and the detailed archive section.

Yellow does not mean failure. It means the intended solution is seen or partially understood but still needs an independent full pass.

Rule:

If a problem blocks too long:

1. Understand the proof once.
2. Write the standard solution once.
3. Mark yellow.
4. Redo later.
5. Move on.

## Phase 4 Boundary

Ordinary unseen/mixed attempts belong in `context/mixed_practice.md`, not in this file.

Do not automatically add every mixed-practice failure to the question bank.

Promote a mixed problem or technique here only when the promotion rules in `context/mixed_practice.md` identify a reusable knowledge gap that should become part of the permanent learned-problem set.

## Questions

| # | Section | Learning Status | Latest Recall | Problem / Topic | Current Note                                                                                                                  |
| ---: | --- | --- | --- | --- |-------------------------------------------------------------------------------------------------------------------------------|
| 1 | DP | ✅ | L3 ✅ 2026-06-24 | Coin Change | Latest recall passed; load detailed history only when needed.                                                                 |
| 2 | DP | ✅ | L3 ✅ 2026-06-24 | Perfect Squares | Latest recall passed; load detailed history only when needed.                                                                 |
| 3 | DP | ✅ | L3 ✅ 2026-06-26 | Combination Sum IV | Latest recall passed; load detailed history only when needed.                                                                 |
| 4 | DP | ✅ | L1 review 2026-07-21 | Maximum Product Cutting | Latest recall needs review; load the matching history section after selection.                                                |
| 5 | DP | ✅ | L1 review 2026-07-24 | Minimum Path Sum Grid | Latest recall needs review; load the matching history section after selection.                                                |
| 6 | DP | ✅ | L1 review 2026-07-19 | Partition Equal Subset Sum | Latest recall needs review; load the matching history section after selection.                                                |
| 7 | DP | ✅ | L1 review 2026-07-21 | Target Sum | Latest recall needs review; load the matching history section after selection.                                                |
| 8 | DP | ✅ | L2 review 2026-07-21 | Longest Increasing Subsequence | Latest recall needs review; load the matching history section after selection.                                                |
| 9 | DP | ✅ | L2 ✅ 2026-07-24 | Longest Common Subsequence | Latest recall passed; load detailed history only when needed.                                                                 |
| 10 | DP | ✅ | L1 review 2026-07-20 | Edit Distance | Latest recall needs review; load the matching history section after selection.                                                |
| 11 | DP | ✅ | L1 review 2026-07-25 | 0/1 Knapsack | Latest recall needs review; load the matching history section after selection.                                                |
| 12 | DP | ✅ | L1 review 2026-07-25 | Unbounded Knapsack basics | Latest recall needs review; load the matching history section after selection.                                                |
| 13 | DP | ✅ | L1 review 2026-07-25 | Longest Palindromic Subsequence | Latest recall needs review; load the matching history section after selection.                                                |
| 14 | DP | ✅ | L1 review 2026-07-26 | Delete Operation for Two Strings | Latest recall needs review; load the matching history section after selection.                                                |
| 15 | DP | ✅ | L1 review 2026-07-26 | Minimum ASCII Delete Sum for Two Strings | Redo the equal-character dominance proof, O(mn) boundary handling, and a careful ASCII trace.                                 |
| 16 | DP | ✅ | L3 ✅ 2026-06-14 | Distinct Subsequences |                                                                                                                               |
| 17 | DP | ✅ | L1 review 2026-07-26 | Longest Palindromic Substring | Latest recall needs review; load the matching history section after selection.                                                |
| 18 | DP | ✅ | L1 review 2026-07-27 | Palindromic Substrings | Could not recall at all. Redo odd/even center ownership, expansion invariant, uniqueness proof, and complexity independently. |
| 19 | Graphs / Matrix | ✅ | L1 review 2026-07-20 | Number of Islands | Latest recall needs review; load the matching history section after selection.                                                |
| 20 | Graphs | ✅ | L1 ✅ 2026-07-25 | Max Area of Island | Latest recall passed; load detailed history only when needed.                                                                 |
| 21 | Graphs | ✅ | L1 review 2026-07-20 | Rotting Oranges | Latest recall needs review; load the matching history section after selection.                                                |
| 22 | Graphs | ✅ | L2 review 2026-07-21 | Clone Graph | Latest recall needs review; load the matching history section after selection.                                                |
| 23 | Graphs | ✅ | L1 review 2026-07-26 | Course Schedule I | Latest recall needs review; load the matching history section after selection.                                                |
| 24 | Graphs | ✅ | L2 review 2026-07-20 | Course Schedule II | Latest recall needs review; load the matching history section after selection.                                                |
| 25 | Graphs | ✅ | L1 ✅ 2026-07-26 | Pacific Atlantic Water Flow | Latest recall passed; load detailed history only when needed.                                                                 |
| 26 | Graphs | ✅ | L1 ✅ 2026-07-26 | Surrounded Regions | Latest recall passed; load detailed history only when needed.                                                                 |
| 27 | Graphs | ✅ | L1 ✅ 2026-07-28 | Number of Enclaves | Latest recall passed; boundary-reachability marking, complement count, DFS safety, and complexity were sound.                 |
| 28 | Graphs | ✅ | L3 ✅ 2026-06-14 | Accounts Merge |                                                                                                                               |
| 29 | Graphs | ✅ | L1 review 2026-07-27 | Redundant Connection | Redo the DSU forest invariant, cycle/tree proof, and inverse-Ackermann complexity independently. |
| 30 | Graphs | ✅ | L1 review 2026-07-28 | Graph Valid Tree | Redo the cycle-plus-connectivity proof and `O(n + m α(n))` optimized-DSU bound independently.                                 |
| 31 | Graphs | ✅ | L2 review 2026-07-22 | Network Delay Time | Latest recall needs review; load the matching history section after selection.                                                |
| 32 | Graphs | ✅ | L1 review 2026-07-28 | Cheapest Flights Within K Stops | Redo `k+1` snapshot rounds, current-round destination minimum, bounded-edge invariant, and complexity independently.          |
| 33 | Graphs | ✅ | L1 review 2026-07-29 | Path With Minimum Effort | Redo the per-cell minimax state, relaxation rule, and priority-ordered exploration independently. |
| 34 | Graphs | ✅ | L1 review 2026-07-29 | Swim in Rising Water | Redo minimax relaxation, first-pop optimality, and `n²`-cell complexity independently. |
| 35 | Backtracking | ✅ | L2 review 2026-07-25 | Subsets | Latest recall needs review; load the matching history section after selection.                                                |
| 36 | Backtracking | ✅ | L1 review 2026-07-27 | Subsets II | Redo the recursion-state invariant, same-depth duplicate proof, and O(n × 2^n) output-sensitive complexity independently.     |
| 37 | Backtracking | ✅ | L2 review 2026-07-26 | Permutations | Latest recall needs review; load the matching history section after selection.                                                |
| 38 | Backtracking | ✅ | L2 review 2026-07-28 | Combination Sum | Redo start-index ownership, same-index reuse, permutation-duplicate prevention, pruning, and complexity independently.        |
| 39 | Backtracking | ✅ | L1 review 2026-07-20 | Combination Sum II | Latest recall needs review; load the matching history section after selection.                                                |
| 40 | Backtracking | ✅ | L1 review 2026-07-24 | Generate Parentheses | Latest recall needs review; load the matching history section after selection.                                                |
| 41 | Backtracking | ✅ | L1 review 2026-07-28 | Letter Combinations of a Phone Number | Redo empty-input handling and `O(nK)` output-sensitive time/space independently; generator was correct.                       |
| 42 | Backtracking | ✅ | L3 ✅ 2026-06-14 | Palindrome Partitioning | Latest recall passed; load detailed history only when needed.                                                                 |
| 43 | Backtracking / Matrix | ✅ | L2 review 2026-07-28 | Word Search | Redo `index + 1`, path-local restoration on every return path, helper invariant, and complexity independently.                |
| 44 | Backtracking |  |  | N-Queens — optional later |                                                                                                                               |
| 45 | Trees | ✅ | L1 ✅ 2026-07-27 | Maximum Depth of Binary Tree | Latest recall passed; recurrence and complexity were independently sound.                                                     |
| 46 | Trees | ✅ | L1 ✅ 2026-07-25 | Same Tree | Latest recall passed; load detailed history only when needed.                                                                 |
| 47 | Trees | ✅ | L1 ✅ 2026-07-28 | Invert Binary Tree | Latest recall passed; recursive subtree contract, safe swap intent, and `O(n)`/`O(h)` bounds were sound.                      |
| 48 | Trees | ✅ | L1 review 2026-07-21 | Diameter of Binary Tree | Latest recall needs review; load the matching history section after selection.                                                |
| 49 | Trees | ✅ | L1 review 2026-07-24 | Balanced Binary Tree | Latest recall needs review; load the matching history section after selection.                                                |
| 50 | Trees | ✅ | L2 review 2026-07-26 | Binary Tree Level Order Traversal | Latest recall needs review; load the matching history section after selection.                                                |
| 51 | Trees | ✅ | L1 ✅ 2026-07-27 | Subtree of Another Tree | Latest recall passed; load detailed history only when needed. |
| 52 | Trees | ✅ | L1 review 2026-07-28 | Lowest Common Ancestor of BST | Redo the same-side elimination proof and `O(h)` balanced/skewed complexity independently.                                    |
| 53 | Trees | ✅ | L1 review 2026-07-20 | Validate Binary Search Tree | Latest recall needs review; load the matching history section after selection.                                                |
| 54 | Trees | ✅ | L1 review 2026-07-21 | Kth Smallest Element in BST | Latest recall needs review; load the matching history section after selection.                                                |
| 55 | Trees | ✅ | L1 ✅ 2026-07-28 | Binary Tree Right Side View | Latest recall passed; level boundaries, left-to-right queue order, empty input, and `O(n)`/`O(w)` bounds were sound.           |
| 56 | Trees | ✅ | L1 ✅ 2026-07-28 | Count Good Nodes in Binary Tree | Latest recall passed; path-maximum state, negative initialization, and traversal complexity were independently sound.         |
| 57 | Trees | ✅ | L1 ✅ 2026-07-29 | Lowest Common Ancestor of Binary Tree | Latest recall passed; path-set membership and the ancestor case were independently repaired after a counterexample. |
| 58 | Trees | ✅ | L1 ✅ 2026-07-20 | Construct Binary Tree from Preorder and Inorder Traversal | Latest recall passed; load detailed history only when needed.                                                                 |
| 59 | Trees | ✅ | L3 ✅ 2026-06-14 | Serialize and Deserialize Binary Tree | Latest recall passed; load detailed history only when needed.                                                                 |
| 60 | Trees |  |  | Binary Tree Maximum Path Sum — optional later |                                                                                                                               |
| 61 | Heap / Priority Queue | ✅ | L1 ✅ 2026-07-26 | Kth Largest Element in an Array | Latest recall passed; load detailed history only when needed.                                                                 |
| 62 | Heap / Priority Queue | ✅ | L2 review 2026-07-24 | Top K Frequent Elements | Latest recall needs review; load the matching history section after selection.                                                |
| 63 | Heap / Priority Queue | ✅ | L1 review 2026-07-28 | K Closest Points to Origin | Redo max-heap direction, offer-then-trim order, overflow-safe squared distance, and `O(n log k)` / `O(k)` analysis independently. |
| 64 | Heap / Priority Queue | ✅ | L1 review 2026-07-24 | Merge K Sorted Lists | Latest recall needs review; load the matching history section after selection.                                                |
| 65 | Heap / Priority Queue | ✅ | L1 review 2026-07-21 | Find Median from Data Stream | Latest recall needs review; load the matching history section after selection.                                                |
| 66 | Heap / Priority Queue |  |  | Task Scheduler — optional later |                                                                                                                               |
| 67 | Heap / Priority Queue |  |  | Last Stone Weight — optional later |                                                                                                                               |
| 68 | Heap / Priority Queue |  |  | Reorganize String — optional later |                                                                                                                               |
| 69 | Heap / Priority Queue |  |  | Meeting Rooms II — optional later |                                                                                                                               |
| 70 | Heap / Priority Queue |  |  | Design Twitter — optional later |                                                                                                                               |
| 71 | Sliding Window | ✅ | L1 review 2026-07-25 | Best Time to Buy and Sell Stock | Latest recall needs review; load the matching history section after selection.                                                |
| 72 | Sliding Window | ✅ | L1 review 2026-07-25 | Longest Substring Without Repeating Characters | Latest recall needs review; load the matching history section after selection.                                                |
| 73 | Sliding Window | ✅ | L1 review 2026-07-28 | Longest Repeating Character Replacement | Redo empty-window initialization, `length - maxFrequency > k`, valid-window shrinking, and complexity independently.          |
| 74 | Sliding Window | ✅✅ | L1 ✅ 2026-07-28 | Permutation in String | Latest L1 recall passed; fixed-size frequency-window model was sound. Use L2 later only if boundary-code fluency is targeted. |
| 75 | Sliding Window | ✅✅ | L1 review 2026-07-29 | Find All Anagrams in a String | Redo the fixed 26-count equality check and `O(|text| + |pattern|)` / `O(1)` analysis independently. |
| 76 | Sliding Window | ✅ | L1 review 2026-07-19 | Minimum Window Substring | Latest recall needs review; load the matching history section after selection.                                                |
| 77 | Sliding Window | 🟡 | L2 review 2026-07-22 | Sliding Window Maximum | Independent ownership is not established; load the matching history section after selection.                                  |
| 78 | Two Pointers | ✅✅ | L2 ✅ 2026-07-28 | Valid Palindrome | Latest recall passed; two-pointer filtering and invariant were sound, with a minor on-the-fly case-normalization optimization. |
| 79 | Two Pointers | ✅ | L1 ✅ 2026-07-28 | Two Sum II — Input Array Is Sorted | Latest recall passed; pointer movement, sorted-order elimination, indexing, and complexity were sound.                        |
| 80 | Two Pointers | ✅ | L1 review 2026-07-25 | 3Sum | Latest recall needs review; load the matching history section after selection.                                                |
| 81 | Two Pointers | ✅✅ | L1 ✅ 2026-07-26 | Container With Most Water | Latest recall passed; load detailed history only when needed.                                                                 |
| 82 | Two Pointers | ✅ | L1 review 2026-07-24 | Trapping Rain Water | Latest recall needs review; load the matching history section after selection.                                                |
| 83 | Intervals | ✅ | L2 review 2026-07-26 | Merge Intervals | Latest recall needs review; load the matching history section after selection.                                                |
| 84 | Intervals | ✅ | L1 ✅ 2026-07-27 | Insert Interval | Latest recall passed; phase boundaries, overlap handling, invariant, and complexity were independently sound.                 |
| 85 | Intervals | ✅ | L1 review 2026-07-26 | Non-overlapping Intervals | Latest recall needs review; load the matching history section after selection.                                                |
| 86 | Intervals | ✅ | L1 ✅ 2026-07-28 | Meeting Rooms | Latest recall passed; sorted-start adjacency test, equality boundary, empty guard, and complexity were sound.                 |
| 87 | Intervals | ✅ | L1 review 2026-07-24 | Meeting Rooms II | Latest recall needs review; load the matching history section after selection.                                                |
| 88 | Intervals | 🟡 | L3 ✅ 2026-07-17 | MinimumIntervalToIncludeEachQuery | Independent ownership is not established; load the matching history section after selection.                                  |
| 89 | Intervals | 🟡 | L1 review 2026-07-27 | Minimum Number of Arrows to Burst Balloons | Redo the scalar overlap boundary, greedy commitment point, and optimality proof independently.                                |
| 90 | Greedy | ✅ | L1 review 2026-07-25 | Maximum Subarray | Latest recall needs review; load the matching history section after selection.                                                |
| 91 | Greedy | 🟡 | L1 ✅ 2026-07-26 | Jump Game | Latest recall passed; independent L3 ownership is not yet established.                                                        |
| 92 | Greedy | 🟡 | L2 review 2026-07-19 | Jump Game II | Independent ownership is not established; load the matching history section after selection.                                  |
| 93 | Greedy | 🟡 | L3 review 2026-07-17 | Gas Station | Independent ownership is not established; load the matching history section after selection.                                  |
| 94 | Greedy | ✅ | L2 ✅ 2026-07-23 | Partition Labels | Latest recall passed; load detailed history only when needed.                                                                 |
| 95 | Greedy | ✅ | L1 review 2026-07-25 | Hand of Straights | Latest recall needs review; load the matching history section after selection.                                                |
| 96 | Greedy | ✅ | L1 review 2026-07-25 | Merge Triplets to Form Target Triplet | Latest recall needs review; load the matching history section after selection.                                                |
| 97 | Stack | ✅ | L1 review 2026-07-26 | Valid Parentheses | Latest recall needs review; load the matching history section after selection.                                                |
| 98 | Stack | ✅ | L2 review 2026-07-24 | Min Stack | Latest recall needs review; load the matching history section after selection.                                                |
| 99 | Stack | ✅ | L1 ✅ 2026-07-27 | Daily Temperatures | Latest recall passed; stack invariant, strict comparison, and amortized complexity were independently sound.                  |
| 100 | Stack | ✅ | L1 ✅ 2026-07-29 | Next Greater Element I | First verified recall passed; right-to-left monotonic-stack model, amortized analysis, and defensive empty-input handling were sound. |
| 101 | Stack | 🟡 | L1 review 2026-07-24 | Car Fleet | Independent ownership is not established; load the matching history section after selection.                                  |
| 102 | Stack | 🟡 | L1 review 2026-07-20 | Largest Rectangle in Histogram | Independent ownership is not established; load the matching history section after selection.                                  |
| 103 | Stack | 🟡 | L1 review 2026-07-27 | Online Stock Span | Active recall was not achieved and retrieval was unreasonably slow; redo the full model and amortized proof independently.    |
| 104 | Linked List | ✅ | L2 review 2026-07-27 | Reverse Linked List | Redo the uniform `previous/current/next` loop, empty input, invariant, and complexity independently.                          |
| 105 | Linked List | ✅ | L1 ✅ 2026-07-27 | Merge Two Sorted Lists | Latest recall passed; merge invariant, edge handling, and complexity were independently sound.                                |
| 106 | Linked List | ✅ | L1 review 2026-07-28 | Linked List Cycle | Redo the relative-gap-modulo-cycle proof and complexity independently; pointer implementation was correct.                    |
| 107 | Linked List | 🟡 | L2 review 2026-07-20 | Reorder List | Independent ownership is not established; load the matching history section after selection.                                  |
| 108 | Linked List | ✅ | L2 review 2026-07-25 | Remove Nth Node From End of List | Latest recall needs review; load the matching history section after selection.                                                |
| 109 | Linked List | ✅ | L2 review 2026-07-26 | Add Two Numbers | Latest recall needs review; load the matching history section after selection.                                                |
| 110 | Linked List | ✅ | L1 review 2026-07-25 | Copy List with Random Pointer | Latest recall needs review; load the matching history section after selection.                                                |
| 111 | Linked List | ✅ | L3 ✅ 2026-07-19 | LRU Cache | Latest recall passed; load detailed history only when needed.                                                                 |
| 112 | Matrix | ✅ | L1 review 2026-07-25 | Set Matrix Zeroes | Latest recall needs review; load the matching history section after selection.                                                |
| 113 | Matrix | 🟡 | L3 review 2026-07-17 | Spiral Matrix | Independent ownership is not established; load the matching history section after selection.                                  |
| 114 | Matrix | ✅ | L2 review 2026-07-26 | Rotate Image | Latest recall needs review; load the matching history section after selection.                                                |
| 115 | Matrix | ✅ | L1 review 2026-07-26 | Search a 2D Matrix | Latest recall needs review; load the matching history section after selection.                                                |
| 116 | Binary Search | ✅ | L2 review 2026-07-27 | Binary Search | Redo the iterative-only state updates and sorted-order elimination proof independently. |
| 117 | Binary Search | ✅ | L1 review 2026-07-27 | Search in Rotated Sorted Array | Redo equality-first control flow, inclusive sorted-half detection, and full range elimination independently.                  |
| 118 | Binary Search | ✅ | L2 review 2026-07-24 | Find Minimum in Rotated Sorted Array | Latest recall needs review; load the matching history section after selection.                                                |
| 119 | Binary Search | ✅ | L1 ✅ 2026-07-28 | Time Based Key-Value Store | Latest recall passed; per-key ordered history, floor lookup, boundary handling, and complexity were independently sound.      |
| 120 | Binary Search | ✅ | L1 review 2026-07-25 | Koko Eating Bananas | Latest recall needs review; load the matching history section after selection.                                                |
| 121 | Binary Search | ✅ | L1 review 2026-07-28 | Capacity To Ship Packages Within D Days | Redo `[max,sum]`, `daysNeeded <= D`, minimum-feasible boundary updates, and greedy day counting independently.                |
| 122 | Binary Search |  |  | Median of Two Sorted Arrays — optional hard later |                                                                                                                               |
| 123 | Tries | ✅ |  | Implement Trie | Duplicate of core recall #20 (`Trie insert/search/prefix`); exempt from learned-problem recall coverage. Evidence stays there. |
| 124 | Tries | ✅ | L1 ✅ 2026-07-28 | Design Add and Search Words Data Structure | Latest recall passed; wildcard branching, exact terminal check, and Trie complexity were sound.                              |
| 125 | Tries | 🟡 | L1 review 2026-07-20 | Word Search II | Independent ownership is not established; load the matching history section after selection.                                  |
| 126 | Math / Geometry / Bit | ✅ | L1 ✅ 2026-07-25 | Plus One | Latest recall passed; load detailed history only when needed.                                                                 |
| 127 | Math / Geometry / Bit | ✅ | L1 review 2026-07-19 | Pow(x, n) | Latest recall needs review; load the matching history section after selection.                                                |
| 128 | Math / Geometry / Bit | ✅ | L1 ✅ 2026-07-28 | Number of 1 Bits | Latest recall passed; fixed-width scan, unsigned shift, invariant, and complexity were independently sound.                    |
| 129 | Math / Geometry / Bit | ✅ | L1 ✅ 2026-07-29 | Counting Bits | First verified recall passed; fixed-width bit scan, `O(n)` analysis, and output-space distinction were sound. |
| 130 | Math / Geometry / Bit | ✅ | L1 review 2026-07-29 | Reverse Bits | Redo the raw bit-placement loop and valid Java shift syntax independently. |
| 131 | Math / Geometry / Bit | ✅ | L1 review 2026-07-28 | Missing Number | Redo XOR of the full `0..n` candidate range against all present values and its cancellation proof independently.              |
| 132 | Math / Geometry / Bit | ✅ | L1 review 2026-07-23 | Sum of Two Integers | Latest recall needs review; load the matching history section after selection.                                                |
| 133 | Math / Geometry / Bit | ✅ | L1 review 2026-07-29 | Bit Operator Drills: check, set, clear, toggle kth bit | Redo zero-based masks, sign-bit check, and Java shift operators independently. |
| 134 | Math / Geometry / Bit | ✅ | L1 ✅ 2026-07-29 | Single Number | First verified recall passed; XOR cancellation, negative-value handling, and bounds were independently sound. |
| 135 | Math / Geometry / Bit | ✅ | L1 ✅ 2026-07-26 | Hamming Distance | Latest recall passed; load detailed history only when needed.                                                                 |
| 136 | Math / Geometry / Bit | ✅ | L1 review 2026-07-26 | Power of Two | Latest recall needs review; load the matching history section after selection.                                                |
