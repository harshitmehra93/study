# Coding Mock Protocol And Log

This file owns formal timed DSA coding rounds. Timed HLD/LLD work remains System
Design. A mock result stays here; later recall, repair, or mixed work is separate.

Use the assistance vocabulary in `context/startup_protocol.md`. Keep outcome,
help, and observed timing separate.

## Prompt Source

For an external prompt, record the provider; use `Novelty=Unknown` unless prior
exposure was checked. Reviewing an old result is read-only.

For a coach-selected prompt:

1. Verify an interview-sized prompt, its solution, and time fit.
2. Check compact learned, core, advanced, mixed, and mock exposure; avoid exact
   or structural repeats.
3. Use `Unseen`, `Known`, or `Unknown` for novelty.
4. Reveal only interview-visible information.

Declare one exact deadline for every new mock; historical ranges remain
uncalibrated. If a mixed candidate is used, set `Consumed — Mock #N` there and
record the attempt only here. If the prompt is recognized, replace it or record
`Novelty=Known`; update a source disposition only when one exists.

## Run

1. State the deadline and expected implementation/testing deliverable.
2. Let Harshit clarify, model, communicate, code, test, and debug.
3. Record the strongest help and close at the deadline under the shared attempt
   boundary.
4. Record timing as `deadline / model selection / total`, plus
   implementation/testing evidence and one gap/next action.

## Outcomes

* `Pass` — the requested round was completed correctly.
* `Partial` — viable direction, but material work remained.
* `Miss` — no viable correct result was reached.
* `Unknown` — evidence is insufficient.

A row is calibrated when novelty, deadline/total timing, outcome, help, and
implementation/testing evidence are observed; model-selection time may remain
`unknown`. It is satisfactory only with `Unseen`, `Pass`, `Help=None`,
completion inside the deadline, and sufficient implementation/testing evidence.

## Log

| # | Date | Source | Prompt | Novelty | Timing | Outcome | Help | Implementation / study.ocp.Test Evidence | Gap / Next Action |
| ---: | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 2026-07-23 | Exponent | Decrypt Message | Unknown | 20-30 min / unknown / unknown | Miss | Unknown | Unknown | Implementation defect unknown; review the code, identify the first defect, then redo; date unset. |
| 2 | 2026-07-23 | Exponent | Drone Flight Planner | Unknown | 20-30 min / unknown / unknown | Pass | Unknown | Unknown | Evidence incomplete; capture the model and feedback before claiming independence. |
