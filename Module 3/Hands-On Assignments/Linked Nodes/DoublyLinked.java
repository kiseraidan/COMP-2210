/**
 * DoublyLinked.java
 * Sample code for working doubly-linked nodes. The Node class is defined
 * as an inner class, so direct access to the fields of Node is available.
 *
 * 
 * 
 *
 */
public class DoublyLinked {

    /**
     * Call a method to perform example operations on nodes.
     */
    public static void main(String[] args) {

        DoublyLinked client = new DoublyLinked();
        client.basicExamples();
        client.add();
        client.delete();
    }

    /**
     * Defines a doubly-linked node.
     * Type Object is used instead of a generic type to make
     * this example cleaner and easier to read. If this class
     * were to be used in the context of implementing a collection,
     * generic types should be used.
     */
    private class Node {
        private Object element;
        private Node next;
        private Node prev;

        public Node(Object e) {
            element = e;
        }
    }
