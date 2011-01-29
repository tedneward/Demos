import java.util.*;
//import fj.*;

interface FilterFn<T>  // F<T,Boolean>
{
    public boolean apply(T it);
}
interface ActionFn<T>   // F<T,Unit>
{
    public void apply(T it);
}
interface TransformerFn<R,T>  // F<T,R>
{
    public R apply(T it);
}
interface FoldFn<R,T>   // F2<R,T,R>
{
    public R apply(R accum, T it);
}


abstract class Option<T>
    implements Iterable<T>
{
    public abstract boolean isSome();
    public abstract boolean isNone();
    
    public abstract T get();
}
final class Some<T> extends Option<T>
{
    public Some(T val) { value = val; }
    
    public T value;   

    public boolean isSome() { return true; }
    public boolean isNone() { return false; }
    
    public T get() { return value; }
    
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            boolean seen = false;
            public boolean hasNext() { return !seen; }
            public T next() { 
                seen = true;
                return value;
            }
            public void remove() { throw new UnsupportedOperationException(); }
        };
    }
}
final class None<T> extends Option<T>
{
    public None() { }
    
    public boolean isSome() { return false; }
    public boolean isNone() { return true; }
    
    public T get() { throw new NullPointerException(); }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            public boolean hasNext() { return false; }
            public T next() { throw new RuntimeException("You are stupid"); }
            public void remove() { throw new UnsupportedOperationException(); }
        };
    }
}



class Utils
{
    public static <T> List<T> convert(T[] src)
    {
        List<T> results = new ArrayList<T>(src.length);
        for (T p : src)
            results.add(p);
        return results;
    }
    public static <R,T> R foldLeft(R seed, List<T> src, FoldFn<R,T> folder)
    {
        R accum = seed;
        for (T it : src)
            accum = folder.apply(accum, it);
        return accum;
    }
    public static <T> Option<T> find(List<T> src, FilterFn<T> criteria)
    {
        for (T it : src)
            if (criteria.apply(it))
                return new Some(it);
        return new None();
    }
    public static <T> List<T> filter(List<T> src, FilterFn<T> criteria)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (criteria.apply(it))
                results.add(it);
        return results;
    }
    /*
    public static <T> List<T> filter(List<T> src, F<T,Boolean> criteria)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (criteria.f(it))
                results.add(it);
        return results;
    }
    */
    public static <R,T> List<R> xform(List<T> src, TransformerFn<R,T> transformer)
    {
        List<R> results = new ArrayList<R>();
        for (T it : src)
            results.add(transformer.apply(it));
        return results;
    }
    public static <T> void map(List<T> src, ActionFn<T> action)
    {
        for (T it : src)
            action.apply(it);
    }
}

public class App
{
    public int add(int left, int right) { return left + right; }


    public static void main(String... args)
    {
        /*
        F2<Integer,Integer,Integer> add = new F2<Integer,Integer,Integer>() {
            public Integer f(Integer left, Integer right) {
                return left + right;
            }
        };
        System.out.println("1 + 1 = " + (add.f(1,1)));
        
        F<Integer,Integer> increment = add.f(1);
        System.out.println("inc 3 = " + (increment.f(3)));
        */
        
        
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Ron", "Teeple", 61),
            new Person("George", "Andrews", 35),
            new Person("Eric", "Smith", 27),
            new Person("Matt", "Philip", 31),
            new Person("Saranya", "Raghavan", 24),
            new Person("Guy", "Levy", 32),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        ));
        people.add(new Person("Kamil", "Chmielewski", 28));

        /*
        List<Person> newards = Utils.filter(people, new F<Person,Boolean>() {
            public Boolean f(Person it) { return it.getLastName().equals("Neward"); }
        });
        
        List<Person> drinkers = Utils.filter(people, new FilterFn<Person>() {
            public boolean apply(Person it) { return it.getAge() >= 21; }
        });
        Utils.map(drinkers, new ActionFn<Person>() {
            public void apply(Person it) { System.out.println(it); }
        });
        
        List<String> lastNames = Utils.xform(people, new TransformerFn<String,Person>() {
            public String apply(Person it) { return it.getLastName(); }
        });
        Utils.map(lastNames, new ActionFn<String>() {
            public void apply(String it) { System.out.println(it); }
        });

        int accum = 0;
        for (Person it : people)
            accum = accum + it.getAge();
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
        */
        
        /*
        for (Person p : people)
            if (p.getAge() >= 21)
                System.out.println("Have a beer, " + p.getFirstName() + "!");
         */

        for (Person res : Utils.find(people, new FilterFn<Person>() {
            public boolean apply(Person it) { return it.getFirstName().equals("Fred"); }
        }))
            System.out.println(res.getFirstName());
            
    }
}



