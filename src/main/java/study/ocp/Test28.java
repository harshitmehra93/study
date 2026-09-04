import java.util.*;
import java.util.stream.*;
class Test28{
	public static void main(String... args){
		User u = new User();
		u.profile = new Profile();
		u.profile.email="123@google.com";

		User v = new User();
		v.profile = new Profile();
		v.profile.email="abc@google.com";

		User w = new User();
		w.profile = new Profile();
		w.profile.email="google.com";

		User x = new User();
		x.profile = new Profile();

		User z = new User();
		z.profile = new Profile();

		System.out.println(emailOrUnknown(u));
		System.out.println(emailOrUnknown(v));
		System.out.println(emailOrUnknown(w));
		System.out.println(emailOrUnknown(x));
		System.out.println(emailOrUnknown(z));
		System.out.println(emailOrUnknown(null));

	}
	static String emailOrUnknown(User u){
		return Optional
			.ofNullable(u)
			.map(a->a.profile)
			.map(a->a.email)
			.filter(a->a.contains("@"))
			.orElseGet(()->"invalid email");

	}

}

class User { Profile profile; }
class Profile { String email; }