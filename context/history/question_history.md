# Learned-Problem Evidence Archive

Sparse cold storage for meaningful guidance, corrections, and progression.
`context/questions.md` owns current state. Load only the selected item's section.

## 1. Coin Change

Clean top-down memoized implementation. Helper state returns the minimum coins needed for a remaining target, or `-1` if impossible. Complexity correction: brute-force recursion has branching factor `m` and height `amount / minCoin`, so `O(m^(amount/minCoin))`; memoization reduces this to `O(amount * m)` time and `O(amount)` space.

## 2. Perfect Squares

Clean top-down memoized implementation. Brute-force recursion has maximum branching factor `sqrt(N)` and height `N`; the full-tree upper bound is `((sqrt(N))^(N+1) - 1) / (sqrt(N) - 1)`, or `O(N^(N/2))`. With memoization, total work is `sum(sqrt(x))` for `x = 1..N`, giving `Theta(N^(3/2))` time and `O(N)` space.

## 3. Combination Sum IV

Clean independent top-down memoized recall. State is remaining target; base cases are `target == 0` gives one completed ordered sequence and `target < 0` gives zero. Recurrence tries every number as the next choice, which counts order-sensitive combinations.

## 4. Maximum Product Cutting

Recall did not independently recover the standard one-dimensional subproblem. The initial model enumerated unit-position cut/no-cut choices and memoized accumulated product, which prevents useful reuse; the later recurrence `max(n, i * R(n-i))` silently allowed the entire current rod to remain uncut and therefore returns 3 rather than the required 2 for `n = 3`. Guided correction: define `R(n)` as the best product after at least one cut, and for every first piece `i` compare leaving the remainder whole, `i * (n-i)`, against cutting it further, `i * R(n-i)`. The first implementation then overwrote the running maximum on every loop iteration; after review, independently corrected the aggregation and added memoization. Final guided implementation is correct with base `R(n) = 1` for `n <= 2`, O(n²) time, and O(n) memo plus recursion space. The key gap is specifying whether the helper must cut its input before writing the recurrence. Redo after spacing; do not count as independent.

## 5. Minimum Path Sum Grid

Independently recovered the correct top-down state: `R(i,j)` is the minimum path sum from `(i,j)` to the target, including both endpoints. Every valid path begins by moving right or down, so the recurrence adds the current cell to the cheaper valid optimal suffix. Boundary aggregation needed correction: initializing a minimum to zero or negative infinity is invalid; out-of-bounds moves can return positive infinity, or a `-1` sentinel must be explicitly excluded. The first pseudocode checked the memo but omitted storing the computed result. Time is O(rows × columns). Space analysis initially counted only the recursion stack; the memo uses O(rows × columns), while maximum recursion depth is O(rows + columns), giving O(rows × columns) total auxiliary space.

## 6. Partition Equal Subset Sum

Independently recovered the correct reduction: an odd total is impossible; otherwise search for one subset totaling `sum / 2` with memoized state `(index, remainingTarget)` and take/skip branches. Repeated values are distinct elements because index is part of the state. The initial explanation omitted the exhausted-index base case, which can access past the array when no subset succeeds: check `remainingTarget == 0` first, then return false for `index == n` or negative remaining target. O(n × target) time and memo space, plus O(n) recursion stack.

## 7. Target Sum

Correctly recalled the recursive state `(index, currentSum)`, terminal condition, and two sign-choice recurrence. Complexity follow-up needed correction: the brute-force recursion takes O(2^n) time and O(n) stack space; memoization has at most `n * (2S + 1)` states for `S = sum(nums)`, so O(nS) time and memo space plus O(n) stack space, combined rather than multiplied. Zero handling also needs recall: `+0` and `-0` are distinct assignments, so `[0]` with target `0` has two ways; the ordinary two-branch recurrence already counts them correctly and should not special-case zero.

## 8. Longest Increasing Subsequence

Initial recall proposed greedily scanning later increasing values from each start, which fails on `[1,5,2,3,4]` because choosing `5` blocks the longer `[1,2,3,4]`. Then independently recovered correct exhaustive take/skip subsequence generation, with the minor correction that the terminal case must return after evaluating a selection; validating every leaf costs O(n * 2^n). Guided state compression produced the correct top-down contract `R(previousSelectedIndex, index)`: skip the current value, or take it when there is no previous selection or `nums[previous] < nums[index]`, returning the maximum branch length. The memo key is `(previousSelectedIndex, index)`; for each index, previous ranges from `-1` through `index - 1`, giving O(n²) states and time. Space is O(n²) for memo plus O(n) recursion, not only the stack. Redo the optimized state independently after spacing.

## 9. Longest Common Subsequence

Independently recovered the correct suffix state `R(indexA, indexB)`, exhausted-string base case, memo key, and a valid exhaustive recurrence: skip from either string and, when the current characters match, also take both and add one. With memoization this has O(mn) states and time, O(mn) memo space, and O(m+n) maximum recursion depth (`O(max(m,n))` is asymptotically equivalent, but O(m+n) describes the path directly). Canonical refinement: when the current characters match, the skip branches are dominated, so return `1 + R(i+1, j+1)` directly; otherwise take the maximum of the two skip branches. The dominance explanation was initially circular. Exchange proof: because the equal characters are the earliest available occurrence in both suffixes, an optimal subsequence using that character later can move the match to the current pair without losing later choices, while an optimum omitting it entirely could be prefixed by it; therefore some optimum takes the current match.

## 10. Edit Distance

Independently recalled the correct `(i, j)` state, base cases, and insert/delete/replace recurrence with memoization. Complexity follow-up: memo has `O(mn)` states, while recursive stack depth is `O(m+n)`, not `O(m)`.

## 11. 0/1 Knapsack

Initial recall used `(index, remainingCapacity, currentValue)`, returned accumulated totals, and initially rewarded an overweight branch. After correcting terminal behavior, guidance was still needed to remove `currentValue` and define the reusable suffix subproblem `R(index, remainingCapacity)` as the maximum additional value obtainable. The revised take/skip recurrence was correct after fixing the exact-fit condition from `remainingCapacity - weight[index] > 0` to `>= 0`. Memo key is `(index, remainingCapacity)`, with O(n × capacity) states and time, O(n × capacity) memo space, and O(n) recursion depth. Advancing to `index + 1` in both branches removes the current item from all descendant states and enforces selection at most once. Redo independently after spacing.

## 12. Unbounded Knapsack basics

Independently chose the compact state `R(remainingCapacity)`, the maximum additional value obtainable without exceeding that capacity, and looped over every item type as the next selection. Returning zero when nothing fits correctly represents leaving unused capacity; recursing only after selecting an item keeps every type available and permits unlimited reuse. The initial explanation incorrectly used reuse to justify harmless order duplication: different selection orders are harmless because they represent the same multiset and total value, while memoization collapses calls reaching the same remaining capacity. Memo key is remaining capacity, giving O(capacity) states, but each state examines all n types, so memoized time is O(n × capacity), not O(capacity). Memo space is O(capacity), maximum recursion depth is O(capacity / minWeight), and the no-memo branching-tree upper bound is O(n^(capacity/minWeight)); O(n^capacity) is a looser bound for positive integer weights. Redo the proof and bounds independently after spacing.

## 13. Longest Palindromic Subsequence

Recall initially used a void helper plus a global maximum, which cannot return and compare the best answers of nested intervals. Correct state is `R(left,right)`, the LPS length inside the inclusive interval. Base cases are zero for an empty interval (`left > right`) and one for a single character; using `-1` breaks adjacent matches such as `"aa"`, which must evaluate as `2 + 0`. When boundaries match, return `2 + R(left+1,right-1)`; otherwise return the maximum of skipping either boundary. The matching-boundary proof required guidance: an optimum omitting both equal endpoints can be extended by both, and one using exactly one endpoint can replace its matching interior endpoint with the unused boundary, so some optimum uses both. Memo key is `(left,right)` with O(n²) states and O(1) work per state, giving O(n²) time and memo space plus O(n) recursion depth, or O(n²) total auxiliary space.

## 14. Delete Operation for Two Strings

Independently recovered the correct suffix state `R(i,j)`, exhausted-string base cases, equal-character transition `R(i+1,j+1)`, and mismatch transition `1 + min(R(i+1,j), R(i,j+1))`. The memo key `(i,j)` gives `(m+1)(n+1) = O(mn)` states and O(mn) time and memo space. Stack-depth analysis was stated as O(max(m,n)); this is asymptotically equivalent, but O(m+n) directly describes a path that may delete from both strings. The equal-character justification needed refinement: because the matching characters are earliest in both suffixes, any common subsequence from later characters can be prefixed by this pair, so some optimum keeps both and deleting either cannot improve the minimum.

## 15. Minimum ASCII Delete Sum for Two Strings

### Recall 2026-07-26 — L1 review

Independently recovered the correct suffix-state model and recurrence: when the leading characters match, keep both; otherwise pay to delete either leading character and take the cheaper resulting suffix problem. The first draft had two mechanical index errors (`j` compared with the wrong string length and the delete-from-first-string branch failed to advance exactly once), both corrected on revision. The state definition needed precision: `R(i,j)` is the minimum ASCII deletion cost required to make suffixes `A[i:]` and `B[j:]` equal.

The equal-character dominance proof was not established independently. There exists an optimal alignment keeping equal leading characters because any solution matching one of them later can instead match the two current equal characters without increasing cost. Complexity also needed correction: if every exhaustion case loops over its remaining suffix, the literal bound is O(mn + m² + n²); precomputed suffix sums or memoized one-character boundary transitions restore O(mn) time. Memo storage is O(mn), with O(m+n) recursion depth. The `"a"` versus `"b"` trace used incorrect ASCII values: `'a' = 97` and `'b' = 98`, so either deletion order costs 195.

## 17. Longest Palindromic Substring

Recalled a valid memoized interval-checking model that explores smaller intervals when the current interval is not palindromic. Initial recursion lacked the `left > right → true` base needed for even palindromes such as `"bb"`, returned true whenever endpoints matched even if the inner interval was not palindromic, and called `Math.max` without assigning the result. Correct flow: if matching endpoints surround a palindromic interior, record the interval and return true; otherwise explore `(left+1,right)` and `(left,right-1)` and return false. Returning the substring requires best-left/right indices, and empty input should return `""` before recursion. Valid non-empty `(left,right)` states number `n(n+1)/2`, plus O(n) empty intervals, so there are O(n²) states—not `n²/4`. With constant work per state, time and memo space are O(n²), and recursion depth is O(n).

