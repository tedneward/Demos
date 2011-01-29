import java.util.*;

interface AddFn<E>
{
    public E onAdd(E it);
}


public class FunList<E>
    implements List<E>
{
    private List<E> list;
    
    public FunList(List<E> src)
    {
        this.list = src;
    }
    public FunList(AddFn<E> adder)
    {
        this.onAdd = adder;
        this.list = new ArrayList<E>();
    }
    public FunList(AddFn<E> adder, List<E> src)
    {
        this.onAdd = adder;
        this.list = src;
    }

    public AddFn<E> onAdd =
        new AddFn<E>() {
            public E onAdd(E it) { 
                return it;
            }
        };
    
    public boolean add(E e)
    { return list.add(onAdd(e)); }


    
    public boolean addAll(Collection<? extends E> ec) 
    { boolean r = list.addAll(ec); return r; }
    public boolean remove(Object o)
    { boolean r = list.remove(o); return r; }
    public E remove(int o)
    { return list.remove(o); }
    public boolean removeAll(Collection<?> c)
    { boolean r = list.removeAll(c); return r; }
    public boolean retainAll(Collection<?> ec)
    { boolean r = list.retainAll(ec); return r; }
    
    public List<E> subList(int whoCares, int iDont)
    {
        throw new UnsupportedOperationException("Left as an exercise to the reader");
    }
    public ListIterator<E> listIterator()
    {
        throw new UnsupportedOperationException("Left as an exercise to the reader");
    }
    public ListIterator<E> listIterator(int whoGivesA)
    {
        throw new UnsupportedOperationException("Left as an exercise to the reader");
    }
    
    public int lastIndexOf(Object o)
    {
        throw new UnsupportedOperationException("Left as an exercise to the reader");
    }
    public int indexOf(Object eieio)
    {
        throw new UnsupportedOperationException("Left as an exercise to the reader");
    }
    
    
    public void clear() { list.clear(); }
    public boolean contains(Object o) { return list.contains(o); }
    public boolean containsAll(Collection <?> c) { return list.containsAll(c); }
    public boolean isEmpty() { return list.isEmpty(); }
    public Iterator<E> iterator() { return list.iterator(); }
    public int size() { return list.size(); }
    public Object[] toArray() { return list.toArray(); }
    public <T> T[] toArray(T[] a) { return list.toArray(a); }
    
    public String toString()
    {
        return list.toString();
    }
}