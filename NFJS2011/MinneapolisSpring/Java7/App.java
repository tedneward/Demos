import java.lang.reflect.*;
import java.io.*;
import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        int reallyBigNumber = 1_000_000;
        System.out.println(reallyBigNumber);
            
        for (String a : args)
        {
            switch (a)
            {
                case "Yaaay!": System.out.println("Yep, it's a string switch"); break;
                default: System.out.println("Uhh...."); break;
            }
        }

        try
        {
            Class appClass = App.class;
            Field nsf = appClass.getField("NONE");
            nsf.setInt(null, 24);
        }
        catch (IllegalAccessException | NoSuchFieldException x)
        {
            x.printStackTrace();
        }
        
        Map<String, List<String>> families = new HashMap<>();

        try (FileReader fr = new FileReader("App.java");
             BufferedReader br = new BufferedReader(fr))
        {
            String line = null;
            while ((line = br.readLine()) != null)
                System.out.println("> " + line);
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        /*
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader("App.java");
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null)
                System.out.println("> " + line);
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            }
            catch (IOException ioEx)
            {
                // WTF?!??!?!
            }
        }
        */
    }
}