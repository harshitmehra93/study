package study.neetcode.coreskills.bst;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import study.utils.TreeUtils;

class BinarySearchTreeTest {
    @Test
    void createBST() {
        BinarySearchTree bst = new BinarySearchTreeImpl();
    }

    @Test
    void createBST_size0() {
        BinarySearchTree bst = new BinarySearchTreeImpl();
        assertEquals(0, bst.getSize());
    }

    @Test
    void createBST_oneElement_size1() {
        List<Integer> list = new ArrayList<>();
        list.add(6758);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(1, bst.getSize());
    }

    @Test
    void createBST_twoElements_size2() {
        List<Integer> list = new ArrayList<>();
        list.add(6758);
        list.add(4567);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(2, bst.getSize());
    }

    @Test
    void inorderWalkBst_returnsListInSortedOrder_1() {
        List<Integer> list = new ArrayList<>();
        list.add(6758);
        list.add(4567);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(2, bst.getSize());

        List<Integer> result = bst.inorderWalk();

        assertEquals(bst.getSize(), result.size());
        assertTrue(isSorted(result));
    }

    @Test
    void inorderWalkBst_returnsListInSortedOrder_2() {
        List<Integer> list = createBigList();

        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(list.size(), bst.getSize());

        List<Integer> result = bst.inorderWalk();

        assertEquals(bst.getSize(), result.size());
        assertTrue(isSorted(result));
    }

