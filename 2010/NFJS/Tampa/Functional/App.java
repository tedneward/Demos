import java.util.*;

interface Predicate<T>
{
    public boolean f(T candidate);
}

interface Transformer<R, T>
{
    public R f(T candidate);
}

interface Action<T>
{
    public void f(T candidate);
}

interface Aggregator<R,T>
{
    public R f(R seed, T it);
}


class Utils
{
    public static <T> List<T> filter(List<T> data, Predicate<T> condition)
    {
        List<T> results = new ArrayList<T>(data.size());
        for (T p : data)
            if (condition.f(p))
                results.add(p);
        return results;
    }
    public static <T> void apply(List<T> data, Action<T> act)
    {
        for (T it : data)
            act.f(it);
    }
    public static <R, T> List<R> transform(List<T> data, Transformer<R,T> xf)
    {
        List<R> results = new ArrayList<R>(data.size());
        for (T p : data)
            results.add(xf.f(p));
        return results;
    }
    public static <R, T> R aggregate(List<T> data, R seed, Aggregator<R,T> a)
    {
        for (T it : data)
            seed = a.f(seed, it);
        return seed;
    }

    public static final Action<Object> PRINTER =
        new Action<Object>() {
            public void f(Object obj) {
                System.out.println(obj);
            }
        };
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Miki", "Yun", 25),
            new Person("Jason", "Warner", 24),
            new Person("Ron", "Gallant", 36),
            new Person("Mohammad", "Khan", 37),
            new Person("Sangeta", "Kundu", 29),
            new Person("Bavel", "Vassilev", 27),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Johan", "Andrade", 16));
        //System.out.println(people);

        List<Person> drinkers = Utils.filter(people, new Predicate<Person>() {
            public boolean f(Person candidate) {
                return candidate.getAge() >= 21;
            }
        });
        Utils.apply(drinkers, new Action<Person>() {
            public void f(Person p) {
                System.out.println("Have a beer, " + p.getFirstName() + "!");
            }
        });
        Utils.apply(drinkers, Utils.PRINTER);
        
        List<String> firstNames = Utils.transform(people, new Transformer<String, Person>() {
            public String f(Person p) { return p.getFirstName(); }
        });
        Utils.apply(firstNames, new Action<String>() {
            public void f(String s) { System.out.println(s); }
        });
        
        List<Integer> ages = Utils.transform(people, new Transformer<Integer, Person>() {
            public Integer f(Person p) { return p.getAge(); }
        });
        Utils.apply(ages, new Action<Integer>() {
            public void f(Integer s) { System.out.println(s); }
        });
        
        Integer sumAges = Utils.aggregate(ages, 0, new Aggregator<Integer, Integer>() {
            public Integer f(Integer seed, Integer it) { return seed + it; }
            });
        System.out.println(sumAges);

        String xml = Utils.aggregate(people, "<people>", 
            new Aggregator<String, Person>() {
                public String f(String xml, Person it) {
                    return xml + "<person>" + it.getFirstName() + "</person>";
                }
            }) + "</people>";
        System.out.println(xml);            
    }
}



