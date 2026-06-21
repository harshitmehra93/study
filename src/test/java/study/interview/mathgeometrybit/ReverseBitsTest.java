package study.interview.mathgeometrybit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ReverseBitsTest {

    private final ReverseBits solution = new ReverseBits();

    @Test
    void reversesExampleInput() {
        int result = solution.reverseBits(43261596);

        assertEquals(964176192, result);
    }

    @Test
    void reversesZeroToZero() {
        int result = solution.reverseBits(0);

        assertEquals(0, result);
    }

    @Test
    void movesLowestBitToSignBit() {
        int result = solution.reverseBits(1);

        assertEquals(Integer.MIN_VALUE, result);
    }

    @Test
    void movesSignBitToLowestBit() {
        int result = solution.reverseBits(Integer.MIN_VALUE);

        assertEquals(1, result);
    }

    @Test
    void handlesAllBitsSet() {
        int result = solution.reverseBits(-1);

        assertEquals(-1, result);
    }

    @Test
    void reversesNegativeInputAsRawBits() {
        int result = solution.reverseBits(-3);

        assertEquals(-1073741825, result);
    }
}
