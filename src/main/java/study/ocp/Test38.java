/*

Level 1 — Warm up (downstream basics)

Dept → list of employee names in sorted order

Dept → number of employees with salary > 50k

Dept → total salary of employees whose name starts with ‘A’

Dept → set of unique name lengths
*/

import java.util.*;
import java.util.stream.*;
public class Test38{
	public static void main(String... args){
		List<Emp> employees = new LinkedList<>();
		for(int i=0;i<10;i++){
			String dept = randomStringGenerator(4);
			for(int j=0;j<10;j++){
				double salary = Math.random()*100000;
				String name = randomStringGenerator(8);
				employees.add(new Emp(dept,name,salary));
			}
		}
		// Dept → list of employee names in sorted order
		System.out.println(
			employees.stream().map(e->e.name()).sorted().collect(Collectors.toList())
		);
		System.out.println(
			employees.stream().collect(Collectors.groupingBy(
				Emp::dept,
				Collectors.mapping(
					e->e.name(),
					Collectors.toCollection(TreeSet::new)
				)
			))
		);

		// Dept → number of employees with salary > 50k
		System.out.println(
			employees.stream()
				.filter(e->e.salary()>50000.0)
				.collect(Collectors.groupingBy(
					Emp::dept,
					Collectors.counting()
				))
		);

		// Dept → total salary of employees whose name starts with ‘A’
		System.out.println(
			employees.stream()
				.collect(Collectors.groupingBy(
					Emp::dept,
					Collectors.mapping(
						e->e,
						Collectors.summingDouble(e->e.name().toLowerCase().startsWith("a") ? e.salary() : 0.0)
					)
				))

		);


		// Dept → set of unique name lengths
		System.out.println(
			employees.stream()
				.collect(Collectors.groupingBy(
					Emp::dept,
					Collectors.mapping(
						e->e.name().length(),
						Collectors.toSet()
					)
				))
		);

	}


	static String randomStringGenerator(int length){
		StringBuilder sb = new StringBuilder();
		String key = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i=0;i<length;i++){
			int randomIndex = (int)(Math.random()*10000)%key.length();
			randomIndex = randomIndex < 0 ? -randomIndex:randomIndex;
			sb.append(key.charAt(randomIndex));
		}
		return sb.toString();
	}
}
record Emp(String dept, String name, double salary) {}