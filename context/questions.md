# Questions

`questions.md` is the source of truth for exact problem status.

Use this table as the base set of questions to attempt in order to learn different LeetCode patterns.

Status format:

- Empty = not attempted.
- `✅` = solved independently once.
- `✅✅` = solved independently multiple times.
- `🟡` = seen, guided, or partially understood; schedule recall.
- `🟡 → ✅` = was yellow, later solved independently.
- Status can also be a chronological progression trail. For example, `✅ 🟡 ✅` means first implementation succeeded, a later reattempt failed or needed guidance, and a later attempt succeeded again.

Keep the `Status` column compact, but preserve meaningful struggle history when it shows progression. Put detailed notes, redo context, and learning takeaways in the `Takeaways` column.

Yellow does **not** mean failure.

Yellow means:

> Seen, partially understood, needs spaced recall.

Rule:

If a problem blocks too long:

1. Understand the proof once.
2. Write the standard solution once.
3. Mark yellow.
4. Redo later.
5. Move on.

| # | Section | Status | Recall Status | Problem / Topic | Takeaways |
| ---: | --- | --- | --- | --- | --- |
| 1 | DP | ✅ | L3 ✅ 2026-06-24 | Coin Change | Clean top-down memoized implementation. Helper state returns the minimum coins needed for a remaining target, or `-1` if impossible. Complexity correction: brute-force recursion has branching factor `m` and height `amount / minCoin`, so `O(m^(amount/minCoin))`; memoization reduces this to `O(amount * m)` time and `O(amount)` space. |
| 2 | DP | ✅ | L3 ✅ 2026-06-24 | Perfect Squares | Clean top-down memoized implementation. Brute-force recursion has maximum branching factor `sqrt(N)` and height `N`; the full-tree upper bound is `((sqrt(N))^(N+1) - 1) / (sqrt(N) - 1)`, or `O(N^(N/2))`. With memoization, total work is `sum(sqrt(x))` for `x = 1..N`, giving `Theta(N^(3/2))` time and `O(N)` space. |
| 3 | DP | ✅ | L3 ✅ 2026-06-26 | Combination Sum IV | Clean independent top-down memoized recall. State is remaining target; base cases are `target == 0` gives one completed ordered sequence and `target < 0` gives zero. Recurrence tries every number as the next choice, which counts order-sensitive combinations. |
| 4 | DP | ✅ | L1 review 2026-07-21 | Maximum Product Cutting | Recall did not independently recover the standard one-dimensional subproblem. The initial model enumerated unit-position cut/no-cut choices and memoized accumulated product, which prevents useful reuse; the later recurrence `max(n, i * R(n-i))` silently allowed the entire current rod to remain uncut and therefore returns 3 rather than the required 2 for `n = 3`. Guided correction: define `R(n)` as the best product after at least one cut, and for every first piece `i` compare leaving the remainder whole, `i * (n-i)`, against cutting it further, `i * R(n-i)`. The first implementation then overwrote the running maximum on every loop iteration; after review, independently corrected the aggregation and added memoization. Final guided implementation is correct with base `R(n) = 1` for `n <= 2`, O(n²) time, and O(n) memo plus recursion space. The key gap is specifying whether the helper must cut its input before writing the recurrence. Redo after spacing; do not count as independent. |
| 5 | DP | ✅ |  | Minimum Path Sum Grid |  |
| 6 | DP | ✅ | L1 review 2026-07-19 | Partition Equal Subset Sum | Independently recovered the correct reduction: an odd total is impossible; otherwise search for one subset totaling `sum / 2` with memoized state `(index, remainingTarget)` and take/skip branches. Repeated values are distinct elements because index is part of the state. The initial explanation omitted the exhausted-index base case, which can access past the array when no subset succeeds: check `remainingTarget == 0` first, then return false for `index == n` or negative remaining target. O(n × target) time and memo space, plus O(n) recursion stack. |
| 7 | DP | ✅ | L1 review 2026-07-21 | Target Sum | Correctly recalled the recursive state `(index, currentSum)`, terminal condition, and two sign-choice recurrence. Complexity follow-up needed correction: the brute-force recursion takes O(2^n) time and O(n) stack space; memoization has at most `n * (2S + 1)` states for `S = sum(nums)`, so O(nS) time and memo space plus O(n) stack space, combined rather than multiplied. Zero handling also needs recall: `+0` and `-0` are distinct assignments, so `[0]` with target `0` has two ways; the ordinary two-branch recurrence already counts them correctly and should not special-case zero. |
| 8 | DP | ✅ |  | Longest Increasing Subsequence |  |
| 9 | DP | ✅ |  | Longest Common Subsequence |  |
| 10 | DP | ✅ | L1 review 2026-07-20 | Edit Distance | Independently recalled the correct `(i, j)` state, base cases, and insert/delete/replace recurrence with memoization. Complexity follow-up: memo has `O(mn)` states, while recursive stack depth is `O(m+n)`, not `O(m)`. |
| 11 | DP | ✅ |  | 0/1 Knapsack |  |
| 12 | DP | ✅ |  | Unbounded Knapsack basics |  |
| 13 | DP | ✅ |  | Longest Palindromic Subsequence |  |
| 14 | DP | ✅ |  | Delete Operation for Two Strings |  |
| 15 | DP | ✅ |  | Minimum ASCII Delete Sum for Two Strings |  |
| 16 | DP | ✅ | L3 ✅ 2026-06-14 | Distinct Subsequences |  |
| 17 | DP | ✅ |  | Longest Palindromic Substring |  |
| 18 | DP | ✅ |  | Palindromic Substrings |  |
| 19 | Graphs | ✅ | L1 review 2026-07-20 | Number of Islands | Recalled the correct outer grid scan plus DFS/visited approach: each traversal consumes one four-directionally connected component and each newly discovered unvisited land cell starts one island. Precision follow-up: mark cells at discovery before pushing/recursing, and express the scan plus traversal as `O(RC)` time rather than `O(2n)`; separate visited storage and worst-case DFS depth are each `O(RC)`. |
| 20 | Graphs | ✅ |  | Max Area of Island |  |
| 21 | Graphs | ✅ | L1 review 2026-07-20 | Rotting Oranges | Recalled the correct multi-source BFS and two-frontier model: enqueue all initially rotten oranges, then process simultaneous infection waves. Minute accounting needed correction: mark a fresh orange rotten and decrement `remainingFresh` immediately when enqueuing it, and after a level increment minutes only if the next frontier is nonempty (or process levels only while the queue is nonempty and fresh oranges remain). This preserves `[[2]] -> 0` and `[[2,1]] -> 1`. Return `-1` iff fresh oranges remain after BFS. `O(RC)` time and `O(RC)` worst-case queue space. |
| 22 | Graphs | ✅ |  | Clone Graph |  |
| 23 | Graphs | ✅ |  | Course Schedule I |  |
| 24 | Graphs | ✅ | L2 review 2026-07-20 | Course Schedule II | Independently recalled edge direction `prerequisite → dependent`, DFS finishing order, WHITE/GREY/BLACK states, back-edge cycle detection, and traversal from every unvisited course. Two implementation details needed correction: sorting explicit finish times adds O(V log V), so append on finish and reverse once (or add to the front); cycle detection must propagate through every recursive caller via a boolean/exception so callers do not continue marking nodes BLACK and appending them. O(V + E) build and traversal time, O(V + E) total space including adjacency storage, and O(V) auxiliary space if the graph is treated as input. |
| 25 | Graphs | ✅ |  | Pacific Atlantic Water Flow |  |
| 26 | Graphs | ✅ |  | Surrounded Regions |  |
| 27 | Graphs | ✅ |  | Number of Enclaves |  |
| 28 | Graphs | ✅ | L3 ✅ 2026-06-14 | Accounts Merge |  |
| 29 | Graphs | ✅ |  | Redundant Connection |  |
| 30 | Graphs | ✅ |  | Graph Valid Tree |  |
| 31 | Graphs | ✅ | L2 review 2026-06-26 | Network Delay Time | Recalled the Dijkstra model, initially reused a nonstandard mutable-map priority comparator, then corrected the implementation to the conventional Java lazy Dijkstra model: immutable `(node, distance)` heap states, skip stale states when popped, and enqueue only after a successful relaxation. Still redo once from a blank method to replace the old muscle memory with the right invariant. |
| 32 | Graphs | ✅ |  | Cheapest Flights Within K Stops |  |
| 33 | Graphs | ✅ |  | Path With Minimum Effort |  |
| 34 | Graphs | ✅ |  | Swim in Rising Water |  |
| 35 | Backtracking | ✅ |  | Subsets |  |
| 36 | Backtracking | ✅ |  | Subsets II |  |
| 37 | Backtracking | ✅ |  | Permutations |  |
| 38 | Backtracking | ✅ |  | Combination Sum |  |
| 39 | Backtracking | ✅ | L1 review 2026-07-20 | Combination Sum II | Recalled sorting, backtracking state `(start, remainingTarget, path)`, one-use index progression, base cases, and `O(n)` depth/path space. Duplicate handling needed correction: skip `candidates[i]` only when `i > start && candidates[i] == candidates[i - 1]`, which removes equal competing choices at the same recursion depth while allowing two equal values from distinct indices across depths. Recurse with `i + 1`; because the array is sorted, break the loop when a candidate exceeds the remaining target. Worst-case search is exponential, with path-copy/output costs commonly expressed as `O(n * 2^n)`. |
| 40 | Backtracking | ✅ |  | Generate Parentheses |  |
| 41 | Backtracking | ✅ |  | Letter Combinations of a Phone Number |  |
| 42 | Backtracking | 🟡 → ✅ | L3 ✅ 2026-06-14 | Palindrome Partitioning — guided, reattempt later | Clean independent recall implementation. |
| 43 | Backtracking | ✅ |  | Word Search |  |
| 44 | Backtracking |  |  | N-Queens — optional later |  |
| 45 | Trees | ✅ |  | Maximum Depth of Binary Tree |  |
| 46 | Trees | ✅ |  | Same Tree |  |
| 47 | Trees | ✅ |  | Invert Binary Tree |  |
| 48 | Trees | ✅ | L1 review 2026-07-21 | Diameter of Binary Tree | Independently recalled the correct postorder recurrence: null height is zero, each node updates a global diameter with `leftHeight + rightHeight`, and returns `1 + max(leftHeight, rightHeight)`. Correctly gave O(n) time and O(h) recursion space—O(log n) balanced and O(n) skewed. Precision follow-up: the returned value is subtree height rather than root-relative depth; a path extendable by the parent must choose only one child because a simple path cannot fork, while the locally recorded diameter may combine both branches because the current node is their turning point. Initialize the nonnegative diameter to zero rather than negative infinity. |
| 49 | Trees | ✅ |  | Balanced Binary Tree |  |
| 50 | Trees | ✅ |  | Binary Tree Level Order Traversal |  |
| 51 | Trees | ✅ |  | Subtree of Another Tree |  |
| 52 | Trees | ✅ |  | Lowest Common Ancestor of BST |  |
| 53 | Trees | ✅ | L1 review 2026-07-20 | Validate Binary Search Tree | Recalled the correct recursive state `(node, minBound, maxBound)`, null base case, and ancestor-bound propagation: left gets `(minBound, node.value)` and right gets `(node.value, maxBound)`. Correctness follow-up: ordering is strict, so duplicates are invalid and reject `value <= minBound || value >= maxBound`. Space is `O(h)`, giving `O(log n)` for a balanced tree and `O(n)` for a skewed tree, not `O(1)`. |
| 54 | Trees | ✅ |  | Kth Smallest Element in BST |  |
| 55 | Trees | ✅ |  | Binary Tree Right Side View |  |
| 56 | Trees | ✅ |  | Count Good Nodes in Binary Tree |  |
| 57 | Trees | ✅ |  | Lowest Common Ancestor of Binary Tree |  |
| 58 | Trees | ✅ | L1 ✅ 2026-07-20 | Construct Binary Tree from Preorder and Inorder Traversal | Independently recalled the correct recursive model: the next preorder value is the current subtree root, its inorder index splits the inclusive range into left and right subtrees, and a single preorder pointer advances once per created node. A value-to-inorder-index map avoids repeated scans; distinct values make that lookup unambiguous. The pseudocode initially reused `preorder[0]` and looked up the mutable preorder index rather than the consumed root value, then corrected to consume `preorder[preorderIndex++]` and map by that value. O(n) construction time, O(n) map space, and O(h) recursion space—O(log n) balanced and O(n) worst-case skewed. |
| 59 | Trees | 🟡 → ✅ | L3 ✅ 2026-06-14 | Serialize and Deserialize Binary Tree — reattempt later | Clean independent preorder implementation using explicit null markers. |
| 60 | Trees |  |  | Binary Tree Maximum Path Sum — optional later |  |
| 61 | Heap / Priority Queue | ✅ |  | Kth Largest Element in an Array |  |
| 62 | Heap / Priority Queue | ✅ |  | Top K Frequent Elements |  |
| 63 | Heap / Priority Queue | ✅ |  | K Closest Points to Origin |  |
| 64 | Heap / Priority Queue | ✅ |  | Merge K Sorted Lists |  |
| 65 | Heap / Priority Queue | 🟡✅(redo done) | L1 review 2026-07-21 | Find Median from Data Stream — guided, reattempt later | Correctly recalled the two-heap partition: a max-heap for the lower half and a min-heap for the upper half, with O(log n) insertion, O(1) median lookup, and O(n) storage. The first size rules allowed the min-heap to hold the extra element while odd-count lookup returned the max-heap root; after correction, chose the consistent invariant `minSize == maxSize` or `minSize == maxSize + 1`, so an odd median is `minHeap.peek()`. Rebalance if the min side exceeds by more than one or the max side becomes larger. Preserve cross-heap ordering, handle the first insertion before peeking, and cast before adding heap roots to avoid integer overflow in the even median. |
| 66 | Heap / Priority Queue |  |  | Task Scheduler — optional later |  |
| 67 | Heap / Priority Queue |  |  | Last Stone Weight — optional later |  |
| 68 | Heap / Priority Queue |  |  | Reorganize String — optional later |  |
| 69 | Heap / Priority Queue |  |  | Meeting Rooms II — optional later |  |
| 70 | Heap / Priority Queue |  |  | Design Twitter — optional later |  |
| 71 | Sliding Window | ✅ |  | Best Time to Buy and Sell Stock |  |
| 72 | Sliding Window | 🟡 done but need to redo with standard pattern, redo done ✅ |  | Longest Substring Without Repeating Characters |  |
| 73 | Sliding Window | 🟡 pattern understood, redo once later. ✅(redo done) |  | Longest Repeating Character Replacement |  |
| 74 | Sliding Window | 🟡 pattern understood, redo once later. ✅(redo done) ✅(redo done) |  | Permutation in String |  |
| 75 | Sliding Window | ✅✅ |  | Find All Anagrams in a String |  |
| 76 | Sliding Window | 🟡 pattern understood, redo once later. ✅(redo done)✅ | L1 review 2026-07-19 | Minimum Window Substring | Recalled the two-boundary direction but needed guidance to make validity and shrinking precise. Use target and window frequency maps plus a scalar `formedKinds`: increment only when an added character reaches its required count and decrement when removal drops it below the requirement. Expand right while invalid; while valid, record best indices and remove from the left until the window becomes invalid again. Shrinking is governed by frequency, not merely whether the left character occurs in `t`; non-target and surplus target characters are removable. Build target state in O(m), scan with each pointer moving at most n times for O(n + m) total time, use O(distinct target characters) auxiliary space, and allocate only the final returned substring. |
| 77 | Sliding Window | 🟡 Did not use standard pattern; heap solution works, but intended monotonic deque pattern needs redo later; ✅ redo guided pattern completed of monotonic deque |  | Sliding Window Maximum |  |
| 78 | Two Pointers | ✅✅ |  | Valid Palindrome |  |
| 79 | Two Pointers | ✅ |  | Two Sum II — Input Array Is Sorted |  |
| 80 | Two Pointers | 🟡 brute force correct, redo with sorted two-pointer pattern; ✅ redo guided pattern completed; ✅ redo completed |  | 3Sum |  |
| 81 | Two Pointers | ✅✅ |  | Container With Most Water |  |
| 82 | Two Pointers | 🟡 solved with guidance / redo later; ✅ clean recall redo done |  | Trapping Rain Water |  |
| 83 | Intervals | 🟡 current version may pass, redo with result-list / last-merged pattern; ✅ redo guided pattern completed; ✅ clean redo done |  | Merge Intervals |  |
| 84 | Intervals | 🟡 solved with 3-phase interval pattern after guidance; ✅ clean recall redo done |  | Insert Interval |  |
| 85 | Intervals | 🟡 first attempt over-modelled with heap; ✅ greedy interval pattern completed after correction |  | Non-overlapping Intervals |  |
| 86 | Intervals | ✅ |  | Meeting Rooms |  |
| 87 | Intervals | 🟡 correct O(n²) active-overlap solution; redo optimal min-heap version; ✅ optimal min-heap solution implemented after brute-force intuition |  | Meeting Rooms II | think of heap as the active rooms |
| 88 | Intervals | ✅ brute force understood; 🟡 optimal heap sweep not owned; ✅ optimal heap sweep implemented; 🟡 redo once later for ownership | L3 ✅ 2026-07-17 | MinimumIntervalToIncludeEachQuery | Recall implementation completed after correcting the key invariant: process queries in sorted order, add every interval once when `left <= query`, maintain a size-ordered heap, and lazily remove expired heap-top intervals when `right < query`. Initial recall missed equality on interval start and reversed the containment check, then corrected both. |
| 89 | Intervals | ✅ correct greedy sort-by-end solution; 🟡 concept was hard; redo once later from memory |  | Minimum Number of Arrows to Burst Balloons |  |
| 90 | Greedy | ✅ core Kadane transition understood; 🟡review again; ✅ |  | Maximum Subarray | Recursive solution with memo was possible but was O(n²). Simple greedy trick is to see at every index whether to extend previous or start new. |
| 91 | Greedy | ✅ brute-force recursion model correct; 🟡 conceptually tricky, short code, needs pattern installation |  | Jump Game | Recursive solution worked but inefficient. Do a pass from 0 to n-1, keep updating the farthest reachable index. If during iteration you reach an index i greater than farthest reachable index then return false. |
| 92 | Greedy | ✅ DP/BFS-style solution done; ✅ greedy range-boundary solution implemented; 🟡 not installed | L2 review 2026-07-19 | Jump Game II | Recovered a valid range-by-range BFS-compression skeleton after guidance. Initial recall incremented the jump count whenever the farthest reach improved and selected the largest raw `nums[i]`; corrected to scan every index in the current reachable layer, maximize the absolute reach `i + nums[i]`, and commit exactly one jump after the complete layer. The chosen initialization (`steps = 1`, first range `[1..nums[0]]`) is valid only with an explicit `n == 1` return of zero; the alternative zero-layer initialization avoids that special case. Each index belongs to one scanned range, so O(n) time and O(1) extra space. Redo boundary initialization and the BFS-layer minimality proof independently. |
| 93 | Greedy | ✅ brute force; 🟡 greedy pattern not installed | L3 review 2026-07-17 | Gas Station | Recovered the one-pass candidate-reset model with guidance, then independently wrote a correct full implementation. Initial local-choice model chose the station with maximum immediate surplus; later revisions treated zero tank as failure, introduced an unnecessary candidate queue, and carried a failed candidate's deficit into the next candidate. Final model is correct: first ensure total gas covers total cost; maintain the tank for the current candidate; after a negative segment, eliminate every start inside that segment, move the candidate to the following station, and reset the local tank. O(n) time and O(1) extra space. Redo the failed-segment elimination proof independently before counting this as an independent pass. |
| 94 | Greedy | ✅ good non-greedy solution; 🟡 greedy solution read; ✅ greedy solution implemented from memory |  | Partition Labels |  |
| 95 | Greedy | ✅ greedy smallest-remaining-card solution implemented |  | Hand of Straights |  |
| 96 | Greedy | ✅ correct greedy feasibility solution |  | Merge Triplets to Form Target Triplet |  |
| 97 | Stack | ✅ |  | Valid Parentheses |  |
| 98 | Stack | 🟡 could not solve; ✅ auxiliary min-history stack solution implemented |  | Min Stack |  |
| 99 | Stack | ✅ brute-force/update-all-previous solution correct; 🟡 redo with monotonic decreasing stack; ✅ redo done |  | Daily Temperatures |  |
| 100 | Stack | ✅ 100th DSA roadmap problem; ✅ monotonic stack pattern connected with prior monotonic deque learning |  | Next Greater Element I |  |
| 101 | Stack | 🟡 Modeling-heavy problem; ✅ Standard solution written; 🟡 Intuition not owned yet | L3 ✅ 2026-06-23 | Car Fleet | Correct recall implementation after fixing the conceptual bug: a car that catches a fleet ahead merges into it; it does not pop or replace the ahead fleet's arrival time. |
| 102 | Stack | 🟡 standard monotonic-stack solution provided; redo later | L3 review 2026-06-23; L1 review 2026-07-20 | Largest Rectangle in Histogram | Recalled the unresolved `(height, earliestStart)` stack model and the transfer of the earliest popped start to the current shorter bar. Current recall initially calculated area using the current height rather than each popped height. When `(height, start)` is resolved at index `i`, its maximal width is `i - start` and its area is `height × (i - start)`. A virtual zero-height bar at index `n` flushes unresolved entries. Each bar is pushed once and popped once, so even roughly `2n` stack operations are O(n) worst-case amortized time; stack space is O(n). |
| 103 | Stack | 🟡 correct O(n²) scan; ✅ optimized span compression implemented after guidance |  | Online Stock Span | Store `(price, span)`; permanently pop every price `<= current` and absorb its compressed span. Amortized O(1) per call. |
| 104 | Linked List | 🟡 correct stack solution; ✅ O(1) pointer reversal implemented |  | Reverse Linked List | Clean constant-space mutation order: preserve the forward link before redirecting each `next`; old head becomes the tail. |
| 105 | Linked List | ✅ |  | Merge Two Sorted Lists | Reused existing nodes with a merged-tail pointer; append the remaining suffix when one input is exhausted. O(n + m) time, O(1) extra space. |
| 106 | Linked List | ✅ |  | Linked List Cycle | Floyd slow/fast pointers implemented without mutation. If no cycle, fast reaches null; inside a cycle, the relative distance closes modulo the cycle length. O(n) time, O(1) space. |
| 107 | Linked List | ✅ correct O(n) stack solution; 🟡 O(1) pointer model recalled with guidance | L2 review 2026-07-20 | Reorder List | Correctly recalled the three-phase constant-space model: find the middle, split and reverse the second half, then interleave. Pointer-safety details needed guidance: save `second = slow.next` before setting `slow.next = null`; during merge, drive the loop by the second-half pointer and save both suffixes at the start of every iteration before rewiring. Conditional or post-advance saves left stale pointers and could create cycles or dereference null. Reversal invariant: `prev` heads the reversed prefix, `current` heads the unprocessed suffix, and a saved next pointer preserves reachability before mutation. O(n) time and O(1) extra space. |
| 108 | Linked List | ✅ |  | Remove Nth Node From End of List | Two-pointer gap solution understood and rewritten into the clean dummy-node shape; dummy makes head deletion use the same `slow.next = slow.next.next` mutation path as every other deletion. Invalid `n > length` guard discussed separately from LeetCode constraints. |
| 109 | Linked List | 🟡 → ✅ |  | Add Two Numbers | Initial model converted lists into an integer, which overflows for long inputs; corrected to digit-by-digit addition with `carry`, dummy head, and output tail pointer. Loop can include `carry != 0` to handle the final carry uniformly. |
| 110 | Linked List | ✅ |  | Copy List with Random Pointer | Correct HashMap model: map each original node identity to its copied node identity, then wire random pointers using that map. Duplicate values are irrelevant. |
| 111 | Linked List | 🟡 correct implementation after debugging; ✅ sentinel-based recall implementation | L3 ✅ 2026-07-19 | LRU Cache | Independently recovered the HashMap + doubly linked list model and implemented it cleanly with dummy head/tail sentinels. In this version, `head.next` is LRU and `tail.prev` is MRU; every get/update detaches and appends the node before the tail. Initial explanation omitted removing an evicted key from the map, then corrected the map/list consistency invariant. Capacity-one, update-without-growth, miss-without-recency-change, and eviction cases are handled. O(1) average get/put and O(capacity) space. |
| 112 | Matrix | ✅ O(m+n) space solution done; O(1) marker version optional later |  | Set Matrix Zeroes | Correct two-phase mutation model: first record original zero rows/columns, then mutate. Sets avoid duplicate row/column work, giving O(mn) time and O(m+n) space. O(1) version uses first row/first column as markers with separate first-row/first-column flags. |
| 113 | Matrix | 🟡 visited-simulation solution likely correct; 🟡 boundary solution implemented after guidance | L3 review 2026-07-17 | Spiral Matrix | Correctly implemented the O(1)-extra-space shrinking-boundary traversal after guided modeling. The code handles square, rectangular, single-row, single-column, and single-cell matrices using `top < bottom` and `left < right` guards to prevent duplicate traversal. The core remaining gap is independently deriving that the unprocessed rectangle exists exactly while `top <= bottom && left <= right`; initial recall instead tried visited state and a layer-count bound. O(mn) time and O(1) extra space excluding the result. |
| 114 | Matrix | 🟡 → ✅ |  | Rotate Image | Correct transpose-then-reverse-row model found. First implementation allocated a second matrix, violating the in-place constraint; corrected to in-place upper-triangle transpose plus row reversal. |
| 115 | Matrix | 🟡 → ✅ |  | Search a 2D Matrix | Valid two-stage binary-search model: find candidate row, then search within row. Main bug was binary-search termination: `i == j` still represents one candidate and must be checked; corrected to `i > j` as the empty-search-space condition. |
| 116 | Matrix | ✅ |  | Word Search |  |
| 117 | Matrix | ✅ |  | Number of Islands |  |
| 118 | Binary Search | ✅ |  | Binary Search | Clean recursive binary search with correct empty-search-space invariant `low > high`, safe midpoint, and inclusive bounds. |
| 119 | Binary Search | 🟡 → ✅ |  | Search in Rotated Sorted Array | Initial pivot + translated-index model was logically valid but used O(n) pivot scan. Redone with one-pass sorted-half elimination: at each mid, one side must be sorted; use value range checks to decide which half can still contain target. Boundary issue around `mid - 1` corrected by comparing against `nums[mid]` after checking equality first. |
| 120 | Binary Search | 🟡 → ✅ |  | Find Minimum in Rotated Sorted Array | Initial attempt used sorted-half recursion plus mutable global `min`. Final guided solution uses answer-in-range invariant: compare `nums[mid]` to `nums[high]`; if mid value is greater, minimum must be right of mid, otherwise minimum is at mid or to its left, so keep `mid`. |
| 121 | Binary Search | 🟡 → ✅ |  | Time Based Key-Value Store | Correct data model: `Map<String, List<(value, timestamp)>>` with per-key timestamps increasing. Final guided solution uses floor search / rightmost-valid pattern: when `timestamp <= query`, store candidate answer and move right; otherwise move left. |
| 122 | Binary Search | 🟡 → ✅ |  | Koko Eating Bananas | Boundary-search-over-answer pattern implemented: speed validity is monotonic, if `hours <= h` then try smaller speed, otherwise increase speed. Minor cleanup: no need to sort/mutate `piles`; use integer ceil division and consider `long` for accumulated hours. |
| 123 | Binary Search | 🟡 → ✅ |  | Capacity To Ship Packages Within D Days | Boundary-search-over-answer structure completed. Feasibility check counts days by preserving package order and starting a new day whenever the next package would exceed capacity. Key bound: capacity range is `[max(weights), sum(weights)]`, because the ship must carry the heaviest package and can ship all packages in one day at total capacity. |
| 124 | Binary Search |  |  | Median of Two Sorted Arrays — optional hard later |  |
| 125 | Tries | 🟡 → ✅ |  | Implement Trie | Prefix path + `isWord` marker model installed. Delete pruning rule understood: after removing terminal marker, prune only nodes with no children that are not word endings. Using 256-character child array by chosen contract. |
| 126 | Tries | ✅ |  | Design Add and Search Words Data Structure | Wildcard trie DFS model installed: normal characters follow one child; `.` branches across all non-null children; base case must check `isWord` for exact-length match. |
| 127 | Tries | 🟡 | L1 review 2026-07-20 | Word Search II | Recalled the correct high-level Trie + board DFS model, path-local visited restoration, terminal-word deduplication, and prefix pruning. The helper contract needed precision: `(row, col, trieNode)` represents the Trie state before consuming the current board cell; move to `nextNode = trieNode.children[c]`, check `nextNode.isWord`, and pass `nextNode` to neighbors so their character is consumed exactly once. Complexity required guidance: for `S` total dictionary characters and maximum word length `L`, build costs O(S), while adversarial board search is bounded by O(RC × 4^L) (or more tightly about `RC × 4 × 3^(L-1)`). Space is O(S + RC + L) with a visited array or O(S + L) with in-place marking, excluding results. |
| 128 | Math / Geometry / Bit | ✅ |  | Plus One | Correct carry handling without integer conversion. Cleaner interview shape: scan right-to-left, turn trailing 9s into 0, return immediately after incrementing first non-9; allocate `[1,0,...,0]` only if all digits were 9. |
| 129 | Math / Geometry / Bit | 🟡 → ✅ | L1 review 2026-07-19 | Pow(x, n) | Recall required substantial guidance. Initial attempts used incorrect repeated squaring and then correct but linear multiplication; the recursive revision duplicated the same half-power subproblem, added unnecessary memoization, and briefly used the wrong `x^0` base case. The exponentiation-by-squaring recurrence was ultimately recovered: compute one half-power, square it, and multiply by the base for an odd exponent. `Integer.MIN_VALUE` handling was not recalled independently: promote `n` to `long` before negation, invert the base for a negative exponent, then recurse on the nonnegative `long` exponent. O(log |n|) time and O(log |n|) recursion space. |
| 130 | Math / Geometry / Bit | 🟡 → ✅ |  | Number of 1 Bits | Initial sign-bit-plus-magnitude model was incorrect for Java `int`; corrected to raw 32-bit two's-complement inspection using `(n & 1)` and unsigned right shift `>>>`. |
| 131 | Math / Geometry / Bit | ✅ |  | Counting Bits | Correct solution using per-number 32-bit hamming weight scan. Stronger pattern to own: `ans[i] = ans[i >> 1] + (i & 1)`, reusing the already-computed count after removing the lowest bit. |
| 132 | Math / Geometry / Bit | 🟡 → ✅ |  | Reverse Bits | Initial approach over-modeled sign handling and two's-complement conversion. Correct model is raw bit transfer: repeat 32 times, append `num & 1` into `result` after left-shifting result, then unsigned-shift `num >>>= 1`. |
| 133 | Math / Geometry / Bit | 🟡 → ✅ |  | Missing Number | First sorting solution was correct but mutated input and used O(n log n). Redone with XOR cancellation: initialize with `n`, then XOR each index `0..n-1` and each array value so paired values cancel and the missing number remains. |
| 134 | Math / Geometry / Bit | 🟡 → ✅ |  | Sum of Two Integers | Guided standard bit-addition model implemented: `a ^ b` gives partial sum without carry, `(a & b) << 1` gives carry shifted to the next column, repeat until carry is zero. Negative/two's-complement behavior not fully owned; schedule recall rather than grinding now. |
| 135 | Math / Geometry / Bit | ✅ |  | Bit Operator Drills: check, set, clear, toggle kth bit | Practiced `&`, `|`, `^`, `~`, and kth-bit masks. Key correction: use `(n & mask) != 0` instead of `> 0`, because checking the sign bit can produce a negative mask value. |
| 136 | Math / Geometry / Bit | ✅ |  | Single Number | Clean XOR cancellation solution: XOR all values; paired duplicates cancel because `a ^ a = 0`, and the single value remains because `a ^ 0 = a`. |
| 137 | Math / Geometry / Bit | ✅ |  | Hamming Distance | Correct XOR model: `a ^ b` marks differing bit positions, then count set bits over 32 positions. Prefer `>>>` for raw-bit shifting, though fixed 32-iteration counting also works with `>>`. |
| 138 | Math / Geometry / Bit | ✅ |  | Power of Two | Solved with standard one-set-bit check: require `n > 0`, then use `(n & (n - 1)) == 0` because subtracting 1 flips the lowest set bit and all lower bits, so powers of two become zero under AND. |
