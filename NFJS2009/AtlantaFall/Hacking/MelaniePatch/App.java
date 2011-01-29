import java.io.*;
import java.lang.reflect.*;
import java.net.*;

class CustomObjectInputStream extends ObjectInputStream 
{
    private ClassLoader classLoader;
    
    public CustomObjectInputStream(InputStream in, ClassLoader classLoader) 
    	throws IOException 
    {
        super(in);
        this.classLoader = classLoader;
    }
    
    protected Class<?> resolveClass(ObjectStreamClass desc) 
    	throws ClassNotFoundException 
	{
        return Class.forName(desc.getName(), false, classLoader);
    }
}

public class App
{
	public static byte[] firstRun()
		throws Exception
	{
		URLClassLoader ucl = new URLClassLoader(new URL[] { new File("./subdir").toURL() });
		
		Class pClass = Class.forName("Person", true, ucl);
		Constructor ctor = pClass.getConstructor(String.class, String.class, int.class);
		Object obj = ctor.newInstance("Melanie", "Smith", 29);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		oos.flush();
		byte[] bits = baos.toByteArray();
		
		return bits;
	}
	
	public static void secondRun(byte[] bits)
		throws Exception
	{
		URLClassLoader ucl = new URLClassLoader(new URL[] { new File("./subdir").toURL() });

		ByteArrayInputStream bais = new ByteArrayInputStream(bits);
		CustomObjectInputStream ois = new CustomObjectInputStream(bais, ucl);
		Object obj2 = ois.readObject();

		System.out.println("The Person is " + obj2);
	}
	
	public static void main(String[] args)
		throws Exception
	{
		byte[] bits = firstRun();

		System.out.println("Press any key to continue");
		System.in.read();
		
		secondRun(bits);
	}
}






