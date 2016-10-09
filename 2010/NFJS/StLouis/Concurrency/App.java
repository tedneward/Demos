import java.util.concurrent.atomic.*;

class MyRunnable implements Runnable
{
    public AtomicBoolean quit = new AtomicBoolean(false);
    public void run()
    {
        System.out.println("Entering child thread" + Thread.currentThread().getId());
        while (!quit.get())
        {
            try { Thread.sleep(1000); System.out.println("PING"); }
            catch (InterruptedException intEx) { intEx.printStackTrace(); }
        }
        System.out.println("Whee! Child thread here!");
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
        System.out.println(t.isAlive());
        System.out.println(t.getId());
        t.start();
        System.out.println(t.isAlive());
        
        t.join(4 * 1000);
        System.out.println("Quitting....");
        r.set(true);
        
        t.join(2 * 1000);
        if (t.isAlive())
        {
            System.out.println("Interrupting....");
            t.interrupt();
            t.join(2 * 1000);
            if (t.isAlive())
            {
                t.stop();
                t.join(2 * 1000);
                if (t.isAlive())
                {
                    System.out.println("WTF?!?!");
                }
            }
        }
        
        System.out.println("Exiting main()");
    }
}






