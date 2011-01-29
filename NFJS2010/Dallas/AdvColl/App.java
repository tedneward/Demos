import java.util.*;
import java.util.concurrent.*;

interface ActionFn<T>
{
    public void apply(T p);
}
interface FilterFn<T>
{
    public boolean apply(T it);
}
interface TransformerFn<R, T>
{
    public R apply(T it);
}
interface FoldingFn<R, T>
{
    public R apply(R accum, T it);
}


class PUtils
{
    private static ExecutorService es = Executors.newFixedThreadPool(5);
    
    public static <T> void act(List<? extends T> src, final ActionFn<? super T> action)
    {
        for (final T it : src)
            es.submit(new Runnable() {
                public void run() {
                    action.apply(it);
                }
            });
    }
}

abstract class Option<T>
    implements Iterable<T>
{
    public abstract boolean isSome();
    public abstract boolean isNone();
    public abstract T get();
    public abstract Iterator<T> iterator();
}
class Some<T> extends Option<T>
{
    public Some(T value) { this.value = value; }
    public boolean isSome() { return true; }
    public boolean isNone() { return false; }
    public T get() { return value; }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            boolean hasNextFlag = true;
            public boolean hasNext() {
                return hasNextFlag;
            }
            public T next() {
                hasNextFlag = false;
                return value;
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    private T value;
}
class None<T> extends Option<T>
{
    public None() { }
    public boolean isSome() { return false; }
    public boolean isNone() { return true; }
    public T get() { throw new NullPointerException(); }
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
        };
    }
}


class Tuple2<T1,T2>
    implements Serializable
{
    public Tuple2(T1 v1, T2 v2)
    {
        this._1 = v1; this._2 = v2;
    }
    
    public T1 _1;
    public T2 _2;
}




class Utils
{
    public static <T> void act(List<? extends T> src, ActionFn<? super T> action)
    {
        for (T it : src)
            action.apply(it);
    }
    public static <T> List<T> filter(List<? extends T> src, FilterFn<? super T> filter)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (filter.apply(it))
                results.add(it);
        return results;
    }
    public static <T> Option<T> find(List<? extends T> src, FilterFn<? super T> filter)
    {
        for (T it : src)
            if (filter.apply(it))
                return new Some<T>(it);
        return new None<T>();
    }
    public static <R,T> List<R> transform(List<T> src, TransformerFn<R,T> xform)
    {
        List<R> results = new ArrayList<R>();
        for (T it : src)
            results.add(xform.apply(it));
        return results;
    }

    public static <R,T> R foldLeft(List<T> src, R seed, FoldingFn<R,T> fold)
    {
        R accum = seed;
        for (T it : src)
            accum = fold.apply(accum, it);
        return accum;
    }
}

public class App
{
    public static final ActionFn<Object> PRINT = new ActionFn<Object>() {
        public void apply(Object obj) {
            System.out.println(Thread.currentThread().toString() + " : " + obj);
        }
    };
    
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Ted", "Neward", 39),
            new Person("Al", "Akbar", 57),
            new Person("Theresa", "Regan", 29),
            new Person("Brian", "Hurley", 42),
            new Person("Tom", "Bozich", 55),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Sudheer", "Gollapudi", 27));
        //PUtils.act(people, PRINT);
        
        for (Person p : Utils.find(people, new FilterFn<Person>() {
            public boolean apply(Person it) {
                return it.getFirstName().equals("Ted") &&
                    it.getLastName().equals("Neward");
            }
        }))
        {
            System.out.println(p);
        }
        for (Person p : Utils.find(people, new FilterFn<Person>() {
            public boolean apply(Person it) {
                return it.getFirstName().equals("Fred") &&
                    it.getLastName().equals("Flintstone");
            }
        }))
        {
            System.out.println(p);
        }

        /*        
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
        List<String> lastNames = Utils.transform(people, new TransformerFn<String, Person>() {
            public String apply(Person it) { return it.getLastName(); }
        });
        System.out.println(lastNames);

        int totalAges = Utils.foldLeft(people, 0, new FoldingFn<Integer, Person>() {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge();
            }
        });
        System.out.println(totalAges);

        String xml = Utils.foldLeft(people, "<persons>", new FoldingFn<String, Person>() {
            public String apply(String accum, Person it) {
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        }) + "</persons>";
        System.out.println(xml);
        */

        /*        
        SortedSet<Person> people2 = new TreeSet<Person>(Person.BY_AGE);
        people2.addAll(people);
        //System.out.println(people2);
        
        SortedSet<Person> drinkers = people2.tailSet(new Person(null, null, 21));
        System.out.println(drinkers);
        for (Person it : drinkers)
            System.out.println("Have a beer, " + it.getFirstName() + "!");
        */

        /*
        for (Person it : people)
            if (it.getAge() > 21)
                System.out.println("Have a beer, " + it.getFirstName() + "!");
        */
    }
}




