import java.util.*;

interface ActionFn<T>
{
    public void apply(T it);
}
interface FilterFn<T>
{
    public boolean apply(T it);
}
interface TransformerFn<R,T>
{
    public R apply(T it);
}
interface FoldFn<R,T>
{
    public R apply(R accum, T it);
}

class Utils
{
    public static <T> List<T> filter(List<T> src, FilterFn<T> crit)
    {
        List<T> result = new ArrayList<T>();
        for (T it : src)
            if (crit.apply(it))
                result.add(it);
        return result;
    }
    public static <T> Option<T> find(List<T> src, FilterFn<T> crit)
    {
        for (T it : src)
            if (crit.apply(it))
                return new Some<T>(it);
        return new None<T>();
    }
        public static <T> void apply(List<T> src, ActionFn<T> act)
    {
        for (T it : src)
            act.apply(it);
    }
    public static <R,T> List<R> map(List<T> src, TransformerFn<R,T> xform)
    {
        List<R> result = new ArrayList<R>();
        for (T it : src)
            result.add(xform.apply(it));
        return result;
    }
    public static <R,T> R fold(R seed, List<T> src, FoldFn<R,T> fold)
    {
        R result = seed;
        for (T it : src)
            result = fold.apply(result, it);
        return result;
    }
}


class Tuple2<T1,T2>
    implements java.io.Serializable
{
    public Tuple2(T1 v1, T2 v2) { _1 = v1; _2 = v2; }
    
    public T1 _1;
    public T2 _2;
}


abstract class Option<T>
    implements Iterable<T>
{
    public abstract Iterator<T> iterator();
    
    public abstract boolean isSome();
    public abstract boolean isNone();
    
    public abstract T get();
}
final class Some<T> extends Option<T>
{
    public Some(T v) { value = v; }
    
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            boolean seen = false;
            public boolean hasNext() {
                return !seen;
            }
            public T next() {
                seen = true;
                return value;
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public boolean isSome() { return true; }
    public boolean isNone() { return false; }
    
    public T get() { return value; }
    
    private T value;
}
final class None<T> extends Option<T>
{
    public None() { }
    
    public boolean isSome() { return false; }
    public boolean isNone() { return true; }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            public boolean hasNext() {
                return false;
            }
            public T next() {
                throw new NullPointerException();
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    public T get() { throw new NullPointerException(); }
}


public class App
{
    public static void main(String[] args)
    {
        // (string*(double*int))
        Tuple2<String,Tuple2<Double,Integer>> t = 
            new Tuple2<String,Tuple2<Double,Integer>>("Ted", 
                new Tuple2<Double,Integer>(1.0,10));
                
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Carleen", "Dickerson", 29),
            new Person("Patrice", "Young", 29),
            new Person("Burt", "Rutherford", 38),
            new Person("Michael", "Stakem", 28),
            new Person("Ikjae", "Park", 24),
            new Person("Chris", "Bell", 25),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        ));
        people.add(new Person("Bubba", "B", 78));

        List<Person> drinkers = Utils.filter(people, new FilterFn<Person>() {
            public boolean apply(Person it) { return it.getAge() >= 21; }
        });
        Utils.apply(people, new ActionFn<Person>() {
            public void apply(Person it) { System.out.println(" > " + it); }
        });
        
        List<String> lastNames = Utils.map(people, new TransformerFn<String,Person>() {
            public String apply(Person it) { return it.getLastName(); }
        });
        System.out.println(lastNames);
        
        int result = 0;
        for (Person it : people)
            result = result + it.getAge();
        System.out.println("total age = " + result);
        
        int totalAge = Utils.fold(0, people, new FoldFn<Integer,Person>() {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge();
            }
        });
        System.out.println("total age = " + totalAge);
        
        for (Person res : Utils.find(people, new FilterFn<Person>() {
            public boolean apply(Person it) {
                return it.getFirstName().equals("Fred");
            }
        }))
        {
            System.out.println(res.getLastName());
        }
        
//        String xml = Utils.fold("<people>", people, (accum, it) ->
//                accum + "<person>" + it.getFirstName() + "</person>" ) + "<people>";
//        System.out.println(xml);
    }
}











