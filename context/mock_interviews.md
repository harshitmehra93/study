# Mock Interview Tracker

This file is the source of truth for timed formal mock-interview performance and follow-up. Track modeling, correctness, implementation, communication, debugging, and time management rather than only completion count.

Formal mocks are not ordinary mixed attempts. Keep the original result only here; a mock may create a separate recall, mixed-practice, implementation, or communication exercise.

## Compact Run Protocol

1. State the interview format and timebox before starting.
2. Let Harshit clarify, model, communicate, code, test, and debug under interview conditions.
3. Keep interviewer intervention realistic and record every meaningful hint or correction.
4. Record actual model-selection and total time when observable; use `unknown` rather than inferring elapsed work time.
5. End or clearly mark the timed round at the timebox, then conduct the review.
6. Record the first meaningful failure and one concrete follow-up, including a target date when a redo is appropriate.

## Calibration Axes

Record outcome separately from independence/evidence.

Outcome:

* `Solved` — a viable solution was completed within the round.
* `Incomplete` — a viable direction existed, but implementation or validation was not completed.
* `Incorrect` — the submitted model or code was materially wrong.
* `No solution` — no viable solution was reached.

Independence / evidence:

* `✅ Independent` — no major algorithmic help, and correctness, edge cases, complexity, communication, and timing were sufficiently observed.
* `🟡 Guided` — interviewer assistance materially affected the model or solution.
* `Uncalibrated` — intervention level, correctness review, timing, or interviewer feedback is too incomplete to judge independence.

Do not infer `✅ Independent` merely from `Solved`.

## Mock Log

| # | Date | Provider | Track / Format | Problem | Timebox | Model / Total Time | Outcome | Hints / Intervention | Independence / Evidence | Primary Gap | Key Notes | Follow-up Status / Date |
| ---: | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 2026-07-23 | Exponent | DSA coding round | Decrypt Message | 20-30 min | unknown / unknown | Incorrect | Unknown; exact intervention not captured | Uncalibrated | `IMPLEMENTATION` — exact defect unknown | The high-level model was on the right track, but the code was incorrect within the round. Exact code defect and interviewer feedback are not yet captured. | Open — review the implementation, identify the first code-level failure, then redo under the same timebox; target date not set. |
| 2 | 2026-07-23 | Exponent | DSA coding round | Drone Flight Planner | 20-30 min | unknown / unknown | Solved | Unknown; intervention not captured | Uncalibrated | Uncalibrated | Reported solved within the round; model, edge-case review, complexity explanation, and interviewer feedback are not yet captured. | Open — record the solution model and feedback before counting an independent pass; target date not set. |

## Review Rule

Use the log to identify repeated gaps across mocks. Do not mechanically translate mock outcomes into `questions.md` or `mixed_practice.md` statuses. Create a cross-mode follow-up only when the review identifies a specific training need.
