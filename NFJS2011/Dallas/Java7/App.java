import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class App
{
  public static void main(String[] args)
  {
    int bigNumber = 10_000_000;
    int thirdBitOn = 0b00000100;
    System.out.println(bigNumber + " " + thirdBitOn);
    
    String msg = null;
    switch (String.valueOf(msg))
    {
      case "Howdy": System.out.println("We got a Howdy!"); break;
      case "null": System.out.println("Null"); break;
      //case null: System.out.println("Null"); break;
      default: System.out.println("Meh"); break;
    }
    
    Map<String, List<Integer>> words = new HashMap<>();
    
    try
    {
      Class appClass = App.class;
      Field xField = appClass.getDeclaredField("x");
      xField.set(null, 12);
    }
    catch (IllegalAccessException | NoSuchFieldException ex) { }

    try(FileReader fr = new FileReader("App.java");
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






