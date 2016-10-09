import java.util.*;

public class Person
    implements Comparable<Person>
{
	public Person(String fn, String ln, int a, Person... kids)
	{
		this.firstName = fn; this.lastName = ln; this.age = a;
		for (Person k : kids)
		    children.add(k);
	}
	
	public int compareTo(Person other)
	{
	    return lastName.compareTo(other.getLastName());
	}

    public final static Comparator<Person> BY_AGE = new Comparator<Person>() {
        public int compare(Person lhs, Person rhs) {
            return lhs.age - rhs.age;
        }
    };
    public final static Comparator<Person> BY_LAST = new Comparator<Person>() {
        public int compare(Person lhs, Person rhs) {
            return lhs.lastName.compareTo(rhs.lastName);
        }
    };
    public final static Comparator<Person> BY_FIRST= new Comparator<Person>() {
        public int compare(Person lhs, Person rhs) {
            return lhs.firstName.compareTo(rhs.firstName);
        }
    };
	
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public int getAge() { return age; }

	public List<Person> getChildren() 
	{ return Collections.unmodifiableList(children); }
	
	public void addChild(String firstName)
	{
	    Person baby = new Person(firstName, this.lastName, 0);
	    children.add(baby);
	}
	
	
	public String toString() 
	{
		return "[Person: firstName=" + firstName + 
			" lastName=" + lastName + 
			" age = " + age +"]";
	}

    private final List<Person> children = new ArrayList<Person>();
	private final String firstName;
	private final String lastName;
	private final int age;
}