import java.io.*;

public class App
{
    private static int xEh = 1000;
    public static void main(String[] args)
    {
        int x = 1_000_000;
        int firstBit = 0b00000001;
        
        System.out.println(x);
        
        for (String a : args)
        //String a = null;
        {
            switch (a)
            {
                case "one":  System.out.println("ONE"); break;
                case "two": System.out.println("TWO"); break;
                default:
                    System.out.println("Whatever");
            }
        }
        
        try
        {
            Class appClass = App.class;
            java.lang.reflect.Field xField = appClass.getField("x");
            xField.set(null, 12);
        }
        catch (IllegalAccessException | NoSuchFieldException | ClassCastException ex)
        {
            ex.printStackTrace();
        }
        
        try (FileReader fr = new FileReader("App.java"); 
             BufferedReader br = new BufferedReader(fr))
        {
            String line = null;
            while ((line = br.readLine()) != null)
                System.out.println(line);
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }
}




