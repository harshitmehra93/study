package study.ocp.lambdas;

// D19
import java.util.*;
interface Lister{
	ArrayList create();
}
class ArrayListConstructorReference{
	public static void main(String... args){
		Lister lister = ArrayList::new;

		lister.create();
	}
	static void print(String str){
		System.out.println(str);
	}
}