package study.contest.leetcode.weeklycontest422;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * LeetCode 3341 — Find Minimum Time to Reach Last Room I
 *
 * <p>Rooms form an {@code n x m} grid. Room {@code (i,j)} can be entered only after {@code
 * moveTime[i][j]}, adjacent moves take one second, and the start is {@code (0,0)} at time zero.
 * Return the earliest arrival time at {@code (n-1,m-1)}.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: moveTime = [[0,4],[4,4]]
 * Output: 6
 *
 * Input: moveTime = [[0,0,0],[0,0,0]]
 * Output: 3
 *
 * Input: moveTime = [[0,1],[1,2]]
 * Output: 3
 * </pre>
 *
 * <p>Constraints: {@code 2 <= n,m <= 50}; {@code 0 <= moveTime[i][j] <= 10^9}.
 *
 * @see <a href="https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/">Problem</a>
 */
public class FindMinimumTimeToReachLastRoomI {
    HashMap<Position, Integer> distanceFromSource;

    public int minTimeToReach(int[][] moveTime) {
        distanceFromSource = new HashMap<>();
        for (int i = 0; i < moveTime.length; i++) {
            for (int j = 0; j < moveTime[0].length; j++) {
                distanceFromSource.put(new Position(i, j), Integer.MAX_VALUE);
            }
        }
        distanceFromSource.put(new Position(0, 0), 0);

        PriorityQueue<Step> q = new PriorityQueue<>((a, b) -> Integer.compare(a.cost(), b.cost()));
        q.offer(new Step(new Position(0, 0), 0, 0));

        Set<Position> visited = new HashSet<>();

        int result = 0;
        while (q.size() != 0) {
            var step = q.poll();

            if (isTarget(step.pos(), moveTime)) {
                result = step.cost();
                break;
            }

            if (!visited.add(step.pos())) {
                continue;
            }

            for (Position nei : getNeighbours(step.pos(), moveTime)) {
                if (!visited.contains(nei)) {
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
        int u = distanceFromSource.get(previousStep.pos());
        int newDistance =
                Math.max(u, moveTime[current.i()][current.j()])
                        + costToMove(previousStep.steps() + 1);

        int oldDistance = distanceFromSource.get(current);
        if (newDistance < oldDistance) {
            q.offer(new Step(current, previousStep.steps() + 1, newDistance));
            distanceFromSource.put(current, newDistance);
        }
    }

    int costToMove(int steps) {
        return 1;
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
