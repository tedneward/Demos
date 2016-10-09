public class App
{
    public static void main(String[] args) {
        list = new ArrayList<String>();
        
        
        Connection conn = ...;
        ResultSet rs;
        try {
            rs = conn.execute("SELECT * FROM persons");
            while (rs.next())
            {
                // ...
            }
        }
        finally {
        rs.close();
        }
    }
    
    public static List<String> list;
}