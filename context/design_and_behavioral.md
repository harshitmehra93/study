# System Design And Behavioral Tracker

This file owns System Design and Behavioral attempt evidence; their behavior
lives in `context/system_design.md` and `context/session_workflow.md`.

Append one row only after a declared assessment. Review and passive discussion
are read-only. IDs are append-only; use `yyyy-mm-dd` and the shared help scale.

Use `Pass` for completed work with no material gap, `Partial` for a viable but
incomplete result, `Miss` for no viable result, and `Unknown` for insufficient
evidence. Keep outcome, help, and timing separate.

Use `Track=HLD`, `LLD`, or `Behavioral`. Design labels contain phase and attempt;
gates add `unseen` or `materially different`, mocks add `unseen`, and delayed
reattempts link `prior #N`. Behavioral uses `Story`.

Record timing as `timebox / actual`, using `untimed` when applicable and
`unknown` only for unobserved values. Qualifying gates/mocks and assessed
Behavioral rows retain a compact rubric or durable artifact in `Detail`.

| # | Track | Phase / Attempt | Date | Prompt / Story | Outcome | Help | Timing | Gap / Next Action | Detail |
| ---: | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | LLD | Phase 0 / Diagnostic | 2026-09-19 | In-memory Zerodha stock broker | Partial | None | 60–75 min / ~60 min | Repair core state invariants and validation; then complete a focused runnable demonstration with failure cases. | Compiles; layered in-memory user, stock, and portfolio persistence; cash checks and portfolio mutation attempted. Material: selling increases rather than deducts holdings and does not enforce sufficient holdings. Invalid quantities/unknown IDs are not handled, orders are neither identified nor retained, facade lacks listStocks, and no verification asserts behaviour. Scope/use cases: Material gap; entities/responsibilities: Satisfactory; invariants/collaboration: Material gap; API/failure contracts: Material gap; abstraction restraint: Satisfactory; Java correctness: Material gap; testability/validation: Material gap; requirement change/concurrency/persistence: Not tested. |
| 2 | LLD | Phase 1 / Repair (prior #1) | 2026-09-19 | Zerodha core-flow repair | Partial | Nudge | untimed | Define missing-entity behavior and add focused executable checks for every agreed operation and failure path. | Corrected sell subtraction and oversell/non-positive-quantity checks; exposed listStocks through the facade; compiles and runs. Remaining material evidence gap: missing user/stock/portfolio lookups still become incidental null dereferences, and the demo prints stock object identities without exercising sell, withdrawals, portfolio results, or failure cases. Scope/use cases: Satisfactory; entities/responsibilities: Satisfactory; invariants/collaboration: Satisfactory for exercised core state; API/failure contracts: Material gap; abstraction restraint: Satisfactory; Java correctness: Satisfactory for normal inputs; testability/validation: Material gap; requirement change/concurrency/persistence: Not tested. |
| 3 | LLD | Phase 1 / Repair (prior #2) | 2026-09-19 | Zerodha failure-path repair | Partial | Nudge | untimed | Resolve failed-order semantics and demonstrate postconditions plus representative rejection paths through the public facade. | Added explicit missing user/stock/portfolio failures and exercised normal sells and cash movements; compiles and runs. The demo bypasses the facade for deposit/withdraw, asserts no state, and still only renders stock identities. Failed trade requests throw while OrderStatus.FAILED remains unused, leaving the promised failure contract unresolved. Scope/use cases: Satisfactory; entities/responsibilities: Satisfactory; invariants/collaboration: Satisfactory for normal paths; API/failure contracts: Material gap; abstraction restraint: Satisfactory; Java correctness: Satisfactory for normal inputs; testability/validation: Material gap; requirement change/concurrency/persistence: Not tested. |
| 4 | LLD | Phase 1 / Repair (prior #3) | 2026-09-19 | Zerodha order-record repair | Partial | Major | untimed | Correct order identity/type and restore the required portfolio API; validate all state before mutation and add focused public-API checks. | Added stock/banking order persistence and now converts lookup failures to FAILED stock orders; compiles and runs. Material regressions: every order retains id 0 so DAO saves overwrite earlier orders, a rejected sell is recorded as BUY, and ZerodhaService no longer exposes getPortfolio. Buy still withdraws before retrieving the portfolio, catch remains broad, failed orders have no reason, and the demo does not verify postconditions. Scope/use cases: Material gap; entities/responsibilities: Satisfactory; invariants/collaboration: Material gap; API/failure contracts: Material gap; abstraction restraint: Satisfactory; Java correctness: Material gap; testability/validation: Material gap; requirement change/concurrency/persistence: Not tested. |
