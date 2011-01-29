import java.util.*;

interface Predicate<T> { public boolean test(T it); }
interface Action<T> { public void act(T it); }
interface Transformer<R,T> { public R transform(T it); }
interface Reducer<R,T> { public R reduce(R accum, T it); }

abstract class Option<T>
    implements Iterable<T>
{
    public abstract boolean isSome();
    public abstract boolean isNone();
    
    public abstract Iterator<T> iterator();
    
    public abstract T get();
}
class Some<T> extends Option<T>
{
    public Some(T value) { this.value = value; }
    
    public boolean isSome() { return true; }
    public boolean isNone() { return false; }
    
    public T get() { return value; }

    public Iterator<T> iterator()
    {
        return Collections.singletonList(value).iterator();
    }
    
    private T value;
}
class None<T> extends Option<T>
{
    public None() { }

    public boolean isSome() { return false; }
    public boolean isNone() { return true; }
    
    public T get() { throw new IllegalArgumentException(); }

    public Iterator<T> iterator()
    {
        return new Iterator<T>() {
            public boolean hasNext() {
                return false;
            }
            public T next() {
                return get();
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}


class Utils
{
    public static <T> List<T> find(List<T> src, Predicate<T> pred)
    {
        List<T> results = new ArrayList<T>(src.size());
        for (T it : src)
            if (pred.test(it))
                results.add(it);
        return results;
    }
    public static <T> Option<T> findOne(List<T> src, Predicate<T> pred)
    {
        for (T it : src)
            if (pred.test(it))
                return new Some(it);
        
        return new None();
    }
    public static <T> void apply(List<T> src, Action<? super T> action)
    {
        for (T it : src)
            action.act(it);
    }
    public static <R, T> List<R> transform(List<T> src, Transformer<R,T> x)
    {
        List<R> results = new ArrayList<R>(src.size());
        for (T it : src)
            results.add(x.transform(it));
        return results;
    }
    public static <R, T> R reduce(R seed, List<T> src, Reducer<R,T> reducer)
    {
        R accum = seed;
        for (T it : src)
        {
            accum = reducer.reduce(accum, it);
        }
        return accum;
    }
}

class PersonUtils
{
    public static final Predicate<Person> DRINKERS =
        new Predicate<Person>() {
            public boolean test(Person it) { return it.getAge() >= 21; }
        };
    public static final Action<Object> PRINTIT =
        new Action<Object>() {
            public void act(Object it) { System.out.println(it); }
        };
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Tracey", "Jacoby", 41),
            new Person("Steven", "Ford", 47),
            new Person("Hima", "Puppala", 27),
            new Person("Karen", "Silverstein", 39),
            new Person("Mike", "Beauchamp", 40),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Kishore", "Smith", 36));
        
        for (Person p : Utils.findOne(people, 
            new Predicate<Person>() {
                public boolean test(Person it) { 
                    return it.getFirstName().equals("Tracey");
                }
            }))
        {
            System.out.println("Found " + p.getLastName());
        }

        List<Person> drinkers = Utils.find(people, PersonUtils.DRINKERS);
        System.out.println(drinkers);
        
        List<String> names = 
            Utils.transform(people, new Transformer<String, Person>() {
                public String transform(Person it) { 
                    return it.getFirstName();
                }
            });
        
        Utils.apply(drinkers, PersonUtils.PRINTIT);
        
        Utils.apply(drinkers, new Action<Person>() {
            public void act(Person it) { 
                System.out.println("Beer to "+ it.getFirstName());
            }
        });
        
        int accum = 0;
        for (Person p : people)
        {
            accum = accum + p.getAge();
        }
        System.out.println("Total age is " + accum);
        
        Integer totalAges = Utils.reduce(0, people, 
            new Reducer<Integer, Person>() {
                public Integer reduce(Integer accum, Person it) { 
                    return accum + it.getAge(); 
                }
            });
        System.out.println("Total age is " + totalAges);
        
        String xml = "<persons>";
        for (Person p : people)
        {
            xml = xml + "<person>" + p.getFirstName() + "</person>";
        }
        xml = xml + "</persons>";
        System.out.println(xml);
        
        String xml2 = Utils.reduce("<persons>", people,
            new Reducer<String, Person>() {
                public String reduce(String accum, Person it) {
                    return accum + 
                        "<person>" + it.getFirstName() + "</person>";
                }
            }) + "</persons>";
        System.out.println(xml2);
    }
}




