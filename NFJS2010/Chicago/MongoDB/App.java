import com.mongodb.*;
import org.bson.types.*;

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        Mongo mongo = new Mongo( "127.0.0.1" );
        
        for (String dbName : mongo.getDatabaseNames() )
            System.out.println("Found db " + dbName);
        
        DB db = mongo.getDB("nfjs_chicago");
        
        for (String coll : db.getCollectionNames() )
            System.out.println("Found coll " + coll);
        DBCollection attendees = db.getCollection("attendees");
        System.out.println(attendees.toString());

        /*        
        DBObject dbobj = new BasicDBObject();
        dbobj.put("_id", "1234");
        dbobj.put("firstname", "Yulia");
        dbobj.put("lastname", "Shulman");
        dbobj.put("age", 37);
        attendees.insert(dbobj);
        */
        
        /*       
        DBObject hack = new BasicDBObject();
        hack.put("firstname", "Dave");
        hack.put("lastname", "Smith");
        hack.put("age", 30);
        hack.put("_id", new ObjectId("4ce024d66f882bf17e826ef9"));
        attendees.save(hack);
        */
        
        DBObject query = new BasicDBObject();
        query.put("$where", "this.age > 5");

        for (DBObject obj : attendees.find(query))
            System.out.println(obj);
            
    }
}