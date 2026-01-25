package study.neetcode.coreskills.bst;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    @Test
    void createBST(){
        BinarySearchTree bst = new BinarySearchTreeImpl();
    }

    @Test
    void createBST_size0(){
        BinarySearchTree bst = new BinarySearchTreeImpl();
        assertEquals(0,bst.getSize());
    }

    @Test
    void createBST_oneElement_size1(){
        List<Integer> list = new ArrayList<>();
        list.add(6758);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(1,bst.getSize());
    }

    @Test
    void createBST_twoElements_size2(){
        List<Integer> list = new ArrayList<>();
        list.add(6758);
        list.add(4567);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(2,bst.getSize());
    }

    @Test
    void inorderWalkBst_returnsListInSortedOrder_1(){
        List<Integer> list = new ArrayList<>();
        list.add(6758);
        list.add(4567);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(2,bst.getSize());

        List<Integer> result = bst.inorderWalk();

        assertEquals(bst.getSize(),result.size());
        assertTrue(isSorted(result));
    }

    @Test
    void inorderWalkBst_returnsListInSortedOrder_2(){
        List<Integer> list = createBigList();

        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(list.size(),bst.getSize());

        List<Integer> result = bst.inorderWalk();

        assertEquals(bst.getSize(),result.size());
        assertTrue(isSorted(result));
    }

    @Test
    void inorderWalkBst_returnsListInSortedOrder_3(){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<20;i++)
            list.add(1);

        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(list.size(),bst.getSize());

        List<Integer> result = bst.inorderWalk();

        assertEquals(bst.getSize(),result.size());
        assertTrue(isSorted(result));
    }

    @Test
    void inorderWalkBst_returnsListInSortedOrder_4(){
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<100000;i++)
            list.add(random.nextInt(100000000));

        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(list.size(),bst.getSize());

        List<Integer> result = bst.inorderWalk();

        assertEquals(bst.getSize(),result.size());
        assertTrue(isSorted(result));
    }

    @Test
    void preOrderWalkBst(){
        List<Integer> list = createBigList();

        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(list.size(),bst.getSize());

        List<Integer> result = bst.preOrder();

        assertEquals(bst.getSize(),result.size());
    }

    @Test
    void postOrderWalkBst(){
        List<Integer> list = createBigList();

        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(list.size(),bst.getSize());

        List<Integer> result = bst.postOrder();

        assertEquals(bst.getSize(),result.size());
    }

    @Test
    void successfulSearch_returnsNode(){
        List<Integer> list = createBigList();

        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.search(4567);

        assertNotNull(node);
        assertEquals(4567,node.val);
    }

    @Test
    void successfulSearch_returnsNode_2(){
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(15);
        list.add(20);
        list.add(25);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.search(5);

        assertNotNull(node);
        assertEquals(5,node.val);
    }

    @Test
    void unsuccessfulSearch_returnsNull(){
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
    void treeMin(){
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(15);
        list.add(20);
        list.add(25);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.treeMin();

        assertNotNull(node);
        assertEquals(5,node.val);
    }

    @Test
    void treeMin_2(){
        List<Integer> list = createBigList();

        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.treeMin();

        assertNotNull(node);
        assertEquals(2,node.val);
    }

    @Test
    void treeMax(){
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(15);
        list.add(20);
        list.add(25);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.treeMax();

        assertNotNull(node);
        assertEquals(25,node.val);
    }

    @Test
    void treeMax_2(){
        List<Integer> list = createBigList();

        BinarySearchTree bst = new BinarySearchTreeImpl(list);

        BstNode node = bst.treeMax();

        assertNotNull(node);
        assertEquals(4532467,node.val);
    }

    @Test
    void emprtytree_successorEmpty(){
        BinarySearchTree bst = new BinarySearchTreeImpl();
        BstNode node = bst.successor(2);
        assertNull(node);
    }

    @Test
    void targetNotInList_successorEmpty(){
        List<Integer> list = new ArrayList<>();
        list.add(3);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        BstNode node = bst.successor(2);
        assertNull(node);
    }

    @Test
    void targetInList_successorEmpty(){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        BstNode node = bst.successor(2);
        assertNull(node);
    }

    @Test
    void targetInList_successorExists(){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        BstNode node = bst.successor(2);
        assertNotNull(node);
        assertEquals(3,node.val);
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
    void emprtytree_predecessorEmpty(){
        BinarySearchTree bst = new BinarySearchTreeImpl();
        BstNode node = bst.predecessor(2);
        assertNull(node);
    }

    @Test
    void targetNotInList_predecessorEmpty(){
        List<Integer> list = new ArrayList<>();
        list.add(3);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        BstNode node = bst.predecessor(2);
        assertNull(node);
    }

    @Test
    void targetInList_predecessorEmpty(){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        BstNode node = bst.predecessor(2);
        assertNull(node);
    }

    @Test
    void targetInList_predecessorExists(){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        BstNode node = bst.predecessor(3);
        assertNotNull(node);
        assertEquals(2,node.val);
    }

    @Test
    void targetInList_predecessorExists_2(){
        Set<Integer> set = new HashSet<>(Stream.iterate(1,n->n+1).limit(1000).toList());
        BinarySearchTree bst = new BinarySearchTreeImpl(new ArrayList<>(set));
        for(int i=2;i<1001;i++){
            BstNode node = bst.predecessor(i);
            assertNotNull(node);
            assertEquals(i-1,node.val);
        }
    }

    private static boolean isSorted(List<Integer> result) {
        boolean isSorted=true;
        for(int i = 0, j = 1; j< result.size(); i++,j++){
            if(result.get(i)> result.get(j)){
                isSorted=false;
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