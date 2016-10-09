import java.util.*;

public class Seq
{
	public static <T> Iterable<T> take(final int n, Iterable<T> iter)
	{
		final Iterator<T> iterator = iter.iterator();
		
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					int count = 0;
					public boolean hasNext() { 
						return (((count++) < n) && iterator.hasNext() );
					}
					public T next() { 
						return iterator.next();
					}
					public void remove() { 
						throw new UnsupportedOperationException(); 
					}
				};
			}
		};
	}
	public static <T> Iterable<T> drop(final int n, Iterable<T> iter)
	{
		final Iterator<T> iterator = iter.iterator();
		
		for (int ct = n; ct > 0, ct--)
			if (iterator.hasNext())
				iterator.next();
		
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					public boolean hasNext() { 
						return iterator.hasNext();
					}
					public T next() { 
						return iterator.next();
					}
					public void remove() { 
						iterator.remove();
					}
				};
			}
		};
	}
}