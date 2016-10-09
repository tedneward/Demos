import java.util.*;
import com.mongodb.*;

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        Mongo mon = new Mongo();
        DB minn = mon.getDB("minneapolis");
        DBCollection atts = minn.getCollection("attendees");
        System.out.println("count = " + atts.getCount());

        DBObject curt = atts.findOne(
            new BasicDBObject()
            .append("firstName", "Curt"));
        
        DBObject brian = atts.findOne(
            new BasicDBObject()
            .append("firstName", "Brian"));

        BasicDBList langs = new BasicDBList();
        langs.addAll(
            Arrays.asList(
                new BasicDBObject()
                    .append("name", "Visual Basic")
                    .append("pretentiousness", 1),
                new BasicDBObject()
                    .append("name", "Lisp")
                    .append("pretentiousness", 10)));
        brian.put("languages", langs);
        atts.save(brian);
        
        System.out.println(curt);
        System.out.println(brian);
        
        System.out.println("Querying....");
        for (DBObject o : atts.find(new BasicDBObject()
            .append("$where",
                "function() { this.languages.pretentiousness > 5 }")))
            System.out.println(o);

        /*
        BasicDBObject obj2 = new BasicDBObject()
            .append("firstName", "Curt")
            .append("lastName", "Schroener");
        System.out.println(obj2);
        atts.save(obj2);
        System.out.println(obj2);
        */
    }
}