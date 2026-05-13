package study.neetcode.interview.graph;

import java.util.*;
import java.util.stream.Collectors;

/*
Problem: Redundant Connection

You are given an undirected graph that started as a tree with n nodes labeled from 1 to n.

Then one extra edge was added.

The graph is given as an array edges, where:

edges[i] = [a, b]

means there is an undirected edge between node a and node b.

Return the edge that can be removed so that the graph becomes a tree again.

If there are multiple answers, return the edge that appears last in the input.

Example 1
edges = [
  [1, 2],
  [1, 3],
  [2, 3]
]

Output:

[2, 3]
 */
public class RedundantConnection {

    private HashSet<Node> visited;
    private List<List<Integer>> nonTreeEdges;
    private Map<Node, NodeColor> nodeColorMap;

    public List<Integer> getRedundantConnection(List<List<Integer>> graph){
        if(graph.size()==0) return new ArrayList<>();
        Map<Integer, Node> nodes = new HashMap<>();
        for(var edge : graph){
            Integer u = edge.get(0);
            Integer v = edge.get(1);
            if(!nodes.containsKey(u)) nodes.put(u,new Node(u));
            if(!nodes.containsKey(v)) nodes.put(v,new Node(v));
            Node U = nodes.get(u);
            Node V = nodes.get(v);
            U.adjList.add(V);
            V.adjList.add(U);
        }

        nodeColorMap = new HashMap<>();
        for(var node:nodes.values()){
            nodeColorMap.put(node,NodeColor.WHITE);
        }

        nonTreeEdges = new ArrayList<>();
        for(var node : nodes.values()){
            if(nodeColorMap.get(node)==NodeColor.WHITE)
                dfsVisit(node,null);
        }
        if(nonTreeEdges.size()>1){
            return getlastEdgeInInput(graph);
        }else if(nonTreeEdges.size()==1){
            return nonTreeEdges.get(0);
        }
        return new ArrayList<>();
    }

    private List<Integer> getlastEdgeInInput(List<List<Integer>> graph) {
        Collections.reverse(graph);
        return graph.stream()
                .filter(list -> IsInNonTreeEdges(list))
                .findFirst().get();
    }

    private boolean IsInNonTreeEdges(List<Integer> list) {
        for(var edge : nonTreeEdges){
            Integer u = edge.get(0);
            Integer v = edge.get(1);
            if(list.get(0)==u&&list.get(1)==v || list.get(0)==v&&list.get(1)==u) return true;
        }
        return false;
    }

    private void dfsVisit(Node node, Node parent) {
        System.out.printf("parent=%d node=%d\n",parent==null?0:parent.value,node.value);
        var nodeColor = nodeColorMap.get(node);
        if(nodeColor==NodeColor.WHITE) {
            nodeColorMap.put(node,NodeColor.GREY);
            for(var nei : node.adjList){
                if(nei!=parent)
                    dfsVisit(nei,node);
            }
            nodeColorMap.put(node,NodeColor.BLACK);
        }
        if(nodeColor==NodeColor.GREY||nodeColor==NodeColor.BLACK){
            nonTreeEdges.add(List.of(parent.value,node.value));
        }
    }

    public static class Node{
        Integer value;
        List<Node> adjList = new ArrayList<>();
        Node(int value){
            this.value = value;
        }

        @Override
        public boolean equals(Object obj){
            if(obj instanceof Node node){
                return node.value==value;
            }
            return false;
        }

        @Override
        public int hashCode(){
            return value.hashCode();
        }
    }

    enum NodeColor{
        WHITE,
        GREY,
        BLACK;
    }
}
