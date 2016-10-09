import java.util.*;

public class Lists
{
	public static <T> Option<T> find(List<T> src, Predicate<T> pred)
	{
		for (T t : src)
			if (pred.test(t))
				return new Some(t);
		return new None();
	}
	
	
	public static <T> List<T> cons(List<T> src, T obj)
	{
		List<T> results = new ArrayList<T>(src);
		results.add(obj);
		return results;
	}
	
	public static <T> List<T> filter(List<T> src, Predicate<T> pred)
	{
		List<T> results = new ArrayList<T>(src.size());
		if (src.size() > 100)
		{
			// do parallel implementation
		}
		else
		{
			for (T t : src)
				if (pred.test(t))
					results.add(t);
		}		
		return results;
	}

	public static <T> Iterable<T> filter(Iterable<T> src, Predicate<T> pred)
	{
		List<T> results = new ArrayList<T>();
		for (T t : src)
			if (pred.test(t))
				results.add(t);
		return results;
	}
	
	public static <T> void map(List<T> src, Procedure<T> appFn)
	{
		for (T t : src)
			appFn.apply(t);
	}
	
	public static <T,U> List<U> transform(List<T> src, Function<U, T> xformFn)
	{
		List<U> results = new ArrayList<U>(src.size());
		for (T t : src)
			results.add(xformFn.apply(t));
		return results;
	}
}







