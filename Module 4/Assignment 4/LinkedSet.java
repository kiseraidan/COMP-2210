import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Aidan Kiser (ark0053@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
      
      Node n = new Node(element);
      Node n2 = new Node(element);
      
      if (isEmpty()) {
         
         front = n;
         rear = n;
         size++;
         
         return true;
      }
      
      n = front;
      
      // If the next element is valid
      while (n.element.compareTo(element) < 0 && n.next != null) {
      
         n = n.next;
      }
      
      // If duplicate
      if (n.element.compareTo(element) == 0) {
         
         return false;
      }
      
      // If n2 belongs after n
      else if (n.element.compareTo(element) < 0) {
      
         if (n.next == null) {
         
            rear = n2;
            
            n2.prev = n;
            n.next = n2;
         }
            
         else {
         
            n2.next = n.next;
            n.next.prev = n2;
            n2.prev = n;
            n.next = n2;
         }
            
         size++;
         
         return true;
      }
      
      // If n2 belongs before n
      else {
      
         if (n == front) {
         
            front = n2;
            n2.next = n;
            n.prev = n2;
         }
         
         else {
         
            n2.prev = n.prev;
            n.prev.next = n2;
            n2.next = n;
            n.prev = n2;
         }
            
         size++;
      
         return true;
      }
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
      
      Node n = new Node(element);
      
      if (isEmpty()) {
      
         return false;
      }
       
      n = front;
       
      while (n.element.compareTo(element) != 0 && n.next != null) {
       
         n = n.next;
      }
      
      if (n.element.compareTo(element) == 0) {
      
         if (n== front && n == rear) {
         
            front = null;
            rear = null;
            
            size--;
            
            return true;
         }
      
         if (n == front) {
         
            front = n.next;
            n.next.prev = null;
         }
         
         else if (n == rear) {
         
            rear = n.prev;
            n.prev.next = null;
         }
         
         else {
         
            n.prev.next = n.next;
            n.next.prev = n.prev;
         }
         
         size--;
      
         return true;
      }
      
      else {
      
         return false;
      }   
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
      
      Node n = new Node(element);
      
      if (isEmpty()) {
      
         return false;
      }
         
      n = front;
      
      while (n.element.compareTo(element) != 0 && n.next != null) {
      
         n = n.next;
      }
         
      if (n.element.compareTo(element) == 0) {
      
         return true;
      }
         
      else {
      
         return false;
      }
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      
      if (this.size() != s.size()) {
      
         return false;
      }
         
      for (T e : s) {
      
         if (!this.contains(e)) {
         
            return false;
         }
      }
         
      return true;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      
      Node n = this.front;
      Node n2 = s.front;
      
      while (n != null && n2 != null && n.element.compareTo(n2.element) == 0) {
      
         n = n.next;
         n2 = n2.next;
      }
         
      if (n == null && n2 == null) {
      
         return true;
      }
         
      else {
      
         return false;
      }
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){
      
      LinkedSet<T> linkedSet = new LinkedSet<>();
      
      for (T e : this) {
      
         linkedSet.addRear(e);
      }
         
      for (T e : s) {
      
         linkedSet.add(e);
      }
         
      return linkedSet;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
      
      if (this.isEmpty() && s.isEmpty()) {
         
         return new LinkedSet<T>();
      }
      
      else if (this.isEmpty()) {
      
         return s;
      }
            
      else if (s.isEmpty()) {
         
         return this;
      }
      
      LinkedSet <T> linkedSet = new LinkedSet<>();
      Node n = this.front;
      Node n2 = s.front;
      
      if (n.element.compareTo(n2.element) == 0) {
      
         linkedSet.front = new Node(n.element);
         n = n.next;
         n2 = n2.next;
      }
         
      else if (n.element.compareTo(n2.element) < 0) {
      
         linkedSet.front = new Node(n.element);
         n = n.next;
      }
         
      else {
      
         linkedSet.front = new Node(n2.element);
         n2 = n2.next;
      }
         
      linkedSet.size++;
      Node n3 = linkedSet.front;
      
      while (n != null && n2 != null) {
      
         if (n.element.compareTo(n2.element) == 0) {
            
            Node newNode = new Node(n.element);
            n3.next = newNode;
            newNode.prev = n3;
            
            n3 = n3.next;
            n = n.next;
            n2 = n2.next;
         }
            
         else if (n.element.compareTo(n2.element) < 0) {
         
            Node newNode = new Node(n.element);
            n3.next = newNode;
            newNode.prev = n3;
            
            n3 = n3.next;
            n = n.next;
         }
            
         else {
         
            Node newNode2 = new Node(n2.element);
            n3.next = newNode2;
            newNode2.prev = n3;
            
            n3 = n3.next;
            n2 = n2.next;
         }
            
         linkedSet.size++;
      }
         
      if (n != null) {
      
         while (n != null) {
         
            Node newNode = new Node(n.element);
            n3.next = newNode;
            newNode = n3;
            
            n3 = n3.next;
            n = n.next;
            
            linkedSet.size++;
         }
      }
         
      else if (n2 != null) {
      
         while (n2 != null) {
         
            Node newNode2 = new Node(n.element);
            n3.next = newNode2;
            newNode2 = n3;
            
            n3 = n3.next;
            n2 = n2.next;
            
            linkedSet.size++;
         }
      }
            
      linkedSet.rear = n2;
         
      return linkedSet;   
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      
      if (this.isEmpty() || s.isEmpty()) {
      
         return new LinkedSet<T>();
      }
         
      LinkedSet<T> linkedSet = new LinkedSet<>();
      
      for (T e : this) {
      
         if (s.contains(e)) {
         
            linkedSet.addRear(e);
         }
      }
            
      return linkedSet;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      
      if (this.isEmpty() || s.isEmpty()) {
      
         return new LinkedSet<T>();
      }
         
      LinkedSet<T> linkedSet = new LinkedSet<>();
      Node n = this.front;
      Node n2 = s.front;
      
      while (n != null && n2 != null) {
      
         int comp = n.element.compareTo(n2.element);
         
         if (comp < 0) {
         
            n = n.next;
         }
            
         else if (comp == 0) {
         
            linkedSet.addRear(n.element);
            n = n.next;
            n2 = n2.next;
         }
            
         else {
         
            n2 = n2.next;
         }
      }
            
      return linkedSet;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      
      if (this.isEmpty()) {
      
         return new LinkedSet<T>();
      }
         
      if (s.isEmpty()) {
      
         return this;
      }
         
      LinkedSet<T> linkedSet = new LinkedSet<>();
      
      for (T e : this) {
      
         if (!s.contains(e)) {
         
            linkedSet.addRear(e);
         }
      }
            
      return linkedSet;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      
      if (this.isEmpty()) {
      
         return new LinkedSet<T>();
      }
         
      if (s.isEmpty()) {
      
         return this;
      }
      
      LinkedSet<T> linkedSet = new LinkedSet<>();
      Node n = this.front;
      Node n2 = s.front;
      
      while (n != null && n2 != null) {
      
         int comp = n.element.compareTo(n2.element);
         
         if (comp < 0) {
         
            linkedSet.addRear(n.element);
            n = n.next;
         }
            
         else if (comp == 0) {
         
            n = n.next;
            n2 = n2.next;
         }
            
         else {
         
            n2 = n2.next;
         }
      }
            
      if (n2 == null) {
      
         while (n != null) {
         
            linkedSet.addRear(n.element);
            n = n.next;
         }
      }
            
      return linkedSet;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      
      return new LinkedSetIterator<T>(this);
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      
      return new LinkedSetDecIterator(this);
   }


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      
      return new PowerSetIterator(this);
   }



   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.

   // Use for when data belongs at the rear and is not a duplicate
   private boolean addRear(T element) {
   
      Node n = new Node(element);
      Node n2 = new Node(element);
      
      if (isEmpty()) {
      
         front = n;
         rear = n;
         
         size++;
         
         return true;
      }
         
      n2 = rear;
      n2.next = n;
      n.prev = n2;
      rear = n;
      
      size++;
      
      return true;
   }
   
   class LinkedSetIterator<E> implements Iterator<T>{
      
      Node n;
      
      public LinkedSetIterator(LinkedSet<T> s) {
         
         n = s.front;
      }
      
      @Override
        public boolean hasNext() {
         
         return (n != null);
      }
      
      @Override
        public T next() {
         
         T out = (T) n.element;
         n = n.next;
         return out; 
      }
   }
   
   class LinkedSetDecIterator implements Iterator<T>{
      
      Node n;
      
      public LinkedSetDecIterator(LinkedSet<T> s)
      {
         n = s.rear;
      }
   
      @Override
        public boolean hasNext() {
         
         return (n != null);
      }
      @Override
        public T next() {
         
         T out = (T) n.element;
         n = n.prev;
         
         return out; 
      }
   }
   
   class PowerSetIterator implements Iterator<Set<T>>{
      
      int curr; 
      int max;
      char[] b_curr;
      Node front; 
      Node n;
      
      public PowerSetIterator(LinkedSet<T> s){
         curr = 0;
         b_curr = Integer.toBinaryString(curr).toCharArray();
         assert s.size() >= 0; //prevent fractional max
         max = (int) Math.pow(2, s.size());
         front = s.front;
      }
      
      @Override
        public boolean hasNext() {
         
         return (curr < max);
      }
      
      @Override
        public Set<T> next() { 
         LinkedSet<T> out = new LinkedSet<>();
         n = front;
         for (int i = b_curr.length - 1; i >= 0; i--){
            if (b_curr[i] == '1'){
               out.add(n.element);
            }
            if (n != null){
               n = n.next;
            }
         }
         
         b_curr = Integer.toBinaryString(++curr).toCharArray();
         
         return out; 
      }
   
        
   }
   ////////////////////
   // Nested classes //
   ////////////////////

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
