import java.rmi.*;

public interface RemoteHello extends Remote
{
    public void sayHello()
        throws RemoteException;
    
    public void say(String msg)
        throws RemoteException;
}