public class Person
	implements java.io.Serializable
{
	static final long serialVersionUID = 8210480587330488935L;
	
	public Person(String fn, String ln, int a)
	{
		firstName = fn; lastName = ln; age = a;
	}	
	
	public Person()
	{
		System.out.println("Person ctor fired");
	}
	
	public String toString()
	{
		return "Person: " + firstName + ", " + lastName + ", " + age + ", " + birthday;
	}
	
	
	public String firstName;
	public String lastName;
	public int age;
	public String birthday;
}