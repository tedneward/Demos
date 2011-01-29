import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Domingo", "Polican", 46),
            new Person("Audrey", "Trout", 29),
            new Person("Marc", "van der Snoot", 39),
            new Person("Zettie", "Chin-Fong", 39),
            new Person("Avneet", "Kang", 27),
            new Person("Blake", "Vanier", 25),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Prashant", "Smith", 40));
        //System.out.println(people);
        
        SortedSet<Person> sba = new TreeSet<Person>(Person.BY_AGE);
        sba.addAll(people);
        System.out.println(sba);
        
        SortedSet<Person> drinkers = sba.tailSet(new Person("", "", 21));
        for (Person p : drinkers)
            System.out.println(p.getFirstName() + " have a beer!");
        
        /*
        for (Person p : people)
            if (p.getAge() >= 21)
                System.out.println(p.getFirstName() + " have a beer!");
        */
    }
}