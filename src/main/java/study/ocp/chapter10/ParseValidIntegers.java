package study.ocp.chapter10; /*
                             13) Parse ints safely (skip invalid)
                             Write:

                             IntStream parseValidInts(Stream<String> s)

                             Keep only strings that are valid integers (e.g., "-10", "0", "42"), return as IntStream.

                             Constraints: no loops. You may use helper method boolean isInt(String).
                             */

import java.util.*;
import java.util.stream.*;

class ParseValidIntegers {
    public static void main(String... args) {
        String[] arr =
                "1 hello there 3 hello there 4 how do you do 98 this is my 1002 world 76"
                        .split(" ");
        List<String> list = Arrays.asList(arr);
        parseValidInts(list.stream()).forEach(System.out::println);
    }

    static IntStream parseValidInts(Stream<String> str) {
        return str.filter(s -> isInt(s)).mapToInt(s -> Integer.parseInt(s));
    }

    static boolean isInt(String s) {
        try {
            Integer i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            // System.out.println(s+" is not a number");
        }
        return false;
    }
}
