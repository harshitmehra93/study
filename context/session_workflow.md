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

### Advanced DSA Topic Learning

When the selected task is an advanced topic, use
`context/advanced_topics.md` as the selection and audit source while retaining
the DSA Pattern Learning behavior above.

* Treat every queue row as an audit hypothesis, not as failed recall or proof of
  missing knowledge.
* Run a cold check before teaching an item marked `Cold check`.
* For an item marked `Install`, prefer a different canonical representative
  when a motivating Google question should remain unseen.
* Do not reveal a protected or held candidate's title, topic, or expected
  direction.
* Record whether the final model was independent or meaningfully guided.
* Update learned/core ownership only after actual session evidence satisfies
  the owning tracker. Until then, update only the advanced-topic audit state or
  notes.

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
