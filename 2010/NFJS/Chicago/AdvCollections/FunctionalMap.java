import java.util.*;

interface GetFn<K,V>
{
    public V apply(K k, V v);
}

interface PutFn<K, V>
{
    public V apply(K k, V oldv, V newv);
}

public class FunctionalMap<K,V>
    implements Map<K,V>
{
    private GetFn<K,V> onGet = new GetFn<K,V>() {
        public V apply(K key, V value) {
            return value;
        }
    };
    private PutFn<K,V> onPut = new PutFn<K,V>() {
        public V apply(K key, V oldvalue, V newvalue) {
            return newvalue;
        }
    };
    private Map<K,V> proxiedMap;
    
    public FunctionalMap(Map<K,V> proxy, GetFn<K,V> getfn, PutFn<K,V> putfn)
    {
        proxiedMap = proxy;
        if (getfn != null) onGet = getfn;
        if (putfn != null) onPut = putfn;
    }

    public V get(Object key) 
    {
        return onGet.apply( (K)key, proxiedMap.get(key) );
    }
    public V put(K key, V value) 
    {
        V oldvalue = proxiedMap.get(key);
        proxiedMap.put(key, onPut.apply(key, oldvalue, value));
        return onGet.apply(key,oldvalue);
    }
    
    
    public int size()
    {
        return proxiedMap.size();
    }
    public boolean isEmpty()
    {
        return proxiedMap.isEmpty();
    }
    public boolean containsKey(Object key) //(K key)
    {
        return proxiedMap.containsKey(key);
    }
    public boolean containsValue(Object value) //(V value)
    {
        return proxiedMap.containsValue(value);
    }
    public V remove(Object key)
    {
        return proxiedMap.remove(key);
    }
    public void putAll(Map<? extends K, ? extends V> map)
    {
        throw new UnsupportedOperationException("Left as an exercise for the reader.");
    }
    public void clear()
    {
        proxiedMap.clear();
    }
    public Set<K> keySet()
    {
        return proxiedMap.keySet();
    }
    public Collection<V> values()
    {
        return proxiedMap.values();
    }
    public Set<Map.Entry<K,V>> entrySet()
    {
        return proxiedMap.entrySet();
    }
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        
        if (obj instanceof FunctionalMap)
        {
            FunctionalMap other = (FunctionalMap)obj;
            return other.proxiedMap.equals(this.proxiedMap);
        }
        return false;
    }
    public int hashCode()
    {
        return proxiedMap.hashCode();
    }
        
        
    public static void main(String[] args)
    {
        Map<String, String> personMap = 
            new FunctionalMap<String,String>(
                new HashMap<String,String>(),
                new GetFn<String,String>() {
                    public String apply(String key, String value) {
                        if (value == null)
                            return "(John Doe)";
                        else
                            return value;
                    }
                },
                null
            );

        String key1 = "Key1";
        String value1 = "Value1";
        personMap.put(key1, value1);
        System.out.println("Get " + key1 + " = " + personMap.get(key1));
        System.out.println("Get unmapped = " + personMap.get("unmapped"));
        
        Map<String, String> notNullMap =
            new FunctionalMap<String,String>(
                new HashMap<String,String>(),
                null,
                new PutFn<String,String>() {
                    public String apply(String key, String oldV, String newV) {
                        if (newV == null)
                            return "<<null>>";
                        else
                            return newV;
                    }
                }
            );

        notNullMap.put(key1, value1);
        System.out.println("Get " + key1 + " = " + notNullMap.get(key1));
        notNullMap.put("nullValue", null);
        System.out.println("Get nullValue = " + notNullMap.get("nullValue"));
    }
}






