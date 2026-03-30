package study.neetcode.coreskills.sets.forest;

import java.util.Optional;
import java.util.Set;

public abstract class DisjointSets<T> {
    public abstract void makeSet(T a);

    public abstract Optional<T> findSet(T a);

    public abstract void union(T a, T b);

    public abstract int getSize();

    public abstract Set<T> getAllElementsOfSet(T a);
}
