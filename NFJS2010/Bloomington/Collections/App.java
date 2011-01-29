import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Bill", "Malone", 30),
            new Person("Matt", "Ayers", 27),
            new Person("Aaron", "Chambers", 45),
            new Person("Wayne", "Caldwell", 39),
            new Person("Christina", "Yelinek", 23),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        //System.out.println(people);
        
        Person c = people.get(4);
        //System.out.println(c);
        //c.getChildren().add(new Person("Billy Bob", "Yelinek", 0));
        
        people.add(new Person("Terri", "Gottleaber", 29));
        //System.out.println(people);
        
        SortedSet<Person> people2 = new TreeSet<Person>(Person.BY_AGE);
        people2.addAll(people);
        //System.out.println(people2);
        
        SortedSet<Person> drinkers = people2.tailSet(new Person("", "", 21));
        for (Person drinker : drinkers)
            System.out.println("Have a beer, " + drinker.getFirstName());
    }
}





