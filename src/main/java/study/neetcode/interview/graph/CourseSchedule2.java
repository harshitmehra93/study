package study.neetcode.interview.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseSchedule2 {

    private int[] state;

    //    Problem: Course Schedule II
    //
    //    You are given numCourses courses labeled:
    //
    //            0 to numCourses - 1
    //
    //    You are also given prerequisites, where:
    //
    //            [a, b]
    //
    //    means:
    //
    //    To take course a, you must first take course b.
    //
    //    Return a valid order in which you can finish all courses.
    //
    //    If it is impossible to finish all courses, return an empty array.
    int[] findOrder(int numCourses, int[][] prerequisites) {
        state = new int[numCourses];
        Arrays.fill(state, 1);

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int prereq = prerequisites[i][1];
            adj.get(course).add(prereq);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 1) {
                boolean hasBackEdge = dfsVisitAndCheckBackEdge(i, adj, result);
                if (hasBackEdge) return new int[0];
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean dfsVisitAndCheckBackEdge(
            int course, List<List<Integer>> adj, List<Integer> result) {
        if (state[course] == 1) { // white
            state[course] = 2; // grey
            boolean hasBackEdge = false;
            for (int prereq : adj.get(course)) {
                hasBackEdge = dfsVisitAndCheckBackEdge(prereq, adj, result);
                if (hasBackEdge) return true;
            }
            state[course] = 3;
            result.add(course);
        }
        if (state[course] == 2) { // back edge
            return true;
        }
        return false;
    }
}
