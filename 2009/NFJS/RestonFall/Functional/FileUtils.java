import java.io.*;
import java.util.*;

public class FileUtils
{
  public static Iterable<String> readFile(String file)
    throws java.io.IOException
  {
    FileReader fr = new FileReader(file);
    final BufferedReader br = new BufferedReader(fr);
    return new Iterable<String>() {
      public Iterator<String> iterator() {
        return new Iterator<String>() {
          String data = readLine();
          public boolean hasNext() { return data != null; }
          public String next() { 
            String tbr = data;
            data = readLine();
            return tbr;
          }
          public void remove() { throw new UnsupportedOperationException(); }

          String readLine()
          {
            try { return br.readLine(); }
            catch (java.io.IOException ioEx) { ioEx.printStackTrace(); return null; }
          }
        };
      }
    };
  }

  public static List<String> naiveReadFile(String file) 
    throws java.io.IOException
  {
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);
    ArrayList<String> results = new ArrayList<String>();
    String data = br.readLine();
    while (data != null)
    {
      results.add(data);
      data = br.readLine();
    }
    return results;
  }
}
