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

  private Map<String, String> emailToPersonMap;
  private HashSet<Node> visited;
  private HashMap<String, Set<Node>> personToEmails;
  private HashMap<String, Node> nodes;
  int connectedComponents = 0;
  private Map<Node, Integer> components;

  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    nodes = new HashMap<>();
    personToEmails = new HashMap<>();
    for(var account : accounts){
      String person = account.get(0);
      for(int i=1;i<account.size();i++){
        String email = account.get(i);
        if(!nodes.containsKey(email)) {
          nodes.put(email,new Node(email,person));
        }
        if(!personToEmails.containsKey(person)) personToEmails.put(person, new HashSet<>());
        personToEmails.get(person).add(nodes.get(email));
      }
    }

    visited = new HashSet<>();
    components = new HashMap<>();
    for(var node: nodes.values()){
      if(!visited.contains(node)){
        dfsVisit(node);
      }
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
