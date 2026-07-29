# Mixed / Unseen Problem Practice

This file owns both the mixed-practice protocol and its private live state.
Never display the candidate pool, held items, schedule rationale, or expected
solution direction during an attempt.

Current state, 2026-07-30: **23 Ready** candidates (17 Medium, 6 Hard), **1
Held**, and **0 fresh attempts**. The dated source audit is preserved in
`history/audits/G30-2026-07-29.md`.

## Run Protocol

Mixed practice asks whether Harshit can solve an unfamiliar, unlabeled problem
under interview conditions. It is not recall or a formal mock.

1. Use the next unused candidate in the First 10 Schedule; afterward choose a
   `Ready` candidate.
2. Before presenting it, confirm that the source/tag is still verified, the
   problem is unseen and not a structural duplicate, and it fits a roughly
   35-40 minute Java interview without an obscure or uninstalled trick.
3. If Harshit recognizes it before meaningful work, mark it `Excluded`, do not
   score it, and choose another.
4. Present only a self-contained paraphrase, examples, constraints, and a
   function signature when useful. Hide the title, topic, difficulty, company
   tag, selection reason, and hints.
5. Let Harshit drive. Clarify the statement freely, but record any substantive
   help honestly.
6. Record the result once in the Fresh Attempt Log. Later work goes only in the
   Reattempt Log.

Use the shared help scale:

| Help | Meaning |
| --- | --- |
| `None` | No meaningful algorithmic help; statement clarification is allowed. |
| `Nudge` | A small directional question, observation, or counterexample. |
| `Major` | The representation, algorithm, data structure, invariant, recurrence, pseudocode, or equivalent structure was supplied. |
| `Unknown` | The record is too incomplete to judge help. |

Use these outcomes independently of help and timing:

| Outcome | Meaning |
| --- | --- |
| `Pass` | The final model, implementation, validation, explanation, and complexity are substantially correct. |
| `Partial` | A viable model was reached, but execution or validation remained materially incomplete or incorrect. |
| `Miss` | No viable interview-acceptable solution was reached. |

Record observed model-selection and total minutes; otherwise use `unknown`.
Write the primary gap in plain language rather than forcing a taxonomy.

## Candidate Pool

`Verified` is the most recent source check, not a permanent guarantee. `Held`
items are not selectable until their handoff is resolved.

