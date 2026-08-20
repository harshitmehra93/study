package study.contest.leetcode.weeklycontest422;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 3342 — Find Minimum Time to Reach Last Room II
 *
 * <p>Rooms form an {@code n x m} grid. Room {@code (i,j)} can be entered only after {@code
 * moveTime[i][j]}. Starting at {@code (0,0)} at time zero, adjacent moves alternately take one and
 * two seconds, beginning with one second. Return the earliest arrival time at {@code (n-1,m-1)}.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: moveTime = [[0,4],[4,4]]
 * Output: 7
 *
 * Input: moveTime = [[0,0,0,0],[0,0,0,0]]
 * Output: 6
 *
 * Input: moveTime = [[0,1],[1,2]]
 * Output: 4
 * </pre>
 *
 * <p>Constraints: {@code 2 <= n,m <= 750}; {@code 0 <= moveTime[i][j] <= 10^9}.
 *
 * @see <a href="https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii/">Problem</a>
 */
public class FindMinimumTimeToReachLastRoomII {
    int[][] distanceFromSource;

    public int minTimeToReach(int[][] moveTime) {
        distanceFromSource = new int[moveTime.length][moveTime[0].length];
        for (var arr : distanceFromSource) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        distanceFromSource[0][0] = 0;

        PriorityQueue<Step> q = new PriorityQueue<>((a, b) -> Integer.compare(a.cost(), b.cost()));
        q.offer(new Step(new Position(0, 0), 0, 0));

        boolean[][] visited = new boolean[moveTime.length][moveTime[0].length];

        int result = 0;
        while (q.size() != 0) {
            var step = q.poll();

            if (isTarget(step.pos(), moveTime)) {
                result = step.cost();
                break;
            }

            int i = step.pos().i();
            int j = step.pos().j();

            if (visited[i][j]) {
                continue;
            }
            visited[i][j] = true;

            for (Position nei : getNeighbours(step.pos(), moveTime)) {
                if (!visited[nei.i()][nei.j()]) {
                    relax(step, nei, q, moveTime);
                }
            }
        }
        return result;
    }

    boolean isTarget(Position current, int[][] moveTime) {
        return current.i() == moveTime.length - 1 && current.j() == moveTime[0].length - 1;
    }

    void relax(Step previousStep, Position current, PriorityQueue<Step> q, int[][] moveTime) {
        int u = distanceFromSource[previousStep.pos().i()][previousStep.pos().j()];
        int newDistance =
                Math.max(u, moveTime[current.i()][current.j()])
                        + costToMove(previousStep.steps() + 1);

        int oldDistance = distanceFromSource[current.i()][current.j()];
        if (newDistance < oldDistance) {
            q.offer(new Step(current, previousStep.steps() + 1, newDistance));
            distanceFromSource[current.i()][current.j()] = newDistance;
        }
    }

    int costToMove(int steps) {
        return steps % 2 == 0 ? 2 : 1;
    }

    List<Position> getNeighbours(Position pos, int[][] moveTime) {
        List<Position> neighbours = new ArrayList<>();
        int i = pos.i();
        int j = pos.j();
        addIfValid(i + 1, j, neighbours, moveTime);
        addIfValid(i - 1, j, neighbours, moveTime);
        addIfValid(i, j + 1, neighbours, moveTime);
        addIfValid(i, j - 1, neighbours, moveTime);
        return neighbours;
    }

    void addIfValid(int i, int j, List<Position> neighbours, int[][] moveTime) {
        if (i >= 0 && i < moveTime.length && j >= 0 && j < moveTime[0].length) {
            neighbours.add(new Position(i, j));
        }
    }

    record Step(Position pos, int steps, int cost) {}

    record Position(int i, int j) {}
}