Guided learning transfer 2026-08-17 (Major help; timing not observed): After the transformed-string Manacher model and a complete generic implementation had been supplied, Harshit submitted a LeetCode 5 solution using `#a#b#...#`, center-inclusive radii, and exclusive `(L,R)` rightmost-palindrome boundaries. The implementation correctly clips the mirrored radius by `R - i`, expands from the first unverified layer, selects a maximum radius, and maps its transformed endpoints back to the original substring for both parities. The official-style cases and 5,000 fixed-seed random strings of length 0–34 passed against a brute-force longest-length oracle. The endpoint mapping is correct but unnecessarily indirect; with this convention, `originalStart = (center - (radius - 1)) / 2` and `originalEndExclusive = (center + (radius - 1)) / 2` expose the invariant more clearly. Prefer `sb.append('#').append(c)` over allocating `"#" + c` on every iteration, and make helper state private. This is successful guided transfer, not independent recall.

## 18. Palindromic Substrings

Recall 2026-07-27 (L1 review): Proposed interval recursion `R(i,j)` that adds the current palindrome and both smaller intervals, attempting to avoid their overlap by returning zero for previously seen states. This made the recurrence traversal-order dependent, and base intervals were not memoized, so `"aaa"` was overcounted. After the overlap flaw and the need for unique ownership were identified, Harshit explicitly requested the missing model. Teach expansion around odd and even centers; redo its invariant, one-center-per-palindrome proof, `"abba"` trace, and `O(n²)` time / `O(1)` auxiliary-space analysis independently.

Guided learning transfer 2026-08-17 (Major help; timing not observed): After direct Manacher teaching and a verified generic implementation, Harshit submitted a LeetCode 647 solution that sums `radius / 2` over the `#a#b#...#` transformed centers. With the chosen center-inclusive radius, this correctly counts each original palindrome once for both letter and separator centers; for example, the transformed radii for `"aaa"` contribute six in total. Construction and counting are `O(n)` time with `O(n)` space, and the constraint-bounded answer fits `int`. This is correct guided transfer, not independent recall; later derive the per-center contribution and uniqueness argument without prompts.

## 19. Number of Islands

Recalled the correct outer grid scan plus DFS/visited approach: each traversal consumes one four-directionally connected component and each newly discovered unvisited land cell starts one island. Precision follow-up: mark cells at discovery before pushing/recursing, and express the scan plus traversal as `O(RC)` time rather than `O(2n)`; separate visited storage and worst-case DFS depth are each `O(RC)`.

## 20. Max Area of Island

Independently recovered the full-grid scan and DFS component-area model. The helper returns the number of previously unvisited land cells reachable from `(row,col)`, returns zero for out-of-bounds, water, or visited cells, marks land before exploring, and returns one plus the four neighbor results. Mark-before-recursion prevents immediate revisits on cyclic adjacency; even two neighboring land cells recurse back into one another if discovery marking is delayed. Every cell is scanned once and every land cell is traversed once, giving O(mn) time. The visited matrix and worst-case recursion depth are each O(mn), so combined auxiliary space remains O(mn). Terminology correction: the island is a connected component that may contain cycles; DFS merely induces a traversal tree.

## 21. Rotting Oranges

Recalled the correct multi-source BFS and two-frontier model: enqueue all initially rotten oranges, then process simultaneous infection waves. Minute accounting needed correction: mark a fresh orange rotten and decrement `remainingFresh` immediately when enqueuing it, and after a level increment minutes only if the next frontier is nonempty (or process levels only while the queue is nonempty and fresh oranges remain). This preserves `[[2]] -> 0` and `[[2,1]] -> 1`. Return `-1` iff fresh oranges remain after BFS. `O(RC)` time and `O(RC)` worst-case queue space.

## 22. Clone Graph

Independently selected BFS and an original-node-to-clone map. The initial skeleton wired a neighbor only when it was newly discovered and did not initially mark the root, which can overwrite the root mapping and omit reverse or cross edges. A second version manually added both directions only on discovery; a triangle counterexample showed that this copies only BFS-tree edges and misses edges between already-discovered nodes. Guided correction established the invariant: clone and enqueue each original node once, but copy every original adjacency entry unconditionally using the mapped neighbor clone. The map itself is sufficient discovery state; handle null before cloning. O(V + E) time, O(V) auxiliary map/queue space, and O(V + E) returned graph space.

## 23. Course Schedule I

Independently selected directed DFS back-edge detection, correctly stored prerequisite edges as `b → a`, and recalled WHITE/GREY/BLACK states with an edge to GREY indicating a cycle. A global cycle flag with short-circuiting is valid when it is set immediately on a GREY edge, observed by every recursive caller and the outer loop, and the final result negates it. A BLACK node is no longer on the active recursion stack, so an edge to it does not close a cycle through the current path. Follow-up was needed on the DFS forest: launch from every WHITE course because a disconnected component may contain a cycle. Traversal takes O(V+E) time. Colors and worst-case recursion use O(V); because edge-pair input must be converted into an adjacency list, include O(V+E) graph storage for O(V+E) total space, or state O(V) auxiliary when adjacency is treated as the input representation.

## 24. Course Schedule II

Independently recalled edge direction `prerequisite → dependent`, DFS finishing order, WHITE/GREY/BLACK states, back-edge cycle detection, and traversal from every unvisited course. Two implementation details needed correction: sorting explicit finish times adds O(V log V), so append on finish and reverse once (or add to the front); cycle detection must propagate through every recursive caller via a boolean/exception so callers do not continue marking nodes BLACK and appending them. O(V + E) build and traversal time, O(V + E) total space including adjacency storage, and O(V) auxiliary space if the graph is treated as input.

## 25. Pacific Atlantic Water Flow

Clean independent recall of reverse reachability. Start one traversal from every Pacific boundary cell (top row and left column) and another from every Atlantic boundary cell (bottom row and right column); in the reversed direction, move only to an equal-or-higher neighbor. The helper rejects out-of-bounds, visited, or lower-than-previous cells and marks before exploring four neighbors. A reverse path is exactly the reversal of a valid forward downhill/equal water path, so a cell reached from an ocean can drain to that ocean. Intersect the two visited matrices for the result. Each ocean traversal and the final intersection scan are O(mn), so total time is O(mn). Two visited matrices and worst-case recursion depth are each O(mn), and their combined auxiliary space remains O(mn).

## 26. Surrounded Regions

Clean independent recall of the reverse-classification model: start DFS from every `'O'` on the first/last row and first/last column, mark every boundary-connected `'O'` safe before exploring its four neighbors, then scan the full board and flip every unvisited `'O'` to `'X'`. The helper returns for out-of-bounds, visited, or `'X'` cells; discovery marking before recursion prevents cyclic revisits. Repeated corner starts are harmless because visited state makes later calls return immediately. Every cell is processed O(1) times across the boundary traversals and final scan, giving O(mn) time. A separate visited matrix and worst-case recursion stack are each O(mn), so combined auxiliary space remains O(mn).

## 27. Number of Enclaves

Recall 2026-07-28 (L1 pass): The first draft started traversal from every cell, returned immediately on boundary cells, and counted visited interior land; `3 × 3` all-land showed that this counts an escaping center incorrectly. After that defect was identified, Harshit independently recovered the correct reverse-classification model: start from every boundary land cell, mark all reachable land, then count land not visited by any boundary traversal. The corrected helper returns only for out-of-bounds, water, or already visited cells and marks before recursing. `visited` therefore means boundary-reachable/escaping land, so its complement among land cells is exactly the enclave count. Time is `O(mn)` and auxiliary space is `O(mn)` for visited state plus a worst-case `O(mn)` recursion stack.

## 29. Redundant Connection

Recall 2026-07-27 (L1 review): Independently selected incremental disjoint sets and correctly proposed rejecting the first input edge whose endpoints already have the same representative. The attempt initially described a DSU component as an MST, used “back edge” without proving the existing-path cycle invariant, and did not establish why the accepted edges form one tree. The amortized path-compression plus union-by-rank/size bound was not known and Harshit explicitly requested help. Redo the accepted-forest/component invariant, connected-plus-acyclic proof, and `O(n α(n))` total time / `O(n)` space independently.

## 30. Graph Valid Tree

Recall history:

- 2026-07-28 — L1 review. Independently selected disjoint sets, rejected an edge whose endpoints already shared a representative, and then repaired the initially missing connectivity condition by requiring all vertices to finish in one set. Harshit correctly identified union by rank and path compression but did not know their precise amortized bound; `O(α(n))` per operation and `O(n + m α(n))` total time had to be supplied. Space is `O(n)`. Redo the cycle-plus-connectivity sufficiency proof and optimized-DSU complexity independently.

## 31. Network Delay Time

Earlier recall: L2 review 2026-06-26. Recalled the Dijkstra model, initially reused a nonstandard mutable-map priority comparator, then corrected the implementation to the conventional Java lazy Dijkstra model. On the 2026-07-22 recall, independently selected immutable `(node, distance)` heap records and correctly explained that an obsolete record is skipped after the node has been finalized. Precision still needed correction: initialize unknown shortest distances to positive infinity; order the heap by total candidate distance from the source rather than edge weight; enqueue a new immutable record carrying the improved distance; and state the settled-node invariant using nonnegative edge weights. Complexity also needed guidance: at most `E` successful relaxations create `O(E)` lazy heap records, giving `O(V + E log E)` time and `O(V + E)` auxiliary space for this implementation, even when adjacency storage is excluded. Keep at review rather than independent ownership.

## 32. Cheapest Flights Within K Stops

Recall 2026-07-28 (L1 review): Selected bounded Bellman–Ford and correctly initialized the source to zero, other cities to infinity, and skipped edges whose source remained unreachable. The first draft ran only `k` rounds and did not specify snapshot isolation; feedback established that `k` stops allow `k+1` edges and that each round must read source distances from the completed previous round. Harshit then adopted previous/current maps and corrected the loop to `k+1` rounds. A material relaxation bug persisted: the candidate was repeatedly compared with `oldDistances[v]` rather than the best `updatedDistances[v]`, so multiple incoming candidates in one round could let a later higher cost overwrite an earlier lower cost. After a counterexample, the corrected comparison, and a plain worked example were supplied, Harshit completed the correct final skeleton: each candidate reads `old[u]` and competes with `updated[v]`. Canonical invariant: after round `r`, `dist[v]` is the minimum cost from `src` to `v` using at most `r` edges. With `k+1` rounds, copying `V` distances and scanning `E` flights per round costs `O((k+1)(V+E))` time and `O(V)` auxiliary distance space. Keep at review because the essential bounded-round relaxation structure required meaningful help; redo independently.

