import java.util.concurrent.atomic.*;

class MyRunnable implements Runnable
{
    public AtomicBoolean quit = false;
    public void run()
    {
        while (!quit.get())
        {
            try { Thread.sleep(20 * 1000); }
            catch (InterruptedException intEx) { intEx.printStackTrace(); }
            System.out.println("PING");
        }
    }
}

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        System.out.println("Entering main()");
        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();

        try { Thread.sleep(2 * 1000); }
        catch (InterruptedException intEx) { intEx.printStackTrace(); }

        System.out.println("Setting quit to true...");        
        t.join(2 * 1000);
        if (t.isAlive())
        {
            System.out.println("Interrupting....");
            t.interrupt();
            t.join(2 * 1000);
            if (t.isAlive())
            {
                System.out.println("Stopping....");
                t.stop();
                t.join(2 * 1000);
                if (t.isAlive())
                {
                    System.out.println("WTF?!?");
                }
            }
        }        
        
        System.out.println("Exiting main()");
    }
}






