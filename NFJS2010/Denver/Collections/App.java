import java.util.*;

class Utils
{
    public static <T> List<T> convert(T[] src)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            results.add(it);
        return results;
    }
}

public class App
{
    public static void main(String... args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("John", "Pierce", 65),
            new Person("Robert", "Rockey", 39),
            new Person("Ping", "Jian", 29),
            new Person("Mo", "Bergman", 32),
            new Person("Match", "Grun", 56),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        ));
        people.add(new Person("Don", "Suit", 56));
        System.out.println(people);
        
        Person ping = people.get(2);
        System.out.println("Ping has " + ping.getChildren().size() + " kids");
        ping.getChildren().add(new Person("Billy Bob", "JoBob", 39));
        System.out.println("Ping has " + ping.getChildren().size() + " kids");

        /*        
        for (Person p : people)
            if (p.getAge() >= 21)
                System.out.println("Have a beer, " + p.getFirstName() + "!");
        */
        SortedSet<Person> sp = new TreeSet<Person>(Person.BY_AGE);
        sp.addAll(people);
        SortedSet<Person> drinkers = sp.tailSet(new Person("", "", 21));
        for (Person p : drinkers)
            System.out.println("Have a beer, " + p.getFirstName() + "!");
    }
}



