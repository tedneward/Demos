import java.util.*;

interface Predicate<T>
{
    public boolean apply(T p);
}
interface Action<T>
{
    public void apply(T p);
}
interface BinaryOp<R, T>
{
    public R apply(R accum, T p);
}
interface Transformer<T, U>
{
    public U apply(T it);
}

class PUtils
{
    public static <T> List<T> filter(List<T> people, Predicate<T> criteria)
    {
        List<T> results = new ArrayList<T>();
        // break collection up into equal-sized chunks
        // ThreadPoolExecutor.execute(new Runnable() {
        //      for (T it : chunk)
        //            
        //    })
        return results;
    }
}

class Utils
{
    public static <T> List<T> filter(List<T> people, Predicate<T> criteria)
    {
        List<T> results = new ArrayList<T>();
        for (T it : people)
            if (criteria.apply(it) == true)
                results.add(it);
        return results;
    }
    public static <T> void iter(List<T> coll, Action<T> action)
    {
        for (T it : coll)
            action.apply(it);
    }
    public static <T, U> List<U> map(List<T> coll, Transformer<T,U> action)
    {
        List<U> results = new ArrayList<U>();
        for (T it : coll)
            results.add(action.apply(it));
        return results
    }
    public static <T, R> R foldLeft(R seed, List<T> coll, BinaryOp<R,T> op)
    {
        R accum = seed;
        for (T it : coll)
            accum = op.apply(accum, it);
        return accum;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Matt", "M", 26),
            new Person("Rich", "W", 52),
            new Person("Will", "Gilbert", 57),
            new Person("Joanna", "Lu", 18),
            new Person("Martha", "B", 29),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        //System.out.println(people.toString());
        
        people.add(new Person("Philip", "Z", 32));
        System.out.println(people.toString());
        
        Person j = people.get(3);
        //j.getChildren().add(new Person("Billy Bob", "Lu", 0));

        /*
        TreeSet<Person> sortedPeople = new TreeSet(Person.BY_AGE);
        sortedPeople.addAll(people);
        System.out.println(sortedPeople);
        SortedSet<Person> drinkers = sortedPeople.tailSet(new Person(null, null, 21));
        for (Person it : drinkers)
            System.out.println("Have a beer, " + it.getFirstName() + "!");
        */
        
        // SELECT * WHERE p.age >= 21
        List<Person> drinkers = Utils.filter(people, new Predicate<Person>() {
            public boolean apply(Person p) {
                return p.getAge() >= 21;
            }
        });
        Utils.iter(drinkers, new Action<Person>() {
            public void apply(Person p) {
                System.out.println("Have a beer, " + p.getFirstName() + "!");
            }
        });
        
        //people.filter( { Person p => p.getAge() > = 21; } )
        //    .iter( { Person p => System.out.println(...); });
        
        
        List<Integer> ages = 
            Utils.map(people, new Transformer<Person, Integer>()
            {
                public Integer apply(Person p) { return p.getAge(); }
            });

        int sumAges = 
            Utils.foldLeft(0, people, new BinaryOp<Integer, Person>() {
            public Integer apply(Integer accum, Person p) {
                return accum + p.getAge();
            }
        });
        System.out.println("Sum of ages = " + sumAges);
        
        String xml = Utils.foldLeft("<people>", people,
            new BinaryOp<String, Person>() {
            public String apply(String accum, Person p) {
                return accum + "<person>" + p.getLastName() + "</person>";
            }
            }) + "</people>";
        System.out.println(xml);
    }
}































