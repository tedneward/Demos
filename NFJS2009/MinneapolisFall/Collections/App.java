import java.util.*;

class CollectionUtils
{
	public static <T> List<T> fromArray(T[] arg)
	{
		List<T> result = new ArrayList<T>(arg.length);
		for (T t : arg)
			result.add(t);
		return result;
	}
}

public class App
{
	public static void main(String[] args)
	{
		List<Person> people = new ArrayList<Person>(Arrays.asList(
			new Person("David", "Kerman", 26),
			new Person("Sri", "Yeluri", 26),
			new Person("Swapna", "Kalabhar", 18),
			new Person("Swarna", "Kanuk", 26),
			new Person("Tim", "McGuire", 43),
			new Person("Michael", "Neward", 16),
			new Person("Matthew", "Neward", 10)
		));
		//System.out.println(people);
		
		people.add(new Person("Tim", "Wick", 54));
		System.out.println(people);

		System.out.println("===================");

		Person s = people.get(2);
		System.out.println(s);
		s.getChildren().add(new Person("Billy Bob", "Kalabhar", 0));
		
		System.out.println("===================");
		
		SortedSet<Person> agedPeople = new TreeSet<Person>(Person.BY_AGE);
		agedPeople.addAll(people);
		System.out.println(agedPeople);
		
		SortedSet<Person> drinkers = agedPeople.tailSet(new Person("", "", 21));
		System.out.println(drinkers);
		for (Person drinker : drinkers)
			System.out.println("have a beer, " + drinker + "!");
	}
}






