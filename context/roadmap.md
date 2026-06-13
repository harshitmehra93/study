# Roadmap

`roadmap.md` is the phase-level planning summary. Use `questions.md` as the source of truth for exact problem status.

## Current Phase

Harshit is in **Phase 3: High-Frequency Pattern Installation + Rolling Recall**.

Active block: **Stack / Monotonic Stack**.

Current milestone: approximately **100+ / 133 roadmap problems completed/touched**.

Immediate DSA focus:

1. Finish Stack / Monotonic Stack.
2. Allow hard modeling-heavy problems like **Car Fleet** and **Largest Rectangle in Histogram** to remain yellow.
3. Move next into **Linked List**, then **Matrix**, **Binary Search**, **Tries**, and **Math / Bit**.
4. Add rolling recall now instead of waiting until the full first pass is complete.

---

## Current DSA Phase Map

| Phase | Goal | Status |
| --- | --- | --- |
| Phase 1 | Basic coding fluency | ✅ Done |
| Phase 2 | Core hard foundations: DP, Graphs, Backtracking, Trees, Heaps | ✅ Mostly done |
| Phase 3A | First-pass high-frequency pattern installation | 🟡 Active, late stage |
| Phase 3B | Rolling recall while finishing first pass | 🟡 Starting now |
| Phase 4 | Mixed timed practice | Not started |
| Phase 5 | Mock interview mode | Not started |

---

## Approximate Readiness

| Area | Current Status |
| --- | ---: |
| Basic coding fluency | 80-85% |
| Java implementation | 80-85% |
| Dynamic Programming | 70-75% |
| Graphs | 70-75% |
| Backtracking | 65-70% |
| Trees | 70-75% |
| Heaps / Priority Queue | 65-70% |
| Sliding Window | 70-75% coverage, some recall gaps |
| Two Pointers | 75-80% |
| Intervals | 70-75% coverage, some heap/greedy redo gaps |
| Greedy | 60-65% coverage, 45-55% ownership |
| Stack / Monotonic Stack | 🟡 Active, currently installing |
| Linked List | Pending |
| Matrix | Pending / partial overlap from graphs |
| Binary Search | Pending |
| Tries | Pending |
| Math / Bit | Pending |
| Pattern recall without labels | Early |
| Mixed timed interview practice | Not started |
| System design | Foundation exists, interview packaging immature |
| Behavioral stories | Strong raw material, needs packaging |
| Overall Google L5 readiness | ~30-40% |
| Overall Google L4 readiness | ~45-55% |

---

## Recently Completed / Advanced Blocks

### Greedy

| Problem | Status |
| --- | --- |
| Maximum Subarray | ✅ Kadane transition understood |
| Jump Game | ✅ `farthestReachable` frontier solution correct |
| Jump Game II | 🟡 BFS-level range compression understood, not fully owned |
| Gas Station | ✅ brute force correct; 🟡 greedy failed-prefix lemma not fully owned |
| Partition Labels | ✅ last-occurrence model understood; optimized boundary redo later |
| Hand of Straights | ✅ smallest remaining card greedy implemented |
| Merge Triplets to Form Target Triplet | ✅ safe-triplet feasibility solution implemented |

Important greedy methodology update:

Greedy must be learned **proof-first**, not as tricks:

1. Understand the objective.
2. Look at simple special cases.
3. Guess a local choice.
4. Break naive greedy choices with counterexamples.
5. Find the real safe choice.
6. Prove the choice using exchange, staying-ahead, induction, cut property, or elimination.
7. Convert the proof into code: sorting, heap, scan, frontier, or interval merge.

### Stack / Monotonic Stack

| Problem | Status |
| --- | --- |
| Valid Parentheses | ✅ Basic stack pattern |
| Min Stack | ✅ Auxiliary min-history stack solution implemented |
| Daily Temperatures | ✅ brute-force/reverse update correct; 🟡 monotonic stack redo pending |
| Next Greater Element I | ✅ monotonic waiting-stack solution implemented |
| Car Fleet | 🟡 standard solution written; intuition/modeling not fully owned |
| Largest Rectangle in Histogram | Pending / expected yellow |
| Online Stock Span | Pending |

