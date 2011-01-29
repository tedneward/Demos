import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Tracey", "Jacoby", 41),
            new Person("Steven", "Ford", 47),
            new Person("Hima", "Puppala", 27),
            new Person("Karen", "Silverstein", 39),
            new Person("Mike", "Beauchamp", 40),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Kishore", "Smith", 36));
        //System.out.println(people.toString());
        
        //Person hima = people.get(2);
        //System.out.println(hima);
        //hima.getChildren().add(new Person("Billy Bob", "Puppala", 0));
        
        SortedSet<Person> pba = new TreeSet<Person>(Person.BY_AGE);
        pba.addAll(people);
        
        SortedSet<Person> drinkers = pba.tailSet(new Person("", "", 21));
        System.out.println(drinkers);
    }
}




