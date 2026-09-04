package study.ocp.lambdas.lowercase;

// D18

interface Parser{
	String toLowerCase(String a);
}
class LowercaseMethodReference{
	public static void main(String... args){
		Parser parser = String::toLowerCase;

		print(parser.toLowerCase("ABC"));
		print(parser.toLowerCase("DEF"));
		print(parser.toLowerCase("HELLO"));
		print(parser.toLowerCase("HARSHIT"));
		print(parser.toLowerCase("MEHRA"));
	}
	static void print(String str){
		System.out.println(str);
	}
}