package study.ocp.collections;

import java.util.*;

class LinkedListOperations {
    public static void main(String... args) {
        List<String> set = new LinkedList<>();
        print(set.add("A"));
        print(set.add("B"));
        print(set.add("B"));
        print(set.add("C"));
        // set.forEach(System.out::println);
        set.removeIf(s -> s.startsWith("A"));
        set.remove("C");
        set.remove("B");
        set.forEach(System.out::println);
        set.clear();
        System.out.println("After clear");
        set.forEach(System.out::println);
    }

    static void print(Object o) {
        System.out.println(o);
    }
}
