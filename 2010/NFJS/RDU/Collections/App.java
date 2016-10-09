import java.util.*;

class Utils
{
    public static <T> List<T> fromArray(T[] src)
    {
        List<T> results = new ArrayList<T>(src.length);
        for (T s : src)
            results.add(s);
        return results;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = Arrays.asList(
            new Person("Neal", "Laney", 21),
            new Person("Marina", "Harrell", 25),
            new Person("David", "Cate", 36),
            new Person("Gaj", "Kabra", 35),
            new Person("Lynn", "Kurtz", 24),
            new Person("Nama", "Eljabaly", 23),
            new Person("John", "Smith", 39),
            new Person("Ted", "Neward", 39,
                new Person("Michael", "Neward", 17),
                new Person("Matthew", "Neward", 10) )
        );
//        System.out.println(people);

        Person m = people.get(1);
        System.out.println(m);        
        m.getChildren().add(new Person("Billy Bob", "Smith", 39));
        System.out.println(m.getChildren().size());
        

        SortedSet<Person> wannabeDrinkers = new TreeSet<Person>(Person.BY_AGE);
        wannabeDrinkers.addAll(people);
        System.out.println(wannabeDrinkers);
        
        SortedSet<Person> drinkers = wannabeDrinkers.tailSet(new Person(null, null, 21));
        for (Person p : drinkers)        
            System.out.println("Have a beer, " + p.getFirstName());

        /*        
        for (Person p : people)
            if (p.getAge() >= 21)
                System.out.println("Have a beer, " + p.getFirstName());
        */
    }
}

