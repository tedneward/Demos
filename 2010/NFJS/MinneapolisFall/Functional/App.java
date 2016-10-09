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
    public static <T> Tuple2<Boolean,T> find(List<T> src, FilterFn<T> condition)
    {
        for (T it : src)
            if (condition.apply(it))
                return new Tuple2(true, it);
        return new Tuple2(false, null );
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
        
        Tuple2<Boolean,Person> result = Utils.find(people, new FilterFn<Person>() {
            public boolean apply(Person it) { return it.getLastName().equals("Neward"); }
        });
        if (result._1)
            System.out.println(it._2.getFirstName());
            
        //Tuple2<String,Tuple2<String,Tuple2<String,Tuple2<String,String>>>> t5;
    }
}















