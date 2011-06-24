import com.fourspaces.couchdb.*;

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        Session s = new Session("localhost",5984);
        Database db = s.getDatabase("test1");

        Document newdoc = new Document();
        newdoc.put("foo","baz"); // same as JSON: { foo: "baz"; }
        db.saveDocument(newdoc); // auto-generated id given by the database

        // Running a view
        ViewResults result = db.getAllDocuments(); // same as db.view("_all_dbs");
        for (Document d: result.getResults()) {
            System.out.println("ID: " + d.getId());

            /*
                    ViewResults may not actually contain the full document, only what the view
                    returned.  So, in order to get the full document, you need to request a
                    new copy from the database.
            */

            Document full = db.getDocument(d.getId());
            System.out.println("Full: " + full);
        }
        
        View v = new View("everything");
        ViewResults vr = db.view(v);
        for (Document d : vr.getResults())
        {
            System.out.println("Everything returned: " + d);
        }

        // Ad-Hoc view
        ViewResults resultAdHoc = db.adhoc("function (doc) { if (doc.foo=='bar') { return doc; }}");    
        for (Document d : resultAdHoc.getResults())
        {
            System.out.println("Ad-hoc result: " + d);
        }
    }
}