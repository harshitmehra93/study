package study.ocp.collections;

import java.util.*;

class DequeAsStack {
    public static void main(String... args) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.forEach(System.out::println);
        System.out.println("-----");

        stack.pop();
        stack.poll();

        stack.forEach(System.out::println);
        System.out.println("-----");

        stack.push(0);
        stack.push(10);

        stack.forEach(System.out::println);
        System.out.println("-----");
    }
}
