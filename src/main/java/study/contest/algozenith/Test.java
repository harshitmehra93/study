package study.contest.algozenith;

public class Test {
    public static void main(String[] args) {
        System.out.println(1 % 3);
        System.out.println((-1 + 3) % 3);
        System.out.println((-2 + 3) % 3);
        System.out.println((-3 + 3) % 3);
        //        new Test().solve();
    }

    void solve() {
        int A = 4;
        int B = 20;

        for (int num = A; num <= B; num++) {
            if (isLucky(num)) System.out.print(num + " ");
        }
    }

    boolean isLucky(int num) {
        if (num == 0) return false;
        while (num != 0) {
            int rem = num % 10;
            if (rem != 4 && rem != 7) return false;
            num /= 10;
        }
        return true;
    }
}
