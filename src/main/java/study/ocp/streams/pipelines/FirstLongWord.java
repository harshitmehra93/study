package study.ocp.streams.pipelines;

import java.util.*;
import java.util.stream.*;

class FirstLongWord {
    public static void main(String... args) {
        List<String> a =
                List.of(
                        "   I",
                        "aaaaaaa",
                        "   This",
                        "  is  ",
                        "the",
                        "  part  ",
                        "where",
                        "    I",
                        "gain",
                        "good",
                        "  understanding  ",
                        "   of",
                        "  Streams  ",
                        "  and  ",
                        "ace",
                        "  OCP",
                        "and   ",
                        "my",
                        "    career",
                        "    becomes",
                        "rocket",
                        "   aaaa",
                        "aaaaa");
        a = new ArrayList<String>(a);

        // Return the first word with length ≥ minLen after trimming whitespace.
        System.out.println(firstLongWord(a, 1).orElse("no word with length found") + " -> 1");
        System.out.println(firstLongWord(a, 2).orElse("no word with length found") + " -> 2");
        System.out.println(firstLongWord(a, 3).orElse("no word with length found") + " -> 3");
        System.out.println(firstLongWord(a, 4).orElse("no word with length found") + " -> 4");
        System.out.println(firstLongWord(a, 5).orElse("no word with length found") + " -> 5");
        System.out.println(firstLongWord(a, 6).orElse("no word with length found") + " -> 6");
        System.out.println(firstLongWord(a, 7).orElse("no word with length found") + " -> 7");
        System.out.println(firstLongWord(a, 8).orElse("no word with length found") + " -> 8");
        System.out.println(firstLongWord(a, 9).orElse("no word with length found") + " -> 9");
        System.out.println(firstLongWord(a, 10).orElse("no word with length found") + " -> 10");
        System.out.println(firstLongWord(a, 11).orElse("no word with length found") + " -> 11");
        System.out.println(firstLongWord(a, 12).orElse("no word with length found") + " -> 12");
        System.out.println(firstLongWord(a, 13).orElse("no word with length found") + " -> 13");
        System.out.println(firstLongWord(a, 14).orElse("no word with length found") + " -> 14");
        System.out.println(firstLongWord(a, 15).orElse("no word with length found") + " -> 15");
        System.out.println(firstLongWord(a, 16).orElse("no word with length found") + " -> 16");
    }

    static Optional<String> firstLongWord(List<String> in, int min) {
        return in.stream().map(a -> a.trim()).filter(a -> a.length() >= min).findFirst();
    }
}
