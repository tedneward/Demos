import java.util.*;

public class MathUtils
{
    public static Iterable<Integer> random(final int max) {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                	Random r = new Random();
                    public boolean hasNext() {
                        return true;
                    }
                    public Integer next() {
                        return r.nextInt(max);
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
	
    public static Iterable<Integer> factorial(final int to) {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    public boolean hasNext() {
                        return current <= to;
                    }
                    public Integer next() {
                        if (current == 1)
                            accum = 1;
                        else
                            accum *= current;
                        current++;
                        return accum;
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                    private int current = 1;
                    private int accum = 0;
                };
            }
        };
    }

    public static Iterable<Integer> factorial() {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    public boolean hasNext() {
                        return true;
                    }
                    public Integer next() {
                        if (current == 1)
                            accum = 1;
                        else
                            accum *= current;
                        current++;
                        return accum;
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                    private int current = 1;
                    private int accum = 1;
                };
            }
        };
    }
}