package study.ocp.chapter09;

import java.util.*;

class ComparatorChaining {
    public static void main(String... args) {
        var d1 = new Dinosaur("TREX", 20, 676);
        var d2 = new Dinosaur("TREX", 2, 234);
        var d3 = new Dinosaur("TREX", 456, 234);
        var d4 = new Dinosaur("HIPPOSAURUS", 456, 676);
        var d5 = new Dinosaur("HIPPOSAURUS", 5768, 457);
        var d6 = new Dinosaur("HELLONASAURUS", 4367, 3234);
        var d7 = new Dinosaur("DOGASAURUS", 4367, 234);
        var d8 = new Dinosaur("DOGASAURUS", 5678, 378);
        var d9 = new Dinosaur("BIRDASAURUS", 23, 378);
        List<Dinosaur> dinos = new ArrayList<>();
        dinos.add(d1);
        dinos.add(d2);
        dinos.add(d3);
        dinos.add(d4);
        dinos.add(d5);
        dinos.add(d6);
        dinos.add(d7);
        dinos.add(d8);
        dinos.add(d9);

        dinos.forEach(System.out::println);

        System.out.println();
        System.out.println();
        System.out.println();

        Comparator<Dinosaur> nameComparator = Comparator.comparing(Dinosaur::getName);
        Comparator<Dinosaur> ageComparator = Comparator.comparing(Dinosaur::getAge);
        Comparator<Dinosaur> heightComparator = Comparator.comparing(Dinosaur::getHeight);
        Comparator<Dinosaur> compoundComparator =
                Comparator.comparing(Dinosaur::getName)
                        .thenComparing(Dinosaur::getAge)
                        .thenComparing(Dinosaur::getHeight)
                        .reversed();

        Collections.sort(dinos, nameComparator);

        dinos.forEach(System.out::println);

        System.out.println();
        System.out.println();
        System.out.println();

        Collections.sort(dinos, ageComparator);

        dinos.forEach(System.out::println);

        System.out.println();
        System.out.println();
        System.out.println();

        Collections.sort(dinos, heightComparator);

        dinos.forEach(System.out::println);

        System.out.println();
        System.out.println();
        System.out.println();

        Collections.sort(dinos, compoundComparator);

        dinos.forEach(System.out::println);

        System.out.println();
        System.out.println();
        System.out.println();
    }
}

class Dinosaur {
    String name;
    Integer height;
    Integer age;

    Dinosaur(String name, int height, int age) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String toString() {
        return "name=" + name + " age=" + age + " height=" + height;
    }

    public String getName() {
        return name;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getAge() {
        return age;
    }
}
