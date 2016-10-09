import akka.actor.*;
import akka.dispatch.*;
import com.eaio.uuid.UUID;
import scala.*;

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        ActorRef actor = Actors.actorOf(SimpleUntypedActor.class);
        actor.start();

        try
        {
            Object response = actor.sendRequestReply("Howdy, Akka!", 1000, actor);
            System.out.println("Response = " + response);
        }
        catch (ActorTimeoutException atx)
        {
            atx.printStackTrace();
        }

        Future f = actor.sendRequestReplyFuture("Greetings", 1000, actor);
        while  (!(f.isCompleted()))
        {
            System.out.print(".");
        }

        Option resultOption = f.result();
        if (resultOption.isDefined())
        {
            Object result = resultOption.get();
            System.out.println("R/R future response: " + result);
        }
        
        
        //actor.stop();

        //untypedMain();
        //typedMain();
        //remoteMain();
    }
    
    public static void untypedMain()
    {
        ActorRef actor = Actors.actorOf(SimpleUntypedActor.class);
        actor.start();
        
        // one-way message (no response)
        System.out.println("====> One-way");
        actor.sendOneWay("Hello");
        
        // request/reply eventually (with timeout)
        System.out.println("====> Request/reply eventually");
        try
        {
            Object result = actor.sendRequestReply("Greetings", 1000, actor);
            System.out.println("Request/reply response: " + result);
        }
        catch (ActorTimeoutException atx)
        {
            System.out.println("EXCEPTION: " + atx);
        }
        
        // request/reply returning a Future immediately
        System.out.println("====> Request/reply Future");
        Future f = actor.sendRequestReplyFuture("Greetings", 1000, actor);
        f.await();
        if (f.isCompleted())
        {
            Option resultOption = f.result();
            if (resultOption.isDefined())
            {
                Object result = resultOption.get();
                System.out.println("R/R future response: " + result);
            }
        }
        
        actor.stop();
    }

    public static void typedMain()
    {
        Person person = (Person)TypedActor.newInstance(Person.class, PersonImpl.class);
        
        person.setFirstName("Ted");
        person.setLastName("Neward");
        person.setAge(40);
        
        System.out.println(person.getFirstName());
        TypedActor.stop(person);
    }

    public static void remoteMain()
    {
        //Actors.remote().start("localhost", 2552).register("hello-service", Actors.actorOf(SimpleUntypedActor.class);
        Actors.remote().start("localhost", 2552);
    }
}