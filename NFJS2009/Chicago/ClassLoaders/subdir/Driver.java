public class Driver
	implements Jimmable
{
	private static int count;

	public Driver()
	{
		count++;
		System.out.println("Driver ctor: " + count + " instances created");
		System.out.println("D00d that rocks!");
	}	
	
	static
	{
		count = 0;
		
		System.out.println("Driver <clinit> loaded!");
	}
	
	public void doSomething(java.util.Map data)
	{
		if (data.get("DATA") == null)
		{
			data.put("DATA", this);
		}
		else
		{
			Driver d = (Driver)data.get("DATA");
			System.out.println("Found the other!");
		}
	}
}