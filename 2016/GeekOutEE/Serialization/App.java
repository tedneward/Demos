import java.io.*;

public class App {
  public static void main(String... args) throws Exception {
    Person p = new Person("Viktor", "Gamov", 31);
    System.out.println(p);
    
    try(FileOutputStream fos = new FileOutputStream("viktor.ser")) {
      try(ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(p);
      }
    }
    
    try(FileInputStream fis = new FileInputStream("viktor.ser")) {
      try(ObjectInputStream ois = new ObjectInputStream(fis)) {
        Person clone = (Person)ois.readObject();
        System.out.println(clone);
      }
    }
  }
}