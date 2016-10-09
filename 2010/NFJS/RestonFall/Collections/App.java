import java.util.*;

class Utils
{
    public static <E> List<E> convert(E... src)
    {
        List<E> result = new ArrayList<E>();
        Collections.addAll(result, src);
        return result;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Carleen", "Dickerson", 29),
            new Person("Patrice", "Young", 29),
            new Person("Burt", "Rutherford", 38),
            new Person("Michael", "Stakem", 28),
            new Person("Ikjae", "Park", 24),
            new Person("Chris", "Bell", 25),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        ));
        people.add(new Person("Bubba", "B", 78));
        System.out.println(people);
        
        //Person c = people.get(0);
        //System.out.println(c.getChildren().size());
        //c.getChildren().add(new Person("Billy Bob", "B", 76));
        //System.out.println(c.getChildren().size());
        
        SortedSet<Person> pset = new TreeSet<Person>(Person.BY_AGE);
        pset.addAll(people);
        
        SortedSet<Person> drinkers = pset.tailSet(new Person("", "", 21));
        
        for (Person p : drinkers)
            System.out.println("Have a beer, " + p.getFirstName() + "!");
    }
}











