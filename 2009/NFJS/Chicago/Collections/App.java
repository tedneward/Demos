import java.util.*;

public class App
{
	public static void main(String[] args)
	{
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
		//System.out.println(people);
		
		people.add(new Person("Saul", "Coleman", 42));
		System.out.println(people);
		
		Person d = people.get(5);
		//System.out.println(d);
		//d.getChildren().add(new Person("Bobby Joe", "Seaman", 0));
		//   throws UnsupportedOperationException!		
		
		SortedSet<Person> peopleByAge = new TreeSet<Person>(Person.BY_AGE);
		peopleByAge.addAll(people);
		System.out.println(peopleByAge);
		
		SortedSet<Person> drinkers = peopleByAge.tailSet(new Person("", "", 21));
		System.out.println(drinkers);
		for (Person d2 : drinkers)
			System.out.println("Have a beer, " + d2.getFirstName() + "!");
	}
}











