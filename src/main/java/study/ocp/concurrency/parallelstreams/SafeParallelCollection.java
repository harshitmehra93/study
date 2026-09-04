package study.ocp.concurrency.parallelstreams; // 14️⃣ Reduction Danger
// Why is this dangerous?

// List<Integer> list = new ArrayList<>();
// IntStream.range(1, 1000)
//     .parallel()
//     .forEach(list::add);

// Fix it properly.
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

class SafeParallelCollection {
    public static void main(String... args) {
        ArrayList<Integer> list = new ArrayList<>();
        IntStream.range(1, 1000).parallel().forEach(list::add);
        System.out.println(list);
        System.out.println(list.size());

        // ArrayList<Integer> list2 = IntStream.range(1,
        // 1000).parallel().collect(ArrayList::new,(newList,element)->newList.add(element),(newList1,newList2)->newList1.addAll(newList2));
        List<Integer> list2 =
                IntStream.range(1, 1000).parallel().boxed().collect(Collectors.toList());
        System.out.println(list2);
        System.out.println(list2.size());
    }
}
