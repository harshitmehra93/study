package study.ocp.streams;

import java.util.stream.*;
import java.util.*;
class FilteringCollector{
	public static void main(String... args){
		// Stream<String> s = Stream.of("1","2","3","4","5","6","7","8","9");
		Stream<Integer> s = Stream.of(1,2,3,4,5,6,7,8,9);
		// System.out.println(s.collect(Collectors.joining(",")));
		System.out.println(s.collect(Collectors.filtering((a)->a%2==0),Collectors.counting()));


	}
}