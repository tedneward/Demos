public class Driver
    implements Jeffable
{
	static int count = 0;
	static
	{
		System.out.println("Driver loaded!");
	}	
	
	public Driver()
	{
		count++;
		System.out.println("Driver ctor: " + count + " instances created");
		System.out.println("Jeff says hi!");
	}
	
	public void doIt(java.util.Map data)
	{
		if (data.get("JEFFSDATA") == null)
		{
			data.put("JEFFSDATA", this);
		}
		else
		{
			Driver d = (Driver)data.get("JEFFSDATA");
			System.out.println(d);
		}
	}
}