import java.util.*;

class Utils
{
    public static <T> List<T> convert(T[] src)
    {
        List<T> results = new ArrayList<T>(src.length);
        for (T p : src)
            results.add(p);
        return results;
    }
}

public class App
{

    public static void main(String... args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Ron", "Teeple", 61),
            new Person("George", "Andrews", 35),
            new Person("Eric", "Smith", 27),
            new Person("Matt", "Philip", 31),
            new Person("Saranya", "Raghavan", 24),
            new Person("Guy", "Levy", 32),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        ));
        people.add(new Person("Kamil", "Chmielewski", 28));
        
        /*
        for (Person p : people)
            if (p.getAge() >= 21)
                System.out.println("Have a beer, " + p.getFirstName() + "!");
         */

        SortedSet<Person> sortedPeople = new TreeSet<Person>(new Comparator<Person>() {
            public int compare(Person lhs, Person rhs) {
                return lhs.getAge() - rhs.getAge();
            }
        });
        sortedPeople.addAll(people);
        
        SortedSet<Person> drinkers = sortedPeople.tailSet(new Person(null, null, 21));
        System.out.println(drinkers);

        for (Person p : drinkers)
            System.out.println("Have a beer, " + p.getFirstName() + "!");
    }
}



