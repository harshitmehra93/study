package study.interview.graph;

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

 */
public class AccountMerge {
    Map<String, Node> nodes;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // build graph with undirected edges
        nodes = new HashMap<>();
        for (List<String> account : accounts) {
            String person = account.get(0);
            Node rootNode = getOrCreate(account.get(1), person);
            for (int i = 2; i < account.size(); i++) {
                var v = getOrCreate(account.get(i), person);
                rootNode.adjList.add(v);
                v.adjList.add(rootNode);
            }
        }

        // do dfs on each node, and get a list of visited nodes
        // sort them and put it into result along with person name
        List<List<String>> result = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        for (var node : nodes.values()) {
            List<String> emails = new ArrayList<>();

            dfsVisit(node, visited, emails);
            if (emails.size() == 0) continue;

            Collections.sort(emails);
            result.add(buildAccount(node.person, emails));
        }
        return result;
    }

    private static List<String> buildAccount(String person, List<String> emails) {
        List<String> account = new ArrayList<>();
        account.add((person));
        for (var email : emails) account.add(email);
        return account;
    }

    private void dfsVisit(Node node, Set<Node> visited, List<String> emails) {
        if (visited.contains(node)) return;

        visited.add(node);
        emails.add(node.email);

        for (var nei : node.adjList) {
            dfsVisit(nei, visited, emails);
        }
    }

    private Node getOrCreate(String email, String person) {
        if (!nodes.containsKey(email)) nodes.put(email, new Node(email, person));
        return nodes.get(email);
    }

    public static class Node {
        String email;
        String person;
        List<Node> adjList = new ArrayList<>();

        Node(String email, String person) {
            this.email = email;
            this.person = person;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node node) {
                return node.email.equals(email);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return email.hashCode();
        }
    }
}
