import java.util.concurrent.locks.*;

class Drop {
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private Lock lock = new ReentrantLock();
    
    //Message sent from producer to consumer.
    private String message;
    //True if consumer should wait for producer to send message, false
    //if producer should wait for consumer to retrieve message.
    private boolean empty = true;

    public String take() {
        lock.lock();
        try
        {
            //Wait until message is available.
            while (empty) {
                try {
                    notEmpty.await(); //lock.wait();
                } catch (InterruptedException e) {}
            }
            //Toggle status.
            empty = true;
            //Notify producer that status has changed.
            notFull.signalAll();   //lock.notifyAll();
            return message;
        }
        finally
        {
            lock.unlock();
        }
    }

    public void put(String message) {
        lock.lock();
        try
        {
            //Wait until message has been retrieved.
            while (!empty) {
                try { 
                    notFull.await(); //lock.wait();
                } catch (InterruptedException e) {}
            }
            //Toggle status.
            empty = false;
            //Store message.
            this.message = message;
            //Notify consumer that status has changed.
            notEmpty.signalAll(); //lock.notifyAll();
        }
        finally
        {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };

        for (int i = 0; i < importantInfo.length; i++) {
            drop.put(importantInfo[i]);
        }
        drop.put("DONE");
    }
}

class Consumer implements Runnable {
    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        for (String message = drop.take(); ! message.equals("DONE");
                message = drop.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
        }
    }
}

public class DropApp {
    public static void main(String[] args) 
        throws Throwable
    {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
        synchronized(drop)
        {
            Thread.sleep(1000 * 60 * 60 * 24 * 365);
        }
    }
}
