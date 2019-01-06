package Exercise1;

public class BinaryNode<Integer> {

	Integer data; // the data in the node
	BinaryNode<Integer> left = null; // Left child
	BinaryNode<Integer> right = null; // Right child

	// Constructors
	BinaryNode(Integer _data) {
		this(_data, null, null);
	}

	BinaryNode(Integer _data, BinaryNode<Integer> _left, BinaryNode<Integer> _right) {
		data = _data;
		left = _left;
		right = _right;
	}

}