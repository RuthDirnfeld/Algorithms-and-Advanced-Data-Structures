package Exercise2;

public class SequenceWithMinimumTest {
	/**
	 * A test class to test if the insert and remove methods are fast enough,
	 * even after a large amount of elements are inserted or removed from the
	 * List.
	 *  
	 * @param args
	 */
	public static void main(String[] args) {

		SequenceWithMinimum seq = new SequenceWithMinimum(); // seq={}
		
		/*
		 * Insert Left Test
		 */
		for (int i = 0; i < 1000005; i++) {
			seq.insertLeft(i);
		}
		long startTime = System.currentTimeMillis();
		seq.insertLeft(5);
		long endTime = System.currentTimeMillis();
		System.out.println("InsertLeft after 1000000 took " + (endTime - startTime) + " ms");

		for (int i = 0; i < 5000005; i++) {
			seq.insertLeft(i);
		}
		long startTime2 = System.currentTimeMillis();
		seq.insertLeft(4);
		long endTime2 = System.currentTimeMillis();
		System.out.println("InsertLeft after 5000000 took " + (endTime2 - startTime2) + " ms");

		for (int i = 0; i < 10000005; i++) {
			seq.insertLeft(i);
		}
		long startTime3 = System.currentTimeMillis();
		seq.insertLeft(15);
		long endTime3 = System.currentTimeMillis();
		System.out.println("InsertLeft after 10000000 took " + (endTime3 - startTime3) + " ms");

		/*
		 * Remove Left Test
		 */
		for (int i = 0; i < 1000000; i++) {
			seq.removeLeft();
		}
		long startTime4 = System.currentTimeMillis();
		seq.removeLeft();
		long endTime4 = System.currentTimeMillis();
		System.out.println("\nRemoveLeft after 1000000 took " + (endTime4 - startTime4) + " ms");

		for (int i = 0; i < 5000000; i++) {
			seq.removeLeft();
		}
		long startTime5 = System.currentTimeMillis();
		seq.removeLeft();
		long endTime5 = System.currentTimeMillis();
		System.out.println("RemoveLeft after 5000000 took " + (endTime5 - startTime5) + " ms");

		for (int i = 0; i < 10000000; i++) {
			seq.removeLeft();
		}
		long startTime6 = System.currentTimeMillis();
		seq.removeLeft();
		long endTime6 = System.currentTimeMillis();
		System.out.println("RemoveLeft after 10000000 took " + (endTime6 - startTime6) + " ms");

		/*
		 * Insert Right Test
		 */
		for (int i = 0; i < 1000005; i++) {
			seq.insertRight(i);
		}
		long startTime7 = System.currentTimeMillis();
		seq.insertRight(9);
		long endTime7 = System.currentTimeMillis();
		System.out.println("\nInsertRight after 1000000 took " + (endTime7 - startTime7) + " ms");

		for (int i = 0; i < 5000005; i++) {
			seq.insertRight(i);
		}
		long startTime8 = System.currentTimeMillis();
		seq.insertRight(4);
		long endTime8 = System.currentTimeMillis();
		System.out.println("InsertRight after 5000000 took " + (endTime8 - startTime8) + " ms");

		for (int i = 0; i < 10000005; i++) {
			seq.insertRight(i);
		}
		long startTime9 = System.currentTimeMillis();
		seq.insertRight(15);
		long endTime9 = System.currentTimeMillis();
		System.out.println("InsertRight after 10000000 took " + (endTime9 - startTime9) + " ms");

		/*
		 * Remove Right Test
		 */
		for (int i = 0; i < 1000000; i++) {
			seq.removeRight();
		}
		long startTime10 = System.currentTimeMillis();
		seq.removeRight();
		long endTime10 = System.currentTimeMillis();
		System.out.println("\nRemoveRight after 1000000 took " + (endTime10 - startTime10) + " ms");

		for (int i = 0; i < 5000000; i++) {
			seq.removeRight();
		}
		long startTime11 = System.currentTimeMillis();
		seq.removeRight();
		long endTime11 = System.currentTimeMillis();
		System.out.println("RemoveRight after 5000000 took " + (endTime11 - startTime11) + " ms");

		for (int i = 0; i < 10000000; i++) {
			seq.removeRight();
		}
		long startTime12 = System.currentTimeMillis();
		seq.removeRight();
		long endTime12 = System.currentTimeMillis();
		System.out.println("RemoveRight after 10000000 took " + (endTime12 - startTime12) + " ms");

		/*
		 * FindMinimum Test
		 */
		for (int i = 0; i < 100005; i++) {
			seq.insertLeft(i);
		}
		long startTime13 = System.currentTimeMillis();
		seq.findMinimum();
		long endTime13 = System.currentTimeMillis();
		System.out.println("\nFindMinimum after 100000 took " + (endTime13 - startTime13) + " ms");

		for (int i = 0; i < 500005; i++) {
			seq.insertLeft(i);
		}
		long startTime14 = System.currentTimeMillis();
		seq.findMinimum();
		long endTime14 = System.currentTimeMillis();
		System.out.println("FindMinimum after 500000 took " + (endTime14 - startTime14) + " ms");

		for (int i = 0; i < 1000005; i++) {
			seq.insertLeft(i);
		}
		long startTime15 = System.currentTimeMillis();
		seq.findMinimum();
		long endTime15 = System.currentTimeMillis();
		System.out.println("FindMinimum after 1000000 took " + (endTime15 - startTime15) + " ms");
	}
}