## 33. Path With Minimum Effort

Recall 2026-07-28 (L1 review): Proposed path-local DFS over every simple grid path. The accumulated update incorrectly used `abs(cellValue - maxEffortOfThePath)`, comparing a height with an effort rather than comparing adjacent cell heights, and the initial `INF` would keep every path effort infinite. Feedback identified those defects, the exponential enumeration, and the need for a per-cell best state, but the same DFS and update were repeated. Canonical minimax-Dijkstra model had to be supplied: `best[r][c]` is the minimum possible maximum edge difference from the source to that cell; moving from `(r,c)` to `(nr,nc)` proposes `max(best[r][c], abs(height[r][c] - height[nr][nc]))`; improve and enqueue the neighbor when that candidate is smaller. A min-heap ordered by candidate effort settles cells in nondecreasing minimax cost, so the destination can be returned when settled. For `V = mn` cells and `E = O(mn)` neighbor edges, time is `O(mn log(mn))` and auxiliary space is `O(mn)`. Redo independently.

Tracker override 2026-07-28: At Harshit's request, cleared this result from `Latest Recall` and scheduled a fresh independent recall for 2026-07-29. Preserve the guided-attempt evidence above, but do not count it as the current recall result or use it to start the recall cooldown.

Recall 2026-07-29 (L1 review): Began with a path-local DFS that correctly updated each path's effort as the maximum adjacent-height difference, aside from the initial source call. After the exponential-path issue was identified, proposed memoizing by cell, but the memo value was invalid because the result depends on the maximum effort already incurred and the active DFS path. Harshit then requested the answer. Canonical model: `best[r][c]` stores the smallest possible maximum edge effort among all source-to-cell paths. From a settled or popped cell, neighbor `(nr,nc)` receives `candidate = max(best[r][c], abs(heights[r][c] - heights[nr][nc]))`; update and enqueue only when `candidate < best[nr][nc]`. Process candidates in ascending effort with a min-heap; the target's first non-stale pop is optimal. For `mn` cells, time is `O(mn log(mn))` and auxiliary space is `O(mn)`. Redo independently.

## 34. Swim in Rising Water

Recall 2026-07-29 (L1 review): Independently chose priority-ordered graph exploration and correctly used visited-on-pop with duplicate candidates. The initial relaxation added one unit per move, but time does not accumulate with path length; guided correction established `candidate = max(currentRequiredTime, neighborElevation)`. A popped state's time is the smallest known maximum elevation along a start-to-cell path. First-pop optimality follows because any lower-bottleneck alternative path would cross from the settled region at a candidate no larger than that alternative, which the min-heap would remove first. For an `n × n` grid, `V = n²` and `E = O(n²)`, so time is `O(n² log(n²)) = O(n² log n)` and auxiliary space is `O(n²)`. Redo independently.

## 35. Subsets

Initial loop-based skeleton recursed with `index + 1` after selecting `nums[i]`, allowing already-passed positions to be reconsidered, and emitted results only at exhaustion, which missed valid partial subsets such as `[]` and `[1]`. Switched to a correct binary include/skip skeleton: at each fixed index, take the value, recurse, undo, then skip and recurse; copy the path at `index == n`. The base case needed braces so `return` is not unconditional. There are 2^n leaves; copying subsets makes time O(n × 2^n). Recursion depth and current-path storage are O(n). Returned-output space is O(n × 2^n), not O(2^n), because the result stores n × 2^(n-1) total element occurrences. Each subset corresponds to exactly one n-bit take/skip vector, and distinct input values prevent different vectors from yielding equal value-subsets. Redo the skeleton independently after spacing.

## 36. Subsets II

2026-07-27 — L1 review. Began with a valid binary take/skip skeleton for distinct values but did not know how to prevent duplicate value-subsets. After meaningful guidance on sorting and duplicate-run handling, first applied a global adjacent-value condition that would lose repeated-value subsets such as `[2,2]`, then switched to the standard correct loop model: emit the current path, consider candidates from `index`, and skip equal candidates only at the same recursion depth. The final explanation still misstated the entry invariant as an empty list and gave polynomial rather than exponential time. Redo independently: at entry, the path contains the chosen prefix and `index` is the first remaining eligible position; sorting makes equal candidates adjacent; same-depth skipping chooses one representative for the next slot while deeper recursion still permits multiple copies; up to `2^n` distinct subsets with O(n) copying gives O(n × 2^n) time and returned-output space, with O(n) auxiliary recursion/path space.

## 37. Permutations

Recalled the correct depth-by-depth choice model with a current path and a set of remaining distinct values. The initial Java-like skeleton removed from the same set being traversed by an enhanced `for`, which would throw `ConcurrentModificationException`; corrected to iterate the fixed input array while mutating only the separate availability set. Every root-to-leaf path removes one distinct available value per depth and restores it on backtrack, so it contains every input exactly once. Two paths that first differ at position k produce different permutations because values are distinct. Counting needed correction: the leaves are `n × (n-1) × ... × 1 = n!`, not `n(n-1)`; copying n values into each result gives O(n × n!) time and O(n × n!) returned-output space. Recursion, current path, and the availability set are each O(n), so combined auxiliary working space is O(n).

## 38. Combination Sum

Recall 2026-07-28 (L2 review): Harshit independently wrote the target-zero/negative base cases and correct choose–recurse–restore mechanics. The first skeleton restarted candidate iteration from zero in every call, generating permutations of the same combination. After a focused prompt he introduced a start index, but the recursive transition used `index + 1` rather than the chosen index and therefore did not model unlimited reuse correctly. When asked to choose the child start state, he reverted to the original zero-based loop. The coach supplied the canonical transition: iterate `i` from `start` and recurse with `i`, which permits reusing the chosen candidate while preventing earlier candidates from appearing later. Redo that state ownership, sorted pruning if used, and output-sensitive complexity independently.

## 39. Combination Sum II

Recalled sorting, backtracking state `(start, remainingTarget, path)`, one-use index progression, base cases, and `O(n)` depth/path space. Duplicate handling needed correction: skip `candidates[i]` only when `i > start && candidates[i] == candidates[i - 1]`, which removes equal competing choices at the same recursion depth while allowing two equal values from distinct indices across depths. Recurse with `i + 1`; because the array is sorted, break the loop when a candidate exceeds the remaining target. Worst-case search is exponential, with path-copy/output costs commonly expressed as `O(n * 2^n)`.

## 40. Generate Parentheses

Independently recovered the recursive remaining-open/remaining-close state and correct StringBuilder backtracking shape. The closing branch initially allowed any remaining close, which can create an invalid prefix; guided correction established `remainingClose > remainingOpen`, equivalently every prefix has used at least as many opens as closes. Every valid result corresponds to one unique sequence of branch choices. Maximum recursion depth and auxiliary space are O(n). Complexity counting required guidance: there are `C_n` valid outputs (the nth Catalan number), each of length `2n`, so output-sensitive time and returned-output space are O(n × C_n); a simpler safe interview upper bound is O(n × 4^n). No Catalan derivation is required for this roadmap.

## 41. Letter Combinations of a Phone Number

Self-reported familiarity on 2026-07-26; no verified recall attempt was performed. Marked recalled at Harshit's request after he reported knowing the problem. No helper contract, recurrence, edge-case explanation, code skeleton, correctness argument, or complexity analysis was demonstrated in this session, therefore it does not count as a verified recall pass.

Recall 2026-07-28 (L1 review): Independently wrote the correct choose–recurse–undo generator with state `(index, StringBuilder)`, one mapped-character choice per digit, terminal string materialization at `index == digits.length()`, and path restoration after every branch. The public empty-input guard was initially omitted, which would return `[""]` rather than `[]`, and was then added. Complexity needed meaningful correction: for `K` generated strings of length `n`, copying and storing each result gives `O(nK)` time and `O(nK)` returned-output space; recursion and the mutable path use `O(n)` auxiliary space. Since `K <= 4^n`, worst-case time and returned output are `O(n × 4^n)`. Redo the output-sensitive accounting independently.

## 42. Palindrome Partitioning

Learning history: initially guided or partial, then solved independently. The original learning pass was guided and scheduled for an independent reattempt. Clean independent recall implementation.

## 43. Word Search

Recall 2026-07-28 (L2 review): After initially skipping the prompt because recent Word Search II work covered similar board traversal, Harshit explicitly returned and wrote a start-from-every-cell DFS skeleton. The first version did not advance the word index and shared a visited set across sibling branches without restoration. After both issues were identified, he produced correct control flow: validate the current cell and character, mark it, recurse on four neighbors with `index + 1`, restore before returning, and preserve the path-local no-reuse invariant. He independently stated `O(mn × 4^L)` worst-case time and `O(L)` path/recursion space, bounded by the board size. Redo the index transition, unconditional restoration, invariant, and complexity independently.

## 46. Same Tree

Clean independent recall. The helper compares two corresponding nodes: both null is true, exactly one null is false, and unequal values are false; otherwise both corresponding left subtrees and both corresponding right subtrees must match. The base-case order distinguishes two absent nodes from a structural mismatch. Worst-case time is O(n) for n corresponding nodes examined. Recursion space is O(h), giving O(log n) for balanced trees and O(n) for skewed trees. Short-circuit `&&` can stop after an early left-side mismatch but does not change the O(n) worst case when the trees are identical or differ only at the last examined node.

## 47. Invert Binary Tree

Recall 2026-07-28 (L1 pass): Independently stated the correct recursive contract—swap the two children at every subtree root, then invert both resulting child subtrees. The pseudocode assigned `node.left` twice instead of assigning the saved original left child to `node.right`; this was treated as a localized typo because the swap intent and saved temporary state were explicit. Every node is processed once for `O(n)` time. Full auxiliary space is `O(h)` for recursion, giving `O(log n)` when balanced and `O(n)` when skewed; only non-stack working state is `O(1)`.

## 48. Diameter of Binary Tree

Independently recalled the correct postorder recurrence: null height is zero, each node updates a global diameter with `leftHeight + rightHeight`, and returns `1 + max(leftHeight, rightHeight)`. Correctly gave O(n) time and O(h) recursion space—O(log n) balanced and O(n) skewed. Precision follow-up: the returned value is subtree height rather than root-relative depth; a path extendable by the parent must choose only one child because a simple path cannot fork, while the locally recorded diameter may combine both branches because the current node is their turning point. Initialize the nonnegative diameter to zero rather than negative infinity.

