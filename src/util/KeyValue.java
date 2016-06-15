package util;

public class KeyValue<K extends Comparable<K>, V>
	implements Comparable<KeyValue<K,V>>
{
	public final K key;
	public final V value;
	public KeyValue(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public int compareTo(KeyValue<K,V> cmp) {
		return key.compareTo(cmp.key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object cmp) {
		if (!(cmp instanceof KeyValue))
			return false;
		return this.compareTo((KeyValue<K,V>)cmp) == 0;
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
}
