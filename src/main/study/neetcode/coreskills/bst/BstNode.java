package study.neetcode.coreskills.bst;

public class BstNode {
    int value;
    BstNode parent;
    BstNode left;
    BstNode right;
    BstNode(BstNode p, Integer val, BstNode left, BstNode right){
        value = val;
        parent = p;
        this.left=left;
        this.right=right;
    }
    BstNode(Integer val){
        value=val;
    }
}
