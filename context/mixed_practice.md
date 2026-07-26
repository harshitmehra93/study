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

A candidate is eligible when:

* it is absent from `questions.md` and the fresh Attempt Log
* Harshit does not clearly remember solving or studying it
* it mainly uses concepts already represented in the roadmap
* it is interview-relevant and reasonable for one interview-style session
* it does not primarily require an obscure competitive-programming trick

Prefer Medium problems. Increase modeling difficulty gradually as transfer improves; use Hard problems selectively.

An occasional problem may expose a genuinely important unknown technique. Record that cause as `NEW_TECHNIQUE`; do not score it as failed transfer.

Prefer a mix of verified contest Q2/Q3 problems, relevant company-tagged problems, strong curated interview problems, and modeling-heavy combinations of familiar primitives. Avoid near-duplicates and visible pattern rotation.

Maintain roughly 15-30 eligible `Ready` candidates when practical. Before adding one, check `questions.md`, the Attempt Log, existing candidates, and current difficulty. If external verification is unavailable, use only already-verified candidates. Never invent an external source, link, or company association.

| ID | Problem | Source | Link | Difficulty | State |
| -: | ------- | ------ | ---- | ---------- | ----- |
|    |         |        |      |            |       |

Allowed states are `Ready`, `Attempted`, and `Excluded`. Do not store solution hints, expected patterns, or solution summaries for `Ready` problems.

If Harshit recognizes a selected problem before meaningful work begins, mark it `Excluded` for mixed practice, do not score it, and choose another candidate.

## Selection And Presentation

For a fresh mixed session:

1. Select one eligible `Ready` problem.
2. Check recent fresh attempts to avoid obvious pattern clustering.
3. Match difficulty to current rolling transfer results.
4. Do not select from Harshit's stated strengths or the most recent recall pattern.

Present only interview-visible information:

* a complete, self-contained paraphrase of the problem
* examples
* constraints
* function signature when useful

Do not reveal the topic, expected algorithm or data structure, source category, company tag, related known problems, difficulty commentary, selection reason, or hints.

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

After logging a fresh attempt, mark its Candidate Bank row `Attempted`. Keep the takeaway to the observation that will affect future training; add longer notes only when needed.

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

Every 10 fresh attempts, report total fresh volume and calculate transfer metrics over **transfer-eligible fresh attempts**.

A transfer-eligible fresh attempt is one whose recorded cause is not `NEW_TECHNIQUE`. Report `NEW_TECHNIQUE` attempts separately; exclude them from every transfer-rate denominator.

| Metric | Formula over transfer-eligible fresh attempts |
| --- | --- |
| Independent interview pass rate | `M0 / eligible fresh attempts` |
| Independent derivation rate | `(M0 + M1) / eligible fresh attempts` |
| Hint dependency | `attempts with Hint H1-H4 / eligible fresh attempts` |
| Major miss rate | `M4 / eligible fresh attempts` |

Also summarize failure stages and causes. Always use the immutable first-attempt status and hint; never substitute reattempt outcomes.

Difficulty guidance:

* Below roughly 40% independent derivation: favor direct-but-unlabeled Medium applications of installed ideas.
* Roughly 40-70%: maintain broad mixed exposure.
* Above roughly 70%: gradually add modeling-heavy Mediums, combinations, tighter timing, and occasional appropriate Hards.

Do not respond to isolated misses by adding large amounts of theory or making every problem harder.
