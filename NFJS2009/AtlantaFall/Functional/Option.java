import java.util.*;

public abstract class Option<T> 
	implements Iterable<T>
{
	protected Option() { }
	
	public static boolean isSome(Option o) { 
		return o instanceof Some;
	}	
	public static boolean isNone(Option o) { 
		return o instanceof None;
	}
	
	public abstract T get();
}
