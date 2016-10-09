import java.util.*;

public class Person
    implements Comparable<Person>
{
    public Person(String fn, String ln, int a)
    {
        this.firstName = fn; this.lastName = ln; this.age = a;
    }
    public Person(Tuple3<String,String,Integer> t3) {
        this.firstName = t3._1; this.lastName = t3._2; this.age = t3._3;
    }
    
    public Tuple3<String,String,Integer> toT() {
        return new Tuple3(firstName, lastName, age);
    }
    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    
    public int compareTo(Person other)
    {
        return BY_LASTNAME.compare(this, other);
    }
    public static final Comparator<Person> BY_AGE = new Comparator<Person>() {
            public int compare(Person l, Person r) {
                return l.age - r.age;
            }
        };
    public static final Comparator<Person> BY_FIRSTNAME = new Comparator<Person>() {
            public int compare(Person l, Person r) {
                return l.firstName.compareTo(r.firstName);
            }
        };
    public static final Comparator<Person> BY_LASTNAME = new Comparator<Person>() {
            public int compare(Person l, Person r) {
                return l.lastName.compareTo(r.lastName);
            }
        };
    
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
