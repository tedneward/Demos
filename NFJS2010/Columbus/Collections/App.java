import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Jeff", "Chambers", 53),
            new Person("Elango", "Narayanasamy", 50),
            new Person("Sudhakar", "Ganapathy", 36),
            new Person("Liping", "Xu", 29),
            new Person("Darrell", "Howard", 49),
            new Person("Esther", "Szeto", 36),
            new Person("Kishore", "Smith", 36),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Li", "Hsu", 29));
        System.out.println(people);

        System.out.println("============");
        
        //Person e = people.get(5);
        //e.getChildren().add(new Person("Billy Bob", "Szeto", 29));
        
        //for (Person it : people)
        //    if (it.getAge() >= 21)
        //        System.out.println("Have a beer, " + it.getFirstName() + "!");
        
        SortedSet<Person> potentialDrinkers = new TreeSet<Person>(Person.BY_AGE);
        potentialDrinkers.addAll(people);
        
        SortedSet<Person> drinkers = potentialDrinkers.tailSet(new Person("", "", 21));
        System.out.println(drinkers);
        for (Person it : drinkers)
            System.out.println("Have a beer, " + it.getFirstName() + "!");
    }
}


