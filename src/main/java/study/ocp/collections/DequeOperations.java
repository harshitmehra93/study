package study.ocp.collections;

import java.util.*;

class DequeOperations {
    public static void main(String... args) {
        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(1);
        deque.offerFirst(2);
        deque.addLast(3);
        deque.offerLast(4);
        deque.forEach(System.out::println);
        System.out.println("-----");

        deque.pollFirst();
        deque.pollLast();

        deque.forEach(System.out::println);
        System.out.println("-----");

        deque.offerFirst(0);
        deque.offerLast(10);

        deque.forEach(System.out::println);
        System.out.println("-----");
    }
}
