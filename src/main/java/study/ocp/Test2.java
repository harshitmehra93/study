// A2
import java.util.*;
interface Maths{
	boolean isPrime(int n);
}

public class Test2{
	public static void main(String... args){
		Maths maths = n->{
			double rootOfN= Math.sqrt(n);
			boolean isPrime=true;
			for(int i=2;i<=rootOfN;i++){
				if(n%i==0){
					isPrime=false;
					break;
				}
			}
			System.out.printf("%d is prime : %b\n",n,isPrime);
			return isPrime;
		};
		for(int i=0;i<50;i++)
			maths.isPrime(i);
	}
}