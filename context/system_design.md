# HLD And LLD System Design

This file owns live HLD and LLD coaching behavior, the stable preparation plan,
phase gates, and design-readiness rules. `context/startup_protocol.md` owns
routing and assistance vocabulary, `context/design_and_behavioral.md` owns
attempt evidence, and `context/roadmap.md` owns only current priorities and
meaningful phase or readiness changes.

HLD and LLD progress independently and may be interleaved with each other and
with DSA according to capacity. Completing material or practice hours does not
advance a phase; the completed diagnostic and qualifying evidence do.

## Session Setup

Before presenting a design prompt, establish:

1. `Track`: `HLD` or `LLD`.
2. `Phase`: the applicable phase in this plan.
3. `Attempt`: `Diagnostic`, `Learning`, `Gate`, `Mock`, `Repair`, `Redo`,
   `Delayed Reattempt`, or `Drill`.
4. Prompt eligibility: fresh, materially different, or an explicit redo.
5. Timebox, or `untimed` when appropriate.
6. Expected deliverable.

For LLD, state whether the deliverable is object/API design, a critical
runnable Java slice, focused tests, or all three. For HLD, state the expected
depth and any interviewer-selected deep dive.

Use HLD when the dominant work is distributed architecture, capacity, storage,
reliability, or cross-service correctness. Use LLD when it is in-process object
modelling, APIs, state transitions, Java implementation, or local concurrency.
When the expected deliverable does not resolve an overlap, ask one focused
track question before starting.

Theory or revision without a design prompt uses the relevant branch and phase
material but creates no attempt row unless Harshit demonstrates work under a
declared assessment contract.

### Prompt Eligibility

For a coach-selected gate or mock:

1. Check `context/design_and_behavioral.md` for prior exposure.
2. Use an unseen or materially different gate; readiness mocks must be unseen.
3. Reveal only interview-visible requirements.
4. Replace a recognized prompt or label it `Redo`.

`Materially different` means that the domain, dominant design risk, or core
requirements have changed enough that the earlier design cannot simply be
replayed. Cosmetic renaming, reordered wording, or a small add-on requirement
does not qualify.

Named gate prompts below are examples, not a required queue.

## Attempt And Review

Use the shared attempt boundary. Let Harshit drive the relevant branch and one
phase-relevant deep dive. Neutral deep-dive, failure, or requirement-change
probes remain `Help=None`; steering is `Nudge`. Recorded help may continue as an
assisted attempt, but close before canonical teaching or direct repair. After
closure, compare alternatives and record one material gap and next action.

## HLD Branch

Use the interview time selectively; do not force equal depth in every area.

1. Clarify functional requirements, non-functional priorities, scope, and the
   most important user journeys.
2. Estimate traffic, storage, bandwidth, object sizes, or growth only where the
   figures affect a design choice.
3. Define external APIs or events, core data, access patterns, and ownership.
4. Propose the main components and end-to-end data flow.
5. Justify storage, caching, partitioning, replication, and asynchronous
   boundaries.
6. Deep-dive on phase-relevant correctness: ordering, duplicate delivery,
   idempotency, consistency, transactions, lag, or hot partitions.
7. Examine bottlenecks, dependency failure, overload, recovery, and regional
   failure as appropriate.
8. Cover reliability, capacity, observability, operational response, and
   disaster recovery at the depth justified by the prompt.
9. Compare one credible alternative and explain the trade-off.

Review only applicable dimensions as `Satisfactory`, `Material gap`, or `Not
tested`:

- requirement prioritization and interview structure
- quantitative reasoning
- APIs, data, and access patterns
- architecture and data flow
- storage and scaling choices
- distributed correctness
- bottleneck and failure reasoning
- reliability, observability, and operations
- alternatives, trade-offs, and communication

Judge a design by correctness, internal consistency, prioritization, and
defended trade-offs—not by whether it reproduces one reference architecture.

## LLD Branch

Scale estimates and distributed architecture are not mandatory unless the
prompt makes them relevant.

1. Clarify use cases, actors, constraints, out-of-scope behavior, and the
   critical flow to implement.
