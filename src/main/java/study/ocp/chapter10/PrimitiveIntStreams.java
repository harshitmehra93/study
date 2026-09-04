package study.ocp.chapter10;

import java.util.*;
import java.util.stream.*;

class PrimitiveIntStreams {
    public static void main(String... args) {
        IntStream s = IntStream.of(1);
        // s.forEach(System.out::println);

        IntStream p = IntStream.rangeClosed(1, 5);
        // System.out.println(p.sum());

        IntStream q = new Random().ints();
        q.limit(200000).filter(i -> i % 2 == 0).filter(j -> j > 0).forEach(System.out::println);
    }
}
