import java.io.*;
import com.mongodb.*;

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        Mongo mserver = new Mongo();
        for (String db : mserver.getDatabaseNames())
            System.out.println(db);
            
        DB demos = mserver.getDB("demos");
        for (String coll : demos.getCollectionNames())
            System.out.println(coll);
        
        DBCollection speakers = demos.getCollection("speakers");

        byte[] code = new byte[3 * 1024];
        FileInputStream fis = new FileInputStream("App.class");
        fis.read(code);

        BasicDBObject ai = new BasicDBObject();
        ai.append("firstName", "IBM");
//        ai.append("skillz", "JavaOne-esque");
        ai.append("age", "55");
        ai.append("speakCode", code);
        ai.append("_id", 1);
        
        speakers.save(ai);

        BasicDBObject query = new BasicDBObject();
        for (DBObject obj : speakers.find())
        {
            if (obj.get("age") == null)
                obj.put("age", new java.util.Date().getMinutes());
            speakers.save(obj);
        }

        query.append("$where", "function() { return this.age > 40;}");
        for (DBObject obj : speakers.find(query))
        {
            System.out.println(obj);
        }
        
        demos.eval("print('Howdy');");
    }
}







