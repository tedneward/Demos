import java.util.*;

class Utils
{
    public static <T> List<T> toList(T[] src)
    {
        List<T> results = new ArrayList<T>();
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
            new Person("Sri", "Komandur", 40),
            new Person("Larry", "Hohm", 50),
            new Person("Jessica", "Bie", 33),
            new Person("Lindsey", "Sutherland", 40),
            new Person("Ian", "Pedersen", 43),
            new Person("Dan", "Teifke", 51),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        ));
        people.add(new Person("Kim", "Todd", 22));
        System.out.println(people);
        
        //Person k = people.get(9);
        //k.getChildren().add(new Person("BillyBob", "Thornton", 54));
        //System.out.println(k.getChildren().size());
        
        
        SortedSet<Person> allPeople = new TreeSet<Person>(Person.BY_AGE);
        allPeople.addAll(people);
        SortedSet<Person> drinkers = allPeople.tailSet(new Person(null, null, 21));
        for (Person p : drinkers)
            System.out.println("Have a beer, " + p + "!");
    }
}










