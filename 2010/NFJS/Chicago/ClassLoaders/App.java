import java.io.*;
import java.net.*;

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        System.out.println("Hello world");
        
        java.util.Map data = new java.util.HashMap();
        
        ClassLoader oldCCL = Thread.currentThread().getContextClassLoader();
        
        URLClassLoader ucl = 
            new URLClassLoader(new URL[] { new File("./subdir").toURL() }, oldCCL);
        Thread.currentThread().setContextClassLoader(ucl);

        Class driverClass = 
            Class.forName("Driver", true, ucl);
        Bearsable b1 = (Bearsable)driverClass.newInstance();
        b1.doIt(data);
        
        System.out.println("Howdy! Press Enter to continue");
        System.in.read();

        URLClassLoader ucl2 = 
            new URLClassLoader(new URL[] { new File("./subdir").toURL() }, oldCCL);
        Class driverClass2 = 
            Class.forName("Driver", true, ucl2);
        Bearsable b2 = (Bearsable)driverClass2.newInstance();
        b2.doIt(data);

        Thread.currentThread().setContextClassLoader(oldCCL);
    }
    
    public static void oldmain(String[] args)
        throws Exception
    {
        System.out.println("Hello world");
        
        ClassLoader appCL = App.class.getClassLoader();
        System.out.println("appCL = " + appCL);

        System.out.println("---------");
        for (ClassLoader curr = appCL; curr != null; curr = curr.getParent())
            System.out.println(curr);
        System.out.println("---------");
        
        //javax.swing.JWindow window = new javax.swing.JWindow();
        Class jwClass = Class.forName("javax.swing.JWindow", true,
                                      Thread.currentThread().getContextClassLoader());
            //appCL.loadClass("javax.swing.JWindow");
        System.out.println("jwClass = " + jwClass);
        ClassLoader jwCL = jwClass.getClassLoader(); //window.getClass().getClassLoader();
        System.out.println("jwCL = " + jwCL);
        
        Object obj = new Object();
        ClassLoader objCL = obj.getClass().getClassLoader();
        System.out.println("objCL = " + objCL);

        Class driverClass = Class.forName("Driver", true, appCL); //appCL.loadClass("Driver");
        ClassLoader driverCL = driverClass.getClassLoader();
        System.out.println("driverCL = " + driverCL);
        //Driver d = new Driver();
    }
}