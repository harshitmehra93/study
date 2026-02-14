package study.neetcode.coreskills.graph;

import java.util.HashSet;
import java.util.Set;
import study.model.GraphNodeBase;

public class IntegerGraphNode implements GraphNodeBase<Integer>, Comparable<IntegerGraphNode> {

    Integer value;
    Set<IntegerGraphNode> adjacencyList;

    public IntegerGraphNode(Integer value) {
        this.value = value;
        adjacencyList = new HashSet<>();
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Set<IntegerGraphNode> getAdjacencyList() {
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
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(IntegerGraphNode o) {
        return this.getValue().compareTo(o.getValue());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
