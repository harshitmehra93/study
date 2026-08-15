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
| CT005 | 2026-08-16 | [LeetCode Weekly Contest 420](https://leetcode.com/contest/weekly-contest-420/) — practice | 3324 correct; 3325 incorrect; 3326 correct; 3327 and timing unknown | Post-contest review verified 3324 and 3326 (`None`); 3325 repaired and verified (`Major`) | Independently redo 3325 with the linear counting window after spacing |

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
  `O(n^2 log |Σ|)`; the supplied linear counting-window model was not used.
- **Next:** Independently redo 3325 in `O(n)` after spacing and explain why only
  the newly added character can violate the invalid-window invariant, and why
  adding `left` counts every valid substring ending at `right`.
