/*
Write:

Map<Boolean, List<Integer>> partitionEven(List<Integer> in)

Partition into even vs odd, preserving encounter order within each list.

Constraints: partitioningBy.

*/

import java.util.*;
import java.util.stream.*;

class Test33{
	public static void main(String... args){
		Stream<String> a = Stream.of("His","palms","are","sweaty","knees","weaks","arms","are","heavy",
			"theres","vommit","on","his","sweater","already","moms","speghetti","hes","nervous","but",
			"on","the","surface","he","looks","calm","and","ready","to","drop","bombs","but","he","keeps",
			"on","forgetting","what","he","wrote","down","Looks");

		Map<Boolean, List<String>> result = a
			.filter(s->s!=null)
			.filter(s->s!="")
			.filter(s->s.length()>0)
			.map(s->s.trim())
			.map(s->s.toLowerCase())
			.distinct()
			.collect(Collectors.partitioningBy(s->s.length()>4));
		System.out.println(result);

	}
}