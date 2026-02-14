package study.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import study.neetcode.coreskills.bst.BinarySearchTree;
import study.neetcode.coreskills.bst.BinarySearchTreeImpl;

class TreeUtilsTest {

    @Test
    void prettyPrint() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(
                        new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)));
        TreeUtils.prettyPrint(bst.getRoot());
    }
}
