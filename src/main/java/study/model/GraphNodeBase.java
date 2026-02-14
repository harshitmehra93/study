package study.model;

import java.util.Set;

public interface GraphNodeBase<T> {
    T getValue();

    Set<? extends GraphNodeBase<T>> getAdjacencyList();
}
