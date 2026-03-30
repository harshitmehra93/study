package study.neetcode.coreskills.sets.forest;

import static java.util.Objects.isNull;

import java.util.*;
import java.util.stream.Collectors;

public class ForestDisjointSets<T> extends DisjointSets<T> {
    Map<T, T> parentsMap = new HashMap<>();
    Set<T> elementSet = new HashSet<>();

    @Override
    public void makeSet(T a) {
        if (isNull(a)) throw new IllegalArgumentException("node cannot be null");
        if (parentsMap.containsKey(a)) throw new IllegalArgumentException("node already exists");
        parentsMap.put(a, a);
        elementSet.add(a);
    }

    @Override
    public Optional<T> findSet(T a) {
        if (isNull(a)) throw new IllegalArgumentException("node cannot be null");
        if (!parentsMap.containsKey(a)) return Optional.empty();
        if (parentsMap.get(a).equals(a)) return Optional.of(a);
        return findSet(parentsMap.get(a));
    }

    @Override
    public void union(T a, T b) {
        Optional<T> aRepresentative = findSet(a);
        Optional<T> bRepresentative = findSet(b);
        if (aRepresentative.isPresent() && bRepresentative.isPresent()) {
            parentsMap.put(bRepresentative.get(), aRepresentative.get());

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
