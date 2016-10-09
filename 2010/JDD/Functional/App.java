import java.util.*;

interface FilterFn<T>
{
    public boolean apply(T it);
}
interface ApplyFn<T>
{
    public void apply(T it);
}
interface TransformFn<R,T>
{
    public R apply(T it);
}
interface FoldFn<R,T>
{
    public R apply(R value, T it);
}

class Utils
{
    public static <T> List<T> filter(List<T> src, FilterFn<T> criteria)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (criteria.apply(it))
                results.add(it);
        return results;
    }
    public static <T> void apply(List<T> src, ApplyFn<T> fn)
    {
        for (T it : src)
            fn.apply(it);
    }
    public static <R,T> List<R> map(List<T> src, TransformFn<R,T> transformer)
    {
        List<R> results = new ArrayList<R>();
        for (T it : src)
            results.add(transformer.apply(it));
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
    public static void main(final String... args)
    {
        List<Person> people = Arrays.asList(
            new Person("Ted", "Neward", 39),
            new Person("Angelika", "Langer", 39),
            new Person("Bill", "Burke", 39),
            new Person("Anna", "K", 29),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        );
        
        List<Person> drinkers = Utils.filter(people, new FilterFn<Person>() {
            public boolean apply(Person it) { return it.getAge()>=21; }
        });
        Utils.apply(drinkers, new ApplyFn<Person>() {
            public void apply(Person it) { 
                System.out.println("Have a beer, " + it + "!");
            }
        });
        
        List<String> names = Utils.map(people, new TransformFn<String,Person>() {
            public String apply(Person it) { return it.getLastName(); }
        });
        System.out.println(names);
        
        int sum = 0;
        for (Person p : people)
            sum = sum + p.getAge();
        System.out.println("Total ages = " + sum);
        
        int sum2 = ParallelwUtils.fold(0, people, new FoldFn<Integer,Person>() {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge();
            }
        });
        System.out.println("Total ages = " + sum2);

        String xml = Utils.fold("<people>", people, new FoldFn<String,Person>() {
            public String apply(String accum, Person it) {
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        }) + "</people>";
        String xml = Utils.fold("<people>", people, (accum, it) ->
                return accum + "<person>" + it.getFirstName() + "</person>";
            ) + "</people>";
        System.out.println(xml);
    }
}

