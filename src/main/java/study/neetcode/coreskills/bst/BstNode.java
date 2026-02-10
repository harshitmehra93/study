package study.neetcode.coreskills.bst;

import study.model.BstNodeBase;
import study.model.TreeNodeBase;

public class BstNode implements BstNodeBase<Integer> {
    int val;
    BstNode parent;
    BstNode left;
    BstNode right;

    BstNode(BstNode p, Integer val, BstNode left, BstNode right) {
        this.val = val;
        parent = p;
        this.left = left;
        this.right = right;
    }

    BstNode(Integer val) {
        this.val = val;
    }

    @Override
    public Integer getValue() {
        return val;
    }

    @Override
    public TreeNodeBase<Integer> getLeft() {
        return left;
    }

    @Override
    public TreeNodeBase<Integer> getRight() {
        return right;
    }

    @Override
    public BstNodeBase<Integer> getParent() {
        return parent;
    }
}
