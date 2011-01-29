import java.io.*;
import java.util.*;

public class FnUtils
{
	public static List<String> naiveReadLines(String file)
		throws IOException
	{
		List<String> results = new ArrayList<String>();
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String line = null;
		while ((line = br.readLine()) != null)
		{
			results.add(line);
		}
		
		br.close();
		
		return results;
	}
	public static Iterable<String> readLines(String file)
		throws IOException
	{
		FileReader fir = new FileReader(file);
		final BufferedReader br = new BufferedReader(fir);
		return new Iterable<String>() {
			public Iterator<String> iterator() {
				return new Iterator<String>() {
					String line = readLine();
					
					private String readLine()
					{
						String line = null;
						try
						{
							line = br.readLine();
							if (line == null)
								br.close();
						}
						catch (IOException ioEx)
						{
							// TODO
						}
						return line;
					}
					
					public boolean hasNext() { 
						return line != null;
					}
					public String next() {
						String oldLine = line;
						line = readLine();
						return oldLine;
					}
					public void remove() { 
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}

	
	public static <T> Iterable<T> take(
		Iterable<T> i, 
		int n)	
	{
		Collection<T> results = new ArrayList<T>();
		Iterator<T> it = i.iterator();
		for (int j = 0; j < n; j++)
		{
			if (it.hasNext())
				results.add(it.next());
			else
				break;
		}
		return results;
	}
	public static <T> void map(Iterable<T> people,
		Function<T> operation)
	{
		for (T p : people)
		{
			operation.apply(p);
		}
	}
	public static <T> Option<T> findFirst(
		Iterable<T> coll,
		Predicate<T> predicate)
	{
		for (T p : coll)
		{
			if (predicate.test(p))
				return new Some(p);
		}
		return new None();
	}
	public static <T> Collection<T> filter(
		Iterable<T> people,
		Predicate<T> predicate)
	{
		Collection<T> results = new ArrayList<T>();
		for (T p : people)
		{
			if (predicate.test(p))
				results.add(p);
		}
		return results;
	}
}
