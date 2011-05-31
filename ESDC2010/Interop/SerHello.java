import java.io.*;

public class SerHello
{
  public static void main(String[] args)
    throws Exception
  {
    Person p = new Person("Michael", "Neward", 16);
    FileOutputStream fos = new FileOutputStream("data.ser");
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(p);
    oos.close();
    fos.close();
  }
}
