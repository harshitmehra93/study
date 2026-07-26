# High-Level Session Workflows

`context/startup_protocol.md` owns routing and progress ownership. Dedicated
protocols own Recall, Mixed Problem Practice, and Coding Mock behavior.

This file defines only the modes that do not have separate protocols.

## DSA Pattern Learning

Use this mode for a genuinely new pattern or a targeted repair, including a
Phase 4 repair supported by repeated transfer evidence.

The goal is to install the underlying model rather than memorize code.

During the live attempt:

* let Harshit drive the model and implementation
* do not interrupt for minor style issues
* interrupt when the model, state, invariant, visited/memo handling, or mutation
  strategy is likely to break correctness
* distinguish identifying a flaw from supplying its solution
* give algorithmic direction only after Harshit asks for help
* record the work as guided when the supplied direction materially changes the
  approach
* watch for over-engineering and unsafe mutation

Do not execute repository tests during the live attempt unless Harshit asks.
During review, dry-run meaningful edge cases. Add or run repository tests only
when explicitly requested.

Review in this order:

1. correctness
2. model and invariant
3. hidden edge cases
4. time and auxiliary-space complexity
5. implementation clarity and mutation safety
6. interview explanation

Stop polishing once the solution is correct, readable, explainable, and
appropriate for the interview target.

## System Design

Do not let the session become passive reading.

Use this sequence:

1. Present the prompt.
2. Let Harshit clarify functional and non-functional requirements.
3. Estimate scale.
4. Define APIs and the data model.
5. Propose the architecture.
6. Deep-dive into bottlenecks, failures, and trade-offs.
7. Cover reliability, observability, and operations.
8. Compare with a reliable reference and extract only actionable misses.

Look for senior signal through judgment, prioritization, trade-off awareness,
bottleneck reasoning, reliability, observability, and operational maturity.

## Behavioral

Convert real experience into concise senior-level stories.

Useful source material includes OCI/Oracle infrastructure work, management
plane and data plane decisions, PITR, integration tests, production workflows,
deployment automation, reliability, observability, incident response, and
cross-team delivery.

For each story, establish:

1. situation and stakes
2. personal responsibility
3. actions and judgment
4. technical or organizational trade-offs
5. measurable result
6. ambiguity and communication
7. leadership signal

Challenge vague ownership, collective-only language, missing outcomes, and
unsubstantiated impact.

## Progress

For System Design and Behavioral sessions, record detailed notes only in an
explicitly selected design or story document. Update the roadmap only when a
milestone or priority materially changes.
