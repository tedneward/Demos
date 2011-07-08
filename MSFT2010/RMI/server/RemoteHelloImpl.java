import java.rmi.*;

public class RemoteHelloImpl
    implements RemoteHello
{
    public void sayHello()
        throws RemoteException
    {
        System.out.println("Guten morgen");
    }
    
    public void say(String msg)
        throws RemoteException
    {
        System.out.println(msg + "gluckenfraken");
    }
}