package study.coreskills.fenwick;

import lombok.Getter;

public class FenwickTree {

    @Getter int size;

    int[] fenwickTree;

    int[] arr;

    public int[] getArr() {
        return arr.clone();
    }

    public int[] getFenwickTree() {
        return fenwickTree.clone();
    }

    public FenwickTree(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("array cannot be null");
        this.arr = arr.clone();
        size = arr.length;
        fenwickTree = new int[arr.length + 1];
        buildTree();
    }

    public FenwickTree() {
        this(new int[0]);
    }

    private void buildTree() {
        for (int i = 1; i <= size; i++) {
            fenwickTree[i] += arr[i - 1];

            int next = getNext(i);
            if (next != -1) {
                fenwickTree[next] += fenwickTree[i];
            }
        }
    }

    /*
     * GetNext()
     * 1. Take 2's complement of Original number
     * 2. And 2's complement with Original Number
     * 3. Add the result to Original number
     * */
    public int getNext(int index) {
        if (index == 0 || index > size || index < 0) return -1;

        int lowestSetBit = -index & index;
        int sum = lowestSetBit + index;

        return sum > size ? -1 : sum;
    }

    /*
     * GetParent
     * 1. 2's complement of index
     * 2. And it with original number
     * 3. Subtract it from original number
     * */
    public int getParent(int index) {
        if (index == 0 || index > size || index < 0) return -1;
        int lowestSetBit = -index & index;
        return index - lowestSetBit;
    }

    public int getPrefixSum(int index) {
        if (index >= size || index < 0) throwIndexNotAvailableException();
        int parentIndex = index + 1;
        int sum = 0;
        while (parentIndex != -1) {
            sum += fenwickTree[parentIndex];
            parentIndex = getParent(parentIndex);
        }
        return sum;
    }

    public void updateIndex(int index, int num) {
        if (index >= size || index < 0) throwIndexNotAvailableException();
        int diff = num - arr[index];
        arr[index] = num;
        int nextIndex = index + 1;
        while (nextIndex != -1) {
            fenwickTree[nextIndex] += diff;
            nextIndex = getNext(nextIndex);
        }
    }

    private static void throwIndexNotAvailableException() {
        throw new IllegalArgumentException("index is not available");
    }
}
