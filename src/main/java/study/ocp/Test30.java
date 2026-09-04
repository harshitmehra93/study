import java.util.*;
import java.util.stream.*;
class Test30{
	public static void main(String... args){
		IntStream a = IntStream.iterate(1,(n)->n+1).limit(10);
		System.out.println(product(a));
		
	}
	static int product(IntStream s){
		return s.reduce(1,(a,b)->a*b);
	}

}