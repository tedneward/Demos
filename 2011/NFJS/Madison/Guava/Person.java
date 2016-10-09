import com.google.common.base.*;

public class Person
{
    public Person(String fn, String ln, int a)
    {
        Preconditions.checkNotNull(fn, "You must have at least a first name");
        Preconditions.checkArgument(a > 0, "You must be at least zero years old");

        this.firstName = fn; this.lastName = ln; this.age = a;
    }
    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    
    private static final Predicate<Object> EQUALS_PRECONDITIONS = 
        Predicates.and(
            Predicates.notNull(),
            Predicates.instaceOf(Person),
            Predicates.equalTo(new Person(...))
        );

    public boolean equals(Object other)
    {
        if (EQUALS_PRECONDITIONS.apply(other))
        {
        }

        return false;
    }
    
    public int hashCode()
    {
        return Objects.hashCode(firstName, lastName, age);
    }
    
    public String toString() 
    {
        return "[Person: firstName=" + firstName + 
            " lastName=" + lastName + 
            " age = " + age +"]";
    }
    
    private String firstName;
    private String lastName;
    private int age;
}
