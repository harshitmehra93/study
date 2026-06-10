package study.neetcode.interview.greedy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JumpGameIITest {

    @Test
    void example1() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {2, 3, 1, 1, 4};

        assertEquals(2, solution.jump(nums));
    }

    @Test
    void example2() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {2, 3, 0, 1, 4};

        assertEquals(2, solution.jump(nums));
    }

    @Test
    void singleElementNeedsZeroJumps() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {0};

        assertEquals(0, solution.jump(nums));
    }

    @Test
    void directJumpToEnd() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {4, 1, 1, 1, 1};

        assertEquals(1, solution.jump(nums));
    }

    @Test
    void allOnesNeedsEveryStep() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {1, 1, 1, 1};

        assertEquals(3, solution.jump(nums));
    }

    @Test
    void greedyMustChooseBetterReachNotNearestJump() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {2, 3, 1, 1, 4};

        assertEquals(2, solution.jump(nums));
    }

    @Test
    void largeJumpAfterFirstStep() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {1, 4, 1, 1, 1, 1};

        assertEquals(2, solution.jump(nums));
    }

    @Test
    void graduallyExpandingRanges() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {1, 2, 3, 4, 5};

        assertEquals(3, solution.jump(nums));
    }

    @Test
    void zerosAreOkayIfSkipped() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {2, 0, 2, 0, 1};

        assertEquals(2, solution.jump(nums));
    }

    @Test
    void longerMixedCase() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {2, 1, 3, 1, 1, 1, 4};

        assertEquals(3, solution.jump(nums));
    }

    @Test
    void improvingFarthestWithinSameJumpRangeShouldNotCountExtraJumps() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {3, 4, 3, 2, 5, 4, 3};

        assertEquals(3, solution.jump(nums));
    }

    @Test
    void directReachImprovementIsNotSameAsJumpCount() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {4, 1, 1, 3, 1, 1, 1};

        assertEquals(2, solution.jump(nums));
    }

    @Test
    void farthestImprovesMultipleTimesInsideSameJumpLayer() {
        JumpGameII solution = new JumpGameII();

        int[] nums = {2, 2, 2, 0, 0};

        assertEquals(2, solution.jump(nums));
    }
}
