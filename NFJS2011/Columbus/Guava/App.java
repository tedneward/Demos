import java.util.*;
import com.google.common.base.*;
import com.google.common.collect.*;
import static com.google.common.base.Objects.*;
import static com.google.common.base.Preconditions.*;

class Person
{
    public static final int MAX_AGE = 125;
    public Person(String fn, String ln, int a)
    {
      checkNotNull(fn);
      checkNotNull(ln);
      checkArgument(a > -1 && a < MAX_AGE, "must be within 0 and %s: %s", MAX_AGE, a);
      
      this.firstName = fn; this.lastName = ln; this.age = a;
    }
    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    
    public int hashCode() { return Objects.hashCode(firstName, lastName, age); }
    
    public String toString() 
    {
      return Objects.toStringHelper(this)
        .add("firstName", firstName)
        .add("lastName", lastName)
        .add("age", age)
        .toString();
    }
    
    private String firstName;
    private String lastName;
    private int age;
}

interface Op<T>
{
  public void apply(T it);
}

class Ops
{
  public static <T> void apply(Iterable<T> iterable, Op<T> action)
  {
    for (T it : iterable)
      action.apply(it);
  }
}

public class App
{
  public static final Splitter NAME_SPLITTER = 
    Splitter.on(';').trimResults().omitEmptyStrings();
  public static void main(String[] args)
  {
    Person p = new Person("Scott", "Coplin", 33);
    int hash = p.hashCode();
    System.out.println(p + " " + hash);
    
    String names = "Ted, Charlotte; ; Jessica;Scott; Brian; Jay";
         
    //names = Joiner.on("; ").useForNull("<<NULL>>").join(nameI);
    //System.out.println(names);
    
    String name = "fooBar";
    System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name));
  }
}

