public class FlexiMap<K, V>
	implements Map 
{
	private Predicate<Tuple2<K, V>> onGet;
	private Predicate<Tuple2<K, V>> onPut;
	private HashMap<K,V> proxiedMap = new HashMap<K,V>();
	
    public FlexiMap(Predicate<Tuple2<K, V>> putfn, Predicate<Tuple2<K, V>> getfn) {
    	onGet = getfn;
    	onPut = putfn;
    }


    public V get(K key) {
    	if (onGet.test(key, null))
	        return proxiedMap.get(key);
        else
        	throw new UnsupportedOperationException();
    }

    public Object put(K key, V value) {
        V oldvalue = proxiedMap.get(key);
        onPut()
        proxiedMap.put(key, onPut.evaluate(oldvalue, value));
        return onGet.evaluate(key,oldvalue);
    }

       /*
        * We'll skip the remaining Map methods for now.
        */

        public void clear() {
            throw new UnsupportedOperationException("Left as an exercise for the reader.");
        }

        public boolean containsKey(K key) {
            throw new UnsupportedOperationException("Left as an exercise for the reader.");
        }

        public boolean containsValue(V value) {
            throw new UnsupportedOperationException("Left as an exercise for the reader.");
        }

        public Set<V> entrySet() {
            throw new UnsupportedOperationException("Left as an exercise for the reader.");
        }

        public boolean isEmpty() {
            throw new UnsupportedOperationException("Left as an exercise for the reader.");
        }

        public Set<K> keySet() {
            throw new UnsupportedOperationException("Left as an exercise for the reader.");
        }

        public void putAll(Map<K,V> t) {
            throw new UnsupportedOperationException("Left as an exercise for the reader.");
        }

        public Object remove(Object key) {
            throw new UnsupportedOperationException("Left as an exercise for the reader.");
        }

        public int size() {
            throw new UnsupportedOperationException("Left as an exercise for the reader.");
        }

        public Collection values() {
            throw new UnsupportedOperationException("Left as an exercise for the reader.");
        }
    }
