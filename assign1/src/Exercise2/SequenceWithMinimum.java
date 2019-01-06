package Exercise2;

public class SequenceWithMinimum implements A1SequenceWithMinimum {

	private class Node<Integer> {
		Integer value;
		Node<Integer> prev;
		Node<Integer> next;

		Integer getData() {
			return value;
		}

		Node(Integer value) {
			this.value = value;
		}

	}

	private Node<Integer> head;
	private Node<Integer> tail;
	private int size;

	@Override
	public void insertRight(Integer value) {

		Node<Integer> newNode = new Node<Integer>(value);

		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		++size;
	}

	@Override
	public Integer removeRight() {
		Node<Integer> newTail = tail.prev;
		tail = newTail;

		if (newTail != null) {
			newTail.next = null;
		} else {
			head = null;
			System.out.println("No elements in this List");
		}
		--size;
		return size;
	}

	@Override
	public void insertLeft(Integer value) {
		Node<Integer> newNode = new Node<Integer>(value);

		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		++size;
	}

	@Override
	public Integer removeLeft() {
		Node<Integer> newHead = head.next;
		head = newHead;

		if (newHead != null) {
			newHead.prev = null;
		} else {
			tail = null;
			System.out.println("\nNo elements in this List");
		}
		--size;
		return size;
	}

	@Override
	public Integer findMinimum()  {
		Node<Integer> curr = head;
		if (head == null) {
			System.out.println("No minimum in empty list");
			System.exit(-1);
		}
		Integer tempMinimum = head.getData();
		
		while (curr != null) {
			if (tempMinimum > curr.getData()) {
				tempMinimum = curr.getData();
			}
			curr = curr.next;
		}
		return tempMinimum;
	}

	public void printList() {
		Node<Integer> temp = head;
		while (temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.next;
		}
		System.out.println("");
	}

	public static void main(String[] args) {

		SequenceWithMinimum seq = new SequenceWithMinimum(); // seq={}

		 seq.insertRight(5); // seq={5}
		 seq.insertRight(4); // seq={5,4}
		
		 seq.printList();
		 System.out.println("Minimum: " + seq.findMinimum());
		 
		 seq.insertRight(15); // seq={5,4,15}
		 seq.removeRight(); // removes 15, and seq={5,4}
		 seq.insertLeft(9); // seq={9,5,4}
		 seq.removeLeft(); // removes 9, and seq={5,4}
		 seq.removeLeft(); // removes 5, and seq={4}
		 seq.insertLeft(41); //seq={41,4}
		 seq.insertLeft(2); //seq={2,41,4}
		 seq.insertLeft(12); ////seq={12,2,41,4}
		 	 
		 seq.printList();
		 System.out.println("Minimum: " + seq.findMinimum());

	}
}
