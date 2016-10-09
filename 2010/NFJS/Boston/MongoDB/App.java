import com.mongodb.*;

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        Mongo m = new Mongo();
        
        DB db = m.getDB("boston");
        
        DBCollection ats = db.getCollection("attendees");
        
        DBObject marina = ats.findOne(new BasicDBObject().append("_id", new ObjectId("4b92c213ea87dc45649f537f")));
        System.out.println("Found marina" + marina);
        
        DBObject chaoyu = ats.findOne(new BasicDBObject().append("_id", new ObjectId("4b92c2321035dc45da69475b")));
        System.out.println("Found chaoyu" + chaoyu);
        System.out.println(chaoyu.get("_id").getClass());
        
        //chaoyu.put("girlfriend", marina.get("_id"));
        //marina.put("boyfriend", chaoyu.get("_id"));
        
    }
}




