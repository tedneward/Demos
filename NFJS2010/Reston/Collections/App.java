import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Phuong", "Lai", 29),
            new Person("Steve", "Gorman", 43),
            new Person("Wei", "Chen", 29),
            new Person("Jeremy", "Purdy", 35),
            new Person("Sundar", "Smith", 31),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Brett", "Matsko", 34));
        //System.out.println(people.toString());
        
        //Person p = people.get(0);
        //p.getChildren().add(new Person("Billy Bob", "Lai", 25));
        
        //for (Person p : people)
        //    if (p.getAge() >= 21)
        //        System.out.println("Have a beer, " + p.getFirstName() + "!");
        
        SortedSet<Person> pba = new TreeSet<Person>(Person.BY_AGE);
        pba.addAll(people);
        
        SortedSet<Person> drinkers = pba.tailSet(new Person("", "", 21));
        System.out.println(drinkers);
        for(Person p : drinkers)
            System.out.println("Have a beer, " + p.getFirstName() + "!");
        
    }
}