2. Identify entities, value objects, state, and lifecycle boundaries.
3. Allocate responsibilities and define important collaborations.
4. State invariants, legal transitions, and how invalid mutation is prevented.
5. Define coherent public APIs, error behavior, and extension contracts.
6. Choose composition, inheritance, interfaces, and patterns deliberately;
   avoid speculative abstractions and central type-based conditionals.
7. Implement the agreed critical Java flow when code is part of the contract.
8. Add or describe focused happy-path, edge, failure, and invalid-transition
   tests at the required depth.
9. When the phase or session contract requires it, introduce the declared
   number of requirement changes and assess whether the design extends cleanly.
10. For concurrent or persistent components, define shared state, protected
    invariants, synchronization and lock scope, deadlock risks, shutdown, and
    transaction or durability boundaries.

During an independent implementation attempt, Harshit may compile, run, test,
and debug his own work. The coach must not generate or modify the solution code
before the attempt closes. After closure, inspect or run focused verification
when an implementation artifact exists and doing so helps the review.

Review only applicable dimensions as `Satisfactory`, `Material gap`, or `Not
tested`:

- scope and use cases
- entities, value objects, and responsibilities
- invariants, state, and collaboration
- API and failure contracts
- abstraction and pattern restraint
- critical Java correctness and clarity
- testability and focused validation
- requirement-change handling
- concurrency and persistence boundaries
- trade-offs and interview explanation

## Outcomes, Gates, And Readiness

Use the outcome definitions in `context/design_and_behavioral.md`. Outcome,
help, and timing remain separate.

### Attempt Qualification

- A recorded `Diagnostic` advances Phase 0 to Phase 1 regardless of result but
  never satisfies a later gate.
- `Learning` and `Repair` may be guided and do not satisfy gates.
- A gate qualifies only when the prompt is unseen or materially different,
  `Outcome=Pass`, `Help=None`, and every mandatory phase criterion is
  satisfactory, plus inside the timebox when declared timed. A qualifying gate
  advances the track; a failed gate creates targeted repair and another gate.
- A satisfactory mock is unseen, timed, `Outcome=Pass`, `Help=None`, and has no
  required-dimension gap.
- Qualifying gates and mocks retain the compact rubric or artifact required by
  `context/design_and_behavioral.md`.
- A `Redo` is a known repeat. A qualifying `Delayed Reattempt` links a prior
  material gap, follows repair and a meaningful interval, and passes the
  relevant skill with `Help=None`; it never replaces the original row.

Count HLD and LLD mock sequences separately. Only unseen mocks count; other
work neither counts nor interrupts the sequence, while a later failed mock
resets it.

Final readiness requires prior gates, six unseen timed mocks, three qualifying
delayed reattempts, and the latest three unseen mocks being satisfactory.

## Evidence Recording

Record each assessed attempt once in `context/design_and_behavioral.md`.

## LLD Preparation Plan

### Phase 0 — Diagnostic

Attempt `Parking Lot` without preparation in 60–90 minutes. The deliverable is
an object model and public APIs, one critical runnable Java flow, focused tests
for that flow, and a brief explanation of the main invariants and trade-offs.
Record gaps in:

- requirement clarification
- object identification
- responsibility allocation
- API design
- Java implementation
- testability

This is a baseline, not a gate. The later Parking Lot learning session is a
known revisit, not fresh evidence.

### Phase 1 — Object Modelling And Java Design

Material:

- Full IK Object Modelling block: UML, object modelling, API design, SOLID,
  Design Patterns Essentials, and MCQs.
- Targeted OCP Java 17: selected Chapter 4 Core APIs; Chapters 5, 6, and 7;
  Chapter 11 exceptions; quick Chapter 1 recall.

Learning problems:

- Library Management
- Parking Lot
- Tic-tac-toe

Gate: attempt an unseen basic modelling problem such as Vehicle Rental, Course
Registration, or a Movie-ticket Kiosk.

Mandatory gate criteria:

- clarify scope
- identify entities and value objects
- assign responsibilities
- define coherent APIs
- use composition and inheritance deliberately
- implement the critical Java flow
- add focused tests for that flow

