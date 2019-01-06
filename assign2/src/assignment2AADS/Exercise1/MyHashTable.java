package assignment2AADS.Exercise1;

public class MyHashTable<T> implements A2HashTable<T> {
	/*
	 * Ideas for this code come from the course book.
	 */
	
	private HashEntry<T>[] array; // The array of elements
	private int currentSize; // The number of occupied cells
	private static final int DEFAULT_TABLE_SIZE = 11;
	private double loadFactor = 0.5; // Default load factor
	private double currentLoadFactor = 0; // Tracks current load factor

	public MyHashTable() {
		this(DEFAULT_TABLE_SIZE, 0.5);
	}
	
	public MyHashTable(double load) {
		this(DEFAULT_TABLE_SIZE, load);
	}

	public MyHashTable(int size, double load) {
		if (load >= 1.0) { // Important to keep it below 1.0
			System.err.println("Load factor should be less than 1.0!");
			return;
		}
		if (!isPrime(size)) {
			System.err.println("Recommended array size should be prime!");
			return;
		}
		allocateArray(size);
		makeEmpty();
		this.loadFactor = load;
	}

	/**
	 * If element is unique, inserts it
	 * @param element to be inserted
	 */
	@Override
	public void insert(T element) {
		
		// Calculate position for the element
		// Do nothing if element exists already
		int currentPos = findPos(element);
		if (currentPos == -1) {
			rehash();
			currentPos = findPos(element);
		}
		if (isActive(currentPos)) {
			return;
		}
		
		// Place element in the position
		array[currentPos] = new HashEntry<>(element, true);
		currentSize++;
		// Calculates current load factor and checks if rehashing (resizing) is needed.
		// If current load factor is higher than set load factor, resize.
		currentLoadFactor = (double)currentSize / (double)array.length;
		if (currentLoadFactor >= loadFactor) {
			rehash();
		}
	}

	/**
	 * If element exists, deletes the element
	 * @param element to be deleted
	 */
	@Override
	public void delete(T element) {
		// Determine at which position the element should be
		int currentPos = findPos(element);
		// If element is not deleted, delete and
		// reduce currentSize
		if (isActive(currentPos)) {
			array[currentPos].isActive = false;
			currentSize--;
		}
	}

	/**
	 * Determines if element exists in hash table
	 * @param element to be found
	 * @return true if it exists, else - false
	 */
	@Override
	public boolean contains(T element) {
		// Get possible location of an element
		int currentPos = findPos(element);
		// If not deleted and not a null, it exists
		return isActive(currentPos);
	}

	/**
	 * Finds the position at which the element should be
	 * @param element to be found
	 * @return the (possible) position of the element
	 */
	private int findPos(T element) {
		// The offset starts at 1, then will increase
		// by 2
		int offset = 1;
		// Position element should go in if it's not taken
		int currentPos = myhash(element);

		// Run loop until an empty spot is found
		// or element exists
		while (array[currentPos] != null && !array[currentPos].element.equals(element)) {
			currentPos += offset; // Compute ith probe
			offset += 2;
			if (currentPos >= array.length && currentPos < (array.length*2-1)) {
				currentPos -= array.length;
			}
			else if(currentPos >= array.length*2) {
				return -1; // Insertion failed
			}
		}

		return currentPos;

	}

	
	/**
	 * Empties hashtable
	 */
	private void makeEmpty() {
		currentSize = 0;
		for (int i = 0; i < array.length; i++)
			array[i] = null;
	}

	/**
	 * Checks if element exists and is not deleted
	 * @param currentPos position of an element
	 * @return true if element exists
	 */
	private boolean isActive(int currentPos) {
		return array[currentPos] != null && array[currentPos].isActive;
	}

	/**
	 * Calculates hash value of an element
	 * @param element of which hashvalue is calculated
	 * @return hash value of an element
	 */
	private int myhash(T element) {
		// Get hashvalue of an individual
		// element
		int hashVal = element.hashCode();

		// Calculate it for this hashtable
		hashVal %= array.length;
		if (hashVal < 0) {
			hashVal += array.length;
		}

		return hashVal;
	}

	/**
	 * When inserted amount of elements reaches
	 * half the table size, this method is called
	 * to resize and to place elements in their positions
	 */
	private void rehash() {
		// save current array into a temporary array
		HashEntry<T>[] oldArray = array;

		// Create a new double-sized, empty table
		allocateArray(nextPrime(2 * oldArray.length));
		currentSize = 0;

		// Copy table over
		for (int i = 0; i < oldArray.length; i++)
			if (oldArray[i] != null && oldArray[i].isActive)
				insert(oldArray[i].element);
	}

	/*
	 * Internal methods
	 * 
	 */
	
	/**
	 * Creates an array of given size
	 * @param arraySize size of the array
	 */
	@SuppressWarnings("unchecked")
	private void allocateArray(int arraySize) {
		array = new HashEntry[nextPrime(arraySize)];
	}

	/**
	 * Returns closest prime value to a given number
	 * @param p given number
	 * @return closest prime value
	 */
	private int nextPrime(int p) {
		if (p <= 0) {
			p = 3;
		}
		if (p % 2 == 0) {
			p++;
		}
		while(!isPrime(p)) {
			p += 2;
		}
		return p;
	}

	/**
	 * Checks if number is prime
	 * @param p number to check
	 * @return true if prime
	 */
	private boolean isPrime(int p) {
		if (p == 2 || p == 3)
			return true;

		if (p == 1 || p % 2 == 0)
			return false;

		for (int i = 3; i * i <= p; i += 2) {
			if (p % i == 0) {
				return false;
			}
		}

		return true;
	}
	
}

/**
 * A separate class for each entry is needed in case an element is
 * marked as deleted 
 */
class HashEntry<T> {
	public T element; // the element
	public boolean isActive; // false if marked deleted

	public HashEntry(T e) {
		this(e, true);
	}

	public HashEntry(T e, boolean i) {
		element = e;
		isActive = i;
	}
}
