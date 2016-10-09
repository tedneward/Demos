import java.util.*;

interface Filter<T>
{
    public boolean apply(T it);
}
interface Action<T>
{
    public void apply(T it);
}
interface Transformer<R,T>
{
    public R apply(T it);
}
interface Reducer<R,T>
{
    public R apply(R accum, T it);
}

class Utils
{
    public static <T> List<T> filter(List<T> src, Filter<T> filter)
    {
        List<T> results = new ArrayList<T>();
        for (T p : src)
            if (filter.apply(p))
                results.add(p);
        return results;
    }
    public static <R,T> List<R> map(List<T> src, Transformer<R,T> transformer)
    {
        List<R> results = new ArrayList<R>(src.size());
        for (T it : src)
            results.add(transformer.apply(it));
        return results;
    }
    public static <T> void apply(List<? extends T> src, Action<? super T> action)
    {
        for (T p : src)
            action.apply(p);
    }
    public static <R,T> R reduce(List<T> src, R seed, Reducer<R,T> reducer)
    {
        R accum = seed;
        for (T it : src)
            accum = reducer.apply(accum, it);
        return accum;
    } 
    
    public static final Action<Object> PRINTER = new Action<Object>() {
            public void apply(Object it) { 
                System.out.println(it);
            }
        };
}
public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new FunList<Person>(
            new AddFn<Person>() {
                public Person onAdd(Person p) {
                    if (p == null)
                        return new Person("(John)", "(Doe)", -1);
                    else if (p.getFirstName().equals("") &&
                        p.getLastName().equals(""))
                        return new Person("(John)", "(Doe)", -1);
                    else
                        return p;
                }
            },
            Arrays.asList(
                new Person("", "", 29),
                null,
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
        System.out.println(people);
    }
}




