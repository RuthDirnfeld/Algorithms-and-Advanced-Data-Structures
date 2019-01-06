package Exercise1;

public class MyIntegerBST implements A1Tree {
	/*
	 * Variable
	 */
	protected BinaryNode<Integer> root;

	int depth = 0;

	/*
	 * Constructor
	 */
	public MyIntegerBST() {
		this.root = null;
	}

	@Override
	public void insert(Integer value) {
		root = insert(value, root);
	}

	@Override
	public Integer mostSimilarValue(Integer value) {
		return mostSimilarValue(root, value);
	}

	@Override
	public void printByLevels() {
		int depth = depth(root);
		for (int i = 1; i <= depth; i++) {
			System.out.println("\n\nDepth " + (i - 1) + ": ");
			printByLevels(root, i);
		}
	}

	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param x
	 *            the item to insert.
	 * @param bn
	 *            the node that roots the tree.
	 * @return the new root.
	 * @throws DuplicateItemException
	 *             if x is already present.
	 */

	private BinaryNode<Integer> insert(Integer value, BinaryNode<Integer> bn) {
		if (bn == null)
			return new BinaryNode<>(value, null, null);

		int compareResult = value.compareTo(bn.data);

		if (compareResult < 0)
			bn.left = insert(value, bn.left);
		else if (compareResult > 0)
			bn.right = insert(value, bn.right);
		else
			; // Duplicate - do nothing
		return bn;
	}

	/**
	 * Internal method to find the most similar value.
	 * 
	 * @param bn
	 *            the node that roots the tree.
	 * @param value
	 *            the Integer value of the searched for element.
	 * 
	 * @return the closest element.
	 * 
	 */
	private Integer mostSimilarValue(BinaryNode<Integer> bn, Integer value) {
		int closest = bn.data; 
		while (bn != null) {
			if (bn.data == value) {
				return bn.data; 
			} else { 
				if (Math.abs(value - closest) >= Math.abs(value - bn.data)) {
					closest = bn.data;
					//System.out.println("current: " + closest);
				}
				if (bn.data > value) {
					bn = bn.left;
				} else {
					bn = bn.right;
				}
			}
		}
		return closest;
	}

	/**
	 * Internal method to print the Nodes on the given Level.
	 * 
	 * @param bn
	 *            the node.
	 * @param level
	 * 
	 */

	private void printByLevels(BinaryNode<Integer> bn, int level) {
		if (bn == null) 
			return;
		if (level == 1) { 
			System.out.print(bn.data + " ");
		} else if (level > 1) {
			printByLevels(bn.left, level - 1);
			printByLevels(bn.right, level - 1);
		}
	}

	int depth(BinaryNode<Integer> bn) {
		if (bn == null)
			return 0;
		else {
			/* compute depth of each subtree */
			int leftDepth = depth(bn.left);
			int rightDepth = depth(bn.right);

			/* use the larger one */
			if (leftDepth > rightDepth)
				return (leftDepth + 1);
			else
				return (rightDepth + 1);
		}
	}
}