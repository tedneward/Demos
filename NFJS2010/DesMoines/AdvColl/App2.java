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
                    
                    public String readLine()
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
            fr = new FileReader("App2.java");
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
            catch (IOException ioEx)
            {
                // WTF do I do now?!?!
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
            fr = new FileReader("App2.java");
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
            catch (IOException ioEx)
            {
                // WTF do I do now?!?!
                ioEx.printStackTrace();
            }
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
                    Random rand = new Random();
                    
                    public boolean hasNext() {
                        return true;
                    }
                    public Integer next() {
                        return rand.nextInt(max - min) + min + 1;
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
    public static <T> Iterable<T> take(final int n, final Iterable<T> src)
    {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return take(n, src.iterator());
            }
        };
    }                
    public static <T> Iterator<T> take(final int n, final Iterator<T> src)
    {
        return new Iterator<T>() {
            int count = 0;
            public boolean hasNext() {
                return ((src.hasNext()) && (count < n));
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
}

/*
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
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

public class App2 {
    public static void main(String[] args) 
        throws Exception
    {
        for (String s : SeqUtils.take(10, FileUtils.readFile("App2.java")))
            System.out.println(s);
            
        for (Integer i : SeqUtils.take(6, MathUtils.random(3,18)))
            System.out.println(i);
        
        //FileUtils.readFile("App2.java", new ActionFn<String>() {
        //    public void apply(String it) {
        //        System.out.println(it);
        //    }
        //});
    }
}