## 49. Balanced Binary Tree

Independently recovered the correct one-pass postorder model: the helper returns subtree height, compares left and right heights locally, and sets a global failure flag that short-circuits remaining work. Minor pseudocode corrections were needed to invoke the helper before returning and express imbalance as `abs(leftHeight - rightHeight) > 1`. Correctly produced a counterexample where the root's height difference is at most one but a deeper node is unbalanced, showing that every node must be checked. Time is O(n). Space analysis initially claimed O(log n) because of short-circuiting, but existing recursion frames remain: stack space is O(h), giving O(log n) for a balanced tree and O(n) for a skewed tree.

## 50. Binary Tree Level Order Traversal

Independently wrote the correct level-processing loop: snapshot the queue size, poll exactly that many nodes into one level, and enqueue left then right children. The skeleton omitted result/queue initialization, enqueuing the root, and the empty-root return. Capturing the size freezes the current level boundary; using a queue size that grows as children are added can consume the next level into the current output list. Every node is enqueued and dequeued once, giving O(n) time. Queue space is O(w) for maximum tree width w, or O(n) worst case (roughly half the nodes in a perfect tree may occupy its last level), rather than a fixed `n/2 + 1` expression.

## 52. Lowest Common Ancestor of BST

Recall 2026-07-28 (L1 review): Independently wrote the correct top-down BST traversal after ordering the targets: descend left when both values are smaller, descend right when both are larger, and return the current node when the paths split or the current node equals a target. The initial correctness explanation relied on imprecise preorder wording; the clean invariant is that both existing targets and their LCA remain in the retained subtree while both values lie strictly on the same side. Complexity was stated only for a balanced tree and incorrectly assumed that every BST step removes half the nodes. The height-based bound had to be supplied: `O(h)` time and `O(h)` recursive stack space, which is `O(log n)` when balanced and `O(n)` when skewed; an iterative implementation uses `O(1)` auxiliary space. Redo the proof and worst-case bound independently.

## 53. Validate Binary Search Tree

Recalled the correct recursive state `(node, minBound, maxBound)`, null base case, and ancestor-bound propagation: left gets `(minBound, node.value)` and right gets `(node.value, maxBound)`. Correctness follow-up: ordering is strict, so duplicates are invalid and reject `value <= minBound` or `value >= maxBound`. Space is `O(h)`, giving `O(log n)` for a balanced tree and `O(n)` for a skewed tree, not `O(1)`.

## 54. Kth Smallest Element in BST

Independently recalled inorder traversal, a visit counter, answer capture at the kth visit, and boolean propagation to short-circuit all remaining recursion. Precision correction: visit and increment after the left subtree and before the right subtree, not after completing both. The useful early-stop bound is O(h + k): up to O(h) work reaches and retains the search path, and inorder processes only the first k values before stopping; it does not visit values greater than the answer. Worst-case time is O(n). Recursion space is O(h), giving O(log n) for a balanced tree and O(n) for a skewed tree.

## 55. Binary Tree Right Side View

Recall 2026-07-28 (L1 pass): Independently selected level-order traversal, captured the last dequeued node from each fixed-size level, and clarified that children are enqueued left before right so queue order remains left-to-right. Empty input returns an empty result. Each of `n` nodes is processed once for `O(n)` time, and the queue uses `O(w)` auxiliary space for maximum tree width `w`.

## 57. Lowest Common Ancestor of Binary Tree

Recall 2026-07-29 (L1 pass): Independently derived a root-to-target path-set solution. Each `find` adds its target and every ancestor on the successful return path. The LCA descent follows the child contained in both sets; when the targets split across children, the current node is the LCA. A counterexample exposed that omitting a target from its own set breaks the ancestor case; Harshit repaired that representation himself. Use node identity rather than values because values can repeat. Two searches and the final descent are `O(n)` time overall; the two path sets and recursion are `O(n)` auxiliary space in the worst case (more precisely, path-set storage and recursion depth are `O(h)`).

## 58. Construct Binary Tree from Preorder and Inorder Traversal

Independently recalled the correct recursive model: the next preorder value is the current subtree root, its inorder index splits the inclusive range into left and right subtrees, and a single preorder pointer advances once per created node. A value-to-inorder-index map avoids repeated scans; distinct values make that lookup unambiguous. The pseudocode initially reused `preorder[0]` and looked up the mutable preorder index rather than the consumed root value, then corrected to consume `preorder[preorderIndex++]` and map by that value. O(n) construction time, O(n) map space, and O(h) recursion space—O(log n) balanced and O(n) worst-case skewed.

## 59. Serialize and Deserialize Binary Tree

Learning history: initially guided or partial, then solved independently. The original learning pass was guided and scheduled for an independent reattempt. Clean independent preorder implementation using explicit null markers.

## 61. Kth Largest Element in an Array

Independently identified both a valid ascending-sort solution returning `nums[n-k]` and the stronger size-k min-heap model. After each processed prefix, the heap contains its largest `min(k, processedCount)` occurrences, with the smallest retained occurrence at the root; when size exceeds k, polling discards the one value that cannot remain among the largest k. Duplicates need no special handling because the heap stores occurrences rather than distinct values. After the full scan, exactly k largest occurrences remain, so the root is the kth largest. Complexity wording needed a minor refinement: up to n offers and n-k polls each cost O(log k), giving O(n log k) total time and O(k) auxiliary space.

## 62. Top K Frequent Elements

Independently recovered a correct frequency-map plus max-heap solution and correctly analyzed repeated heap insertion and extraction as O(n log n) worst-case time with O(n) space. That model does not satisfy the strict better-than-O(n log n) follow-up. The linear bucket model required guidance: because every frequency is an integer in `[1,n]`, use frequency as an array index, store every distinct value once in its frequency bucket, then scan buckets from `n` down until `k` values are collected. No comparison sort or heap is required. Building the map is O(n), distributing `m` distinct values is O(m), and scanning bucket indexes plus contents is O(n + m), giving O(n) total time and O(n) auxiliary space. Redo the bucket skeleton independently after spacing.

## 63. K Closest Points to Origin

Recall 2026-07-28 (L1 review): Harshit independently chose the intended size-`k` max-heap model and stated that the farthest retained point should be removed when the heap exceeds `k`. His first pseudocode implemented a min-heap and trimmed before insertion, allowing a final size of `k + 1`; after feedback he corrected both. Distance comparison initially used square root and unsafe subtraction; the overflow-safe squared-`long` comparison was supplied. He stated the correct retained-prefix invariant, then needed correction from `O(n log n)` / `O(n)` to the bounded-heap `O(n log k)` / `O(k)` analysis. Redo the implementation direction/order, safe distance comparison, and bounded-heap complexity independently.

## 64. Merge K Sorted Lists

Independently proposed a correct repeated k-head scan: maintain one pointer per list, select the smallest current head, advance that list, and append the selected node. This is correct but costs O(Nk) for N total nodes. After an optimization prompt, identified the min-heap model. Heap invariant: it contains exactly the first unmerged node from every non-exhausted list. Pop the minimum, preserve and enqueue its successor if present, and append the popped node using a dummy head and result tail. Each node enters and leaves a heap of size at most k, giving O(N log k) time and O(k) auxiliary space, excluding the returned list. Original nodes may be reused; preserve the successor before rewiring. Redo the heap model and Java skeleton independently after spacing.

## 65. Find Median from Data Stream

Prior learning-status trail: 🟡✅(redo done). The original learning pass was guided and scheduled for an independent reattempt. Correctly recalled the two-heap partition: a max-heap for the lower half and a min-heap for the upper half, with O(log n) insertion, O(1) median lookup, and O(n) storage. The first size rules allowed the min-heap to hold the extra element while odd-count lookup returned the max-heap root; after correction, chose the consistent invariant `minSize == maxSize` or `minSize == maxSize + 1`, so an odd median is `minHeap.peek()`. Rebalance if the min side exceeds by more than one or the max side becomes larger. Preserve cross-heap ordering, handle the first insertion before peeking, and cast before adding heap roots to avoid integer overflow in the even median.

## 69. Meeting Rooms II

Tracker disposition: alias of active problem #87, `Meeting Rooms II`; never
select this row independently.

## 71. Best Time to Buy and Sell Stock

After asking whether prefix sums applied, used the supplied prefix-minimum direction to immediately produce the correct one-pass pseudocode. Before processing the current price, `minValue` is the minimum price among earlier days; `max` is the greatest valid buy-before-sell profit found so far. Evaluate `current - minValue` before extending the prefix minimum with the current price. This returns zero for one price or a strictly decreasing sequence. O(n) time and O(1) auxiliary space. Keep at review because the key prefix-summary abstraction was prompted rather than independently recovered.

## 72. Longest Substring Without Repeating Characters

Prior learning-status trail: 🟡 done but need to redo with standard pattern, redo done ✅. Independently recovered the correct set-based two-boundary model. The set represents exactly the unique characters in the current half-open window `[left,right)`: when the incoming character is already present, hold `right` fixed and remove from the left until the earlier copy is gone; otherwise insert it, advance right, and update the maximum. The first initialization preloaded `s[0]` with `max = 0`, which throws for an empty string and returns zero for a one-character string; corrected with an empty guard and `max = 1`, though starting from an empty window is cleaner. Each character is inserted once and removed at most once, so total pointer movement is O(n) and average time is O(n) with a hash set. Space is O(min(n, alphabet size)), not automatically O(1) unless the alphabet is fixed and bounded.

## 73. Longest Repeating Character Replacement

Prior learning-status trail: 🟡 pattern understood, redo once later. ✅(redo done).

Recall 2026-07-28 (L1 review): Harshit began with a frequency-map sliding window but tried to validate it by sorting frequency groups and spending `k` across them. The first `TreeSet` version dropped equal-frequency entries; replacing it with a priority queue preserved entries but remained unnecessarily complex, omitted the result maximum, and failed empty input. After focused prompting, he derived the correct replacement quantity `windowLength - maxFrequency`, but initially reversed the invalidity inequality and then used a single shrink with exact recomputation rather than restoring the valid-window invariant. The preloaded first character also made empty input fail and a one-character input return zero. Redo from an empty window using the invalidity test `windowLength - maxFrequency > k`, restore the chosen window invariant before recording the answer, and state `O(n)` time / fixed-alphabet space independently.

## 74. Permutation in String

Prior learning-status trail: 🟡 pattern understood, redo once later. ✅(redo done) ✅(redo done).

