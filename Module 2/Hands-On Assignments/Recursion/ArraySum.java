/*
 * Directions:
 * In the starter code ArraySum.java you are provided a method with the
 * following signature:
 * public static int sum(int[] a, int left, int right) 
 * You are to complete the body of this method so that it returns the sum of 
 * the values in array a from index left to index right inclusive. You must 
 * use recursion to implement the method body. The table below provides example
 * values for these parameters and the expected return value of the method.
 */

/**
 * Provides a sum function on arrays.
 *
 * @author Aidan Kiser
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 9 February 2023
 */
public class ArraySum {

	/** Returns the sum of values in the given array. */
	public static int sum(int[] a, int left, int right) {
		if (left > right) {
            return 0;
        }
        return a[left] + sum(a, ++left, right);
	}
}
