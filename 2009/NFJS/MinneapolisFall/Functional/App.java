import java.io.*;
import java.util.*;

interface Action<T>
{
	public void action(T o);
}
interface Predicate<T>
{
	public boolean action(T o);
}

class Tuple2<T1,T2>
	implements java.io.Serializable
{
	public Tuple2(T1 t1, T2 t2)
	{ this.t1value = t1; this.t2value = t2; }
	
	public T1 t1value;
	public T2 t2value;
	
	public String toString()
	{
		return "(" + t1value + "+" + t2value + ")";
	}
}


class FunctionUtils
{
	
	/*	
	public abstract class Option<T>
	{
		Option<T>() { }
		
		public static boolean isSome(Option<T> opt) { ... }
		public static boolean isNone(Option<T> opt) { ... }
		
		public abstract T get();
	}
	public final class Some<T> extends Option<T>
	{
		public Some(T value) { this.value = value; }
		
		public String toString() { return "Some:" + value; }
		
		public T get() { return value; }
		
		private T value;
	}
	public final class None<T> extends Option<T>
	{
		public None() { }
		
		public String toString() { return "None"; }
	}
	*/
	
	
	public static <T> List<T> Filter(List<T> list, Predicate<T> pred)
	{
		List<T> results = new ArrayList<T>();
		for (T t : list)
		{
			if (pred.action(t))
				results.add(t);
		}
		return results;
	}
	
	/*
	public static <T> Option<T> Find(List<T> list, Predicate<T> pred)
	{
		Option<T> result;
		for (T t : list)
		{
			if (pred.action(t))
				result = new Some(t);
		}
		if (result == null)
			result = new None();
		return result;
	}
	public static <T> Tuple2<Boolean, T> Find(List<T> list, Predicate<T> pred)
	{
		T result;
		for (T t : list)
		{
			if (pred.action(t))
				result = new Tuple2(true, t);
		}
		if (result == null)
			result = new Tuple2(false, null);
		return result;
	}
	*/
	
	
	
	public static <T> void Do(List<T> list, Action<T> action)
	{
		for (T t : list)
		{
			action.action(t);
		}
	}
	public static <T> void ParallelDo(List<T> list, Action<T> action)
	{
		for (T t : list)
		{
			// ThreadPool-aware execution of action(t)
		}
	}
	
	public static <T> List<T> take(Iterable<T> iter, int n)
	{
		return take(iter.iterator(), n);
	}
	public static <T> List<T> take(Iterator<T> iter, int n)
	{
		List<T> results = new ArrayList<T>();
		for (int i = 0; i<n; i++)
			results.add(iter.next());
		return results;
	}
}

class FileUtils
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
		FileReader fr = new FileReader(file);
		final BufferedReader br = new BufferedReader(fr);
		return new Iterable<String>() {
			public Iterator<String> iterator()
			{
				return new Iterator<String>() {
					String line = getLine();
					private String getLine() {
						try { return br.readLine(); }
						catch (IOException ioEx) { return null; }
					}
					public boolean hasNext() {
						return line != null;
					}
					public String next() {
						String result = line;
						line = getLine();
						return result;
					}
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
}

public class App
{
	public static List<String> getNames()
	{
		return Arrays.asList("Neal", "Mark", "Venkat", "Nate", "Jay");
	}
	
	public static void main(String[] args)
		throws Exception
	{
		for (String line : FunctionUtils.take(FileUtils.readLines("App.java"), 10))
			System.out.println("==> " + line);
		
		List<String> names = getNames();
		FunctionUtils.Do(names, new Action<String>() {
			public void action(String n) {
				System.out.println(n);
			}
		});
	}
}















