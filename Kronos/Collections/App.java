import java.util.*;
import static java.util.Arrays.*;

interface Action<T>
{
    public void apply(T it);
}
interface Pred<T>
{
    public boolean apply(T it);
}

class Utils
{
    public static <T> void iterate(List<T> src, Action<T> action)
    {
        for (T it : src)
            action.apply(it);
    }
    public static <T> List<T> filter(List<T> src, Pred<T> pred)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (pred.apply(it))
                results.add(it);
        return results;
    }
    public static <T> List<T> arrayToList(T[] src)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            results.add(it);
        return results;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = asList(
            new Person("Patrick", "Girdwood", 38),
            new Person("Edmund", "Tanase", 37),
            new Person("Pascal", "Labbe", 22),
            new Person("Katie", "Reczko", 23),
            new Person("Ted", "Neward", 40),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        );
        System.out.println(people.toString() );
        
        System.out.println("========");
        
        SortedSet<Person> drinkers = new TreeSet<Person>(Person.COMPARE_BY_AGE);
        drinkers.addAll(people);
        System.out.println(drinkers);

        /*
        for (Person it : people)
            if (it.getAge() >= 21)
                System.out.println("Have a beer, " + it.getFirstName() + "!");
        */
        //List<Person> drinkers
        //    .filter(people, #(Person it) -> return it.getAge() >= 21;)
        //    .iterate(#(Person it) -> System.out.println("Have a beer, " + it.getFirstName() + "!"); );
    }
}
