package study.neetcode.coreskills.sets;

import java.util.LinkedList;
import java.util.List;

public class LinkedListSet<T> extends Set<T> {
    LinkedList<T> list = new LinkedList<>();

    @Override
    public void add(T a) {
        if (size() == 0) setRepresentative(a);
        list.add(a);
    }

    @Override
    public boolean contains(T a) {
        return list.contains(a);
    }

    @Override
    public boolean addAll(Set<T> b) {
        return list.addAll(b.getAllElements());
    }

    @Override
    public List<T> getAllElements() {
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }
}
