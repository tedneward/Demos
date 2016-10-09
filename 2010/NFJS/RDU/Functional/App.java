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
            new Person("Ted", "Neward", 39,
                new Person("Michael", "Neward", 17),
                new Person("Matthew", "Neward", 10) )
        );
//        System.out.println(people);

        Utils.act(people, 
            new Action<Person>() {
                public void apply(Person p) { 
                    System.out.println("Have a beer, " + p.getFirstName()); 
                }
            });

        List<Person> oldFogies = Utils.filter(people, new Predicate<Person>() {
            public boolean apply(Person p) {
                return (p.getAge() >= 30);
            }
        });
        System.out.println(oldFogies);
        
        List<String> lastNames = Utils.transform(people, new Transformer<String,Person>() {
            public String apply(Person p) {
                return p.getLastName();
            }
        });
        System.out.println(lastNames);
        
        int accum = 0;
        for (Person it : people)
            accum = accum + it.getAge();
        System.out.println("Total ages = " + accum);
        
        System.out.println("Total ages = " +
            Utils.fold(people, 0, new Fold<Integer,Person>() {
                public Integer apply(Integer accum, Person p) {
                    return p.getAge() + accum;
                }
            }));

        System.out.println("xml = " + 
            Utils.fold(people, "<people>", new Fold<String,Person>() {
                public String apply(String accum, Person p) {
                    return accum + "<person>" + p.getFirstName() + "</person>";
                }
            }) + "</people>");
    }
}






