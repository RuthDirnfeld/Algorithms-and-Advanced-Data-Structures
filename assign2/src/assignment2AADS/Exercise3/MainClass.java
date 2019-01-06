package assignment2AADS.Exercise3;

import java.util.Arrays;

public class MainClass {

	public static void main (String[] args) {
		MyMeasure test = new MyMeasure();
		int[] arr1 = {10,1,7,10};
		int[] arr2 = {1, 10, 7,10};
		int[] arr3 = {10,1,7,9};
		int[] arr4 = {1, 10, 7, 7};
		int[] arr5 = {1,7,10};
		System.out.println("Should return true, returns: " + test.isSameCollection(arr1, arr2));
		System.out.println("Should return false, returns: " + test.isSameCollection(arr3, arr2));
		System.out.println("Should return false, returns: " + test.isSameCollection(arr4, arr5));
		
		int arr00[] = { 1, 2, 3, 4}; 
        int arr01[] = { 3, 4, 4, 2}; 
          
        System.out.println("Should return false, returns: " + test.isSameCollection(arr00, arr01));
        
        int arr02[] = {15, 25, 35, 45};
        int arr03[] = {45, 25, 35, 15};
        System.out.println("Should return true, returns: " + test.isSameCollection(arr02, arr03));
        
		int[] arr6 = {2,5,3,9};
		int[] arr7 = {15,12,1,3};
		System.out.println("Should return 86, returns: " + test.minDifferences(arr6, arr7));
		
		int[] arr9 = {200, 16, 2};
		System.out.println("Should return [] or [16], returns: " + Arrays.toString(test.getPercentileRange(arr9, 40, 60)));
		
		int[] arr8 = {20, 16, 2, 4, 10, 6, 12, 8, 14, 18};
		System.out.println("Should return [2], returns: " + Arrays.toString(test.getPercentileRange(arr8, 0, 10)));
		System.out.println("Should return [4], returns: " + Arrays.toString(test.getPercentileRange(arr8, 10, 20)));
		System.out.println("Should return [4,6,8,10], returns: " + Arrays.toString(test.getPercentileRange(arr8, 10, 50)));
		System.out.println("Should return [14], returns: " + Arrays.toString(test.getPercentileRange(arr8, 60, 70)));
		System.out.println("Should return [2,4,6,8,10,12,14,16,18,20], returns: " + Arrays.toString(test.getPercentileRange(arr8, 0, 100)));
	}
}
