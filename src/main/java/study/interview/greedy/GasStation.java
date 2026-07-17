package study.interview.greedy;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumOfGas = 0;
        for (int i = 0; i < gas.length; i++) sumOfGas += gas[i];

        int sumOdCost = 0;
        for (int i = 0; i < cost.length; i++) sumOdCost += cost[i];

        if (sumOfGas < sumOdCost) return -1;

        int gasInTank = 0;
        int candidateStart = 0;
        for (int i = 0; i < gas.length; i++) {
            if (gasInTank < 0) {
                candidateStart = i;
                gasInTank = 0;
            }

            gasInTank += gas[i] - cost[i];
        }
        return candidateStart;
    }
}
