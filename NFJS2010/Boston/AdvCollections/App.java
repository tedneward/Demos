import java.io.*;
import java.util.*;

interface Action<T>
{
    public void apply(T it);
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
            int current = 0;
            public boolean hasNext() {
                if (current < n && src.hasNext())
                    return true;
                else
                    return false;
            }
            public T next() {
                current++;
                return src.next();
            }
            public void remove() { 
                throw new UnsupportedOperationException();
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

        return new Iterable<String>()
        {
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    String getNextLine()
                    {
                        String result = null;
                        try { result = br.readLine(); }
                        catch (Exception x) { x.printStackTrace(); }
                        return result;
                    }
                    
                    String line = getNextLine();
                    
                    public boolean hasNext() {
                        return line != null;
                    }
                    public String next() {
                        String temp = line;
                        line = getNextLine();
                        return temp;
                    }
                    public void remove() { 
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
    public static void functionalReadFile(
        String filename, Action<String> action)
        throws java.io.IOException
    {
        FileReader fr = null;
        BufferedReader br = null;

        try
        {
            
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String line = null;
            
            while ((line = br.readLine()) != null)
                action.apply(line);
        }
        finally
        {
            if (br != null)
                br.close();
            if (fr != null)
                fr.close();
        }
    }
}

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Matt", "M", 26),
            new Person("Rich", "W", 52),
            new Person("Will", "Gilbert", 57),
            new Person("Joanna", "Lu", 18),
            new Person("Martha", "B", 29),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10)
        ));
        //System.out.println(people);
        
        for (String s : SeqUtils.take(10, FileUtils.readFile("App.java")))
            System.out.println(s);
/*
        FileUtils.readFile("App.java", new Action<String>() {
            public void apply(String it) {
                System.out.println(it);
            }
        });
*/
    }
}










