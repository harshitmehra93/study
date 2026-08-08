package study.contest.leetcode.weeklycontest513;

/**
 * LeetCode 4012 — Count of Unfinished Tasks After Each Shift
 *
 * <p>You are given arrays {@code tasks} and {@code shifts}. {@code tasks[i]} is the time required
 * for the i-th task, and {@code shifts[j]} is the time available during the j-th shift. Tasks are
 * processed in order.
 *
 * <p>Work on a partially completed task carries into the next shift. If all tasks are completed
 * during a shift, that shift ends immediately, its unused time is discarded, and the next shift
 * starts again from task 0. A task currently in progress is unfinished.
 *
 * <p>Return an array where the j-th value is the number of unfinished tasks immediately after the
 * j-th shift.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: tasks = [1, 4, 4], shifts = [9, 1, 4]
 * Output: [0, 2, 1]
 *
 * Input: tasks = [2, 3, 4], shifts = [20, 4, 5]
 * Output: [0, 2, 0]
 *
 * Input: tasks = [4, 2], shifts = [3, 6, 1]
 * Output: [2, 0, 2]
 * </pre>
 *
 * <p>Constraints: {@code 1 <= tasks.length, shifts.length <= 100000} and {@code 1 <= tasks[i],
 * shifts[i] <= 1000000000}.
 *
 * @see <a
 *     href="https://leetcode.com/problems/count-of-unfinished-tasks-after-each-shift/">Problem</a>
 */
public class CountOfUnfinishedTasksAfterEachShift {
    public int[] countTasks(int[] tasks, int[] shifts) {
        int[] result = new int[shifts.length];

        int taskPointer = 0;
        int currentTaskBalance = tasks[0];
        for (int i = 0; i < shifts.length; i++) {
            int shiftCapacity = shifts[i];
            while (shiftCapacity != 0) {
                if (currentTaskBalance == 0) {
                    if (taskPointer == tasks.length - 1) taskPointer = 0;
                    else taskPointer++;

                    currentTaskBalance = tasks[taskPointer];
                }

                if (shiftCapacity > currentTaskBalance) {
                    shiftCapacity -= currentTaskBalance;
                    currentTaskBalance = 0;
                } else if (shiftCapacity == currentTaskBalance) {
                    shiftCapacity = 0;
                    currentTaskBalance = 0;
                } else {
                    shiftCapacity = 0;
                    currentTaskBalance -= shiftCapacity;
                }
            }
            result[i] = tasks.length - taskPointer - 1;
            if (currentTaskBalance > 0) result[i]++;
        }
        return result;
    }
}
