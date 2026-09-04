/*
9) Grouping + mapping
Write:

Map<Integer, List<String>> wordsByLengthLower(List<String> in)

Group by length of trimmed word, values are lowercased words, ignore blanks/nulls.

Constraints: groupingBy + mapping.


*/
import java.util.*;
import java.util.stream.*;

class Test32{
	public static void main(String... args){
		Stream<String> a = Stream.of("His","palms","are","sweaty","knees","weaks","arms","are","heavy",
			"theres","vommit","on","his","sweater","already","moms","speghetti","hes","nervous","but",
			"on","the","surface","he","looks","calm","and","ready","to","drop","bombs","but","he","keeps",
			"on","forgetting","what","he","wrote","down","Looks");
		Map<Integer, List<String>> result = a
			.filter(s->s!=null)
			.filter(s->s!="")
			.filter(s->s.length()>0)
			.map(s->s.trim())
			.map(s->s.toLowerCase())
			.distinct()
			.collect(Collectors.groupingBy(String::length));
		System.out.println(result);

	}
}