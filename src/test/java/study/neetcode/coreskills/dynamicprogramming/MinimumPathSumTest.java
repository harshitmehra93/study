package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MinimumPathSumTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(int[][] grid, int expected) {
        MinimumPathSum solver = new MinimumPathSum();
        assertEquals(expected, solver.minimumPathSum(grid));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(

                // Original small case
                Arguments.of(
                        new int[][] {
                            {1, 3, 1},
                            {1, 5, 1},
                            {4, 2, 1}
                        },
                        7),

                // Uniform grid
                Arguments.of(
                        new int[][] {
                            {1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1}
                        },
                        9),

                // Increasing rows
                Arguments.of(
                        new int[][] {
                            {1, 2, 3, 4, 5},
                            {1, 2, 3, 4, 5},
                            {1, 2, 3, 4, 5},
                            {1, 2, 3, 4, 5}
                        },
                        18),

                // Increasing columns
                Arguments.of(
                        new int[][] {
                            {1, 1, 1, 1},
                            {2, 2, 2, 2},
                            {3, 3, 3, 3},
                            {4, 4, 4, 4}
                        },
                        13),

                // Zig-zag optimal path
                Arguments.of(
                        new int[][] {
                            {1, 100, 1, 1},
                            {1, 100, 1, 100},
                            {1, 1, 1, 100},
                            {100, 100, 1, 1}
                        },
                        7),

                // Large value traps
                Arguments.of(
                        new int[][] {
                            {1, 1000, 1000, 1000},
                            {1, 1, 1, 1000},
                            {1000, 1000, 1, 1000},
                            {1000, 1000, 1, 1}
                        },
                        7),

                // Single row
                Arguments.of(new int[][] {{5, 4, 3, 2, 1}}, 15),

                // Single column
                Arguments.of(new int[][] {{5}, {4}, {3}, {2}, {1}}, 15),

                // Medium random grid
                Arguments.of(
                        new int[][] {
                            {7, 1, 3, 5, 8},
                            {3, 8, 1, 3, 2},
                            {5, 6, 7, 2, 4},
                            {2, 4, 3, 1, 6}
                        },
                        24));
    }

    @ParameterizedTest
    @MethodSource("testCases2")
    void test2(int[][] grid, int expected) {
        MinimumPathSum solver = new MinimumPathSum();
        assertEquals(expected, solver.minimumPathSum(grid));
    }

    static Stream<Arguments> testCases2() {
        return Stream.of(

                // 15x15 uniform grid
                Arguments.of(buildGrid(15, 15, 1), 29),

                // 20x20 uniform grid
                Arguments.of(buildGrid(20, 20, 1), 39),

                // 12x12 diagonal obstacle grid
                Arguments.of(buildDiagonalObstacleGrid(12), 2021),

                // Zig-zag path forcing decisions
                Arguments.of(
                        new int[][] {
                            {1, 100, 100, 100, 100, 100, 100},
                            {1, 1, 1, 1, 1, 1, 100},
                            {100, 100, 100, 100, 100, 1, 100},
                            {100, 1, 1, 1, 100, 1, 100},
                            {100, 1, 100, 1, 100, 1, 100},
                            {100, 1, 100, 1, 1, 1, 1}
                        },
                        12),

                // Wide grid (10x20)
                Arguments.of(buildGrid(10, 20, 1), 29),

                // Tall grid (20x10)
                Arguments.of(buildGrid(20, 10, 1), 29),

                // Random medium-heavy grid
                Arguments.of(
                        new int[][] {
                            {5, 9, 6, 3, 2, 1, 7, 8},
                            {4, 7, 2, 8, 1, 3, 9, 6},
                            {8, 1, 3, 2, 7, 6, 4, 5},
                            {3, 6, 9, 5, 8, 2, 1, 7},
                            {7, 2, 5, 1, 6, 9, 3, 4},
                            {6, 3, 1, 4, 9, 7, 2, 8},
                            {9, 8, 4, 7, 3, 5, 6, 1},
                            {2, 5, 7, 6, 4, 8, 1, 3}
                        },
                        53));
    }

    // Helper: uniform grid
    static int[][] buildGrid(int rows, int cols, int value) {
        int[][] grid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = value;
            }
        }
        return grid;
    }

    // Helper: diagonal obstacle grid
    static int[][] buildDiagonalObstacleGrid(int n) {
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = (i == j) ? 1000 : 1;
            }
        }
        return grid;
    }
}