Recall 2026-07-28 (L1 pass): Independently identified the fixed-size window of `pattern.length()`, target and current character frequencies, the equality condition for a permutation, and adding the entering character while removing the leaving character. That model is sufficient for L1 and gives `O(|pattern| + |text|)` time with fixed 26-entry arrays and `O(1)` auxiliary space. An optional loop skeleton omitted both pointer increments and would run forever; this is an L2 implementation issue and did not invalidate the independently sound L1 model. Use an L2 recall later only if precise boundary-code fluency is the target.

## 75. Find All Anagrams in a String

Recall 2026-07-29 (L1 review): Independently recovered the fixed-size sliding-window frequency model: build target counts, update the entering and leaving characters as the window moves, and emit an index when counts match. Initially described reversal as a brute-force anagram test; it was clarified that reversal is only one permutation. The complexity analysis assumed scanning all pattern counts at every window and therefore concluded `O(|text| × |pattern|)` time and `O(|pattern|)` space. Meaningful guidance established that, for lowercase English letters, two 26-entry frequency arrays compare in constant time, yielding `O(|text| + |pattern|)` time and `O(1)` auxiliary space. Redo the bounded-alphabet reasoning independently.

## 76. Minimum Window Substring

Prior learning-status trail: 🟡 pattern understood, redo once later. ✅(redo done)✅. Recalled the two-boundary direction but needed guidance to make validity and shrinking precise. Use target and window frequency maps plus a scalar `formedKinds`: increment only when an added character reaches its required count and decrement when removal drops it below the requirement. Expand right while invalid; while valid, record best indices and remove from the left until the window becomes invalid again. Shrinking is governed by frequency, not merely whether the left character occurs in `t`; non-target and surplus target characters are removable. Build target state in O(m), scan with each pointer moving at most n times for O(n + m) total time, use O(distinct target characters) auxiliary space, and allocate only the final returned substring.

## 77. Sliding Window Maximum

Prior learning-status trail: 🟡 Did not use standard pattern; heap solution works, but intended monotonic deque pattern needs redo later; ✅ redo guided pattern completed of monotonic deque. Recall initially returned to the valid but non-optimal lazy max-heap model, which takes `O(n log n)` time and can retain `O(n)` stale entries. Guided recovery reached the linear candidate-deque model: store indices in decreasing value order, expire out-of-window indices from the front, remove candidates dominated by the incoming value from the back, and read the maximum at the front. The implementation attempt mixed up the deque ends, mutated `k`, omitted reliable expiry, and returned the index rather than its value; these were corrected during review. Amortized complexity also needed guidance: every index is inserted once and removed at most once, giving `O(n)` time and `O(k)` space. Redo from a blank loop before counting as independent.

## 79. Two Sum II — Input Array Is Sorted

Recall 2026-07-28 (L1 pass): Independently wrote the correct endpoint-pointer scan over the sorted array: increase `left` when the sum is too small, decrease `right` when it is too large, and return one-based indices on equality. The concise explanation that sorted order makes moving left increase the sum was sufficient when read together with the symmetric code; a longer verbal elimination proof was not required. The loop is `O(n)` time and uses `O(1)` auxiliary space.

## 80. 3Sum

Prior learning-status trail: 🟡 brute force correct, redo with sorted two-pointer pattern; ✅ redo guided pattern completed; ✅ redo completed. Recall initially returned to sorted backtracking. After correcting recursive index progression and the size-three return, that model correctly enumerates distinct triplets but takes O(n³), which is too slow for n up to 3000. Guided optimization fixed each distinct first value and reduced the suffix to a two-value target search with left/right pointers. The suffix search must enumerate every unique pair rather than stop after the first; on a match, record it, move both pointers, and skip duplicates on both sides, while the outer loop also skips duplicate fixed values. Because the array is sorted, once `nums[i] > 0`, every remaining candidate is positive and no zero-sum triplet is possible. O(n²) time and O(1) pointer workspace excluding the result and sorting implementation workspace.

## 81. Container With Most Water

Clean independent recall of the two-boundary scan: initialize at both ends, compute `(right-left) × min(height[left],height[right])`, update the maximum, and move a shortest boundary inward. Elimination proof: if `height[left] <= height[right]`, then for every closer `right'`, width is smaller and the usable height is still at most `height[left]`, so keeping that left boundary cannot improve the current area; the symmetric argument handles the right boundary. When heights tie, either boundary is limiting and either may move safely. O(n) time and O(1) auxiliary space.

## 82. Trapping Rain Water

Prior learning-status trail: 🟡 solved with guidance / redo later; ✅ clean recall redo done. Independently recalled the correct per-index formula `min(leftMax[i], rightMax[i]) - height[i]` and an O(n)-time prefix/suffix-boundary solution, but that version uses O(n) auxiliary space. The O(1)-space model required guidance: maintain two pointers with scalar `leftMax` and `rightMax`; when `leftMax <= rightMax`, the known right boundary is already at least as high as the left boundary, so the left position is finalized by `leftMax`, and symmetrically finalize the right position when `rightMax < leftMax`. Update the chosen side's maximum before adding `max - height`, then move only that pointer. O(n) time and O(1) auxiliary space. Redo the smaller-boundary safety proof and loop independently after spacing.

## 83. Merge Intervals

Prior learning-status trail: 🟡 current version may pass, redo with result-list / last-merged pattern; ✅ redo guided pattern completed; ✅ clean redo done. Recalled the last-merged-container scan, overlap check `current.start <= last.end`, and merge/non-overlap actions, but initially omitted sorting by ascending start and the empty-input guard. Without sorting, even disjoint `[[5,6],[1,2]]` is merged incorrectly. Correct invariant: the last result interval is the merged union of the current overlapping run, while all earlier result intervals are finalized and disjoint. Sorting costs O(n log n); the scan, O(1)-time pushes, and any final container conversion each cost O(n), so total time is O(n log n), not an additional O(n log n) for stack insertion. The proposed separate stack plus copied result uses O(n) auxiliary space in addition to O(n) returned output; appending directly to the result list avoids the extra stack. Redo sorting, empty input, and the result-list skeleton independently after spacing.

## 84. Insert Interval

Prior learning-status trail: 🟡 solved with 3-phase interval pattern after guidance; ✅ clean recall redo done.

## 85. Non-overlapping Intervals

Prior learning-status trail: 🟡 first attempt over-modelled with heap; ✅ greedy interval pattern completed after correction. Recalled a start-sorted scan but initially maintained a stack of active overlaps and chose between overlapping intervals by shorter length. Length is not the safe criterion: `[9,11]` is shorter than `[1,10]` but ends later and can block `[10,12]`. Guided correction reached the scalar boundary invariant: `retainedEnd` is the smallest possible ending time after making the minimum removals through the processed prefix. If `current.start >= retainedEnd`, retain current and update the boundary; otherwise one interval must be removed, increment the count, and keep `min(retainedEnd,current.end)`. The smaller end leaves at least as much room for every future interval, so it cannot reduce future compatibility. Sorting is O(n log n), scanning is O(n), and the scan uses O(1) auxiliary space excluding sorting workspace.

## 86. Meeting Rooms

Recall 2026-07-28 (L1 pass): Independently selected sorting by start time and rejecting when the current start is strictly less than the previous accepted end. Initially used an unnecessary stack, then reduced the scan to a scalar `lastEndTime`; because the processed prefix is already non-overlapping, only the immediately previous end can constrain the next interval. Added the initially omitted fewer-than-two-meetings guard and correctly treated end-equals-start as non-overlapping. Time is `O(n log n)` for sorting plus an `O(n)` scan. The scan uses `O(1)` auxiliary space; total sorting workspace depends on the chosen sort and is `O(1)` only under an explicitly in-place constant-workspace sort.

## 87. Meeting Rooms II

Prior learning-status trail: 🟡 correct O(n²) active-overlap solution; redo optimal min-heap version; ✅ optimal min-heap solution implemented after brute-force intuition. On recall, initially sorted by end time and used a stack, which cannot maintain the active set: starts must be processed chronologically, and an expired earlier meeting can be trapped beneath a later-ending stack top. Guided correction reached the standard model: sort by start time and keep active end times in a min-heap. Before inserting the current meeting, remove every end time `<= currentStart`; equality matters because a room is reusable when one meeting ends exactly as another begins. After insertion, the heap contains exactly the meetings active at the current start, so its maximum size is the maximum simultaneous overlap and the minimum room count. Sorting costs O(n log n); all heap operations cost O(n log k) for maximum active count k ≤ n, giving O(n log n) total time. Heap space is O(k), or O(n) worst case, plus sorting workspace.

## 88. MinimumIntervalToIncludeEachQuery

Prior learning-status trail: ✅ brute force understood; 🟡 optimal heap sweep not owned; ✅ optimal heap sweep implemented; 🟡 redo once later for ownership. Recall implementation completed after correcting the key invariant: process queries in sorted order, add every interval once when `left <= query`, maintain a size-ordered heap, and lazily remove expired heap-top intervals when `right < query`. Initial recall missed equality on interval start and reversed the containment check, then corrected both.

The independent L3 pass on 2026-07-17 establishes current ownership; the
current `Learning Status` is therefore `✅`.

## 89. Minimum Number of Arrows to Burst Balloons

Prior learning-status trail: ✅ correct greedy sort-by-end solution; 🟡 concept was hard; redo once later from memory.

Recall 2026-07-27 (L1 review): Proposed sorting by start and maintaining active intervals in a min-end priority queue. The grouping approach became correct after independently changing the split test from `minEnd <= currentStart` to the strict closed-interval condition `minEnd < currentStart`. The attempt did not recover that the queue can be replaced by the scalar intersection boundary `minEnd`, nor the greedy commitment and global-optimality proof; Harshit explicitly requested the missing model. Redo the scalar invariant and proof independently.

## 90. Maximum Subarray

Prior learning-status trail: ✅ core Kadane transition understood; 🟡review again; ✅. Recall first asked whether prefix sums applied; a minimum-prefix formulation is valid, but the intended scalar recurrence was not independently recovered. The initial update compared `current` with the previous ending sum rather than with `previousEndingSum + current`, failing on `[2,-1,2]`. Guided examples established the contract `bestEndingHere = max(current, previousBestEndingHere + current)`: every non-empty subarray ending at the current index either starts there or extends a subarray ending immediately before it, and among extensions only the largest previous ending sum can matter because every candidate receives the same current value. A separate global best is required because the optimal subarray may end earlier, as in `[5,-10,1]`. Final safe implementation initializes both scalars from `nums[0]`, scans from index one, and correctly handles all-negative input. O(n) time and O(1) auxiliary space. Redo the recurrence and proof independently after spacing.

