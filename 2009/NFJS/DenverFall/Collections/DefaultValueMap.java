import java.util.*;

public class DefaultValueMap<K, V>
    extends com.google.common.collect.ForwardingMap<K, V>
{
    private Map<K, V> del = new HashMap<K,V>();
    private V defaultValue;

    public DefaultValueMap(Map<K, V> delegate, V dV) { this.defaultValue = dV; del = delegate; }    
    public DefaultValueMap(V dV) { this.defaultValue = dV; }
    
    public V get(Object key)
    {
        if (!delegate().containsKey(key))
            return defaultValue;
        else
            return delegate().get(key);
    }
    
    protected Map<K,V> delegate() { return del; }
    
    public static void main(String[] args)
    {
        DefaultValueMap<String, Person> people = 
            new DefaultValueMap<String, Person>(new Person("John", "Doe", 29));
        people.put("Ted", new Person("Ted", "Neward", 38));
        System.out.println(people.get("Ted"));
        
        System.out.println(people.get("Michael"));
    }
}