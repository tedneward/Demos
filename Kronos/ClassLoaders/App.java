import java.net.*;

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        java.util.Map dataCtx = new java.util.HashMap();
    
        ClassLoader ucl = new URLClassLoader(new URL[] { new java.io.File("./subdir").toURL() }, App.class.getClassLoader());
        ClassLoader oldCCL = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(ucl);

        Class dClass = Class.forName("Driver", true, Thread.currentThread().getContextClassLoader());
        Jamesable d = (Jamesable)dClass.newInstance();
        d.go(dataCtx);

        System.out.println("Press ENTER to continue");
        System.in.read();
        
        ClassLoader ucl2 = new URLClassLoader(new URL[] { new java.io.File("./subdir").toURL() }, App.class.getClassLoader());
        Thread.currentThread().setContextClassLoader(ucl2);
        Class dClass2 = Class.forName("Driver", true, ucl2);
        Jamesable d2 = (Jamesable)dClass2.newInstance();
        d2.go(dataCtx);
        
        Thread.currentThread().setContextClassLoader(oldCCL);
    }

    public static void oldmain(String[] args)
        throws Throwable
    {
        System.out.println("Hello, world!");

        System.out.println("App CL = " + App.class.getClassLoader());
        ClassLoader aCL = App.class.getClassLoader();
        
        for (ClassLoader current = aCL; current != null; current = current.getParent())
            System.out.println("Current = " + current);
            
        ClassLoader oldCCL = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(aCL);

        Class dClass = Class.forName("Driver", true, Thread.currentThread().getContextClassLoader()); 
        System.out.println("Driver CL = " + dClass.getClassLoader());

        System.out.println("Object CL = " + Class.forName("java.lang.Object").getClassLoader());
        
        Class jwClass = aCL.loadClass("javax.swing.JWindow");
        System.out.println("JWindow CL = " + jwClass.getClassLoader());
        
        Thread.currentThread().setContextClassLoader(oldCCL);
    }
}