import java.util.concurrent.atomic.*;

class DoSomething
	implements Runnable
{
	private AtomicBoolean running = new AtomicBoolean(true);
	public void stopRunning()
	{
		running.lazySet(false);
	}
	public boolean getRunning()
	{
		return running.get();
	}
	public void run() 
	{
		System.out.println("Tom's child thread");
		while (running.get())
		{
			System.out.println("PING");
			try { Thread.sleep(30 * 1000); }
			catch (Throwable intEx) { 
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
		System.out.println("Entering main");
		DoSomething d = new DoSomething();
		Thread t = new Thread(d);
		t.start();
		
		Thread.sleep(2 * 1000);
		
		// Try to exit the thread
		System.out.println("First attempt");
		d.stopRunning();
		t.join(1 * 1000);
		if (t.isAlive())
		{
			System.out.println("Second attempt");
			t.interrupt();
			t.join(1 * 1000);
			if (t.isAlive())
			{
				System.out.println("Third attempt");
				t.stop();
				t.join(1 * 1000);
				if (t.isAlive())
				{
					System.out.println("AAAGH!");
				}
			}
		}
		
		System.out.println("Exiting main");
	}
}











