package study.ocp.lambdas;

// A4

import java.time.*;
interface MyTime{
	LocalTime currentTime();
}
class CurrentTimeMethodReference{
	public static void main(String... args){
		MyTime myTime = LocalTime::now;
		System.out.println(myTime.currentTime());
	}
}