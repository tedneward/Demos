import java.util.*;

interface FilterFn<T>
{
    public boolean apply(T it);
}
interface ActionFn<T>
{
    public void apply(T it);
}
interface MapFn<R,T>
{
    public R apply(T it);
}
interface FoldFn<R,T>
{
    public R apply(R accum, T it);
}

class Utils
{
    public static <T> List<T> toList(T[] data)
    {
        List<T> results = new ArrayList<T>(data.length);
        for (T p : data)
            results.add(p);
        return results;
    }
    
    public static <T> List<T> filter(List<T> src, FilterFn<T> criteria)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (criteria.apply(it))
                results.add(it);
        return results;
    }
    public static <T> void act(List<T> src, ActionFn<T> action)
    {
        for (T it : src)
            action.apply(it);
    }
    public static <R,T> List<R> map(List<T> src, MapFn<R, T> mapper)
    {
        List<R> results = new ArrayList<R>();
        for (T it : src)
            results.add(mapper.apply(it));
        return results;
    }
    public static <R,T> R fold(R seed, List<T> src, FoldFn<R,T> fold)
    {
        R accum = seed;
        for (T it : src)
            accum = fold.apply(accum, it);
        return accum;
    }
}

public class App
{
    public static final FoldFn<String,Person> XML_IZER = 
        new FoldFn<String,Person>() {
            public String apply(String accum, Person it) {
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        };

    public static void main(String[] args)
    {
        List<Person> people = Arrays.asList(
            new Person("Hong", "Zhou", 29),
            new Person("Jong", "Ling", 52),
            new Person("Myname", "James", 29),
            new Person("Anderson", "Imes", 28),
            new Person("Zach", "Bonham", 40),
            new Person("James", "Latham", 69),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 10)
        );
        System.out.println(people.toString());

        List<Person> drinkers = Utils.filter(people, new FilterFn<Person>() {
            public boolean apply(Person it) {
                return it.getAge() >= 21;
            }
        });
        Utils.act(drinkers, new ActionFn<Person>() {
            public void apply(Person it) {
                System.out.println("Have a beer, " + it.getFirstName() + "!");
            }
        });
        
        List<String> names = Utils.map(people, new MapFn<String,Person>() {
            public String apply(Person it) {
                return it.getLastName();
            }
        });
        System.out.println(names);
        
        int accum = 0;
        for (Person it : people)
            accum = accum + it.getAge();
        System.out.println("total ages = " + accum);
        
        Integer totalAges = Utils.fold(0, people, new FoldFn<Integer,Person>() {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge();
            }
        });
        System.out.println(totalAges);

        String xml = Utils.fold("<people>", people, XML_IZER) + "</people>";
        System.out.println(xml);
    }
}




