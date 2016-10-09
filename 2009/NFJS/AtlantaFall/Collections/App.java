import java.util.*;

public class App
{
	public static void main(String[] args)
	{
		List<Person> people = new ArrayList<Person>(Arrays.asList(
			new Person("Matt", "Noarmsnolegssitsinfrontofadoor", 29),
			new Person("Eric", "Asbury", 38),
			new Person("Wendy", "Chang", 25),
			new Person("Teresa", "Robisson", 26),
			new Person("Chris", "Hampton", 43),
			new Person("Randy", "Alexander", 30),
			new Person("Ted", "Neward", 38),
			new Person("Michael", "Neward", 16),
			new Person("Matthew", "Neward", 10)
		));
		System.out.println(people);
		
		people.add(new Person("Susan", "Siegler", 29));
		System.out.println(people);
		
		System.out.println(people.get(9));
		Person susan = people.get(9);
		//susan.getChildren().add(new Person("Billy Bob", "Siegler", 0));
		
		System.out.println("========================");
		
		NavigableSet<Person> pset = new TreeSet<Person>(Person.BY_AGE);
		pset.addAll(people);
		System.out.println(pset);
		
		SortedSet<Person> drinkers = pset.tailSet(new Person("", "", 21));
		System.out.println(drinkers);
	}
}

















