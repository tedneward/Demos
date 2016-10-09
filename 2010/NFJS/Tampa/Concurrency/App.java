import java.util.concurrent.atomic.*;

class MyTask implements Runnable
{
    public AtomicBoolean pleaseQuit = new AtomicBoolean(false);
    
    public void run()
    {
        while (!(pleaseQuit.get()))
        {
            System.out.println("PING: " + 
                Thread.currentThread().getName());
            try { Thread.sleep(60 * 60 * 1000); }
            catch (InterruptedException ix) { ix.printStackTrace(); }
        }
        //System.out.println("Whee! A different thread!");
    }
}

public class App
{
    public static void main(String[] args)
        throws InterruptedException
    {
        new Object();
        
        MyTask task = new MyTask();
        Thread t = new Thread(task);
        t.start();
        
        Thread.currentThread().sleep(4 * 1000);
        task.pleaseQuit.set(true);
        
        Thread.currentThread().sleep(2 * 1000);
        if (t.isAlive())
        {
            System.out.println("It's still alive");
            t.interrupt();
            Thread.currentThread().sleep(2 * 1000);
            if (t.isAlive())
            {
                System.out.println("It's STILL alive");
                t.stop();
                Thread.currentThread().sleep(2 * 1000);
                if (t.isAlive())
                {
                    System.out.println("It's STILL alive?!?!?");
                    System.out.println("WTF!?!");
                }
            }
        }
        
        System.out.println("End of main()");
    }
}