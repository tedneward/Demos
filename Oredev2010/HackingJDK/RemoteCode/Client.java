
import java.lang.reflect.*;
import java.net.*;

public class Client
{
    public static void main(String[] args)
        throws Exception
    {
        URLClassLoader ucl = new URLClassLoader(
            new URL[] { 
                new URL("http://localhost:8080/App.jar") 
                },
            Client.class.getClassLoader()
            );
        Class appClass = Class.forName("App", true, ucl);
        Method main = appClass.getMethod("main");
        main.invoke(null);
    }
}