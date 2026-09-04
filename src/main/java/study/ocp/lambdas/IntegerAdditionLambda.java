package study.ocp.lambdas;

// A1

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class IntegerAdditionLambda{
	public static void main(String... args){
		Driver driver = (a,b)->a+b;
		System.out.println(driver.add(1,2));
		System.out.println(driver.add(2,3));
	}
}
interface Driver{
	int add(int a, int b);
}