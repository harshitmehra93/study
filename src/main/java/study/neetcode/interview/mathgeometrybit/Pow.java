package study.neetcode.interview.mathgeometrybit;

/*
Implement pow(x, n), which calculates x raised to the power n.
double myPow(double x, int n)
Examples:
x = 2.0, n = 10  -> 1024.0
x = 2.1, n = 3   -> 9.261
x = 2.0, n = -2  -> 0.25
 */
public class Pow {
    public double myPow(double base, int power) {
        if (base == 1) return 1;
        if (base == 0 && power > 0) return 0;
        if (power == 0) return 1;

        long exp = power;
        if (exp < 0) {
            exp = -exp;
            base = 1 / base;
        }

        double result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = result * base;
                exp--;
            }

            base = base * base;
            exp = exp / 2;
        }
        return result;
    }
}
