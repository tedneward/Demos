import java.util.*;

interface Action<T>
{
    public void apply(T it);
}
interface Filter<T>
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
    public static <T> void act(List<T> src, { T it => void } action)
    {
        for (T it : src)
            action.apply(it);
    }
    public static <T> List<T> filter(List<T> src, Filter<T> crit)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (crit.apply(it))
                results.add(it);
        return results;
    }
    public static <R,T> List<R> map(List<T> src, Transformer<R,T> xform)
    {
        List<R> results = new ArrayList<R>(src.size());
        for (T it : src)
            results.add(xform.apply(it));
        return results;
    }
    public static <R,T> R foldLeft(R seed, List<T> src, Fold<R,T> fold)
    {
        R total = seed;
        for (T it : src)
            total = fold.apply(total, it);    // totalAges = totalAges + p.getAge();
        return total;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Jason", "Belcher", 40),
            new Person("Tim", "Hirschi", 29),
            new Person("Ken", "Beesley", 56),
            new Person("Jaswind", "Sakpan", 31),
            new Person("Gary", "Licaso", 32),
            new Person("Troy", "Coats", 39),
            new Person("Ted", "Neward", 39),
            new Person("Matthew", "Neward", 10),
            new Person("Michael", "Neward", 17)
        ));
        //System.out.println(people);
        
        people.add(new Person("Abdel", "Remani", 25));
        //System.out.println(people);
        
        //Person j = people.get(3);
        //j.getChildren().add(new Person("Billy Bob", "Smith", 21));
        //System.out.println(j.getChildren());
        
        //for (Person it : people)
        //    if (it.getAge() >= 21)
        //        System.out.println("Have a beer, " + it.getFirstName() + "!");
        
        //SortedSet<Person> peopleByAge = new TreeSet<Person>(Person.BY_AGE);
        //peopleByAge.addAll(people);
        
        //SortedSet<Person> drinkers = peopleByAge.tailSet(new Person(null, null, 21));
        //System.out.println(drinkers);
        //for (Person p : drinkers)
        //    System.out.println("Have a beer, " + p.getFirstName() + "!");

        List<Person> drinkers = Utils.filter(people, new Filter<Person>() {
            public boolean apply(Person it) {
                return it.getAge() >= 21;
            }
        });
        System.out.println(drinkers);
        
        Utils.act(people, new Action<Person>() {
            public void apply(Person it) {
                System.out.println("Howdy, " + it.getFirstName());
            }
        });
        
        List<String> lastNames = Utils.map(people, new Transformer<String, Person>() {
            public String apply(Person it) {
                return it.getLastName();
            }
        });
        System.out.println(lastNames);
        
        int totalAges = 0;
        for (Person p : people)
            totalAges = totalAges + p.getAge();
        System.out.println(totalAges);

        totalAges = Utils.foldLeft(0, people, new Fold<Integer, Person>() {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge();
            }
        });
        System.out.println(totalAges);
        
        String xml = Utils.foldLeft("<people>", people, new Fold<String, Person>() {
            public String apply(String accum, Person it) {
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        }) + "</people>";
        System.out.println(xml);
    }
}






