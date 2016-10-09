import akka.actor.*;
import com.eaio.uuid.UUID;

public class SimpleUntypedActor extends UntypedActor 
{
  public SimpleUntypedActor()
  {
    this.prefix = ">> ";
  }

  public SimpleUntypedActor(String prefix)
  {
    this.prefix = prefix;
  }

  public void onReceive(Object message)
    throws Exception 
  {
    if (message instanceof String) 
    {
      if (message.equals("Hello"))
      {
        System.out.println("Received String message: " + prefix + message);
      }
      else if (message.equals("Greetings"))
      {
        getContext().replyUnsafe(prefix + message + 
          " from " + getContext().getUuid());
      }
      else
      {
        System.out.println("Not sure what we got here: " + prefix + message);
      }
    }
    else 
      throw new IllegalArgumentException("Unknown message: " + message);
  }
  
  private String prefix;
}