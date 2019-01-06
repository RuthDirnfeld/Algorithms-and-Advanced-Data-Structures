package assignment3AADS.Exercise1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class MyDirectedGraph implements A3Graph {

	// Number of vertices
	int numVertices = 0;
	// An array of doubly linked lists
	Vertex[] vertices;

	// Size specified
	public MyDirectedGraph(int vertices) {
		this.vertices = new Vertex[vertices];
		for (int i = 0; i < vertices; ++i) {
			this.addVertex(i);
		}
	}

	// No size specified
	public MyDirectedGraph() {
		this.numVertices = 0;
		this.vertices = new Vertex[4];
	}

	@Override
	public void addVertex(int vertex) {
		// Added vertex has a spot in the array
		if (vertex < vertices.length - 1) {
			vertices[vertex] = new Vertex(vertex);
			numVertices++;
		}
		// Added vertex is larger than array size
		else {
			vertices = Arrays.copyOf(vertices, vertices.length * 2);
			vertices[vertex] = new Vertex(vertex);
			numVertices++;
		}
	}

	@Override
	public void addEdge(int sourceVertex, int targetVertex) {
		vertices[sourceVertex].getChildren().add(targetVertex);

	}

	// returns true if graph is strongly
	// connected
	@Override
	public boolean isConnected() {
		// Set all vertices as unvisited
		boolean visited[] = new boolean[numVertices];
		for (int i = 0; i < numVertices; i++) {
			visited[i] = false;
		}

		// DFS traversal
		traverseDFS(0, visited);

		// If a vertex is not visited, then
		// return false
		for (int i = 0; i < numVertices; i++) {
			if (!visited[i]) {
				return false;
			}
		}

		// Create a reverse graph
		MyDirectedGraph graph = getReversed();

		// Set all vertices as not visited (For
		// second DFS)
		for (int i = 0; i < numVertices; i++) {
			visited[i] = false;
		}
		// DFS traversal for reversed graph
		graph.traverseDFS(0, visited);

		// If a vertex is not visited in second DFS, then
		// return false
		for (int i = 0; i < numVertices; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

	// Function that returns a reverse graph
	MyDirectedGraph getReversed() {
		MyDirectedGraph temp = new MyDirectedGraph(numVertices);
		for (int v = 0; v < numVertices; v++) {
			// Recur for all the vertices adjacent to this vertex
			Iterator<Integer> i = vertices[v].getChildren().iterator();
			while (i.hasNext()) {
				int el = i.next();
				temp.vertices[el].getChildren().add(v);
			}
		}
		return temp;
	}

	// A DFS way of traversing the vertex list
	private void traverseDFS(int vertex, boolean visited[]) {
		// Set the current node as visited
		visited[vertex] = true;

		// for printing the nodes in each strongly connected component
		System.out.print(vertex + " ");

		int n;

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = vertices[vertex].getChildren().iterator();
		while (i.hasNext()) {
			n = i.next();
			// Found a non visited
			if (!visited[n])
				traverseDFS(n, visited);
		}
	}

	// acyclic == a graph having no graph cycles
	// returns true if the graph is acyclic (if has no cycles)
	@Override
	public boolean isAcyclic() {
		for (int i = 0; i < numVertices; i++) {
			vertices[i].setVisited(false);
			vertices[i].setRecursive(false);
		}

		for (int i = 0; i < numVertices; i++) {
			if (isAcyclic(i)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * recursiveArr[] keeps track of vertices in the recursion stack visited[] is
	 * used to keep track of already visited vertices
	 */
	private boolean isAcyclic(int i) {
		// Mark the current node as visited and
		// part of recursion stack
		if (vertices[i].getRecursive()) {
			return true;
		}

		if (vertices[i].getVisited()) {
			return false;
		}

		vertices[i].setVisited(true);

		vertices[i].setRecursive(true);
		List<Integer> children = vertices[i].getChildren();

		for (Integer c : children) {
			if (isAcyclic(c)) {
				return true;
			}
		}

		vertices[i].setRecursive(false);

		return false;
	}

	// https://www.geeksforgeeks.org/strongly-connected-components/
	@Override
	public List<List<Integer>> connectedComponents() {
		Stack stack = new Stack();

		// For the first DFS - set vertices as not visited
		boolean visited[] = new boolean[numVertices];
		for (int i = 0; i < numVertices; i++) {
			visited[i] = false;
		}

		// Fill vertices in stack by finishing time
		for (int i = 0; i < numVertices; i++) {
			if (visited[i] == false) {
				fillStack(i, visited, stack);
			}
		}

		// Reversed graph
		MyDirectedGraph graph = getReversed();

		// For second DFS - set vertices as not visited
		for (int i = 0; i < numVertices; i++) {
			visited[i] = false;
		}

		// Process all vertices
		while (stack.empty() == false) {
			// Pop vertex from stack
			int v = (int) stack.pop();

			// Print the strongly connected components
			if (visited[v] == false) {
				graph.traverseDFS(v, visited);
				System.out.println();
			}
		}
		return stack;
	}

	private void fillStack(int vertex, boolean visited[], Stack stack) {
		// Set current node as visited
		visited[vertex] = true;

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> it = vertices[vertex].getChildren().iterator();
		while (it.hasNext()) {
			int n = it.next();
			if (!visited[n])
				fillStack(n, visited, stack);
		}

		// All connected vertices to vertex are processed,
		// push vertex to Stack
		stack.push(new Integer(vertex));
	}
}