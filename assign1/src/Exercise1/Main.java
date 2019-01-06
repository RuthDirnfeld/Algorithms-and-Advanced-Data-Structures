package Exercise1;

public class Main {
	public static void main(String[] args) {
		MyIntegerBST bst = new MyIntegerBST();

		/*
		 * unbalanced
		 */
		
/*		bst.insert(1);
		bst.insert(4);
		bst.insert(8);
		bst.insert(12);
		bst.insert(16);
		bst.insert(20);
		bst.insert(24);
*/
		/*
		 * balanced
		 */
		
		bst.insert(10);
		bst.insert(7);
		bst.insert(4);
		bst.insert(9);
		bst.insert(8);
		bst.insert(20);
		bst.insert(25);
	
		
		System.out.println();
		System.out.println("Expected Output of mostSimilarValue(18)-> " + bst.mostSimilarValue(18));
		System.out.println("Expected Output of mostSimilarValue(21)-> " + bst.mostSimilarValue(21));
		System.out.println("Expected Output of mostSimilarValue(24)-> " + bst.mostSimilarValue(24));
		System.out.println("Expected Output of mostSimilarValue(1)-> " + bst.mostSimilarValue(1));
		System.out.println("Expected Output of mostSimilarValue(9)-> " + bst.mostSimilarValue(9));
		System.out.println("Expected Output of mostSimilarValue(15)-> " + bst.mostSimilarValue(15));
		
		bst.printByLevels();	
	}
}
