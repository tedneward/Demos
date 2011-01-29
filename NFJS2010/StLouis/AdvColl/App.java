import java.util.*;

interface FilterFn<T>
{
    public boolean apply(T cand);
}
interface ActionFn<T>
{
    public void apply(T cand);
}
interface TransformerFn<R, T>
{
    public R apply(T cand);
}
interface ReduceFn<R, T>
{
    public R apply(R value, T cand);
}

abstract class Option<T>
    implements Iterable<T>
{
    public abstract boolean isNone();
    public abstract boolean isSome();
    public abstract T get();
    
    public abstract Iterator<T> iterator();
}
final class Some<T> extends Option<T>
{
    public Some(T value) { this.value = value; }
    
    public boolean isNone() { return false; }
    public boolean isSome() { return true; }
    public T get() { return value; }

    public Iterator<T> iterator()
    {
        return (Iterator<T>)Collections.singletonList(value).iterator();
    }
    
    private T value;
}
final class None<T> extends Option<T>
{
    public boolean isNone() { return true; }
    public boolean isSome() { return false; }
    public T get() { throw new IllegalArgumentException(); }

    public Iterator<T> iterator()
    {
        return (Iterator<T>)Collections.emptyList().iterator();
    }
}


class Utils
{
    public static <T> void act(List<T> src, ActionFn<T> actionFunc)
    {
        for (T it : src)
            actionFunc.apply(it);
    }
    public static <T> List<T> filter(List<T> src, FilterFn<T> filterFunc)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (filterFunc.apply(it))
                results.add(it);
        return results;
    }
    public static <T> Option<T> findFirst(List<T> src, FilterFn<T> filterFunc)
    {
        for (T it : src)
            if (filterFunc.apply(it))
                return new Some<T>(it);
        return new None<T>();
    }
    public static <R,T> List<R> transform(List<T> src, TransformerFn<R, T> xformFunc)
    {
        List<R> results = new ArrayList<R>();
        for (T it : src)
            results.add(xformFunc.apply(it));
        return results;
    }
    public static <R, T> R reduce(R seed, List<T> src, ReduceFn<R, T> reduceFn)
    {
        R accum = seed;
        for (T it : src)
            accum = reduceFn.apply(accum, it);
        return accum;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(new Person[] { 
            new Person("Paul", "Hawke", 40),
            new Person("Anna", "Chow", 26),
            new Person("Brandon", "Spies", 28),
            new Person("Darryl", "Parks", 47),
            new Person("Shikhar", "Mishra", 32),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        }));
        people.add(new Person("Brandon", "Murdock", 23));
        System.out.println(people);
        
        System.out.println("==============");
        
        List<Person> drinkers = Utils.filter(people, new FilterFn<Person>() {
            public boolean apply(Person cand) {
                return cand.getAge() > 21;
            }
        });
        Utils.act(drinkers, new ActionFn<Person>() {
            public void apply(Person it) {
                System.out.println("Have a beer, " + it.getFirstName() + "!");
            }
        });
        
        List<String> lastNames = Utils.transform(people, new TransformerFn<String, Person>(){
            public String apply(Person it) { return it.getLastName(); }
        });
        System.out.println(lastNames);

        Integer totalAge = 0;
        for (Person p : people)
            totalAge = totalAge + p.getAge();
        System.out.println(totalAge);
        
        Integer totalAge2 = Utils.reduce(0, people, new ReduceFn<Integer, Person>() {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge(); 
            }
        });
        System.out.println(totalAge2);
        
        String xml = Utils.reduce("<persons>", people,
            new ReduceFn<String, Person>() {
                public String apply(String accum, Person it) {
                    return accum + "<person>" + it.getFirstName() + "</person>";
                }
            }) + "</persons>";
        System.out.println(xml);
        
        for (Person fred : Utils.findFirst(people, new FilterFn<Person>() {
            public boolean apply(Person cand) {
                return cand.getFirstName().equals("Ted") &&
                    cand.getLastName().equals("Neward");
            }
        }))
        {
            System.out.println(fred.getAge());
        }
    }
}






