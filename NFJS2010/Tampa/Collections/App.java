import java.util.*;

class Util
{
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Miki", "Yun", 25),
            new Person("Jason", "Warner", 24),
            new Person("Ron", "Gallant", 36),
            new Person("Mohammad", "Khan", 37),
            new Person("Sangeta", "Kundu", 29),
            new Person("Bavel", "Vassilev", 27),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Johan", "Andrade", 16));
        //System.out.println(people);
        
        SortedSet<Person> pba = new TreeSet<Person>(Person.BY_AGE);
        pba.addAll(people);
        //System.out.println(pba);
        
        SortedSet<Person> drinkers = pba.tailSet(new Person("", "", 21));
        for (Person p : drinkers)
            System.out.println("Have a beer, " + p.getFirstName() + "!");
    }
}