### Phase 2 — Behaviour, State, And Domain Rules

Material:

- Head First Design Patterns: Strategy, Factory, Observer, and State.
- Uncle Bob only when repair requires SRP, OCP, LSP, or polymorphism.
- Targeted OCP Java 17: Chapters 8, 9, and 10; date/time and Core APIs from
  Chapter 4; exception design from Chapter 11; quick Chapters 2–3 recall.

Learning problems:

- Vending Machine
- Splitwise
- Meeting Scheduler
- Elevator

Gate: attempt an unseen stateful or rule-heavy problem such as an Order
Lifecycle, Document Approval system, or Tournament Manager.

Mandatory gate criteria:

- define invariants and valid transitions
- prevent invalid state mutation
- choose Strategy or State only when justified
- implement and test the core behaviour
- handle one changed requirement

### Phase 3 — Extensible Components And Frameworks

Material:

- Head First Design Patterns: Decorator, Command, Adapter, Facade, Template
  Method, Composite, and selectively Proxy.
- Targeted Uncle Bob repair: ISP, DIP, boundaries, and TDD/testability.
- Targeted OCP Java 17: Chapter 5 API-design revisit; Chapter 7 interface
  evolution; Chapter 8 callbacks and functional policies; Chapter 9 advanced
  generics; Chapter 10 applied streams; Chapter 11 exception translation; and
  selected Chapter 14 I/O.

Learning problems:

- Notification Framework
- Logging Framework
- Payment Processing Framework
- File-processing Pipeline

Gate: attempt an unseen framework problem such as a Rule Engine,
Event-listener Framework, or Plugin-based Document Processor.

Mandatory gate criteria:

- add implementations without modifying the core
- avoid central type-based conditionals
- define clean extension and failure contracts
- justify every abstraction
- handle two requirement changes
- avoid unnecessary patterns
- implement and test a critical extension or failure flow

### Phase 4 — Concurrent And Persistent Components

Material:

- Full IK Concurrency: mutexes, conditions, semaphores, atomics,
  reader-writer locks, deadlocks, and producer-consumer/pub-sub concepts.
- Targeted OCP Java 17: Chapter 13 concurrency; Chapter 14 I/O and resource
  handling; Chapter 15 JDBC; Chapter 11 try-with-resources; cautious Chapter 10
  parallel-stream review.

Learning problems:

- Thread-safe Cache
- Bounded Blocking Queue
- Connection Pool
- Local Rate Limiter
- Job Scheduler
- Seat Reservation Manager

Gate: attempt an unseen concurrent component such as a Worker Pool, Resource
Allocator, or In-memory Pub/Sub Broker.

Mandatory gate criteria:

- identify shared state and protected invariants
- define synchronization policy and lock scope
- explain deadlock risks
- handle cancellation and shutdown
- define persistence or transaction boundaries where relevant
- implement and test a critical concurrent flow

### Phase 5 — Interview Conversion

Satisfy the shared readiness rule above; use requirement-change rounds and
targeted repair as evidence indicates.

## HLD Preparation Plan

### Phase 0 — Diagnostic

Attempt `URL Shortener` without preparation in 45–60 minutes. Record gaps in:

- requirements
- scale estimation
- APIs
- data modelling
- architecture
- bottlenecks
- failure analysis

This is a baseline, not a gate. The later URL Shortener learning session is a
known revisit, not fresh evidence.

### Phase 1 — Scalable-system Foundations

Material:

- Full IK Scalable Systems 1: requirements and scale, APIs and data models,
  stateless services, storage choices, caching, load balancing, replication,
  partitioning, queues, asynchronous processing, and basic reliability.
- DDIA support: Chapter 3 recall, Chapter 5 Replication, and Chapter 6 recall.

Learning problems:

- URL Shortener
- Pastebin or Document Service
- Distributed Rate Limiter
- Notification Service
- File or Object Storage

Gate: attempt an unseen basic scalable service such as an Image-sharing
Backend, Product Catalogue, or Link-preview Service.

Mandatory gate criteria:

- structure the interview
- estimate scale
- define APIs and a data model
- choose storage and caching sensibly
- select a partition key
- explain replication and basic failure handling

