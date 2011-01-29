public class Driver
    implements Bearsable
{
    static
    {
        System.out.println("Driver loaded; aint this cool?");
    }
    
    static int count = 0;
    
    public Driver()
    {
        System.out.println("Driver .ctor; count = " + (++count));
    }

    public void doIt(java.util.Map data)
    {
        if (data.get("DATA") == null)
        {
            data.put("DATA", this);
        }
        else
        {
            Object o = data.get("DATA");
            if (o instanceof Driver)
                System.out.println("instanceof works");
            Driver d = (Driver) data.get("DATA");
        }
    }
}