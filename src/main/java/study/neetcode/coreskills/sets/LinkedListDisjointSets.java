package study.neetcode.coreskills.sets;

import static java.util.Objects.isNull;

import java.util.*;

public class LinkedListDisjointSets<T> extends DisjointSets<T> {
    Map<T, Set<T>> elementToSetMap = new HashMap<>();
    List<Set<T>> sets = new ArrayList<>();

    @Override
    public Set<T> makeSet(T element) {
        if (isNull(element)) throw new IllegalArgumentException("cannot be null");
        if (findSet(element).isPresent())
            throw new IllegalArgumentException("set with element already exist");
        var set = new LinkedListSet<T>();
        set.add(element);
        elementToSetMap.put(element, set);
        sets.add(set);
        return set;
    }

    @Override
    public Optional<Set<T>> findSet(T a) {
        if (elementToSetMap.containsKey(a)) return Optional.of(elementToSetMap.get(a));
        return Optional.empty();
    }

    @Override
    public Set<T> union(Set<T> setA, Set<T> setB) {
        if (isNull(setA) || isNull(setB)) throw new IllegalArgumentException("set cannot be null");
        if (!sets.contains(setA) || !sets.contains(setB))
            throw new IllegalArgumentException("set does not exist");
        if (setA.equals(setB)) throw new IllegalArgumentException("cant union set with itself");

        var smallerSet = setA.size() < setB.size() ? setA : setB;
        var largerSet = setA.size() > setB.size() ? setA : setB;
        if (setA.size() == setB.size()) {
            largerSet = setA;
            smallerSet = setB;
        }
        largerSet.addAll(smallerSet);
        sets.remove(smallerSet);
        for (var element : smallerSet.getAllElements()) {
            elementToSetMap.put(element, largerSet);
        }
        return largerSet;
    }

    @Override
    public int getSize() {
        return sets.size();
    }

    @Override
    public List<Set<T>> getSets() {
        return sets;
    }
}
