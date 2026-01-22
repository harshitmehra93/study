package study.neetcode.coreskills.bst;

import java.util.List;

public interface BinarySearchTree {
    int getSize();

    List<Integer> inorderWalk();
    List<Integer> preOrder();
    List<Integer> postOrder();

    BstNode search(int i);

    BstNode treeMin();

    BstNode treeMax();
}
