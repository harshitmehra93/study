# Progress Log

Use this file lightly. It is a memory aid, not a diary.

## Current Status

- Phase: Core Hard Foundations
- Active DSA block: Graphs
- DP status: Maintenance
- System design status: Early packaging practice
- Behavioral status: Raw material exists, story bank pending

## Completed Graph Problems

- Number of Islands
- Max Area of Island
- Rotting Oranges
- Clone Graph
- Course Schedule I
- Course Schedule II
- Pacific Atlantic Water Flow
- Surrounded Regions
- Number of Enclaves

## Immediate Next Problems

1. Accounts Merge
2. Redundant Connection
3. Network Delay Time
4. One light timed graph recall problem

## Session Note Format

Add entries like this:

```md
### YYYY-MM-DD - Topic

- Problem/design/story:
- Independent or guided:
- What went well:
- Main correction:
- Next recall:
```

## Latest Notes

### 2026-05-10 - Number of Enclaves

- Problem/design/story: Number of Enclaves
- Independent or guided: Independent implementation, reviewed after completion
- What went well: Correct boundary-source graph model; DFS marks land connected to the boundary, then unvisited land is counted as enclaves.
- Main correction: Recursive DFS is logically correct but can risk stack overflow on a large connected grid; tests should cover all-land, all-water, single row/column, and interior-only land cases.
- Next recall: Accounts Merge; keep graph model explicit before coding.
