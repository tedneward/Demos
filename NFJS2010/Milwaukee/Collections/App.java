import java.util.*;

interface Action<T>
{
    public void act(T t);
}
interface Predicate<T>
{
    public boolean act(T t);
}
interface Transform<T,U>
{
    public U act(T t);
}
interface Reduce<T,U>
{
    public U act(U accum, T t);
}

class CollUtils
{
    public static <T> List<T> filter(Predicate<T> pred, List<T> items)
    {
        ArrayList<T> results = new ArrayList<T>(items.size());
        for (T it : items)
            if (pred.act(it))
                results.add(it);
        return results;
    }
    public static <T> void foreach(Action<T> operation, List<T> items)
    {
        for (T it : items)
        {
            operation.act(it);
        }
    }
    public static <T,U> List<U> map(Transform<T,U> oper, List<T> items)
    {
        ArrayList<U> results = new ArrayList<U>(items.size());
        for (T it : items)
            results.add(oper.act(it));
        return results;
    }
    public static <T,U> U reduce(U seed, 
                        Reduce<T,U> oper, List<T> items)
    {
        U accum = seed;
        for (T it : items)
            accum = oper.act(accum, it);
        return accum;
    }
}

public class App
{
    public static void main(String[] args)
    {
        List<Person> people = new ArrayList<Person>(
          Arrays.asList(
            new Person("Fran", "Dooley", 48),
            new Person("Matt", "Johnson", 34),
            new Person("Kobus", "Roussouw", 47),
            new Person("Nada", "Smith", 34),
            new Person("Belinda", "Hanson", 29),
            new Person("Kris", "Koskelin", 34),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
                  ));
        //System.out.println(people);
        
        people.add(new Person("Devi", "Smith", 29));
        System.out.println(people);

        List<Integer> peopleAges =
            CollUtils.map(new Transform<Person, Integer>() {
                public Integer act(Person p) {
                    return p.getAge();
                }
            }, people);
        Integer sumAge =
            CollUtils.reduce(0, new Reduce<Person, Integer>() {
                public Integer act(Integer accum, Person p) {
                    return accum + p.getAge();
                }
            }, people);
        System.out.println("Total ages is " + sumAge);
        
        String xmlString =
            CollUtils.reduce("<people>", new Reduce<Person, String>() {
                public String act(String accum, Person p) {
                    return accum + "<person>" +
                        "<firstName>" + p.getFirstName() + "</firstName>" +
                        "</person>";
                }
            }, people) + "</people>";
        System.out.println(xmlString);
        
        String xmlString =
            CollUtils.reduce("<people", 
                {accum, p => accum + "<person>" + p "</person>" },
                people);

        List<Person> drinkers = 
            CollUtils.filter(new Predicate<Person>() {
                public boolean act(Person p) {
                    return p.getAge() >= 21;
                }
            }, people);
        CollUtils.foreach(new Action<Person>() {
            public void act(Person p) {
                System.out.println("Have a beer, " + 
                    p.getFirstName() + "!");
            }
        }, drinkers);
        
        /*
        Person p = people.get(9);
        System.out.println(p);
        
        p.getChildren().add(new Person("Ricky Bobby", "Smith", 0));
        
        System.out.println("==============");
        
        TreeSet<Person> peopleByAge = new TreeSet<Person>(Person.BY_AGE);
        peopleByAge.addAll(people);
        SortedSet<Person> drinkers = 
            peopleByAge.tailSet(new Person(null, null, 21));
        System.out.println(drinkers);
        */
    }
}
