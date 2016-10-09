import java.util.*;

class Util
{
    public static <T> List<T> fromArray(T[] src)
    {
        List<T> results = new ArrayList<T>(src.length);
        for (T it : src)
            results.add(it);
        return results;
    }
}

public class App
{
    public static void main(String... args)
    {
        List<Person> people = Arrays.asList(
            new Person("Alla", "Bushoy", 39),
            new Person("Ken", "Wadland", 60),
            new Person("Jamie", "McCullough", 38),
            new Person("Li", "Tang", 47),
            new Person("Steve", "Valin", 45),
            new Person("Lilia", "Medina", 39),
            new Person("George", "Foto", 50),
            new Person("Iwona", "Borowicz", 29),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        );
        System.out.println(people);
        
        //Person i = people.get(7);
        //System.out.println(i);
        
        //i.getChildren().add(new Person("Billy Bob", "Boro-whatever", 35));
        //System.out.println(i.getChildren().size());
        

        
        SortedSet<Person> possibleDrinkers = new TreeSet<Person>(Person.BY_AGE);
        possibleDrinkers.addAll(people);
        
        SortedSet<Person> drinkers = possibleDrinkers.tailSet(new Person("", "", 21));
        
        for (Person it : drinkers)
            System.out.println("Have a beer, " + it.getFirstName() + "!");
    }
}




















