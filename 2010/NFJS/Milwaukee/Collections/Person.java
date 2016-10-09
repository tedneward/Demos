import java.util.*;

public class Person
    implements Comparable<Person>
{
    public Person(String fn, String ln, int a)
    {
        this.firstname = fn;
        this.lastname = ln;
        this.age = a;
    }
    
    public String getFirstName() { return firstname; }
    public String getLastName() { return lastname; }
    public int getAge() { return age; }
    public List<Person> getChildren() 
        { return Collections.unmodifiableList(children); }
    
    public static final Comparator<Person> BY_FIRSTNAME = 
        new Comparator<Person>() {
            public int compare(Person lhs, Person rhs) {
                return lhs.firstname.compareTo(rhs.firstname);
            }
        };
    public static final Comparator<Person> BY_LASTNAME = 
        new Comparator<Person>() {
            public int compare(Person lhs, Person rhs) {
                return lhs.lastname.compareTo(rhs.lastname);
            }
        };
    public static final Comparator<Person> BY_AGE = 
        new Comparator<Person>() {
            public int compare(Person lhs, Person rhs) {
                return lhs.age - rhs.age;
            }
        };
    public int compareTo(Person other)
    {
        return BY_LASTNAME.compare(this, other);
    }
    
    public String toString() {
        return "[Person: "+ 
            "firstname=" + firstname + " " +
            "lastname=" + lastname + " " +
            "age=" + age + 
            "]";
    }
    
    private final String firstname;
    private final String lastname;
    private final int age;
    private final List<Person> children = new ArrayList<Person>();
}