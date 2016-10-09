import java.util.*;

class Tuple2<T1, T2>
    implements java.io.Serializable
{
    public Tuple2(T1 t1, T2 t2){ _1 = t1; _2 = t2; }
    
    public T1 _1;
    public T2 _2;
}



interface FilterFn<T>
{
    public boolean apply(T it);
}
interface ActionFn<T>
{
    public void apply(T it);
}
interface TransformerFn<T,U>
{
    public U apply(T it);
}
interface FoldFn<T,U>
{
    public U apply(U accum, T it);
}

class Utils
{
    public static <T> List<T> filter(List<T> src, FilterFn<T> filter)
    {
        List<T> results = new ArrayList<T>();
        for (T p : src)
            if (filter.apply(p))
                results.add(p);
        return results;
    }
    public static <T> void act(List<T> src, ActionFn<T> action)
    {
        for (T p : src)
            action.apply(p);
    }
    public static <T,U> List<U> map(List<T> src, TransformerFn<T,U> xform)
    {
        List<U> results = new ArrayList<U>();
        for (T p : src)
            results.add(xform.apply(p));
        return results;
    }
    public static <T,U> U foldLeft(U seed, List<T> src, FoldFn<T,U> fold)
    {
        U total = seed;
        for (T it : src)
            total = fold.apply(total, it);
        return total;
    }
}





public class App
{
    public static final ActionFn<Object> PRINTIT = 
        new ActionFn<Person>() {
            public void apply(Person it) {
                System.out.println(it.getFirstName() + " have a beer!");
            }
        };
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Domingo", "Polican", 46),
            new Person("Audrey", "Trout", 29),
            new Person("Marc", "van der Snoot", 39),
            new Person("Zettie", "Chin-Fong", 39),
            new Person("Avneet", "Kang", 27),
            new Person("Blake", "Vanier", 25),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Prashant", "Smith", 40));
        //System.out.println(people);

        List<Person> drinkers = Utils.filter(people, new FilterFn<Person>() {
            public boolean apply(Person it) {
                return it.getAge() >= 21;
            }
        });
        Utils.act(drinkers, PRINTIT);
        
        List<String> lastNames = Utils.map(people, new TransformerFn<Person, String>() {
            public String apply(Person it) {
                return it.getLastName();
            }
        });
        System.out.println(lastNames);
        
        int totalAges = 0;
        for (Person it : people)
            totalAges = totalAges + it.getAge();
        System.out.println(totalAges);

        Integer totAges = Utils.foldLeft(0, people, new FoldFn<Person, Integer>() {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge();
            }
        });
        System.out.println(totAges);
        
        String xml = Utils.foldLeft("<persons>", people, new FoldFn<Person, String>() {
            public String apply(String accum, Person it) {
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        }) + "</persons>";
        System.out.println(xml);

        /*        
        SortedSet<Person> sba = new TreeSet<Person>(Person.BY_AGE);
        sba.addAll(people);
        System.out.println(sba);
        
        SortedSet<Person> drinkers = sba.tailSet(new Person("", "", 21));
        for (Person p : drinkers)
            System.out.println(p.getFirstName() + " have a beer!");
        */
        
        /*
        for (Person p : people)
            if (p.getAge() >= 21)
                System.out.println(p.getFirstName() + " have a beer!");
        */
    }
}