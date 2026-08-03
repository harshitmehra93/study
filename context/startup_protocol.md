# Interview-Coaching Startup Protocol

This is the single router for coaching tasks. Apply it once when a new coaching
task starts.

## Direct Work

Handle repository work, framework explanations, audits, and status/history
questions directly; do not start an exercise or update progress. Old evidence
is read-only unless Harshit deliberately submits it as a current assessment. If
that intent is unclear, review without recording an attempt.

## Routing

Choose a mode in this order:

1. Use the explicitly requested mode only when it is compatible with the domain
   and artifact/ownership eligibility below.
2. Otherwise infer it from the action and referenced artifact.
3. Use `Default next coaching mode` in `context/roadmap.md` only for an
   open-ended request such as `start coaching`.

* Recall, quiz, or independent redo of an owned learned problem or tracked core
  skill → Recall.
* Teach, learn, or repair a learned problem or core skill → Learning / Repair.
* An unowned coding/DSA problem requested for teaching or repair, or an optional
  row outside the active bank → Learning / Repair; an alias outside the bank
  redirects to its canonical owner.
* `advanced topics`, `advanced DSA`, or a named advanced-queue item → Advanced
  DSA Learning.
* A fresh unseen, untracked coding/DSA problem requested as a cold independent
  attempt, or a revisit of a logged mixed problem → Mixed Practice.
* A formal timed coding round → Coding Mock.
* Any HLD or LLD coaching—including theory, revision, repair, a diagnostic,
  learning problem, exit gate, timed design mock, drill, or delayed reattempt →
  System Design.
* A story prompt → Behavioral.

A timebox alone does not make a mock.

## Minimal Loads And Owners

First load `context/student_profile.md` and `context/roadmap.md`.

| Mode | Then load | Behavior owner | Evidence owner |
| --- | --- | --- | --- |
| Learning / Repair | `context/session_workflow.md` and the relevant learned/core tracker | `context/session_workflow.md` | canonical tracker when admitted; otherwise none |
| Advanced DSA Learning | `context/session_workflow.md`, `context/advanced_topics.md`, and the relevant learned/core tracker | workflow plus advanced safety rules | the learned/core tracker; advanced stores disposition only |
| Recall | `context/recall.md` and the relevant learned/core tracker | `context/recall.md` | the relevant learned/core tracker |
| Mixed Practice | `context/mixed_practice.md` | `context/mixed_practice.md` | `context/mixed_practice.md` |
| Coding Mock | `context/mock_interviews.md` | `context/mock_interviews.md` | `context/mock_interviews.md` |
| System Design | the shared protocol and relevant track/phase in `context/system_design.md`, plus `context/design_and_behavioral.md` | `context/system_design.md` | `context/design_and_behavioral.md` |
| Behavioral | `context/session_workflow.md`, `context/design_and_behavioral.md` | `context/session_workflow.md` | `context/design_and_behavioral.md` |

For a named recall item, load only its owner. For coach-selected cross-bank
recall, load both compact tables. After selecting recall, repair, or redo work,
load only that item's matching history section; never a full archive.

For coach-selected mixed practice or coding mocks, scan only compact learned,
core, advanced, mixed, and mock exposure trackers. For System Design, load
shared behavior through Evidence Recording and only the active track and phase;
load the Planning Envelope only for planning or status work.

## Attempt Boundary

Before giving the canonical answer, directly repairing an artifact, or starting
post-attempt teaching, close the assessed portion and freeze the demonstrated
work, result, strongest help, and observed timing. Recorded hints may continue
an assisted attempt, but only `Help=None` is independent. Post-close teaching
does not change the result; a later demonstration is a new attempt.

## Assistance

Use one vocabulary everywhere:

| Help | Meaning |
| --- | --- |
| `None` | No answer-shaping help. Statement clarification, repeating an explicit constraint, or a neutral evaluator probe that requests elaboration or validation without suggesting content stays `None`. |
| `Nudge` | A small question, observation, or counterexample intended to steer or repair the answer without supplying the essential structure. |
| `Major` | Supplies essential model, structure, content, algorithm, invariant, proof, design, story framing, or implementation direction. |
| `Unknown` | The help record is insufficient. |

Only `None` can establish independence. `Nudge`, `Major`, and `Unknown` cannot.

## Evidence Integrity

One attempt has one evidence owner; its compact tracker and sparse history count
as one owner. Other files may receive only a disposition, handoff, or roadmap
milestone. Never duplicate an attempt or record guided work as independent.
