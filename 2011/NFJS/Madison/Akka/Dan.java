import akka.actor.*;

public class Dan
    extends UntypedActor
{
    static class HowdyMessage { }
    static class GreetingMessage { }

    public void onReceive(Object message)
        throws Exception
    {
        if (message instanceof HowdyMessage)
        {
            System.out.println("Dan says hi!");
        }
        if (message instanceof GreetingMessage)
        {
            String response = "Dan says howdy to you, stranger!";
            getContext().replyUnsafe(response);
        }
    }
}