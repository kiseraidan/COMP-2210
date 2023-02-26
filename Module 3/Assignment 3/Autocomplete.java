/**
 * Directions:
 * Autocomplete uses the Term and BinarySearch class to provide complete 
 * autocomplete functionality for a given set of strings and weights.
 * 
 * The constructor must store the contents of terms in its own internal array 
 * and then sort this array in natural order (lexicographic order of query). 
 * The allMatches method must use the binary search methods to identify the 
 * range of methods that begin with the given prefix and return these elements 
 * in an array sorted in descending order of weight. This returned array 
 * represents the predicted completions for the given prefix.
 * 
 * @author Aidan Kiser
 * @version 25 February 2023
 */

 import java.util.Arrays;
 import java.util.Comparator;
 
 public class Autocomplete {
 
     /**
      * Initializes a data structure from the given array of terms.
      * This method throws a NullPointerException if terms is null.
      */
     
    private Term[] termIn;
    public Autocomplete(Term[] terms) { 
       if (terms == null) {
          throw new NullPointerException();
       }
        
       termIn = new Term[terms.length];
       for (int i = 0; i < terms.length; i++) {
          termIn[i] = terms[i];
       }
    }
 
     /** 
      * Returns all terms that start with the given prefix, in descending order of weight. 
      * This method throws a NullPointerException if prefix is null.
      */
    public Term[] allMatches(String prefix) {
       if (prefix == null) {
          throw new NullPointerException();
       }
        
       Comparator<Term> comparator = Term.byPrefixOrder(prefix.length());
         
       int beg = BinarySearch.<Term>firstIndexOf(termIn, new Term(prefix, 0), comparator);
       int last = BinarySearch.<Term>lastIndexOf(termIn, new Term(prefix, 0), comparator);
        
       Term[] matches = Arrays.copyOfRange(termIn, beg, last + 1);
       Arrays.sort(matches, Term.byDescendingWeightOrder());
        
       return matches;
    }
 }
