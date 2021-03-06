import java.util.*;

public class Person
    //implements Iterable<Person>
    implements Comparable<Person>
{
	public Person(String fn, String ln, int a)
	{
		this.firstName = fn; this.lastName = ln; this.age = a;
	}
	
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public int getAge() { return age; }
	public List<Person> getChildren() { return Collections.unmodifiableList(children); }
	//public Iterator<Person> getChildren() { return children.iterator(); }
	//public Iterator<Person> iterator() { return children.iterator(); }
	public void haveChild(String name) {
	    children.add(new Person(name, lastName, 0));
	}
	
	public int compareTo(Person other)
	{
	    return lastName.compareTo(other.lastName);
	}
    public static final Comparator<Person> BY_LASTNAME = 
        new Comparator<Person>() {
            public int compare(Person lhs, Person rhs) {
                return lhs.lastName.compareTo(rhs.lastName);
            }
        };
    public static final Comparator<Person> BY_AGE = 
        new Comparator<Person>() {
            public int compare(Person lhs, Person rhs) {
                return lhs.age - rhs.age;
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
	private List<Person> children = new ArrayList<Person>();
}