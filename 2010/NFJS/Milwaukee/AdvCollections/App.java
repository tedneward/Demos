import java.util.*;

class FileIterable
    implements Iterable<String>
{
    java.io.BufferedReader reader;
    public FileIterable(String file)
        throws Exception
    {
        reader = new java.io.BufferedReader(new java.io.FileReader(file));
    }
    
    public Iterator<String> iterator() {
        return new Iterator<String>()
        {
            String nextLine = read();
            String read()
            {
                try
                {
                    return reader.readLine();
                }
                catch (Exception ex)
                {
                    return null;
                }
            }
            public boolean hasNext() {
                return nextLine != null;
            }
            public String next() {
                String temp = nextLine;
                nextLine = read();
                return temp;
            }
            public void remove() { throw new UnsupportedOperationException(); }    
        };
    }
}


public class App
{
    public static void main(String[] args)
        throws Exception
    {
        Person ted = new Person("Ted", "Neward", 39,
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10),
            new Person("Tim", "Neward", 18));
        System.out.println(ted);

        System.out.println("Children: =====");
        for (Person kid : ted.getChildren())
            System.out.println(kid);

        // Find all the drivers
        System.out.println("Drivers: =====");
        for (Person oldKid : ted.getDrivingChildren())
            System.out.println(oldKid);

        for (String s : new FilteredIterable<String>(
                new FileIterable("App.java"), new Predicate<String>() {
                    public boolean apply(String it) {
                        return it.trim().length() > 0;
                    }
            }))
            System.out.println(s);
    }
}











