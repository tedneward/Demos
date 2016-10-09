import java.util.*;

public class None<T> extends Option<T>
{
	public None() { }
	
	public T get() { throw new UnsupportedOperationException(); }

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			public boolean hasNext() { return false;}
			public T next() { throw new UnsupportedOperationException(); }
			public void remove() { throw new UnsupportedOperationException(); }
		};
	}
	
	public String toString() { return "None"; }
}