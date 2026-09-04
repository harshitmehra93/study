package study.ocp.streams;

import java.util.*;
import java.util.stream.*;
class JoinNonBlankStrings{
	public static void main(String... args){
		Stream<String> a = Stream.of("It","starts","",null,"with","one","thing","I","dont","know","why");
		System.out.println(joinWithDash(a));
	}

	static String joinWithDash(Stream<String> in){
		String result = in
			.filter(s->s!=null)
			.map(s->s.trim())
			.filter(s->s!="")
			.reduce("",(a,b)->a+"-"+b);
		return result.substring(1,result.length());
	}
}