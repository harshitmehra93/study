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

  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    Map<String, Node> emailToNode = new HashMap<>();

    for (var account : accounts) {
      var person = account.get(0);
      Node root = null;

      for (int i = 1; i < account.size(); i++) {
        var email = account.get(i);

        if (!emailToNode.containsKey(email)) {
          emailToNode.put(email, new Node(email, person));
        }

        Node emailNode = emailToNode.get(email);

        if (i == 1) {
          root = emailNode;
        } else {
          root.adjList.add(emailNode);
          emailNode.adjList.add(root);
        }
      }
    }

    List<List<String>> result = new ArrayList<>();
    Set<Node> visited = new HashSet<>();

    for (var node : emailToNode.values()) {
      if (!visited.contains(node)) {
        List<String> emails = new ArrayList<>();

        dfsVisit(node, visited, emails);

        Collections.sort(emails);

        List<String> mergedAccount = new ArrayList<>();
        mergedAccount.add(node.person);
        mergedAccount.addAll(emails);

        result.add(mergedAccount);
      }
    }

    return result;
  }

  private void dfsVisit(Node node, Set<Node> visited, List<String> emails) {
    if (visited.contains(node)) return;

    visited.add(node);
    emails.add(node.email);

    for (var nei : node.adjList) {
      dfsVisit(nei, visited, emails);
    }
  }

  public static class Node{
    String email;
    String person;
    List<Node> adjList = new ArrayList<>();

    Node(String email, String person){
      this.email=email;
      this.person=person;
    }

    @Override
    public boolean equals(Object obj) {
      if(obj instanceof Node node){
        return node.email.equals(email);
      }
      return false;
    }

    @Override
    public int hashCode(){
      return email.hashCode();
    }
  }

}
