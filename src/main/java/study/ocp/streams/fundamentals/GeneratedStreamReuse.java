package study.ocp.streams.fundamentals;

import java.util.*;
import java.util.stream.*;

class GeneratedStreamReuse {
    public static void main(String... args) {
        Stream<Double> inf = Stream.generate(Math::random).limit(1000);
        inf = Stream.generate(Math::random).limit(1000);
        inf.forEach(System.out::println);
        System.out.println("----");

        System.out.println(inf.findFirst());
    }
}
