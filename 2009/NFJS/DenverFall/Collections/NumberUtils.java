import java.util.*;

public class NumberUtils
{
    public static Iterable<Integer> random(int upTo)
    {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    Random r = new Random();
                    public boolean hasNext() { 
                        return true; 
                    }
                    public Integer next() {
                        return r.nextInt(upTo);
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}