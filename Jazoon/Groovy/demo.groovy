public class Person
{
    public Person(String fn, String ln, int a)
    {
        firstName = fn;
        lastName = ln;
        age = a;
    }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String value) { firstName = value; }
    public String getLastName() { return lastName; }
    public void setLastName(String value) { lastName = value; }
    public int getAge() { return age; }
    public void setAge(int value) { age = value; }
}