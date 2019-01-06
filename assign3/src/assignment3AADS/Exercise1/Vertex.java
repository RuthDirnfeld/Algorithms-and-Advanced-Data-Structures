package assignment3AADS.Exercise1;

import java.util.LinkedList;

public class Vertex {
	private int value;
	private LinkedList<Integer> children = new LinkedList<Integer>();
	private boolean visited = false;
	private boolean recursiveArr = false;
	private int distance = -1;
	private Vertex pred = null;

	public Vertex() {
	}

	public Vertex(int value) {
		this.value = value;
	}

	public void setVisited(boolean v) {
		this.visited = v;
	}

	public boolean getVisited() {
		return this.visited;
	}

	public int getValue() {
		return this.value;
	}

	public LinkedList<Integer> getChildren() {
		return this.children;
	}

	public void setDistance(int d) {
		this.distance = d;
	}

	public int getDistance() {
		return this.distance;
	}

	public boolean getRecursive() {
		return recursiveArr;
	}

	public void setRecursive(boolean r) {
		this.recursiveArr = r;
	}

	public Vertex getPred() {
		return pred;
	}

	public void setPred(Vertex p) {
		this.pred = p;
	}
}
