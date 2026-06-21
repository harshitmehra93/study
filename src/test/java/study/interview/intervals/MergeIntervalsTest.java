package study.interview.intervals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MergeIntervalsTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(
                        new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}},
                        new int[][] {{1, 6}, {8, 10}, {15, 18}},
                        "classic overlapping intervals"),
                new TestCase(
                        new int[][] {{1, 4}, {4, 5}},
                        new int[][] {{1, 5}},
                        "touching boundaries count as overlap"),
                new TestCase(
                        new int[][] {{1, 4}, {2, 3}},
                        new int[][] {{1, 4}},
                        "one interval fully contained inside another"),
                new TestCase(
                        new int[][] {{1, 4}, {0, 2}, {3, 5}},
                        new int[][] {{0, 5}},
                        "unsorted input with chain overlap"),
                new TestCase(
                        new int[][] {{1, 2}, {3, 4}, {5, 6}},
                        new int[][] {{1, 2}, {3, 4}, {5, 6}},
                        "no intervals overlap"),
                new TestCase(
                        new int[][] {{1, 10}, {2, 3}, {4, 5}, {6, 7}},
                        new int[][] {{1, 10}},
                        "large interval contains many smaller intervals"),
                new TestCase(
                        new int[][] {{5, 7}, {1, 3}, {2, 4}, {6, 8}},
                        new int[][] {{1, 4}, {5, 8}},
                        "multiple separate merged groups"),
                new TestCase(new int[][] {{1, 1}}, new int[][] {{1, 1}}, "single point interval"),
                new TestCase(
                        new int[][] {{1, 1}, {1, 1}},
                        new int[][] {{1, 1}},
                        "duplicate point intervals"),
                new TestCase(
                        new int[][] {{1, 2}, {2, 3}, {3, 4}},
                        new int[][] {{1, 4}},
                        "touching chain merges into one"),
                new TestCase(
                        new int[][] {{10, 12}, {1, 2}, {3, 5}, {4, 8}, {9, 10}},
                        new int[][] {{1, 2}, {3, 8}, {9, 12}},
                        "unsorted mixed overlaps and boundary touch"));
    }

    @ParameterizedTest(name = "{2}: intervals={0}")
    @MethodSource("testCases")
    void merge(TestCase testCase) {
        MergeIntervals solver = new MergeIntervals();

        int[][] actual = solver.merge(testCase.intervals);

        assertArrayEquals(testCase.expected, actual);
    }

    private record TestCase(int[][] intervals, int[][] expected, String description) {
        @Override
        public String toString() {
            return Arrays.deepToString(intervals);
        }
    }
}