Stack / monotonic stack learning hook:

> Stack problems are “waiting / resolution” problems.

Ask:

1. What is waiting?
2. What future/current event resolves it?
3. Should the stack store value or index?
4. When popping, what answer can be computed?
5. What remains unresolved gets default answer.

---

Current high-priority yellow redo list:

- Jump Game II
- Gas Station
- Partition Labels optimized version
- Daily Temperatures monotonic stack
- Car Fleet
- Largest Rectangle in Histogram
- Minimum Interval to Include Each Query
- Sliding Window Maximum
- Minimum Window Substring
- Serialize and Deserialize Binary Tree
- Find Median from Data Stream

---

## Remaining Phase 3 First-Pass Blocks

| Block | Status |
| --- | --- |
| Stack / Monotonic Stack | 🟡 Active |
| Linked List | Pending |
| Matrix | Pending |
| Binary Search | Pending |
| Tries | Pending |
| Math / Geometry / Bit | Pending |

Immediate next sequence:

1. Largest Rectangle in Histogram
2. Online Stock Span
3. Linked List block
4. Matrix block
5. Binary Search block
6. Tries
7. Math / Bit

---

## Phase 4: Mixed Timed Practice

Start after first pass is complete and rolling recall has begun.

Structure:

| Activity | Split |
| --- | ---: |
| Mixed timed problems | 70% |
| Targeted yellow redo | 30% |

Goal changes from:

> Can I learn this pattern?

to:

> Can I recognize the pattern without being told, solve in 30-40 minutes, explain clearly, and handle edge cases?

---

## Phase 5: Mock Interview Mode

Start after:

1. First pass is complete.
2. Rolling recall is active.
3. Initial mixed timed practice has started.

Initial mock cadence:

- 1 coding mock every 1-2 weeks.
- Increase frequency later.

For Google-level readiness, focus on:

- Pattern recognition
- Clean code
- Edge cases
- Communication
- Speed
- Calm debugging

---

## System Design Track

System design continues in parallel, but lighter than DSA.

Spine:

> **Interview Kickstart Scalable Systems**

Selective support:

- DDIA
- SRE book/workbook
- Alex Xu

Do not binge books linearly.

System design session format:

1. Attempt design first.
2. Write FR/NFR.
3. Estimate scale.
4. Define API and data model.
5. Design components.
6. Identify bottlenecks.
7. Discuss tradeoffs.
8. Then watch/read and patch gaps.

Current status:

- URL Shortener arc completed through SLO/SLA area.
- Partitioning and throughput basics covered.
- Need more active design reps.

Target:

> Around 3 system design sessions per week while DSA remains daily.

---

## Behavioral / Senior Stories Track

Story packaging should run slowly in parallel.

Target cadence:

> 1-2 sessions per week.

Story bank:

- PITR for OCI PostgreSQL
- Terraform provider GA early
- ARM migration
- AAIL / shape work
- On-call Sev2 handling
- Region launches
- Major version upgrade lessons

First story to polish:

> **PITR ownership story**

Use:

1. Situation
2. Task
3. Action
4. Result
5. Technical tradeoffs
6. Ambiguity
7. Leadership signal

---

## Work Execution Track

Daily minimum:

> One visible artifact per day.

Examples:

- Small PR
- Debug note
- Test result
- Status update
- Risk list
- Design note
- Next-step doc

Rule:

> Reduce ambiguity into one concrete artifact.

---

## Weekly Structure

| Track | Weekly Target |
| --- | --- |
| DSA recall + new problem | 4 days |
| Yellow redo / cleanup | 1 day |
| System design | 3 sessions |
| Behavioral story packaging | 1-2 sessions |
| Work execution | Daily visible artifact |

---

## Final Plan

Finish first-pass DSA while adding rolling recall, then transition into mixed timed practice, while system design and behavioral stories run in parallel, and daily work execution stays stable.

Current immediate action:

> Continue Stack / Monotonic Stack. Largest Rectangle can be yellow. Then finish Online Stock Span and move to Linked List.
