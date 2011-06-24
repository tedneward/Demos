import java.util.*;
import com.google.common.base.*;
import static com.google.common.base.Preconditions.*;
import com.google.common.collect.*;
import static com.google.common.collect.Iterables.*;

class Person
{
    public Person(String fn, String ln, int a, Person... kids)
    {
        checkNotNull(fn);
        checkNotNull(ln);
        checkValue(a > 0 && a < 125, "must be within 0 and 125, eclusive: %s", a);
        
        this.firstName = fn; this.lastName = ln; this.age = a;
        for (Person p : kids)
          children.add(p);
    }
    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    
    public int hashCode() { 
      return Objects.hashCode(firstName, lastName, age); 
    }
    
    public String toString() 
    {
      return Objects.toStringHelper(this)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .addValue("age=" + age + " years")
                .add("children", children)
                .toString();
    }
    
    private String firstName;
    private String lastName;
    private int age;
    private List<Person> children = new ArrayList<Person>();
}

public class App
{
  public static final Splitter NAME_SPLITTER = 
    Splitter.on(';').trimResults().omitEmptyStrings();
    
  public static final Predicate<String> LONG_NAME =
    Predicates.and(
      Predicates.notNull(),
      new Predicate<String>() {
        public boolean apply(String in) { return in.length() > 3; }
      });
  public static void main(String[] args)
  {
    Person p = new Person("Karen", "Boss", 34, 
                  new Person("Joe", "Boss", 16),
                  new Person("Jane", "Boss", 0));
    System.out.println(p);
    
    String nameString = "Ted ; Charlotte; Michael  ; Matthew; ; Aaron; Scooter; Riley";
    for (String s : filter(NAME_SPLITTER.split(nameString), LONG_NAME))
        System.out.println(s);
        
    
  }
}


