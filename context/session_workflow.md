# Session Workflow Definitions

## DSA Session

During coding:

- In phase 2 and 3 - Goal is to install patterns of the given problem. 
- Make sure student understand the underlying pattern
- Do not interrupt for small style issues.
- Interrupt if the model is wrong.
- Interrupt if the chosen state representation, invariant, visited/memo handling, or mutation strategy will likely break correctness.
- Watch for over-engineering.
- Keep mutation safety explicit.
- Write unit tests to prove correctness of my solution in the test directory and appropriate package when asked
- Don't give any hints, unless asked

After coding, review in this order:

1. Does it solve the problem?
2. Is the model correct?
3. Is there a hidden edge case?
4. Is complexity acceptable?
5. Is code readable?
6. Can he explain it in interview language?

Dont run tests, just validate code correctness.

Do not over-polish if the solution is correct, readable, explainable, and tested.

## System Design Session

Do not let the session become passive reading.

Use this order:

1. Problem statement.
2. Harshit attempts the design using the template.
3. Review requirements, scale, APIs, data model, architecture, deep dives, trade-offs, failure modes, and metrics.
4. Compare against the book or canonical solution.
5. Extract only the actionable misses.

Senior signal should show:

- judgment
- trade-off awareness
- bottleneck thinking
- reliability
- observability
- operational maturity

## Behavioral Session

Convert real experience into senior stories.

Use raw material from:

- OCI / Oracle infrastructure work
- management plane / data plane work
- PITR features
- integration tests
- production workflows
- deployment processes
- infrastructure automation
- reliability and observability concerns

Each story should show ownership, ambiguity handling, judgment, communication, and technical leadership.

## Calibration Rules

Count a problem as independent only if Harshit:

- selected the right model mostly unaided
- handled edge cases without major prompting
- coded a working solution
- explained complexity clearly

If the solution needed heavy hints, mark it as guided and schedule recall later.

## Recall Sessions

Refer to `recall.md`.
