import java.util.*;

public class App
{
	public static void main(String[] args)
		throws Exception
	{
		for (int i : Seq.take(5, MathUtils.random(20)))
			System.out.println(i);
		
		for (String line : Lists.filter(Seq.take(7, FileUtils.readlines("App.java")),
				new Predicate<String>() {
					public boolean test(String s) { return !(s.trim().isEmpty()); }
				}) )
		{
			System.out.println(line);
		}
		
		Collection<Person> people = new FunctorCollection<Person>();
		people.onAdd = new Predicate<Person>() {
			public boolean test (Person p) {
				return p.getFirstName().length() > 0 &&
					p.getLastName().length() > 0 &&
					p.getAge() > 21;
			}
		};

		/*		
		List<Person> people = new ArrayList<Person>(Arrays.asList(
			new Person("Andrew", "Bauman", 26),
			new Person("Tahir", "Faqir", 39),
			new Person("Ajay", "Goyal", 38),
			new Person("Alex", "Chan", 45),
			new Person("Pio", "Raj", 40),
			new Person("Aaron", "Seaman", 26),
			new Person("Dagmar", "Murray", 50),
			new Person("Ted", "Neward", 38),
			new Person("Michael", "Neward", 16),
			new Person("Matthew", "Neward", 10)
		));
		
		for (Person p : Lists.find(people, new Predicate<Person>() {
				public boolean test(Person p) { return p.getFirstName().equals("Alex"); }
			}))
		{
			System.out.println(p);
		}
		
		System.out.println("=====================");
		
		List<Person> drinkers = Lists.filter(people, new Predicate<Person>() {
			public boolean test(Person p) { return p.getAge() > 21; }
		});
		List<Tuple2<String, String>> loudNames = 
			Lists.transform(people, new Function<Tuple2<String, String>, Person>() {
				public Tuple2<String,String> apply(Person p) { 
					return new Tuple2(p.getFirstName().toUpperCase(), p.getLastName().toUpperCase());
				}
			});
		Lists.map(loudNames, new Procedure<Tuple2<String,String>>() {
			public void apply(Tuple2<String,String> s) { System.out.println(s); }
		});
		
		Lists.map(drinkers, new Procedure<Person>() {
			public void apply(Person p) { System.out.println("Have a beer, " + p.getFirstName() + "!"); }
		});
		
		*/
	}
}


















