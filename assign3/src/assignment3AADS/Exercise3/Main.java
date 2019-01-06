package assignment3AADS.Exercise3;

public class Main {
	public static void main(String[] args) {
		MySocialNetwork network = new MySocialNetwork(14);

		network.addEdge(4, 0);
		network.addEdge(4, 1);
		network.addEdge(4, 3);
		network.addEdge(4, 5);
		network.addEdge(4, 6);
		network.addEdge(4, 7);
		network.addEdge(4, 8);
		network.addEdge(4, 11);
		network.addEdge(0, 1);
		network.addEdge(0, 3);
		network.addEdge(0, 2);
		network.addEdge(1, 2);
		network.addEdge(1, 3);
		network.addEdge(2, 3);
		network.addEdge(2, 12);
		network.addEdge(5, 13);
		network.addEdge(6, 10);
		network.addEdge(6, 9);
		network.addEdge(9, 7);
		network.addEdge(9, 8);

		System.out.println("Should be 8, returns: " + network.numberOfPeopleAtFriendshipDistance(4, 1));
		System.out.println("Should be 4, returns: " + network.numberOfPeopleAtFriendshipDistance(4, 2));
		System.out.println("Should be 1, returns: " + network.numberOfPeopleAtFriendshipDistance(4, 3));
		System.out.println("Should be 0, returns: " + network.numberOfPeopleAtFriendshipDistance(4, 5));

		System.out.println("Should be 2, returns: " + network.numberOfPeopleAtFriendshipDistance(9, 2));
		System.out.println("Should be 7, returns: " + network.numberOfPeopleAtFriendshipDistance(10, 3));

		System.out
				.println("Furthest of 4 should be 3, returns: " + network.furthestDistanceInFriendshipRelationships(4));
		System.out.println(
				"Furthest of 9 should be 5, returns: " + network.furthestDistanceInFriendshipRelationships(10));

		System.out.println("Should return [2,9], returns: " + network.possibleFriends(4));

	}

}
