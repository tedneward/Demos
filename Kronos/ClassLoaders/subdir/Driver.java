public class Driver
    implements Jamesable
{
    private static int count = 0;
    public Driver()
    {
        System.out.println("Driver constructor #" + (count++) + ", beeyatch!");
    }
    
    public void go(java.util.Map data)
    {
        if (data.get("UNIQUE") == null)
            data.put("UNIQUE", this);
        else
        {
            Driver d = (Driver)data.get("UNIQUE");
        }
    }
    
    static
    {
        System.out.println("static init block");
    }
}