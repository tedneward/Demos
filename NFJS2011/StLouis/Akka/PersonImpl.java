import akka.actor.*;
//import akka.dispatch.*;
//import com.eaio.uuid.UUID;
//import scala.*;

public class PersonImpl 
    extends TypedActor 
    implements Person
{
    public String getFirstName() { return first; }
    public String getLastName() { return last; }
    public int getAge() { return a; }
    
    public void setFirstName(String value) { first = value; }
    public void setLastName(String value) { last = value; }
    public void setAge(int value) { a = value; }
    
    public String toString()
    {
        return "[PersonImpl: " + first + "]";
    }
    
    private String first;
    private String last;
    private int a;
}
