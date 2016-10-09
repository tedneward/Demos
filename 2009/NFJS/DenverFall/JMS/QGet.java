import java.util.Properties;
import javax.naming.*;
import javax.jms.*;

public class QGet
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

        QueueReceiver consumer = session.createReceiver( destination, "Coolness > 5" );
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message m)
            {                
                if (m instanceof TextMessage)
                {
                    try { System.out.println("TextMessage received! ==> " + ((TextMessage)m).getText()); }
                    catch ( Exception e) { e.printStackTrace(); }
                }
                else
                    System.out.println("Unknown message type! ==> " + m);
            }
        });
        connection.start();
    }
}
