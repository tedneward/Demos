public class Tuple3<T1,T2,T3>
    implements java.io.Serializable
{
    public Tuple3(T1 t1, T2 t2, T3 t3)
    {
        _1 = t1; _2 = t2; _3 = t3;
    }
    
    @SuppressWarnings("unchecked")
    public boolean equals(Object other)
    {
        if (other == this)
            return true;
        
        if (other instanceof Tuple3)
        {
            // This cast generates the "unchecked" warning
            Tuple3<T1,T2,T3> rhs = (Tuple3<T1,T2,T3>)other;
            
            return (
                (this._1.getClass().isAssignableFrom(rhs._1.getClass())) &&
                (this._2.getClass().isAssignableFrom(rhs._2.getClass())) &&
                (this._3.getClass().isAssignableFrom(rhs._3.getClass())) &&
                (this._1.equals(rhs._1)) &&
                (this._2.equals(rhs._2)) &&
                (this._3.equals(rhs._3))
            );
        }
        
        return false;
    }
    
    public int hashCode()
    {
        return _1.hashCode() & _2.hashCode() & _3.hashCode();
    }
    
    public String toString()
    {
        return "Tuple3<" + 
            _1.getClass().toString() + "," +
            _2.getClass().toString() + "," +
            _3.getClass().toString() + ">" +
            "(" + _1.toString() + "," +
            _2.toString() + "," +
            _3.toString() + ")";
    }
    
    public T1 _1;
    public T2 _2;
    public T3 _3;
}
