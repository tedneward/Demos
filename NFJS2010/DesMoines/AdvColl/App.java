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

class Tuple2<T1,T2>
    implements java.io.Serializable, Iterable<Object>
{
    public Tuple2(T1 t1, T2 t2) { _1 = t1; _2 = t2; }
    
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            int count = 0;
            
            public boolean hasNext() {
                return count < 2;
            }
            public Object next() {
                switch (count) {
                    case 0: count++; return _1;
                    case 1: count++; return _2;
                    default:
                        throw new UnsupportedOperationException();
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
    implements java.io.Serializable
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
    
    public abstract T get();
}
class Some<T> extends Option<T>
{
    public Some(T val) { this._value = val; }
    
    public boolean isSome() { return true; }
    public boolean isNone() { return false; }
    
    public T get() { return _value; }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            boolean seen = false;
            public boolean hasNext() {
                return !seen;
            }
            public T next() {
                seen = true;
                return _value;
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    private T _value;
}
class None<T> extends Option<T>
{
    public None() { }
    
    public boolean isSome() { return false; }
    public boolean isNone() { return true; }
    
    public T get() { return null; }

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


class Utils
{
    public static <T> void act(List<T> src, ActionFn<T> action)
    {
        for (T it : src)
            action.apply(it);
    }
    public static <T> List<T> filter(List<T> src, FilterFn<T> criteria)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (criteria.apply(it))
                results.add(it);
        return results;
    }
    public static <T> Option<T> find(List<T> src, FilterFn<T> criteria)
    {
        for (T it : src)
            if (criteria.apply(it))
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
    public static <R,T> R fold(R seed, List<T> src, FoldFn<R,T> fold)
    {
        R accum = seed;
        for (T it : src)
            accum = fold.apply(accum, it);
        return accum;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Blair", "Dewey", 45),
            new Person("Janet", "Dejager", 34),
            new Person("Doug", "Graham", 44),
            new Person("David", "Kessler", 35),
            new Person("Kery", "Stanley", 35),
            new Person("Richard", "Harms", 39),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Matt", "Druhl", 39));
        //System.out.println(people);
        
        List<Person> drinkers = Utils.filter(people, new FilterFn<Person>() {
            public boolean apply(Person it) {
                return it.getAge() >= 21;
            }
        });
        System.out.println(drinkers);
        
        Utils.act(people, new ActionFn<Person>() {
            public void apply(Person it) {
                System.out.println(it);
            }
        });
        
        List<String> lastNames = Utils.transform(people, new TransformerFn<String, Person>() {
            public String apply(Person it) {
                return it.getLastName();
            }
        });
        System.out.println(lastNames);
        
        int accum = 0;
        for (Person p : people)
            accum = accum + p.getAge();
        System.out.println("total age = " + accum);
        
        Integer totalAges = Utils.fold(0, people, new FoldFn<Integer, Person>() {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge();
            }
        });
        System.out.println(totalAges);
        
        String xml = Utils.fold("<people>", people, new FoldFn<String,Person>() {
            public String apply(String accum, Person it) {
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        }) + "</people>";
        System.out.println(xml);
        
        Option<Person> r = Utils.find(people, new FilterFn<Person>() {
            public boolean apply(Person it) {
                return it.getFirstName().equals("Fred");
            }
        });
        for (Person p : r)
            System.out.println("Found " + p.getFirstName());
    }
}













