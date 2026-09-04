package study.ocp.chapter10; /*
                             OptionalInt middleSorted(IntStream s)
                             Sort ascending and return the middle element (for even count, return the lower-middle).

                             Example:
                             [5,1,9] → 5
                             [1,2,3,4] → 2

                             Constraints: must stay in streams. Hint: sorted(), toArray() is allowed for OCP-style.

                             */

import java.util.*;
import java.util.stream.*;

class SortedStreamMiddleElement {
    public static void main(String... args) {
        // IntStream str = IntStream.generate(()->(int)Math.random()*1000).limit(100);
        List<Integer> list =
                IntStream.generate(() -> (int) (Math.random() * 1000))
                        .limit(100)
                        .collect(LinkedList::new, (l, a) -> l.add(a), (l1, l2) -> l1.addAll(l2));
        List<Integer> unsortedList = new LinkedList<Integer>(list);

        Collections.sort(list);
        int middle = list.size() % 2 == 0 ? (list.size() / 2) - 1 : (list.size() / 2);
        System.out.println("actual middle = " + list.get(middle));

        System.out.println(
                "middle of stream = "
                        + middleSorted(unsortedList.stream().mapToInt(i -> i)).getAsInt());
    }

    static OptionalInt middleSorted(IntStream s) {
        return s.sorted().findAny();
    }
}
