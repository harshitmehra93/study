import java.util.stream.*;
import java.util.*;
class Test21{
	public static void main(String... args){
		Stream<Double> inf = Stream.generate(Math::random).limit(1000);
		inf = Stream.generate(Math::random).limit(1000);
		inf.forEach(System.out::println);
		System.out.println("----");

		System.out.println(inf.findFirst());
	}
}