import java.util.*;

import com.google.common.collect.*;

class EventedCollection<T>
	extends ForwardingCollection<T>
{
	private Collection<T> goods = new ArrayList<T>();
	protected Collection<T> delegate()
	{
		return goods;
	}
	
	public Predicate<T> testAdd = new Predicate<T>() {
		public boolean test(Object item) {
			return true;
		}
	};
	public <R> Operation<R, T> afterAdd = new Operation<T, T>() {
		public T apply(T item) {
			return item;
		}
	};
	
	public boolean add(T obj)
	{
		if (testAdd.test(obj))
			return super.add(obj);
		else
			return false;
	}
	public boolean addAll(Collection<? extends T> colls)
	{
		for (T t : colls)
			if (testAdd.test(t))
				delegate().add(t);
		return true;
	}
}



public class App
{
	public static final Iterable<Integer> GENERATOR =
		new Iterable<Integer>() {
			public Iterator<Integer> iterator() {
				final Random r = new Random(0);
				return new Iterator<Integer>() {
					public boolean hasNext() { 
						return true; 
					}
					public Integer next() { 
						return r.nextInt(); 
					}
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	
 	public static final Function<Person> HAVE_BEER =
 		new Function<Person>() {
 			public void apply(Person p){
				System.out.println("Have a beer " + 
					p.getFirstName());
 			}
 		};
	public static void main(String[] args)
		throws Exception
	{
		List<Person> people = 
		    new ArrayList<Person>(Arrays.asList(
			new Person("Matt", "Noarmsnolegs", 29),
			new Person("Eric", "Asbury", 38),
			new Person("Wendy", "Chang", 25),
			new Person("Teresa", "Robisson", 26),
			new Person("Chris", "Hampton", 43),
			new Person("Randy", "Alexander", 30),
			new Person("Ted", "Neward", 38),
			null,
			new Person("Michael", "Neward", 16),
			new Person("Matthew", "Neward", 10),
			new Person("Sonny", null, 0),
			new Person("Cher", "", 0)
		));
		
		EventedCollection<Person> ec = new EventedCollection<Person>();
		
		final Predicate<Person> nullChecker = new Predicate<Person>() {
			public boolean test(Person i) {
				return i != null;
			}
		};
		final Predicate<Person> nameChecker = new Predicate<Person>() {
			public boolean test(Person i) {
				return (i.getFirstName() != null) && (i.getLastName() != null);
			}
		};

		Predicate<Person> compositePred = new Predicate<Person>() {
			public boolean test(Person i) {
				return nullChecker.test(i) && nameChecker.test(i);
			}
		};
		
		ec.testAdd = compositePred;
		ec.addAll(people);
		System.out.println(ec);
		
		/*
		FnUtils.map(
			FnUtils.filter(people, Person.IS_21), 
			App.HAVE_BEER);
		FnUtils.findFirst(people,
			new Predicate<Person>() {
				public boolean test(Person p) {
					return p.getLastName().equals("Smith");
				}
			}).toString();
		FnUtils.map(
			FnUtils.take(FnUtils.readLines("App.java"), 20),
			new Function<String>() {
				public void apply(String s) {
					System.out.println("==> " + s);
				}
			}
		);
		*/
		
		/*
		Iterable<Person> peopleIter = Iterables.cycle(people);
		for (Iterator<Person> i = peopleIter.iterator(); i.hasNext(); )
		{
			Person p = i.next();
			if (p.getAge() > 45)
			{
				i.remove();
			}
			else
			{
				p.setAge(p.getAge() + 1);
			}
			System.out.println(p);
		}
		*/
	}
}
