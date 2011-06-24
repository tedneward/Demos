import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class App
{
  public static void main(String[] args)
  {
    int bigNumber = 1_000_000;
    int thirdBitSet = 0b00000100;
    System.out.println(bigNumber);
    System.out.println(thirdBitSet);
    
    for (String s : args)
      switch (s)
      {
        case "Yay!": System.out.println("You're excited!"); break;
        default: System.out.println("Meh."); break;
      }

    Map<String, List<Integer>> wordPlacements = new HashMap<>();
    
    try
    {
      Class appClass = App.class;
      Field xField = appClass.getDeclaredField("x");
      xField.set(null, 12);
    }
    catch (IllegalAccessException | NoSuchFieldException ex)
    {
      ex.printStackTrace();
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

