import java.util.*;
import fj.*;

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
    public static <T> void act(List<T> src, F<T, Void> f)
    {
        for (T it : src)
            f.f(it);
    }
    public static <T> void act(List<T> src, ActionFn<T> f)
    {
        for (T it : src)
            f.apply(it);
    }
    public static <T> List<T> filter(List<T> src, FilterFn<T> criteria)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (criteria.apply(it))
                results.add(it);
        return results;
    }
    public static <R,T> List<R> transform(List<T> src, MapFn<R,T> mapper)
    {
        List<R> results = new ArrayList<R>(src.size());
        for (T it : src)
            results.add(mapper.apply(it));
        return results;
    }
    public static <R,T> R foldLeft(R seed, List<T> src, FoldFn<R,T> folder)
    {
        R accum = seed;
        for (T it : src)
            accum = folder.apply(accum, it);
        return accum;
    }
    
}

public class App
{
    
    public static void main(String... args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Sri", "Komandur", 40),
            new Person("Larry", "Hohm", 50),
            new Person("Jessica", "Bie", 33),
            new Person("Lindsey", "Sutherland", 40),
            new Person("Ian", "Pedersen", 43),
            new Person("Dan", "Teifke", 51),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        ));
        people.add(new Person("Kim", "Todd", 22));
        //System.out.println(people);

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
        
        List<String> names = Utils.transform(people, new MapFn<String,Person>() {
            public String apply(Person it) {
                return it.getFirstName();
            }
        });
        List<String> names = Utils.transform(people, (Person it) -> it.getFirstName());


        Utils.act(names, new ActionFn<String>() {
            public void apply(String it) {
                System.out.println(it);
            }
        });
        
        int accum = 0;
        for (Person p : people)
            accum = accum + p.getAge();
        System.out.println("total age = " + accum);
        
        Integer totalAge = Utils.foldLeft(0, people, new FoldFn<Integer,Person>() {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge();
            }
        });
        System.out.println("total age = " + totalAge);
        
        String xml = Utils.foldLeft("<people>", people, new FoldFn<String,Person>() {
            public String apply(String accum, Person it) {
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        }) + "</people>";
        System.out.println(xml);
    }
}










