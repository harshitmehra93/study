# Questions

`questions.md` owns current learned-problem ownership, recall, and selection
data. The first table is the active recall bank. Optional items and aliases live
in a separate non-active table, so no per-row scope field is needed.

`Learning Status` records current ownership of the intended interview solution:

- Empty = not attempted.
- `🟡` = seen, guided, or partially understood; an independent full solution is not yet established.
- `✅` = independently solved in full once.
- `✅✅` = independently solved in full multiple times.

`Latest Recall` is the only recall-recency field and follows
`context/recall.md`. Learning or repair work does not change it. Keep
`Current Note` empty unless an item-specific target or ownership clarification
is useful; detailed evidence belongs in `context/history/question_history.md`.

Yellow does not mean failure. It means the intended solution is seen or partially understood but still needs an independent full pass.

If a problem blocks too long, understand and write the standard solution once,
mark it yellow, and revisit it later rather than stalling the roadmap.

## Phase 4 Boundary

Ordinary unseen/mixed attempts belong in `context/mixed_practice.md`, not in this file.

Do not automatically add every mixed-practice failure to the question bank.

Promote a mixed problem or technique here only when the promotion rules in
`context/mixed_practice.md` identify a reusable knowledge gap that should
become part of the permanent learned-problem set.

Advanced-topic audit hypotheses and installation planning belong in
`context/advanced_topics.md`. Add or change a row here only after an actual
learning or assessment session supplies evidence under this file's ownership
rules.

## Active Recall Bank

