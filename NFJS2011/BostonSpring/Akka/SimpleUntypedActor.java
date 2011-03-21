import akka.actor.*;
import com.eaio.uuid.UUID;

public class SimpleUntypedActor extends UntypedActor 
{
    public void onReceive(Object message)
        throws Exception 
    {
        if (message instanceof String)
        {
            System.out.println((String)message);
            String response = "SUA says " + (String)message + " back!";
            getContext().replyUnsafe(response);
        }
        else
            throw new IllegalArgumentException("Not a string!: " + message);
    }
}
    
        /*
        if (message instanceof String) 
        {
            if (message.equals("Hello"))
            {
                System.out.println("Received String message: " + message);
            }
            else if (message.equals("Greetings"))
            {
                getContext().replyUnsafe(message + " from " + getContext().getUuid());
            }
            else
            {
                System.out.println("Not sure what we got here: " + message);
            }
        }
        else 
            throw new IllegalArgumentException("Unknown message: " + message);
    }
        */