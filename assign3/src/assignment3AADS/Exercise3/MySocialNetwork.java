package assignment3AADS.Exercise3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import assignment3AADS.Exercise1.MyUndirectedGraph;
import assignment3AADS.Exercise1.Vertex;

public class MySocialNetwork extends MyUndirectedGraph implements A3SocialNetwork {

	public MySocialNetwork(int adj) {
		super(adj);
	}

	/**
	 * The method returns vertices from given source to given distance
	 */
	@Override
	public int numberOfPeopleAtFriendshipDistance(int vertexIndex, int distance) {
		// keeps track of number of vertices as requested distance
		int vertexCount = 0;
		Queue<Vertex> queue = new LinkedList<Vertex>();
		// Since neighbors are getting removed, we need to copy the vertex array
		// to not modify the original
		// Clear up vertex distance and previous values
		for (int i = 0; i < numVertices; i++) {
			vertices[i].setDistance(-1);
		}

		// Set a provided vertex as a starting vertex
		vertices[vertexIndex].setDistance(0);
		queue.add(vertices[vertexIndex]);

		// Use BFS to go through each vertex.
		// If vertex has no distance, it has not been visited
		// so update its distance. It does visit
		// parent vertices too.
		while (!queue.isEmpty()) {
			// Get a vertex on top
			Vertex v = queue.poll();
			// If the new distance will be higher than requested,
			// there is no need to continue on looping
			if (v.getDistance() + 1 > distance) {
				break;
			}
			// Iterate through all the children of
			// current vertex
			for (Integer i : v.getChildren()) {
				// Child has not been visited (distance not set)
				// visit it and calculate new distance (depending on a parent(
				// or current) vertex)
				if (vertices[i].getDistance() == -1) {
					// Reached vertex is at the depth as requested,
					// so add it to result set
					if (v.getDistance() + 1 == distance) {
						vertices[i].setDistance(v.getDistance() + 1);
						vertexCount++;
					}
					// Otherwise, update distance, and add the vertex
					// to the queue to be processed in later loop
					else {
						vertices[i].setDistance(v.getDistance() + 1);
						queue.add(vertices[i]);
					}
				}
			}
		}
		return vertexCount;
	}

	/**
	 * Returns a distance of farthest possible reachable node from a source node.
	 */
	@Override
	public int furthestDistanceInFriendshipRelationships(int vertexIndex) {
		int distance = 0;
		Queue<Vertex> queue = new LinkedList<Vertex>();
		// Since neighbors are getting removed, we need to copy the vertex array
		// to not modify the original
		// Clear up vertex distance and previous values
		for (int i = 0; i < numVertices; i++) {
			vertices[i].setDistance(-1);
		}

		// Get the source vertex and set its distance to 0 (that's
		// where iteration starts)
		vertices[vertexIndex].setDistance(0);
		queue.add(vertices[vertexIndex]);

		// Use BFS to go through each vertex.
		// If vertex has no distance, it has not been visited
		// so update its distance. It does visit
		// parent vertices too.
		while (!queue.isEmpty()) {
			// Get a vertex on top
			Vertex v = queue.poll();
			// Iterate through all the children of
			// current vertex
			for (Integer i : v.getChildren()) {
				// Unvisited child found
				if (vertices[i].getDistance() == -1) {
					// Update distance
					vertices[i].setDistance(v.getDistance() + 1);
					// If currently tracked distance is smaller than
					// new distance, update the value
					if (distance < vertices[i].getDistance()) {
						distance = vertices[i].getDistance();
					}
					// Prepare item for next iteration
					queue.add(vertices[i]);
				}
			}
		}

		return distance;
	}

	/**
	 * Returns a list of nodes that are 2 steps away from given node and share at
	 * least 3 neighbors it.
	 */
	@Override
	public List<Integer> possibleFriends(int vertexIndex) {
		List<Integer> result = new ArrayList<Integer>(); // Stores possible friends, if any
		// Used to track vertices with at least 3 children and 2 steps away from given
		// vertex
		List<Integer> visited = new ArrayList<Integer>();
		// Children of given vertex
		Set<Integer> neighbours = new HashSet<Integer>(vertices[vertexIndex].getChildren());
		// Distance is preset in this case
		int distance = 2;

		Queue<Vertex> queue = new LinkedList<Vertex>();
		// Since neighbors are getting removed, we need to copy the vertex array
		// to not modify the original
		// Clear up vertex distance and previous values
		for (int i = 0; i < numVertices; i++) {
			vertices[i].setDistance(-1);
		}

		// Starting off with given vertex
		vertices[vertexIndex].setDistance(0);
		queue.add(vertices[vertexIndex]);

		// Use BFS to go through each vertex.
		// If vertex has no distance, it has not been visited
		// so update its distance. It does visit
		// parent vertices too.
		while (!queue.isEmpty()) {
			// Get a vertex on top
			Vertex v = queue.poll();
			// We are further than allowed distance, no need to continue iterating
			if (v.getDistance() + 1 > distance) {
				break;
			}
			// Iterate through current vertex children
			for (Integer i : v.getChildren()) {
				// Unvisited vertex, so we visit it
				if (vertices[i].getDistance() == -1) {
					// If the child of current vertex is at distance 2, add it
					// to the list
					if (v.getDistance() + 1 == distance) {
						visited.add(vertices[i].getValue());
						vertices[i].setDistance(v.getDistance() + 1);
					}
					// Else this vertex might have children that are at distance 2
					// so add it to queue to process later
					else {
						vertices[i].setDistance(v.getDistance() + 1);
						queue.add(vertices[i]);
					}
				}
			}
		}

		// Remove vertices that don't have at least 3 children
		Iterator<Integer> it = visited.iterator();
		while (it.hasNext()) {
			int el = it.next();
			if (vertices[el].getChildren().size() < 3) {
				it.remove();
			}
		}

		// Iterate through remaining vertices (with 3 or more children)
		// If once vertex has at least 3 same children as source vertex
		// it fits
		it = visited.iterator();
		while (it.hasNext()) {
			int el = it.next();
			int count = 0;
			// Iterate through element's children
			for (Integer i : vertices[el].getChildren()) {
				// Increase count of same neighbors
				if (neighbours.contains(i)) { // O(1), thanks to hashset
					count++;
				}
				// Don't care if there are more same than 3.
				// 3 is enough for it to be added to the result
				if (count == 3) {
					result.add(el);
					break;
				}
			}
		}

		return result;
	}
}
