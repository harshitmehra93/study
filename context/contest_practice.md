# Contest Practice

This file is a lightweight history of competitive-programming contests. Keep
contest results separate from interview mixed practice and coding mocks.

## Recording Rule

Whenever Harshit presents his contest answers, do both:

1. Add or update `src/main/java/study/contest/<platform>/<contest>/`. Keep one
   descriptively named class per presented problem. Its comment contains a
   concise official question, constraints, examples, and the official link;
   its code contains Harshit's latest provided solution.
2. Add one contest-log row and a short note containing only:

- what happened during the contest;
- what was upsolved afterward;
- the next action.

When Harshit later provides a better solution, update the problem class and the
Upsolve note, but never replace the original contest result. Do not silently
substitute an editorial solution for his solution. Use `Unknown` when a contest
detail was not supplied.

## Contest Log

| ID | Date | Contest | Performance | Upsolve | Next |
| --- | --- | --- | --- | --- | --- |
| CT001 | 2026-08-01 | [AtCoder Beginner Contest 469](https://atcoder.jp/contests/abc469) | A-B correct; C correct simulation but `O(N^2)`; D incorrect; E-G not attempted | C model complete; D core reduction found but edge cases remain | Implement and verify C and D, then try E |
| CT002 | 2026-08-05 | [LeetCode Weekly Contest 513](https://leetcode.com/contest/weekly-contest-513/) — virtual | 4011 correct; 4010 and 4012 incorrect; 4013 not attempted | 4011 exact revision verified (`None`); 4010, 4012, and 4013 verified (`Major`) | Independently redo and explain 4013's ordered-prefix model after spacing |
| CT003 | 2026-08-08 | [AtCoder Beginner Contest 470](https://atcoder.jp/contests/abc470) | A-B correct; C correct simulation but `O(NQ)`; D incorrect and `O(NQ)`; E-G unknown | D implemented and verified (`Major`); C map version correct but too slow (`Major`) | Replace C's map with active-array compaction |
| CT004 | 2026-08-13 | [LeetCode Weekly Contest 419](https://leetcode.com/contest/weekly-contest-419/) — practice | 3318 incorrect (`None`); 3319 solution presented with verdict unknown; 3320-3321 and timing unknown | 3318 and 3319 verified (`None`); 3320 recurrence verified but map implementation is not constraint-safe (`None`); 3321 verified (`Major`) | Independently redo 3321's ordered-partition invariant after spacing; implement and verify 3320 with rolling DP |
| CT005 | 2026-08-16 | [LeetCode Weekly Contest 420](https://leetcode.com/contest/weekly-contest-420/) — practice | 3324 correct; 3325 incorrect; 3326 correct; 3327 and timing unknown | 3324 and 3326 verified (`None`); 3325 repaired and verified (`Major`); guided 3327 now has linear Manacher queries but recursive DFS is depth-unsafe (`Major`) | Replace 3327's recursive DFS with iterative postorder and verify the maximum chain; later independently redo 3325's linear window |
| CT006 | 2026-08-18 | [LeetCode Weekly Contest 421](https://leetcode.com/contest/weekly-contest-421/) | Q1 and Q3 close but unfinished; Q2 model incorrect; Q4 unfinished for lack of time; excessive time spent on Q1 | 3334 is verified (`Major`); 3335 frequency simulation is verified (`None`), while its alternate contribution-precompute attempt needs repair (`None`); 3336 is verified (`Major`); 3337's transition model is correct but matrix exponentiation is deliberately deferred (`None`) | Continue with a new LeetCode contest and retain the early-problem switch checkpoint |
| CT007 | 2026-08-20 | [LeetCode Weekly Contest 422](https://leetcode.com/contest/weekly-contest-422/) | 3340 correct by inspection; 3341 incorrect; 3342 not presented; 3343 incomplete and constraint-unsafe; verdicts, timing, and help unknown | 3341, 3342, and 3343 verified (`Major`) | Independently redo and explain 3343's digit-allocation DP after spacing; continue with a new contest |

## Notes

### CT001 — AtCoder Beginner Contest 469

- **Contest:** A and B were correct. C simulated the process correctly but was
  too slow. D considered only pairs already present as finalists and also
  counted duplicate finals. E through G were not attempted.
- **Upsolve:** C was reduced to the position of the `k`-th `x`, giving `O(N)`.
  D found the first-final endpoint reduction, but still needs the case where
  every final contains the fixed player and distinct-pair handling.
- **Next:** Implement and verify C and D. Then make one cold attempt on E; skip
  F and G for now.

### CT002 — LeetCode Weekly Contest 513 (virtual)

- **Contest:** 4011 was correct in `O(N^2)`. In 4010, multiplication could
  overflow before conversion to `long`, and the trial-division GCD was too slow.
  In 4012, unused shift time incorrectly restarted the task list, and a partial
  shift was not subtracted from the current task. 4013 was not attempted.
- **Upsolve:** 4011 was independently refined from floating-point ratios to
  exact cross-multiplication and verified (`None`). After Euclid's GCD and early
  `long` conversion were supplied, 4010 was implemented and passed examples,
  boundary cases, randomized comparison, and a maximum-size runtime check
  (`Major`). Two 4012 simulations remained incorrect: one reset partial task-0
  work, while the other restarted the list within the same shift and remained
  too slow. The cumulative-progress and prefix-search model was then supplied
  (`Major`). The next implementation used that model correctly, but searched
  for the first prefix `>= workDone` instead of `> workDone` and used overflowing
  `int` cumulative values. After repairing both, 4012 passed official, boundary,
  randomized, and maximum-constraint tests (`Major`). This introduced a new
  pattern for Harshit: combining prefix sums with binary search over cumulative
  completion boundaries. In the initial 4013 upsolve, independently
  transformed even values to `+b` and odd values to `-a`, correctly reducing
  the ratio condition to counting subarrays with transformed sum at most zero.
  After a nudge, derived that earlier prefixes must be greater than the current
  prefix but missed the equality case; the ordered counting structure was not
  recovered. The partial implementation used a hash map, which cannot directly
  count the required prefix inequality, and the local answer was `int` despite
  the possible quadratic count. Assessed portion closed incomplete with
  `Help=Nudge`; subsequent structure teaching is `Major`. In the 2026-08-12
  guided transfer, the intended greater-than-or-equal earlier-prefix condition
  was stated correctly and the solution used descending coordinate ranks with
  a Fenwick frequency tree. It remained incorrect because ranks were read from
  the sorted prefix array rather than the time-ordered prefix array, equal
  prefixes were excluded, and the answer remained `int`; the official examples
  returned `4, 1, 0` instead of `7, 3, 0`. After those three repairs, the latest
  submitted solution passed the official examples, 10,000 randomized cases
  against the quadratic 4011 solution, and a maximum-size all-odd case returning
  `5,000,050,000` (`Major`).
- **Next:** Independently redo and explain 4013's ordered-prefix model after
  spacing.

### CT003 — AtCoder Beginner Contest 470

- **Contest:** A and B were correct. C maintained the XOR correctly but scanned
  all `N` values for every type-2 query. D maintained both a permutation and
  its inverse, but rebuilt them on every type-2 query and printed after every
  query instead of only once at the end. E through G were not reported.
- **Upsolve:** After the coach supplied the efficient model, D was implemented
  with two inverse arrays and reference swapping, then passed all official
  samples and randomized comparison against brute force (`Major`). C's guided
  `ConcurrentHashMap` implementation passed the official samples and randomized
  correctness checks, but exceeded five CPU seconds on a smaller adversarial
  case because traversal scans the retained backing table.
- **Next:** Replace C's map with an active-index array using in-place compaction,
  then verify it.

### CT004 — LeetCode Weekly Contest 419 (practice)

- **Contest:** For 3318, the submitted solution recognized the frequency map,
  frequency/value ranking, and fixed-window structure, but it did not build a
  complete first window, keyed frequency reads by pointer indices instead of
  values, and evicted the highest-ranked heap entry. The assessed submission is
  incorrect (`None`). A solution for 3319 was also presented and preserved; its
  verdict was not reported. Timing and results for 3320 and 3321 were not reported.
- **Upsolve:** The independent 3318 upsolve repaired the first-window construction,
  value-keyed frequency updates, and heap direction. It passed the official
  examples, boundary cases, and 100,000 randomized comparisons against a
  sorting-based brute force (`Help=None`). The accepted independent 3319 upsolve
  correctly identified perfect subtrees from perfect children of equal depth and
  retained the largest `k` sizes with a bounded min-heap. It passed the
  official examples, boundary cases, and 20,000 randomized tree comparisons
  against a one-pass oracle (`Help=None`). The 3320 upsolve derived the correct
  state, score transitions, no-repeat rule, and winning base case independently.
  It passed both official examples and exhaustive brute-force comparison through
  length eight, but a cyclic length-1000 input exhausted a 256 MB heap because
  the quadratic state space was stored as boxed hash-map records. The assessed
  upsolve is incomplete (`Help=None`); subsequent rolling-DP direction is guided
  (`Major`). In 3321, the independent attempt correctly transferred the sliding
  frequency map, ranking, and `long` sum from 3318, and recognized that the
  ranking must be maintained incrementally. It still rebuilt the heap for every
  window and used reference inequality for boxed frequencies. After a nudge to
  reason about maintaining selected versus unselected entries, Harshit identified
  the remaining blocker as an uninstalled data-structure pattern. The assessed
  attempt closed incomplete (`Help=Nudge`); subsequent structure learning is
  `Major`. In the 2026-08-16 guided upsolve, the first implementation adapted the
  two-partition structure but stored partial frequencies independently in each
  side and moved individual occurrences. After whole-value global-frequency
  ownership and boundary swapping were supplied, the repaired implementation
  passed both official examples, the frequency-group boundary case, 10,000
  randomized comparisons, and a maximum-constraint overflow check (`Major`).
- **Next:** Independently redo and explain 3321's global-frequency ordered-partition
  invariant after spacing, and implement and verify primitive rolling DP for 3320.

### CT005 — LeetCode Weekly Contest 420 (practice)

- **Contest:** The presented 3324 simulation is correct and passed both official
  examples. The 3325 solution passed the official examples but is incorrect: its
  frequency-only `TreeSet` comparator collapses different characters with equal
  frequencies; for `s = "aabbc", k = 2`, it returns 7 instead of 8. The presented
  3326 solution has the correct right-to-left greedy reduction and passed all
  official examples, 20,000 randomized comparisons, and a maximum-size adversarial
  case. Actual judge verdicts, timing, and 3327 were not reported.
- **Upsolve:** The assessed submissions closed with `Help=None`. After the
  equal-frequency comparator defect and its character tiebreak were identified,
  the repaired 3325 implementation passed both official examples, the prior
  counterexample, 20,000 randomized comparisons, and a maximum-length case in
  about 1.5 seconds locally (`Major`). It is correct and constraint-safe at
  `O(n^2 log |Σ|)`; the supplied linear counting-window model was not used. The
  initial independent 3327 upsolve generated the required subtree strings
  correctly and passed both official examples plus 20,000 randomized comparisons,
  but repeated string construction and palindrome scans take `Θ(n^2)` on a chain,
  and the recursive implementation raised `StackOverflowError` on a valid
  100,000-node chain (`Help=None`). The global-postorder interval reduction and
  linear palindrome-preprocessing direction were then supplied. The guided second
  attempt correctly built the global postorder string once and represented each
  subtree by its `[enter, exit)` interval, eliminating the repeated construction.
  It remained `Θ(n^2)` because `substring` and `isPalindrome` process every
  subtree interval separately, and recursive DFS still overflowed on the maximum
  chain. The next guided revision removed the `substring` allocation and scanned
  the global string in place, but the sum of the separately scanned interval
  lengths remains `Θ(n^2)` and the recursion-depth failure remains (`Major`). The
  latest guided submission correctly adds one transformed-string Manacher pass and
  maps every subtree's `[enter,exit)` postorder interval to an `O(1)` palindrome
  query, so the algorithmic work is now linear by inspection. It still uses
  recursive DFS and therefore remains unsafe for a valid 100,000-node chain; no
  new judge verdict or timing was supplied (`Major`).
- **Next:** Replace 3327's recursive DFS with an iterative postorder traversal that
  preserves increasing child order, then verify official examples, randomized
  small trees, and the maximum chain. After spacing, independently redo 3325 in
  `O(n)` and explain its valid-start counting invariant.

### CT006 — LeetCode Weekly Contest 421 (upsolve)

- **Contest:** Q1 and Q3 were close but not completed. Q2's idea was incorrect,
  and there was not enough time to complete Q4. A disproportionate amount of the
  contest was spent on Q1, reducing time available for the remaining problems.
- **Upsolve:** The independent 3336 submission correctly models each index's
  three legal destinations: the first subsequence, the second subsequence, or
  neither. It therefore counts ordered, disjoint pairs correctly on small inputs.
  The assessed version is incomplete (`Help=None`): it explores `3^n` assignments,
  recomputes both GCDs at every leaf, stores each partial subsequence, accumulates
  into an overflowing `int`, and does not apply the required modulus.
  After the two-GCD state model was supplied, the guided revision correctly
  removed the materialized subsequences and carried only `(gcdA,gcdB)`. It is
  still incorrect because it adds one whenever an intermediate state has equal
  non-zero GCDs, counting the same chosen pair again along later skip transitions.
  It also remains exponential because repeated states are not memoized and still
  omits modular arithmetic (`Major`). The next guided revision fixed both the
  terminal-only counting and memoized-state structure. It still returns the
  never-updated field `count` instead of the recursive result, allocates GCD axes
  of length 200 although the valid value 200 is used as an index, and adds the
  three counts in `int` without applying the required modulus (`Major`). The
  latest guided revision repaired all three defects. It passed the official
  examples, the value-200 boundary, 10,000 randomized small comparisons against
  exhaustive enumeration, and a descending 200-element stress case returning
  `246467506` in about 1.3 seconds with a 256 MB heap. The same stress case raised
  `OutOfMemoryError` with a 128 MB heap because `Long[][][]` combines roughly eight
  million reference slots with separately boxed memo values (`Major`). The latest
  revision replaced boxed storage with `int[][][]`; it still passed all prior
  correctness checks and completed the descending maximum case in about 0.74
  seconds with a 128 MB heap. However, it tests zero to mean uncached even though
  zero is a valid modular result. Such states are recomputed; for example, from
  GCD state `(2,3)` with only values `6` remaining, all three choices preserve
  unequal GCDs and repeatedly generate the same zero-result subproblem. The final
  guided revision initializes primitive memo cells to `-1` and caches zero-result
  states correctly. It passed all official examples, the value-200 boundary,
  10,000 randomized comparisons, the descending 200-element stress case in about
  0.75 seconds, and `[2,3,6,6,...]` of length 200 in about 6 milliseconds with a
  128 MB heap. The upsolve is complete and constraint-safe (`Major`).
  In the subsequent independent 3335 upsolve, the submitted frequency simulation
  correctly shifts counts for `a` through `y` and sends every `z` occurrence to
  both `a` and `b`, applying the required modulus. Its `O(|s| + 26t)` time and
  `O(26)` working space are constraint-safe. It passed both official examples,
  10,000 randomized comparisons against literal string construction, and a
  maximum-size case in about 6 milliseconds locally (`Help=None`; solution timing
  not observed). A later independent alternative identified the reusable idea of
  precomputing `length[character][time]` and summing each starting character's
  contribution. The submitted recurrence is incorrect for `z`: it subtracts the
  `b` contribution instead of adding it, so `s="z", t=1` would contribute zero
  instead of two. Its static top-down fill also follows a recursion chain of
  length 100,000 and is unsafe for Java's call stack. This alternative attempt is
  incomplete (`Help=None`; timing not observed).
  The independent 3337 upsolve then generalized the same frequency transition
  correctly: every source letter distributes its count to the next `nums[i]`
  cyclic letters. It is not constraint-safe because it repeats that transition
  for every time step; with `t <= 10^9`, its `O(t * sum(nums))` work can reach
  roughly 650 billion updates. The transition passed both official examples and
  10,000 randomized small comparisons against literal expansion (`Help=None`;
  timing not observed).
  The subsequent 3334 upsolve uses independent prefix/suffix GCD and LCM folds to
  evaluate every single-element exclusion in constant time after linear
  preprocessing. The prefix/suffix structure and its boundary identities are
  correct and passed all three official examples plus 10,000 randomized
  comparisons against an `O(n^2)` remove-each-index oracle. The standard LCM
  formula was supplied immediately beforehand, so the overall attempt is
  recorded as guided (`Major`; timing not observed). The reusable exclusion
  technique is retained as Advanced Topic AT21, while this contest tracker
  remains the sole evidence owner.
- **Next:** Matrix exponentiation for 3337 is deliberately deferred because its
  current interview-preparation value does not justify the learning cost. Continue
  with a new LeetCode contest. Use a deliberate checkpoint on an early problem:
  if there is no concrete converging implementation after roughly 15-20 minutes,
  scan the next problem before investing further. The alternate 3335 precompute
  repair is optional because the verified frequency solution is already complete.

### CT007 — LeetCode Weekly Contest 422

- **Contest:** The presented 3340 solution correctly sums digits by index parity
  in linear time. The 3341 solution recursively minimizes over simple paths, but
  adds room opening times as path costs; opening times are deadlines that require
  waiting based on the current arrival time, and memoizing a cell while excluding
  the current recursion path is not a valid shortest-path state. The presented
  3343 solution enumerates distinct permutations with a frequency array. This is
  exponential for length 80, and its `int` accumulation is not kept modulo the
  required modulus. No solution was presented for 3342. Judge verdicts, timing,
  and assistance are unknown.
- **Upsolve:** The first guided 3341 revision replaces recursive path enumeration
  with Dijkstra's algorithm and correctly uses a min-priority queue, settled set,
  and distance relaxation structure. Its edge cost is still incorrect: it adds
  the destination room's opening time to the current distance instead of waiting
  until that time, and it special-cases the target's opening time as zero. For
  `moveTime = [[0,0],[0,100]]`, it returns `2` instead of `101`. The assessed
  revision is incomplete (`Major`).
  The next guided 3341 revision repairs the relaxation to
  `max(currentTime, moveTime[next]) + 1` and no longer ignores the target's
  opening time. It passed all three official examples, the locked-target case,
  and 10,000 randomized comparisons against an independent shortest-path oracle
  (`Major`; solution timing not observed). The subsequent guided 3342 transfer
  correctly alternates move costs between one and two seconds. A separate parity
  state is unnecessary on this grid because every walk to `(i,j)` has parity
  `(i+j) mod 2`. It passed all three official examples, 10,000 randomized
  comparisons against a two-parity-state oracle, and a maximum 750-by-750
  all-zero grid with the expected answer `2247`. The maximum case completed in
  about 5.4 seconds with a 256 MB heap and 6.4 seconds with a 128 MB heap locally
  (`Major`; solution timing not observed). Both upsolves are correct; primitive
  arrays would reduce 3342's substantial object-allocation overhead. The latest
  3342 revision makes that change for both distances and visited state while
  preserving the same Dijkstra relaxation. It passed the official and 10,000
  randomized checks again, and the same maximum case dropped to about 0.32
  seconds with a 128 MB heap (`Major`; solution timing not observed). The latest
  solution is correct and constraint-safe.
  The guided 3343 upsolve correctly replaces permutation enumeration with a
  memoized state over the current digit, even-index sum, and remaining even
  slots. It derives the processed odd sum and remaining odd slots, precomputes
  binomial coefficients modulo the required modulus, tries every split of a
  digit's frequency between the two parities, and multiplies the independent
  even-slot and odd-slot placement counts. An initially pasted subtraction sign
  was clarified as a formatting error rather than the implemented operator. The
  corrected preserved solution passed all three official examples, 10,000
  randomized small strings against distinct-permutation brute force, an
  80-identical-digit case, and an 80-digit mixed case in about 2 milliseconds
  locally with a 128 MB heap (`Major`; solution timing not observed). It is
  correct and constraint-safe; the unused contribution variable and factorial
  helpers are optional cleanup.
- **Next:** Independently redo and explain 3343's digit-allocation state and
  multiplicative slot-counting invariant after spacing. Continue with a new
  contest.
