package assignment3AADS.Exercise1;

import java.util.List;

public class MainUndirectGraph {

	public static void main(String[] args) {
		MyUndirectedGraph graph = new MyUndirectedGraph(5);

		// https://www.geeksforgeeks.org/graph-and-its-representations/
		graph.addEdge(0, 1);
		graph.addEdge(0, 4);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);

		System.out.println("Should be [1, 4] is: " + graph.vertices[0].getChildren().toString());
		System.out.println("Should be [0, 2, 3, 4] is: " + graph.vertices[1].getChildren().toString());
		System.out.println("Should be [1, 3] is: " + graph.vertices[2].getChildren().toString());
		System.out.println("Should be [1, 2, 4] is: " + graph.vertices[3].getChildren().toString());
		System.out.println("Should be [0, 1, 3] is: " + graph.vertices[4].getChildren().toString());
		if (graph.isAcyclic())
			System.out.println("Should contain cycle, is: -> " + "Is acyclic (no cicle)");
		else
			System.out.println("Should contain cycle, is: -> " + "Is not acyclic (has cicle)");

		System.out.println("Should return true (graph is connected), returns: " + graph.isConnected());

		List<List<Integer>> result = graph.connectedComponents();
		System.out.println("Connected components: ");
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}

		System.out.println("================================");
		// Create a graph given in the above diagram
		MyUndirectedGraph g1 = new MyUndirectedGraph(5);
		g1.addEdge(1, 0);
		g1.addEdge(1, 2);
		g1.addEdge(2, 0);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);
		if (g1.isAcyclic())
			System.out.println("Should contain cycle, is: -> " + "Is acyclic (no cicle)");
		else
			System.out.println("Should contain cycle, is: -> " + "Is not acyclic (has cicle)");

		System.out.println("Should return true (graph is connected), returns: " + g1.isConnected());
		List<List<Integer>> resultG1 = g1.connectedComponents();
		System.out.println("Connected components: ");
		for (int i = 0; i < resultG1.size(); i++) {
			System.out.println(resultG1.get(i).toString());
		}
		System.out.println("================================");
		MyUndirectedGraph g2 = new MyUndirectedGraph(4);
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		if (g2.isAcyclic())
			System.out.println("Should NOT contain cycle, is: -> " + "Is acyclic (no cicle)");
		else
			System.out.println("Should NOT contain cycle, is: -> " + "Is not acyclic (has cicle)");

		System.out.println("Should return false (graph not connected), returns: " + g2.isConnected());
		List<List<Integer>> resultG2 = g2.connectedComponents();
		System.out.println("Connected components: ");
		for (int i = 0; i < resultG2.size(); i++) {
			System.out.println(resultG2.get(i).toString());
		}
		System.out.println("================================");
		MyUndirectedGraph g3 = new MyUndirectedGraph(3);
		System.out.println("Should return false (graph not connected), returns: " + g3.isConnected());
		List<List<Integer>> resultG3 = g3.connectedComponents();
		System.out.println("Connected components: ");
		for (int i = 0; i < resultG3.size(); i++) {
			System.out.println(resultG3.get(i).toString());
		}

		System.out.println("================================");
		MyUndirectedGraph g4 = new MyUndirectedGraph(9);

		g4.addEdge(0, 8);
		g4.addEdge(0, 5);
		g4.addEdge(0, 1);
		g4.addEdge(0, 7);
		g4.addEdge(5, 8);
		g4.addEdge(1, 3);
		g4.addEdge(1, 2);
		g4.addEdge(1, 7);
		g4.addEdge(7, 6);
		g4.addEdge(7, 4);
		g4.addEdge(4, 6);
		g4.addEdge(2, 3);
		System.out.println("Should return true (has Euler's path): " + g4.hasEulerPath());
		List<List<Integer>> resultG4 = g4.connectedComponents();
		System.out.println("Connected components: ");
		for (int i = 0; i < resultG4.size(); i++) {
			System.out.println(resultG4.get(i).toString());
		}
		System.out.println("Euler's path should be [0, 7, 4, 6, 7, 1, 2, 3, 1, 0, 5, 8, 0], actually is: "
				+ g4.eulerPath().toString());

		System.out.println("Should return true: " + g4.isConnected());

	}

}
