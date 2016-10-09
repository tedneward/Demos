import java.io.*;

public class SerRead
{
  public static void main(String[] args)
    throws Exception
  {
    FileInputStream fis = new FileInputStream("data.ser");
    ObjectInputStream ois = new ObjectInputStream(fis);
    Person m = (Person)ois.readObject();
    System.out.println(m);
  }
}