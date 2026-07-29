# Coding Mock Protocol And Log

This file owns formal timed-round behavior and evidence. A mock outcome stays
here; a later recall, repair, or mixed attempt is separate.

Use the assistance vocabulary in `context/startup_protocol.md`. Keep outcome,
help, and observed timing separate.

## Prompt Source

For an externally supplied prompt, record the provider and use the prompt as
given. Reviewing an old result without a new timed round is read-only.

For a coach-selected prompt:

1. Use a permitted private source, such as the Extended / Mock Queue in
   `context/mixed_practice.md`.
2. Check the compact learned, core, mixed, and mock trackers for exact prior
   exposure. Search history headings only when needed; do not load an archive.
3. Avoid exact or structural duplicates unless this is an explicit redo.
4. Verify the statement, solution, complexity, scope, and time fit.
5. Reveal only interview-visible information; keep source, tags, rationale, and
   expected technique private.

If Harshit recognizes the prompt before meaningful work, replace it or label
the round a redo. A source queue may update its disposition, but the mock result
is recorded only here.

## Run

1. State format and timebox.
2. Let Harshit clarify, model, communicate, code, test, and debug.
3. Keep intervention realistic and record the strongest help.
4. Record model-selection and total time when observed; otherwise use
   `unknown`.
5. Mark the timed-round end, then review.
6. Record the first material gap and one next action.

## Outcomes

* `Pass` — the requested round was completed correctly.
* `Partial` — viable direction, but material work remained.
* `Miss` — no viable correct result was reached.
* `Unknown` — evidence is insufficient.

## Log

| # | Date | Source | Format | Prompt | Timebox | Model / Total | Outcome | Help | Primary Gap | Next Action |
| ---: | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 2026-07-23 | Exponent | DSA coding | Decrypt Message | 20-30 min | unknown / unknown | Miss | Unknown | Implementation defect unknown | Review the code, identify the first defect, then redo; date unset. |
| 2 | 2026-07-23 | Exponent | DSA coding | Drone Flight Planner | 20-30 min | unknown / unknown | Pass | Unknown | Evidence incomplete | Capture the model and feedback before claiming independence. |

Historical review may identify a follow-up, but record it elsewhere only after
that separate activity occurs.
