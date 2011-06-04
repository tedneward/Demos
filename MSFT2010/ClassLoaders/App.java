import java.net.*;

public class App extends Object // static
{
    public javax.swing.JWindow window;
    
    public static void main(String[] args)
        throws Exception
    {
       System.out.println("Hello world");
       
       Class ac = App.class;
       ClassLoader acl = ac.getClassLoader();
       System.out.println(acl);
       
       for (ClassLoader current = acl; current != null;
            current = current.getParent())
        {
            System.out.println(current);
        }
        
        System.out.println("================");
        
        java.util.Map data = new java.util.HashMap();
        
        URLClassLoader ucl = 
            new URLClassLoader(
                new URL[] {
                    new java.io.File("subdir").toURL() },
                acl);
        Class d = Class.forName("Driver", true, ucl);
        Object o = d.newInstance();
        Plugin p = (Plugin)o;
        p.doit(data);
        
        System.out.println("Press a key");
        System.in.read();

        URLClassLoader ucl2 = 
            new URLClassLoader(
                new URL[] {
                    new java.io.File("subdir").toURL() },
                acl);
        Class d2 = Class.forName("Driver", true, ucl2);
        Object o2 = d2.newInstance();
        Plugin p2 = (Plugin)o2;
        p2.doit(data);
        

        /*
       Class oc = Object.class;
       ClassLoader ocl = oc.getClassLoader();
       System.out.println(ocl);
       
       Class wc = acl.loadClass("javax.swing.JWindow");
       ClassLoader wcl = wc.getClassLoader();
       System.out.println(wcl);
       */
       
       System.out.println("Exiting");
    } 
}


