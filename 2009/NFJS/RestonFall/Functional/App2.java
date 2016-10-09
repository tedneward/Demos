import java.util.*;

public class App2
{
  public static void main(String[] args)
    throws Exception
  {
    for (String line : FileUtils.readFile("App2.java"))
      System.out.println(line);
  }
}