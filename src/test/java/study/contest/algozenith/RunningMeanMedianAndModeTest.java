package study.contest.algozenith;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.Test;

class RunningMeanMedianAndModeTest {

    @Test
    void matchesProvidedSample() {
        String input =
                """
                1
                12
                insert 4
                insert 3
                insert 5
                getMean
                getMedian
                getMode
                insert 4
                remove 3
                insert 5
                getMean
                getMedian
                getMode
                """;

        assertEquals(
                """
                4
                4
                3
                500000008
                500000008
                4
                """,
                run(input));
    }

    @Test
    void handlesDuplicatesRemovalsAndEmptyQueries() {
        String input =
                """
                1
                17
                insert 4
                insert 4
                insert 5
                insert 5
                getMean
                getMedian
                getMode
                remove 4
                getMean
                getMedian
                getMode
                remove 4
                remove 5
                remove 5
                getMean
                getMedian
                getMode
                """;

        assertEquals(
                """
                500000008
                500000008
                4
                666666676
                5
                5
                -1
                -1
                -1
                """,
                run(input));
    }

    @Test
    void handlesDifferentQueryCountsAcrossTestCases() {
        String input =
                """
                2
                1
                getMode
                5
                insert 1
                insert 2
                getMean
                getMedian
                getMode
                """;

        assertEquals(
                """
                -1
                500000005
                500000005
                1
                """,
                run(input));
    }

    @Test
    void avoidsOverflowAndKeepsDuplicateMedianValues() {
        String input =
                """
                1
                5
                insert 1000000000
                insert 1000000000
                insert 1000000000
                getMean
                getMedian
                """;

        assertEquals(
                """
                1000000000
                1000000000
                """,
                run(input));
    }

    @Test
    void matchesANaiveMultisetAcrossRandomUpdates() {
        RunningMeanMedianAndMode statistics = new RunningMeanMedianAndMode();
        List<Integer> values = new ArrayList<>();
        Random random = new Random(7_301_101L);

        for (int step = 0; step < 2_000; step++) {
            if (values.isEmpty() || random.nextBoolean()) {
                int number = random.nextInt(20) + 1;
                values.add(number);
                statistics.dynamicMean.insert(number);
                statistics.dynamicMedian.insert(number);
                statistics.dynamicMode.insert(number);
            } else {
                int index = random.nextInt(values.size());
                int number = values.remove(index);
                statistics.dynamicMean.remove(number);
                statistics.dynamicMedian.remove(number);
                statistics.dynamicMode.remove(number);
            }

            assertEquals(
                    expectedMean(values), statistics.dynamicMean.getMean(), "mean at step " + step);
            assertEquals(
                    expectedMedian(values),
                    statistics.dynamicMedian.getMedian(),
                    "median at step " + step);
            assertEquals(
                    expectedMode(values), statistics.dynamicMode.getMode(), "mode at step " + step);
        }
    }

    private static long expectedMean(List<Integer> values) {
        if (values.isEmpty()) {
            return -1;
        }

        long sum = values.stream().mapToLong(Integer::longValue).sum();
        return ((sum % RunningMeanMedianAndMode.MOD)
                        * RunningMeanMedianAndMode.modInverse(values.size()))
                % RunningMeanMedianAndMode.MOD;
    }

    private static long expectedMedian(List<Integer> values) {
        if (values.isEmpty()) {
            return -1;
        }

        List<Integer> sorted = new ArrayList<>(values);
        sorted.sort(Integer::compareTo);
        int middle = sorted.size() / 2;
        if ((sorted.size() & 1) == 1) {
            return sorted.get(middle);
        }

        long middleSum = (long) sorted.get(middle - 1) + sorted.get(middle);
        return ((middleSum % RunningMeanMedianAndMode.MOD) * RunningMeanMedianAndMode.modInverse(2))
                % RunningMeanMedianAndMode.MOD;
    }

    private static long expectedMode(List<Integer> values) {
        if (values.isEmpty()) {
            return -1;
        }

        Map<Integer, Integer> frequencies = new HashMap<>();
        int mode = Integer.MAX_VALUE;
        int highestFrequency = 0;
        for (int value : values) {
            int frequency = frequencies.merge(value, 1, Integer::sum);
            if (frequency > highestFrequency || frequency == highestFrequency && value < mode) {
                highestFrequency = frequency;
                mode = value;
            }
        }
        return mode;
    }

    private static String run(String input) {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        StringWriter output = new StringWriter();
        PrintWriter writer = new PrintWriter(output);

        RunningMeanMedianAndMode.fs = new RunningMeanMedianAndMode.FastScanner(inputStream);
        RunningMeanMedianAndMode.out = writer;
        RunningMeanMedianAndMode.solve();
        writer.flush();

        return output.toString();
    }
}
