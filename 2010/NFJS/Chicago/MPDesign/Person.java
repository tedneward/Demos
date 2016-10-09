import java.util.*;

public class Person
    implements Comparable<Person>
{
    public Person(String fn, String ln, int a)
    {
        this.firstName = fn; this.lastName = ln; this.age = a;
    }
    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public List<Person> getChildren() { return new ArrayList<Person>(children); }
    public void forEachChild(ActionFn<Person> behavior) {
        for (Person it : children) {
            behavior.apply(it);
        }
    }
    
    public Person haveChild(String name) {
        // if (sex == MALE) throw new BiologicalException("");
        // if (Republican) throw new CannotHaveBabyException("");
        // if (salary < 50000) throw new TooPoorException("");
        
        Person baby = new Person(name, this.lastName, 0);
        children.add(baby);
        return baby;
    }
    
    public int compareTo(Person other) {
        return this.lastName.compareTo(other.lastName);
    }
    
    public String toString() 
    {
        return "[Person: firstName=" + firstName + 
            " lastName=" + lastName + 
            " age = " + age +"]";
    }
    
    private List<Person> children = new ArrayList<Person>();
    private String firstName;
    private String lastName;
    private int age;
}