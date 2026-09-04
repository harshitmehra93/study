package study.ocp.concurrency.parallelstreams;

/*
List<Integer> squaresParallel(List<Integer> in)

Return squares using parallel stream, but must not mutate shared state.

Constraints: no external ArrayList + forEach. Use map + collect(toList()).
*/
import java.util.*;
import java.util.stream.*;

class ParallelSquareCollection {
    public static void main(String... args) {
        List<Integer> a = Stream.iterate(0, n -> n + 1).limit(100).collect(Collectors.toList());
        squaresParallel(a).forEach(System.out::println);
    }

    static List<Integer> squaresParallel(List<Integer> in) {
        return in.parallelStream().map(n -> n * n).collect(Collectors.toList());
    }
}
