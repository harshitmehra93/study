package study.neetcode.coreskills.bst;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        result.forEach(System.out::println);
    }

    @Test
    void inorderWalkBst_returnsListInSortedOrder_2(){
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

        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(list.size(),bst.getSize());

        List<Integer> result = bst.inorderWalk();

        assertEquals(bst.getSize(),result.size());
        assertTrue(isSorted(result));
        result.forEach(System.out::println);
    }

    @Test
    void inorderWalkBst_returnsListInSortedOrder_3(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        BinarySearchTree bst = new BinarySearchTreeImpl(list);
        assertEquals(list.size(),bst.getSize());

        List<Integer> result = bst.inorderWalk();

        assertEquals(bst.getSize(),result.size());
        assertTrue(isSorted(result));
        result.forEach(System.out::println);
    }

    private static boolean isSorted(List<Integer> result) {
        boolean isSorted=true;
        for(int i = 0, j = 1; j< result.size(); i++,j++){
            if(result.get(i)> result.get(i)){
                isSorted=false;
                break;
            }
        }
        return isSorted;
    }
}