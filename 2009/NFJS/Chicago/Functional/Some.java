import java.util.*;

public final class Some<T> extends Option<T>
{
	public Some(T val) { value = val; }
	
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			boolean next = true;
			public boolean hasNext() { return next;}
			public T next() { 
				if (next)
				{
					next = false;
					return value;
				}
				else
					throw new UnsupportedOperationException();
			}
			public void remove() { throw new UnsupportedOperationException(); }
		};
	}
	
	public T get() { return value; }
	
	public String toString() { return "Some(" + value + ")"; }
	
	private final T value;
}