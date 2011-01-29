import java.dyn.*;

class #"java\\lang\\Object"
{
	
}

class #"I don't wanna do this but my boss is making me"
{
	
}

public class Hello
{
	public static boolean #"isWeekend?"()
	{
		return true;
	}
	
	public static void main(String[] args)
	{
		System.out.println("Hellow!");
		System.out.println("Is it the weekend? " + #"isWeekend?"());
		
		MethodHandle iw =
			MethodHandles.lookup().findStatic(Hello.class, "isWeekend?", MethodType.make(boolean.class));
		boolean result = iw.<boolean>invoke();
		System.out.println("Is it the weekend? " + result);
	}
}











