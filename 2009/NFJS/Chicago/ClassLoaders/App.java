import java.net.*;

public class App extends Object
{
	public static void main(String[] args)
		throws Exception
	{
		ClassLoader acl = App.class.getClassLoader();
		
		java.util.Map dataMap = new java.util.HashMap();
		
		URLClassLoader ucl = new URLClassLoader(new URL[] { new java.io.File("./subdir").toURL() }, acl);
		
		ClassLoader oldCCL = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(ucl);
		
		Class d = Class.forName("Driver", true, ucl);
		Object o = d.newInstance();
		Jimmable j = (Jimmable)o;
		j.doSomething(dataMap);
		
		Thread.currentThread().setContextClassLoader(oldCCL);
		
		System.out.println("Press the ANY key to continue");
		System.in.read();

		URLClassLoader ucl2 = new URLClassLoader(new URL[] { new java.io.File("./subdir").toURL() }, acl);
		Thread.currentThread().setContextClassLoader(ucl2);
		
		Class d2 = Class.forName("Driver", true, ucl2);
		Object o2 = d2.newInstance();
		Jimmable j2 = (Jimmable)o2;
		j2.doSomething(dataMap);
		
		Thread.currentThread().setContextClassLoader(oldCCL);
	}
	
	public static void oldmain(String[] args)
		throws Exception
	{
		System.out.println("Hello world");
		
		ClassLoader appCL = App.class.getClassLoader();
		print("appCL", appCL);

		Class driver = 
			Class.forName("Driver", true, 
				Thread.currentThread().getContextClassLoader());
		ClassLoader drvCL = driver.getClassLoader();
		print("drvCL", drvCL);
		
		Class obj = appCL.loadClass("java.lang.Object");
		ClassLoader objCL = obj.getClassLoader();
		print("objCL", objCL);
		
		Class win = appCL.loadClass("javax.swing.JWindow");
		ClassLoader winCL = win.getClassLoader();
		print("winCL", winCL);
		
		System.out.println("===============");
		
		for (ClassLoader curr = appCL; curr != null; curr = curr.getParent())
			System.out.println("==>" + curr);
		
		System.out.println("End of main");
				
	}
	public static void print(String name, ClassLoader cl)
	{
		System.out.println(name + " = " + cl);
	}
}