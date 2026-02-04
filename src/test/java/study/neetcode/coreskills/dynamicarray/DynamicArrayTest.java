package study.neetcode.coreskills.dynamicarray;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DynamicArrayTest {
    @Test
    public void createADynamicArray() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        assertNotNull(dynamicArray1);
    }

    @Test
    void createDynamicArrayWithDefaultCapacity() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        assertNotNull(dynamicArray1);
        assertEquals(DynamicArray.DEFAULT_CAPACITY, dynamicArray1.getCapacity());
    }

    @ParameterizedTest
    @CsvSource({"1", "2", "3", "4", "5", "6"})
    void createDynamicArrayWithCustomCapacity(int capacity) {
        DynamicArray dynamicArray1 = new DynamicArrayImpl(capacity);
        assertNotNull(dynamicArray1);
        assertEquals(capacity, dynamicArray1.getCapacity());
    }

    @ParameterizedTest
    @CsvSource({"-1", "0"})
    void createDynamicArrayWithInvalidCapacity(Integer capacity) {
        assertThrows(InvalidParameterException.class, () -> new DynamicArrayImpl(capacity));
    }

    @Test
    void createDynamicArrayWithInvalidCapacity() {
        assertThrows(InvalidParameterException.class, () -> new DynamicArrayImpl(null));
    }

    @Test
    void getCapacityOfDynamicArray() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        assertNotNull(dynamicArray1);
        assertEquals(DynamicArray.DEFAULT_CAPACITY, dynamicArray1.getCapacity());
    }

    @Test
    void sizeOfEmptyDynamicArray_isZero() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        assertEquals(0, dynamicArray1.getSize());
    }

    @ParameterizedTest
    @CsvSource({"1", "2", "3", "4"})
    void sizeOfEmptyNCapacityDynamicArray_IsZero(int capacity) {
        DynamicArray dynamicArray1 = new DynamicArrayImpl(capacity);
        assertNotNull(dynamicArray1);
        assertEquals(capacity, dynamicArray1.getCapacity());
        assertEquals(0, dynamicArray1.getSize());
    }

    @Test
    void pushBackOneElement_sizeIs1() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        assertEquals(DynamicArray.DEFAULT_CAPACITY, dynamicArray1.getCapacity());
        assertEquals(0, dynamicArray1.getSize());

        dynamicArray1.pushback(1);

        assertEquals(DynamicArray.DEFAULT_CAPACITY, dynamicArray1.getCapacity());
        assertEquals(1, dynamicArray1.getSize());
    }

    @Test
    void pushBackTwoElements_sizeIs2() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        assertEquals(DynamicArray.DEFAULT_CAPACITY, dynamicArray1.getCapacity());
        assertEquals(0, dynamicArray1.getSize());

        dynamicArray1.pushback(1);
        dynamicArray1.pushback(1);

        assertEquals(DynamicArray.DEFAULT_CAPACITY, dynamicArray1.getCapacity());
        assertEquals(2, dynamicArray1.getSize());
    }

    @Test
    void pushBack50Elements_sizeIs50() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        assertEquals(DynamicArray.DEFAULT_CAPACITY, dynamicArray1.getCapacity());
        assertEquals(0, dynamicArray1.getSize());

        for (int i = 0; i < 50; i++) dynamicArray1.pushback(i);

        assertEquals(DynamicArray.DEFAULT_CAPACITY, dynamicArray1.getCapacity());
        assertEquals(50, dynamicArray1.getSize());
    }

    @Test
    void push1_get1() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        dynamicArray1.pushback(1);
        assertEquals(1, dynamicArray1.get(0));
    }

    @Test
    void push2Elements_getBoth() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        dynamicArray1.pushback(1);
        dynamicArray1.pushback(2);
        assertEquals(1, dynamicArray1.get(0));
        assertEquals(2, dynamicArray1.get(1));
    }

    @Test
    void push2Elements_getThirdElementReturnsException() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        dynamicArray1.pushback(1);
        dynamicArray1.pushback(2);
        assertEquals(1, dynamicArray1.get(0));
        assertEquals(2, dynamicArray1.get(1));
        assertThrows(DynamicArrayImpl.DynamicArrayException.class, () -> dynamicArray1.get(2));
    }

    @Test
    void push2Elements_getNegativeIndex_returnsException() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        dynamicArray1.pushback(1);
        dynamicArray1.pushback(2);
        assertEquals(1, dynamicArray1.get(0));
        assertEquals(2, dynamicArray1.get(1));
        assertThrows(DynamicArrayImpl.DynamicArrayException.class, () -> dynamicArray1.get(-1));
    }

    @Test
    void pushNoElements_getZerothIndex_returnsException() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        assertThrows(DynamicArrayImpl.DynamicArrayException.class, () -> dynamicArray1.get(0));
    }

    @Test
    void push2Elements_popBack_returnsSecondElement() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        dynamicArray1.pushback(1);
        dynamicArray1.pushback(2);
        assertEquals(1, dynamicArray1.get(0));
        assertEquals(2, dynamicArray1.get(1));
        assertEquals(2, dynamicArray1.popback());
    }

    @Test
    void pushNoElements_popBack_returnsException() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        assertThrows(DynamicArrayImpl.DynamicArrayException.class, () -> dynamicArray1.popback());
    }

    @Test
    void push2Elements_setSecondElementTo3() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        dynamicArray1.pushback(1);
        dynamicArray1.pushback(2);
        assertEquals(2, dynamicArray1.get(1));

        dynamicArray1.set(1, 3);

        assertEquals(3, dynamicArray1.get(1));
    }

    @Test
    void emptyDynamicArray_setSecondIndexTo3_returnsException() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();

        assertThrows(DynamicArrayImpl.DynamicArrayException.class, () -> dynamicArray1.set(1, 3));
    }

    @Test
    void emptyDynamicArray_setNegativeIndexTo3_returnsException() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();

        assertThrows(DynamicArrayImpl.DynamicArrayException.class, () -> dynamicArray1.set(-1, 3));
    }

    @Test
    void createDynamicArrayWith1Capcity_add2Elements_shouldResize() {
        DynamicArray dynamicArray = new DynamicArrayImpl(1);
        assertEquals(1, dynamicArray.getCapacity());
        dynamicArray.pushback(1);
        assertDoesNotThrow(() -> dynamicArray.pushback(2));
        assertEquals(2, dynamicArray.getCapacity());
    }

    @Test
    void pushBack51ElementsOnDefaultCapacity_resizesTheArray() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        assertEquals(DynamicArray.DEFAULT_CAPACITY, dynamicArray1.getCapacity());
        assertEquals(0, dynamicArray1.getSize());

        for (int i = 0; i < 50; i++) dynamicArray1.pushback(i);

        assertEquals(50, dynamicArray1.getSize());

        dynamicArray1.pushback(50);
        assertEquals(51, dynamicArray1.getSize());
        assertEquals(100, dynamicArray1.getCapacity());

        for (int i = 0; i < 51; i++) assertEquals(i, dynamicArray1.get(i));
    }

    @Test
    void empthyDynamicArray_resizeCall_resizesTheArray() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();
        assertEquals(DynamicArray.DEFAULT_CAPACITY, dynamicArray1.getCapacity());
        assertEquals(0, dynamicArray1.getSize());

        dynamicArray1.resize();

        assertEquals(DynamicArray.DEFAULT_CAPACITY * 2, dynamicArray1.getCapacity());
        assertEquals(0, dynamicArray1.getSize());
    }

    @Test
    void push2Elements_popback1_popsLastElement() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();

        dynamicArray1.pushback(1);
        dynamicArray1.pushback(2);

        assertEquals(2, dynamicArray1.popback());
        assertEquals(1, dynamicArray1.getSize());
    }

    @Test
    void push2Elements_popback2Elements_sizeIsZero() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();

        dynamicArray1.pushback(1);
        dynamicArray1.pushback(2);

        assertEquals(2, dynamicArray1.popback());
        assertEquals(1, dynamicArray1.popback());
        assertEquals(0, dynamicArray1.getSize());
    }

    @Test
    void push2Elements_popback3Elements_returnException() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl();

        dynamicArray1.pushback(1);
        dynamicArray1.pushback(2);

        assertEquals(2, dynamicArray1.popback());
        assertEquals(1, dynamicArray1.popback());
        assertThrows(DynamicArrayImpl.DynamicArrayException.class, () -> dynamicArray1.popback());
    }

    @Test
    void capacity2Array_push2Elements_popback2Elements_doesNotResize() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl(2);

        dynamicArray1.pushback(1);
        dynamicArray1.pushback(2);

        assertEquals(2, dynamicArray1.popback());
        assertEquals(1, dynamicArray1.popback());
        assertEquals(0, dynamicArray1.getSize());
        assertEquals(2, dynamicArray1.getCapacity());
    }

    @Test
    void capacity2Array_push3Elements_resizes() {
        DynamicArray dynamicArray1 = new DynamicArrayImpl(2);

        dynamicArray1.pushback(1);
        dynamicArray1.pushback(2);
        dynamicArray1.pushback(3);

        assertEquals(3, dynamicArray1.getSize());
        assertEquals(4, dynamicArray1.getCapacity());
    }
}
