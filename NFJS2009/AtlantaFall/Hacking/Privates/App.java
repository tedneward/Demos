import java.lang.reflect.*;

public class App
{
	public static void main(String[] args)
		throws Exception
	{
		Point p = new Point(5, 5);
		
		System.out.println(p);
		
		Class clsPoint = p.getClass();
		for (Method m : clsPoint.getDeclaredMethods())
		{
			System.out.println(m);
		}
		for (Field f : clsPoint.getDeclaredFields())
		{
			System.out.println(f);
		}
		
		Method mthInit = clsPoint.getDeclaredMethod("init", new Class[] { int.class, int.class });
		System.out.println(mthInit);
		
		mthInit.setAccessible(true);
		
		mthInit.invoke(p, 10, 10);
		
		Field fldX = clsPoint.getDeclaredField("x");
		fldX.setAccessible(true);
		fldX.set(p, 25);
		
		System.out.println(p);
	}
}