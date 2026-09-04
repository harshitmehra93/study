package study.ocp.chapter09;

import java.util.*;

class HashSetOperations {
    public static void main(String... args) {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(4);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(4);
        System.out.println(set.size());
        System.out.println("==== print");
        set.forEach(System.out::println);
        System.out.println("====");

        set.remove(1);
        System.out.println(set.size());
        System.out.println(set.remove(5));
        System.out.println("==== print");
        set.forEach(System.out::println);
        System.out.println("====");
        System.out.println(set.size());

        set.removeIf((s) -> s % 2 == 0);
        System.out.println("==== print");
        set.forEach(System.out::println);
        System.out.println("====");

        // set.removeAll((s)->s%1==0);
        System.out.println(set.size());
        System.out.println("==== print");
        set.forEach(System.out::println);
        System.out.println("====");

        Set<Integer> copy = Set.copyOf(set);
        copy.add(6);
    }
}
