package study.neetcode.coreskills.deque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    private Deque deque;

    @BeforeEach
    void setup(){
        deque = new DequeLinkedListImpl();
    }

    @Test
    void createEmptyDeque(){
        deque = new DequeLinkedListImpl();
        assertNotNull(deque);
    }

    @Test
    void createEmptyDeque_sizeZero_isEmpty(){
        assertNotNull(deque);
        assertEquals(0,deque.getSize());
        assertTrue(deque.isEmpty());
    }

    @Test
    void emptyDeque_getFront_returnsException(){
        assertNotNull(deque);
        assertTrue(deque.isEmpty());
        assertThrows(DequeException.class,()->deque.getFront());
    }

    @Test
    void emptyDeque_getRear_returnsException(){
        assertNotNull(deque);
        assertTrue(deque.isEmpty());
        assertThrows(DequeException.class,()->deque.getRear());
    }

    @Test
    void insertFront_sizeIsOne(){
        assertNotNull(deque);
        assertEquals(0,deque.getSize());
        assertEquals(Boolean.TRUE,deque.insertFront(1));
        assertEquals(1,deque.getSize());
        assertFalse(deque.isEmpty());
    }

    @Test
    void insertFront_sizeIsOne_getFront_returnsElement(){
        assertNotNull(deque);
        assertEquals(0,deque.getSize());
        assertEquals(Boolean.TRUE,deque.insertFront(1));
        assertEquals(1,deque.getSize());
        assertEquals(1,deque.getFront());
        assertFalse(deque.isEmpty());
    }

    @Test
    void insertTwoFront_sizeIsTwo_getFront_returnsLastAddedElement(){
        assertNotNull(deque);
        assertEquals(0,deque.getSize());

        assertEquals(Boolean.TRUE,deque.insertFront(1));
        assertEquals(Boolean.TRUE,deque.insertFront(2));
        // 2 1

        assertEquals(2,deque.getSize());
        assertEquals(2,deque.getFront());
    }

    @Test
    void insert3Fronts_sizeIsThree_getFront_returnsLastAddedElement(){
        assertNotNull(deque);
        assertEquals(0,deque.getSize());

        assertEquals(Boolean.TRUE,deque.insertFront(1));
        assertEquals(Boolean.TRUE,deque.insertFront(2));
        assertEquals(Boolean.TRUE,deque.insertFront(3));
        // 3 2 1

        assertEquals(3,deque.getSize());
        assertEquals(3,deque.getFront());
    }

    @Test
    void insertLast_sizeIsOne(){
        assertNotNull(deque);
        assertEquals(0,deque.getSize());

        assertEquals(Boolean.TRUE,deque.insertLast(1));
        assertEquals(1,deque.getSize());
    }

    @Test
    void insertLast_getRear_sizeIsOne_returnsLast(){
        assertNotNull(deque);
        assertEquals(0,deque.getSize());

        assertEquals(Boolean.TRUE,deque.insertLast(1));
        assertEquals(1,deque.getSize());
        assertEquals(1,deque.getRear());
    }

    @Test
    void insertLast_getRearAndGetFront_ReturnsSameElement(){
        assertNotNull(deque);
        assertEquals(0,deque.getSize());

        assertEquals(Boolean.TRUE,deque.insertLast(10));
        assertEquals(1,deque.getSize());
        assertEquals(10,deque.getRear());
        assertEquals(10,deque.getFront());
    }

    @Test
    void insertTwoLast_getRearReturnsLast_AndGetFrontReturnsFirst(){
        assertNotNull(deque);
        assertEquals(0,deque.getSize());

        assertEquals(Boolean.TRUE,deque.insertLast(10));
        assertEquals(Boolean.TRUE,deque.insertLast(20));
        // 10 20

        assertEquals(2,deque.getSize());
        assertEquals(10,deque.getFront());
        assertEquals(20,deque.getRear());
    }

    @Test
    void insertTwoFront_getRearReturnsLast_AndGetFrontReturnsFirst(){
        assertNotNull(deque);
        assertEquals(0,deque.getSize());

        assertEquals(Boolean.TRUE,deque.insertFront(10));
        assertEquals(Boolean.TRUE,deque.insertFront(20));
        // 20 10

        assertEquals(2,deque.getSize());
        assertEquals(20,deque.getFront());
        assertEquals(10,deque.getRear());
    }

    @Test
    void insertTwoFront_insertTwoLast_getRearReturnsLast_AndGetFrontReturnsFirst(){
        assertNotNull(deque);
        assertEquals(0,deque.getSize());

        assertEquals(Boolean.TRUE,deque.insertFront(10));
        assertEquals(Boolean.TRUE,deque.insertFront(20));
        assertEquals(Boolean.TRUE,deque.insertLast(30));
        assertEquals(Boolean.TRUE,deque.insertLast(40));
        // 20 10 30 40

        assertEquals(4,deque.getSize());
        assertEquals(20,deque.getFront());
        assertEquals(40,deque.getRear());
    }

    @Test
    void emptyQ_deleteLast_returnsException(){
        assertNotNull(deque);
        assertTrue(deque.isEmpty());
        assertThrows(DequeException.class,()->deque.deleteLast());
    }

    @Test
    void insertFront_deleteLast_sizeIsZero(){
        deque.insertFront(10);
        assertEquals(1,deque.getSize());

        assertEquals(Boolean.TRUE,deque.deleteLast());

        assertEquals(0,deque.getSize());
    }

    @Test
    void insertFront_deleteLast_getFront_throwsException(){
        deque.insertFront(10);
        assertEquals(1,deque.getSize());

        assertEquals(Boolean.TRUE,deque.deleteLast());

        assertEquals(0,deque.getSize());
        assertThrows(DequeException.class,()->deque.getFront());
        assertThrows(DequeException.class,()->deque.getRear());
    }

    @Test
    void insertFront_deleteLast_insertFront_getFront_getRear_returnsSecondElement(){
        deque.insertFront(10);
        deque.deleteLast();
        deque.insertFront(20);

        assertEquals(1,deque.getSize());
        assertEquals(20,deque.getFront());
        assertEquals(20,deque.getRear());
    }

    @Test
    void emptyQ_deleteFront_returnsException(){
        assertThrows(DequeException.class,()->deque.deleteFront());
    }

    @Test
    void insertFront_deleteFront_sizeIsZero(){
        deque.insertFront(1);
        deque.deleteFront();
        assertEquals(0,deque.getSize());
    }

    @Test
    void insertFront_deleteFrontTwice_returnException(){
        deque.insertFront(1);
        deque.deleteFront();
        assertThrows(DequeException.class,()->deque.deleteFront());
    }

    @Test
    void insertFront_insertLast_deleteFront_getRearAndgetFrontReturnsLastElement(){
        deque.insertFront(10);
        deque.insertLast(20);
        deque.deleteFront();

        assertEquals(1,deque.getSize());
        assertEquals(20,deque.getRear());
        assertEquals(20,deque.getFront());
    }

    @Test
    void insertFrontTwice_insertLastThrice_deleteFront_deleteLast_getRearAndgetFrontReturnsCorreclty(){
        deque.insertFront(10);
        deque.insertFront(20);
        deque.insertLast(30);
        deque.insertLast(40);
        deque.insertLast(50);
        // 20 10 30 40 50

        deque.deleteFront();
        deque.deleteLast();
        // 10 30 40

        assertEquals(3,deque.getSize());
        assertEquals(40,deque.getRear());
        assertEquals(10,deque.getFront());
    }

    @Test
    void createQueueWithCapacity0(){
        deque = new DequeLinkedListImpl(0);
    }

    @Test
    void createQueueWithCapacity0_getCapacityReturnsZero(){
        deque = new DequeLinkedListImpl(0);
        assertEquals(0,deque.getCapacity());
    }

    @Test
    void createQueueWithNoCapacity0_getCapacityReturnsDefault(){
        deque = new DequeLinkedListImpl();
        assertEquals(50,deque.getCapacity());
    }

    @Test
    void createQueueWithCapacity0_insertHead_returnException(){
        deque = new DequeLinkedListImpl(0);
        assertThrows(DequeException.class,()->deque.insertLast(1));
    }

    @Test
    void createQueueWithCapacity1_insertHeadTwice_returnException(){
        deque = new DequeLinkedListImpl(1);
        assertEquals(Boolean.TRUE,deque.insertFront(1));
        assertThrows(DequeException.class,()->deque.insertFront(2));
    }

    @Test
    void createQueueWithCapacity1_insertTailTwice_returnException(){
        deque = new DequeLinkedListImpl(1);
        assertEquals(Boolean.TRUE,deque.insertLast(1));
        assertThrows(DequeException.class,()->deque.insertLast(2));
    }

    @Test
    void createQueueWithNegativeCapacity_throwsException(){
        assertThrows(DequeException.class,()->new DequeLinkedListImpl(-1));
    }

    @Test
    void createQueueWithCapacity1_insertTail_isFullIsTrue(){
        deque = new DequeLinkedListImpl(1);
        deque.insertLast(1);
        assertEquals(Boolean.TRUE,deque.isFull());
    }

    @Test
    void createQueueWithCapacity1_isFullIsFalse(){
        deque = new DequeLinkedListImpl(1);
        assertEquals(Boolean.FALSE,deque.isFull());
    }

    @Test
    void playgroundTest_1(){
        deque.insertFront(10);
        deque.insertLast(30);
        deque.insertFront(20);
        deque.insertLast(40);
        deque.insertFront(60);
        deque.insertLast(50);
        // 60 20 10 30 40 50

        assertEquals(50,deque.getRear());
        assertEquals(60,deque.getFront());
        assertEquals(6,deque.getSize());

        deque.deleteLast();
        assertEquals(40,deque.getRear());
        assertEquals(60,deque.getFront());
        assertEquals(5,deque.getSize());

        deque.deleteLast();
        assertEquals(30,deque.getRear());
        assertEquals(60,deque.getFront());
        assertEquals(4,deque.getSize());

        deque.deleteLast();
        assertEquals(10,deque.getRear());
        assertEquals(60,deque.getFront());
        assertEquals(3,deque.getSize());

        deque.deleteLast();
        assertEquals(20,deque.getRear());
        assertEquals(60,deque.getFront());
        assertEquals(2,deque.getSize());

        deque.deleteLast();
        assertEquals(60,deque.getRear());
        assertEquals(60,deque.getFront());
        assertEquals(1,deque.getSize());

        deque.deleteLast();
        assertEquals(0,deque.getSize());
        assertThrows(DequeException.class,()->deque.getFront());
        assertThrows(DequeException.class,()->deque.getRear());
    }

    @Test
    void playgroundTest_2(){
        deque.insertFront(10);
        deque.insertLast(30);
        deque.insertFront(20);
        deque.insertLast(40);
        deque.insertFront(60);
        deque.insertLast(50);
        // 60 20 10 30 40 50

        assertEquals(50,deque.getRear());
        assertEquals(60,deque.getFront());
        assertEquals(6,deque.getSize());

        deque.deleteFront();
        assertEquals(50,deque.getRear());
        assertEquals(20,deque.getFront());
        assertEquals(5,deque.getSize());

        deque.deleteFront();
        assertEquals(50,deque.getRear());
        assertEquals(10,deque.getFront());
        assertEquals(4,deque.getSize());

        deque.deleteFront();
        assertEquals(50,deque.getRear());
        assertEquals(30,deque.getFront());
        assertEquals(3,deque.getSize());

        deque.deleteFront();
        assertEquals(50,deque.getRear());
        assertEquals(40,deque.getFront());
        assertEquals(2,deque.getSize());

        deque.deleteFront();
        assertEquals(50,deque.getRear());
        assertEquals(50,deque.getFront());
        assertEquals(1,deque.getSize());

        deque.deleteFront();
        assertEquals(0,deque.getSize());
        assertThrows(DequeException.class,()->deque.getFront());
        assertThrows(DequeException.class,()->deque.getRear());
    }

    @Test
    void playgroundTest_3(){
        deque.insertFront(10);
        deque.insertLast(30);
        deque.insertFront(20);
        // 20 10 30
        assertEquals(30,deque.getRear());
        assertEquals(20,deque.getFront());
        assertEquals(3,deque.getSize());

        deque.deleteFront();
        deque.deleteFront();
        deque.deleteFront();

        deque.insertLast(40);
        deque.insertFront(60);
        deque.insertLast(50);
        // 60 40 50

        assertEquals(50,deque.getRear());
        assertEquals(60,deque.getFront());
        assertEquals(3,deque.getSize());

        deque.deleteFront();
        deque.deleteFront();
        deque.deleteFront();

        assertEquals(0,deque.getSize());
        assertThrows(DequeException.class,()->deque.getFront());
        assertThrows(DequeException.class,()->deque.getRear());
    }

}