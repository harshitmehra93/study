# Learning And Non-DSA Workflows

`context/startup_protocol.md` owns routing, assistance vocabulary, and evidence
ownership. Dedicated files own Recall, Mixed Practice, and Coding Mock.

## DSA Learning / Repair

Use this for a new learned problem or core skill, or a targeted repair. Install
the model rather than memorizing code.

During the attempt:

* let Harshit drive the model and implementation
* ignore minor style issues
* interrupt a correctness-breaking model, state, invariant, or mutation
* give algorithmic direction only after help is requested
* record help using `None`, `Nudge`, `Major`, or `Unknown`

Do not run repository tests during the live attempt unless Harshit asks. In
review, check correctness, model/invariant, edge cases, complexity,
implementation clarity, and interview explanation. Stop once the result is
correct, readable, and explainable.

For an existing item, load its selected history section when one exists.
Learned-problem work updates `context/questions.md`; core-skill work updates
`context/core_recall.md`. Learning or repair does not change `Latest Recall`.
Append detailed history only when it captures useful evidence or progression.

For advanced work, retain this behavior and follow
`context/advanced_topics.md` for cold checks, installation safety, spoiler
protection, and queue disposition. Store learned/core evidence only in its
eventual owner.

## System Design

Do not let the session become passive reading:

1. Present the prompt.
2. Let Harshit clarify requirements and estimate scale.
3. Define APIs and data.
4. Propose the architecture.
5. Deep-dive on bottlenecks, failures, and trade-offs.
6. Cover reliability, observability, and operations.
7. Compare with a reliable reference and extract actionable misses.

Judge prioritization, trade-offs, bottleneck reasoning, reliability, and
operational maturity. Record a newly demonstrated attempt in
`context/non_dsa.md`; historical review is read-only.

## Behavioral

Turn real experience into concise senior-level stories. Establish:

1. situation and stakes
2. personal responsibility
3. actions and judgment
4. trade-offs
5. measurable result
6. ambiguity, communication, and leadership

Challenge vague ownership, collective-only language, missing outcomes, and
unsupported impact. Record a newly demonstrated attempt in
`context/non_dsa.md`; historical review is read-only.