    @Test
    void inorderWalkBst_returnsListInSortedOrder_3() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) list.add(1);

        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(list.size(), bst.getSize());

        List<Integer> result = bst.inorderWalk();

        assertEquals(bst.getSize(), result.size());
        assertTrue(isSorted(result));
    }

    @Test
    void inorderWalkBst_returnsListInSortedOrder_4() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) list.add(random.nextInt(100000000));

        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(list.size(), bst.getSize());

        List<Integer> result = bst.inorderWalk();

        assertEquals(bst.getSize(), result.size());
        assertTrue(isSorted(result));
    }

    @Test
    void preOrderWalkBst() {
        List<Integer> list = createBigList();

        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(list.size(), bst.getSize());

        List<Integer> result = bst.preOrder();

        assertEquals(bst.getSize(), result.size());
    }

    @Test
    void postOrderWalkBst() {
        List<Integer> list = createBigList();

        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(list.size(), bst.getSize());

        List<Integer> result = bst.postOrder();

        assertEquals(bst.getSize(), result.size());
    }

    @Test
    void successfulSearch_returnsNode() {
        List<Integer> list = createBigList();

        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.search(4567);

        assertNotNull(node);
        assertEquals(4567, node.val);
    }

    @Test
    void successfulSearch_returnsNode_2() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(15);
        list.add(20);
        list.add(25);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.search(5);

        assertNotNull(node);
        assertEquals(5, node.val);
    }

    @Test
    void unsuccessfulSearch_returnsNull() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(15);
        list.add(20);
        list.add(25);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.search(0);

        assertNull(node);
    }

    @Test
    void treeMin() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(15);
        list.add(20);
        list.add(25);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.treeMin();

        assertNotNull(node);
        assertEquals(5, node.val);
    }

    @Test
    void treeMin_2() {
        List<Integer> list = createBigList();

        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.treeMin();

        assertNotNull(node);
        assertEquals(2, node.val);
    }

    @Test
    void treeMax() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(15);
        list.add(20);
        list.add(25);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.treeMax();

        assertNotNull(node);
        assertEquals(25, node.val);
    }

    @Test
    void treeMax_2() {
        List<Integer> list = createBigList();

        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.treeMax();

        assertNotNull(node);
        assertEquals(4532467, node.val);
    }

    @Test
    void emprtytree_successorEmpty() {
        BinarySearchTree bst = new BinarySearchTreeImpl();
        BstNode node = bst.successor(2);
        assertNull(node);
    }

    @Test
    void targetNotInList_successorEmpty() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        BstNode node = bst.successor(2);
        assertNull(node);
    }

    @Test
    void targetInList_successorEmpty() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        BstNode node = bst.successor(2);
        assertNull(node);
    }

    @Test
    void targetInList_successorExists() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        BstNode node = bst.successor(2);
        assertNotNull(node);
        assertEquals(3, node.val);
    }

    @Test
    void targetInList_successorExists_2() {
        Set<Integer> set = new HashSet<>(Stream.iterate(1, n -> n + 1).limit(1000).toList());
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(set));
        for (int i = 1; i < 998; i++) {
            BstNode node = bst.successor(i);
            assertNotNull(node);
            assertEquals(i + 1, node.val);
        }
    }

    @Test
    void emprtytree_predecessorEmpty() {
        BinarySearchTree bst = new BinarySearchTreeImpl();
        BstNode node = bst.predecessor(2);
        assertNull(node);
    }

    @Test
    void targetNotInList_predecessorEmpty() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        BstNode node = bst.predecessor(2);
        assertNull(node);
    }

    @Test
    void targetInList_predecessorEmpty() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        BstNode node = bst.predecessor(2);
        assertNull(node);
    }

    @Test
    void targetInList_predecessorExists() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        BstNode node = bst.predecessor(3);
        assertNotNull(node);
        assertEquals(2, node.val);
    }

    @Test
    void targetInList_predecessorExists_2() {
        Set<Integer> set = new HashSet<>(Stream.iterate(1, n -> n + 1).limit(1000).toList());
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(set));
        for (int i = 2; i < 1001; i++) {
            BstNode node = bst.predecessor(i);
            assertNotNull(node);
            assertEquals(i - 1, node.val);
        }
    }

    @Test
    void insert_emptyTree_size1() {
        BinarySearchTree bst = new BinarySearchTreeImpl();

        bst.insert(1);

        assertEquals(1, bst.getSize());
        assertEquals(1, bst.search(1).val);
    }

    @Test
    void insert1_insert2_inorder_isSorted() {
        BinarySearchTree bst = new BinarySearchTreeImpl();

        bst.insert(1);
        bst.insert(2);

        assertEquals(2, bst.getSize());
        assertEquals(1, bst.search(1).val);
        assertEquals(2, bst.search(2).val);
        assertTrue(isSorted(bst.inorderWalk()));
    }

    @Test
    void insertBigList_inorder_isSorted() {
        List<Integer> bigList = createBigList();
        BinarySearchTree bst = new BinarySearchTreeImpl(bigList);

        assertEquals(bigList.size(), bst.getSize());
        assertTrue(isSorted(bst.inorderWalk()));
    }

    @Test
    void insertBigList_inorder_isSorted_2() {
        List<Integer> bigList =
                Stream.generate(() -> ((Double) (Math.random() * 10000)).intValue())
                        .limit(1000)
                        .toList();
        BinarySearchTree bst = new BinarySearchTreeImpl();

        bigList.forEach(bst::insert);

        assertEquals(bigList.size(), bst.getSize());
        int randomIndex = ((Double) (Math.random() * 1000)).intValue();
        Integer expected = bigList.get(randomIndex);
        assertEquals(expected, bst.search(expected).val);
        assertTrue(isSorted(bst.inorderWalk()));
    }

    @Test
    void pretty_print_1() {
        BinarySearchTree bst = new BinarySearchTreeImpl();
        TreeUtils.prettyPrint(bst.getRoot());
    }

    @Test
    void pretty_print_oneNode() {
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(List.of(1)));
        TreeUtils.prettyPrint(bst.getRoot());
    }

    @Test
    void pretty_print_twoNodes() {
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(List.of(1, 2)));
        TreeUtils.prettyPrint(bst.getRoot());
    }

    @Test
    void getMaxHeight_nullTree() {
        BinarySearchTree bst = new BinarySearchTreeImpl();
        assertEquals(0, bst.getSize());
        assertEquals(0, bst.getMaxHeight());
    }

    @Test
    void getMaxHeight_singleNodeTree_returnsOne() {
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(List.of(1)));
        assertEquals(1, bst.getSize());
        assertEquals(1, bst.getMaxHeight());
    }

    @Test
    void getMaxHeight_3NodeTree_returnsTwo() {
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(List.of(1, 2, 3)));
        assertEquals(3, bst.getSize());
        assertEquals(2, bst.getMaxHeight());
    }

    @Test
    void getMaxHeight_5NodeTree_returnsThree() {
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(List.of(1, 2, 3, 4, 5)));
        assertEquals(5, bst.getSize());
        assertEquals(3, bst.getMaxHeight());
    }

    @Test
    void getMaxHeight_7NodeTree_returnsThree() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7)));
        assertEquals(7, bst.getSize());
        assertEquals(3, bst.getMaxHeight());
    }

    @Test
    void getMaxHeight_8NodeTree_returnsThree() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8)));
        assertEquals(8, bst.getSize());
        assertEquals(4, bst.getMaxHeight());
    }

    @Test
    void getMaxHeight_9NodeTree_returnsThree_2() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(new ArrayList<>(List.of(-1, -2, 1, 2, 3, 4, 5, 6, 7)));
        assertEquals(9, bst.getSize());
        assertEquals(4, bst.getMaxHeight());
    }

    @Test
    void prettyPrint_1() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        TreeUtils.prettyPrint(bst.getRoot());
    }

    @Test
    void prettyPrint_2() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(
                        new ArrayList<>(
                                List.of(
                                        1, 2, 3, 4, 23, 567, 666, 777, 12, 68, 123, 5677, 123, 56,
                                        5, 6, 7)));
        TreeUtils.prettyPrint(bst.getRoot());
    }

    @Test
    void prettyPrint_3() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(
                        new ArrayList<>(
                                List.of(
                                        1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18,
                                        21, 22, 23, 24, 25, 26, 27, 28, 29, 30)));
        TreeUtils.prettyPrint(bst.getRoot());
    }

    @Test
    void delete_emptyTree_returnsFalse() {
        BinarySearchTree bst = new BinarySearchTreeImpl();
        assertFalse(bst.delete(3));
    }

    @Test
    void delete_nodeDoesNotExist_returnsFalse() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        assertFalse(bst.delete(10));
    }

    @Test
    void delete_nodeExist_returnsTrue() {
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(List.of(1)));
        assertTrue(bst.delete(1));
        assertEquals(0, bst.getSize());
    }

    @Test
    void delete_leafNode_returnsTrue() {
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(List.of(1, 2, 3)));
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(bst.delete(1));
        assertEquals(2, bst.getSize());
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(isSorted(bst.inorderWalk()));
    }

    @Test
    void delete_leafNode_returnsTrue_2() {
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(List.of(1, 2, 3)));
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(bst.delete(3));
        assertEquals(2, bst.getSize());
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(isSorted(bst.inorderWalk()));
    }

    @Test
    void delete_leafNode_returnsTrue_3() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7)));
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(bst.delete(7));
        assertEquals(6, bst.getSize());
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(isSorted(bst.inorderWalk()));
    }

    @Test
    void delete_NodeWithOneChild_returnsTrue() {
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(List.of(1, 2)));
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(bst.delete(1));
        assertEquals(1, bst.getSize());
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(isSorted(bst.inorderWalk()));
    }

    @Test
    void delete_NodeWithOneChild_returnsTrue_2() {
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(bst.delete(1));
        assertEquals(5, bst.getSize());
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(isSorted(bst.inorderWalk()));
    }

    @Test
    void delete_NodeWithOneChild_returnsTrue_3() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(
                        new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)));
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(bst.delete(1));
        assertEquals(10, bst.getSize());
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(isSorted(bst.inorderWalk()));
    }

    @Test
    void delete_NodeWithOneChild_returnsTrue_4() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(
                        new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)));
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(bst.delete(4));
        assertEquals(10, bst.getSize());
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(isSorted(bst.inorderWalk()));
    }

    @Test
    void delete_NodeWithOneChild_returnsTrue_5() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(
                        new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)));
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(bst.delete(7));
        assertEquals(10, bst.getSize());
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(isSorted(bst.inorderWalk()));
    }

    @Test
    void delete_NodeWithOneChild_returnsTrue_6() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(
                        new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)));
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(bst.delete(10));
        assertEquals(10, bst.getSize());
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(isSorted(bst.inorderWalk()));
    }

    /*
    *
    * before delete -
                                                      60

                                             30                   90

                                        10       40            70        100

                                           20  35           80       95       110

                                                 38                         105

                                                                         102

                                                                            104
      After deleting 100 -
                                                      60

                                             30                   90

                                        10       40            70        102

                                           20      50       80       95       110

                                                                            105

                                                                         104
    *
    * */
    @Test
    void delete_NodeWithTwoChild_returnsTrue() {
        BinarySearchTree bst =
                new BinarySearchTreeImpl(
                        new ArrayList<>(List.of(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110)));
        bst.insert(95);
        bst.insert(105);
        bst.insert(102);
        bst.insert(104);
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(bst.delete(100));
        TreeUtils.prettyPrint(bst.getRoot());
        assertTrue(isSorted(bst.inorderWalk()));
    }

    private static boolean isSorted(List<Integer> result) {
        boolean isSorted = true;
        for (int i = 0, j = 1; j < result.size(); i++, j++) {
            if (result.get(i) > result.get(j)) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    private static List<Integer> createBigList() {
        List<Integer> list = new ArrayList<>();
        list.add(6758);
        list.add(4567);
        list.add(6758);
        list.add(4567);
        list.add(676558);
        list.add(4567);
        list.add(676858);
        list.add(4532467);
        list.add(634758);
        list.add(459067);
        list.add(2);
        list.add(3);
        list.add(768);
        list.add(23);
        list.add(45);
        list.add(89);
        list.add(87);
        list.add(6);
        list.add(6);
        list.add(23);
        return list;
    }
}
