import java.util.*;

class Utils
{
    public static <T> List<T> convert(T[] src)
    {
        List<T> result = new ArrayList<T>(src.length);
        for (T p : src) result.add(p);
        return result;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Mike", "Kangas", 37),
            new Person("Luda", "Fisher", 29),
            new Person("Joseph", "Witthuhn", 22),
            new Person("Senid", "Cimic", 27),
            new Person("Ron", "Nasby", 47),
            new Person("Bill", "Reay", 33),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        ));
        people.add(new Person("Darryn", "Kozak", 29));
        //System.out.println(people);
        
        SortedSet<Person> potdrinkers = new TreeSet<Person>(Person.BY_AGE);
        potdrinkers.addAll(people);
        
        SortedSet<Person> drinkers = potdrinkers.tailSet(new Person("", "",21));
        for (Person p : drinkers)
            System.out.println("Have a beer, " + p.getFirstName() + "!");
    }
}










