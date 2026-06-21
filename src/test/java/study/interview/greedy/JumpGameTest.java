package study.interview.greedy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class JumpGameTest {

    @Test
    void exampleReachable() {
        JumpGame solution = new JumpGame();

        int[] nums = {2, 3, 1, 1, 4};

        assertTrue(solution.canJump(nums));
    }

    @Test
    void exampleNotReachable() {
        JumpGame solution = new JumpGame();

        int[] nums = {3, 2, 1, 0, 4};

        assertFalse(solution.canJump(nums));
    }

    @Test
    void singleElementIsAlreadyAtEnd() {
        JumpGame solution = new JumpGame();

        int[] nums = {0};

        assertTrue(solution.canJump(nums));
    }

    @Test
    void zeroAtStartWithMoreElementsCannotMove() {
        JumpGame solution = new JumpGame();

        int[] nums = {0, 1};

        assertFalse(solution.canJump(nums));
    }

    @Test
    void canJumpDirectlyToEnd() {
        JumpGame solution = new JumpGame();

        int[] nums = {4, 0, 0, 0, 0};

        assertTrue(solution.canJump(nums));
    }

    @Test
    void zerosAreFineIfAlreadyReachablePastThem() {
        JumpGame solution = new JumpGame();

        int[] nums = {2, 0, 0};

        assertTrue(solution.canJump(nums));
    }

    @Test
    void getsStuckBeforeEnd() {
        JumpGame solution = new JumpGame();

        int[] nums = {1, 0, 1, 0};

        assertFalse(solution.canJump(nums));
    }

    @Test
    void largeJumpSkipsOverBadZero() {
        JumpGame solution = new JumpGame();

        int[] nums = {2, 0, 2, 0, 1};

        assertTrue(solution.canJump(nums));
    }

    @Test
    void farthestReachExpandsGradually() {
        JumpGame solution = new JumpGame();

        int[] nums = {1, 2, 0, 1, 0};

        assertTrue(solution.canJump(nums));
    }

    @Test
    void farthestReachDoesNotExpandEnough() {
        JumpGame solution = new JumpGame();

        int[] nums = {1, 1, 0, 1};

        assertFalse(solution.canJump(nums));
    }
}
