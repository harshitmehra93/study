package study.neetcode.coreskills.sets;

import java.util.List;

public abstract class Set<T> {
    private T representative;

    public abstract void add(T a);

    public abstract boolean contains(T a);

    public abstract boolean addAll(Set<T> b);

    public abstract List<T> getAllElements();

    public abstract int size();

    public T getRepresentative() {
        return representative;
    }

    public void setRepresentative(T rep) {
        representative = rep;
    }
}
