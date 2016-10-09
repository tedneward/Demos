import com.google.common.base.*;
import static com.google.common.base.Preconditions.*;

public class Person
{
    public Person(String fn, String ln, int a)
    {
        checkArgument(a > 0, "Age must be positive: %s", a);
        checkNotNull(fn);
        checkNotNull(ln);
        this.firstName = fn; this.lastName = ln; this.age = a;
    }
    
    public int hashCode()
    {
        return Objects.hashCode(firstName, lastName, age);
    }
    
    public String toString()
    {
        return Objects.toStringHelper(Person.class)
            .add("firstName", firstName)
            .add("lastName", lastName)
            .add("age", age)
            .toString();
    }
    
    private String firstName;
    private String lastName;
    private int age;
}