package assignment2AADS.Exercise3;

import java.util.Arrays;

public class MyMeasure implements A2Measure {

	// Methods could be static, so this would not be needed
	public MyMeasure() {
	}

	/**
	 * Calculates if two collections are the same (same length, same elements
	 * and same number of elements)
	 */
	@Override
	public boolean isSameCollection(int[] array1, int[] array2) {
		// If length is not equal, don't continue
		if (array1.length != array2.length)
			return false;

		// Stores arr1[] elements and count in hash map
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(array1.length);
		int counter = 0;
		for (int i = 0; i < array1.length; i++) {
			// Element doesn't exist
			if (map.get(array1[i]) == null)
				map.put(array1[i], 1);
			// Element already exists, so simply increase its count
			else {
				counter = map.get(array1[i]);
				counter++;
				map.put(array1[i], counter);
			}
		}

		// checks if all elements of arr2[] are present same number
		// of times
		for (int i = 0; i < array1.length; i++) {
			// check if there is an element in arr2[], but
			// not in arr1[]
			if (!map.containsKey(array2[i]))
				return false;

			// check if an element of arr2[] appears more
			// times than it appears in arr1[]
			if (map.get(array2[i]) == 0)
				return false;

			counter = map.get(array2[i]);
			--counter;
			map.put(array2[i], counter);
		}

		// check if count of elements becomes zero
		for (int i = 0; i < array1.length; i++) {
			if (map.get(array2[i]) > 0)
				return false;
		}
		return true;
	}

	/**
	 * Calculates the sum of differences from smallest element 
	 * to largest of two arrays.
	 */
	@Override
	public int minDifferences(int[] array1, int[] array2) {
		// Get max value of both arrays
		int max1 = getMax(array1);
		int max2 = getMax(array2);
		// Get number of digits of max value
		int length1 = (int)(Math.log10(max1)+1);
		int length2 = (int)(Math.log10(max2)+1);
		
		// Decide if sort array1 with radix or merge
		if (length1 >= array1.length) {
			mergeSort(array1);
		}
		else {
			radixSort(array1);
		}
		
		// Decide if sort array2 with radix or merge
		if(length2 >= array2.length) {
			mergeSort(array2);
		}
		else {
			radixSort(array2);
		}

		
		int finalSum = 0;
		// Calculate sum
		for (int i = 0; i < array1.length; i++) { // o(n)
			finalSum = finalSum + (int) Math.pow(array1[i] - array2[i], 2);
		}

		return finalSum;
	}

	/**
	 * Gets elements in a provided percentile range
	 * of a sorted array (given array might not be sorted)
	 */
	@Override
	public int[] getPercentileRange(int[] arr, int lower, int upper) {
		// since lower ≤ upper
		if (lower > upper) {
			return null;
		}
		
		// Wrong input provided
		if (lower < 0 || upper > 100) {
			return null;
		}
		
		// If number of digits of largest number of the array
		// is less than the lenght of the array, radix sorting is used. Otherwise
		// - mergeSort. It is switched to merge sort as radix sort complexity
		// is O(kn), where k is the number of digits of the largest number
		// and n is the length of the array. if k = n, that O(n^2), in which
		// case merge sort is better, as it offers o(n log n) worst case
		int max = getMax(arr);
		int length = (int)(Math.log10(max)+1);
		if (length >= arr.length) {
			mergeSort(arr);
		}
		else {
			radixSort(arr);
		}

		// calculate lowest and highest position in regards to 
		// array length
		int lowerPos = Math.abs((arr.length * lower) / 100); 
		int upperPos = Math.abs((arr.length * upper) / 100);
		int[] result = new int[upperPos - lowerPos];
		int counter = 0;

		// calculate result by going through every single item
		for (int i = lowerPos; i < upperPos; i++) {
			result[counter] = arr[i];
			counter++;
		}

		return result;
	}

	/*
	 * Internal methods
	 */
	// Ideas from the course book
	private void mergeSort(int[] a) {
		if (a.length <= 1) {
			return;
		}
		// New array of same length as original
		int[] tmp = new int[a.length];
		mergeSort(a, tmp, 0, a.length - 1);
	}

	private void mergeSort(int[] a, int[] tmp, int left, int right) {
		// If there are still positions left to split the array from
		if (left < right) {
			// Get middle
			int center = (left + right) / 2;
			// Take left part of the array (left of part of the array)
			mergeSort(a, tmp, left, center);
			// Take right part in same logic
			mergeSort(a, tmp, center + 1, right);
			// Eventually join the two parts together
			merge(a, tmp, left, center + 1, right);
		}
	}

	private void merge(int[] a, int[] tmp, int left, int right, int rightEnd) {
		int leftEnd = right - 1; // where left side should end
		int k = left; // counter of left size
		int num = rightEnd - left + 1; // length of array

		// while left counter hasn't reached its end
		// and right counter hasn't reached its end
		while (left <= leftEnd && right <= rightEnd) {
			// if element on the left is smaller, then 
			// left element is moved into temporary array
			// otherwise right element is moved into temporary array
			if (a[left] < a[right]) {
				tmp[k++] = a[left++];
			} else {
				tmp[k++] = a[right++];
			}
		}

		// some elements on left side might have been left over
		while (left <= leftEnd) {
			tmp[k++] = a[left++];
		}

		// some elements on right sidee might have been left over
		while (right <= rightEnd) {
			tmp[k++] = a[right++];
		}

		// copy tmp back
		for (int i = 0; i < num; i++, rightEnd--) {
			a[rightEnd] = tmp[rightEnd];
		}
	}
	
	private int getMax(int[] arr) {
		int max = arr[0]; 
		// First, we get the maximum number of the array
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i]; 
            }
        }
        return max;
	}
	
	private void radixSort(int[] arr) {
		if (arr.length <= 1) {
			return;
		}
		
		int max = getMax(arr);
        int growth = 1;
        int[] result = new int[arr.length]; // output array 
        int[] buckets = new int[10]; 
        
        // Start will all 0 counts
        for (int i = 0; i < result.length; i++) {
        	result[i] = 0;
        }
        
        // Go through all the digits
        // this will run as many times
        // as the largest number has digits
        while (max/growth > 0) {
        	//Resetting array for each iteration
            result = new int[arr.length]; 
            buckets = new int[10];
        	
        	// Calculate how many times certain digit
        	// appeared
        	for (int i = 0; i < arr.length; i++) {
        		buckets[ (arr[i]/growth)%10 ]++; 
        	}
            // Change buckets[i] so that buckets[i] now contains 
            // actual position
            for (int i = 1; i < 10; i++) {
            	buckets[i] += buckets[i - 1]; 
            }
            // Create an output array using buckets[] array
            // information
            for (int i = arr.length - 1; i >= 0; i--){ 
            	result[buckets[(arr[i]/growth)%10] - 1] = arr[i]; 
                buckets[ (arr[i]/growth)%10 ]--; 
            }
            // Finally, update original array
            for (int i = 0; i < arr.length; i++) {
            	arr[i] = result[i];
            }
            
            growth++;
        }
	}
}
