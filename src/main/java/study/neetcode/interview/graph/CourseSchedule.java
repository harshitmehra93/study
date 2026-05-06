package study.neetcode.interview.graph;

import java.util.*;

public class CourseSchedule {
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

    private HashSet<Integer> visited;
    private int[] state;

    public boolean canFinishCourse(int numOfCourse, int[][] preReq) {
        state = new int[numOfCourse];
        Arrays.fill(state, 1);

        List<List<Integer>> adj = new ArrayList<>(); // course with no prereq will have null adj
        for (int i = 0; i < numOfCourse; i++) adj.add(i, new ArrayList<>());
        for (int i = 0; i < preReq.length; i++) {
            int course = preReq[i][0];
            int requirement = preReq[i][1];
            adj.get(course).add(requirement);
        }
        for (int i = 0; i < numOfCourse; i++) {
            if (state[i] == 1) {
                boolean hasBackEdge = dfsVisitAndCheckBackEdge(i, adj);
                if (hasBackEdge) return false;
            }
        }

        return true;
    }

    private boolean dfsVisitAndCheckBackEdge(int course, List<List<Integer>> adj) {
        System.out.printf("%d is in state %d\n", course, state[course]);
        if (state[course] == 2) { // grey
            return true; // found back edge
        }
        if (state[course] == 1) { // white
            state[course] = 2; // mark grey
            boolean hasBackEdge = false;
            for (var preReq : adj.get(course)) {
                hasBackEdge = dfsVisitAndCheckBackEdge(preReq, adj);
                if (hasBackEdge) return true;
            }
            state[course] = 3;
        }
        return false;
    }
}
