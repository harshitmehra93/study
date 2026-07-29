# Roadmap

Status date: **2026-07-30**

This file owns phase-level planning, priorities, transition gates, and qualitative readiness. It does not own exact problem, recall, mixed-attempt, or mock results.

## Current Snapshot

Primary DSA phase:

> **Phase 4 mixed / unseen transfer practice is active.**

Default next coaching mode:

> **Mixed Problem Practice**

The required learned-problem bank now has first-pass recall coverage for
127/127 items, and the active core rotation has coverage for 24/24 items.
Coverage is complete; this measures exposure, not mastery. Latest `review`
results remain targeted consolidation work.

Parallel exposure:

* **Phase 4 mixed transfer:** active with 24 verified Google-tagged `Ready`
  candidates (18 Medium / 6 Hard) and 0 logged fresh attempts.
* **Advanced DSA audit:** bounded missing-foundation and advanced Google-coverage
  queue is preserved in `advanced_topics.md`; it is demand-triggered and does
  not replace the Phase 4 operating split.
* **Phase 5 coding mocks:** light exposure started on 2026-07-23; exact results belong in `mock_interviews.md`.
* **System design:** foundation exists, but active interview packaging needs more repetitions.
* **Behavioral:** strong raw material exists, but senior stories still need packaging.

## Immediate Priorities

1. Execute the precommitted first-ten mixed schedule: 8 Medium / 2 Hard, no
   Hard in the first three attempts, no consecutive Hards, and broad family
   coverage.
2. Keep the Phase 4 training split near 70% fresh mixed work and 30% targeted
   recall, redo, and transfer-gap repair.
3. Replenish every `Attempted` or recognized candidate so the verified
   `Ready` inventory remains 24 whenever practical.
4. Install a new technique when recall, transfer, or private Google-candidate
   screening shows that it is an important missing foundation; keep a held
   candidate unseen when practical. Use `advanced_topics.md` for the dated
   audit, bounded queue, and installation safety rules.
5. Continue light coding-mock, system-design, and behavioral exposure in
   parallel.

## DSA Phase Map

| Phase | Goal | Status |
| --- | --- | --- |
| Phase 1 | Basic coding fluency | ✅ Done |
| Phase 2 | Core DP, graphs, backtracking, trees, and heap foundations | 🟡 Mostly done |
| Phase 3A | Required first-pass pattern installation | ✅ Required pass complete |
| Phase 3B | First full rolling-recall pass | ✅ Required learned and active core first-pass coverage complete |
| Phase 4 | Mixed / unseen transfer practice | Active; 24 verified `Ready` candidates (18 Medium / 6 Hard), 0 fresh attempts |
| Phase 5 | Formal mock consistency | Light calibration exposure started |

## Phase 4 Operating Split

The transition became active after all three gates were satisfied:

1. Every required learned problem has received its first recall attempt, subject to explicit optional exceptions in `questions.md`.
2. Repeated recall gaps have been identified for targeted repair.
3. A verified `Ready` candidate pool exists in `mixed_practice.md`.

Use approximately:

| Activity | Split |
| --- | ---: |
| Fresh mixed / unseen problems | 70% |
| Targeted recall, redo, and transfer-gap repair | 30% |

This is a training split, not a daily quota. Do not return to broad block-by-block learning unless repeated evidence exposes a genuine knowledge gap.

Phase 4 readiness is judged from the fresh-attempt metrics in `mixed_practice.md`, especially independent derivation, interview-level passes, hint dependency, major misses, and repeated gap categories.

Current gate status:

1. Required learned-problem first-pass coverage: complete.
2. Recall gaps for targeted repair: identified in the latest `review` results.
3. Verified `Ready` candidate pool: complete — 24 Google-tagged candidates,
   all at least 10/12 under the Phase 4 quality gate.

## Mock Cadence

With Phase 4 active:

> Start around one coding mock per week, then adjust from observed consistency.

Mocks measure complete interview execution and remain separate from mixed-practice transfer results.

## Qualitative Readiness

Current strengths:

* solid basic coding and Java implementation
* broad first-pass DSA coverage
* strong infrastructure and cloud experience

Current gaps:

* first-pass recall coverage is complete, but many latest results still require targeted independent redos
* unseen transfer is not yet calibrated because no fresh mixed attempts are logged
* a bounded advanced-topic audit exists, but its rows are hypotheses until cold
  checks or learning sessions produce evidence
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
* `advanced_topics.md` — missing-technique audits, advanced-topic queue, and
  future diagnostic questions; not learned ownership
* `mock_interviews.md` — formal mock evidence and follow-up
* `roadmap.md` — phase-level status and meaningful transitions only
