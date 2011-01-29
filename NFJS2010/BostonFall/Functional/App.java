import java.util.*;

interface FilterProc<T>
{
    public boolean apply(T it);
}
interface ApplicationProc<T>
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

class Utils
{
    public static <T> List<T> filter(List<T> src, FilterProc<T> filter)
    {
        List<T> results = new ArrayList<T>();
        for (T it : src)
            if (filter.apply(it))
                results.add(it);
        return results;
    }
    
    public static <T> void apply(List<T> src, ApplicationProc<T> operation)
    {
        for (T it : src)
            operation.apply(it);
    }
    
    public static <R,T> List<R> map(List<T> src, MapFn<R, T> mapper)
    {
        List<R> results = new ArrayList<R>(src.size());
        for (T it : src)
            results.add(mapper.apply(it));
        return results;
    }
    
    public static <R,T> R fold(R seed, List<T> src, FoldFn<R,T> folder)
    {
        R result = seed;
        for (T it : src)
            result = folder.apply(result, it);
        return result;
    }

    
}

public class App
{
    public static void main(String... args)
    {
        List<Person> people = Arrays.asList(
            new Person("Alla", "Bushoy", 39),
            new Person("Ken", "Wadland", 60),
            new Person("Jamie", "McCullough", 38),
            new Person("Li", "Tang", 47),
            new Person("Steve", "Valin", 45),
            new Person("Lilia", "Medina", 39),
            new Person("George", "Foto", 50),
            new Person("Iwona", "Borowicz", 29),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        );

        List<Person> drinkers = Utils.filter(people, new FilterProc<Person>() {
            public boolean apply(Person it) {
                return it.getAge() >= 21;
            }
        });
        System.out.println(drinkers);

        List<Person> newards = Utils.filter(people, new FilterProc<Person>() {
            public boolean apply(Person it) {
                return it.getLastName().equals("Neward");
            }
        });
        System.out.println(newards);
        
        Utils.apply(people, new ApplicationProc<Person>() {
            public void apply(Person it) {
                System.out.println("Doing something to " + it);
            }
        });

        List<Integer> ages = Utils.map(people, new MapFn<Integer,Person>() {
            public Integer apply(Person it) {
                return it.getAge();
            }
        });
        System.out.println(ages);
        
        int sum = 0;
        for (Person it : people)
            sum = sum + it.getAge();
        System.out.println(sum);

        Integer sumAges = Utils.fold(0, people, new FoldFn<Integer,Person>() {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge();
            }
        });
        System.out.println(sumAges);
        
        String xml = Utils.fold("<people>", people, new FoldFn<String,Person>() {
            public String apply(String accum, Person it) {
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        }) + "</people>";
        System.out.println(xml);

        /*        
        SortedSet<Person> possibleDrinkers = new TreeSet<Person>(Person.BY_AGE);
        possibleDrinkers.addAll(people);
        
        SortedSet<Person> drinkers = possibleDrinkers.tailSet(new Person("", "", 21));
        
        for (Person it : drinkers)
            System.out.println("Have a beer, " + it.getFirstName() + "!");
        */
    }
}




















