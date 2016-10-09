public class Tuple2<T1,T2>
	implements java.io.Serializable
{
	public Tuple2(T1 t1, T2 t2)
	{ this.t1value = t1; this.t2value = t2; }
	
	public final T1 t1value;
	public final T2 t2value;
	
	public String toString()
	{
		return "(" + t1value + "+" + t2value + ")";
	}
}