| ID | Problem | Link | Tier | Difficulty | State | Verified |
| ---: | --- | --- | --- | --- | --- | --- |
| G01 | Longest Consecutive Sequence | [LeetCode](https://leetcode.com/problems/longest-consecutive-sequence/) | 30d | Medium | Ready | 2026-07-29 |
| G02 | Decode String | [LeetCode](https://leetcode.com/problems/decode-string/) | 30d | Medium | Ready | 2026-07-29 |
| G03 | Single Element in a Sorted Array | [LeetCode](https://leetcode.com/problems/single-element-in-a-sorted-array/) | 30d | Medium | Ready | 2026-07-29 |
| G04 | Word Break | [LeetCode](https://leetcode.com/problems/word-break/) | 30d | Medium | Ready | 2026-07-29 |
| G05 | Maximum Width of Binary Tree | [LeetCode](https://leetcode.com/problems/maximum-width-of-binary-tree/) | 30d | Medium | Ready | 2026-07-29 |
| G06 | Next Permutation | [LeetCode](https://leetcode.com/problems/next-permutation/) | 30d | Medium | Ready | 2026-07-29 |
| G07 | Candy | [LeetCode](https://leetcode.com/problems/candy/) | 3m-only | Hard | Ready | 2026-07-29 |
| G08 | Maximum XOR of Two Numbers in an Array | [LeetCode](https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/) | 30d | Medium | Ready | 2026-07-29 |
| G09 | Product of Array Except Self | [LeetCode](https://leetcode.com/problems/product-of-array-except-self/) | 30d | Medium | Ready | 2026-07-29 |
| G10 | Alien Dictionary | [LeetCode](https://leetcode.com/problems/alien-dictionary/) | 6m-only | Hard | Ready | 2026-07-29 |
| G11 | Minimum Cost For Tickets | [LeetCode](https://leetcode.com/problems/minimum-cost-for-tickets/) | 30d | Medium | Ready | 2026-07-29 |
| G12 | Subarray Sum Equals K | [LeetCode](https://leetcode.com/problems/subarray-sum-equals-k/) | 30d | Medium | Held — AT07 | 2026-07-29 |
| G13 | Smallest Subsequence of Distinct Characters | [LeetCode](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/) | 30d | Medium | Ready | 2026-07-29 |
| G14 | Insert Delete GetRandom O(1) | [LeetCode](https://leetcode.com/problems/insert-delete-getrandom-o1/) | 3m-only | Medium | Ready | 2026-07-29 |
| G15 | Sort an Array | [LeetCode](https://leetcode.com/problems/sort-an-array/) | 30d | Medium | Ready | 2026-07-29 |
| G16 | Find Peak Element | [LeetCode](https://leetcode.com/problems/find-peak-element/) | 30d | Medium | Ready | 2026-07-29 |
| G17 | Group Anagrams | [LeetCode](https://leetcode.com/problems/group-anagrams/) | 30d | Medium | Ready | 2026-07-29 |
| G18 | Sort Colors | [LeetCode](https://leetcode.com/problems/sort-colors/) | 3m-only | Medium | Ready | 2026-07-29 |
| G19 | Find Original Array From Doubled Array | [LeetCode](https://leetcode.com/problems/find-original-array-from-doubled-array/) | 30d | Medium | Ready | 2026-07-29 |
| G20 | Random Pick with Weight | [LeetCode](https://leetcode.com/problems/random-pick-with-weight/) | 30d | Medium | Ready | 2026-07-29 |
| G21 | Regular Expression Matching | [LeetCode](https://leetcode.com/problems/regular-expression-matching/) | 30d | Hard | Ready | 2026-07-29 |
| G22 | Maximum Profit in Job Scheduling | [LeetCode](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) | 6m-only | Hard | Ready | 2026-07-29 |
| G23 | Basic Calculator | [LeetCode](https://leetcode.com/problems/basic-calculator/) | 3m-only | Hard | Ready | 2026-07-29 |
| G24 | Longest Increasing Path in a Matrix | [LeetCode](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/) | 6m-only | Hard | Ready | 2026-07-29 |

For held G12, install AT07 with a different representative, verify it
independently, and then recheck G12 for novelty and interview fit before making
it `Ready`.

Aim for at least 10 `Ready` candidates. Replenish before the pool would fall
below 8; do not weaken novelty, fit, or source verification merely to increase
the count.

## First 10 Schedule

| Attempt | Candidate | Difficulty |
| ---: | ---: | --- |
| 1 | G01 | Medium |
| 2 | G02 | Medium |
| 3 | G03 | Medium |
| 4 | G04 | Medium |
| 5 | G05 | Medium |
| 6 | G06 | Medium |
| 7 | G07 | Hard |
| 8 | G08 | Medium |
| 9 | G09 | Medium |
| 10 | G10 | Hard |

The next slot is the lowest numbered candidate absent from the Fresh Attempt
Log. The schedule remains 8 Medium / 2 Hard, with no Hard in the first three
and no consecutive Hards.

## Updating State

After a fresh attempt, append its immutable log row and change the candidate
state to `Attempted`. A recognized, stale, or invalid candidate becomes
`Excluded` without an attempt row. Keep `Held` for unresolved technique or
scope dependencies.

Promote work into `questions.md` only for a reusable missing technique,
fundamental invariant, or repeated transfer gap; never for an isolated miss.

## Fresh Attempt Log

| # | Date | Candidate | Problem | Outcome | Help | Model Time | Total Time | Primary Gap | Revisit | Key Takeaway |
| ---: | --- | ---: | --- | --- | --- | --- | --- | --- | --- | --- |

## Reattempt Log

Reattempts never replace or enter the fresh-result metrics.

| Candidate | Problem | Date | Outcome | Help | Model Time | Total Time | Primary Gap | Key Takeaway |
| ---: | --- | --- | --- | --- | --- | --- | --- | --- |

## Calibration

After every 10 fresh attempts, use all ten results—never remove misses or
unknown-technique problems. Report:

* raw pass rate: `Pass / all fresh attempts`
* unassisted rate: `Help None / all fresh attempts`
* within-time rate: `observed inside timebox / all fresh attempts`, with the
  unknown-time count shown
* the most common plain-language primary gaps

Raise difficulty only when correct implementations and within-time completion
are consistent, not merely when the high-level model is recognized.
