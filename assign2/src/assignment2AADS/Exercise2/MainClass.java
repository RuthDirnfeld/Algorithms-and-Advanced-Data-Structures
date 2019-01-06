package assignment2AADS.Exercise2;

import java.util.Arrays;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("Starting tests: ");
		A2Direction[] dir = {A2Direction.LEFT, A2Direction.DOWN, A2Direction.DOWN, A2Direction.RIGHT, A2Direction.UP, A2Direction.LEFT};
		MyItinerary test = new MyItinerary(dir);
		System.out.println("Width, should return 1, returns: " + test.widthOfItinerary());
		System.out.println("Height, should return 2, returns: " + test.heightOfItinerary());
		System.out.println("Intersections, should return [5], returns: " + Arrays.toString(test.getIntersections()));
		System.out.println("Rotate right, should return [UP, LEFT, LEFT, DOWN, RIGHT, UP], returns: " + Arrays.toString(test.rotateRight()));
		
		A2Direction[] dir2 = {A2Direction.LEFT, A2Direction.DOWN, A2Direction.RIGHT, A2Direction.DOWN, A2Direction.LEFT, A2Direction.UP, 
				A2Direction.LEFT, A2Direction.UP, A2Direction.RIGHT, A2Direction.UP};
		MyItinerary test2 = new MyItinerary(dir2);
		System.out.println("Intersections, should return [5, 8], returns: " + Arrays.toString(test2.getIntersections()));
		
		// No intersections test
		A2Direction[] dir3 = {A2Direction.LEFT, A2Direction.DOWN, A2Direction.DOWN, A2Direction.LEFT};
		MyItinerary test3 = new MyItinerary(dir3);
		System.out.println("Should be a null, returns: " + (test3.getIntersections() == null ? null : Arrays.toString(test3.getIntersections())));
		

	}

}
