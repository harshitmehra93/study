// A5
interface Maths{
	int square(int a);
}
class Test5{
	public static void main(String... args){
		Maths maths = a->a*a;

		print(maths.square(1));
		print(maths.square(11));
		print(maths.square(156));
		print(maths.square(112));
		print(maths.square(18));
		print(maths.square(190));
	}
	static void print(int str){
		System.out.println(str);
	}
}