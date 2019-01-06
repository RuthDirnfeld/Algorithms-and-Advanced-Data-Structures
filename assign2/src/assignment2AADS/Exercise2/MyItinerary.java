package assignment2AADS.Exercise2;

import java.util.Arrays;

import assignment2AADS.Exercise1.MyHashTable;

public class MyItinerary implements A2Itinerary<A2Direction> {
	// Visited locations
	private MyHashTable<Point> traspositionTbl = new MyHashTable<Point>();

	// For tracking x and y
	private Point current = new Point(0, 0);
	private Point largest = new Point (0, 0);
	private Point smallest = new Point(0, 0);

	// Keeps track of amount of elements. Starts at 0
	private int movesPos = 0; 
	private int[] intersections = new int[0];
	private int interSize = 0;

	private A2Direction[] rotatedRight = new A2Direction[0];
	private int rightRotSize = 0; 

	public MyItinerary() {
		traspositionTbl.insert(new Point (0, 0)); 
	}

	public MyItinerary(A2Direction[] directions) {
		traspositionTbl.insert(new Point (0, 0)); 
		insert(directions);
	}

	public void insert(A2Direction direction) {
		int x = getX(direction);
		int y = getY(direction);

		Point coord = new Point(x, y);

		// Increases array size by one with every move on demand
		rotatedRight = Arrays.copyOf(rotatedRight, rightRotSize + 1); // O(n)
		if (direction == A2Direction.LEFT) {
			rotatedRight[rightRotSize++] = A2Direction.UP;
		} else if (direction == A2Direction.RIGHT) {
			rotatedRight[rightRotSize++] = A2Direction.DOWN;
		} else if (direction == A2Direction.DOWN) {
			rotatedRight[rightRotSize++] = A2Direction.LEFT;
		} else if (direction == A2Direction.UP) {
			rotatedRight[rightRotSize++] = A2Direction.RIGHT;
		}

		// If x is larger than x1, then it can't be smaller than x2
		if (x > largest.x) {
			largest.x = x;
		} else if (x < smallest.x) {
			smallest.x = x;
		}

		// If y is larger than y1, then it can't be smaller than y2
		if (y > largest.y) {
			largest.y = y;
		} else if (y < smallest.y) {
			smallest.y = y;
		}

		// Check if the coordinate was already accessed before. If it has,
		// that means it's an intersection. Otherwise, add the coordinate to 
		// the transposition table
		if (traspositionTbl.contains(coord)) {
			intersections = Arrays.copyOf(intersections, interSize + 1); // O(n)
			intersections[interSize++] = movesPos;
		} else {
			traspositionTbl.insert(coord); 
		}

		movesPos++;
	}

	/**
	 * Inserts the directions 
	 * @param direction
	 */
	public void insert(A2Direction[] direction) {
		for (int i = 0; i < direction.length; i++) {
			insert(direction[i]);
		}
	}
	
	/**
	 * Rotates the array to the right
	 * @return rotatedRight - the right rotated 
	 * directions
	 */
	@Override
	public A2Direction[] rotateRight() {
		return rotatedRight;
	}
	
	/**
	 * Computes the width of the Itinerary
	 * @return Math.abs(x1 - x2) - the total width
	 */
	@Override
	public int widthOfItinerary() {
		return Math.abs(largest.x - smallest.x);
	}
	
	/**
	 * Computes the height of the Itinerary
	 * @return Math.abs(y1 - y2) - the total width
	 */
	@Override
	public int heightOfItinerary() {
		return Math.abs(largest.y - smallest.y); 
	}

	/**
	 * Shows the elements that reaches a point that 
	 * had been already visited
	 * @return intersections - returns an array with 
	 * all the positions of the itinerary array that 
	 * reach a location that has been previously visited
	 */
	@Override
	public int[] getIntersections() {
		if (intersections.length == 0) {
			return null;
		}
		return intersections; 
	}

	/**
	 * Computes the position on the x-axis. If the direction
	 * is LEFT, moves 1 position to the left. If direction is
	 * RIGHT, moves 1 position to the right.
	 * @param direction - direction on the x-axis
	 * @return currentX - current position on the x-axis
	 */
	private int getX(A2Direction direction) { 
		if (direction == A2Direction.LEFT) {
			current.x--;
			return current.x;
		} else if (direction == A2Direction.RIGHT) {
			current.x++;
			return current.x;
		} else {
			return current.x;
		}
	}

	/**
	 * Computes the position on the y-axis. If the direction
	 * is DOWN, moves 1 position down. If direction is
	 * UP, moves 1 position up.
	 * @param direction - direction on the y-axis
	 * @return currentY - current position on the y-axis
	 */
	private int getY(A2Direction direction) {
		if (direction == A2Direction.DOWN) {
			current.y--;
			return current.y;
		} else if (direction == A2Direction.UP) {
			current.y++;
			return current.y;
		} else {
			return current.y;
		}
	}
}
class Point {
	int x = 0;
	int y = 0;
	
	Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Point class uses HashTable,therefore
	// implementation of hashCode() and equals() is required.
	// Otherwise, some methods of the hashtable might fail.
	
	// https://stackoverflow.com/questions/22826326/good-hashcode-function-for-2d-coordinates
    @Override
    public int hashCode() {
    	int tmp = ( y +  ((x+1)/2));
        return x +  ( tmp * tmp);
    }
    
    @Override
    public boolean equals(Object obj) {
    	// Same exact object being compared
        if (this == obj) {
            return true;
        }
        // Compared object is a null
        if (obj == null) {
            return false;
        }
        // Compared object is of different class
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point givenPoint = (Point) obj;
        if (this.x == givenPoint.x) {
        	if (this.y == givenPoint.y) {
        		return true;
        	}
        }
        return false;
    }
}
