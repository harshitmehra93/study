package study.neetcode.coreskills.graph;

import java.util.HashSet;
import java.util.Set;
import study.model.GraphNodeBase;

public class IntegerGraphNode implements GraphNodeBase<Integer> {

  Integer value;
  Set<GraphNodeBase<Integer>> adjacencyList;

  public IntegerGraphNode(Integer value) {
    this.value = value;
    adjacencyList = new HashSet<>();
  }
  @Override
  public Integer getValue() {
    return value;
  }

  @Override
  public Set<GraphNodeBase<Integer>> getAdjacencyList() {
    return adjacencyList;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof IntegerGraphNode integerGraphNode) {
      return integerGraphNode.getValue().equals(value);
    }
    return false;
  }

  @Override
  public int hashCode(){
    return value.hashCode();
  }
}
