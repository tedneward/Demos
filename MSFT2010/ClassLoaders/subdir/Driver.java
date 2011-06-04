public class Driver
    implements Plugin
{
    int x = 0;
    static int sx = 0;
    
    public void doit(java.util.Map map)
    {
        if (map.get("DATA") == null)
            map.put("DATA", this);
        else
        {
            Driver d = (Driver)map.get("DATA");
            d.toString();
        }
    }

    public Driver()
    {
        x++; sx++;
        System.out.println("x = " + x + " sx=" + sx);
    }
    
    static
    {
        System.out.println("Driver classloaded");
    }
}