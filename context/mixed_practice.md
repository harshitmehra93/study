# Mixed / Unseen Problem Practice

This file is the operating protocol and source of truth for Phase 4 candidate problems, fresh mixed attempts, and mixed-problem reattempts.

## Purpose And Boundaries

Mixed practice tests transfer:

> Can Harshit derive, explain, implement, test, and defend a solution to an unfamiliar, unlabeled problem within interview time?

It is not another syllabus or recall queue.

* Learned-problem recall belongs in `questions.md`.
* Core algorithm/data-structure recall belongs in `core_recall.md`.
* Formal mock interviews belong in `mock_interviews.md`.
* Do not copy a formal mock result into the mixed attempt log.

## Candidate Bank

The agent selects the problem. Harshit should not choose the topic.

The exclusive source universe is LeetCode Premium's current Google-tagged
catalog. LeetCode says company groupings are derived from continuously updated
user interview surveys rather than official company disclosures; see
[LeetCode's methodology](https://support.leetcode.com/hc/en-us/articles/360011985113-How-does-grouping-questions-by-company-work).

Inspect candidates in this order:

1. Google-tagged in the last 30 days.
2. Present in 3 months but not 30 days.
3. Present in 6 months but not 3 months.
4. Older Google-tagged questions only when they remain exceptionally strong
   interview problems.

Frequency orders inspection; it does not guarantee admission. `Ready` is an
assessment state, not a judgment that every other Google problem is
unimportant.

Before creating a record, check the learned bank, history, attempt logs,
Candidate Bank, both holding queues, and the uninstalled-technique audit in
`advanced_topics.md`. Reuse an existing record instead of creating a duplicate
active candidate.

Permanently exclude a candidate from mixed-transfer use when:

* it appears in `questions.md`, `context/history/question_history.md`, or
  either mixed-attempt log
* it is a structural near-duplicate of learned work: substantially the same
  state, control flow, and correctness argument with only the story changed
* Harshit already recognizes it
* it is Easy, SQL-only, domain-specific, or driven mainly by an obscure trick
* its official statement, constraints, canonical editorial solution, and
  complexity analysis cannot be verified

Do not admit a high-value candidate to `Ready` yet when:

* its full scope cannot reasonably be modeled, implemented in Java, tested,
  and defended in approximately 35-40 minutes; route it to the Extended / Mock
  Queue
* it primarily requires an important technique not represented in the
  installed roadmap; route it to the Technique Installation Queue

If the oversized problem is not valuable enough for extended work, or the
uninstalled technique is not sufficiently reusable and interview-relevant,
mark the candidate `Excluded` instead.

### Non-Ready Routing

Holding queues are private and do not count toward the 24 `Ready` candidates.
Their titles and metadata remain hidden during live attempts.

#### Technique Installation Queue

Use this queue for a strong Google problem whose primary technique is important
but not installed yet.

`advanced_topics.md` may hold the reusable technique-level audit and diagnostic
questions. This table remains the owner of problem-level holding state. Do not
copy solution directions into either a `Ready` row or a live-attempt surface.

1. Keep the held Google candidate unseen.
2. Install the technique using a different canonical representative.
3. Require an independent recall check of the technique.
4. Re-evaluate the held candidate against the normal time, novelty, and quality
   gates. Promote it to `Ready` only if it then qualifies.

| Topic ID | Candidate ID | Problem | Link | Google tier | Difficulty | State |
| -------- | -----------: | ------- | ---- | ----------- | ---------- | ----- |
|          |              |         |      |             |            |       |

Allowed states are `Held`, `Promoted`, and `Dropped`.

#### Extended / Mock Queue

Use this queue for a strong Google problem whose full version is too large or
implementation-heavy for the normal 35-40 minute H0 format.

* Run it only as explicitly unscored extended practice or under the formal
  mock protocol in `mock_interviews.md`.
* Do not copy its result into the fresh mixed-attempt log.
* If a faithful, interview-sized scope can be defined without removing the
  essential reasoning, re-evaluate that scope for `Ready`.

| ID | Problem | Link | Google tier | Difficulty | State |
| -: | ------- | ---- | ----------- | ---------- | ----- |
|    |         |      |             |            |       |

Allowed states are `Held`, `Promoted`, and `Dropped`.

If an attempted problem nevertheless exposes a genuinely important unknown
technique, record `NEW_TECHNIQUE` as a post-attempt diagnosis and exclude that
attempt from transfer-rate denominators. Create or update its technique-level
record in `advanced_topics.md`; keep problem-level holding state here. Do not
intentionally admit such a problem.

Maintain exactly 24 unattempted `Ready` candidates whenever practical:

* 18 Medium
* 6 Hard
* 0 Easy

A Hard qualifies only when it is interview-sized, has a clean canonical model,
and is not driven mainly by an obscure trick or excessive implementation.

### Quality Gate

Score every candidate from 0-2 on each dimension:

| Code | Dimension |
| --- | --- |
| `S` | Google-source confidence and recency |
| `R` | Interview realism and time fit |
| `N` | Novelty relative to learned work |
| `M` | Modeling and correctness-reasoning depth |
| `I` | Implementation and edge-case signal |
| `F` | Follow-up and optimization potential |

`2` means strong, `1` means acceptable, and `0` means the candidate fails that
dimension. Admission requires at least 10/12 with no zero. Before setting
`Ready`, verify the official statement, constraints, editorial, complexity,
Java feasibility, and meaningful edge cases.

The Google tier below is the earliest exact catalog window in which the problem
was found. Frequency is the visible Google report count in the corresponding
company interval on the official problem page. All rows were observed and
re-verified on 2026-07-29.

| ID | Problem | Link | Google tier | Visible frequency | Difficulty | Quality (`S/R/N/M/I/F`) | State |
| -: | ------- | ---- | ----------- | ----------------- | ---------- | ------------------------- | ----- |
| G01 | Longest Consecutive Sequence | [LeetCode](https://leetcode.com/problems/longest-consecutive-sequence/) | 30d | 0-3m: 23 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G02 | Decode String | [LeetCode](https://leetcode.com/problems/decode-string/) | 30d | 0-3m: 7 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G03 | Single Element in a Sorted Array | [LeetCode](https://leetcode.com/problems/single-element-in-a-sorted-array/) | 30d | 0-3m: 9 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G04 | Word Break | [LeetCode](https://leetcode.com/problems/word-break/) | 30d | 0-3m: 4 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G05 | Maximum Width of Binary Tree | [LeetCode](https://leetcode.com/problems/maximum-width-of-binary-tree/) | 30d | 0-3m: 3 | Medium | `2/2/1/2/2/2 = 11` | Ready |
| G06 | Next Permutation | [LeetCode](https://leetcode.com/problems/next-permutation/) | 30d | 0-3m: 7 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G07 | Candy | [LeetCode](https://leetcode.com/problems/candy/) | 3m-only | 0-3m: 4 | Hard | `2/2/2/2/2/2 = 12` | Ready |
| G08 | Maximum XOR of Two Numbers in an Array | [LeetCode](https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/) | 30d | 0-3m: 8 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G09 | Product of Array Except Self | [LeetCode](https://leetcode.com/problems/product-of-array-except-self/) | 30d | 0-3m: 8 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G10 | Alien Dictionary | [LeetCode](https://leetcode.com/problems/alien-dictionary/) | 6m-only | 0-6m: 3 | Hard | `2/2/1/2/2/2 = 11` | Ready |
| G11 | Minimum Cost For Tickets | [LeetCode](https://leetcode.com/problems/minimum-cost-for-tickets/) | 30d | 0-3m: 3 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G12 | Subarray Sum Equals K | [LeetCode](https://leetcode.com/problems/subarray-sum-equals-k/) | 30d | 0-3m: 15 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G13 | Smallest Subsequence of Distinct Characters | [LeetCode](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/) | 30d | 0-3m: 7 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G14 | Insert Delete GetRandom O(1) | [LeetCode](https://leetcode.com/problems/insert-delete-getrandom-o1/) | 3m-only | 0-3m: 6 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G15 | Sort an Array | [LeetCode](https://leetcode.com/problems/sort-an-array/) | 30d | 0-3m: 9 | Medium | `2/2/2/1/2/2 = 11` | Ready |
| G16 | Find Peak Element | [LeetCode](https://leetcode.com/problems/find-peak-element/) | 30d | 0-3m: 7 | Medium | `2/2/2/2/2/1 = 11` | Ready |
| G17 | Group Anagrams | [LeetCode](https://leetcode.com/problems/group-anagrams/) | 30d | 0-3m: 13 | Medium | `2/2/2/1/2/1 = 10` | Ready |
| G18 | Sort Colors | [LeetCode](https://leetcode.com/problems/sort-colors/) | 3m-only | 0-3m: 7 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G19 | Find Original Array From Doubled Array | [LeetCode](https://leetcode.com/problems/find-original-array-from-doubled-array/) | 30d | 0-3m: 4 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G20 | Random Pick with Weight | [LeetCode](https://leetcode.com/problems/random-pick-with-weight/) | 30d | 0-3m: 4 | Medium | `2/2/2/2/2/2 = 12` | Ready |
| G21 | Regular Expression Matching | [LeetCode](https://leetcode.com/problems/regular-expression-matching/) | 30d | 0-3m: 6 | Hard | `2/2/2/2/2/2 = 12` | Ready |
| G22 | Maximum Profit in Job Scheduling | [LeetCode](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) | 6m-only | 0-6m: 3 | Hard | `1/2/2/2/2/2 = 11` | Ready |
| G23 | Basic Calculator | [LeetCode](https://leetcode.com/problems/basic-calculator/) | 3m-only | 0-3m: 2 | Hard | `1/2/2/2/2/2 = 11` | Ready |
| G24 | Longest Increasing Path in a Matrix | [LeetCode](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/) | 6m-only | 0-6m: 7 | Hard | `2/2/1/2/2/2 = 11` | Ready |

Initial acceptance audit on 2026-07-29:

* 24/24 were visible in the authenticated Google catalog: 17 in 30d, 4 in
  3m-only, and 3 in 6m-only.
* 24/24 had an official statement, constraints, editorial, complexity
  analysis, and a feasible Java implementation.
* 24/24 passed the 10/12 gate with no zero; IDs, titles, and links are unique.
* 0 exact or structural overlaps remained after comparison with the learned
  bank, history, core inventory, and mixed logs.
* 6/6 Hards passed the interview-sized, canonical-model, non-trick, and
  non-marathon screen.

Allowed states are `Ready`, `Attempted`, and `Excluded`. Do not store solution hints, expected patterns, or solution summaries for `Ready` problems.

If Harshit recognizes a selected problem before meaningful work begins, mark it `Excluded` for mixed practice, do not score it, and choose another candidate.

### First 10 Attempt Schedule

The first ten attempts are precommitted by opaque candidate ID so the live
attempt does not reveal a title or selection rationale.

| Attempt | Candidate ID | Difficulty |
| -: | ---: | ---------- |
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

This is 8 Medium and 2 Hard; the first three are Medium, Hards are not
consecutive, and at least six broad families are covered. The family audit is
intentionally not persisted because it would reveal solution information. The
first candidate also avoids the topic of the immediately preceding recall
attempt. Continue avoiding the immediately preceding recall or mixed topic
when replenishing or scheduling later candidates.

### Rotation

* After an attempt, mark the row `Attempted` and add a verified replacement.
* Replace Medium with Medium and Hard with Hard unless calibration changes the
  18/6 target.
* If Harshit recognizes a problem, mark it `Excluded` without scoring and
  replace it immediately at the same difficulty.
* If a `Ready` problem loses its current Google tag, mark it `Excluded`
  without scoring and replace it at the same difficulty.
* After installing or drilling a technique, privately re-screen every
  structurally affected `Ready` row. Exclude and replace a row when the new
  learning removes its novelty or makes it a near-duplicate.
* Keep 24 unattempted `Ready` rows whenever practical. Preserve `Attempted` and
  `Excluded` rows as history; the 24-row invariant applies only to `Ready`.

## Selection And Presentation

For a fresh mixed session:

1. Select one eligible `Ready` problem.
2. Reverify that it is still Google-tagged before presenting it.
3. Check recent fresh attempts to avoid obvious pattern clustering.
4. Match difficulty to current rolling transfer results.
5. Follow the precommitted schedule for attempts 1-10.
6. Avoid the topic of the immediately preceding recall or mixed attempt.

Present only interview-visible information:

* a complete, self-contained paraphrase of the problem
* examples
* constraints
* function signature when useful

Do not reveal the candidate title, topic, expected algorithm or data structure,
source category, company tag, related known problems, difficulty, frequency,
selection reason, or hints. Do not open the Candidate Bank in front of Harshit
during a live attempt; operate from the opaque candidate ID.

Scored attempts must use no LeetCode AI assistance or intelligent
autocomplete.

## Independent Attempt

The default target is approximately 35-40 minutes. Productive reasoning need not stop at exactly 40 minutes, but exceeding interview time affects calibration; do not allow an ordinary attempt to become unbounded.

During H0, Harshit drives the reasoning. The agent may clarify the statement, listen, inspect the proposed algorithm or code, point out a factual misunderstanding of the statement, and evaluate correctness.

Do not use leading checklists, disguised Socratic hints, or agent-supplied counterexamples during H0. Begin algorithmic hints only after:

* Harshit explicitly requests one, or
* an agreed dead-end or timebox checkpoint is reached.

When using the second condition, first state that further intervention will count as a hint.

Evaluate, without mechanically prompting, the following:

| Dimension | Question |
| --- | --- |
| Understanding | Are the output, constraints, and important edge cases understood? |
| Baseline | Is there a correct exhaustive or straightforward model? |
| Model / optimization | Is the relevant state, repeated work, ordering, dominance, or reduction identified? |
| Invariant / proof | Can the transition or elimination be defended? |
| Implementation | Is the model translated into correct Java code? |
| Validation | Are adversarial tests and complexity handled? |

## Hint Levels

Record the strongest meaningful assistance supplied before the fresh attempt ends.

| Hint | Meaning |
| --- | --- |
| `H0` | No meaningful algorithmic assistance. Statement clarification and repetition of an explicit constraint normally remain H0. |
| `H1` | A small observation, targeted question, or counterexample exposes the first conceptual gap without naming the solution structure. |
| `H2` | The major representation, algorithm family, or data-structure direction is revealed. |
| `H3` | The key invariant, recurrence, proof, or main algorithm is explained. |
| `H4` | Detailed pseudocode, mutation order, or code-level construction is supplied. |

If an agent observation materially changes the algorithmic direction, the attempt is not H0.

## Fresh-Attempt Status

Status records the first fresh attempt only.

| Status | Definition |
| --- | --- |
| `M0 ✅` | H0; correct target model and interview-acceptable algorithm; substantially correct implementation, validation, explanation, and complexity; completed in approximately interview time. |
| `M1 review` | H0 and the viable target algorithm was independently derived, but execution was not interview-ready because of time, implementation, debugging, validation, proof, or complexity. |
| `M2 review` | H1 was the highest hint and Harshit then reached a viable interview-acceptable solution while deriving most remaining details. |
| `M3 review` | H2 was the highest hint and Harshit then reached a viable interview-acceptable solution. |
| `M4 miss` | No viable interview-acceptable solution was reached, or H3/H4 assistance was required. This includes attempts that end unsuccessfully at H0, H1, or H2. |

If accurate timing is unavailable, record `unknown`. Do not claim the interview-time requirement of M0 solely from assumed chat elapsed time.

## Gap Classification

For M1-M4, record the first meaningful failure stage and, when useful, one cause. This keeps failure location separate from why it happened.

Stages:

| Stage | Meaning |
| --- | --- |
| `UNDERSTANDING` | The statement or an important constraint was misunderstood. |
| `BASELINE` | A correct straightforward solution could not be constructed. |
| `MODEL` | The useful representation or state was not found. |
| `OPTIMIZATION` | A correct baseline existed, but the required efficient solution was not derived. |
| `IMPLEMENTATION` | The intended algorithm did not become correct code. |
| `VALIDATION` | Testing, debugging, correctness justification, or complexity remained materially wrong. |

Optional causes:

| Cause | Meaning |
| --- | --- |
| `TRANSFER` | A learned idea was not recognized in the unfamiliar formulation. |
| `NEW_TECHNIQUE` | The important technique was not adequately represented in the existing roadmap. |
| `STATE` | The state retained the wrong information or unnecessary history. |
| `INVARIANT_PROOF` | The maintained condition or justification was missing or wrong. |
| `DATA_STRUCTURE` | The needed information was understood, but its maintaining structure was not. |
| `DEBUGGING` | A close implementation could not be repaired within the attempt. |
| `TIME` | The reasoning or execution was independently valid but too slow. |

Use `Stage / Cause`, for example `OPTIMIZATION / TRANSFER`. Use `—` when no meaningful gap exists.

Before recording a definitive optimality or `NEW_TECHNIQUE` conclusion, verify it against a reliable editorial or canonical solution when available. Otherwise preserve the uncertainty.

## Fresh Attempt Log

Keep one immutable row per fresh mixed problem. Never replace its status, hint, time, or gap with a later reattempt result.

| # | Date | Problem | Status | Hint | Model / Total Time | Primary Gap | Revisit | Key Takeaway |
| -: | ---- | ------- | ------ | ---- | ------------------ | ----------- | ------- | ------------ |
|   |      |         |        |      |                    |             |         |              |

After logging a fresh attempt, mark its Candidate Bank row `Attempted` and
replenish the `Ready` inventory under the Rotation rules. Keep the takeaway to
the observation that will affect future training; add longer notes only when
needed.

## Reattempts

Use spaced reattempts selectively:

| First status | Default action |
| --- | --- |
| `M0` | Move on. |
| `M1` | Reattempt only for an important execution weakness. |
| `M2` | Consider one spaced reattempt when the missing observation was important. |
| `M3` | Schedule one spaced reattempt. |
| `M4` | Diagnose transfer versus new technique first, then choose targeted repair or a reattempt. |

Repeated failure of the same idea should trigger targeted repair of that idea, not endless repetition of individual problems.

Record every deliberate revisit here; do not alter the fresh row.

| Problem | Date | Status | Hint | Model / Total Time | Primary Gap | Key Takeaway |
| ------- | ---- | ------ | ---- | ------------------ | ----------- | ------------ |
|         |      |        |      |                    |             |              |

Reattempts do not enter fresh-attempt metrics.

## Promotion Into `questions.md`

Do not automatically add mixed problems to `questions.md`. Promote a problem or technique only when it:

* exposes a genuinely new reusable interview technique
* reveals an important missing roadmap pattern
* represents a repeated conceptual transfer failure
* contains a fundamental invariant worth installing
* is a strong canonical representative of an underrepresented technique

A one-off failure on an already-known idea remains only in this file.

## Rolling Calibration

After every 10 additional **transfer-eligible fresh attempts**, report total
fresh volume and calculate transfer metrics over transfer-eligible fresh
attempts.

A transfer-eligible fresh attempt is one whose recorded cause is not `NEW_TECHNIQUE`. Report `NEW_TECHNIQUE` attempts separately; exclude them from every transfer-rate denominator.

| Metric | Formula over transfer-eligible fresh attempts |
| --- | --- |
| Independent interview pass rate | `M0 / eligible fresh attempts` |
| Independent derivation rate | `(M0 + M1) / eligible fresh attempts` |
| Hint dependency | `attempts with Hint H1-H4 / eligible fresh attempts` |
| Major miss rate | `M4 / eligible fresh attempts` |

Also summarize failure stages and causes. Always use the immutable first-attempt status and hint; never substitute reattempt outcomes.

Difficulty guidance per ten transfer-eligible attempts:

* Below 40% independent derivation: approximately 9 Medium / 1 Hard.
* From 40-70%: approximately 7-8 Medium / 2-3 Hard.
* Above 70%: approximately 6-7 Medium / 3-4 Hard, emphasizing
  modeling-heavy questions and follow-ups.

A Medium with substantial interviewer follow-ups may provide Hard-level signal
without being counted as a Hard. When calibration changes the target ratio,
replenish toward the new ratio rather than preserving 18/6 mechanically.

Do not respond to isolated misses by adding large amounts of theory or making every problem harder.
