/*
import com.google.common.collect.*;

public class FwdedSortedCollection<E>
    extends ForwardingCollection<E>
{
    private Collection<E> src;
    
    public FwdedSortedCollection(Collection<E> src)
    {
        this.src = src;
    }
    
    public boolean add(E e)
    { boolean r = super.add(e); sortThis(); return r; }
    public boolean addAll(Collection<? extends E> ec) 
    { boolean r = super.addAll(ec); sortThis(); return r; }
    
    protected Collection<E> delegate() { return src; }
}
*/