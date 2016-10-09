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
interface MapFn<T1,T2>
{
    public T2 apply(T1 it);
}
interface FoldFn<R,T>
{
    public R apply(R accum, T it);
}

class Maths
{
    public static final F2<Integer,Integer,Integer> add = 
        new F2<Integer,Integer,Integer>() { 
            public Integer f(final Integer l, final Integer r) { 
                return l + r; 
            }
        };
}

class Utils
{
    public static <T> List<T> convert(T[] src)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            results.add(it);
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
    public static <T> void act(List<T> src, ActionFn<T> actor)
    {
        for (T it : src)
            actor.apply(it);
    }
    public static <T1,T2> List<T2> transform(List<T1> src, MapFn<T1,T2> transformer)
    {
        List<T2> results = new ArrayList<T2>();
        for (T1 it : src)
            results.add(transformer.apply(it));
        return results;
    }
    public static <R,T> R fold(R seed, List<T> src, FoldFn<R,T> folder)
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
            new Person("John", "Pierce", 65),
            new Person("Robert", "Rockey", 39),
            new Person("Ping", "Jian", 29),
            new Person("Mo", "Bergman", 32),
            new Person("Match", "Grun", 56),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        ));
        people.add(new Person("Don", "Suit", 56));
        //System.out.println(people);
        
        /*        
        for (Person p : people)
            if (p.getAge() >= 21)
                System.out.println("Have a beer, " + p.getFirstName() + "!");
        */
        /*
        SortedSet<Person> sp = new TreeSet<Person>(Person.BY_AGE);
        sp.addAll(people);
        SortedSet<Person> drinkers = sp.tailSet(new Person("", "", 21));
        for (Person p : drinkers)
            System.out.println("Have a beer, " + p.getFirstName() + "!");
        */
        List<Person> drinkers = Utils.filter(people, new FilterFn<Person>() {
            public boolean apply(Person it) { return it.getAge() >= 21; }
        });
        Utils.act(drinkers, new ActionFn<Person>() {
            public void apply(Person it) { 
                System.out.println("Have a beer, " + it.getFirstName() + "!"); 
            }
        });
        List<String> lastNames = Utils.transform(people, new MapFn<Person,String>() {
            public String apply(Person it) {
                return it.getLastName();
            }
        });
        System.out.println(lastNames);
        
        int totalAge = 0;
        for (Person p : people)
            totalAge = totalAge + p.getAge();
        System.out.println("Total age = " + totalAge);
        
        List<Person> olderPeople = Utils.transform(people, new MapFn<Person,Person>() {
            public Person apply(Person it) { 
                return new Person(it.getFirstName(), it.getLastName(), it.getAge()+1); 
            }
        });
        
        int foldedAge = ParallelUtils.fold(0, people, new FoldFn<Integer,Person>() {
            public Integer apply(Integer accum, Person it) { return accum + it.getAge(); }
        });
        System.out.println("Total age = " + foldedAge);

        /* PROPOSED: Java 8
        String xml = Utils.fold("<people>", people, 
            (accum, it) -> accum + "<person>" + it.getFirstName() + "</person>") 
                +"</people>";
        */

        String xml = Utils.fold("<people>", people, new FoldFn<String,Person>() {
            public String apply(String accum, Person it) { 
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        }) + "</people>";
        System.out.println(xml);
        
        int i = 1; int j = 2;
        System.out.println(Maths.add.f(1,2));
        
        F<Integer,Integer> inc = Maths.add.f(1);
        System.out.println(inc.f(1));
    }
}








