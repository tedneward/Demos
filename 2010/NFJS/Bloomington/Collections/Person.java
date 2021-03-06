import java.util.*;

public class Person
    implements Comparable<Person>
{
	public Person(String fn, String ln, int a, Person... kids)
	{
		this.firstName = fn; this.lastName = ln; this.age = a;
		for (Person k : kids) children.add(k);
	}
	
	public int compareTo(Person p)
	{
	    return BY_LASTNAME.compare(this, p);
	}
	
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
	
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public int getAge() { return age; }
	public List<Person> getChildren() { 
	    return Collections.unmodifiableList(children); 
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
	private List<Person> children = new ArrayList<Person>();
}