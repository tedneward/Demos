import java.util.*;

public class CollUtils
{
    public static <T> Iterable<T> take(int n, Iterable<T> iterable)
    {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return take(n, iterable.iterator());
            }
        };
    }
    public static <T> Iterator<T> take(final int n, final Iterator<T> iterator)
    {
        return new Iterator<T>() {
            public int count = 0;
            public boolean hasNext() {
                return (count < n && iterator.hasNext());
            }
            public T next() {
                count++;
                return iterator.next();
            }
            public void remove() {
                iterator.remove();
            }
        };
    }
    
    public static <T> Option<List<T>> find(List<T> list, Predicate1<T> pred)
    {
        List<T> results = new ArrayList<T>(list.size());
        for (T t : list)
            if (pred.apply(t))
                results.add(t);
        return results.size() > 0 ? new Some<List<T>>(results) : new None<List<T>>();
    }
    public static <T> List<T> filter(List<T> list, Predicate1<T> pred)
    {
        List<T> results = new ArrayList<T>(list.size());
        for (T t : list)
            if (pred.apply(t))
                results.add(t);
        return results;
    }
    public static <T> void forall(List<T> list, Predicate1<T> pred, Procedure1<T> proc)
    {
        for (T t : list)
            if (pred.apply(t))
                proc.apply(t);
    }
    public static <T> void foreach(List<T> list, Procedure1<T> proc)
    {
        if (list.size() > 25)
        {
            // use parallel version
        }
        else
            for (T t : list)
                proc.apply(t);
    }
}