package study.ocp.streams.primitivestreams;

import java.util.*;
import java.util.stream.*;

class AverageOfEvenNumbers {
    public static void main(String... args) {
        IntStream a = IntStream.iterate(0, (n) -> n + 1).limit(100);

        // OptionalDouble averageOfEven(IntStream s)
        // Compute average of even numbers only.
        System.out.println(averageOfEven(a).getAsDouble());
    }

    static OptionalDouble averageOfEven(IntStream s) {
        return s.filter(a -> a % 2 == 0).average();
    }
}
