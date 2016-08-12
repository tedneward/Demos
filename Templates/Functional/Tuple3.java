public class Tuple2<T1,T2>
    implements java.io.Serializable
{
    public Tuple2(T1 t1, T2 t2)
    {
        _1 = t1; _2 = t2;
    }
 
    @SuppressWarnings("unchecked")
    public boolean equals(Object other)
    {
        if (other == this)
            return true;
        
        if (other instanceof Tuple2)
        {
            // This cast generates the "unchecked" warning
            Tuple2<T1,T2> rhs = (Tuple2<T1,T2>)other;
            
            return (
                (this._1.getClass().isAssignableFrom(rhs._1.getClass())) &&
                (this._2.getClass().isAssignableFrom(rhs._2.getClass())) &&
                (this._1.equals(rhs._1)) &&
                (this._2.equals(rhs._2))
            );
        }
        
        return false;
    }
    
    public int hashCode()
    {
        return _1.hashCode() & _2.hashCode();
    }
    
    public String toString()
    {
        return "Tuple2<>(" + 
            _1.toString() + "," +
            _2.toString() + ")";
    }
    
    public T1 _1;
    public T2 _2;
}