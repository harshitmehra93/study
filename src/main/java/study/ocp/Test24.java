import java.util.*;
import java.util.stream.*;
class Test24{
	public static void main(String... args){
		List<String> a = List.of("aaaaaaa","This","is","the","part","where","I","gain"
			,"good","understanding","of","Streams","and","ace","OCP","and","my","career","becomes","rocket","aaaa","aaaaa");
		a=new ArrayList<String>(a);
		lengthsStartingWithA(a).forEach(System.out::println);
	}

	// Return lengths of strings that start with "a" (case-insensitive), sorted ascending, no nulls.
	static List<Integer> lengthsStartingWithA(List<String> in){
		return in.stream()
			.filter(a->a.toLowerCase().startsWith("a"))
			.map(a->a.length())
			.sorted()
			.collect(Collectors.toList());
	}

}