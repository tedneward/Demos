import java.util.*;
import com.mongodb.*;

public class App
{
    static Mongo m = null;
    static DB db = null;
    static DBCollection users = null;
    
    public static void main(String... args)
        throws Exception
    {
        m = new Mongo();
        showDatabases();
        
        db = m.getDB("attendees");
        
        showCollections();
        
        users = db.getCollection("persons");
        
        displayPersons();
        
        //addPerson("Sonya");
        //displayPersons();
        
        //removePerson("Sonya");
        //displayPersons();
        
        //feedbackOnPerson("Sonya");
        //displayPersons();
        
        blindUpdate("Sonya");
        displayPersons();  
    }
    
    public static void showDatabases()
    {
        for (String db : m.getDatabaseNames())
            System.out.println(db);
    }

    public static void displayPersons()
    {
        System.out.println("Persons:");
        for (DBObject obj : users.find())
            System.out.println("Found: " + obj);
    }
    
    public static void addPerson(String name)
    {
        System.out.println("Adding " + name);
        
        DBObject person = new BasicDBObject();
        person.put("firstname", name);
        
        users.insert(person);
        
        System.out.println("Done");
    }
    public static void blindUpdate(String name)
    {
        BasicDBObject query = new BasicDBObject();
        query.put("firstname", name);

        DBObject person = new BasicDBObject();
        person.put("lastname", "Thompson");
        
        users.update(query, person);
    }
    
    public static void removePerson(String name)
    {
        
        DBObject person = users.findOne();
        System.out.println("Removing " + person);
        
        users.remove(person);
    }
    public static void ageifyPerson(String name, int age)
    {
        BasicDBObject query = new BasicDBObject();
        query.put("firstname", name);
        DBObject person = users.findOne(query);
        System.out.print("Ageifying " + person);
        person.put("age", age);
        System.out.println(" to " + person);
        users.update(query, person);
    }
    public static void feedbackOnPerson(String name)
    {
        BasicDBObject query = new BasicDBObject();
        query.put("firstname", name);
        DBObject person = users.findOne(query);
        Date d = new Date();
        BasicDBObject feedback = new BasicDBObject();
        feedback.put("date", d);
        feedback.put("text", "She was a great attendee. Gave me high marks.");
        person.put("feedback", feedback);
        users.update(query, person);
    }
    
    
    public static void showCollections()
    {
        System.out.println("Collections: ");
        for (String coll : db.getCollectionNames())
            System.out.println(coll);
    }
}

