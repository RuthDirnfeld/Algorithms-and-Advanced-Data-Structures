package assignment3AADS.Exercise1;

public class MainDirectedGraph {
	public static void main(String[] args) {

		MyDirectedGraph graph = new MyDirectedGraph(2);

		graph.addEdge(0, 1);
		graph.addEdge(1, 0);

		if (graph.isAcyclic())
			System.out.println("Should contain cycle, is: -> " + "Is acyclic (no cicle)");
		else
			System.out.println("Should contain cycle, is: -> " + "Is not acyclic (has cicle)");

		MyDirectedGraph graph1 = new MyDirectedGraph(2);

		graph1.addEdge(0, 1);

		if (graph1.isAcyclic())
			System.out.println("Should NOT contain cycle, is: -> " + "Is acyclic (no cicle)");
		else
			System.out.println("Should NOT contain cycle, is: -> " + "Is not acyclic (has cicle)");

		System.out.println("================================");

		// Create graphs given in the above diagrams
		MyDirectedGraph g1 = new MyDirectedGraph(5);
		g1.addEdge(0, 1);
		g1.addEdge(1, 2);
		g1.addEdge(2, 3);
		g1.addEdge(3, 0);
		g1.addEdge(2, 4);
		g1.addEdge(4, 2);
		if (g1.isConnected())
			System.out.println("Should return YES -> " + "Yes");
		else
			System.out.println("Should return YES -> " + "No");

		MyDirectedGraph g2 = new MyDirectedGraph(4);
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		g2.addEdge(2, 3);
		if (g2.isConnected())
			System.out.println("Should return NO -> " + "Yes");
		else
			System.out.println("Should return NO -> " + "No");

		System.out.println("================================");

		MyDirectedGraph g = new MyDirectedGraph(5);
		g.addEdge(1, 0);
		g.addEdge(0, 2);
		g.addEdge(2, 1);
		g.addEdge(0, 3);
		g.addEdge(3, 4);

		System.out.println("Strongly connected components: ");
		g.connectedComponents();

		System.out.println("================================");

		MyDirectedGraph g3 = new MyDirectedGraph(10);
		g3.addEdge(1, 0);
		g3.addEdge(0, 2);
		g3.addEdge(2, 3);
		g3.addEdge(3, 1);
		g3.addEdge(5, 6);

		if (g3.isAcyclic())
			System.out.println("Acyclic - Should return NO -> " + "Yes");
		else
			System.out.println("Acyclic - Should return NO -> " + "No");

		System.out.println("================================");

		// Test for unspecified size

		MyDirectedGraph gu = new MyDirectedGraph();
		for (int i = 0; i < 10; i++) {
			gu.addVertex(i);
		}

		gu.addEdge(1, 0);
		gu.addEdge(0, 2);
		gu.addEdge(2, 3);
		gu.addEdge(3, 1);
		gu.addEdge(5, 6);

		if (gu.isConnected())
			System.out.println("Connected - Should return NO -> " + "Yes");
		else
			System.out.println("Connected - Should return NO -> " + "No");

		if (gu.isAcyclic())
			System.out.println("Acyclic - Should return NO -> " + "Yes");
		else
			System.out.println("Acyclic - Should return NO -> " + "No");

		System.out.println("================================");

		MyDirectedGraph gA = new MyDirectedGraph();
		for (int i = 0; i < 5; i++) {
			gA.addVertex(i);
		}

		gA.addEdge(0, 2);
		gA.addEdge(2, 1);
		gA.addEdge(1, 0);
		gA.addEdge(0, 3);

		System.out.println("Strongly connected components: ");
		gA.connectedComponents();

	}
}