import java.util.*;

class CollUtils
{
    public static <T> List<T> arrayCopy(T[] rawData)
    {
        List<T> results = new ArrayList<T>();
        for (T p : rawData)
            results.add(p);
        return results;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Jesse", "Haraldsen", 31),
            new Person("Anu", "Vaidya", 33),
            new Person("David", "Greenlaw", 55),
            new Person("Thierry", "Lemeur", 42),
            new Person("Terry", "Hedin", 36),
            new Person("Ted", "Neward", 39, 
                new Person("Michael", "Neward", 16),
                new Person("Matthew", "Neward", 10))
        ));
        //System.out.println(people);
        
        System.out.println("==============");
        
        Person a = people.get(1);
        //a.getChildren().add(new Person("Billy Bob", "Vaidya", 0));
        
        people.add(new Person("Manij", "Shrestha", 26));
        System.out.println(people);
        
        System.out.println("==============");

        NavigableSet<Person> sba = new TreeSet<Person>(Person.BY_AGE);
        sba.addAll(people);
        //System.out.println(sba); 
        
        SortedSet<Person> drinkers = sba.tailSet(new Person(null, null, 21));
        System.out.println(drinkers);
    }
}










