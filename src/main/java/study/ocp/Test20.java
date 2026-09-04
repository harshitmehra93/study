import java.util.*;

class Test20{
	static Random random=new Random();
	static Set<Dinosaur> set;
	public static void main(String... args){
		System.out.println("");
		System.out.println("");
		System.out.println("");
		set = new TreeSet<Dinosaur>(Comparator.comparing(Dinosaur::getName));
		setupDinos(10);
		set.forEach(System.out::println);

		System.out.println("");
		System.out.println("");
		System.out.println("");

		Set<Dinosaur> speciesSet = new TreeSet<Dinosaur>(Comparator.comparing(Dinosaur::getSpecies));
		speciesSet.addAll(set);
		speciesSet.forEach(System.out::println);

		System.out.println("");
		System.out.println("");
		System.out.println("");

		Set<Dinosaur> ageSet = new TreeSet<Dinosaur>(Comparator.comparing(Dinosaur::getAge));
		ageSet.addAll(set);
		ageSet.forEach(System.out::println);
	}

	public static void setupDinos(int num){
		for(int i=0;i<num;i++){
			int age = random.nextInt(0,1000);
			String name = "Dino-"+getRandomString();
			String species = "S-"+getRandomString();
			set.add(new Dinosaur(name,species,age));
		}
		
	}
	

	public static String getRandomString(){
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < 4; j++) {
		    sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
		}
		return sb.toString();
	}
}


class Dinosaur{
	String name;
	String species;
	int age;
	Dinosaur(String n, String s,int a){
		name=n;
		species=s;
		age=a;
	}
	String getName(){return name;}
	String getSpecies(){return species;}
	int getAge(){return age;}
	public String toString(){
		return "name="+name+" species="+species+" age="+age;
	}
}