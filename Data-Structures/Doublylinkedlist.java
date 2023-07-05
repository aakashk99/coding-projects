/**
 * The Doublylinkedlist Class implements a functioning Doubly Linked List Data Structure
 * 
 * @author Aakash Karlekar
 * @version 1.0
 * @since 2023-07-05
 */

public class Doublylinkedlist <T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;

    /**
     * Private Class used to Create the Nodes of the Doubly Linked List
     */
    private class Node <T> {
        public T value;
        public Node<T> next;
        public Node<T> prev;

        public Node(T value, Node<T> next, Node<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public String toString() {
            return ""+this.value;
        }
    }

    /**
     * Single-Argument Constructor that instantiates a Doubly Linked List with value as its head
     * @param value
     */
    public Doublylinkedlist(T value) {
        Node<T> firstNode =  new Node(value, null, null);
        this.head = firstNode; 
        this.tail = firstNode;
        this.length = 1;
    }

    /**
     * No-Argument Constructor that instantiates an empty Doubly Linked List
     */
    public Doublylinkedlist() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    /**
     * Appends value to the Tail of the Doubly Linked List 
     * @param value
     */
    public void append(T value) {
        if (this.head == null) {
            Node<T> firstNode = new Node(value, null, null);
            this.head = firstNode;
            this.tail = firstNode;
            this.length++;
        }
        else if (this.length == 1) {
           Node<T> newNode = new Node(value, null, this.head);
           this.head.next = newNode; 
           this.tail = newNode;
           this.length++;
        }
        else {
           Node<T> newNode = new Node(value, null, this.tail);
           this.tail.next = newNode;
           this.tail = newNode;
           this.length++;
        }
    }

    /**
     * Inserts value at the head of the Doubly Linked List
     * @param value
     */
    public void insert(T value) {
        if (length == 0) {
            Node<T> newHead = new Node(value, null, null);
            this.head = newHead;
            this.tail = newHead;
        }
        else {
            Node<T> newHead = new Node(value, this.head, null);
            this.head.prev = newHead;
            this.head = newHead;
        }
        this.length++;
    }

    /**
     * Inserts value at the specified index within the Doubly Linked List
     * Elements previously at or to the right of index are shifted one index to the right 
     * @param index
     * @param value
     * @throws RuntimeException if Doubly Linked List is Empty
     * @throws IllegalArgumentException if specified index is invalid for the Doubly Linked List
     */
    public void insertAt(int index, T value) {
        if (length == 0) {
            throw new RuntimeException("Empty List");
        }
        else if (index < 0 || index > this.length-1) {
            throw new IllegalArgumentException("Index must be valid for this Linked List");
        }
        else if (index == 0) 
            insert(value);
        else {
            Node<T> newValue = new Node(value,null, null);
            Node<T> pointer1 = this.head;
            Node<T> pointer2 = this.head;
            int count = 0;
            while (count != index-1) {
                pointer1 = pointer1.next;
                pointer2 = pointer2.next;
                count++;
            }
            pointer2 = pointer2.next;
            newValue.next = pointer2;
            newValue.prev = pointer1;
            pointer1.next = newValue;
            pointer2.prev = newValue;
            length++;
        }
    }

    /**
     * Replaces the current value at index with the specified value
     * @param index
     * @param value
     * @throws RuntimeException if the Doubly Linked List is Empty
     * @throws IllegalArgumentException if the index is invalid for the Doubly Linked List
     */
    public void replace(int index, T value) {
        if (this.length == 0) 
            throw new RuntimeException("Empty List");
        if (index >= this.length || index < 0) {
            throw new IllegalArgumentException("Index must be valid for this Linked List");
        }
        Node<T> pointer = this.head;
        int count = 0;
        while (count != index) {
            pointer = pointer.next;
            count++;
        }
        pointer.value = value;
    }

    /**
     * Removes the node located at the specified index 
     * @param index
     * @throws RuntimeException if the Doubly Linked List is Empty
     * @throws IllegalArgumentException if the index is invalid for the Doubly Linked List
     */
    public void removeAt(int index) {
        if (length == 0) {
            throw new RuntimeException("Empty List");
        }
        if (index < 0 || index >= this.length) {
            throw new IllegalArgumentException("Index must be valid for this linked list");
        }
        if (index == 0) {
            Node<T> pointer = this.head.next;
            this.head = null;
            this.head = pointer;
            this.length--;
        }
        else if (index == this.length-1) {
            this.pop();
        }
        else {
            Node<T> pointer = this.head;
            int count = 0;
            while (count != index) {
                pointer = pointer.next;
                count++;
            }
            pointer.prev.next = pointer.next;
            pointer.next.prev = pointer.prev;
            pointer = null;
            this.length--;
        }
    }

    /**
     * Removes the Node located at the Tail of the Doubly Linked List
     * @throws RuntimeException if the Doubly Linked List is empty
     */
    public void pop() {
        if (this.length == 0) 
            throw new RuntimeException("Empty List");
        Node<T> pointer = this.tail.prev;
        pointer.next = null;
        this.tail = null;
        this.tail = pointer;
        this.length--;
    }

    /**
     * Removes the node located at the head of the Doubly Linked List
     * @throws RuntimeException if the Doubly Linked List is empty
     */
    public void removeHead() {
        if (length == 0) 
            throw new RuntimeException("Empty List");
        removeAt(0);
    }

    /**
     * Removes the first instance of value from the Doubly Linked List
     * @param value
     * @throws RuntimeException if the Doubly Linked List is empty
     * @throws IllegalArgumentException if the given value is not in the Doubly Linked List
     */
    public void remove(T value) {
        if (length == 0) {
            throw new RuntimeException("Empty List");
        }
        if (!contains(value)) {
            throw new IllegalArgumentException("Value Not In List");
        }
        int index = this.indexOf(value);
        this.removeAt(index);
    }

    /**
     * Clears the Doubly Linked List of All Nodes
     * @throws RuntimeException if the Doubly Linked List is empty
     */
    public void clear() {
        if (this.length == 0) 
            throw new RuntimeException("Empty List");
        else {
            Node<T> pointer = this.head.next;
            while (pointer != null) {
                pointer.prev = null;
                pointer = pointer.next;
            }    
            this.length = 0; 
            this.head = null;
            this.tail = null;
        }
    }

    /**
     * @return the length of the Doubly Linked list
     */
    public int length() {
        return this.length;
    }

    /**
     * @return true if the Doubly Linked List is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.length == 0;
    }

    /**
     * @param value
     * @return the index of the given value within the Doubly Linked List. If the value is not present in the list, -1 is returned
     */
    public int indexOf(T value) {
        Node<T> pointer = this.head;
        int count = 0;
        while (pointer != null) {
            if (pointer.value == value)
                return count; 
            pointer = pointer.next;
            count++; 
        }
        return -1;
    }

    /**
     * @param value
     * @return true if the Doubly Linked List contains the value, false otherwise
     */
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    /**
     * @return the Head Node of the Doubly Linked List
     * @throws RuntimeException if the Doubly Linked List is Empty
     */
    public Node<T> getHead() {
        if (this.length == 0) 
            throw new RuntimeException("List is Empty");
        return this.head;
    }

    /**
     * @return the Tail Node of the Doubly Linked List
     * @throws RuntimeException if the Doubly Linked List is Empty
     */
    public Node<T> getTail() {
        if (this.length == 0) 
            throw new RuntimeException("List is Empty");
        return this.tail;
    }

    /**
     * @return the Head Value of the Doubly Linked List
     * @throws RuntimeException if the Doubly Linked List is Empty
     */
    public T getHeadValue() {
        if (this.length == 0) 
            throw new RuntimeException("List is Empty");
        return this.head.value;
    }

    /**
     * @return the Tail Value of the Doubly Linked List
     * @throws RuntimeException if the Doubly Linked List is Empty
     */
    public T getTailValue() {
        if (this.length == 0) 
            throw new RuntimeException("List is Empty");
        return this.tail.value;
    }

    /**
     * @param list
     * @return true if the two lists are equivalent, false otherwise
     */
    public boolean equals(Doublylinkedlist<T> list) {
        if (this.length != list.length())
            return false;  
        Node<T> pointer1 = this.head; 
        Node<T> pointer2 = list.getHead();
        while (pointer1 != null) {
            if (pointer1.value != pointer2.value)
                return false;
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        return true; 
    }

    /**
     * Creates String representation of the Doubly Linked List
     */
    public String toString() {
        Node<T> pointer = this.head;
        String result = "";
        if (pointer == null)
            return "";
        while (pointer.next != null) {
           result += pointer.value + " <--> ";
           pointer = pointer.next; 
        }
        result+=pointer.value;
        return result;
    }
}