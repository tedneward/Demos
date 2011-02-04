import java.util.*;

public class Person
  implements Comparable<Person>
{
  private String firstName;
  private String lastName;
  private int age;
  private List<Person> children = new ArrayList<Person>();

  public Person(String f, String l, int a) {
    firstName =f ; lastName = l; age = a;
  }
  public String toString() {
    return "[Person: " + firstName + " " + lastName +
      "(" + age + " years old)";
  }

  public int compareTo(Person other)
  {
    return BY_LASTNAME.compare(this, other);
  }
  public final static Comparator<Person> BY_AGE = new Comparator<Person>() {
    public int compare(Person lhs, Person rhs) {
      return lhs.age - rhs.age;
    }
  };
  public final static Comparator<Person> BY_FIRSTNAME = new Comparator<Person>() {
    public int compare(Person lhs, Person rhs) {
      return lhs.firstName.compareTo(rhs.firstName);
    }
  };
  public final static Comparator<Person> BY_LASTNAME = new Comparator<Person>() {
    public int compare(Person lhs, Person rhs) {
      return lhs.lastName.compareTo(rhs.lastName);
    }
  };

  public String getFirstName() { return firstName; }
  public String getLastName() { return lastName; }
  public int getAge() { return age; }
  public List<Person> getChildren() { 
    //return new ArrayList<Person>(children);
    return Collections.unmodifiableList(children);
  }
}
