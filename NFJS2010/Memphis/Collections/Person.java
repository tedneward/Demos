import java.util.*;

public class Person
//    implements Iterable<Person>
    implements Comparable<Person>  // natural order
{
	public Person(String fn, String ln, int a, Person... kids)
	{
		this.firstName = fn; this.lastName = ln; this.age = a;
		children.addAll(Arrays.asList(kids));
	}

    public void addChild(String name)
    {
        // Validation logic
        Person child = new Person(name, this.lastName, 0);
    }
	
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public int getAge() { return age; }
	public List<Person> getChildren() {
	    return Collections.unmodifiableList(children);
    }
	//public Iterator<Person> iterator() { return children.iterator(); }

    public static final Comparator<Person> BY_AGE = 
        new Comparator<Person>() {
            public int compare(Person lhs, Person rhs) {
                return lhs.age - rhs.age;
            }
        };
	
	public int compareTo(Person other)
	{
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