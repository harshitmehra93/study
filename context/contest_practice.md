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
| CT002 | 2026-08-05 | [LeetCode Weekly Contest 513](https://leetcode.com/contest/weekly-contest-513/) — virtual | 4011 correct; 4010 and 4012 incorrect; 4013 not attempted | None yet | Fix 4010 and 4012, then try 4013 |
| CT003 | 2026-08-08 | [AtCoder Beginner Contest 470](https://atcoder.jp/contests/abc470) | A-B correct; C correct simulation but `O(NQ)`; D incorrect and `O(NQ)`; E-G unknown | D implemented and verified (`Major`); C guided implementation incorrect | Repair and verify C |

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
- **Upsolve:** None yet.
- **Next:** Fix 4010 with Euclid's GCD and `long` multiplication. Upsolve 4012,
  then attempt 4013.

### CT003 — AtCoder Beginner Contest 470

- **Contest:** A and B were correct. C maintained the XOR correctly but scanned
  all `N` values for every type-2 query. D maintained both a permutation and
  its inverse, but rebuilt them on every type-2 query and printed after every
  query instead of only once at the end. E through G were not reported.
- **Upsolve:** After the coach supplied the efficient model, D was implemented
  with two inverse arrays and reference swapping, then passed all official
  samples and randomized comparison against brute force (`Major`). C's guided
  implementation used a map of positive values, but removed entries during
  enhanced iteration and did not store surviving decrements.
- **Next:** Repair and verify C using an active-index array with in-place
  compaction.
