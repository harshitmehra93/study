package study.neetcode.interview.graph;
/*
**Accounts Merge**

You are given a list of accounts. Each account is a list of strings:

```text
[name, email1, email2, email3, ...]
```

The first string is the person’s name. The remaining strings are email addresses.

Two accounts belong to the same person if they share **at least one common email address**.

Return the merged accounts. Each merged account should contain:

```text
[name, sorted unique emails...]
```

The emails in each merged account must be sorted lexicographically. The returned list can be in any order.

Example:

```text
Input:
accounts = [
  ["John","johnsmith@mail.com","john_newyork@mail.com"],
  ["John","johnsmith@mail.com","john00@mail.com"],
  ["Mary","mary@mail.com"],
  ["John","johnnybravo@mail.com"]
]

Output:
[
  ["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
  ["Mary","mary@mail.com"],
  ["John","johnnybravo@mail.com"]
]
```

Explanation:

The first two John accounts share `johnsmith@mail.com`, so they are merged.

Before coding, give me the graph model:

- Nodes
- Edges
- Source(s)
- Target / success condition
- Natural or reversed direction
- Traversal
- Visited meaning
- Failure condition
- Complexity
 */
public class AccountMerge {}
