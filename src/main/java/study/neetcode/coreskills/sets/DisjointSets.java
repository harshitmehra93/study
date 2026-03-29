package study.neetcode.coreskills.sets;

import java.util.List;
import java.util.Optional;

public abstract class DisjointSets<T> {
    public abstract Set<T> makeSet(T a);

    public abstract Optional<Set<T>> findSet(T a);

    public abstract Set<T> union(Set<T> a, Set<T> b);

    public abstract int getSize();

    public abstract List<Set<T>> getSets();
}
