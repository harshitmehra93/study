package study.contest.leetcode.weeklycontest419;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 3320 — Count The Number of Winning Sequences
 *
 * <p>Alice and Bob play {@code n} rounds using Fire ({@code F}), Water ({@code W}), or Earth
 * ({@code E}). Water beats Fire, Fire beats Earth, and Earth beats Water; equal creatures score no
 * points. Alice's moves are given by {@code s}. Bob may not use the same creature in consecutive
 * rounds.
 *
 * <p>Return, modulo {@code 1_000_000_007}, the number of Bob's distinct valid move sequences for
 * which Bob finishes with strictly more points than Alice.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: s = "FFF"
 * Output: 3
 *
 * Input: s = "FWEFW"
 * Output: 18
 * </pre>
 *
 * <p>Constraints: {@code 1 <= s.length() <= 1000}; every character is {@code F}, {@code W}, or
 * {@code E}.
 *
 * @see <a href="https://leetcode.com/problems/count-the-number-of-winning-sequences/">Problem</a>
 */
public class CountTheNumberOfWinningSequences {
    Map<State, Long> memo;
    char[] choices = new char[] {'F', 'W', 'E'};
    static final long MOD = 1_000_000_007L;

    public int countWinningSequences(String s) {
        memo = new HashMap<>();
        return (int) (countWinningSequences(s, 0, 0, 'O') % MOD);
    }

    long countWinningSequences(String s, int index, int score, char previousChoiceOfBob) {
        if (index == s.length()) {
            if (score > 0) return 1;
            return 0;
        }
        State state = new State(index, score, previousChoiceOfBob);
        if (memo.containsKey(state)) return memo.get(state);

        char aliceMove = s.charAt(index);
        long winningSequences = 0;
        for (var bobsChoice : choices) {
            if (previousChoiceOfBob == bobsChoice) continue;

            if (aliceMove == 'F') {
                if (bobsChoice == 'F') {
                    winningSequences += countWinningSequences(s, index + 1, score, bobsChoice);
                } else if (bobsChoice == 'W') {
                    winningSequences += countWinningSequences(s, index + 1, score + 1, bobsChoice);
                } else if (bobsChoice == 'E') {
                    winningSequences += countWinningSequences(s, index + 1, score - 1, bobsChoice);
                }
            } else if (aliceMove == 'W') {
                if (bobsChoice == 'F') {
                    winningSequences += countWinningSequences(s, index + 1, score - 1, bobsChoice);
                } else if (bobsChoice == 'W') {
                    winningSequences += countWinningSequences(s, index + 1, score, bobsChoice);
                } else if (bobsChoice == 'E') {
                    winningSequences += countWinningSequences(s, index + 1, score + 1, bobsChoice);
                }
            } else if (aliceMove == 'E') {
                if (bobsChoice == 'F') {
                    winningSequences += countWinningSequences(s, index + 1, score + 1, bobsChoice);
                } else if (bobsChoice == 'W') {
                    winningSequences += countWinningSequences(s, index + 1, score - 1, bobsChoice);
                } else if (bobsChoice == 'E') {
                    winningSequences += countWinningSequences(s, index + 1, score, bobsChoice);
                }
            }
        }
        memo.put(state, winningSequences % MOD);
        return winningSequences % MOD;
    }

    record State(int index, int score, char previousChoiceOfBob) {}
}
