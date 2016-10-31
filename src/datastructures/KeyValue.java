package datastructures;

public class KeyValue<K, V>
	implements Comparable<KeyValue<Comparable<K>,V>>
{
	public final K key;
	public final V value;
	public KeyValue(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public int compareTo(KeyValue<Comparable<K>,V> cmp) {
		return -1 * cmp.key.compareTo(key); 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object cmp) {
		if (!(cmp instanceof KeyValue))
			return false;
		return key.equals(((KeyValue<K,V>)cmp).key);
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
}