## 91. Jump Game

Prior learning-status trail: ✅ brute-force recursion model correct; 🟡 conceptually tricky, short code, needs pattern installation. Recursive solution worked but inefficient. Do a pass from 0 to n-1, keep updating the farthest reachable index. If during iteration you reach an index i greater than farthest reachable index then return false.

## 92. Jump Game II

Prior learning-status trail: ✅ DP/BFS-style solution done; ✅ greedy range-boundary solution implemented; 🟡 not installed. Recovered a valid range-by-range BFS-compression skeleton after guidance. Initial recall incremented the jump count whenever the farthest reach improved and selected the largest raw `nums[i]`; corrected to scan every index in the current reachable layer, maximize the absolute reach `i + nums[i]`, and commit exactly one jump after the complete layer. The chosen initialization (`steps = 1`, first range `[1..nums[0]]`) is valid only with an explicit `n == 1` return of zero; the alternative zero-layer initialization avoids that special case. Each index belongs to one scanned range, so O(n) time and O(1) extra space. Redo boundary initialization and the BFS-layer minimality proof independently.

## 93. Gas Station

Prior learning-status trail: ✅ brute force; 🟡 greedy pattern not installed. Recovered the one-pass candidate-reset model with guidance, then independently wrote a correct full implementation. Initial local-choice model chose the station with maximum immediate surplus; later revisions treated zero tank as failure, introduced an unnecessary candidate queue, and carried a failed candidate's deficit into the next candidate. Final model is correct: first ensure total gas covers total cost; maintain the tank for the current candidate; after a negative segment, eliminate every start inside that segment, move the candidate to the following station, and reset the local tank. O(n) time and O(1) extra space. Redo the failed-segment elimination proof independently before counting this as an independent pass.

## 94. Partition Labels

Prior learning-status trail: ✅ good non-greedy solution; 🟡 greedy solution read; ✅ greedy solution implemented from memory. Independently recovered a correct interval model: preprocess each character's first and last occurrence, then scan the string while tracking how many character intervals have started but not ended. A cut is safe at the earliest index where the open-interval count returns to zero, because every character seen in the current segment has exhausted all future occurrences; taking every earliest safe cut maximizes the partition count. The initial interval-sorting and overlap-merging approach was also correct but unnecessary; the final scan avoids sorting. Minor output correction: return partition lengths rather than substrings. O(n) average time across preprocessing and scanning, with O(k) auxiliary space for k distinct characters (O(1) under the 26-letter constraint).

## 95. Hand of Straights

Prior learning-status trail: ✅ greedy smallest-remaining-card solution implemented. Independently recovered the smallest-remaining-card greedy model using a frequency map plus min-heap with lazy stale-entry removal. The first helper repeatedly checked `first + 1` because it did not advance `prev`, and it polled the group-start card without decrementing its frequency; both break map/heap consistency and can reject valid later groups. Correct invariant: `frequency[x]` is the number of unconsumed copies of x, while the heap may contain stale entries but yields the smallest value with positive frequency after skipping zero-count entries. That smallest remaining card must begin a group because placing it later would require an even smaller unused predecessor, which does not exist; the following `groupSize - 1` values are forced. Across the run, at most n heap entries are polled at O(log n) each and exactly n card consumptions are checked in the map, giving O(n log n) total time and O(n) space. Bottom-up heapify is O(n); repeated insertion is O(n log n).

## 96. Merge Triplets to Form Target Triplet

Prior learning-status trail: ✅ correct greedy feasibility solution. Correctly identified that any triplet exceeding the target in any coordinate is permanently unsafe because coordinate-wise maximum cannot decrease that coordinate. Initial recall incorrectly searched for a pair of safe triplets, missing that repeated binary merges can accumulate values from any number of triplets; `[3,1,1]`, `[1,3,1]`, and `[1,1,3]` are all required for target `[3,3,3]`. After correction, recovered the one-pass three-flag model: among safe triplets, record whether each target coordinate is matched, and return true when all three are covered. Sufficiency: every participating coordinate is at most its target and at least one equals it, so the coordinate-wise maximum equals the target exactly. O(n) time and O(1) auxiliary space. Redo independently after spacing.

## 97. Valid Parentheses

Correctly recalled the core waiting-stack model: push opening brackets, and for each closing bracket require the stack top to be its matching opener. Follow-up was needed for two correctness conditions: reject a closing bracket when the stack is empty before peeking/popping, and accept after the scan only when no unmatched openers remain. Each bracket is pushed and popped at most once, giving O(n) time. Worst-case stack space is O(n) for arbitrary input such as all opening brackets; `n/2` is the maximum simultaneous opener count only when restricting to a valid length-n string, and is asymptotically O(n) anyway.

## 98. Min Stack

Prior learning-status trail: 🟡 could not solve; ✅ auxiliary min-history stack solution implemented. Recalled a valid but unconventional main-stack plus deque model. The deque head stores the current minimum; a pushed value goes to the head when it is less than or equal to the head and otherwise goes to the tail. On pop, remove the deque head when it equals the popped stack value, otherwise remove the tail. The initial strict-less-than rule made duplicate minima ambiguous: after `push(2), push(1), push(1)`, both deque ends could equal the popped value, and removing the wrong end loses the remaining minimum. Using `<=` puts every new equal minimum at the head. LIFO order guarantees that before an older stack value is popped, all later values have been removed, exposing it again at its original deque end. All operations are O(1) and total auxiliary space is O(n). The model is correct, though the standard auxiliary minimum-history stack is simpler to explain.

## 99. Daily Temperatures

Prior learning-status trail: ✅ brute-force/update-all-previous solution correct; 🟡 redo with monotonic decreasing stack; ✅ redo done.

## 100. Next Greater Element I

Recall 2026-07-29 (L1 pass): Independently derived the right-to-left monotonic-stack lookup. After removing values `<= current`, the stack top is the nearest greater value to the right; each value is pushed and popped at most once. Used an index map into the `nums2`-aligned next-greater result for `nums1` lookups. Time is `O(|nums1| + |nums2|)`; auxiliary space is `O(|nums2|)` and total space including the returned output is `O(|nums1| + |nums2|)`. Covered empty inputs defensively.

Prior learning-status trail: ✅ 100th DSA roadmap problem; ✅ monotonic stack pattern connected with prior monotonic deque learning.

## 101. Car Fleet

Prior learning-status trail: 🟡 Modeling-heavy problem; ✅ Standard solution written; 🟡 Intuition not owned yet. Earlier recall: L3 ✅ 2026-06-23. Correct recall implementation after fixing the conceptual bug: a car that catches a fleet ahead merges into it; it does not pop or replace the ahead fleet's arrival time. On the 2026-07-24 recall, independently selected position sorting and arrival-time comparison, but the initial left-to-right scan propagated the faster rear car's time after a merge. Corrected to a right-to-left scan where the stored time is the arrival time of the rearmost established fleet ahead; a car with a larger solo arrival time creates a new fleet, while an equal or smaller time catches that fleet. Initialization briefly double-counted the frontmost car before being corrected. Total time is O(n log n); the reverse scan is O(n). Auxiliary space is O(1) only if paired car data can be sorted in place; building sortable pairs or indices generally uses O(n).

## 102. Largest Rectangle in Histogram

Prior learning-status trail: 🟡 standard monotonic-stack solution provided; redo later. Earlier recall: L3 review 2026-06-23. Recalled the unresolved `(height, earliestStart)` stack model and the transfer of the earliest popped start to the current shorter bar. Current recall initially calculated area using the current height rather than each popped height. When `(height, start)` is resolved at index `i`, its maximal width is `i - start` and its area is `height × (i - start)`. A virtual zero-height bar at index `n` flushes unresolved entries. Each bar is pushed once and popped once, so even roughly `2n` stack operations are O(n) worst-case amortized time; stack space is O(n).

## 103. Online Stock Span

Prior learning-status trail: 🟡 correct O(n²) scan; ✅ optimized span compression implemented after guidance. Store `(price, span)`; permanently pop every price `<= current` and absorb its compressed span. Amortized O(1) per call.

Recall 2026-07-27 (L1 review): Harshit explicitly reported that active recall was not achieved and retrieval took an unreasonably long time; do not treat the eventual reconstruction as fluent ownership. A decreasing stack of `(price, earliestIndex)` entries and the correct pop/compression control flow eventually emerged, but the first versions used an offline loop/result array rather than a coherent online return, and the ordering invariant and complexity analysis required repeated prompts. The dominance idea was substantially correct, but the amortized result followed after the push-once/pop-once fact was supplied. Redo the online contract, strict price ordering, earliest-index meaning, safe-discard argument, and `O(n)` worst-case call / `O(1)` amortized call / `O(n)` total analysis independently.

## 104. Reverse Linked List

Prior learning-status trail: 🟡 correct stack solution; ✅ O(1) pointer reversal implemented. Clean constant-space mutation order: preserve the forward link before redirecting each `next`; old head becomes the tail.

On 2026-07-27 Harshit self-reported knowing the three-pointer solution and asked to move on without demonstrating the selected L2 or the required L1 reasoning. Preserve as familiarity only; no verified recall result or cooldown.

Recall 2026-07-27 (L2 review): Harshit returned to demonstrate an implementation. Early versions failed to terminate the old head, creating a cycle, and either failed to connect or lost the final node; empty input was also not handled. After repeated trace-based feedback and a supplied uniform-loop invariant, he produced the correct non-empty loop using reversed-prefix head `A`, current node `B`, and saved `tmp`; only the leading `head == null` guard remained. Redo the standard uniform loop, invariant, edge cases, and `O(n)` time / `O(1)` auxiliary-space analysis independently.

## 105. Merge Two Sorted Lists

Self-reported familiarity on 2026-07-26; no verified recall attempt was performed. Marked recalled at Harshit's request after he reported knowing the problem. No fresh helper invariant, pseudocode, edge-case trace, or complexity explanation was demonstrated in this session, therefore it does not count as a verified recall pass. Existing solution model remains: reuse original nodes with a dummy head and merged-tail pointer, advance the list whose current node is appended, and attach the remaining suffix when one input is exhausted for O(n+m) time and O(1) auxiliary space.

## 106. Linked List Cycle

Floyd slow/fast pointers implemented without mutation. If no cycle, fast reaches null; inside a cycle, the relative distance closes modulo the cycle length. O(n) time, O(1) space.