| # | Section | Learning Status | Latest Recall | Problem / Topic | Current Note |
| ---: | --- | --- | --- | --- | --- |
| 1 | DP | ✅ | L3 ✅ 2026-06-24 | Coin Change |  |
| 2 | DP | ✅ | L3 ✅ 2026-06-24 | Perfect Squares |  |
| 3 | DP | ✅ | L3 ✅ 2026-06-26 | Combination Sum IV |  |
| 4 | DP | ✅ | L1 review 2026-07-21 | Maximum Product Cutting |  |
| 5 | DP | ✅ | L1 review 2026-07-24 | Minimum Path Sum Grid |  |
| 6 | DP | ✅ | L1 review 2026-07-19 | Partition Equal Subset Sum |  |
| 7 | DP | ✅ | L1 review 2026-07-21 | Target Sum |  |
| 8 | DP | ✅ | L2 review 2026-07-21 | Longest Increasing Subsequence |  |
| 9 | DP | ✅ | L2 ✅ 2026-07-24 | Longest Common Subsequence |  |
| 10 | DP | ✅ | L1 review 2026-07-20 | Edit Distance |  |
| 11 | DP | ✅ | L1 review 2026-07-25 | 0/1 Knapsack |  |
| 12 | DP | ✅ | L1 review 2026-07-25 | Unbounded Knapsack basics |  |
| 13 | DP | ✅ | L1 review 2026-07-25 | Longest Palindromic Subsequence |  |
| 14 | DP | ✅ | L1 review 2026-07-26 | Delete Operation for Two Strings |  |
| 15 | DP | ✅ | L1 review 2026-07-26 | Minimum ASCII Delete Sum for Two Strings | Redo the equal-character dominance proof, O(mn) boundary handling, and a careful ASCII trace. |
| 16 | DP | ✅ | L3 ✅ 2026-06-14 | Distinct Subsequences |  |
| 17 | DP | ✅ | L1 review 2026-07-26 | Longest Palindromic Substring |  |
| 18 | DP | ✅ | L1 review 2026-07-27 | Palindromic Substrings | Could not recall at all. Redo odd/even center ownership, expansion invariant, uniqueness proof, and complexity independently. |
| 19 | Graphs / Matrix | ✅ | L1 review 2026-07-20 | Number of Islands |  |
| 20 | Graphs | ✅ | L1 ✅ 2026-07-25 | Max Area of Island |  |
| 21 | Graphs | ✅ | L1 review 2026-07-20 | Rotting Oranges |  |
| 22 | Graphs | ✅ | L2 review 2026-07-21 | Clone Graph |  |
| 23 | Graphs | ✅ | L1 review 2026-07-26 | Course Schedule I |  |
| 24 | Graphs | ✅ | L2 review 2026-07-20 | Course Schedule II |  |
| 25 | Graphs | ✅ | L1 ✅ 2026-07-26 | Pacific Atlantic Water Flow |  |
| 26 | Graphs | ✅ | L1 ✅ 2026-07-26 | Surrounded Regions |  |
| 27 | Graphs | ✅ | L1 ✅ 2026-07-28 | Number of Enclaves |  |
| 28 | Graphs | ✅ | L3 ✅ 2026-06-14 | Accounts Merge |  |
| 29 | Graphs | ✅ | L1 review 2026-07-27 | Redundant Connection | Redo the DSU forest invariant, cycle/tree proof, and inverse-Ackermann complexity independently. |
| 30 | Graphs | ✅ | L1 review 2026-07-28 | Graph Valid Tree | Redo the cycle-plus-connectivity proof and `O(n + m α(n))` optimized-DSU bound independently. |
| 31 | Graphs | ✅ | L2 review 2026-07-22 | Network Delay Time |  |
| 32 | Graphs | ✅ | L1 review 2026-07-28 | Cheapest Flights Within K Stops | Redo `k+1` snapshot rounds, current-round destination minimum, bounded-edge invariant, and complexity independently. |
| 33 | Graphs | ✅ | L1 review 2026-07-29 | Path With Minimum Effort | Redo the per-cell minimax state, relaxation rule, and priority-ordered exploration independently. |
| 34 | Graphs | ✅ | L1 review 2026-07-29 | Swim in Rising Water | Redo minimax relaxation, first-pop optimality, and `n²`-cell complexity independently. |
| 35 | Backtracking | ✅ | L2 review 2026-07-25 | Subsets |  |
| 36 | Backtracking | ✅ | L1 review 2026-07-27 | Subsets II | Redo the recursion-state invariant, same-depth duplicate proof, and O(n × 2^n) output-sensitive complexity independently. |
| 37 | Backtracking | ✅ | L2 review 2026-07-26 | Permutations |  |
| 38 | Backtracking | ✅ | L2 review 2026-07-28 | Combination Sum | Redo start-index ownership, same-index reuse, permutation-duplicate prevention, pruning, and complexity independently. |
| 39 | Backtracking | ✅ | L1 review 2026-07-20 | Combination Sum II |  |
| 40 | Backtracking | ✅ | L1 review 2026-07-24 | Generate Parentheses |  |
| 41 | Backtracking | ✅ | L1 review 2026-07-28 | Letter Combinations of a Phone Number | Redo empty-input handling and `O(nK)` output-sensitive time/space independently; generator was correct. |
| 42 | Backtracking | ✅ | L3 ✅ 2026-06-14 | Palindrome Partitioning |  |
| 43 | Backtracking / Matrix | ✅ | L2 review 2026-07-28 | Word Search | Redo `index + 1`, path-local restoration on every return path, helper invariant, and complexity independently. |
| 45 | Trees | ✅ | L1 ✅ 2026-07-27 | Maximum Depth of Binary Tree |  |
| 46 | Trees | ✅ | L1 ✅ 2026-07-25 | Same Tree |  |
| 47 | Trees | ✅ | L1 ✅ 2026-07-28 | Invert Binary Tree |  |
| 48 | Trees | ✅ | L1 review 2026-07-21 | Diameter of Binary Tree |  |
| 49 | Trees | ✅ | L1 review 2026-07-24 | Balanced Binary Tree |  |
| 50 | Trees | ✅ | L2 review 2026-07-26 | Binary Tree Level Order Traversal |  |
| 51 | Trees | ✅ | L1 ✅ 2026-07-27 | Subtree of Another Tree |  |
| 52 | Trees | ✅ | L1 review 2026-07-28 | Lowest Common Ancestor of BST | Redo the same-side elimination proof and `O(h)` balanced/skewed complexity independently. |
| 53 | Trees | ✅ | L1 review 2026-07-20 | Validate Binary Search Tree |  |
| 54 | Trees | ✅ | L1 review 2026-07-21 | Kth Smallest Element in BST |  |
| 55 | Trees | ✅ | L1 ✅ 2026-07-28 | Binary Tree Right Side View |  |
| 56 | Trees | ✅ | L1 ✅ 2026-07-28 | Count Good Nodes in Binary Tree |  |
| 57 | Trees | ✅ | L1 ✅ 2026-07-29 | Lowest Common Ancestor of Binary Tree |  |
| 58 | Trees | ✅ | L1 ✅ 2026-07-20 | Construct Binary Tree from Preorder and Inorder Traversal |  |
| 59 | Trees | ✅ | L3 ✅ 2026-06-14 | Serialize and Deserialize Binary Tree |  |
| 61 | Heap / Priority Queue | ✅ | L1 ✅ 2026-07-26 | Kth Largest Element in an Array |  |
| 62 | Heap / Priority Queue | ✅ | L2 review 2026-07-24 | Top K Frequent Elements |  |
| 63 | Heap / Priority Queue | ✅ | L1 review 2026-07-28 | K Closest Points to Origin | Redo max-heap direction, offer-then-trim order, overflow-safe squared distance, and `O(n log k)` / `O(k)` analysis independently. |
| 64 | Heap / Priority Queue | ✅ | L1 review 2026-07-24 | Merge K Sorted Lists |  |
| 65 | Heap / Priority Queue | ✅ | L1 review 2026-07-21 | Find Median from Data Stream |  |
| 71 | Sliding Window | ✅ | L1 review 2026-07-25 | Best Time to Buy and Sell Stock |  |
| 72 | Sliding Window | ✅ | L1 review 2026-07-25 | Longest Substring Without Repeating Characters |  |
| 73 | Sliding Window | ✅ | L1 review 2026-07-28 | Longest Repeating Character Replacement | Redo empty-window initialization, `length - maxFrequency > k`, valid-window shrinking, and complexity independently. |
| 74 | Sliding Window | ✅✅ | L1 ✅ 2026-07-28 | Permutation in String | Use L2 later only if boundary-code fluency is targeted. |
| 75 | Sliding Window | ✅✅ | L1 review 2026-07-29 | Find All Anagrams in a String | Redo the fixed 26-count equality check and `O(textLength + patternLength)` / `O(1)` analysis independently. |
| 76 | Sliding Window | ✅ | L1 review 2026-07-19 | Minimum Window Substring |  |
| 77 | Sliding Window | 🟡 | L2 review 2026-07-22 | Sliding Window Maximum |  |
| 78 | Two Pointers | ✅✅ | L2 ✅ 2026-07-28 | Valid Palindrome |  |
| 79 | Two Pointers | ✅ | L1 ✅ 2026-07-28 | Two Sum II — Input Array Is Sorted |  |
| 80 | Two Pointers | ✅ | L1 review 2026-07-25 | 3Sum |  |
| 81 | Two Pointers | ✅✅ | L1 ✅ 2026-07-26 | Container With Most Water |  |
| 82 | Two Pointers | ✅ | L1 review 2026-07-24 | Trapping Rain Water |  |
| 83 | Intervals | ✅ | L2 review 2026-07-26 | Merge Intervals |  |
| 84 | Intervals | ✅ | L1 ✅ 2026-07-27 | Insert Interval |  |
| 85 | Intervals | ✅ | L1 review 2026-07-26 | Non-overlapping Intervals |  |
| 86 | Intervals | ✅ | L1 ✅ 2026-07-28 | Meeting Rooms |  |
| 87 | Intervals | ✅ | L1 review 2026-07-24 | Meeting Rooms II |  |
| 88 | Intervals | ✅ | L3 ✅ 2026-07-17 | MinimumIntervalToIncludeEachQuery | Independent L3 ownership established on 2026-07-17. |
| 89 | Intervals | 🟡 | L1 review 2026-07-27 | Minimum Number of Arrows to Burst Balloons | Redo the scalar overlap boundary, greedy commitment point, and optimality proof independently. |
| 90 | Greedy | ✅ | L1 review 2026-07-25 | Maximum Subarray |  |
| 91 | Greedy | 🟡 | L1 ✅ 2026-07-26 | Jump Game | Target an independent L3 pass to establish ownership. |
| 92 | Greedy | 🟡 | L2 review 2026-07-19 | Jump Game II |  |
| 93 | Greedy | 🟡 | L3 review 2026-07-17 | Gas Station |  |
| 94 | Greedy | ✅ | L2 ✅ 2026-07-23 | Partition Labels |  |
| 95 | Greedy | ✅ | L1 review 2026-07-25 | Hand of Straights |  |
| 96 | Greedy | ✅ | L1 review 2026-07-25 | Merge Triplets to Form Target Triplet |  |
| 97 | Stack | ✅ | L1 review 2026-07-26 | Valid Parentheses |  |
| 98 | Stack | ✅ | L2 review 2026-07-24 | Min Stack |  |
| 99 | Stack | ✅ | L1 ✅ 2026-07-27 | Daily Temperatures |  |
| 100 | Stack | ✅ | L1 ✅ 2026-07-29 | Next Greater Element I |  |
| 101 | Stack | 🟡 | L1 review 2026-07-24 | Car Fleet |  |
| 102 | Stack | 🟡 | L1 review 2026-07-20 | Largest Rectangle in Histogram |  |
| 103 | Stack | 🟡 | L1 review 2026-07-27 | Online Stock Span | Active recall was not achieved and retrieval was unreasonably slow; redo the full model and amortized proof independently. |
| 104 | Linked List | ✅ | L2 review 2026-07-27 | Reverse Linked List | Redo the uniform `previous/current/next` loop, empty input, invariant, and complexity independently. |
| 105 | Linked List | ✅ | L1 ✅ 2026-07-27 | Merge Two Sorted Lists |  |
| 106 | Linked List | ✅ | L1 review 2026-07-28 | Linked List Cycle | Redo the relative-gap-modulo-cycle proof and complexity independently; pointer implementation was correct. |
| 107 | Linked List | 🟡 | L2 review 2026-07-20 | Reorder List |  |
| 108 | Linked List | ✅ | L2 review 2026-07-25 | Remove Nth Node From End of List |  |
| 109 | Linked List | ✅ | L2 review 2026-07-26 | Add Two Numbers |  |
| 110 | Linked List | ✅ | L1 review 2026-07-25 | Copy List with Random Pointer |  |
| 111 | Linked List | ✅ | L3 ✅ 2026-07-19 | LRU Cache |  |
| 112 | Matrix | ✅ | L1 review 2026-07-25 | Set Matrix Zeroes |  |
| 113 | Matrix | 🟡 | L3 review 2026-07-17 | Spiral Matrix |  |
| 114 | Matrix | ✅ | L2 review 2026-07-26 | Rotate Image |  |
| 115 | Matrix | ✅ | L1 review 2026-07-26 | Search a 2D Matrix |  |
| 116 | Binary Search | ✅ | L2 review 2026-07-27 | Binary Search | Redo the iterative-only state updates and sorted-order elimination proof independently. |
| 117 | Binary Search | ✅ | L1 review 2026-07-27 | Search in Rotated Sorted Array | Redo equality-first control flow, inclusive sorted-half detection, and full range elimination independently. |
| 118 | Binary Search | ✅ | L2 review 2026-07-24 | Find Minimum in Rotated Sorted Array |  |
| 119 | Binary Search | ✅ | L1 ✅ 2026-07-28 | Time Based Key-Value Store |  |
| 120 | Binary Search | ✅ | L1 review 2026-07-25 | Koko Eating Bananas |  |
| 121 | Binary Search | ✅ | L1 review 2026-07-28 | Capacity To Ship Packages Within D Days | Redo `[max,sum]`, `daysNeeded <= D`, minimum-feasible boundary updates, and greedy day counting independently. |
| 124 | Tries | ✅ | L1 ✅ 2026-07-28 | Design Add and Search Words Data Structure |  |
| 125 | Tries | 🟡 | L1 review 2026-07-20 | Word Search II |  |
| 126 | Math / Geometry / Bit | ✅ | L1 ✅ 2026-07-25 | Plus One |  |
| 127 | Math / Geometry / Bit | ✅ | L1 review 2026-07-19 | Pow(x, n) |  |
| 128 | Math / Geometry / Bit | ✅ | L1 ✅ 2026-07-28 | Number of 1 Bits |  |
| 129 | Math / Geometry / Bit | ✅ | L1 ✅ 2026-07-29 | Counting Bits |  |
| 130 | Math / Geometry / Bit | ✅ | L1 review 2026-07-29 | Reverse Bits | Redo the raw bit-placement loop and valid Java shift syntax independently. |
| 131 | Math / Geometry / Bit | ✅ | L1 review 2026-07-28 | Missing Number | Redo XOR of the full `0..n` candidate range against all present values and its cancellation proof independently. |
| 132 | Math / Geometry / Bit | ✅ | L1 review 2026-07-23 | Sum of Two Integers |  |
| 133 | Math / Geometry / Bit | ✅ | L1 review 2026-07-29 | Bit Operator Drills: check, set, clear, toggle kth bit | Redo zero-based masks, sign-bit check, and Java shift operators independently. |
| 134 | Math / Geometry / Bit | ✅ | L1 ✅ 2026-07-29 | Single Number |  |
| 135 | Math / Geometry / Bit | ✅ | L1 ✅ 2026-07-26 | Hamming Distance |  |
| 136 | Math / Geometry / Bit | ✅ | L1 review 2026-07-26 | Power of Two |  |

## Outside Recall Rotation

These rows retain stable IDs but are never selected from the active recall
bank. An optional item becomes a learning exercise only when requested. An
alias redirects to its canonical tracker row.

| # | Section | Problem / Topic | Disposition |
| ---: | --- | --- | --- |
| 44 | Backtracking | N-Queens | Optional |
| 60 | Trees | Binary Tree Maximum Path Sum | Optional |
| 66 | Heap / Priority Queue | Task Scheduler | Optional |
| 67 | Heap / Priority Queue | Last Stone Weight | Optional |
| 68 | Heap / Priority Queue | Reorganize String | Optional |
| 69 | Heap / Priority Queue | Meeting Rooms II | Alias of active #87 |
| 70 | Heap / Priority Queue | Design Twitter | Optional |
| 122 | Binary Search | Median of Two Sorted Arrays | Optional |
| 123 | Tries | Implement Trie | Alias of core recall #20 |
