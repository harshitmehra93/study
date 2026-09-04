package study.ocp.chapter09;

import java.util.*;

class ListCreationAndMutability {
    public static void main(String... args) {
        Integer[] array = new Integer[] {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(array));

        List<Integer> asList = Arrays.asList(array);
        System.out.println(asList.toString());

        array[0] = 6;
        System.out.println(asList.toString());

        List<Integer> of = List.of(array);
        System.out.println(of.toString());

        List<Integer> copy = List.copyOf(of);
        System.out.println(copy.toString());
        // copy.add(1);

        List<Integer> list = new ArrayList<>();
        System.out.println(list.toString());
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(list.toString());
        list.replaceAll(x -> x * 2);
        System.out.println(list.toString());
    }
}
