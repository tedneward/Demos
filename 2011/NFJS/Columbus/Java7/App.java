import java.lang.reflect.*;
import java.io.*;

public class App
{
    public static int X = 12;
    
    public static void main(String[] args)
    {
        int thirdBitOn = 0b00000100;
        System.out.println(thirdBitOn);
        int tedsSalary = 1_000_000;
        System.out.println(tedsSalary);
        
        for (String s : args)
        {
            switch (s)
            {
                case "one" : System.out.println("ONE!"); break;
                default: System.out.println("Meh."); break;
            }
        }
        
        java.util.Map<String, Integer> words = new java.util.HashMap<>();
        
        try
        {
            Class appClass = App.class;
            Field xField = appClass.getDeclaredField("Y");
            xField.set(null, 24);
        }
        catch (NoSuchFieldException | IllegalAccessException x) { // This should never happen
            x.printStackTrace();
        }
        
        try (FileReader fr = new FileReader("App.java");
             BufferedReader br = new BufferedReader(fr))
        {
            String line = null;
            while ((line = br.readLine()) != null)
                System.out.println(">> " + line);
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }
}