Recall 2026-07-28 (L1 review): Independently selected slow/fast pointers and wrote correct null-safe control flow using `slow = head`, `fast = head.next.next`, one-step and two-step movement, and equality detection. The implementation correctly handles empty input, one- and two-node acyclic lists, self-cycles, and ordinary cycles. The meeting proof was not recalled: parity of the fixed node labels is irrelevant because both pointers move. Meaningful help supplied the invariant that on a cycle of length `L`, fast gains one node on slow per iteration, so their relative gap changes by one modulo `L` and becomes zero within at most `L` iterations. Time is `O(n)` and auxiliary space is `O(1)`. Redo the modular relative-gap proof independently.

## 107. Reorder List

Prior learning-status trail: ✅ correct O(n) stack solution; 🟡 O(1) pointer model recalled with guidance. Correctly recalled the three-phase constant-space model: find the middle, split and reverse the second half, then interleave. Pointer-safety details needed guidance: save `second = slow.next` before setting `slow.next = null`; during merge, drive the loop by the second-half pointer and save both suffixes at the start of every iteration before rewiring. Conditional or post-advance saves left stale pointers and could create cycles or dereference null. Reversal invariant: `prev` heads the reversed prefix, `current` heads the unprocessed suffix, and a saved next pointer preserves reachability before mutation. O(n) time and O(1) extra space.

## 108. Remove Nth Node From End of List

Recalled a valid one-pass three-pointer gap model. The initial description advanced the lead pointer by n edges from the head and stopped it at the tail, which leaves the target one node too early; it also treated the predecessor before head as null even though that pointer must move. Corrected formulation: use a real dummy predecessor, place `target` and `fast` at head, advance `fast` by `n - 1` edges, then move predecessor, target, and fast together while `fast.next != null`. At termination, target is the nth node from the end and predecessor is immediately before it, so `predecessor.next = target.next` handles head deletion uniformly and return `dummy.next`. O(length) time and O(1) auxiliary space.

## 109. Add Two Numbers

Learning history: initially guided or partial, then solved independently. Recalled digit-by-digit addition with two input pointers, carry, a dummy output head, and output tail, but the first skeleton did not advance either input pointer and would loop forever. After fixing pointer progression, the unequal-length loops were correct but the final carry was initially omitted; `99 + 1` then produced `0 → 0` instead of `0 → 0 → 1`. The final three-loop solution is correct after appending a carry node. A cleaner equivalent is one loop while either pointer or carry remains, treating absent digits as zero. Time is O(max(m,n)), auxiliary working space is O(1), and the returned list contains at most max(m,n)+1 nodes. Primitive conversion is unsafe because input lists can encode more digits than Java `int` or `long` can hold.

## 110. Copy List with Random Pointer

Independently selected the correct original-node-identity to copied-node-identity map and a two-pass construction: allocate/link the copied next-chain, then resolve every copied random pointer through the map. Duplicate values cannot serve as keys because distinct original nodes with equal values require distinct clones. The initial `getOrCreate` dereferenced null random targets; adding `getOrCreate(null) → null` fixed that, but empty input still needed an explicit top-level return before evaluating `head.next`. Complexity correction: two passes take O(n) time, while the identity map itself uses O(n) auxiliary space rather than O(1); the returned copied list is another O(n) output space. After the first pass every original node maps to exactly one clone, so `clone.random = map.get(original.random)` can reference only the corresponding clone or null, never an original node. Redo null flow and space analysis independently after spacing.

## 111. LRU Cache

Prior learning-status trail: 🟡 correct implementation after debugging; ✅ sentinel-based recall implementation. Independently recovered the HashMap + doubly linked list model and implemented it cleanly with dummy head/tail sentinels. In this version, `head.next` is LRU and `tail.prev` is MRU; every get/update detaches and appends the node before the tail. Initial explanation omitted removing an evicted key from the map, then corrected the map/list consistency invariant. Capacity-one, update-without-growth, miss-without-recency-change, and eviction cases are handled. O(1) average get/put and O(capacity) space.

## 112. Set Matrix Zeroes

Prior learning-status trail: ✅ O(m+n) space solution done; O(1) marker version optional later. Recalled the O(1)-auxiliary marker model: use each interior zero `(i,j)` to mark row `i` in `matrix[i][0]` and column `j` in `matrix[0][j]`, then mutate only after marker collection. The column-marker expression was initially mistyped, and the recall used `matrix[0][0]` to decide both the first row and first column. That single cell cannot encode two independent facts: preserve `firstRowHasZero` and `firstColumnHasZero` before marking, process the interior from indexes one onward, and mutate the first row and column last from their separate flags. Every interior zero must set both markers even if one marker was already zero. O(mn) time and O(1) auxiliary space.

## 113. Spiral Matrix

Prior learning-status trail: 🟡 visited-simulation solution likely correct; 🟡 boundary solution implemented after guidance. Correctly implemented the O(1)-extra-space shrinking-boundary traversal after guided modeling. The code handles square, rectangular, single-row, single-column, and single-cell matrices using `top < bottom` and `left < right` guards to prevent duplicate traversal. The core remaining gap is independently deriving that the unprocessed rectangle exists exactly while `top <= bottom && left <= right`; initial recall instead tried visited state and a layer-count bound. O(mn) time and O(1) extra space excluding the result.

## 114. Rotate Image

Learning history: initially guided or partial, then solved independently. Independently recalled the correct two-phase in-place model: transpose the square matrix across its main diagonal, then reverse every row. Guidance was needed to implement transposition without overwriting or swapping twice: for each row `i`, visit only `j = i+1 ... n-1` and swap `(i,j)` with `(j,i)`. Row reversal uses `left = 0`, `right = n-1`, swaps within the fixed row while `left < right`, then moves both pointers inward. The initial time count treated the phases as linear; transposition performs `n(n-1)/2` swaps and row reversal performs about `n × n/2`, so total time is O(n²). Auxiliary space is O(1), and `n = 1` naturally performs no swaps. Redo the complete loops independently after spacing.

## 115. Search a 2D Matrix

Learning history: initially guided or partial, then solved independently. Independently selected the valid two-stage model: binary-search for a candidate row, then binary-search within it. Both initial search directions were reversed. Correct row-search contract: find the first row whose last value is at least the target; if `last(mid) >= target`, retain mid and search `[left,mid]`, otherwise search `[mid+1,right]`. All earlier rows end below the target, while every later row starts above the candidate row's last value, so only the candidate can contain it. The inner search moves right when `row[mid] < target` and left when `row[mid] > target`; `[left,mid-1]` is canonical after disproving mid, though `[left,mid]` also terminates with the stated single-candidate base case. Empty matrix must be handled before accessing row zero. Time is O(log rows + log columns); because the recursive searches run sequentially, maximum stack space is O(max(log rows, log columns)).

## 116. Binary Search

Clean recursive binary search with correct empty-search-space invariant `low > high`, safe midpoint, and inclusive bounds.

Recall 2026-07-27 (L2 review): First wrote correct recursive binary search, then placed the same recursive returns inside a `while` loop. After feedback that an iterative search must mutate its bounds rather than recurse, produced the correct inclusive iterative control flow and stated the candidate-range invariant, `O(log n)` time, and `O(1)` space. The elimination explanation relied only on `mid` being unequal and did not use sorted order to exclude the entire discarded half; Harshit chose to move on rather than complete that proof. Redo the iterative state transitions, sorted-order elimination proof, and explicit empty/single-element behavior independently.

## 117. Search in Rotated Sorted Array

Learning history: initially guided or partial, then solved independently. Initial pivot + translated-index model was logically valid but used O(n) pivot scan. Redone with one-pass sorted-half elimination: at each mid, one side must be sorted; use value range checks to decide which half can still contain target. Boundary issue around `mid - 1` corrected by comparing against `nums[mid]` after checking equality first.

Recall 2026-07-27 (L1 review): Harshit began with the correct one-pass sorted-half direction but initially discarded a half using only comparison with `nums[mid]`, which failed the prompt example. After a counterexample, he added the full target-range tests. The target equality check then occurred after the sorted-half branches and could discard an exact midpoint match; after that was identified, he moved it first. A strict `nums[mid] > nums[left]` test still failed to advance on the two-element rotation `[3,1]`; after a focused boundary prompt, he changed it to the inclusive left-sorted test and produced correct control flow. Redo the equality-first order, inclusive sorted-half detection, and complete containment tests independently.

## 118. Find Minimum in Rotated Sorted Array

Learning history: initially guided or partial, then solved independently. Recall repeatedly compared `nums[left]` with `nums[right]`. That comparison can detect an already sorted interval, in which case `nums[left]` may be returned immediately, but it does not locate which side of `mid` contains the rotation; `[5,1,2,3,4]` is a counterexample to always searching right when the endpoints indicate rotation. Guided correction restored the answer-in-range invariant: compare `nums[mid]` with `nums[right]`. If `nums[mid] > nums[right]`, the minimum is strictly right of `mid`, so recurse on `[mid + 1, right]`; otherwise the minimum is at `mid` or to its left, so retain `mid` and recurse on `[left, mid]`. The asymmetric ranges both preserve the answer and guarantee progress; `[mid, right]` can repeat forever for a two-element interval. Base case `left == right` returns `nums[left]`. O(log n) time and O(log n) recursive stack space, or O(1) auxiliary space iteratively. Redo independently after spacing.

## 119. Time Based Key-Value Store

Learning history: initially guided or partial, then solved independently. Correct data model: `Map<String, List<(value, timestamp)>>` with per-key timestamps increasing. Final guided solution uses floor search / rightmost-valid pattern: when `timestamp <= query`, store candidate answer and move right; otherwise move left.

Recall history:

- 2026-07-28 — L1 pass. Independently recalled the per-key ordered-history model and logarithmic lookup. The first draft left the candidate index unset when there was no exact timestamp; after that concrete defect was identified, Harshit independently corrected the search so its final midpoint represents either the floor or the ceiling, using the predecessor in the latter case. Missing keys and queries before, between, exactly at, and after stored timestamps were handled. Complexity was correctly identified as amortized `O(1)` for `set`, `O(log k)` for `get` with `k` entries for the key, and `O(n)` total storage.

## 120. Koko Eating Bananas

Learning history: initially guided or partial, then solved independently. Recalled the correct candidate range `[1,maxPile]`, O(n) hours calculation for a candidate speed, and logarithmic answer search. The initial feasibility direction was reversed and treated equality as failure; correct validity is `hoursNeeded(k) <= h`, so a feasible `mid` is retained by moving the right boundary to `mid`, while an infeasible speed moves left to `mid + 1`. The special case `h == piles.length` returns `maxPile`, not the number of piles, and speed one is valid when it takes exactly h hours. Integer ceiling is `pile / k + (pile % k == 0 ? 0 : 1)` or `(pile + k - 1) / k`. Accumulated hours require `long` because the constraint product can reach `10^13`. The invariant is that `[left,right]` always contains the minimum feasible speed. O(n log M) time for `M = maxPile` and O(1) auxiliary space.

