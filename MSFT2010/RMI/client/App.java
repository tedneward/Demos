import java.rmi.*;
import java.rmi.registry.*;

public class App
{
    public static void main(String[] args) 
        throws Exception {
        RemoteHello rh = (RemoteHello)
            LocateRegistry.getRegistry().lookup("Babelfish");
        
        rh.sayHello();
        rh.say("Au revoir");
    }
}