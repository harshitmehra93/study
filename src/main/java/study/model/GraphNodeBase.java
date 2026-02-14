package study.model;

import java.util.Set;

public interface GraphNodeBase<T> {
  T getValue();
  Set<GraphNodeBase<Integer>> getAdjacencyList();
}
