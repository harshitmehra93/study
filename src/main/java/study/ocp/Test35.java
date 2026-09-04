/*
12) Word frequency (flatMap + grouping)
Write:

Map<String, Long> wordFrequency(List<String> lines)

Split each line on whitespace, lowercase, ignore blanks, count occurrences.

Constraints: flatMap, Collectors.groupingBy(Function.identity(), counting()).

*/

import java.util.*;
import java.util.stream.*;

class Test35{
	public static void main(String... args){
		List<String> a = List.of("His palms are sweaty knees weaks arms are heavy",
			"theres vommit on his sweater already moms speghetti hes nervous but",
			"on the surface he looks calm and ready to drop bombs but he keeps",
			"on forgetting what he wrote down Looks");

		var result = wordFrequency(a);
		System.out.println("Hi");
		System.out.println(result);

	}
	static Map<String, Long> wordFrequency(List<String> lines){
		return lines.stream()
			.map(line->line.toLowerCase())
			.map(line->line.trim())
			.filter(line->line.length()>0)
			.flatMap(line->{
				var l = List.of(line.split(" "));
				System.out.println(l);
				return l.stream();
			})
			.collect(
				HashMap<String,Long>::new,
				(map,s)->{
					System.out.println(s);
					var prev = map.getOrDefault(s,0L);
					map.put(s,prev+1);
				},
				(m1,m2)->{
					for(var entry : m1.entrySet()){
						System.out.println(entry);
						var m2Value = m2.getOrDefault(entry.getKey(),0L);
						m1.put(entry.getKey(),entry.getValue()+m2Value);
					}
					m2.clear();
				}
			);
	}
}