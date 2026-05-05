package study.neetcode.interview.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSchedule {
    private Map<Node, Integer> discoveryTime;
    private Map<Node, Integer> finishTime;
    //    Problem: Course Schedule

    //    You are given numCourses courses labeled:
    //
    //            0 to numCourses - 1
    //
    //    You are also given prerequisites, where:
    //
    //            [a, b]
    //
    //    means: To take course a, you must first take course b.
    //           Return true if you can finish all courses, otherwise return false.
    //
    //    Function signature
    //    boolean canFinish(int numCourses, int[][] prerequisites);
    public boolean canFinishCourse(int numOfCourse, int[][] preReq) {
        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 0; i < numOfCourse; i++) {
            var node = new Node(i);
            graph.put(i, node);
        }
        for (int i = 0; i < preReq.length; i++) {
            graph.get(preReq[i][0]).adj.add(graph.get(preReq[i][1]));
            if (preReq[i][0] == preReq[i][1]) return false; // self loop
        }

        discoveryTime = new HashMap<>();
        finishTime = new HashMap<>();
        int time = 0;
        for (var node : graph.values()) {
            if (!discoveryTime.containsKey(node)) {
                try {
                    dfsVisit(node, null, time);
                } catch (IllegalStateException e) {
                    return false;
                }
            }
        }
        return true;
    }

    private void dfsVisit(Node node, Node parent, int time) {
        if (!discoveryTime.containsKey(node)) { // white | tree
            time++;
            discoveryTime.put(node, time);
            for (var child : node.adj) {
                dfsVisit(child, node, time);
            }
            time++;
            finishTime.put(node, time);
        } else {
            if (finishTime.containsKey(node)) { // black | cross/forward
                if (discoveryTime.get(parent) < discoveryTime.get(node)) {
                    System.out.printf("%d -> %d forward", parent.name, node.name);
                }
                if (discoveryTime.get(parent) > discoveryTime.get(node)) {
                    System.out.printf("%d -> %d cross", parent.name, node.name);
                }
            } else { // grey | back
                throw new IllegalStateException("Back edge found");
            }
        }
    }

    public static class Node {
        int name;
        List<Node> adj = new ArrayList<>();

        Node(int name) {
            this.name = name;
        }
    }
}
