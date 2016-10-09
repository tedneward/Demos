import java.io.*;
import java.util.*;

interface Predicate<T>
{
    public boolean test(T it);
}
interface Action<T>
{
    public void apply(T it);
}
interface Transformer<T,R>
{
    public R apply(T it);
}
interface FoldOp<T,R>
{
    public R apply(R accum, T it);
}

class CollUtils
{
    //public static <T> List<T> filter({ T => boolean } crit, List<T> src)
    public static <T> List<T> filter(Predicate<T> crit, List<T> src)
    {
        List<T> results = new ArrayList<T>(src.size());
        for (T p : src)
            //if (crit(p) == true)
            if (crit.test(p) == true)
                results.add(p);
        return results;
    }
    public static <T> void execute(Action<T> act, List<T> src)
    {
        for (T it : src)
            act.apply(it);
    }
    public static <T,R> List<R> map(Transformer<T,R> xform, List<T> src)
    {
        List<R> results = new ArrayList<R>(src.size());
        for (T p : src)
            results.add(xform.apply(p));
        return results;
    }
    public static <T,R> R foldLeft(R seed, FoldOp<T,R> op, List<T> src)
    {
        R accum = seed;
        for (T it : src)
            accum = op.apply(accum, it);
        return accum;
    }
}

class SeqUtils
{
    public static <T> Iterable<T> take(final int n, final Iterable<T> src)
    {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return take(n, src.iterator());
            }
        };
    }
    public static <T> Iterator<T> take(final int n, final Iterator<T> src)
    {
        return new Iterator<T>() {
            int count = 0;
            public boolean hasNext() {
                return src.hasNext() && (count < n);
            }
            public T next() {
                count++;
                return src.next();
            }
            public void remove() { }
        };
    }
}

class FileUtils
{
    public static List<String> naiveReadFile(String filename)
        throws IOException
    {
        List<String> results = new ArrayList<String>();
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader("App.java");
            br = new BufferedReader(fr);
            
            String line = null;
            while ((line = br.readLine()) != null)
                results.add(line);
        }        
        finally
        {
            br.close();
            fr.close();
        }
        return results;
    }
    public static Iterable<Integer> diceRoll(final int min, final int max) {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    Random r = new Random();
                    public boolean hasNext() {
                        return true;
                    }
                    public Integer next() {
                        int temp = 0;
                        while ((temp = r.nextInt(max)) < min)
                            ;
                        return temp;
                    }
                    public void remove() { }
                };
            }
        };
    }
    public static Iterable<String> readFile(String filename)
        throws IOException
    {
        final FileReader fr = new FileReader(filename);
        final BufferedReader br = new BufferedReader(fr);
        
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    String line = getLine();
                    String getLine() {
                        String temp = null;
                        try { temp = br.readLine(); }
                        catch (IOException ex) { temp = null; }
                        return temp;
                    }
                    public boolean hasNext() {
                        return line != null;
                    }
                    public String next() {
                        String current = line;
                        line = getLine();
                        return current;
                    }
                    public void remove() { 
                        throw new UnsupportedOperationException(); 
                    }
                };
            }
        };
    }
}


public class App
{
    public static void main(String[] args)
        throws Exception
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Jesse", "Haraldsen", 31),
            new Person("Anu", "Vaidya", 33),
            new Person("David", "Greenlaw", 55),
            new Person("Thierry", "Lemeur", 42),
            new Person("Terry", "Hedin", 36),
            new Person("Ted", "Neward", 39), 
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        System.out.println(people);        

        /*
        List<Person> drinkers = CollUtils.filter(
            {Person p => p.getAge() >= 21; }, people);
         */

        List<Person> drinkers = CollUtils.filter(new Predicate<Person>() {
                public boolean test(Person p) { return p.getAge() >= 21; }
            }, people);
        CollUtils.execute(new Action<Person>() {
                public void apply(Person p) { 
                    System.out.println("Have a beer, " + p.getFirstName());
                }
            }, drinkers);
            
        List<Integer> ages = CollUtils.map(new Transformer<Person, Integer>()
            {
                public Integer apply(Person p) { return p.getAge(); }
            }, drinkers);
        System.out.println(ages);

        Integer sumAges = CollUtils.foldLeft(0, new FoldOp<Integer,Integer>(){
            public Integer apply(Integer accum, Integer it) {
                return accum + it;
            }
        }, ages);
        System.out.println("Total ages = " + sumAges);

        String xml =
            CollUtils.foldLeft("<people>\n", new FoldOp<Person, String>() {
                public String apply(String accum, Person it) {
                    return accum + 
                        "<person>" + it.getFirstName() + "</person>\n";
                }
            }, people) + "</people>";
        System.out.println(xml);        

        /*
        NavigableSet<Person> sba = new TreeSet<Person>(Person.BY_AGE);
        sba.addAll(people);
        //System.out.println(sba); 
        
        SortedSet<Person> drinkers = sba.tailSet(new Person(null, null, 21));
        System.out.println(drinkers);
        */

        for (String line : SeqUtils.take(10, FileUtils.readFile("App.java")))
            System.out.println(line);
            
        for (Integer i : SeqUtils.take(6, FileUtils.diceRoll(3, 18)))
            System.out.println(i);
    }
}
