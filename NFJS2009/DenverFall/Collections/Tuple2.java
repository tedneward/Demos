public class Tuple2<T1, T2>
    implements java.io.Serializable
{
    public Tuple2(T1 t1, T2 t2)
    { this._1 = t1; this._2 = t2; }
    
    public String toString()
    {
        return "(" + _1 + "," + _2 + ")";
    }
    
    public final T1 _1;
    public final T2 _2;
}