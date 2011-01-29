import java.util.*;

interface FilterFn<T>
{
    public boolean apply(T it);
}
interface ActionFn<T>
{
    public void apply(T it);
}
interface TransformerFn<R,T>
{
    public R apply(T it);
}
interface FoldFn<R,T>
{
    public R apply(R accum, T it);
}

class Tuple2<T1,T2>
    implements java.io.Serializable
{
    public Tuple2(T1 t1, T2 t2) { _1 = t1; _2 = t2; }
    
    public String toString() { return "(" + _1 + ", " + _2 + ")"; }
    
    public T1 _1;
    public T2 _2;
}


class Utils
{
    public static <T> List<T> filter(List<T> src, FilterFn<T> filter)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (filter.apply(it))
                results.add(it);
        return results;
    }
    public static <T> void act(List<T> src, ActionFn<T> action)
    {
        for (T it : src)
            action.apply(it);
    }
    public static <R,T> List<R> map(List<T> src, TransformerFn<R,T> xform)
    {
        List<R> results = new ArrayList<R>();
        for (T it : src)
            results.add(xform.apply(it));
        return results;
    }
    public static <R,T> R foldLeft(R seed, List<T> src, FoldFn<R,T> fold)
    {
        R total = seed;
        for (T it : src)
            total = fold.apply(total, it);
        return total;
    }
    public static <T> Tuple2<Boolean,T> find(List<T> src, FilterFn<T> filter)
    {
        for (T it : src)
            if (filter.apply(it))
                return new Tuple2(true, it);
        return new Tuple2(false, null);
    }
}


public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Jeff", "Chambers", 53),
            new Person("Elango", "Narayanasamy", 50),
            new Person("Sudhakar", "Ganapathy", 36),
            new Person("Liping", "Xu", 29),
            new Person("Darrell", "Howard", 49),
            new Person("Esther", "Szeto", 36),
            new Person("Kishore", "Smith", 36),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        people.add(new Person("Li", "Hsu", 29));
        System.out.println(people);

        System.out.println("============");
        
        //Person e = people.get(5);
        //e.getChildren().add(new Person("Billy Bob", "Szeto", 29));
        
        //for (Person it : people)
        //    if (it.getAge() >= 21)
        //        System.out.println("Have a beer, " + it.getFirstName() + "!");

        /*        
        SortedSet<Person> potentialDrinkers = new TreeSet<Person>(Person.BY_AGE);
        potentialDrinkers.addAll(people);
        
        SortedSet<Person> drinkers = potentialDrinkers.tailSet(new Person("", "", 21));
        System.out.println(drinkers);
        for (Person it : drinkers)
            System.out.println("Have a beer, " + it.getFirstName() + "!");
        */
        
        List<Person> drinkers = Utils.filter(people, new FilterFn<Person>() {
            public boolean apply(Person it) {
                return it.getAge() >= 21;
            }
        });
        Utils.act(drinkers, new ActionFn<Person>() {
            public void apply(Person it) {
                System.out.println("Have a beer, " + it.getFirstName() + "!");
            }
        });
        
        System.out.println("===========");
        
        List<String> lastNames = Utils.map(people, new TransformerFn<String,Person>() {
            public String apply(Person it) {
                return it.getLastName();
            }
        });
        Utils.act(lastNames, new ActionFn<String>() {
            public void apply(String it) {
                System.out.println(it);
            }
        });
        
        System.out.println("===========");

        int totalAges = 0;
        for (Person it : people)
            totalAges = totalAges + it.getAge();
        System.out.println(totalAges);
        
        int totalAges2 = Utils.foldLeft(0, people, new FoldFn<Integer,Person>() {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge();
            }
        });
        System.out.println(totalAges2);
        
        String xml = "<people>";
        for (Person it : people)
            xml = xml + "<person>" + it.getFirstName() + "</person>";
        xml += "</people>";
        System.out.println(xml);
        
        String xml2 = Utils.foldLeft("<people>", people, new FoldFn<String,Person>() {
            public String apply(String accum, Person it) {
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        }) + "</people>";
        System.out.println(xml2);
        
        Tuple2<Boolean, Person> ted = Utils.find(people, new FilterFn<Person>() {
            public boolean apply(Person it) { return it.getFirstName().equals("Ted"); }
        });
        System.out.println(ted);

        Tuple2<Boolean, Person> funky = Utils.find(people, new FilterFn<Person>() {
            public boolean apply(Person it) { return it.getFirstName().equals("Funky"); }
        });
        System.out.println(funky);
        
        Tuple2<Boolean, Person> ted = 
            Utils.find(people, { Person it => it.getFirstName().equals("Ted"); });

        if (ted._1)
            System.out.println(ted._2);

    }
}





















