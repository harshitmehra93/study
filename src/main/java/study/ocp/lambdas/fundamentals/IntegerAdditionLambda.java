package study.ocp.lambdas.fundamentals;

// A1

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class IntegerAdditionLambda {
    public static void main(String... args) {
        Driver driver = (a, b) -> a + b;
        System.out.println(driver.add(1, 2));
        System.out.println(driver.add(2, 3));
    }
}

interface Driver {
    int add(int a, int b);
}
