# Interview-Coaching Startup Protocol

This file is the single router for interview-coaching tasks. It owns context
loading, session selection, instruction precedence, and progress ownership.

## Scope

Apply this protocol once at the start of a new interview-coaching task.

Do not apply coaching-mode selection, a coaching-session brief, or tracker
updates to explicit non-coaching work such as repository maintenance,
instruction audits, explanations, or status questions. Handle those requests
directly and load only the files they require.

## Base Context

For a coaching task, first read:

1. `context/student_profile.md`
2. `context/roadmap.md`

These files establish the coaching posture, current phase, and default next
coaching mode.

## Instruction Precedence

Use this order when context files overlap:

1. Harshit's explicit request selects the task and session mode.
2. A dedicated mode protocol controls that mode's behavior and calibration.
3. `context/session_workflow.md` supplies high-level behavior where no
   dedicated protocol exists.
4. `context/student_profile.md` supplies general coaching style.
5. `context/roadmap.md` selects priorities; it does not override behavior,
   calibration, or tracker ownership.

An explicit request for help may end an independent attempt, but it never
changes how much assistance must be recorded.

Identifying an observed flaw is feedback. Supplying the missing abstraction,
algorithmic direction, invariant, or implementation structure is a hint and
must be calibrated according to the active mode.

## Session Selection And Ownership

If Harshit explicitly requests a mode, use it. Otherwise use the
`Default next coaching mode` field in `context/roadmap.md`.

| Mode | Additional context to load | Behavior owner | Primary progress owner |
| --- | --- | --- | --- |
| DSA Pattern Learning | `context/session_workflow.md`, `context/questions.md` | DSA section of `context/session_workflow.md` | `context/questions.md` |
| Recall | `context/recall.md`, `context/questions.md`, `context/core_recall.md` | `context/recall.md` | Problem recall → `context/questions.md`; core recall → `context/core_recall.md` |
| Mixed Problem Practice | `context/mixed_practice.md`, `context/questions.md` | `context/mixed_practice.md` | `context/mixed_practice.md` |
| Coding Mock | `context/mock_interviews.md` | Run protocol in `context/mock_interviews.md` | `context/mock_interviews.md` |
| System Design | `context/session_workflow.md` and relevant design material | System-design section of `context/session_workflow.md` | Detailed notes only in an explicitly selected design document; `context/roadmap.md` only for meaningful milestones |
| Behavioral | `context/session_workflow.md` and relevant story material | Behavioral section of `context/session_workflow.md` | Detailed notes only in an explicitly selected story document; `context/roadmap.md` only for meaningful milestones |

Review and calibration requests are direct tasks, not a separate exercise mode.
Load only the relevant tracker or trackers, report the evidence, and do not
change state unless the request includes an update.

Do not infer a general coaching mode from an `Active DSA block`; use the
explicit `Default next coaching mode` field. Parallel priorities in the roadmap
inform scheduling but do not silently replace an explicit request.

## Session Brief

Produce a short brief only when beginning an exercise or when the agent selected
the mode. Do not prepend it to a narrow explicit request.

Include:

* current phase
* selected task
* why it matters
* what success looks like
* what to avoid

For mixed practice, reveal only what its dedicated protocol permits.

## Progress Integrity

Record the primary attempt only in its owning tracker.

Update another tracker only for:

* an explicit promotion or follow-up required by a dedicated protocol
* a meaningful phase-level milestone
* a separate activity that actually occurred

Additional rules:

* preserve meaningful chronological evidence
* never inflate independence after meaningful guidance
* do not duplicate one attempt across trackers
* do not use `context/roadmap.md` as a detailed attempt log
* do not automatically add mixed problems to `context/questions.md`
* promote mixed work only under `context/mixed_practice.md`
* update `context/roadmap.md` only when phase, gate, cadence, or qualitative
  readiness meaningfully changes
