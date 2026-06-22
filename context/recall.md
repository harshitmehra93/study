# Rolling Recall Plan

Use spaced recall for attempted problems in `questions.md`.

Also use `core_recall.md` for important algorithms and data structures practiced under `src/main/java/study/coreskills`.

Update the `Recall Status` column in `questions.md` using this format:

- `L1 ✅ yyyy-mm-dd` = explained pattern, invariant, and complexity
- `L2 ✅ yyyy-mm-dd` = wrote code skeleton
- `L3 ✅ yyyy-mm-dd` = full implementation
- `Lx review yyyy-mm-dd` = attempted but needs another recall

Give me full problem statements in the recall session and check my answer

For core-skills recall, give a concise prompt instead of a LeetCode statement:

- operation/algorithm to explain or implement
- invariant
- edge cases
- complexity
- one small example

## Recall Levels

| Level | Description |
| --- | --- |
| Level 1 | Explain pattern, invariant, and complexity |
| Level 2 | Write code skeleton |
| Level 3 | Full implementation |

Most days, Level 1 or Level 2 is enough.

## Recall Priority

Prioritize:

1. Yellow problems. I had started marking problems as yellow only after completing Heap section, so recall earlier green problems too.
2. Older solved problems with no recent recall.
3. High-frequency problems from the current or previous block.
4. Problems where the model was guided rather than independently discovered.
5. Core algorithms/data structures that support interview explanation: graph traversal, shortest paths, MST, DSU, heap, hash table, BST, trie, dynamic array, linked list/deque.

## Update Progress

After a LeetCode recall attempt, update the `Recall Status` column in `questions.md`.

After a core-skills recall attempt, update `context/core_recall.md`.
