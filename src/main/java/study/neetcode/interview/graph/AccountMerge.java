package study.neetcode.interview.graph;

import java.util.*;

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
public class AccountMerge {

    private Set<Node> visited = new HashSet<>();
    ;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Node> nodes = new HashMap<>();
        for (List<String> account : accounts) {
            String person = account.get(0);
            String firstEmail = account.get(1);
            Node first;
            if (nodes.containsKey(firstEmail)) {
                first = nodes.get(firstEmail);
            } else {
                first = new Node(firstEmail, person);
            }
            nodes.put(firstEmail, first);
            for (int i = 2; i < account.size(); i++) {
                String email = account.get(i);
                Node node;
                if (nodes.containsKey(email)) {
                    node = nodes.get(email);
                } else {
                    node = new Node(email, person);
                }
                first.adjList.add(node);
                node.adjList.add(first);
                nodes.put(email, node);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (var node : nodes.values()) {

            if (!visited.contains(node)) {
                List<String> emails = new ArrayList<>();
                dfsVisit(node, emails);
                Collections.sort(emails);
                emails.add(0, node.person);
                result.add(emails);
            }
        }
        return result;
    }

    private void dfsVisit(Node node, List<String> emails) {
        if (visited.contains(node)) return;

        visited.add(node);
        emails.add(node.email);

        for (var nei : node.adjList) {
            dfsVisit(nei, emails);
        }
    }

    public static class Node {
        String email;
        List<Node> adjList = new ArrayList<>();
        String person;

        Node(String email, String person) {
            this.email = email;
            this.person = person;
        }
    }
}
