import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public class App
{
  public static void main(String[] args)
  {
    int tedsSalary = 1_000_000;
    System.out.println(tedsSalary);
    
    int thirdBitOn = 0b00000100;
    System.out.println(thirdBitOn);
    
    for (String s : args)
    {
      switch (s)
      {
        case "Yay!" : System.out.println("YAAAAAYYY!"); break;
        default: System.out.println("Meh"); break;
      }
    }
    
    Map<String, Integer> words = new HashMap<>();

    try
    {    
      Class appClass = App.class;
      Field notExist = appClass.getDeclaredField("foobar");
      notExist.set(null, 12);
    }
    catch (IllegalAccessException | NoSuchFieldException iae)
    {
      iae.printStackTrace();
      // What do I do now?
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

