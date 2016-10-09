import java.util.Random;
import java.util.Properties;
import javax.naming.*;
import javax.jms.*;

public class QPost
{
    public static void main(String[] args)
    	throws Exception
    {
        Properties properties = new Properties();
        //properties.load(new java.io.FileReader("dropboxmq.properties"));
        properties.load(new java.io.FileReader("activemq.properties"));
        InitialContext initialContext = new InitialContext( properties );
        
        QueueConnectionFactory connectionFactory = 
            (QueueConnectionFactory)initialContext.lookup( "QueueConnectionFactory" );
        Queue destination = (Queue)initialContext.lookup( "TestQueue1" );

        QueueConnection connection = connectionFactory.createQueueConnection();
        QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE );

        QueueSender producer = session.createSender( destination );

        Random r = new Random();

        for (String s : args)
        {
            TextMessage message = session.createTextMessage( s );
            int cool = r.nextInt(9); // 0 - 9
            message.setIntProperty("Coolness", cool);
            producer.send( message );
            System.out.println("Sent " + message + " at coolness " + cool);
        }
        
        connection.close();
    }
}








