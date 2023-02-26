/**
 * Directions:
 * Term is used to represent a (query, weight) pair. You must write this class
 * so that it is completely consistent with the following API.
 * Note that Term supports comparison by three different orders:
 * (1) lexicographic order (the natural order), descending order of weight
 * (one alternate ordering), and lexicographic order using only the first 
 * length characters of the query string (a family of alternate orderings). By 
 * providing three different comparison methods, we are able to sort terms in 
 * three different orders. All three orders will be important in delivering 
 * the autocomplete functionality.
 * 
 * @author Aidan Kiser
 * @version 25 February 2023
 */

 import java.util.Comparator;

 public class Term implements Comparable<Term> {
 
     /**
      * Initialize a term with the given query and weight.
      * This method throws a NullPointerException if query is null,
      * and an IllegalArgumentException if weight is negative.
      */
     
    private String queryIn;
    private long weightIn;
     
    public Term(String query, long weight) { 
       queryIn = query;
       weightIn = weight;
         
       if (query.equals(null)) {
          throw new NullPointerException();
       }
         
       if (weight < 0) {
          throw new IllegalArgumentException();
       }
    }
 
     /**
      * Compares the two terms in descending order of weight.
      */
    public static Comparator<Term> byDescendingWeightOrder() { 
       return new WeightOrder();
    }
     /**
      * Compares the two terms in ascending lexicographic order of query,
      * but using only the first length characters of query. This method
      * throws an IllegalArgumentException if length is less than or equal
      * to zero.
      */
    public static Comparator<Term> byPrefixOrder(int length) { 
       if (length <= 0) {
          throw new IllegalArgumentException();
       }
         
       return new PrefixOrder(length);
    }
 
     /**
      * Compares this term with the other term in ascending lexicographic order
      * of query.
      */
    @Override
     public int compareTo(Term other) { 
       if (queryIn.compareTo(other.queryIn) > 0) {
          return 1;
       }
         
       if (queryIn.compareTo(other.queryIn) < 0) {
          return -1;
       }
         
       return 0;
    }
     
    public long getWeight() {
       return weightIn;
    }
     
    public String getQuery() {
       return queryIn;
    }
 
    public static class WeightOrder implements Comparator<Term> {
       public int compare(Term a, Term b) {
          if (a.getWeight() > b.getWeight()) {
             return -1;
          }
           
          if (a.getWeight() < b.getWeight()) {
             return 1;
          }
          return 0;
       }
    }
     
    public static class PrefixOrder implements Comparator<Term> {
       private int lengthIn;
    
       public PrefixOrder(int length) {
          lengthIn = length;
       }
         
       public int compare (Term a, Term b) {
          String a1 = a.getQuery().substring(0, Math.min(a.getQuery().length(), lengthIn));
          String a2 = b.getQuery().substring(0, Math.min(b.getQuery().length(), lengthIn));
          return a1.compareTo(a2);
       }
    }
     /**
      * Returns a string representation of this term in the following format:
      * query followed by a tab followed by weight
      */
    @Override
     public String toString() { 
       return queryIn + "\t" + weightIn;
    }
 
 }