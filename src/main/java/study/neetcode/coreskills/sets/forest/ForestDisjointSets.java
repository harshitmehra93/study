package study.neetcode.coreskills.sets.forest;

import static java.util.Objects.isNull;

import java.util.*;
import java.util.stream.Collectors;

public class ForestDisjointSets<T> extends DisjointSets<T> {
    Map<T, T> parentsMap = new HashMap<>();
    Set<T> elementSet = new HashSet<>();
    Map<T, Integer> roots = new HashMap<>();

    @Override
    public void makeSet(T a) {
        if (isNull(a)) throw new IllegalArgumentException("node cannot be null");
        if (parentsMap.containsKey(a)) throw new IllegalArgumentException("node already exists");
        parentsMap.put(a, a);
        elementSet.add(a);
        roots.put(a, 1);
    }

    @Override
    public Optional<T> findSet(T a) {
        if (isNull(a)) throw new IllegalArgumentException("node cannot be null");
        if (!parentsMap.containsKey(a)) return Optional.empty();
        if (parentsMap.get(a).equals(a)) {
            return Optional.of(a);
        }
        var root = findSet(parentsMap.get(a));
        if (root.isEmpty()) return Optional.empty();
        parentsMap.put(a, root.get());
        return root;
    }

    @Override
    public void union(T a, T b) {
        Optional<T> aRepresentative = findSet(a);
        Optional<T> bRepresentative = findSet(b);
        if (aRepresentative.isPresent() && bRepresentative.isPresent()) {
            int sizeOfA = roots.get(aRepresentative.get());
            int sizeOfB = roots.get(bRepresentative.get());
            var larger = sizeOfA > sizeOfB ? aRepresentative.get() : bRepresentative.get();
            var smaller = sizeOfA < sizeOfB ? aRepresentative.get() : bRepresentative.get();
            if (sizeOfB == sizeOfA) {
                larger = aRepresentative.get();
                smaller = bRepresentative.get();
            }
            parentsMap.put(smaller, larger);
            roots.put(larger, sizeOfA + sizeOfB);
            roots.remove(smaller);
        } else throw new IllegalArgumentException("node not present");
    }

    @Override
    public int getSize() {
        Set<T> uniqueSets = new HashSet<>();
        for (var node : elementSet) {
            uniqueSets.add(findSet(node).get());
        }
        return uniqueSets.size();
    }

    @Override
    public Set<T> getAllElementsOfSet(T a) {
        var aRepresentative = findSet(a);
        if (aRepresentative.isPresent()) {
            return elementSet.stream()
                    .filter(s -> findSet(s).get().equals(aRepresentative.get()))
                    .collect(Collectors.toSet());
        } else throw new IllegalArgumentException("node not present");
    }
}
