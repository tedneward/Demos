import java.io.*;
import java.util.*;

/*
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    public boolean hasNext() {
                        
                    }
                    public String next() {
                        
                    }
                    public void remove() {
                        
                    }
                };
            }
        };
 */

class FileUtils
{
    public static Iterable<String> readFile(final String filename)
    {
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    FileReader fr = null;
                    BufferedReader br = null;

                    {
                        try {
                            fr = new FileReader(filename);
                            br = new BufferedReader(fr);
                        }
                        catch (IOException ioEx) {
                            ioEx.printStackTrace();
                        }
                    }
                    
                    String line = readLine();
                    
                    String readLine() {
                        try {
                            return br.readLine();
                        }
                        catch (IOException ioEx) {
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
    
    public static void readFile(String filename, ApplicationProc<String> exec)
    {
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            
            String line = null;
            while ((line = br.readLine()) != null)
                exec.apply(line);
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
            catch (IOException ioEx)
            {
                ioEx.printStackTrace();
            }
            
            return results;
        }
    }
}


class MathUtils {
    public static Iterable<Integer> rolls(final int min, final int max) {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    Random r = new Random();
                    
                    public boolean hasNext() {
                        return true;
                    }
                    public Integer next() {
                        return r.nextInt(max - min) + min + 1;
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}


class IterUtils {
    public static <T> Iterator<T> take(final int n, final Iterator<T> src) {
        return new Iterator<T>() {
            int count = 0;
            public boolean hasNext() {
                return (src.hasNext()) && (count < n);
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


public class App2 {
    public static void main(String[] args) {
        for (String line : IterUtils.take(10, FileUtils.readFile("App2.java")))
            System.out.println(line);
            
        for (Integer score : IterUtils.take(6, MathUtils.rolls(3,18)))
            System.out.println(score);
        
        //FileUtils.readFile("App2.java", new ApplicationProc<String>() {
        //    public void apply(String it) {
        //        System.out.println(it);
        //    }
        //});
    }
}




