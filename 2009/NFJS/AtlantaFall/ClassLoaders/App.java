import java.net.*;
import java.util.*;

public class App
{
	public static void main(String[] args)
		throws Exception
	{
		Map serverData = new HashMap();
		
		// create CL around "subdir"
		URLClassLoader ucl = new URLClassLoader(new URL[] { new java.io.File("./subdir").toURL() } );
		
		ClassLoader occl = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(ucl);
		
		// Load "Driver"
		Class driver = Class.forName("Driver", true, Thread.currentThread().getContextClassLoader());

		// Instantiate
		Object d = driver.newInstance();
		Jeffable j = (Jeffable)d;
		j.doIt(serverData);

		// Clean up
		Thread.currentThread().setContextClassLoader(occl);
		
		System.out.println("Press the ANY key to continue");
		System.in.read();

		URLClassLoader ucl2 = new URLClassLoader(new URL[] { new java.io.File("./subdir").toURL() } );
		Thread.currentThread().setContextClassLoader(ucl2);
		
		// Load "Driver"
		driver = Class.forName("Driver", true, Thread.currentThread().getContextClassLoader());

		// Instantiate
		d = driver.newInstance();
		j = (Jeffable)d;
		j.doIt(serverData);

		// Clean up
		Thread.currentThread().setContextClassLoader(occl);
	}
	
	public static void oldmain(String[] args)
		throws Exception
	{
		System.out.println("Hello world");		
		
		ClassLoader acl = App.class.getClassLoader();
		System.out.println(acl);
				
		Class driver = Class.forName("Driver", true, Thread.currentThread().getContextClassLoader());
		ClassLoader dcl = driver.getClassLoader();
		System.out.println(dcl);

		Class jw = acl.loadClass("javax.swing.JWindow");
		ClassLoader jwcl = jw.getClassLoader();
		System.out.println(jwcl);
		
		System.out.println("====================");
		
		for (ClassLoader cl = acl; cl != null; cl = cl.getParent())
			System.out.println(cl);

		System.out.println("====================");
		
		Object d = driver.newInstance();
	}
}