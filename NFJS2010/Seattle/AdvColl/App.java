import java.util.*;
//import fj.*;

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


class Tuple2<T1,T2>
    implements Iterable<Object>
{
    public Tuple2(T1 t1, T2 t2) { _1 = t1; _2 = t2; }
    
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            int count = 0;
            public boolean hasNext() {
                return (count < 2);
            }
            public Object next() {
                switch (count++)
                {
                    case 0: return _1;
                    case 1: return _2;
                    default:
                        throw new NoSuchElementException();
                }
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    public T1 _1;
    public T2 _2;
}
class Tuple3<T1,T2,T3>
{
    public Tuple3(T1 t1, T2 t2, T3 t3) { _1 = t1; _2 = t2; _3 = t3; }
    
    public T1 _1;
    public T2 _2;
    public T3 _3;
}


abstract class Option<T>
    implements Iterable<T>
{
    public abstract boolean isSome();
    public abstract boolean isNone();
    
    public abstract Iterator<T> iterator();
    
    public abstract T get();
    public abstract T getOrElse(T def);
}
final class Some<T> extends Option<T>
{
    public Some(T value) { this.val = value; }
    
    public boolean isSome() { return true; }
    public boolean isNone() { return false; }
    
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            public boolean seen = false;
            public boolean hasNext() {
                return (!seen);
            }
            public T next() {
                seen = true;
                return value;
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }

    public T get() { return val; }
    public T getOrElse(T def) { return val; }
    
    private T val;
}
final class None<T> extends Option<T>
{
    public None() { }
    
    public boolean isSome() { return false; }
    public boolean isNone() { return true;}

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            public boolean hasNext() {
                return false;
            }
            public T next() {
                throw new UnsupportedOperationException();
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }

    public T get() { throw new NullPointerException(); }
    public T getOrElse(T def) { return def; }
}


class Utils
{
    /*
    public static <T> void act(List<T> src, F<T, Void> f)
    {
        for (T it : src)
            f.f(it);
    }
    */
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
    public static <T> Tuple2<Boolean,T> find(List<T> src, FilterFn<T> criteria)
    {
        for (T it : src)
            if (criteria.apply(it))
                return new Tuple2(true, it);
        return new Tuple2(false, null);
    }
    public static <T> Tuple2<Boolean,T> find(List<T> src, T def, FilterFn<T> criteria)
    {
        for (T it : src)
            if (criteria.apply(it))
                return new Tuple2(true, it);
        return new Tuple2(false, def);
    }
    public static <T> Option<T> ofind(List<T> src, FilterFn<T> criteria)
    {
        for (T it : src)
            if (criteria.apply(it))
                return new Some(it);
        return new None();
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
        //List<String> names = Utils.transform(people, (Person it) -> it.getFirstName());


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
        
        Tuple2<Boolean,Person> t = Utils.find(people, new FilterFn<Person>() {
            public boolean apply(Person it) {
                return it.getLastName().equals("Reynolds");
            }
        });
        System.out.println(t._2);
        
        for (Person r : Utils.ofind(people, new FilterFn<Person>() {
            public boolean apply(Person it) {
                return it.getLastName().equals("Reynolds");
            }
        }))
            System.out.println(r.get());
    }
}










