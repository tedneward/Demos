import java.util.*;

class Utils
{
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Blair", "Dewey", 45),
            new Person("Janet", "Dejager", 34),
            new Person("Doug", "Graham", 44),
            new Person("David", "Kessler", 35),
            new Person("Kery", "Stanley", 35),
            new Person("Richard", "Harms", 39),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Matt", "Druhl", 39));
        //System.out.println(people);
        
        Person k = people.get(4);
        System.out.println(k.getChildren());
        //k.getChildren().add(new Person("Billy Bob", "Stanley", 17));
        //System.out.println(k.getChildren());
        
        SortedSet<Person> pba = new TreeSet<Person>(Person.BY_AGE);
        pba.addAll(people);
        
        SortedSet<Person> drinkers = pba.tailSet(new Person("", "", 21));
        for (Person it : drinkers)
            System.out.println("Have a beer, " + it.getFirstName() + "!");
    }
}







