# Rolling Recall Protocol

Use recall for learned interview problems in `questions.md` and active core
skills in `core_recall.md`. Those trackers own current state. The matching files
under `context/history/` are sparse cold storage; select an item first, then load
its history only when prior corrections are useful.

## Prompts

For a learned problem, give a complete self-contained paraphrase with the
input/output contract, reasoning-relevant constraints, and a statement-provided
example. Do not add coach-derived edge cases or reveal the pattern before
Harshit identifies the model.

For a core skill, initially ask only for the named operation or algorithm. Keep
the invariant, edge cases, complexity, and a small example as the evaluator
checklist. If the initial response omits a dimension, ask a neutral completeness
follow-up without supplying its content.

## Levels And Status

| Level | Required work |
| --- | --- |
| L1 | Demonstrate the model, important invariant, edge cases, complexity, and a small example through explanation, code, pseudocode, or a trace. |
| L2 | Complete L1 and write a coherent code skeleton with the important state and control flow. |
| L3 | Complete L2, write a complete implementation, and perform a brief correctness and edge-case check. |

Evaluate the evidence holistically. Correct code or a trace need not be repeated
in prose unless a material ambiguity remains.

Use one value in `Latest Recall`:

- Empty = no verified recall attempt.
- `L1 ✅ yyyy-mm-dd`, `L2 ✅ yyyy-mm-dd`, or `L3 ✅ yyyy-mm-dd` = pass.
- `L1 review yyyy-mm-dd`, `L2 review yyyy-mm-dd`, or
  `L3 review yyyy-mm-dd` = another recall is needed.

A pass requires the important reasoning for that level without meaningful
conceptual or algorithmic help. Self-correction and minor wording or syntax
repairs are compatible with a pass.

A legacy pass with an unattributed correction keeps its outcome but counts as
`Help=Unknown` for future readiness until an independent recall.

A review means the attempt was materially incomplete or incorrect, or needed
meaningful help with the model, invariant, proof, data structure, recurrence, or
essential control flow. Correct final code after that help remains a review.

Self-reported familiarity is not a verified attempt and does not update
`Latest Recall`. A declined, withdrawn, or merely presented prompt is also not
an attempt.

Most sessions should use L1 or L2. Use L3 when implementation fluency or
independent ownership is the target.

## Feedback

- Do not force the learner to recite a correction supplied by the coach.
- Probe proof depth in proportion to the problem.

## Soft Selection

An explicit eligible request wins. An item in the learned bank's Outside Recall
Rotation is not a recall candidate: redirect an alias to its canonical row, and
treat an optional or unowned problem as learning. Reference-only core topics
are also excluded; admission follows the relevant tracker.

Otherwise choose from the Active Recall Bank rows with non-empty
`Learning Status`, plus the active core Recall Table. Use these soft preferences:

1. Avoid repeating the same item within about 7 days when practical.
2. Prefer `review` items, oldest first (empty is oldest), but periodically sample
   an old pass.
3. Use core priority only when candidates are otherwise comparable.
4. Interleave topics and avoid consecutive same-pattern prompts unless
   diagnosing a specific gap or running an explicit redo.

There is no computed due date or hard cooldown. `Latest Recall` is the only
recency field; do not use Git timestamps, filesystem times, or undocumented
memory.

## Updating State

After a demonstrated recall attempt:

1. Replace the owning row's `Latest Recall` with the level, outcome, and date.
2. Keep the current note empty unless there is an item-specific next target or
   ownership clarification.
3. Archive `Nudge`, `Major`, or `Unknown` with the exact help label; archive other
   detail only when it is useful. Routine `Help=None` passes need only the tracker.

After an independent L3 learned-problem pass, apply the ownership transition in
`context/questions.md`; a weak recall never demotes ownership.

Learning, repair, and ordinary practice never change `Latest Recall`.
