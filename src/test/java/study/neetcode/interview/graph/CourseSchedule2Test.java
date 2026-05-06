package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CourseSchedule2Test {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(int numCourses, int[][] prerequisites, boolean possible) {
        CourseSchedule2 solver = new CourseSchedule2();
        int[] order = solver.findOrder(numCourses, prerequisites);

        if (!possible) {
            assertEquals(0, order.length);
            return;
        }

        assertValidTopologicalOrder(numCourses, prerequisites, order);
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                // Basic
                Arguments.of(2, new int[][] {{1, 0}}, true),

                // Basic cycle
                Arguments.of(2, new int[][] {{1, 0}, {0, 1}}, false),

                // No prerequisites
                Arguments.of(3, new int[][] {}, true),

                // Simple chain: 0 -> 1 -> 2 -> 3
                Arguments.of(4, new int[][] {{1, 0}, {2, 1}, {3, 2}}, true),

                // Diamond:
                // 0 before 1 and 2, both before 3
                Arguments.of(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}}, true),

                // Disconnected acyclic components
                Arguments.of(6, new int[][] {{1, 0}, {2, 1}, {4, 3}, {5, 4}}, true),

                // Disconnected graph with one cycle
                Arguments.of(6, new int[][] {{1, 0}, {2, 1}, {4, 3}, {3, 4}}, false),

                // Self-cycle
                Arguments.of(1, new int[][] {{0, 0}}, false),

                // Multiple prerequisites for same course
                Arguments.of(5, new int[][] {{4, 0}, {4, 1}, {4, 2}, {4, 3}}, true),

                // Larger acyclic graph
                Arguments.of(
                        8,
                        new int[][] {
                            {1, 0},
                            {2, 0},
                            {3, 1},
                            {3, 2},
                            {4, 3},
                            {5, 3},
                            {6, 4},
                            {7, 5}
                        },
                        true),

                // Larger graph with cycle
                Arguments.of(
                        5,
                        new int[][] {
                            {1, 0},
                            {2, 1},
                            {3, 2},
                            {1, 3},
                            {4, 2}
                        },
                        false));
    }

    private static void assertValidTopologicalOrder(
            int numCourses, int[][] prerequisites, int[] order) {
        assertEquals(numCourses, order.length);

        Map<Integer, Integer> position = new HashMap<>();

        for (int i = 0; i < order.length; i++) {
            int course = order[i];

            assertTrue(course >= 0 && course < numCourses, "Invalid course number: " + course);

            assertFalse(position.containsKey(course), "Duplicate course in result: " + course);

            position.put(course, i);
        }

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];

            assertTrue(
                    position.get(prerequisite) < position.get(course),
                    "Prerequisite " + prerequisite + " should appear before course " + course);
        }
    }
}
