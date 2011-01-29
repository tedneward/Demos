import java.io.*;
import java.util.*;

interface Action<T> { public void act(T it); }


/*
                return new Iterator<String>() {
                    public boolean hasNext() {
                        
                    }
                    public String next() {
                        
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };

 */

class Tuple1<T1>
    implements Iterable<Object>
{
    public Tuple1(T1 v1) { this.v1 = v1; }
    
    public T1 v1;
    
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            int count = 0; int max = 1;
            public boolean hasNext() {
                return count < max;
            }
            public Object next() {
                count++;
                switch(count)
                {
                    case 0: return v1;
                    default: throw new IllegalArgumentException();
                }
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }
}
class Tuple2<T1,T2>
{
    public Tuple2(T1 v1, T2 v2) { this.v1 = v1; this.v2 = v2;}
    
    public T1 v1;
    public T2 v2;
}
class Tuple3<T1,T2,T3>
{
    public Tuple3(T1 v1, T2 v2, T3 v3) { 
        this.v1 = v1; this.v2 = v2; this.v3 = v3;
    }
    
    public T1 v1;
    public T2 v2;
    public T3 v3;
}



class SeqUtils
{
    public static <T> Iterator<T> take(final int num, final Iterator<T> src)
    {
        return new Iterator<T>() {
            int count = 0;
            public boolean hasNext() {
                return src.hasNext() && (count < num);
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
    public static <T> Iterable<T> take(final int num, final Iterable<T> src)
    {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return SeqUtils.take(num, src.iterator());
            }
        };
    }
}

class MathUtils
{
    public static Iterable<Integer> infiniteList(final int start)
    {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    int count = start;
                    public boolean hasNext() {
                        return true;
                    }
                    public Integer next() {
                        return count++;
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
        final FileReader fr = new FileReader(filename);
        final BufferedReader br = new BufferedReader(fr);
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    String line = readLine();
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
                    String readLine() {
                        try
                        {
                            return br.readLine();
                        }
                        catch (IOException ioEx)
                        {
                            return null;
                        }
                    }
                };
            }
        };
    }
    public static void readFile(String filename, Action<String> action)
    {
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader("App.java");
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null)
            {
                action.act(line);
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
                if (br != null)
                    br.close();
                if (fr != null)
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
            fr = new FileReader("App.java");
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
                if (br != null)
                    br.close();
                if (fr != null)
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

public class App {
    public static void main(String[] args) 
        throws Exception
    {
        for (String line : SeqUtils.take(10, FileUtils.readFile("App.java")))
            System.out.println(line);

        /*        
        FileUtils.readFile("App.java", new Action<String>() {
            public void act(String it) {
                System.out.println(it);
            }
        });
        */
        
        for (int i : SeqUtils.take(5, MathUtils.infiniteList(2)))
        {
            System.out.println(i);
        }
    }
}










