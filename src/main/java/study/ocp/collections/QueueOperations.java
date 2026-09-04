package study.ocp.collections;

import java.util.*;

class QueueOperations {
    public static void main(String... args) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        q.offer(2);
        q.offer(3);
        q.offer(4);
        q.forEach(System.out::println);
        System.out.println(q.contains(2));
        System.out.println(q.peek());
        q.remove();
        q.remove();
        System.out.println(q.contains(2));
        q.forEach(System.out::println);
        // System.out.println(q.poll());
        // System.out.println(q.poll());
        // System.out.println(q.poll());
        // System.out.println(q.poll());
        // System.out.println(q.poll());
        // System.out.println(q.size());
        // System.out.println(q.contains(2));

        // q.remove();
    }
}
