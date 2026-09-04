package study.ocp.chapter13; /*
                             15) Parallel reduce correctness
                             Write a method that sums lengths:

                             int parallelLengthSum(List<String> in)

                             It must work correctly in parallel.

                             Constraints: must use parallelStream() and a correct reduction.(If you use reduce, ensure identity + accumulator + combiner are correct.)

                             */

import java.util.*;
import java.util.stream.*;

class ParallelLengthReduction {
    public static void main(String... args) {
        String[] arr =
                "a hundred days have made me older since the last time that I saw your pretty face a thousand lives have made colder but I dont think I can look at this the same"
                        .split(" ");
        List<String> list = Arrays.asList(arr);

        int sum = list.parallelStream().reduce(0, (a, b) -> a + b.length(), (a, b) -> a + b);
        System.out.println(sum);
    }
}
