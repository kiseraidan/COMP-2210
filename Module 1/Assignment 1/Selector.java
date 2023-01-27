import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Aidan Kiser (ark0053@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  26 January 2023
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {

        // Throws IAExceptions if array is null or length zero.
      if (a == null) {
         throw new IllegalArgumentException();
      }
      if (a.length == 0) {
         throw new IllegalArgumentException();
      }

      int min = a[0]; // Sets the minimum to the first index in the array.
      
      /* Searches the array for a value less than the current minimum. 
       * If such value exists, the new value is set as the new minimum and is returned.
       */
      for (int i = 0; i < a.length; i++) {
        if (a[i] < min) {
            min = a[i];
        }
      }
      
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
     
        // Throws IAExceptions if array is null or length zero.
        if (a == null) {
            throw new IllegalArgumentException();
         }
         if (a.length == 0) {
            throw new IllegalArgumentException();
         }
   
         int max = a[0]; // Sets the maximum to the first index in the array.
         
         /** Searches the array for a value greater than the current maximum. 
          * If such value exists, the new value is set as the new maximum and is returned.
          */
         for (int i = 0; i < a.length; i++) {
           if (a[i] > max) {
               max = a[i];
           }
         }
         
         return max;
      }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {

        /* Throws IAExceptions if array is null, length zero, or if k is less 
         than zero, or if k is larger than the length of the original array. */
        if (a == null) {
            throw new IllegalArgumentException();
         }
         if (a.length == 0) {
            throw new IllegalArgumentException();
         }
         if (k < 1 || k > a.length) {
            throw new IllegalArgumentException();
         }

         // Searches the original array for values that occur multiple times.
         int undistictValues = 0;
         for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == a[i + 1]) {
                undistictValues++;
            }
         }

         /* Throws IAException if k is greater than the than the number of 
         distinct values in the original array. */
         if (k > a.length - undistictValues) {
            throw new IllegalArgumentException();
         }

         // Copies array original array and sorts in ascending order.
         int[] b = Arrays.copyOf(a, a.length);
         Arrays.sort(b);
         // Searches the new array for kmin.
         for (int i = 0; i < k - 1; i++) {
            if (b[i] == b[i + 1]) {
                k++;
            }
         }

         int kmin = b[k - 1];

      return kmin;
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      
        /* Throws IAExceptions if array is null, length zero, or if k is less 
         than zero, or if k is larger than the length of the original array. */
         if (a == null) {
            throw new IllegalArgumentException();
         }
         if (a.length == 0) {
            throw new IllegalArgumentException();
         }
         if (k < 1 || k > a.length) {
            throw new IllegalArgumentException();
         }

         // Searches the original array for values that occur multiple times.
         int undistictValues = 0;
         for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == a[i + 1]) {
                undistictValues++;
            }
         }

         /* Throws IAException if k is greater than the than the number of 
         distinct values in the original array. */
         if (k > a.length - undistictValues) {
            throw new IllegalArgumentException();
         }

         // Copies array original array and sorts in ascending order.
         int[] b = Arrays.copyOf(a, a.length);
         
         for (int i = 0; i < b.length; i++) {
            b[i]= b[i] * - 1;
         }

         // Sorts the new array in descending order.
         Arrays.sort(b);
         for (int i = 0; i < b.length; i++) {
            b[i]= b[i] * - 1;
         }

        // Searches the new array for kmin.
         for (int i = 0; i < k - 1; i++) {
            if (b[i] == b[i + 1]) {
                k++;
            }
         }

         int kmax = b[k - 1];

      return kmax;
   }



   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {

    // Throws IAExceptions if array is null or length zero.
    if (a == null) {
        throw new IllegalArgumentException();
     }
     if (a.length == 0) {
        throw new IllegalArgumentException();
     }

     // Finds the amount within the range.
    int count = 0;
    for (int i = 0; i < a.length; i++) {
        if (a[i] >= low && a[i] <= high) {
            count++;
        }
    }

    // Creates the array of range and fills it.
    int[] range = new int[count];
    int j = 0;
    for (int i = 0; i < a.length; i++) {
        if (a[i] >= low && a[i] <= high) {
            range[j] = a[i];
            j++;
        }
    }

      return range;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      
    // Throws IAExceptions if array is null or length zero.
    if (a == null) {
        throw new IllegalArgumentException();
     }
     if (a.length == 0) {
        throw new IllegalArgumentException();
     }
    
     // Sets the ceiling to the current max of a using the max method.
     int ceiling = Selector.max(a); 
     // Sets the ceiling as not found.
     boolean ceilingFound = false;

    /** Searches for the ceiling and if a new ceiling is found, 
     * the ceiling will be reset. */
     for (int i = 0; i < a.length; i++) {
        if (a[i] >= key && a[i] <= ceiling) {
            ceiling = a[i];
            ceilingFound = true;
        }
    }

    // Throws IAException if a ceiling is not found.
    if (!ceilingFound) {
        throw new IllegalArgumentException();
    }
    
    return ceiling;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      
    // Throws IAExceptions if array is null or length zero.
    if (a == null) {
        throw new IllegalArgumentException();
     }
     if (a.length == 0) {
        throw new IllegalArgumentException();
     }
    
     // Sets the floor to the current min of a using the min method.
     int floor = Selector.min(a);
     // Sets the floor as not found.
     boolean floorFound = false;

     /** Searches for the ceiling and if a new ceiling is found, 
     * the ceiling will be reset. */
     for (int i = 0; i < a.length; i++) {
        if (a[i] <= key && a[i] >= floor) {
            floor = a[i];
            floorFound = true;
        }
    }

    // Throws IAException if a floor is not found.
    if (!floorFound) {
        throw new IllegalArgumentException();
    }
    
    return floor;
   }

}
