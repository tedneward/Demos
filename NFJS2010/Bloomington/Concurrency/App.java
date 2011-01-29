import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

class DoSomething implements Runnable
{
    private AtomicBoolean quit = new AtomicBoolean(false);
    public void setQuit(boolean value) 
    { quit.lazySet(value); }
    public void run()
    {
        while (!(quit.get()))
        {
            try
            {
                Thread.sleep(60 * 1000);
                System.out.println("PING");
            }
            catch (Throwable intEx) { intEx.printStackTrace(); }
        }
    }
}

public class App
{
    public static void main(String[] arg)
        throws Exception
    {
        Callable<Double> calculatePi = new Callable<Double>() {
            public Double call() {
                System.out.println("Calculating PI");
                
                try { Thread.sleep(2 * 1000); }
                catch (InterruptedException intex) { intex.printStackTrace(); }
                
                return Math.PI;
            }
        };
        
        ExecutorService es = Executors.newFixedThreadPool(5);
        Future<Double> f = es.submit(calculatePi);
        while (f.isDone() != true)
        {
            try { Thread.sleep(500); System.out.print("."); }
            catch (InterruptedException intex) { intex.printStackTrace(); }
        }
        System.out.println("PI = " + f.get());
        
        
        DoSomething d = new DoSomething();
        Thread t = new Thread(d);
        t.start();
        
        Thread.sleep(1 * 1000);
        System.out.println("d.quit = true");
        d.setQuit(true);

        t.join(2 * 1000);
        if (t.isAlive())
        {
            System.out.println("It's not dead yet!");
            
            t.interrupt();
            t.join(2 * 1000);
            if (t.isAlive())
            {
                System.out.println("STILL not dead!");
                
                t.stop();
                t.join(2 * 1000);
                if (t.isAlive())
                {
                    System.out.println("WTF?!?");
                }
                
                System.out.println("ACK! Giving up!");
            }
        }
        
        Thread.UncaughtExceptionHandler tueh =
            new Thread.UncaughtExceptionHandler() {
                public void uncaughtException(Thread t1, Throwable t2) {
                    System.out.println("AAAAAAAGGGHH!");
                    t2.printStackTrace();
                }   
            };
        Thread.setDefaultUncaughtExceptionHandler(tueh);
        
        throw new RuntimeException("I'm a little teapot");
        
        //System.out.println("Exiting main()");
    }
}
