package study.contest.algozenith;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class SupportQueriesIITest {

    @Test
    void matchesProvidedSample() {
        String input =
                """
                17 3
                1 5
                1 2
                1 3
                1 6
                3 ?
                2 3
                3 ?
                2 2
                3 ?
                2 2
                3 ?
                2 5
                3 ?
                2 5
                3 ?
                2 6
                3 ?
                """;

        assertEquals(
                """
                14
                13
                11
                11
                6
                6
                0
                """,
                run(input));
    }

    @Test
    void sumsEverythingWhenFewerThanKElementsExist() {
        String input =
                """
                8 5
                3 ?
                2 42
                1 4
                1 2
                3 ?
                2 4
                3 ?
                2 2
                """;

        assertEquals(
                """
                0
                6
                2
                """,
                run(input));
    }

    @Test
    void supportsDuplicateValues() {
        SupportQueriesII.TopKSum topKSum = new SupportQueriesII.TopKSum(2);

        topKSum.insert(5);
        topKSum.insert(5);
        topKSum.insert(5);
        assertEquals(10, topKSum.getSumOfTopK());

        topKSum.remove(5);
        assertEquals(10, topKSum.getSumOfTopK());

        topKSum.remove(5);
        assertEquals(5, topKSum.getSumOfTopK());

        topKSum.remove(5);
        assertEquals(0, topKSum.getSumOfTopK());
    }

    @Test
    void usesLongForLargeSums() {
        SupportQueriesII.TopKSum topKSum = new SupportQueriesII.TopKSum(3);

        topKSum.insert(1_000_000_000);
        topKSum.insert(1_000_000_000);
        topKSum.insert(1_000_000_000);

        assertEquals(3_000_000_000L, topKSum.getSumOfTopK());
    }

    private static String run(String input) {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        StringWriter output = new StringWriter();
        PrintWriter writer = new PrintWriter(output);

        SupportQueriesII.solve(new SupportQueriesII.FastScanner(inputStream), writer);
        writer.flush();

        return output.toString();
    }
}
