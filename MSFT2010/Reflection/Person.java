public class Person
    implements Personable
{
    public Person(String fn, String ln)
    {
        this.first = fn; this.last = ln;
    }
        
    public String getFirstName() { return first; }
    public String getLastName() { return last; }
    
    private String first;
    private String last;
}