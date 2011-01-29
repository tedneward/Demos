import java.io.*;
import java.util.*;

interface EventHandler<T>
{
	public boolean event(T item);
}

class EventedCollection<T>
	implements Collection<T>
{
	private Collection wrapped;
	public EventedCollection(Collection wrapped, EventHandler handler) {
		 this.wrapped = wrapped; 
		 this.handler = handler;
	 }
	
    public int size()
    {
    	return wrapped.size();
    }
    public boolean isEmpty()
    {
    	return wrapped.isEmpty();
    }
    public boolean contains(java.lang.Object);
    public java.util.Iterator iterator();
    public java.lang.Object[] toArray();
    public java.lang.Object[] toArray(java.lang.Object[]);
    public boolean add(java.lang.Object o)
    {
    	if (handler.event(o))
	    	return add(o);
    	else
    		return false;
    }
    public boolean remove(java.lang.Object);
    public boolean containsAll(java.util.Collection);
    public boolean addAll(java.util.Collection);
    public boolean removeAll(java.util.Collection);
    public boolean retainAll(java.util.Collection);
    public void clear();
}


class CollectionUtils
{
	public static <T> Collection<T> eventedCollection(Collection<T> wrapped, EventHandler<T> handler) {
		return new EventedCollection(wrapped, handler);
	}
	public static Iterable<String> readFile(final String filename)
		throws IOException 
	{
		FileReader fr = new FileReader(filename);
		final BufferedReader br = new BufferedReader(fr);
		return new Iterable<String>() {
			public Iterator<String> iterator() {
				return new Iterator<String>() {
					String line = getLine();
					
					private String getLine()
					{
						try { return br.readLine(); }
						catch (IOException ioEx) { ioEx.printStackTrace(); }
						return null;
					}
					public boolean hasNext() {
						return line != null;
					}
					public String next() {
						String oldLine = line;
						line = getLine();
						return oldLine;
					}
					public void remove() { throw new UnsupportedOperationException(); }
				};
			}
		};
	}

	public static <T> boolean safeAdd(Collection<T> coll, T toAdd)
	{ 
		try
		{
			coll.add(toAdd);
			return true;
		}
		catch (Exception x)
		{
			return false;
		}
	}
	
	public interface Action<T>
	{
		public void evaluate(T item);
	}
	public interface Predicate<T>
	{
		public boolean evaluate(T item);
	}
	
	public static <T> void iter(Collection<T> coll, Action<T> action)
	{
		for (T item : coll)
		{
			action.evaluate(item);
		}
	}
	public static <T> Collection<T> filter(Collection<T> coll, Predicate<T> pred)
	{
		Collection<T> results = new ArrayList<T>();
		if (coll.size() < 50)
		{
			for (T item : coll)
			{
				if (pred.evaluate(item))
					results.add(item);
			}
		}
		else
		{
			// grab the ExecutorService
			// spin up threads
			// partition work out to threads
			// add to results as necessary
		}
		return results;
	}
}

public class App
{
	public static final <T> Predicate<T> NOT_NULL = new Predicate<T>() {
		public boolean evaluate(T item) { return item != null; }
	};
	public static void main(String[] args) 
		throws Exception
	{
		for (String l : CollectionUtils.readFile("App.java"))
			System.out.println(l);
		
		List<Person> people = new ArrayList<Person>(Arrays.asList(
			new Person("Doug", "James", 29),
			new Person("Stephane", "Vaudandaine", 40),
			new Person("Dan", "C", 46),
			new Person("Jimmy", "Wang", 38),
			new Person("Elise", "Wallick", 29),
			new Person("Sachin", "Saindane", 29),
			new Person("Michael", "Neward", 16),
			new Person("Matthew", "Neward", 10)
		));
		System.out.println(people);
		people.add(new Person("Miene", "Lee", 35));
		System.out.println(people);
		
		Collection<Person> drinkers = 
		    CollectionUtils.filter(people, new CollectionUtils.Predicate<Person> () {
				public boolean evaluate(Person p) {
					return p.getAge() >= 21;
				}
		    });

		CollectionUtils.iter(drinkers, new CollectionUtils.Action<Person> () {
				public void evaluate(Person p) {
					System.out.println("Here, " + p.getFirstName() + " have a beer!");
				}
			});
			
		Collection<Person> allDrinkers = new CollectionUtils.eventedCollection(people,
			new EventHandler<Person> () {
				public boolean event(Person p) {
					return p.getAge() > 21;
				}
			});
		allDrinkers.add(new Person("Michael", "Neward", 16));
		
		CollectionUtils.filter(people, NOT_NULL);

		/*		
		
		Person el = people.get(4);
		System.out.println(el.getChildren());
		//el.getChildren().add(new Person("Billy Joe", "Wallick", 0));
		//System.out.println(el.getChildren());

		NavigableSet<Person> sba = new TreeSet<Person>(Person.BY_AGE);
		sba.addAll(people);
		SortedSet<Person> drinkers = sba.tailSet(new Person(null, null, 21));
		System.out.println(drinkers);
		*/
	}
}







