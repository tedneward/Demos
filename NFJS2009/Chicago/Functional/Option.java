import java.util.*;

public abstract class Option<T>
	implements Iterable<T>
{
	public abstract T get();
	
	public static <T> boolean isSome(Option<T> o) { return o instanceof Some; }
	public static <T> boolean isNone(Option<T> o) { return o instanceof None; }
}