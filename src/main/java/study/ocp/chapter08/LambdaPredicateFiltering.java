package study.ocp.chapter08;

import java.util.*;

public class LambdaPredicateFiltering {
    public static void main(String[] args) {
        Animal a = new Animal("a", true, true);
        Animal b = new Animal("b", true, false);
        Animal c = new Animal("c", false, true);
        Animal d = new Animal("d", false, false);
        Animal e = new Animal("e", true, true);
        Animal f = new Animal("f", true, false);
        Animal g = new Animal("g", false, true);
        Animal h = new Animal("h", false, false);
        List<Animal> animals = List.of(a, b, c, d, e, f, g, h);

        print(animals, i -> i.canHop());
        print(animals, i -> i.canSwim());
    }

    private static void print(List<Animal> animals, CheckTrait checker) {
        for (Animal animal : animals) {
            if (checker.test(animal)) System.out.println(animal + " ");
        }
    }
}

record Animal(String species, boolean canHop, boolean canSwim) {}

interface CheckTrait {
    boolean test(Animal a);
}
