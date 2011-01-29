import java.util.*;

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

class ParUtils
{
    public static <R,T> R foldLeft(R seed, List<T> src, FoldFn<R,T> fold)
    {
        // imagine parallelization happens here
        R result = seed;
        for (T it : src)
            result = fold.apply(result, it);
        return result;
    }
}


class Tuple2<T1,T2>
    implements java.io.Serializable
{
    public Tuple2(T1 t1, T2 t2)
    {
        _1 = t1; _2 = t2;
    }
    
    public Iterator<Object> iterator()
    {
        return new Iterator<Object>() {
            int count = 0;
            public boolean hasNext() {
                return count < 2;
            }
            public Object next() {
                switch (count){
                    case 0: return _1;
                    case 1: return _2;
                    default:
                        throw new ArrayIndexOutOfBoundsException();
                }
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    
    public final T1 _1;
    public final T2 _2;
}
class Tuple3<T1,T2,T3>
{
    public Tuple3(T1 t1, T2 t2,T3 t3)
    {
        _1 = t1; _2 = t2; _3 = t3;
    }
    
    public final T1 _1;
    public final T2 _2;
    public final T3 _3;
}

abstract class Option<T>
    implements Iterable<T>
{
    public abstract T get();    
    
    public abstract boolean isSome();
    public abstract boolean isNone();
    
    public abstract Iterable<T> iterator();
}
final class Some<T> extends Option<T>
{
    public Some(T val) { this.value = val; }
    
    public boolean isSome() { return true; }
    public boolean isNone() { return false; }
    public T get() { return value; }
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
    
    private T value;
}
final class None<T> extends Option<T>
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


class Utils
{
    public static <T> List<T> convert(T[] src)
    {
        List<T> result = new ArrayList<T>(src.length);
        for (T p : src) result.add(p);
        return result;
    }
    public static <T> List<T> filter(List<T> src, FilterFn<T> condition)
    {
        List<T> results = new ArrayList<T>(src.size());
        for (T it : src)
            if (condition.apply(it))
                results.add(it);
        return results;
    }
    public static <T> Option<T> find(List<T> src, FilterFn<T> condition)
    {
        for (T it : src)
            if (condition.apply(it))
                return new Some(it);
        return new None();
    }
    public static <T> void apply(List<T> src, ActionFn<T> action)
    {
        for (T it : src)
            action.apply(it);
    }
    public static <R,T> List<R> map(List<T> src, MapFn<R,T> mapper)
    {
        List<R> results = new ArrayList<R>(src.size());
        for (T it : src)
            results.add(mapper.apply(it));
        return results;
    }
    public static <R,T> R foldLeft(R seed, List<T> src, FoldFn<R,T> fold)
    {
        R result = seed;
        for (T it : src)
            result = fold.apply(result, it);
        return result;
    }
    /*
    public static <T1,T2> List<Tuple2<T1,T2>> zip(List<T1> src, List<T2> src2)
    {
        
    }
    public static <T1,T2,T3> List<Tuple2<T1,T2,T3>> 
        zip3(List<T1> src, List<T2> src2, List<T3> src3)
    {
        
    }
    public static <T1,T2> Tuple2<List<T1>,List<T2>> unzip(List<Tuple2<T1,T2>> src)
    {
        
    }
    public static <T1,T2,T3> List<Tuple2<T1,T2,T3>> 
        zip3(List<T1> src, List<T2> src2, List<T3> src3)
    {
        
    }
    */
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Mike", "Kangas", 37),
            new Person("Luda", "Fisher", 29),
            new Person("Joseph", "Witthuhn", 22),
            new Person("Senid", "Cimic", 27),
            new Person("Ron", "Nasby", 47),
            new Person("Bill", "Reay", 33),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        ));
        people.add(new Person("Darryn", "Kozak", 29));
        //System.out.println(people);
        
        Person luda = people.get(1);
        //luda.getChildren().add(new Person("Billy Bob", "Thornton", 68));

        List<Person> drinkers = Utils.filter(people, new FilterFn<Person>() {
            public boolean apply(Person it) { return it.getAge() >= 21; }
        });
        Utils.apply(drinkers, new ActionFn<Person>() {
            public void apply(Person it) { 
                System.out.println("Have a beer, " + it.getFirstName() + "!");
            }
        });
        
        List<String> names = Utils.map(people, new MapFn<String,Person>() {
            public String apply(Person it) { return it.getLastName(); }
        });
        System.out.println(names);
        
        int sum = 0;
        for (Person it : people)
            sum = sum + it.getAge();
        System.out.println("sum = " + sum);
        
        int sum2 = Utils.foldLeft(0, people, new FoldFn<Integer,Person>() {
            public Integer apply(Integer accum, Person it) { return accum + it.getAge(); }
        });
        System.out.println("sum = " + sum2);
        
        String xmlo = "<persons>";
        for (Person it : people)
            xmlo = xmlo + "<person>" + it.getFirstName() + "</person>";
        xmlo = xmlo + "</persons>";
        System.out.println(xmlo);
        
        String xml = ParUtils.foldLeft("<persons>", people, new FoldFn<String,Person>() {
            public String apply(String accum, Person it) { 
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        }) + "</persons>";
        System.out.println(xml);
        
        for (Person p : Utils.find(people, new FilterFn<Person>() {
            public boolean apply(Person it) { return it.getLastName().equals("Flintstone"); }
        }))
            System.out.println(p.getFirstName());
            
        //Tuple2<String,Tuple2<String,Tuple2<String,Tuple2<String,String>>>> t5;
    }
}















