package study.ocp.lambdas;

// A3

interface StringUtil{
	String concat(String a, String b);
}
public class StringConcatenationMethodReference{
	public static void main(String... args){
		StringUtil stringUtil = String::concat;

		System.out.println(stringUtil.concat("H","M"));
		stringUtil.concat("Ha","Me");
		stringUtil.concat("Har","Meh");
	}	
}