package study.ocp.streams;

/*
8) Collect into Map with merge function
Write:

Map<Character, Integer> totalLengthsByFirstChar(List<String> in)

For each non-null trimmed non-empty string, group by first character and sum lengths.

Constraints: Collectors.toMap(keyMapper, valueMapper, mergeFunction).

Test: ["aa","ab","b","bbb"] → {a=4, b=4}


*/
import java.util.*;
import java.util.stream.*;

class TotalLengthsByFirstCharacter{
	public static void main(String... args){
		Stream<String> a = Stream.of("His","palms","are","sweaty","knees","weaks","arms","are","heavy",
			"theres","vommit","on","his","sweater","already","moms","speghetti","hes","nervous","but",
			"on","the","surface","he","looks","calm","and","ready","to","drop","bombs","but","he","keeps",
			"on","forgetting","what","he","wrote","down","Looks");
		Map<Character, Integer> result = a
			.filter(s->s!=null)
			.filter(s->s!="")
			.filter(s->s.length()>0)
			.map(s->s.toLowerCase())
			.collect(Collectors.toMap(s->s.charAt(0),s->s.length(),(v1,v2)->v1+v2));
		System.out.println(result);

	}
}