public class Tuple2<T1, T2>
	implements java.io.Serializable
{
	public Tuple2(T1 t1, T2 t2) { this.t1 = t1; this.t2 = t2; }
	
	public String toString() { return "(" + t1 + "," + t2 + ")"; }
	
	public final T1 t1;
	public final T2 t2;
}