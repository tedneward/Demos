import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class Server
{
    public static void main(String[] args)
        throws Exception
    {
        RemoteHello servant = new RemoteHelloImpl();
        
        RemoteHello stub = (RemoteHello)
            UnicastRemoteObject.exportObject(servant);
        Registry rmiregistry = LocateRegistry.getRegistry();
        rmiregistry.bind("Babelfish", stub);
        
        System.out.println("Now what?!?");
    }
}