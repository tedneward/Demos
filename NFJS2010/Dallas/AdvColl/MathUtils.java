import java.util.*;

public class MathUtils
{
    private static Random rand = new Random();
    public static Iterable<Integer> random(final int min, final int max)
    {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    public boolean hasNext() {
                        return true;
                    }
                    public Integer next() {
                        return rand.nextInt(max - min) + min;
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}