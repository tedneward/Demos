import java.io.*;
import java.util.*;

public class FileUtils
{
    public static Iterable<String> readFile(final String filename)
    {
        try
        {
            final FileReader fr = new FileReader(filename);
            final BufferedReader br = new BufferedReader(fr);
            return new Iterable<String>() {
                public Iterator<String> iterator() {
                    return new Iterator<String>() {
                        String line = readLine();
                        String readLine()
                        {
                            try
                            {
                                return br.readLine();
                            }
                            catch (IOException ioEx)
                            {
                                return null;
                            }
                        }
                        public boolean hasNext() {
                            return line != null;
                        }
                        public String next() {
                            String temp = line;
                            line = readLine();
                            return temp;
                        }
                        public void remove() {
                            throw new UnsupportedOperationException();
                        }
                    };
                }
            };
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
            return null;
        }
    }

    public static List<String> naiveReadFile(String filename)
    {
        List<String> results = new ArrayList<String>();
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null)
            {
                results.add(line);
            }
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                if (br != null) br.close();
                if (fr != null) fr.close();
            }
            catch (IOException ioEx)
            {
                // WTF?!?
            }
            
            return results;
        }
    }
}