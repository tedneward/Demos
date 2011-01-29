import java.lang.reflect.*;

public class CallPrivateMethods
{
    public static void main(String[] args)
        throws Exception
    {
        Class secret_klass = Class.forName("Secret",
            true, CallPrivateMethods.class.getClassLoader());
        
        Method ycsm = secret_klass.getDeclaredMethod("youCantSeeMe");
        ycsm.setAccessible(true);
        
        ycsm.invoke(null);
        
        ClassLoader appCL = CallPrivateMethods.class.getClassLoader();
        for (Class c = appCL.getClass(); c != null; c = c.getSuperclass())
        {
            for (Method m : c.getDeclaredMethods())
            {
                System.out.println(m.getName());
            }
        }
    }
}