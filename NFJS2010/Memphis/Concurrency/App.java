class MyRunnable
    implements Runnable
{
    private AtomicBoolean running = new AtomicBoolean(true);
    public void setRunning(boolean value) { 
        running.set(value); 
    }
    public void run()
    {
        System.out.println("Howdy from child thread");
        while (running.get())
        {
            System.out.println("PING");
            try { Thread.currentThread().sleep(5 * 60 * 1000); }
            catch (Throwable intEx) { 
                // TODO 
                intEx.printStackTrace();
            }
        }
    }
}

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        MyRunnable mr = new MyRunnable();
        Thread t = new Thread(mr);
        t.start();
        // ...
        
        /*
        ScheduledExecutorService ses =
            Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture sh = 
            ses.scheduleAtFixedRate(new Runnable() {
                public void run() { System.out.println("PING"); }
            }, 5, 5, TimeUnit.SECONDS);
        Thread.sleep(7 * 1000);
        sh.cancel(true);
        */
        
        Thread.sleep(7 * 1000);
        System.out.println("Time to die");
        if (t.isAlive())
        {
            mr.setRunning(false);
            System.out.println("Set running to false");
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
                        System.out.println("WTF?!?");
                    }
                }
            }
        }
        
        System.out.println("End of main()");
    }
}











