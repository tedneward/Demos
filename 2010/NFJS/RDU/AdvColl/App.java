import java.util.*;

interface Action<T>
{
    public void apply(T it);
}
interface Predicate<T>
{
    public boolean apply(T it);
}
interface Transformer<R,T>
{
    public R apply(T it);
}
interface Fold<R,T>
{
    public R apply(R accum, T it);
}

class Tuple2<T1,T2>
    implements java.io.Serializable
{
    public Tuple2(T1 t1, T2 t2)
    {
        _1 = t1; _2 = t2;
    }
    
    public T1 _1;
    public T2 _2;
}
class Tuple3<T1,T2,T3>
    implements java.io.Serializable
{
    public Tuple3(T1 t1, T2 t2, T3 t3)
    {
        _1 = t1; _2 = t2; _3 = t3;
    }
    
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
    public static <T> void act(List<T> src, Action<T> actionFn)
    {
        for (T it : src)
            actionFn.apply(it);
    }
    public static <T> List<T> filter(List<T> src, Predicate<T> filterFn)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (filterFn.apply(it))
                results.add(it);
        return results;
    }
    public static <T> Option<T> find(List<T> src, Predicate<T> filterFn)
    {
        for (T it : src)
            if (filterFn.apply(it))
                return new Some(it);
        return new None();
    }
    public static <R,T> List<R> transform(List<T> src, Transformer<R,T> xformerFn)
    {
        List<R> results = new ArrayList<R>();
        for (T it : src)
            results.add(xformerFn.apply(it));
        return results;
    }
    public static <R,T> R fold(final List<T> src, final R seed, final Fold<R,T> foldFn)
    {
        R accum = seed;
        for (T it : src)
            accum = foldFn.apply(accum, it);
        return accum;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = Arrays.asList(
            new Person("Neal", "Laney", 21),
            new Person("Marina", "Harrell", 25),
            new Person("David", "Cate", 36),
            new Person("Gaj", "Kabra", 35),
            new Person("Lynn", "Kurtz", 24),
            new Person("Nama", "Eljabaly", 23),
            new Person("John", "Smith", 39),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 10)
        );
//        System.out.println(people);

        for (Person found : Utils.find(people, new Predicate<Person>() {
                                public boolean apply(Person it) {
                                    return it.getLastName().equals("Neward");
                                }
                            }))
            System.out.println(found);
    }
}






