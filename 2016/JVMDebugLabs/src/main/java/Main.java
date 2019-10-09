import java.io.FileNotFoundException;
import java.util.*;

public class Main {

  public static List<String> strList;

  public static void main(String... args) {
    System.out.println("Hello asserted world");

    int i = 1;
    assert args.length < 1;
    assert 1 + i == 2;

    strList = new ArrayList<String>();
    strList.add("Fred");

    Thread t = new Thread(() -> {
        throw new RuntimeException();
      });
    t.start();

    try {
      Class.forName("Nonexistent");

      if (false)
        throw new FileNotFoundException();
    }
    catch (ClassNotFoundException | FileNotFoundException eex) {
      eex.printStackTrace();
    }
    finally {
      System.out.println("Finally block");
    }
  }
}