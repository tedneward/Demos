import java.util.*;

public class App
{
  public static void main(String[] args)
  {
    List<Person> people = new ArrayList<Person>(Arrays.asList(
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
    //System.out.println(people);

    people.add(new Person("Joe", "Sondow", 32));
    //System.out.println(people);

    //Person kamal = people.get(3);
    //kamal.getChildren().add(new Person("Billy Bob", "Kang", 0));


    SortedSet<Person> peopleByAge = new TreeSet<Person>(Person.BY_AGE);
    peopleByAge.addAll(people);

    Set<Person> drinkers = peopleByAge.tailSet(new Person("", "", 21));
    System.out.println(drinkers);

    for (Person p : drinkers)
      System.out.println("Have a beer, " + p.getFirstName() + "!");
  }
}


