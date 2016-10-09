import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("John", "Carney", 38),
            new Person("Laleh", "Samani", 29),
            new Person("David", "Hoover", 23),
            new Person("Mathi", "Vairavan", 34),
            new Person("Param", "Kollengode", 37),
            new Person("Jose", "Mendez", 38),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 10)
        ));
        
        people.add(new Person("Ann", "Ford", 39));
        //System.out.println(people);
        
        //Person la = people.get(1);
        //people.get(1).getChildren().add(new Person("Billy Bob", "Samani", 25));

        SortedSet<Person> peopleSet = new TreeSet<Person>(Person.BY_AGE);
        peopleSet.addAll(people);
        SortedSet<Person> drinkers = peopleSet.tailSet(new Person("", "", 21));
        System.out.println(drinkers);
        
        for (Person p : drinkers)
            System.out.println("Have a beer " + p.getFirstName() + "!");
    }
}



