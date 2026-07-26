# Rolling Recall Protocol

Use spaced recall for:

- learned interview problems in `questions.md`
- core algorithms and data structures in `core_recall.md`

`questions.md` owns current learned-problem status and selection data.
`core_recall.md` owns current core-skill status and selection data. This file
owns the shared recall protocol and recall-status format.

Detailed historical evidence is archived in
`context/history/question_history.md` and
`context/history/core_recall_history.md`. Select from the compact tracker first,
then load only the matching archive section for the chosen item.

## Prompts

For a learned-problem recall:

- give a complete, self-contained paraphrase of the problem rather than copying an external statement verbatim
- include the input/output contract, constraints needed for reasoning, at least one example, and important edge conditions
- do not reveal the pattern or expected algorithm before Harshit identifies the model

For a core-skill recall, give a concise self-contained prompt containing:

- the operation or algorithm to explain or implement
- the required invariant
- edge cases
- complexity
- one small example

## Recall Levels And Shared Status Format

| Level | Required work |
| --- | --- |
| L1 | Explain the model or pattern, invariant, important edge cases, complexity, and a small example. |
| L2 | Write a coherent code skeleton with the important state and control flow. |
| L3 | Write a complete implementation and perform a brief correctness and edge-case check. |

Use exactly one latest recall value in each tracker:

- Empty = no verified recall attempt has been recorded.
- `L1 ✅ yyyy-mm-dd`, `L2 ✅ yyyy-mm-dd`, or `L3 ✅ yyyy-mm-dd` = pass at that level.
- `L1 review yyyy-mm-dd`, `L2 review yyyy-mm-dd`, or `L3 review yyyy-mm-dd` = attempted at that level but another recall is needed.

A **pass** means Harshit completed the important reasoning required for the selected level without meaningful conceptual or algorithmic assistance. Self-correction and minor wording or syntax corrections do not invalidate a pass when the model, invariant, and essential control flow were independently sound.

A **review** means the attempt was incomplete, materially incorrect, or required meaningful help with the model, invariant, recurrence, proof, data structure, or essential control flow. Correct final code after such help remains a review.

A **self-reported** item is one Harshit says he knows without demonstrating the
selected recall level. Preserve that fact in the compact note and matching
history section if useful, but do not write a latest-recall value, count it as a
verified attempt, or use it to start the recall cooldown.

Most sessions should use L1 or L2. Use L3 when full implementation fluency is the target or when a prior review exposed an implementation gap.

## Deterministic Eligibility And Priority

Determine recency only from dated evidence in the owning tracker:

1. the date in `Latest Recall`
2. a later explicit `Last practice: yyyy-mm-dd` entry in the compact current note

Do not use Git history, filesystem modification times, or undocumented memory as recency evidence.

The `last activity date` is the most recent tracker date above. If neither exists, the date is unknown.

An item is eligible when:

- its last activity date is unknown; or
- at least 21 full calendar days have elapsed since its last activity date.

For learned problems, consider only rows whose `Learning Status` is non-empty. An unattempted problem is pattern learning or mixed practice, not recall. Core rows are recall candidates because `core_recall.md` contains skills already practiced in the core-skills implementation area.

Harshit may explicitly request a specific item before its eligibility date.

Rank eligible items in this order:

1. Items with an empty latest-recall field.
2. Items whose latest recall is `review`.
3. Items whose latest recall is a pass.
4. Within the same tier, prefer a guided/partially owned learned problem over an independently owned one.
5. Then treat an unknown activity date as oldest; otherwise choose the earliest activity date.
6. Use the core-skill priority in `core_recall.md` when otherwise comparable.
7. Prefer current/previous-block or high-frequency material only as a final tie-breaker.

## Interleaved Learned-Problem Recall

- Mix topics instead of presenting consecutive questions from the same section or pattern.
- Avoid same-topic follow-ups because the previous question can reveal the model and inflate apparent recall.
- Use consecutive same-topic questions only to diagnose a specific gap, compare closely related models, or run an explicit redo block.
- “Interleaved learned-problem recall” still uses already learned problems. It is distinct from unseen Mixed Problem Practice.

## Updating Progress

After every demonstrated attempt:

1. Replace the owning row's latest recall field with the new level, outcome, and date.
2. Keep the compact current note focused on eligibility and the next action.
3. Append meaningful evidence to the matching history section when it explains a
   recurring gap or real progression; do not archive routine pass boilerplate.
4. Record one concise next action when the result is `review`.

For a learned interview problem:

- update `questions.md`
- change `Learning Status` from guided to independent only after an independent L3 solution demonstrates ownership of the intended interview solution
- do not demote `Learning Status` after a weak recall; the latest recall field owns current retention

For a core algorithm or data structure:

- update `core_recall.md`

Do not write the same recall attempt to mixed-practice or mock-interview trackers.
