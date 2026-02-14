package study.model;

import java.util.List;
import java.util.Set;

public interface GraphNodeBase<T> {
    T getValue();

    Set<? extends GraphNodeBase<T>> getAdjacencyList();
}
