import java.util.*;

public class FilteredIterator<T>
    implements Iterator<T>
{
    public FilteredIterator(Iterator<T> iter, Predicate<T> pred) { 
        this.iter = iter; this.pred = pred;
    }
    public Iterator<T> iter;
    public Predicate<T> pred;
    public T it = null;
    public boolean hasNext() {
        while (iter.hasNext())
        {
            it = iter.next();
            if (pred.apply(it))
               return true; 
        }
        return false;
    }
    public T next() { 
        return it; 
    }
    public void remove() { throw new UnsupportedOperationException(); }
}
