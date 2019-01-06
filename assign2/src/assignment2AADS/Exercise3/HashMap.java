package assignment2AADS.Exercise3;

public class HashMap<K, V> implements Map<K, V> {
	private static final int DEFAULT_SIZE = 11;
	private HashMapEntry<K, V>[] table;
	private int size;

	public HashMap() {
		this(DEFAULT_SIZE);
	}

	@SuppressWarnings("unchecked")
	public HashMap(int tableSize) {
		table = new HashMapEntry[tableSize];
		size = 0;
	}

	/**
	 * Links the specific value with the given key in the map. If the key is
	 * already in the map, the old value is replaced by the new value.
	 * @param key - search for the key
	 * @param value - replace the old value for new
	 */
	public void put(K key, V value) {
		int keyBucket = hash(key);

		HashMapEntry<K, V> temp = table[keyBucket];
		// Check if key is already present
		while (temp != null) {
			if ((temp.key == null && key == null) || (temp.key != null && temp.key.equals(key))) {
				temp.value = value;
				return;
			}
			temp = temp.next;
		}

		table[keyBucket] = new HashMapEntry<K, V>(key, value, table[keyBucket]);
		size++;
	}

	/**
	 * Check if the map contains a mapping for the given key
	 * @param key search for the key
	 * @return true if map contains mapping for the key
	 */
	public boolean containsKey(K key) {
		int keyBucket = hash(key);

		HashMapEntry<K, V> temp = table[keyBucket];
		while (temp != null) {
			if ((temp.key == null && key == null) || (temp.key != null && temp.key.equals(key))) {
				return true;
			}
			temp = temp.next;
		}

		return false;
	}

	private int hash(K key) {
		if (key == null) {
			return 0;
		} else {
			return Math.abs(key.hashCode() % this.table.length);
		}
	}

	@Override
	public V get(K key) {
		int hash = hash(key);
		if (table[hash] == null) {
			return null;
		} else {
			HashMapEntry<K, V> temp = table[hash];
			// search for key
			while (temp != null) {
				if (temp.key.equals(key))
					return temp.value;
				temp = temp.next; // return value corresponding to key.
			}
			return null; // returns null if key is not found.
		}
	}
}
 /**
  * Inner class for each entry
  * @param <K> key
  * @param <V> value
  */
class HashMapEntry<K, V> {
	K key;
	V value;
	HashMapEntry<K, V> next;

	public HashMapEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public HashMapEntry(K key2, V value2, HashMapEntry<K, V> hashMapEntry) {
		this.key = key2;
		this.value = value2;
		this.next = hashMapEntry;
	}
}
