# Mixed / Unseen Problem Practice

This file owns mixed-practice behavior and state. Do not repeat unused candidate
identities or order in learner-facing output; the repository remains visible,
so rescreen exposure. The dated source audit is in
`history/audits/G30-2026-07-29.md`.

## Run Protocol

Mixed practice asks whether Harshit can solve an unfamiliar, unlabeled problem
under interview conditions. It is not recall or a formal mock.

1. Use the next eligible first-ten entry or replacement; afterward choose any
   unused `Ready` candidate.
2. Recheck source, novelty, structural duplication, and fit for one exact
   timebox, usually 35-40 minutes, using compact current exposure trackers.
   Exclude and replace a known or recognized candidate without scoring it.
3. Present only a self-contained paraphrase, examples, constraints, and a
   function signature when useful. Hide the title, topic, difficulty, company
   tag, selection reason, and hints.
4. Let Harshit drive. Clarify the statement freely, but record any substantive
   help honestly.
5. Close at the deadline under the shared attempt boundary, then record the
   frozen result once. Later work goes only in the Reattempt Log.

Use the shared help scale in `context/startup_protocol.md`; only `Help=None`
can establish independence. Keep outcome, help, and timing separate.

Use these outcomes independently of help and timing:

| Outcome | Meaning |
| --- | --- |
| `Pass` | The final model, implementation, validation, explanation, and complexity are substantially correct. |
| `Partial` | A viable model was reached, but execution or validation remained materially incomplete or incorrect. |
| `Miss` | No viable interview-acceptable solution was reached. |

Record timing as `deadline / model selection / total`, using `unknown` when
unobserved. Keep the gap and next action in plain language.

## Candidate Pool

`Source Check` is the dated catalog tier; reverify it before selection. Only
`Ready` is selectable. Other states are `Held — reason`, `Consumed — Mixed #N`,
`Consumed — Mock #N`, or `Excluded — reason`.

| ID | Problem | Source Check | Difficulty | State |
| ---: | --- | --- | --- | --- |
| G01 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | 30d @ 2026-07-29 | Medium | Ready |
| G02 | [Decode String](https://leetcode.com/problems/decode-string/) | 30d @ 2026-07-29 | Medium | Ready |
| G03 | [Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/) | 30d @ 2026-07-29 | Medium | Ready |
| G04 | [Word Break](https://leetcode.com/problems/word-break/) | 30d @ 2026-07-29 | Medium | Ready |
| G05 | [Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/) | 30d @ 2026-07-29 | Medium | Ready |
| G06 | [Next Permutation](https://leetcode.com/problems/next-permutation/) | 30d @ 2026-07-29 | Medium | Ready |
| G07 | [Candy](https://leetcode.com/problems/candy/) | 3m-only @ 2026-07-29 | Hard | Ready |
| G08 | [Maximum XOR of Two Numbers in an Array](https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/) | 30d @ 2026-07-29 | Medium | Ready |
| G09 | [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) | 30d @ 2026-07-29 | Medium | Ready |
| G10 | [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) | 6m-only @ 2026-07-29 | Hard | Ready |
| G11 | [Minimum Cost For Tickets](https://leetcode.com/problems/minimum-cost-for-tickets/) | 30d @ 2026-07-29 | Medium | Ready |
| G12 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | 30d @ 2026-07-29 | Medium | Excluded — advanced exposure |
| G13 | [Smallest Subsequence of Distinct Characters](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/) | 30d @ 2026-07-29 | Medium | Ready |
| G14 | [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/) | 3m-only @ 2026-07-29 | Medium | Ready |
| G15 | [Sort an Array](https://leetcode.com/problems/sort-an-array/) | 30d @ 2026-07-29 | Medium | Ready |
| G16 | [Find Peak Element](https://leetcode.com/problems/find-peak-element/) | 30d @ 2026-07-29 | Medium | Ready |
| G17 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | 30d @ 2026-07-29 | Medium | Ready |
| G18 | [Sort Colors](https://leetcode.com/problems/sort-colors/) | 3m-only @ 2026-07-29 | Medium | Ready |
| G19 | [Find Original Array From Doubled Array](https://leetcode.com/problems/find-original-array-from-doubled-array/) | 30d @ 2026-07-29 | Medium | Ready |
| G20 | [Random Pick with Weight](https://leetcode.com/problems/random-pick-with-weight/) | 30d @ 2026-07-29 | Medium | Ready |
| G21 | [Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/) | 30d @ 2026-07-29 | Hard | Ready |
| G22 | [Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) | 6m-only @ 2026-07-29 | Hard | Ready |
| G23 | [Basic Calculator](https://leetcode.com/problems/basic-calculator/) | 3m-only @ 2026-07-29 | Hard | Ready |
| G24 | [Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/) | 6m-only @ 2026-07-29 | Hard | Ready |

G12 was directly exposed while installing AT07 and is excluded from the mixed
pool. Verify AT07 independently on a different pool-safe representative.

Aim for at least 10 `Ready` candidates. Replenish before the pool would fall
below 8; do not weaken novelty, fit, or source verification merely to increase
the count.

## First 10 Schedule

| Attempt | Candidate |
| ---: | ---: |
| 1 | G01 |
| 2 | G02 |
| 3 | G03 |
| 4 | G04 |
| 5 | G05 |
| 6 | G06 |
| 7 | G07 |
| 8 | G08 |
| 9 | G09 |
| 10 | G10 |

For each fresh-attempt number, use its scheduled candidate only when `Ready` and
unused; otherwise use an unused `Ready` candidate of the same difficulty.
Preserve 8 Medium / 2 Hard, no Hard in the first three, and no consecutive
Hards. Replenish if no safe replacement exists.

## Updating State

After a fresh attempt, append its immutable row and set `Consumed — Mixed #N`.
Use `Excluded — reason` without a row for failed screening, `Held — reason` for
an unresolved dependency, and `Consumed — Mock #N` when a coding mock owns the
attempt.

Create a learning handoff only for a reusable technique or repeated transfer
gap. Never copy attempt evidence into another tracker.

## Fresh Attempt Log

| # | Date | Candidate | Outcome | Help | Timing | Gap / Next Action |
| ---: | --- | ---: | --- | --- | --- | --- |

## Reattempt Log

Reattempts never enter fresh-result metrics.

| Candidate | Date | Outcome | Help | Timing | Gap / Next Action |
| ---: | --- | --- | --- | --- | --- |

## Calibration

At each 10-attempt checkpoint, use all fresh attempts to date, including misses
and unknown-technique problems. Report:

* raw pass rate: `Pass / all fresh attempts`
* unassisted rate: `Help None / all fresh attempts`
* within-time rate: `observed inside timebox / all fresh attempts`, with the
  unknown-time count shown
* the most common plain-language primary gaps

Raise difficulty only when correct, unassisted, within-time implementations are
consistent.
