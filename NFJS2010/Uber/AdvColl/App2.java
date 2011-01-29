import java.io.*;
import java.util.*;

class FileUtils
{
    public static Iterable<String> readFile(String filename)
        throws IOException
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
                    
                    public String next() {
                        String temp = line;
                        line = readLine();
                        return temp;
                    }
                    public boolean hasNext() {
                        return line != null;
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
    public static void readFile(String filename, ActionFn<String> act)
        throws IOException
    {
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            
            String line = "";
            while ((line = br.readLine()) != null)
                act.apply(line);
        }
        finally
        {
            if (br != null) br.close();
            if (fr != null) fr.close();
        }
    }
    
    public static List<String> naiveReadFile(String filename)
        throws IOException
    {
        List<String> results = new ArrayList<String>();
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            
            String line = "";
            while ((line = br.readLine()) != null)
                results.add(line);
        }
        finally
        {
            if (br != null) br.close();
            if (fr != null) fr.close();
        }
        return results;
    }
}

class MathUtils
{
    public static Iterable<Integer> random(final int min, final int max)
    {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    Random r = new Random();
                    public boolean hasNext() {
                        return true;
                    }
                    public Integer next() {
                        return r.nextInt(max - min) + min;
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

class SeqUtils
{
    public static <T> Iterator<T> take(final int n, final Iterator<T> src)
    {
        return new Iterator<T>() {
            int count = 0;
            public boolean hasNext() {
                return src.hasNext() && (count < n);
            }
            public T next() {
                count++;
                return src.next();
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    public static <T> Iterable<T> take(final int n, final Iterable<T> src)
    {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return take(n, src.iterator());
            }
        };
    }
    
}

public class App2
{
    public static void main(String[] args)
        throws IOException
    {
        //for (String line : FileUtils.naiveReadFile("App2.java"))
        //    System.out.println(line);
        //FileUtils.readFile("App2.java", new ActionFn<String>() {
        //    public void apply(String it) { System.out.println(it); }
        //});
        for (String line : SeqUtils.take(6, FileUtils.readFile("App2.java")))
            System.out.println(line);
            
        for (Integer score : SeqUtils.take(6, MathUtils.random(3, 18)))
            System.out.println(score);
    }
}





