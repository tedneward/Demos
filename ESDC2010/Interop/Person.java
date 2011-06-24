public class Person 
  implements java.io.Serializable
{
  public Person(String fname, String lname, int age)
  {
    this.firstName = fname; this.lastName = lname; this.age = age;
  }

  public String toString() { return firstName + " " + lastName + " " + age; }

  private String firstName;
  private String lastName;
  private int age;
}