### Phase 2 — Distributed Correctness

Material:

- DDIA Chapter 7 Transactions
- DDIA Chapter 8 Trouble with Distributed Systems
- DDIA Chapter 9 Consistency and Consensus
- Matching IK review only where needed; do not rewatch everything.

Learning problems:

- Chat System
- News Feed
- Search or Autocomplete
- Booking System
- Payment or Order Workflow

Gate: attempt an unseen distributed system involving real-time communication,
fan-out, indexing, or consistency-sensitive writes.

Mandatory gate criteria:

- reason about ordering and duplicate delivery
- define idempotency
- account for replication lag
- handle hot keys and partitions
- choose transactions or eventual consistency deliberately
- handle partial dependency failure

### Phase 3 — Advanced Systems And Operations

Material:

- Full IK Scalable Systems 2.
- DDIA Chapter 11 Stream Processing.
- DDIA Chapter 10 Batch Processing at lower priority.
- DDIA Chapter 12 synthesis only when useful.

Learning problems:

- Distributed Job Scheduler
- Metrics or Logging Pipeline
- Video-processing Platform
- Payment System
- Multi-region Control Plane

Use relevant OCI experience for work requests, desired versus actual state,
reconciliation, retry and idempotency, garbage collection, regional failure,
PITR and recovery, rollback, and observability.

Gate: attempt an unseen failure-heavy system such as a Workflow Orchestration
Platform, Multi-region Configuration Service, or Large Event-processing
System.

Mandatory gate criteria:

- handle backpressure and retry storms
- define deduplication and worker recovery
- reason about regional failover and disaster recovery
- cover capacity and overload
- cover observability and operational response

### Phase 4 — Interview Conversion

Satisfy the shared readiness rule above; use failure deep-dives, alternative
designs, and targeted repair as evidence indicates.

## Planning Envelope

These are planning ranges, not advancement criteria.

LLD:

| Item | Hours |
| --- | ---: |
| IK Object Modelling, API Design, SOLID, and Patterns | 3h 35m+ |
| IK Concurrency | 8h 46m+ |
| Selected Head First Design Patterns chapters | 9–11h |
| Targeted OCP Java 17 revision | 8–12h |
| Uncle Bob targeted repair or revision | 2–4h |
| Pre-mock LLD problem practice | 40–62h |

HLD:

| Item | Hours |
| --- | ---: |
| IK Scalable Systems 1 | 9h 56m |
| IK Scalable Systems 2 | 6h 40m |
| Selected DDIA chapters and recall | About 11h 51m |
| Pre-mock HLD problem practice | 30–46h |

| Track | Material and revision | Pre-mock problem practice | First-pass subtotal |
| --- | ---: | ---: | ---: |
| LLD | 31h 21m–39h 21m | 40–62h | 71h 21m–101h 21m |
| HLD | 28h 27m | 30–46h | 58h 27m–74h 27m |
| Combined | About 60–68h | 70–108h | About 130–176h |

Approximately 150 focused hours is a sensible midpoint for the core curriculum
and pre-mock first pass. At different weekly commitments, that range is about:

| Weekly effort | First-pass duration |
| ---: | ---: |
| 8 hours | 16–22 weeks |
| 10 hours | 13–18 weeks |
| 15 hours | 9–12 weeks |
| 20 hours | 7–9 weeks |

The range excludes untimed IK resources, assignments, the twelve required
design mocks, six delayed reattempts, requirement-change and alternative-design
drills, delayed recall, and contingent repair. Reserve roughly another 25–35
hours for the named interview-conversion sessions and their basic reviews;
readiness repair remains open-ended.

To keep the LLD practice range credible, every learning problem should receive
a design, API, invariant, and review pass. Require a complete runnable Java
slice with focused tests for a representative eight to ten learning problems
and every LLD exit gate. If every learning problem receives a full
implementation, revise the LLD practice range upward.

Final stack:

- Mandatory: IK, selected Head First, targeted OCP revision, selected DDIA, and
  problem practice.
- On demand: Uncle Bob repair.
- Progress measure: independent designs, gates, and mocks—not viewing hours.
