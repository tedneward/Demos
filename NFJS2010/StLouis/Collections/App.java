import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(new Person[] { 
            new Person("Paul", "Hawke", 40),
            new Person("Anna", "Chow", 26),
            new Person("Brandon", "Spies", 28),
            new Person("Darryl", "Parks", 47),
            new Person("Shikhar", "Mishra", 32),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        }));
        people.add(new Person("Brandon", "Murdock", 23));
        System.out.println(people);
        
        System.out.println("==============");
        
        SortedSet<Person> interim = new TreeSet<Person>(Person.BY_AGE);
        interim.addAll(people);
        SortedSet<Person> drinkers = interim.tailSet(new Person("", "", 21));
        for (Person it : drinkers)
            System.out.println("Have a beer, " + it.getFirstName() + "!");
    }
}

