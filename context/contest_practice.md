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
| CT002 | 2026-08-05 | [LeetCode Weekly Contest 513](https://leetcode.com/contest/weekly-contest-513/) — virtual | 4011 correct; 4010 and 4012 incorrect; 4013 not attempted | 4011 exact revision verified (`None`); 4010 and 4012 verified (`Major`); 4013 partial upsolve (`Nudge`) | Learn and implement 4013's ordered-prefix counting step |
| CT003 | 2026-08-08 | [AtCoder Beginner Contest 470](https://atcoder.jp/contests/abc470) | A-B correct; C correct simulation but `O(NQ)`; D incorrect and `O(NQ)`; E-G unknown | D implemented and verified (`Major`); C map version correct but too slow (`Major`) | Replace C's map with active-array compaction |

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
  `Help=Nudge`; subsequent structure teaching is `Major`.
- **Next:** Install Fenwick point-frequency updates and prefix-count queries on
  a generic drill, then transfer coordinate-compressed counting to 4013 and
  verify it.

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
