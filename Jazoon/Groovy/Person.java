public class Person
{
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age)
    {
        this.firstName = firstName; this.lastName = lastName; this.age = age;
    }
    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public void setFirstName(String value) { firstName = value; }
    public void setLastName(String value) { lastName = value; }
    public void setAge(int value) { age = value; }
    
    public String toString()
    {
        return "[Person: " + firstName + " " + lastName + " (" + age + " years old)]";
    }
}