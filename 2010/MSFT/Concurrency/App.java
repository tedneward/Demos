class MyRunnable implements Runnable
{
    private AtomicBoolean quit = new AtomicBoolean(false);
    public void setQuit(boolean value)
    {
        quit.set(value);
    }

    public void run()
    {
        System.out.println("I'm on a different thread. Whee!");
        System.out.println("Thread " + Thread.currentThread().getName() + Thread.currentThread().getId());
        
        //throw new RuntimeException("Waaah. Wanna cookie!");        

        while (!(quit.get()))
        {
            try { Thread.sleep(1 * 1000); }
            catch (Throwable t) { t.printStackTrace(); }
            
            System.out.println("PING");
        }    
    }
}


public class App
{
    public static void main(String[] args)
        throws Exception
    {
        System.out.println("Main() Thread " + Thread.currentThread().getName() + Thread.currentThread().getId());

        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(
            new Thread.UncaughtExceptionHandler() {
                public void uncaughtException(Thread t, Throwable th) {
                    System.out.println("AAACK! AN ERROR!");
                    th.printStackTrace();
                }
            });
        t.start();

        System.out.println("Quitting....");
        r.setQuit(true);
        t.join(3 * 1000);
        if (t.isAlive())
        {
            System.out.println("Interrupting...");
            t.interrupt();
            t.join(3 * 1000);
            if (t.isAlive())
            {
                System.out.println("Stopping....");
                t.stop();
                t.join(3 * 1000);
                if (t.isAlive())
                {
                    System.out.println("WTF?!??!?!?!");
                }
            }
        }

        System.out.println("Exiting main");        
    }
}







