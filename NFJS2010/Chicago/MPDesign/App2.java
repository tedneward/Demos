import java.io.*;
import java.util.*;

class Tuple2<T1,T2>
    implements java.io.Serializable, Iterable<Object>
{
    public Tuple2(T1 v1, T2 v2) { _1 = v1; _2 = v2; }
    
    final public T1 _1;
    final public T2 _2;
    
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            int count = 0;
            public boolean hasNext() {
                return count < 2;
            }
            public Object next() {
                switch(count++) {
                    case 0: return _1;
                    case 1: return _2;
                    default: throw new RuntimeException("");
                }
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

public class App2 
{
    public static void main(String[] args) 
        throws Throwable
    {
        for (String line : SeqUtils.take(10, FileUtils.readFile("App2.java")))
            System.out.println(line);
            
        for (Integer score : SeqUtils.take(6,MathUtils.random(3,18)))
            System.out.println(score);
    }
}

/*
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    public boolean hasNext() {
                        
                    }
                    public String next() {
                        
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
 */
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
    public static void readFile(String filename, ActionFn<String> act)
    {
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            
            String line = null;
            while ((line = br.readLine()) != null)
                act.apply(line);
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            }
            catch (IOException ioEx2)
            {
                ioEx2.printStackTrace();
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
            
            String line = null;
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
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            }
            catch (IOException ioEx2)
            {
                ioEx2.printStackTrace();
            }
            
            return results;
        }
    }
}

class MathUtils
{
    public static Iterable<Integer> random(final int min, final int max) {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    Random random = new Random();
                    public boolean hasNext() {
                        return true;
                    }
                    public Integer next() {
                        return random.nextInt(max - min) + min + 1;
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
    public static <T> Iterator<T> take(final int n, final Iterator<T> src) {
        return new Iterator<T>() {
            int count = 0;
            public boolean hasNext() {
                return (src.hasNext()) && count < n;
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
    public static <T> Iterable<T> take(final int n, final Iterable<T> src) {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return take(n, src.iterator());
            }
        };
    }
}








