package assignment3AADS.Exercise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class MyUndirectedGraph implements A3Graph {

	// Number of vertices
	protected int numVertices = 0;
	// An array of doubly linked lists
	protected Vertex[] vertices;

	// Size specified
	public MyUndirectedGraph(int vertices) {
		this.vertices = new Vertex[vertices];
		for (int i = 0; i < vertices; i++) {
			addVertex(i);
		}
	}

	// No size specified
	public MyUndirectedGraph() {
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

	// Add an edge to the undirected graph
	@Override
	public void addEdge(int sourceVertex, int targetVertex) {
		// Make it that both, source and target vertices
		// are aware of the edge between them
		vertices[sourceVertex].getChildren().add(targetVertex);
		vertices[targetVertex].getChildren().add(sourceVertex);
	}

	@Override
	public boolean isConnected() {
		// Set all vertices as unvisited
		for (int i = 0; i < numVertices; i++) {
			vertices[i].setVisited(false);
		}

		// Perform DFS traverse
		traverseDFS(vertices[0].getValue());

		// Check if any unvisited vertices
		for (int i = 0; i < numVertices; i++) {
			// A vertex was not visited during traverse,
			// meaning it's not connected
			if (!vertices[i].getVisited()) {
				return false;
			}
		}
		// For loop was successful, therefore all are connected
		return true;
	}

	// A DFS way of traversing the vertex list
	// Helper method to find if graph is connected or not
	private void traverseDFS(int vertex) {
		// We visit the first
		vertices[vertex].setVisited(true);

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> it = vertices[vertex].getChildren().listIterator();
		while (it.hasNext()) {
			int v = it.next();
			// Found a not visited
			if (!vertices[v].getVisited())
				traverseDFS(v);
		}
	}

	// acyclic == a graph having no graph cycles
	// returns true if the graph is acyclic (if has no cycles)
	// https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
	@Override
	public boolean isAcyclic() {
		// Marking all vertices as not visited
		for (int i = 0; i < numVertices; i++) {
			vertices[i].setVisited(false);
		}

		// Call recursive helper to detect cycle
		for (int j = 0; j < numVertices; j++)
			// Check if already visited. If not, visit, otherwise
			// do nothing
			if (!vertices[j].getVisited()) {
				if (isAcyclic(j, -1)) {
					return false;
				}
			}

		return true;
	}

	// A recursive method that uses visited[] and parent to detect
	// cycle in subgraph reachable from vertex
	boolean isAcyclic(int vertex, int parent) {
		// We are visiting the node, so mark it as visited
		vertices[vertex].setVisited(true);
		int i;

		// Iterator for all adjacent (connected) vertices
		Iterator<Integer> it = vertices[vertex].getChildren().iterator();
		while (it.hasNext()) {
			i = it.next();
			// Check connected vertex, whether it's been visited or not.
			// If it hasn't, visit it and call this method on the
			// unvisited vertex and the current one
			if (!vertices[i].getVisited()) {
				if (isAcyclic(i, vertex)) {
					return true;
				}
			}
			// Connected vertex was visited and is not
			// a parent, that means it's not a cycle
			else if (i != parent) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<List<Integer>> connectedComponents() {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		// Marking all vertices as not visited
		for (int i = 0; i < numVertices; i++) {
			vertices[i].setVisited(false);
		}

		for (int i = 0; i < numVertices; i++) {
			if (!vertices[i].getVisited()) {
				List<Integer> temp = new ArrayList<Integer>();
				traverseChildren(temp, vertices[i].getValue());
				result.add(temp);
			}
		}

		return result;
	}

	// DFS way of traversing. Just visiting, not doing anything else
	private void traverseChildren(List<Integer> storage, int parent) {
		// Add visiting vertex into the list
		// and mark it as visited
		storage.add(parent);
		vertices[parent].setVisited(true);

		Iterator<Integer> it = vertices[parent].getChildren().iterator();
		while (it.hasNext()) {
			int el = it.next();

			if (!vertices[el].getVisited()) {
				vertices[el].setVisited(true);
				traverseChildren(storage, el);
			}
		}

	}

	@Override
	public boolean hasEulerPath() {
		// Can't have euler's path if not connected
		if (!isConnected()) {
			return false;
		}

		// Euler's path can't happen if any vertex has
		// odd degree of edges
		int odds = 0;
		for (int i = 0; i < numVertices; i++) {
			if (vertices[i].getChildren().size() % 2 != 0) {
				odds++;
			}
		}
		// If exactly two vertices have odd number of edges,
		// Euler path is still possible if we start at one of the odd
		// degree vertices and finish at the other
		if (odds > 0 && odds != 2) {
			return false;
		}

		return true;
	}

	@Override
	public List<Integer> eulerPath() {
		List<Integer> result = new ArrayList<Integer>();

		// Doesn't have euler's path
		if (!hasEulerPath()) {
			return result;
		}

		// Since neighbors are getting removed, we need to copy the vertex array
		// to not modify the original
		Vertex[] copyArr = Arrays.copyOf(vertices, numVertices); // O(n)

		// Might not be the best solution, but unlikely largest integer will be pushed
		// in testing cases
		int startingVertex = Integer.MAX_VALUE;
		boolean isOdd = false;
		// Graph had two odd edged vertices, one of them should be the start
		for (int i = 0; i < numVertices; i++) {
			if (vertices[i].getChildren().size() % 2 != 0) {
				startingVertex = vertices[i].getValue();
				isOdd = true;
			}
		}
		// Graph didn't have odd edged vertices, assign first vertex as starting
		if (startingVertex == Integer.MAX_VALUE) {
			startingVertex = vertices[0].getValue();
		}

		// Stack helps track of vertices that have no neighbors left
		Stack<Integer> stack = new Stack<Integer>();
		int currentVertex = startingVertex;
		do {
			// If vertex has adjacent vertices, get first added vertex
			// and remove their edge
			if (!copyArr[currentVertex].getChildren().isEmpty()) {
				// Pushing worked with vertex into the stack
				stack.push(currentVertex);
				// Removing edges
				int temp = copyArr[currentVertex].getChildren().getFirst();
				int index = copyArr[currentVertex].getChildren().indexOf(temp);
				copyArr[currentVertex].getChildren().remove(index);
				index = copyArr[temp].getChildren().indexOf(currentVertex);
				copyArr[temp].getChildren().remove(index);
				// Setting new vertex to work with
				currentVertex = temp;
			}
			// No adjacent vertices, so it needs to be pushed
			// to result array
			else {
				result.add(currentVertex);
				currentVertex = stack.pop();
			}
		} while (!stack.isEmpty());

		// If two odd edged vertices were present, then the result
		// should be correct and this is not needed. However, if
		if (!isOdd) {
			result.add(startingVertex);
		}

		return result;
	}
}
