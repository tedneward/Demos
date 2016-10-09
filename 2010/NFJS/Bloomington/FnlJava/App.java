import java.util.*;

interface FilterFn<T>
{
    public boolean f(T obj);
}

interface ActionFn<T>
{
    public void f(T obj);
}

interface TransformerFn<R, T>
{
    public R f(T obj);
}

interface SumFn<R, T>
{
    public R f(R curr, T obj);
}




class Utils
{
    public static final ActionFn<Object> print = new ActionFn<Object>() {
        public void f(Object obj) { System.out.println(obj); }
    };
    
    public static <T> List<T> 
        filter(List<T> data, FilterFn<T> condition)
    {
        List<T> results = new ArrayList<T>(data.size());
        for (T p : data)
            if (condition.f(p))
                results.add(p);
        return results;
    }
    public static <R, T> List<R> 
        transform(List<T> data, TransformerFn<R, T> xformer)
    {
        List<R> results = new ArrayList<R>(data.size());
        for (T p : data)
            results.add(xformer.f(p));
        return results;
    }
    public static <T> void
        act(List<T> data, ActionFn<T> action)
    {
        for (T p : data)
            action.f(p);
    }
    public static <R, T> R
        foldFront(R seed, List<T> data, SumFn<R, T> action)
    {
        for (T i : data)
            seed = action.f(seed, i);
        return seed;
    }
    public static <T> Option<T>
        find(List<T> data, FilterFn<T> pred)
    {
        for (T p : data)
            if (pred.f(p))
                return new Some(p);
        return new None();
    }
}

abstract class Option<T>
    implements Iterable<T>
{
    public abstract boolean isSome();
    public abstract boolean isNone();
    public abstract T get();
}
class Some<T> extends Option<T>
{
    public Some(T value) { this.value = value;}
    public boolean isSome() { return true; }
    public boolean isNone() { return false; }
    public T get() { return value; }
    public String toString() { return "Some(" + value.toString() + ")"; }
    public Iterator<T> iterator() {
        List<T> l = new ArrayList<T>();
        l.add(value);
        return l.iterator();
    }
    private T value;
}
class Null<T> extends Option<T>
{
    public boolean isSome() { return false; }
    public boolean isNone() { return true; }
    public T get() { throw new UnsupportedOperationException(); }
    public String toString() { return "None"; }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            public boolean hasNext() { return false; }
            public T next() { throw new UnsupportedOperationException(); }
            public void remove() { throw new UnsupportedOperationException(); }
        };
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Bill", "Malone", 30),
            new Person("Matt", "Ayers", 27),
            new Person("Aaron", "Chambers", 45),
            new Person("Wayne", "Caldwell", 39),
            new Person("Christina", "Yelinek", 23),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Terri", "Gottleaber", 29));

        List<Person> intermed = Utils.filter(people, new FilterFn<Person>() {
            public boolean f(Person p) { return p.getAge() > 21; }
        });
        Utils.act(intermed, new ActionFn<Person>() {
            public void f(Person p) { System.out.println("Have a beer, " +
                p.getFirstName() + "!"); }
            });

        List<Integer> allAges = 
            Utils.transform(people, new TransformerFn<Integer, Person>() {
                    public Integer f(Person p) { return p.getAge(); }
                });
        //Utils.act(allAges, Utils.print);
            
        List<String> firstNames =
            Utils.transform(people, new TransformerFn<String, Person>() {
                public String f(Person p) { return p.getFirstName(); }
                });
        //Utils.act(firstNames, Utils.print);

        Integer sumAges = 
            Utils.foldFront(0, allAges, new SumFn<Integer, Integer>() {
                public Integer f(Integer curr, Integer it){return curr + it;}
            });
        System.out.println(sumAges);
        
        String xml =
            Utils.foldFront("<people>", people, Person.TO_XML) + "</people>";
        System.out.println(xml);
        
        Option<Person> result = 
            Utils.find(people, new FilterFn<Person>() {
                public boolean f(Person p) { 
                    return p.getFirstName().equals("Fred");
                }
            });
        if (result.isNull())
        {
            
        }
        for (Person p : result)
            System.out.println(p);
    }
}



