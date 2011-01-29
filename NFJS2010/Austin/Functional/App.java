import java.util.*;

interface ActionFn<T>
{
    public void apply(T it);
}
interface FilterFn<T>
{
    public boolean apply(T it);
}
interface TransformerFn<R, T>
{
    public R apply(T it);
}
interface FoldFn<R, T>
{
    public R apply(R accum, T it);
}

class Utils
{
    public static <T> void act(List<T> src, #{ T => void } action)
    {
        for (T it : src)
            action(it);
    }
    /*
    public static <T> void act(List<T> src, ActionFn<T> action)
    {
        for (T it : src)
            action.apply(it);
    }
    */
    public static <T> List<T> filter(final List<T> src, final FilterFn<T> criteria)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (criteria.apply(it))
                results.add(it);
        return results;
    }
    public static <R,T> List<R> transform(final List<T> src, final TransformerFn<R,T> transformer)
    {
        List<R> results = new ArrayList<R>();
        for (T it : src)
            results.add(transformer.apply(it));
        return results;
    }
    public static <R,T> R foldLeft(final R seed, final List<T> src, final FoldFn<R,T> folder)
    {
        R accum = seed;
        for (T it : src)
            accum = folder.apply(accum, it);
        return accum;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("John", "Carney", 38),
            new Person("Laleh", "Samani", 29),
            new Person("David", "Hoover", 23),
            new Person("Mathi", "Vairavan", 34),
            new Person("Param", "Kollengode", 37),
            new Person("Jose", "Mendez", 38),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 10)
        ));
        
        people.add(new Person("Ann", "Ford", 39));
        //System.out.println(people);
        
        //Person la = people.get(1);
        //people.get(1).getChildren().add(new Person("Billy Bob", "Samani", 25));

        /*
        SortedSet<Person> peopleSet = new TreeSet<Person>(Person.BY_AGE);
        peopleSet.addAll(people);
        SortedSet<Person> drinkers = peopleSet.tailSet(new Person("", "", 21));
        System.out.println(drinkers);
        
        for (Person p : drinkers)
            System.out.println("Have a beer " + p.getFirstName() + "!");
        */
        
        List<Person> drinkers = Utils.filter(people, new FilterFn<Person>() {
            public boolean apply(Person it) {
                return it.getAge() >= 21;
            }
        });

        Utils.act(drinkers, new ActionFn<Person>() { 
            public void apply(Person it) {
                System.out.println("Have a beer " + it.getFirstName() + "!");
            }
        });
        
        List<String> lnames = Utils.transform(people, new TransformerFn<String, Person>() {
            public String apply(Person it) {
                return it.getLastName();
            }
        });
        System.out.println(lnames);
        
        int totalAges = 0;
        for (Person it : people)
            totalAges = totalAges + it.getAge();
        System.out.println(totalAges);
        
        Integer totalAges2 = Utils.foldLeft(0, people, new FoldFn<Integer, Person> () {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge();
            }
        });
        System.out.println(totalAges2);
        
        String xml = Utils.foldLeft("<people>", people, new FoldFn<String, Person> () {
            public String apply(String accum, Person it) {
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        }) + "</people>";
        System.out.println(xml);



            
    }
}



