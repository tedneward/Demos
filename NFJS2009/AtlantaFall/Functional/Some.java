import java.util.*;

public final class Some<T> extends Option<T>
{
	public Some(T value) { this.value = value; }
	
	public T get() { return this.value; }
	
	public Iterator<T> iterator() {
		return Arrays.asList(value).iterator();
	}
	
	public String toString() {
		return "[Option: " + value + "]";
	}
	
	private final T value;
}
