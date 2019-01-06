package assignment2AADS.Exercise1;

public class MainClass {
	public static void main (String[] args) {
		MyHashTable<Integer> table = new MyHashTable<Integer>(0.5);
		
		for (int i = 0; i< 1000; i++) {
			System.out.println("Inserting: " + i);
			table.insert(new Integer(i));
		}
	}
}
