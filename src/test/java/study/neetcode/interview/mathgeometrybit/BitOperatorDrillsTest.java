package study.neetcode.interview.mathgeometrybit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BitOperatorDrillsTest {

    private final BitOperatorDrills drills = new BitOperatorDrills();

    @Test
    void checksWhetherBitIsSet() {
        assertTrue(drills.isBitSet(10, 1));
        assertTrue(drills.isBitSet(10, 3));
        assertFalse(drills.isBitSet(10, 0));
        assertFalse(drills.isBitSet(10, 2));
    }

    @Test
    void checksSignBit() {
        assertTrue(drills.isBitSet(Integer.MIN_VALUE, 31));
        assertFalse(drills.isBitSet(Integer.MAX_VALUE, 31));
    }

    @Test
    void setsBit() {
        assertEquals(11, drills.setBit(10, 0));
        assertEquals(14, drills.setBit(10, 2));
    }

    @Test
    void settingAlreadySetBitLeavesNumberUnchanged() {
        assertEquals(10, drills.setBit(10, 1));
    }

    @Test
    void clearsBit() {
        assertEquals(8, drills.clearBit(10, 1));
        assertEquals(2, drills.clearBit(10, 3));
    }

    @Test
    void clearingAlreadyClearBitLeavesNumberUnchanged() {
        assertEquals(10, drills.clearBit(10, 0));
    }

    @Test
    void togglesBit() {
        assertEquals(11, drills.toggleBit(10, 0));
        assertEquals(8, drills.toggleBit(10, 1));
        assertEquals(2, drills.toggleBit(10, 3));
    }
}
