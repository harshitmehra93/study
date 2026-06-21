package study.interview.stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class DailyTemperaturesTest {

    @Test
    void example1() {
        DailyTemperatures solution = new DailyTemperatures();

        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};

        assertArrayEquals(
                new int[] {1, 1, 4, 2, 1, 1, 0, 0}, solution.dailyTemperatures(temperatures));
    }

    @Test
    void strictlyIncreasingTemperatures() {
        DailyTemperatures solution = new DailyTemperatures();

        int[] temperatures = {30, 40, 50, 60};

        assertArrayEquals(new int[] {1, 1, 1, 0}, solution.dailyTemperatures(temperatures));
    }

    @Test
    void strictlyDecreasingTemperatures() {
        DailyTemperatures solution = new DailyTemperatures();

        int[] temperatures = {60, 50, 40, 30};

        assertArrayEquals(new int[] {0, 0, 0, 0}, solution.dailyTemperatures(temperatures));
    }

    @Test
    void noWarmerDayForEqualTemperatures() {
        DailyTemperatures solution = new DailyTemperatures();

        int[] temperatures = {70, 70, 70};

        assertArrayEquals(new int[] {0, 0, 0}, solution.dailyTemperatures(temperatures));
    }

    @Test
    void equalTemperatureDoesNotCountAsWarmer() {
        DailyTemperatures solution = new DailyTemperatures();

        int[] temperatures = {70, 70, 71};

        assertArrayEquals(new int[] {2, 1, 0}, solution.dailyTemperatures(temperatures));
    }

    @Test
    void warmerDayFarInFuture() {
        DailyTemperatures solution = new DailyTemperatures();

        int[] temperatures = {75, 71, 69, 72, 76};

        assertArrayEquals(new int[] {4, 2, 1, 1, 0}, solution.dailyTemperatures(temperatures));
    }

    @Test
    void singleDayHasNoFutureWarmerDay() {
        DailyTemperatures solution = new DailyTemperatures();

        int[] temperatures = {80};

        assertArrayEquals(new int[] {0}, solution.dailyTemperatures(temperatures));
    }

    @Test
    void mixedSmallCase() {
        DailyTemperatures solution = new DailyTemperatures();

        int[] temperatures = {30, 60, 90};

        assertArrayEquals(new int[] {1, 1, 0}, solution.dailyTemperatures(temperatures));
    }

    @Test
    void valleyThenWarmerTemperature() {
        DailyTemperatures solution = new DailyTemperatures();

        int[] temperatures = {80, 70, 60, 70, 80, 90};

        assertArrayEquals(new int[] {5, 3, 1, 1, 1, 0}, solution.dailyTemperatures(temperatures));
    }

    @Test
    void repeatedTemperaturesBeforeWarmerDay() {
        DailyTemperatures solution = new DailyTemperatures();

        int[] temperatures = {70, 69, 69, 69, 71};

        assertArrayEquals(new int[] {4, 3, 2, 1, 0}, solution.dailyTemperatures(temperatures));
    }
}
