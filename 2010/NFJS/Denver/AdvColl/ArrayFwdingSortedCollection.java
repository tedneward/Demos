import java.util.*;
import com.google.common.collect.*;

public class ArrayFwdingSortedCollection<E>
    extends ForwardingCollection<E>
    implements SortedCollection<E>
{
    private Collection<E> del = new ArrayList<E>();
    private Comparator<E> comparator = null;
    
    public FilterFn<E> predicate = null;

    public ArrayFwdingSortedCollection()
    {
    }
    public ArrayFwdingSortedCollection(Comparator<E> comp)
    {
        setComparator(comp);
    }
    public ArrayFwdingSortedCollection(FilterFn<E> pred)
    {
        predicate = pred;
    }
    
    public Comparator<E> getComparator() { return comparator; }
    public void setComparator(Comparator<E> comp) { comparator = comp; }
    
    public Collection<E> delegate()
    {
        return del;
    }
    
    public boolean add(E el) { 
        if (pred != null)
        {
            if (predicate.apply(el))
            {
                boolean result = delegate().add(el);
                sortThis();
                return result;
            }
        }
        return false;
    }
}









