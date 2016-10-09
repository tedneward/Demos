import java.util.*;

interface Predicate<T>
{
  public boolean apply(T t);
}
interface Operation<T>
{
  public void apply(T t);
}
interface Function<R, T>
{
  public R apply(T t);
}

class Tuple2<T1, T2> implements java.io.Serializable
{
  public T1 fst;
  public T2 snd;
  public Tuple2(T1 t1, T2 t2) { fst = t1; snd = t2; }
}
class Tuple3<T1, T2, T3> implements java.io.Serializable
{
  public T1 fst;
  public T2 snd;
  public T3 thd;
  public Tuple3(T1 t1, T2 t2, T3 t3) { fst = t1; snd = t2; thd = t3; }
}

abstract class Option<T> extends Iterable<T>
{
  public abstract T get();
  public abstract Iterator<T> iterator();

  public static <T> boolean isSome(Option<T> opt) { return opt instanceof Some; }
  public static <T> boolean isNone(Option<T> opt) { return opt instanceof None; }
}
class Some<T> extends Option<T>
{
  public Some(T value) { val = value; }

  public Iterator<T> iterator() {
    return new Iterator<T>() {
    };
  }

  public T get() { return val; }

  private T val;
}
class None<T> extends Option<T>
{
  public None() { }

  public T get() { throw new NullPointerException(); }
}


class FunUtil
{
  public static <T> List<T> filter(List<T> coll, Predicate<T> pred)
  {
    List<T> results = new ArrayList<T>();
    for (T t : coll)
    {
      if (pred.apply(t))
        results.add(t);
    }
    return results;
  }
  public static <T> void map(List<T> coll, Operation<T> oper)
  {
    for (T t : coll)
      oper.apply(t);
  }
  public static <R, T> List<R> transform(List<T> coll, Function<R,T> oper)
  {
    return null;
  }

  public static <T> List<T> take(Iterable<T> seq, int num)
  {
    Iterator<T> iter = seq.iterator();
    List<T> results = new ArrayList<T>();
    for (int i=0; i<num; i++)
    {
      if (iter.hasNext())
        results.add(iter.next());
      else
        break;
    }
    return results;
  }
}

public class App
{
  public static Iterable<Integer> randomNumbers(final int max)
  {
    return new Iterable<Integer>() {
      public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
          Random r = new Random();
          public boolean hasNext() { return true; }
          public Integer next() { return r.nextInt(max) + 1; }
          public void remove() { throw new UnsupportedOperationException(); }
        };
      }
    };
  }

  static List<Person> people = new ArrayList<Person>(Arrays.asList(
      new Person("Geoff", "The", 40),
      new Person("Harvey", "Flaisher", 35),
      new Person("Clint", "Shank", 38),
      new Person("Kamal", "Kang", 29),
      new Person("David", "Johnson", 32),
      new Person("Mike", "Fadul", 40),
      new Person("Michael", "Neward", 16),
      new Person("Matthew", "Neward", 10),
      new Person("Ted", "Neward", 38)
    ));
  public static void main(String[] args)
    throws Exception
  {
    System.out.println(people);

    List<Person> drinkers = FunUtil.filter(people, new Predicate<Person>() {
      public boolean apply(Person p) { return p.getAge() > 20; }
    });
    System.out.println(drinkers);

    FunUtil.map(drinkers, new Operation<Person>() {
      public void apply(Person p) { System.out.println("Have a beer, " + p.getFirstName() + "!"); }
    });

    Option<Person> mike = find("Mike");
    if (Option.isSome(mike))
      System.out.println("Found " + mike.get());

    List<Integer> stats = FunUtil.take(randomNumbers(20), 6);
    System.out.println(stats);

    for (String s : FunUtil.take(FileUtils.readFile("App.java"), 10))
      System.out.println(s);
  }
  public static Option<Person> find(String fname) {
    for (Person p : people)
      if (p.getFirstName().equals(fname))
        return new Some<Person>(p);

    return new None<Person>();
  }
}


