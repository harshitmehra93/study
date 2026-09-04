// D17

interface Parser{
	Integer parseInt(String a);
}
class Test6{
	public static void main(String... args){
		Parser parser =Integer::parseInt;

		print(parser.parseInt("123"));
		print(parser.parseInt("1234"));
		print(parser.parseInt("12367"));
		print(parser.parseInt("12356"));
		print(parser.parseInt("123a"));
	}

	static void print(int str){
		System.out.println(str);
	}
}