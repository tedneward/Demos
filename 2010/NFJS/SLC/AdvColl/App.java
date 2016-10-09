import java.util.*;

class Tuple2<T1,T2>
    implements java.io.Serializable
{
    public Tuple2(T1 t1, T2 t2)
    {
        _1 = t1;
        _2 = t2;
    }
    
    public T1 _1;
    public T2 _2;
}

abstract class Option<T>
    implements Iterable<T>
{
    public abstract boolean isSome();
    public abstract boolean isNone();
    
    public abstract Iterator<T> iterator();
    
    public abstract T get();
}
class Some<T> extends Option<T>
{
    public Some(T val) { value = val;}

    public boolean isSome() { return true; }
    public boolean isNone() { return false; }
    
    public T get() { return value; }

    public Iterator<T> iterator()
    {
        return new Iterator<T>() {
            boolean seen = false;
            public boolean hasNext() {
                return !seen;
            }
            public T next() {
                seen = true;
                return value;
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    T value;
}
class None<T> extends Option<T>
{
    public None() { }
    
    public boolean isSome() { return false;}
    public boolean isNone() { return true; }
    
    public T get() { throw new NullPointerException(); }

    public Iterator<T> iterator()
    {
        return new Iterator<T>() {
            public boolean hasNext() {
                return false;
            }
            public T next() {
                throw new NullPointerException();
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}




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
    public static <T> void act(List<T> src, Action<T> action)
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
/*
    public static <T> Tuple2<Boolean, T> find(List<T> src, Filter<T> crit)
    {
        for (T it : src)
            if (crit.apply(it))
                return new Tuple2(true, it);
        return new Tuple2(false, null);
    }
*/
    public static <T> Option<T> find(List<T> src, Filter<T> crit)
    {
        for (T it : src)
            if (crit.apply(it))
                return new Some(it);
        return new None();
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
        //System.out.println(drinkers);
        
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
        //System.out.println(lastNames);
        
        int totalAges = 0;
        for (Person p : people)
            totalAges = totalAges + p.getAge();
        //System.out.println(totalAges);

        totalAges = Utils.foldLeft(0, people, new Fold<Integer, Person>() {
            public Integer apply(Integer accum, Person it) {
                return accum + it.getAge();
            }
        });
        //System.out.println(totalAges);
        
        String xml = Utils.foldLeft("<people>", people, new Fold<String, Person>() {
            public String apply(String accum, Person it) {
                return accum + "<person>" + it.getFirstName() + "</person>";
            }
        }) + "</people>";
        //System.out.println(xml);
        
        for (Person t : Utils.find(people, new Filter<Person>() {
            public boolean apply(Person it) {
                return it.getFirstName().equals("Ted");
            }
        }))
        {
            System.out.println(t);
        }
    
        for (Person f : Utils.find(people, new Filter<Person>() {
            public boolean apply(Person it) {
                return it.getFirstName().equals("Fred");
            }
        }))
        {
            System.out.println(f);
        }
    }
}






