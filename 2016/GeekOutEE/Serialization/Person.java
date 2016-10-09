import java.io.*;

public class Person implements java.io.Serializable
{
  public String firstName;
  public String lastName;
  public int age;
  
  public Object writeReplace()
    throws ObjectStreamException {
      System.out.println("writeReplace executed!");
      
      PersonProxy pp = new PersonProxy();
      pp.string = this.toString();
      return pp;
  }
  
  public Person(String f, String l, int a) {
    firstName = f; lastName = l; age = a;
  }
  
  public String toString() {
    return "Person: " + firstName + " " + lastName + " " + age;
  }
}