import java.util.*;
import com.mongodb.*;

public class App
{
    public static void main(String[] args)
        throws java.net.UnknownHostException
    {
        Mongo m = new Mongo();
        
        DB myDB = m.getDB("NFJSAttendees");
        DBCollection memphis = myDB.getCollection("memphis");
        
        /*
        DBObject d1 = new BasicDBObject().
            append("firstName", "Chad").
            append("lastName", "Miller").
            append("age", 28);
        memphis.insert(d1);
        */
        
        System.out.println("BEFORE:");
        for (DBObject obj : memphis.find(
                new BasicDBObject().
                    append("firstName", "Chad").
                    append("lastName", "Miller")))
            System.out.println(obj);
            
        DBObject obj = memphis.findOne(
            new BasicDBObject().
                append("firstName", "Chad"));
        obj.put("hair", "none");
        memphis.save(obj);

        System.out.println("AFTER");
        for (DBObject o : memphis.find())
            System.out.println(o);
    }
}
