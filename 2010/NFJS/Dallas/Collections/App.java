import java.util.*;

class Utils
{
    public static <T> List<T> fromArray(T[] data)
    {
        List<T> results = new ArrayList<T>(data.length);
        for (T it : data)
            results.add(it);
        return results;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Ted", "Neward", 39),
            new Person("Al", "Akbar", 57),
            new Person("Theresa", "Regan", 29),
            new Person("Brian", "Hurley", 42),
            new Person("Tom", "Bozich", 55),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Sudheer", "Gollapudi", 27));
        //System.out.println(people);
        
        Person t = people.get(2);
        t.getChildren().add(new Person("Billy Bob", "Regan", 0));
        
        SortedSet<Person> people2 = new TreeSet<Person>(Person.BY_AGE);
        people2.addAll(people);
        //System.out.println(people2);
        
        SortedSet<Person> drinkers = people2.tailSet(new Person(null, null, 21));
        System.out.println(drinkers);
        for (Person it : drinkers)
            System.out.println("Have a beer, " + it.getFirstName() + "!");

        /*
        for (Person it : people)
            if (it.getAge() > 21)
        */
    }
}




