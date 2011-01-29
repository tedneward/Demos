import java.util.*;

public class FilteredIterable<T>
    implements Iterable<T>
{
    public Iterable<T> iterable;
    public Predicate<T> pred;
    public FilteredIterable(Iterable<T> iterable, Predicate<T> pred) {
        this.iterable = iterable; this.pred = pred;
    }
    public Iterator<T> iterator() { 
        return new FilteredIterator(iterable.iterator(), pred); 
    }
}
