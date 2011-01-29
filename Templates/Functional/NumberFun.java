public class NumberFun
{
	public static Iterator<Integer> factorial(int upTo)
	{
		return factorial(upTo).iterator();
	}
	
	public static Iterable<Integer> factorial(int upTo)
	{
		return new Iterable<Integer>() {
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>() {
					public boolean hasNext() { 
						
					}
					public Integer next() { 
						
					}
					public void remove() { throw new UnsupportedOperationException(); }
				};
			}
		};
	}
}