package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CourseScheduleTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(int numCourses, int[][] prerequisites, boolean expected) {
        CourseSchedule solver = new CourseSchedule();
        assertEquals(expected, solver.canFinishCourse(numCourses, prerequisites));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                // Basic possible
                Arguments.of(2, new int[][] {{1, 0}}, true),

                // Basic cycle
                Arguments.of(2, new int[][] {{1, 0}, {0, 1}}, false),

                // No prerequisites
                Arguments.of(3, new int[][] {}, true),

                // Simple chain: 0 -> 1 -> 2 -> 3
                Arguments.of(4, new int[][] {{1, 0}, {2, 1}, {3, 2}}, true),

                // Chain with cycle: 0 -> 1 -> 2 -> 0
                Arguments.of(3, new int[][] {{1, 0}, {2, 1}, {0, 2}}, false),

                // Diamond dependency:
                // 0 -> 1, 0 -> 2, 1 -> 3, 2 -> 3
                Arguments.of(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}}, true),

                // Disconnected components, all acyclic
                Arguments.of(6, new int[][] {{1, 0}, {2, 1}, {4, 3}, {5, 4}}, true),

                // Disconnected components, one has cycle
                Arguments.of(6, new int[][] {{1, 0}, {2, 1}, {4, 3}, {3, 4}}, false),

                // Self-cycle
                Arguments.of(1, new int[][] {{0, 0}}, false),

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

                // Larger graph with back edge cycle
                Arguments.of(
                        5,
                        new int[][] {
                            {1, 0},
                            {2, 1},
                            {3, 2},
                            {1, 3},
                            {4, 2}
                        },
                        false),

                // Multiple prerequisites for same course
                Arguments.of(
                        5,
                        new int[][] {
                            {4, 0},
                            {4, 1},
                            {4, 2},
                            {4, 3}
                        },
                        true),

                // Duplicate edge should not break correctness
                Arguments.of(
                        3,
                        new int[][] {
                            {1, 0},
                            {1, 0},
                            {2, 1}
                        },
                        true));
    }
}
