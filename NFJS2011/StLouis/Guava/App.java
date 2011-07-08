import java.util.*;

import com.google.common.base.*;
import com.google.common.collect.*;
import static com.google.common.base.Preconditions.*;

class Person
{
  public Person(String fn, String ln, int a)
  {
    checkArgument(fn != null, "First name cannot be null");
    checkArgument(ln != null, "Last name cannot be null");
    checkArgument(a >= 0, "Age must be positive");
  
    this.firstName = fn; this.lastName = ln; this.age = a;
  }

  public String getFirstName() { return firstName; }
  public String getLastName() { return lastName; }
  public int getAge() { return age; }

  public boolean equals(Object obj) {
    if (obj instanceof Person) {
      Person other = (Person)obj;
      return this.firstName.equals(other.firstName) &&
        this.lastName.equals(other.lastName);
    }
    return false;
  }
  public int hashCode() { return Objects.hashCode(firstName, lastName, age); }

  public String toString() {
    return Objects.toStringHelper(this)
      .add("firstName", firstName)
      .add("lastName", lastName)
      .add("age", age)
      .addValue("yay")
      .toString();
  }
  
  private String firstName;
  private String lastName;
  private int age;
}

public class App
{
  public static final Splitter nameSplitter = 
    Splitter.on(",").omitEmptyStrings();
    
  public static final Predicate<String> longName =
                        new Predicate<String>() {
                          public boolean apply(String it) {
                            return it.length() > 3;
                          }
                        };
  public static final Predicate<String> eEnder =
                        new Predicate<String>() {
                          public boolean apply(String it) {
                            return it.endsWith("e");
                          }
                        };
  public static void main(String[] args)
  {
    Person p1 = new Person("Ted", "Neward", 40);
    Person p2 = new Person("Ted", "Neward", 40);
    System.out.println(Objects.equal(p1, p2));
    System.out.println(p1);
    
    String names = "Ted, Fred, Ken, Venkat, Matthew, Jay, , Charlotte, Jessica";
    //for (String s : Iterables.filter(nameSplitter.trimResults().split(names), longNames))
    //  System.out.println(s);

    for (String s : Iterables.filter(nameSplitter.trimResults().split(names), 
          Predicates.and(longName, eEnder)))
      System.out.println(s);

    System.out.println("=======================");
    
    List<String> nameList = Lists.newArrayList(nameSplitter.trimResults().split(names));
    nameList.add(null);
    System.out.println(Joiner.on("|").useForNull("<<NULL>>").join(nameList));    
    
    nameList = Constraints.constrainedList(nameList, Constraints.notNull());
    nameList.add(null);
    
    //Person p3 = new Person(null, null, -12);
    
    System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "this_is_a_test"));
  }
}








