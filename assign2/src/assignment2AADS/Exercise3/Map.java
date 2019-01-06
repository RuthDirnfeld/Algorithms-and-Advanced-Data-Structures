package assignment2AADS.Exercise3;

public interface Map<K, V> {

	public void put(K key, V value);
	
	public boolean containsKey(K key);

	public V get(K key);	
}
