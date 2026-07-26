# Roadmap

Status date: **2026-07-26**

This file owns phase-level planning, priorities, transition gates, and qualitative readiness. It does not own exact problem, recall, mixed-attempt, or mock results.

## Current Snapshot

Primary DSA phase:

> **Phase 3B — complete the first full rolling-recall pass across the learned question bank.**

Default next coaching mode:

> **Recall**

At the last calibration, about 60% of the question bank had a first verified
recall attempt recorded. This measures recall coverage, not mastery. Use
non-empty `Latest Recall` values in `questions.md` for the current exact figure.

Parallel exposure:

* **Phase 4 mixed transfer:** ramp planned; `mixed_practice.md` currently has 0 logged fresh attempts.
* **Phase 5 coding mocks:** light exposure started on 2026-07-23; exact results belong in `mock_interviews.md`.
* **System design:** foundation exists, but active interview packaging needs more repetitions.
* **Behavioral:** strong raw material exists, but senior stories still need packaging.

## Immediate Priorities

1. Finish first-pass recall coverage across required problems in `questions.md`.
2. Continue core algorithm and data-structure recall from `core_recall.md`.
3. Use new pattern learning only when recall exposes a genuine missing foundation.
4. Prepare the verified mixed candidate pool, but keep Recall as the default DSA mode until the transition gate.
5. Continue light coding-mock, system-design, and behavioral exposure in parallel.

## DSA Phase Map

| Phase | Goal | Status |
| --- | --- | --- |
| Phase 1 | Basic coding fluency | ✅ Done |
| Phase 2 | Core DP, graphs, backtracking, trees, and heap foundations | 🟡 Mostly done |
| Phase 3A | Required first-pass pattern installation | ✅ Required pass complete |
| Phase 3B | First full rolling-recall pass | 🟡 Primary active block |
| Phase 4 | Mixed / unseen transfer practice | Planned ramp; 0 fresh attempts logged |
| Phase 5 | Formal mock consistency | Light calibration exposure started |

## Phase 4 Transition

Make mixed practice the primary DSA mode after:

1. Every required learned problem has received its first recall attempt, subject to explicit optional exceptions in `questions.md`.
2. Repeated recall gaps have been identified for targeted repair.
3. A verified `Ready` candidate pool exists in `mixed_practice.md`.

After transition, use approximately:

| Activity | Split |
| --- | ---: |
| Fresh mixed / unseen problems | 70% |
| Targeted recall, redo, and transfer-gap repair | 30% |

This is a training split, not a daily quota. Do not return to broad block-by-block learning unless repeated evidence exposes a genuine knowledge gap.

Phase 4 readiness is judged from the fresh-attempt metrics in `mixed_practice.md`, especially independent derivation, interview-level passes, hint dependency, major misses, and repeated gap categories.

## Mock Cadence

While Phase 3B remains primary:

> Approximately one coding mock every 1-2 weeks.

After Phase 4 is active:

> Start around one coding mock per week, then adjust from observed consistency.

Mocks measure complete interview execution and remain separate from mixed-practice transfer results.

## Qualitative Readiness

Current strengths:

* solid basic coding and Java implementation
* broad first-pass DSA coverage
* strong infrastructure and cloud experience

Current gaps:

* recall consistency is not yet established across the full bank
* unseen transfer is not yet calibrated because no fresh mixed attempts are logged
* mock evidence is sparse and currently uncalibrated
* system-design judgment needs more active interview-format repetitions
* senior behavioral experience needs concise story packaging

## System Design Track

* URL Shortener work has reached the SLO/SLA area.
* Partitioning and throughput basics have been covered.
* The next need is active design repetitions rather than more passive reading.
* Target cadence: around three sessions per week while DSA remains daily.

## Behavioral Track

Build senior stories from PITR ownership, Terraform provider delivery, ARM migration, shape work, Sev2 handling, region launches, and major-version upgrades.

* First story to polish: **PITR ownership**
* Target cadence: one or two packaging sessions per week.

## Tracker Ownership

* `questions.md` — exact learned-problem and recall status
* `core_recall.md` — core algorithm/data-structure recall
* `mixed_practice.md` — candidate bank, fresh mixed attempts, and mixed reattempts
* `mock_interviews.md` — formal mock evidence and follow-up
* `roadmap.md` — phase-level status and meaningful transitions only
