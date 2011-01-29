import java.dyn.*;

class #"This is a really long class name and probably should get the developer shot in teh face with a large bazooka"
{
	
}

class #"java\\lang\\Object"
{}


public class App
{
	public static boolean #"open?"()
	{
		return false;
	}
	
	public static void main(String[] args)
	{
		int #"George's age is" = 39;
		
		Object o = new #"java\\lang\\Object"();
		
		System.out.println("Hello, 7!");
		System.out.println("The App is " + #"open?"());
		
		MethodHandle mh_o = MethodHandles.lookup().findStatic(App.class, "open?", MethodType.make(boolean.class));
		System.out.println(mh_o.<boolean>invoke());
	}
}