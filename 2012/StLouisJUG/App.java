import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class App
{
  public static int tedsSalary = 1_000_000;
  
  public static void main(String[] args)
  {
    System.out.println(tedsSalary);
    for (String a : args)
    {
      switch (a)
      {
      case "Ken": System.out.println("MANAGER ALERT!"); break;
      default: System.out.println("Whew.");
      }

      Map<String, List<Integer>> wordPlacements = 
        new HashMap<>();
    }

    try {
    Class appClass = App.class;
    Field f = appClass.getDeclaredField("x");
    f.set(null, 12);
    }
    catch (IllegalAccessException | NoSuchFieldException nsfEx) {
      nsfEx.printStackTrace();
    }

    try (FileReader fr = new FileReader("App.java");
         BufferedReader br = new BufferedReader(fr))
    {
      String line = null;
      while ((line = br.readLine()) != null)
        System.out.println(">> " + line);
    }
    catch (IOException ioEx) { ioEx.printStackTrace(); }
  }
}






