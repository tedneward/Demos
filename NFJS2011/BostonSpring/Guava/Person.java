import com.google.common.base.*;
import static com.google.common.base.Preconditions.*;

public class Person
{
    public Person(String fn, String ln, int a)
    {
        checkArgument(a >= 0, "Age cannot be negative");
        checkArgument(fn != null, "Firstname cannot be null");
    
        this.firstName = fn; this.lastName = ln; this.age = a;
    }
    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    
    public String toString() 
    {
        return Objects.toStringHelper(Person.class)
            .add("first", firstName)
            .add("last", lastName)
            .add("age", age)
            .toString();
    }
    
    public int hashCode()
    {
        return Objects.hashCode(firstName, lastName, age);
    }
    
    private String firstName;
    private String lastName;
    private int age;
}