import java.io.*;
import java.util.*;

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
}

public class App2 {
    public static void main(String[] args) {
        for (String line : SeqUtils.take(5, FileUtils.readFile("App2.java")))
            System.out.println(line);
            
        for (Integer score : SeqUtils.take(6, MathUtils.random(3, 18)))
            System.out.println(score);
        
        /*
        FileUtils.readFile("App2.java", new ActionFn<String>() {
            public void apply(String line) {
                System.out.println(line);
            }
        });
        */
    }
}
