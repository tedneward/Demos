import java.io.*;
import java.util.*;

interface ActionFn<T>
{
    public void f(T obj);
}

class SeqUtils
{
    public static <T> Iterable<T> take(final int n, final Iterable<T> it)
    {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return take(n, it.iterator());
            }
        };
    }
    public static <T> Iterator<T> take(final int n, final Iterator<T> it)
    {
        return new Iterator<T>() {
            int count = 0;
            public boolean hasNext() {
                if (count == n)
                    return false;
                else
                    return it.hasNext();
            }
            public T next() {
                count++;
                return it.next();
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

class FileUtils
{
    public static Iterable<Integer> RNG = new Iterable<Integer>()
        {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    Random r = new Random();
                    public boolean hasNext() {
                        return true;
                    }
                    public Integer next() {
                        return (r.nextInt(6) + 1) + 
                            (r.nextInt(6) + 1) +
                            (r.nextInt(6) + 1);
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    
    public static Iterable<String> readFile(final String filename)
        throws IOException
    {
        final FileReader fr = new FileReader(filename);
        final BufferedReader br = new BufferedReader(fr);
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    String line = readLine();
                    String readLine() {
                        try
                        {
                            String temp = br.readLine();
                            if (temp == null)
                            {
                                br.close();
                                fr.close();
                            }
                            return temp;
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
    
    public static void readFile(String filename, ActionFn<String> op)
    {
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null)
                op.f(line);
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
                fr.close();
            }
            catch (IOException ioEx)
            {
                ioEx.printStackTrace();
            }
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
            String line = "";
            while ((line = br.readLine()) != null)
                results.add(line);
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
                fr.close();
            }
            catch (IOException ioEx)
            {
                ioEx.printStackTrace();
            }
        }
        return results;
    }
}

public class App 
{ 
    public static void main(String[] args) 
        throws Throwable
    {
        /*
        FileUtils.readFile("App.java", new ActionFn<String>() {
            public void f(String line) {
                System.out.println(line);
            }
        });
        */
        for (String line : SeqUtils.take(10, FileUtils.readFile("App.java")))
            System.out.println(line);
            
        for (Integer score : SeqUtils.take(6, FileUtils.RNG))
            System.out.println(score);
            
        List<Integer> scores = 
            SeqUtils.bundle(SeqUtils.take(6, FileUtils.RNG));
    }
}



