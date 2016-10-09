import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Jason", "Belcher", 40),
            new Person("Tim", "Hirschi", 29),
            new Person("Ken", "Beesley", 56),
            new Person("Jaswind", "Sakpan", 31),
            new Person("Gary", "Licaso", 32),
            new Person("Troy", "Coats", 39),
            new Person("Ted", "Neward", 39),
            new Person("Matthew", "Neward", 10),
            new Person("Michael", "Neward", 17)
        ));
        //System.out.println(people);
        
        people.add(new Person("Abdel", "Remani", 25));
        //System.out.println(people);
        
        //Person j = people.get(3);
        //j.getChildren().add(new Person("Billy Bob", "Smith", 21));
        //System.out.println(j.getChildren());
        
        //for (Person it : people)
        //    if (it.getAge() >= 21)
        //        System.out.println("Have a beer, " + it.getFirstName() + "!");
        
        SortedSet<Person> peopleByAge = new TreeSet<Person>(Person.BY_AGE);
        peopleByAge.addAll(people);
        
        SortedSet<Person> drinkers = peopleByAge.tailSet(new Person(null, null, 21));
        System.out.println(drinkers);
        for (Person p : drinkers)
            System.out.println("Have a beer, " + p.getFirstName() + "!");
    }
}






