import java.util.*;

public class Person implements Comparable<Person>
{
	public Person(String fn, String ln, int a)
	{
		this.firstName = fn; this.lastName = ln; this.age = a;
	}
	
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public int getAge() { return age; }

    public static final Comparator<Person> BY_AGE = 
        new Comparator<Person>() {
            public int compare(Person lhs, Person rhs) {
                return lhs.age - rhs.age;
            }
        };
    public static final Comparator<Person> BY_LASTNAME = 
        new Comparator<Person>() {
            public int compare(Person lhs, Person rhs) {
                return lhs.lastName.compareTo(rhs.lastName);
            }
        };
    public static final Comparator<Person> BY_FIRSTNAME = 
        new Comparator<Person>() {
            public int compare(Person lhs, Person rhs) {
                return lhs.firstName.compareTo(rhs.firstName);
            }
        };
	
	public int compareTo(Person other)
	{
	    return BY_LASTNAME.compare(this, other);
	}
	
	public String toString() 
	{
		return "[Person: firstName=" + firstName + 
			" lastName=" + lastName + 
			" age = " + age +"]";
	}
	
	private final String firstName;
	private final String lastName;
	private final int age;
}
