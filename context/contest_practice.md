# Contest Practice

This file is a lightweight history of competitive-programming contests. Keep
contest results separate from interview mixed practice and coding mocks.

## Recording Rule

For each contest, add one table row and a short note containing only:

- what happened during the contest;
- what was upsolved afterward;
- the next action.

Do not replace the contest result with the upsolve result. Use `Unknown` when a
detail was not supplied.

## Contest Log

| ID | Date | Contest | Performance | Upsolve | Next |
| --- | --- | --- | --- | --- | --- |
| CT001 | 2026-08-01 | [AtCoder Beginner Contest 469](https://atcoder.jp/contests/abc469) | A-B correct; C correct simulation but `O(N^2)`; D incorrect; E-G not attempted | C model complete; D core reduction found but edge cases remain | Implement and verify C and D, then try E |

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
