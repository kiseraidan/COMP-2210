/**
 * Directions:
 * BinarySearch provides two search methods, both of which are variants of the 
 * classic binary search presented in lecture. When binary searching a sorted 
 * array that contains more than one key equal to the search key, the client 
 * may want to know the index of either the first or last matching key.
 * Both methods must use the binary search algorithm and make on the order of 
 * log N comparisons to a[middle] where N is the number of elements in a[].
 * 
 * @author Aidan Kiser
 * @version 25 February 2023
 */

 import java.util.Comparator;

 public class BinarySearch {
 
     /**
      * Returns the index of the first key in a[] that equals the search key, 
      * or -1 if no such key exists. This method throws a NullPointerException
      * if any parameter is null.
      */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) { 
       if (a == null || key == null || comparator == null) {
          throw new NullPointerException();
       }
    
       int left = 0;
       int right = a.length;
    
       while (left <= right) {
          int mid = left + (right - left) / 2;
             
          if (mid == 0) {
             return mid;
          }
             
          if (comparator.compare(a[mid], key) > 0) {
             right = mid - 1;
          }
          else if (comparator.compare(a[mid], key) < 0) {
             left = mid + 1;
                 
          }
          else if (comparator.compare(a[mid-1], a[mid]) == 0) {
             right = mid - 1;
          }
          else {
             return mid;
          }
       }
       return -1;
    }
 
     /**
      * Returns the index of the last key in a[] that equals the search key, 
      * or -1 if no such key exists. This method throws a NullPointerException
      * if any parameter is null.
      */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) { 
       if (a == null || key == null || comparator == null) {
          throw new NullPointerException();
       }
    
       int left = 0;
       int right = a.length;
         
       while (left <= right) {
          int mid = left + (right - left) / 2;
           
          if (comparator.compare(a[a.length-1], key) == 0) {
             return a.length - 1; 
          }
           
          if (comparator.compare(key, a[mid]) < 0) {
             right = mid - 1;
          }
          else if (comparator.compare(key, a[mid]) > 0) {
             left = mid + 1;
          }
          else if (comparator.compare(a[mid + 1], a[mid]) == 0) {
             left = mid + 1;
          }
          else {
             return mid;
          }
       }
       return -1;
    }
 }