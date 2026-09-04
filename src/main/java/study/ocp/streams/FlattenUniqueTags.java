package study.ocp.streams;

/*
11) Flatten and dedupe
Write:

List<String> allUniqueTags(List<List<String>> tags)

Flatten nested lists, drop nulls/blanks, lowercase, distinct, sorted.

Constraints: flatMap, filter, map, distinct, sorted, collect.

*/

import java.util.*;
import java.util.stream.*;

class FlattenUniqueTags{
	public static void main(String... args){
		List<List<String>> a = List.of(List.of("His","palms","are","sweaty"),List.of("knees","weaks","arms","are","heavy"),
			List.of("theres","vommit","on","his","sweater","already","moms","speghetti","hes","nervous","but"),
			List.of("on","the","surface","he","looks","calm","and","ready","to","drop","bombs","but","he","keeps"),
			List.of("on","forgetting","what","he","wrote","down","Looks","  "));

		List<String> result = allUniqueTags(a);
		System.out.println(result);

	}
	static List<String> allUniqueTags(List<List<String>> tags){
		return tags.stream()
			.filter(l->l!=null)
			.flatMap(l->l.stream())
			.filter(s->s!=null)
			.map(s->s.trim())
			.filter(s->s.length()>0)
			.map(s->s.toLowerCase())
			.distinct()
			.collect(ArrayList::new,(l,e)->l.add(e),(l1,l2)->l1.addAll(l2));
	}
}