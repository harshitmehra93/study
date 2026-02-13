package study.neetcode.coreskills.heap;

import study.model.TreeNodeBase;

public class HeapNode implements TreeNodeBase<Integer> {
    Integer value;
    HeapNode left;
    HeapNode right;
    HeapNode parent;

    HeapNode(Integer val) {
        this.value = val;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public TreeNodeBase<Integer> getLeft() {
        return left;
    }

    @Override
    public TreeNodeBase<Integer> getRight() {
        return right;
    }
}
