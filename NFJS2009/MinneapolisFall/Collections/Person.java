import java.util.*;

public class Person
	implements Comparable<Person>
{
	public Person(String fn, String ln, int a)
	{
		firstname = fn; lastname = ln; age = a;
	}
	
	public int compareTo(Person other)
	{
		return BY_LASTNAME.compare(this, other);
	}

	public static final Comparator<Person> BY_AGE = new Comparator<Person>() {
			public int compare(Person lhs, Person rhs) {
				return lhs.age - rhs.age;
			}
		};
	public static final Comparator<Person> BY_FIRSTNAME = new Comparator<Person>() {
			public int compare(Person lhs, Person rhs) {
				return lhs.firstname.compareTo(rhs.firstname);
			}
		};
	public static final Comparator<Person> BY_LASTNAME = new Comparator<Person>() {
			public int compare(Person lhs, Person rhs) {
				return lhs.lastname.compareTo(rhs.lastname);
			}
		};
	
	
	public String getFirstName() { return firstname; }
	public String getLastName() { return lastname; }
	public int getAge() { return age; }
	public List<Person> getChildren() { return Collections.synchronizedList(Collections.unmodifiableList(children)); }
	
	public String toString() { return "[Person: " + firstname + " " + lastname + " (" + age + ")]"; }
	
	private String firstname;
	private String lastname;
	private int age;
	private List<Person> children = new ArrayList<Person>();
}
















