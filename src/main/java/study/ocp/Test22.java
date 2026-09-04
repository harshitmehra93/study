import java.util.stream.*;
import java.util.*;
class Test22{
	public static void main(String... args){
		Stream<Integer> s = Stream.of(1,2,3,4,5,6,7,8,9);
		Spliterator<Integer> main = s.spliterator();
		Spliterator<Integer> first = main.trySplit();
		first.forEachRemaining(System.out::println);
		// System.out.println(first.tryAdvance(System.out::println));
		// System.out.println(first.tryAdvance(System.out::println));
		// System.out.println(first.tryAdvance(System.out::println));
		// System.out.println(first.tryAdvance(System.out::println));
		// System.out.println(first.tryAdvance(System.out::println));
		// System.out.println(first.tryAdvance(System.out::println));
		Spliterator<Integer> second = main.trySplit();
		second.forEachRemaining(System.out::println);

		Spliterator<Integer> third = main.trySplit();
		third.forEachRemaining(System.out::println);

		Spliterator<Integer> fourth = main.trySplit();
		fourth.forEachRemaining(System.out::println);

		Spliterator<Integer> fifth = main.trySplit();
		fifth.forEachRemaining(System.out::println);

		Spliterator<Integer> a = main.trySplit();
		a.forEachRemaining(System.out::println);

		Spliterator<Integer> b = main.trySplit();
		b.forEachRemaining(System.out::println);

		Spliterator<Integer> c = main.trySplit();
		c.forEachRemaining(System.out::println);

	}
}