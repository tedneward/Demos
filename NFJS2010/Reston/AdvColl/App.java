import java.io.*;
import java.util.*;

interface Action<T>
{
    public void apply(T it);
}

/*
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                public boolean hasNext() {
                    
                }
                public String next() {
                    
                }
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            }
        };
*/

class SeqUtils
{
    public static <T> Iterable<T> take(final int n, final Iterable<T> src)
    {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return SeqUtils.take(n, src.iterator());
            }
        };
    }
    public static <T> Iterator<T> take(final int n, final Iterator<T> src)
    {
        return new Iterator<T>() {
            int count = 0;
            public boolean hasNext() {
                return src.hasNext() && (count < n);
            }
            public T next() {
                ++count;
                return src.next();
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

class MathUtils
{
    public static Iterable<Integer> generateScore(final int min, final int max)
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
                    String readLine() {
                        try {
                            return br.readLine();
                        }
                        catch (IOException ioEx)
                        {
                            ioEx.printStackTrace();
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
    {
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null)
            {
                act.apply(line);
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
            catch (IOException ioEx2)
            {
                System.out.println("Java is the sux");
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
            catch (IOException ioEx2)
            {
                System.out.println("Java is the sux");
                ioEx2.printStackTrace();
            }
            return results;
        }
    }
}

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        //for (String line : FileUtils.readFile("App.java"))
        //    System.out.println(line);
        
        //FileUtils.readFile("App.java", new Action<String>() {
        //    public void apply(String it) { System.out.println(it); }
        //});

        for (String line : SeqUtils.take(10, FileUtils.readFile("App.java")))
            System.out.println(line);

        for (Integer score : SeqUtils.take(6, MathUtils.generateScore(3,18)))
            System.out.println(score);

        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Phuong", "Lai", 29),
            new Person("Steve", "Gorman", 43),
            new Person("Wei", "Chen", 29),
            new Person("Jeremy", "Purdy", 35),
            new Person("Sundar", "Smith", 31),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        
    }
}




