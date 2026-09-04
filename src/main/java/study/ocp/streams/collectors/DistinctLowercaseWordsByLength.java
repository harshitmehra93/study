package study.ocp.streams.collectors;

import java.util.*;
import java.util.stream.*;

class DistinctLowercaseWordsByLength {
    public static void main(String... args) {
        List<String> a =
                List.of(
                        "   I",
                        "aaAAAaa",
                        "",
                        "   This",
                        "  IS  ",
                        "the",
                        "  part  ",
                        "where",
                        "    I",
                        "gain",
                        "good",
                        "  understanding  ",
                        "   of",
                        "  Streams  ",
                        "  AND  ",
                        "ace",
                        "  OCP",
                        "And   ",
                        "my",
                        "    career",
                        "    becomes",
                        "rocket",
                        "   AAAA",
                        "AAAA",
                        "AAaa",
                        "BBBB",
                        "bbbb");
        a = new ArrayList<String>(a);

        // Lowercase everything, remove blanks (after trim), count distinct.
        // System.out.println(countDistinctLowercaseWords(a));
        for (var entry : countDistinctLowercaseWords(a).entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    static Map<Integer, List<String>> countDistinctLowercaseWords(List<String> in) {
        return in.stream()
                .map(a -> a.trim().toLowerCase())
                .distinct()
                .collect(Collectors.groupingBy(a -> a.length()));
    }
}
