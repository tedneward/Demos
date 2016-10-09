import java.io.*;
import java.util.*;


interface Action<T>
{
    public void f(T it);
}

class MathUtils
{
    public static Iterable<Integer> generateRandom(final int n)
    {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    Random r = new Random();
                    public boolean hasNext() {
                        return true;
                    }
                    public Integer next() {
                        return r.nextInt(n);
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

class FileUtils
{
    public static Iterable<String> readFile(String filename)
        throws IOException
    {
        FileReader fr = new FileReader(filename);
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
    
    public static void readFile(String filename, Action<String> act)
        throws IOException
    {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        
        try
        {
            String line = null;
            while ((line = br.readLine()) != null)
                act.f(line);
        }
        finally
        {
            br.close();
            fr.close();
        }
    }
    public static List<String> naiveReadFile(String filename)
        throws IOException
    {
        List<String> results= new ArrayList<String>();
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        
        try
        {
            String line = null;
            while ((line = br.readLine()) != null)
                results.add(line);
        }
        finally
        {
            br.close();
            fr.close();
        }
        return results;
    }
}

class SeqUtils
{
    public static <T> Iterable<T> take(final Iterable<T> src, final int n)
    {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return take(src.iterator(), n);
            }
        };
    }
    public static <T> Iterator<T> take(final Iterator<T> src, final int n)
    {
        return new Iterator<T>() {
            int i = 0;
            public boolean hasNext() {
                return ((i < n) && (src.hasNext()));
            }
            public T next() {
                ++i;
                return src.next();
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        /*
        FileUtils.readFile("App.java", new Action<String>() {
            public void f(String it) {
                System.out.println(it);
            }
        });
        */
        
        for (String line : SeqUtils.take(FileUtils.readFile("App.java"), 10))
            System.out.println(line);
            
        for (Integer roll : SeqUtils.take(MathUtils.generateRandom(20), 6))
            System.out.println(roll);
    }
}





