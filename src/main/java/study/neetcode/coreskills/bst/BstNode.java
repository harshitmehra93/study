package study.neetcode.coreskills.bst;

public class BstNode {
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
}
