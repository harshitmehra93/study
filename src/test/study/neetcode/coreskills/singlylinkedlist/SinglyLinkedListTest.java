package study.neetcode.coreskills.singlylinkedlist;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    private SinglyLinkedList singlyLinkedList;

    @BeforeEach
    void setup(){
        singlyLinkedList = new SinglyLinkedListListNodeImpl();
    }

    @Test
    void createSinglyLinkedList(){
        singlyLinkedList = new SinglyLinkedListListNodeImpl();
        assertNotNull(singlyLinkedList);
    }

    @Test
    void emptyLinkedList_getNthIndex_returnsMinusOne(){
        assertEquals(-1,singlyLinkedList.get(0));
    }

    @Test
    void emptyLinkedList_getNegativeIndex_returnsMinusOne(){
        assertEquals(-1,singlyLinkedList.get(-1));
    }

    @Test
    void emptyLinkedList_getSize_returnsZero(){
        assertEquals(0,singlyLinkedList.getSize());
    }

    @Test
    void emptyLinkedList_insertHead(){
        singlyLinkedList.insertHead(1);
        assertEquals(1,singlyLinkedList.getSize());
    }

    @Test
    void emptyLinkedList_insertTwoHeads(){
        singlyLinkedList.insertHead(1);
        singlyLinkedList.insertHead(2);
        assertEquals(2,singlyLinkedList.getSize());
    }

    @Test
    void emptyLinkedList_insertTail(){
        singlyLinkedList.insertTail(1);
        assertEquals(1,singlyLinkedList.getSize());
    }

    @Test
    void insertHead_getZeroIndex_returnsHead(){
        singlyLinkedList.insertHead(10);

        assertEquals(1,singlyLinkedList.getSize());
        assertEquals(10,singlyLinkedList.get(0));
    }

    @Test
    void insertTwoHead_getZeroIndex_returnsSecondHead(){
        singlyLinkedList.insertHead(10);
        singlyLinkedList.insertHead(20);

        assertEquals(2,singlyLinkedList.getSize());
        assertEquals(20,singlyLinkedList.get(0));
    }

    @Test
    void insertTwoHead_getFirstIndex_returnsFirstHead(){
        singlyLinkedList.insertHead(10);
        singlyLinkedList.insertHead(20);
        // 20 10

        assertEquals(2,singlyLinkedList.getSize());
        assertEquals(20,singlyLinkedList.get(0));
    }

    @Test
    void insertTwoHead_insertTail_getZeroIndex_returnsSecondHead(){
        singlyLinkedList.insertHead(10);
        singlyLinkedList.insertHead(20);
        singlyLinkedList.insertTail(30);
        // 20 10 30

        assertEquals(3,singlyLinkedList.getSize());
        assertEquals(20,singlyLinkedList.get(0));
        assertEquals(10,singlyLinkedList.get(1));
        assertEquals(30,singlyLinkedList.get(2));
    }

    @Test
    void insertTwoHead_insertTail_getWrongIndex_returnsMinusone(){
        singlyLinkedList.insertHead(10);
        singlyLinkedList.insertHead(20);
        singlyLinkedList.insertTail(30);
        // 20 10 30

        assertEquals(3,singlyLinkedList.getSize());
        assertEquals(20,singlyLinkedList.get(0));
        assertEquals(10,singlyLinkedList.get(1));
        assertEquals(30,singlyLinkedList.get(2));

        assertEquals(-1,singlyLinkedList.get(20));
    }

    @Test
    void insert3Tail(){
        singlyLinkedList.insertTail(10);
        singlyLinkedList.insertTail(20);
        singlyLinkedList.insertTail(30);
        // 10 20 30

        assertEquals(3,singlyLinkedList.getSize());
        assertEquals(10,singlyLinkedList.get(0));
        assertEquals(20,singlyLinkedList.get(1));
        assertEquals(30,singlyLinkedList.get(2));

        assertEquals(-1,singlyLinkedList.get(20));
    }

    @Test
    void insert3Tail_insert2Head(){
        singlyLinkedList.insertTail(10);
        singlyLinkedList.insertTail(20);
        singlyLinkedList.insertTail(30);
        singlyLinkedList.insertHead(40);
        singlyLinkedList.insertHead(50);
        // 50 40 10 20 30

        assertEquals(5,singlyLinkedList.getSize());
        assertEquals(50,singlyLinkedList.get(0));
        assertEquals(40,singlyLinkedList.get(1));
        assertEquals(10,singlyLinkedList.get(2));
        assertEquals(20,singlyLinkedList.get(3));
        assertEquals(30,singlyLinkedList.get(4));

        assertEquals(-1,singlyLinkedList.get(5));
        assertEquals(-1,singlyLinkedList.get(20));
    }

    @Test
    void emptyList_removeZeroIndex_returnFalse() {
        assertEquals(Boolean.FALSE, singlyLinkedList.remove(0));
    }

    @Test
    void emptyList_removeNegativeIndex_returnFalse() {
        assertEquals(Boolean.FALSE, singlyLinkedList.remove(-2));
    }

    @Test
    void emptyList_removenonExistentIndex_returnFalse() {
        assertEquals(Boolean.FALSE, singlyLinkedList.remove(99));
    }

    @Test
    void addHead_removeZeroIndex_returnTrue() {
        singlyLinkedList.insertHead(1);
        assertEquals(1,singlyLinkedList.getSize());
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(0));
    }

    @Test
    void add3Elements_removeZeroIndex_returnTrue() {
        singlyLinkedList.insertTail(1);
        singlyLinkedList.insertTail(2);
        singlyLinkedList.insertTail(3);
        // 1 2 3

        assertEquals(3,singlyLinkedList.getSize());
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(0));

        assertEquals(2,singlyLinkedList.getSize());
        assertEquals(2,singlyLinkedList.get(0));
        assertEquals(3,singlyLinkedList.get(1));
    }

    @Test
    void add3Elements_removeFirstIndex_returnTrue() {
        singlyLinkedList.insertTail(1);
        singlyLinkedList.insertTail(2);
        singlyLinkedList.insertTail(3);
        // 1 2 3

        assertEquals(3,singlyLinkedList.getSize());
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(1));

        assertEquals(2,singlyLinkedList.getSize());
        assertEquals(1,singlyLinkedList.get(0));
        assertEquals(3,singlyLinkedList.get(1));
    }

    @Test
    void add3Elements_removeSecondIndex_returnTrue() {
        singlyLinkedList.insertTail(1);
        singlyLinkedList.insertTail(2);
        singlyLinkedList.insertTail(3);
        // 1 2 3

        assertEquals(3,singlyLinkedList.getSize());
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(2));

        assertEquals(2,singlyLinkedList.getSize());
        assertEquals(1,singlyLinkedList.get(0));
        assertEquals(2,singlyLinkedList.get(1));
    }

    @Test
    void add3Elements_removeAll_returnTrue() {
        singlyLinkedList.insertTail(1);
        singlyLinkedList.insertTail(2);
        singlyLinkedList.insertTail(3);
        // 1 2 3

        assertEquals(3,singlyLinkedList.getSize());
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(0));
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(0));
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(0));

        assertEquals(0,singlyLinkedList.getSize());
        assertEquals(-1,singlyLinkedList.get(0));
    }

    @Test
    void add3Elements_remove4Elements_returnTrue() {
        singlyLinkedList.insertTail(1);
        singlyLinkedList.insertTail(2);
        singlyLinkedList.insertTail(3);
        // 1 2 3

        assertEquals(3,singlyLinkedList.getSize());
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(0));
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(0));
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(0));
        assertEquals(Boolean.FALSE, singlyLinkedList.remove(0));

        assertEquals(0,singlyLinkedList.getSize());
        assertEquals(-1,singlyLinkedList.get(0));
    }

    @Test
    void randomlyAddElements_remove4Elements() {
        singlyLinkedList.insertTail(1);
        singlyLinkedList.insertHead(2);
        singlyLinkedList.insertTail(3);
        singlyLinkedList.insertTail(4);
        singlyLinkedList.insertHead(5);
        singlyLinkedList.insertTail(6);
        // 5 2 1 3 4 6

        assertEquals(6,singlyLinkedList.getSize());
        assertEquals(5,singlyLinkedList.get(0));
        assertEquals(2,singlyLinkedList.get(1));
        assertEquals(1,singlyLinkedList.get(2));
        assertEquals(3,singlyLinkedList.get(3));
        assertEquals(4,singlyLinkedList.get(4));
        assertEquals(6,singlyLinkedList.get(5));

        assertEquals(Boolean.TRUE, singlyLinkedList.remove(0));
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(1));
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(2));
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(0));
        // 3 6

        assertEquals(2,singlyLinkedList.getSize());
        assertEquals(3,singlyLinkedList.get(0));
        assertEquals(6,singlyLinkedList.get(1));

    }

    @Test
    void randomlyAddElements_removeAllButOneElement() {
        singlyLinkedList.insertTail(1);
        singlyLinkedList.insertHead(2);
        singlyLinkedList.insertTail(3);
        singlyLinkedList.insertTail(4);
        singlyLinkedList.insertHead(5);
        singlyLinkedList.insertTail(6);
        // 5 2 1 3 4 6

        assertEquals(6,singlyLinkedList.getSize());

        assertEquals(Boolean.TRUE, singlyLinkedList.remove(0));
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(1));
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(2));
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(0));
        assertEquals(Boolean.TRUE, singlyLinkedList.remove(1));
        // 3

        assertEquals(1,singlyLinkedList.getSize());
        assertEquals(3,singlyLinkedList.get(0));
    }

    @Test
    void emptyLinkedList_getValues_returnsEmptyIntArray(){
        int[] result = singlyLinkedList.getValues();
        assertNotNull(result);
        assertEquals(0,result.length);
    }

    @Test
    void singleElement_getValues_returnsIntArrayWithElement(){
        singlyLinkedList.insertHead(10);
        int[] result = singlyLinkedList.getValues();
        assertNotNull(result);
        assertEquals(1,result.length);
        assertEquals(10,result[0]);
    }

    @Test
    void multipleElements_getValues_returnsIntArrayWithElements(){
        singlyLinkedList.insertHead(10);
        singlyLinkedList.insertHead(20);
        singlyLinkedList.insertHead(30);
        singlyLinkedList.insertHead(40);
        singlyLinkedList.insertHead(50);
        singlyLinkedList.insertHead(60);

        int[] result = singlyLinkedList.getValues();
        assertNotNull(result);
        assertEquals(6,result.length);
        assertEquals(60,result[0]);
        assertEquals(50,result[1]);
        assertEquals(40,result[2]);
        assertEquals(30,result[3]);
        assertEquals(20,result[4]);
        assertEquals(10,result[5]);

    }

}