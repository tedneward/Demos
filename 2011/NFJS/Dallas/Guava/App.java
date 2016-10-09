import java.util.*;
import com.google.common.base.*;
import com.google.common.collect.*;
import static com.google.common.base.Objects.*;

class Person
{
    public Person(String fn, String ln, int a)
    {
        this.firstName = fn; this.lastName = ln; this.age = a;
    }
    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    
    public String toString() 
    {
      return Objects.toStringHelper(Person.class)
        .add("firstName", firstName)
        .add("lastName", lastName)
        .add("age", age)
        .toString();
    }
    
    public int hashCode()
    {
      return Objects.hashCode(lastName);
    }
    
    private String firstName;
    private String lastName;
    private int age;
}

class StringPredicates
{
  public static Predicate<String> greaterThan(final int x) {
    return new Predicate<String>() {
        public boolean apply(String it) {
          return it.length() > x;
        }
      };
  }
}

public class App
{
  public static final Joiner SEMI_JOINER = Joiner.on(";");
  public static final Joiner NAME_JOINER = SEMI_JOINER.useForNull("<<NULL>>");
  
  public static final Predicate<String> THREE_CHARS_OR_LESS = 
    Predicates.and(
      Predicates.notNull(),
      StringPredicates.greaterThan(3)
    );
  
  public static void main(String[] args)
  {
    Person p1 = new Person("Ted", "Neward", 40);
    Person p2 = null;
    if (equal(p1, p2))
      System.out.println("They are the same");
      
    System.out.println(p1);
    
    String names = "Ted,Jessica, Charlotte , , Michael,     Matthew, Tim, Pratik, Ken";
    List<String> personNames = 
      Constraints.constrainedList(
        Lists.newArrayList(Splitter.on(',').omitEmptyStrings().trimResults().split(names)),
        Constraints.notNull()
        );
    System.out.println(personNames);

    personNames.add(null);

    for (String s : Iterables.filter(personNames,     
                Predicates.and(Predicates.notNull(), StringPredicates.greaterThan(3))
          ))
        System.out.println(s);
    
    String names2 = NAME_JOINER.join(personNames);
    System.out.println(names2);
    
    String value = "this_is_an_example_of_C";
    System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, value));
  }
}











