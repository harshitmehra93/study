# Rolling Recall Protocol

Use recall for learned interview problems in `questions.md` and active core
skills in `core_recall.md`. Those trackers own current state. The matching files
under `context/history/` are sparse cold storage; select an item first, then load
its history only when prior corrections are useful.

## Prompts

For a learned problem, give a complete self-contained paraphrase with the
input/output contract, reasoning-relevant constraints, an example, and important
edge cases. Do not reveal the pattern before Harshit identifies the model.

For a core skill, ask for the operation or algorithm, invariant, edge cases,
complexity, and one small example.

## Levels And Status

| Level | Required work |
| --- | --- |
| L1 | Demonstrate the model, important invariant, edge cases, complexity, and a small example through explanation, code, pseudocode, or a trace. |
| L2 | Write a coherent code skeleton with the important state and control flow. |
| L3 | Write a complete implementation and perform a brief correctness and edge-case check. |

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

A review means the attempt was materially incomplete or incorrect, or needed
meaningful help with the model, invariant, proof, data structure, recurrence, or
essential control flow. Correct final code after that help remains a review.

Self-reported familiarity is not a verified attempt and does not update
`Latest Recall`. A declined, withdrawn, or merely presented prompt is also not
an attempt.

Most sessions should use L1 or L2. Use L3 when implementation fluency or
independent ownership is the target.

## Feedback

- Lead with the verdict and the highest-leverage reason.
- Treat explanation, code, and traces as complementary evidence.
- Ask one focused follow-up only when material evidence is missing.
- Do not force the learner to recite a correction supplied by the coach.
- Probe proof depth in proportion to the problem.
- If feedback is challenged, reassess both correctness and materiality.
- Stop when the outcome and next action are clear.

## Soft Selection

An explicit request wins. An item in the learned bank's Outside Recall Rotation
table is not a recall candidate: redirect an alias to its canonical row, and
treat an optional or unowned problem as learning. Reference-only core topics
are also excluded.

Otherwise choose from the Active Recall Bank rows with non-empty
`Learning Status`, plus the active core Recall Table. Use these soft preferences:

1. Prefer `review` items over passes.
2. Avoid repeating the same item within about 7 days when practical.
3. Then prefer the oldest date in `Latest Recall`; treat an empty date as oldest.
4. Use core priority only when candidates are otherwise comparable.
5. Interleave topics and avoid consecutive same-pattern prompts unless
   diagnosing a specific gap or running an explicit redo.

There is no computed due date or hard cooldown. `Latest Recall` is the only
recency field; do not use Git timestamps, filesystem times, or undocumented
memory.

## Updating State

After a demonstrated recall attempt:

1. Replace the owning row's `Latest Recall` with the level, outcome, and date.
2. Keep the current note empty unless there is an item-specific next target or
   ownership clarification.
3. Archive only evidence worth teaching from: meaningful guidance, a correction,
   a counterexample, or real progression. Routine passes need only the tracker.

For a learned problem, promote `Learning Status` from guided to independent only
after an independent L3 solution establishes ownership. Do not demote ownership
after a weak recall; `Latest Recall` records retention.

Learning, guided repair, and ordinary practice do not change recall recency.
They may update ownership or an item-specific note when justified, and may add
meaningful cold-storage evidence, but they do not change `Latest Recall`.

Do not copy the same attempt into mixed-practice or mock-interview trackers.
