# Interview-Coaching Startup Protocol

This is the single router for coaching tasks. Apply it once when a new coaching
task starts.

## Direct Work

Handle repository work, framework explanations, audits, and status/history
questions directly. Load only what the request needs; do not start an exercise,
show a session brief, or update progress unless asked.

Reviewing old evidence is direct. Evaluating a newly supplied explanation,
trace, solution, or code is a new attempt even if the request says `review`,
`check`, or `calibrate`.

## Routing

Choose a mode in this order:

1. Use the explicitly requested mode.
2. Otherwise infer it from the action and referenced artifact.
3. Use `Default next coaching mode` in `context/roadmap.md` only for an
   open-ended request such as `start coaching`.

* Recall, quiz, or independent redo of an owned learned problem or tracked core
  skill → Recall.
* Teach, learn, or repair a learned problem or core skill → Learning / Repair.
* An unowned problem or an optional row outside the active bank → Learning /
  Repair; an alias outside the bank redirects to its canonical owner.
* `advanced topics`, `advanced DSA`, or a named advanced-queue item → Advanced
  DSA Learning.
* A fresh unseen problem, or revisit of a logged mixed problem → Mixed Practice.
* A formal timed round → Coding Mock.
* A design prompt → System Design; a story prompt → Behavioral.

Artifact origin wins when wording is ambiguous: a mixed revisit remains mixed,
and an advanced-queue item remains advanced.

## Minimal Loads And Owners

First load `context/student_profile.md` and `context/roadmap.md`.

| Mode | Then load | Behavior owner | Evidence owner |
| --- | --- | --- | --- |
| Learning / Repair | `context/session_workflow.md` and the relevant `context/questions.md` or `context/core_recall.md` | `context/session_workflow.md` | the relevant learned/core tracker |
| Advanced DSA Learning | `context/session_workflow.md`, `context/advanced_topics.md`, and the relevant learned/core tracker | workflow plus advanced safety rules | the learned/core tracker; advanced stores disposition only |
| Recall | `context/recall.md` and the relevant learned/core tracker | `context/recall.md` | the relevant learned/core tracker |
| Mixed Practice | `context/mixed_practice.md` | `context/mixed_practice.md` | `context/mixed_practice.md` |
| Coding Mock | `context/mock_interviews.md` | `context/mock_interviews.md` | `context/mock_interviews.md` |
| System Design / Behavioral | `context/session_workflow.md`, `context/non_dsa.md` | `context/session_workflow.md` | `context/non_dsa.md` |

For a coach-selected mock, also load only the compact exposure/source trackers
named by `context/mock_interviews.md`.

## Lazy History

Select from the compact tracker first. For recall, repair, or redo of an existing
item, load its matching section, when present, from
`context/history/question_history.md` or
`context/history/core_recall_history.md`. Never load a full archive during
routine startup or broad selection.

## Assistance

Use one vocabulary everywhere:

| Help | Meaning |
| --- | --- |
| `None` | No solution help. Statement clarification or repeating an explicit constraint stays `None`. |
| `Nudge` | A small question, observation, or counterexample that does not supply the solution structure. |
| `Major` | Supplies the representation, algorithm, invariant, proof, or essential implementation direction. |
| `Unknown` | The help record is insufficient. |

Only `None` can establish independence. `Nudge`, `Major`, and `Unknown` cannot.

## Evidence Integrity

One attempt has one evidence owner. Other files may receive only a queue
disposition, handoff link, or meaningful roadmap milestone. Never duplicate an
attempt, and never record materially guided work as independent.
