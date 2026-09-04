class Test9{
	public static void main(String... args){
		Tester t1 = System.out::println;
		t1.run("Hello");
		t1.run("Hello1");
		t1.run("Hello2");
		t1.run("Hello3");
		t1.run("Hello4");
		t1.run("Hello5");

	}
}
interface Tester{
	void run(String arg);
}


/*
Supplier T get()
Consumer void accept(T)
BiConsumer void accept(T,U)
Function<T,R> R apply(T)
BiFunction<T,U,R> R apply(T,U)
UnaryOperator<T> T accept(T)
BinaryOperator<T> T accept(T,T)
Predicate<T> boolean test(T)
BiPredicate<T,U> boolean test(T)

Returns a String without taking any parameters - Supplier<String> String get()


Returns a Boolean and takes a String - Predicate<T> boolean test(T), Function<String, boolean> R apply(T), 


Returns an Integer and takes two Integers


*/