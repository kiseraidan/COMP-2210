import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Aidan Kiser (ark0053@auburn.edu)
 * @version 13 April 2023
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   /////////////////////////////////////////////////////////////////////////////
   // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
   // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
   // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
   // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
   // table with chaining).
   /////////////////////////////////////////////////////////////////////////////

   TreeSet<String> lexicon = new TreeSet<String>();

   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         
         //////////////////////////////////////
         // INSTANTIATE lexicon OBJECT HERE  //
         //////////////////////////////////////
         
         lexicon = new TreeSet<String>();
      
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         
         while (s.hasNext()) {
            String str = s.next();
         
            /////////////////////////////////////////////////////////////
            // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
            /////////////////////////////////////////////////////////////
         
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         in.close();
      }
      
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////

   public int getWordCount() {
      return lexicon.size();
   }

   public boolean isWord(String str) {
      return lexicon.contains(str.toLowerCase());
   }

   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
         return -1;
      }
      int count = 0;
      for (int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            count++;
         }
      }
      return count;
   }

   public List<String> getNeighbors(String word) {
      List<String> neighbors = new ArrayList<String>();
      for (String s : lexicon) {
         if (getHammingDistance(word, s) == 1) {
            neighbors.add(s);
         }
      }
      return neighbors;
   }

   public boolean isWordLadder(List<String> sequence) {
      if (sequence == null || sequence.isEmpty()) {
         return false;
      }
      if ((!isWord(sequence.get(0)))) {
         return false;
      }
      for (int i = 0; i < sequence.size(); i++) {
         if (getHammingDistance(sequence.get(i), sequence.get(i - 1)) != 1) {
            return false;
         }
         if (!isWord(sequence.get(i))) {
            return false;
         }
      }
      return true;
   }

   public List<String> getMinLadder(String start, String end) {
      List<String> list = new ArrayList<String>();
      if (start == null || end == null) {
         return list;
      }
      if (getHammingDistance(start, end) == -1 || !isWord(start) || !isWord(end)) {
         return list;
      }
      
      start.toLowerCase();
      end.toLowerCase();
      List<String> ladder = new ArrayList<String>();
      
      if (start.equals(end)) {
         ladder.add(end);
         return list;
      }
   
      ArrayDeque<Node> queue = new ArrayDeque<Node>();
      HashSet<String> visited = new HashSet<String>();
   
      Node startNode = new Node(start);
      visited.add(startNode.word);
      queue.add(startNode);
   
      while (!queue.isEmpty()) {
         Node currNode = queue.removeFirst();
         List<String> neighbors = currNode.neighbors;
         
         for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
               Node neighborNode = new Node(neighbor, currNode);
               visited.add(neighbor);
               queue.add(neighborNode);
               if (neighborNode.equals(end)) {
                  return makeList(new Node(neighborNode, currNode));
               }
            }
         }
      }
      return list;
   }

   //////////////////////////////////////////////////////////////
   ////////////////////// PRIVATE METHODS ///////////////////////
   //////////////////////////////////////////////////////////////

   private class Node {
      private String word;
      private Node next;
      private List<String> neighbors;
      
      public Node(String word) {
         this.word = word;
         this.neighbors = getNeighbors(this.word);
      }

      public Node(String w, Node n) {
         this.word = w;
         this.next = n;
         this.neighbors = getNeighbors(this.word);
      }
   }

   private List<String> makeList(Node n) {
      List<String> list = new LinkedList<String>();
      String word = n.word;
      Node prev = n.next;
      list.add(word);

      while (prev != null) {
        word = prev.word; 
        list.add(0, word);
         prev = prev.next;
      }
      return list;
   }
}
