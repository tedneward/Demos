// new File("filename").iter { | it | puts it }

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
                            // Blame that guy in the room
                            try
                            {
                                if (br != null)
                                    br.close();
                                
                                if (fr != null)
                                    fr.close();
                            }
                            catch (IOException ioEx2)
                            {
                                // TODO
                            }
                            
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

    public static void readFile(String filename, ActionFn<String> actionFn)
        throws IOException
    {
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null)
                actionFn.apply(line);
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
                // WTF do I do now?!??!?
            }
        }
    }

    public static List<String> naiveReadFile(String filename)
    {
        FileReader fr = null;
        BufferedReader br = null;
        List<String> results = new ArrayList<String>();
        try
        {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null)
                results.add(line);
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
                // WTF do I do now?!??!?
            }
            
            return results;
        }
    }
}

class MathUtils
{
    public static Iterable<Integer> diceRolls(final int min, final int max)
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

class IteraUtils
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
                return (count < n) && src.hasNext();
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


public class App2
{
    public static void main(String[] args)
        throws Exception
    {
        for (String line : IteraUtils.take(6, FileUtils.readFile("App2.java")))
            System.out.println(line);

        for (Integer num : IteraUtils.take(6, MathUtils.diceRolls(3, 18)))
            System.out.println(num);
    }
}