## 121. Capacity To Ship Packages Within D Days

Learning history: initially guided or partial, then solved independently. Boundary-search-over-answer structure completed. Feasibility check counts days by preserving package order and starting a new day whenever the next package would exceed capacity. Key bound: capacity range is `[max(weights), sum(weights)]`, because the ship must carry the heaviest package and can ship all packages in one day at total capacity.

Recall 2026-07-28 (L1 review): Correctly identified a minimum-capacity search over a monotone days-needed function. The first feasibility helper started a new day after remaining capacity became `<= 0`, causing an exactly filled final package to be reprocessed indefinitely; this was corrected to trigger only on negative remaining capacity. The binary search remained materially incorrect after direct feedback: it searched from `0`/`1` instead of `max(weights)`, advanced `left` by one for an impossible capacity, treated `daysNeeded == targetDays` as an exact answer even though smaller capacities can require the same number of days, and assigned an infeasible midpoint as `bestCapacity`. The corrected rules and `[3,1,1], D=2` counterexample were supplied, but the same branches were repeated. Canonical search: bounds are `[max(weights), sum(weights)]`; `daysNeeded(capacity) <= D` is feasible, so retain `mid` and move the upper boundary left; otherwise move the lower boundary to `mid + 1`. Time is `O(n log(sum(weights) - max(weights) + 1))` and auxiliary space is `O(1)`. Redo independently.

## 123. Implement Trie

Learning history: initially guided or partial, then solved independently. Prefix path + `isWord` marker model installed. Delete pruning rule understood: after removing terminal marker, prune only nodes with no children that are not word endings. Using 256-character child array by chosen contract.

Tracker note 2026-07-28: Marked as a duplicate of core recall #20, `Trie insert/search/prefix`. Both items exercise insertion, exact-word lookup, prefix lookup, and terminal-word state, including the same `"apple"` / `"app"` distinction. This item now lives in the Outside Recall Rotation table as an alias and is never selected independently. Do not copy the core attempt into this tracker. Current recall evidence remains owned by `../core_recall.md`, where the canonical item is `L2 review 2026-07-24`.

## 124. Design Add and Search Words Data Structure

Wildcard trie DFS model installed: normal characters follow one child; `.` branches across all non-null children; base case must check `isWord` for exact-length match.

Recall 2026-07-28 (L1 pass): Independently wrote correct recursive insertion and wildcard search. Ordinary characters follow one child, `.` explores every non-null child with short-circuit success, every branch consumes exactly one pattern character, and the terminal case returns `node.isWord` for exact-length matching. Insertion and literal lookup take `O(L)` time. Harshit identified `A^W` wildcard branching; the general safe bound was refined to `O(L × A^W)` for length `L`, alphabet size `A`, and `W` wildcard positions, with the all-wildcard case commonly summarized as `O(A^L)`. Recursion uses `O(L)` stack space. Trie storage is `O(S)` worst case for `S` total inserted characters, reduced in practice by shared prefixes.

## 125. Word Search II

Recalled the correct high-level Trie + board DFS model, path-local visited restoration, terminal-word deduplication, and prefix pruning. The helper contract needed precision: `(row, col, trieNode)` represents the Trie state before consuming the current board cell; move to `nextNode = trieNode.children[c]`, check `nextNode.isWord`, and pass `nextNode` to neighbors so their character is consumed exactly once. Complexity required guidance: for `S` total dictionary characters and maximum word length `L`, build costs O(S), while adversarial board search is bounded by O(RC × 4^L) (or more tightly about `RC × 4 × 3^(L-1)`). Space is O(S + RC + L) with a visited array or O(S + L) with in-place marking, excluding results.

## 126. Plus One

Independently produced a correct right-to-left carry-propagation algorithm without integer conversion. Each processed suffix stores the correct low-order digits after adding one, while `carry` records whether one still needs to be added to the unprocessed prefix. The initial loop continued unnecessarily after carry became zero; returning immediately is cleaner because the remaining prefix is unchanged. Carry survives past index zero only when every original digit was 9, requiring a new `[1,0,...,0]` result. Worst-case O(n) time and O(1) auxiliary working space; the overflow case allocates O(n) returned-output space.

## 127. Pow(x, n)

Learning history: initially guided or partial, then solved independently. Recall required substantial guidance. Initial attempts used incorrect repeated squaring and then correct but linear multiplication; the recursive revision duplicated the same half-power subproblem, added unnecessary memoization, and briefly used the wrong `x^0` base case. The exponentiation-by-squaring recurrence was ultimately recovered: compute one half-power, square it, and multiply by the base for an odd exponent. `Integer.MIN_VALUE` handling was not recalled independently: promote `n` to `long` before negation, invert the base for a negative exponent, then recurse on the nonnegative `long` exponent. O(log abs(n)) time and O(log abs(n)) recursion space.

## 128. Number of 1 Bits

Learning history: initially guided or partial, then solved independently. Initial sign-bit-plus-magnitude model was incorrect for Java `int`; corrected to raw 32-bit two's-complement inspection using `(n & 1)` and unsigned right shift `>>>`.

## 129. Counting Bits

Correct solution using per-number 32-bit hamming weight scan. Stronger pattern to own: `ans[i] = ans[i >> 1] + (i & 1)`, reusing the already-computed count after removing the lowest bit.

Recall 2026-07-29 (L1 pass): Independently used a fixed 32-bit scan for every value from `0` through `n`: inspect the low bit with `num & 1`, count it when set, then advance with unsigned right shift `>>>`. Since the bit width is fixed, this is `O(n)` time with `O(1)` auxiliary space apart from the returned array.

## 130. Reverse Bits

Learning history: initially guided or partial, then solved independently. Initial approach over-modeled sign handling and two's-complement conversion. Correct model is raw bit transfer: repeat 32 times, append `num & 1` into `result` after left-shifting result, then unsigned-shift `num >>>= 1`.

Recall 2026-07-29 (L1 review): Began with a correct high-input-mask/low-output-mask direction but required material guidance to avoid AND-ing bits at their original positions after selecting an output position. Final corrected model scans from bit 31 to bit 0 with `mask1 = 1 << 31`, ORs the corresponding `mask2` directly when the input bit is set, and advances masks with `>>>` and `<<`. Runs in fixed `O(1)` time and `O(1)` auxiliary space. Redo independently.

## 131. Missing Number

Learning history: initially guided or partial, then solved independently. First sorting solution was correct but mutated input and used O(n log n). Redone with XOR cancellation: initialize with `n`, then XOR each index `0..n-1` and each array value so paired values cancel and the missing number remains.

Recall 2026-07-28 (L1 review): Proposed XORing only the present array values and returning that result, with a special case when the XOR was zero. This fails for `[0,1]`, whose missing value is `2` but whose present-value XOR is `1`. Harshit did not recall the missing construction after the counterexample. Meaningful help supplied the standard model: initialize `answer = n`, then for every array index `i`, XOR both `i` and `nums[i]`. This XORs the full candidate range `0..n` against all present values, so every present value appears twice and cancels while the missing value appears once. Time is `O(n)`, auxiliary space is `O(1)`, and XOR avoids arithmetic overflow. Redo independently.

## 132. Sum of Two Integers

Learning history: initially guided or partial, then solved independently. Recalled a valid fixed-width bit-by-bit full-adder model rather than the standard whole-word loop. For each of all 32 raw bits, `resultBit = bitA ^ bitB ^ carryIn`; carry-out must combine both possible sources: the bitwise OR of `(bitA & bitB)` and `((bitA ^ bitB) & carryIn)`. Initial recall incorrectly planned to stop before or separately determine the sign bit from the input signs and magnitudes. Correct two's-complement model: process bit 31 exactly like every other bit, construct the resulting 32-bit pattern, and discard overflow beyond bit 31; the sign is already encoded in the result. Fixed 32-iteration implementation is O(1) time and O(1) space. The alternative whole-word recurrence—XOR for partial sum and shifted AND for carry—is cleaner but not required for correctness.

## 133. Bit Operator Drills: check, set, clear, toggle kth bit

Practiced `&`, bitwise OR, `^`, and `~`, and kth-bit masks. Key correction: use `(n & mask) != 0` instead of `> 0`, because checking the sign bit can produce a negative mask value.

Recall 2026-07-29 (L1 review): Recalled the correct set (`|`), clear (`& ~mask`), and toggle (`^`) operations. Material guidance was needed for the zero-based mask `1 << k`, Java's lack of a `<<<` operator, and the sign-safe check `(n & mask) != 0`. All four operations run in `O(1)` time and `O(1)` space. Redo independently.

## 134. Single Number

Clean XOR cancellation solution: XOR all values; paired duplicates cancel because `a ^ a = 0`, and the single value remains because `a ^ 0 = a`.

Recall 2026-07-29 (L1 pass): Independently derived XOR reduction: equal values cancel through `a ^ a = 0`, and XOR with zero preserves the unique value. Negative values require no special handling because XOR operates on raw bit patterns. Time is `O(n)` with `O(1)` auxiliary space.

## 135. Hamming Distance

Clean independent recall of the XOR model: `x ^ y` marks exactly the bit positions where the inputs differ, then inspect the low bit with a mask and shift across all 32 positions. Java precision: prefer unsigned shift `>>>` for a raw bit pattern; signed `>>` also counts correctly when the loop is fixed at exactly 32 iterations and only the low bit is inspected. Equal inputs XOR to zero and therefore have distance zero. A fixed 32-iteration loop is O(1) time for Java `int` and uses O(1) auxiliary space.

## 136. Power of Two

Correctly recalled that a positive power of two has exactly one set bit and proposed a valid fixed 32-iteration count using low-bit masking and unsigned shifting. The intended constant-expression recall needed correction: `n & (n-1)` equals zero for a one-set-bit value, not `Integer.MAX_VALUE`, because subtracting one clears the lowest set bit and turns lower zeroes into ones. Final condition is `n > 0 && (n & (n-1)) == 0`. The positivity guard is essential because `0 & -1` is also zero and would otherwise make zero a false positive. For fixed-width Java `int`, both the 32-step count and the bit expression use O(1) time and O(1) space, though the expression is simpler.
