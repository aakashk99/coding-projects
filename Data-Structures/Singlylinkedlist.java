/**
 * The Singlylinkedlist Class implements a functioning Singly Linked List
 * 
 * @author Aakash Karlekar
 * @version 1.0
 * @since 2023-01-24
 */

public class Singlylinkedlist <T> {
    private Node <T> head;
    private int length;
    private Node <T> pointer;

    /**
     * Private Class used to Create the Nodes of a Singly Linked List
     */
    private class Node <T> {
        public T value;
        public Node<T> next;
        
        private Node (T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * Single Argument Contructor that Instantiates a Singly Linked List with a Head Value of Value 
     * @param value
     */
    public Singlylinkedlist(T value) {
        head = new Node(value, null);
        length = 1;
    }

    /**
     * Adds value to the end of the Singly Linked List
     * @param value
     */
    public void append(T value) {
        if (head == null) {
            head = new Node(value, null);
            length++;
        } 
        else {
            pointer = head;
            while (pointer.next != null) {
                pointer = pointer.next; 
            }
            pointer.next = new Node(value, null);
            length++;
        }
    }

    /**
     * Adds value at the Head of the Singly Linked List
     * @param value
     */
    public void insert(T value) {
        Node<T> temp = new Node(value, head);
        head = temp; 
        length++;
    }

    /**
     * Inserts value at index in the Singly Linked List
     * @param index
     * @param value
     * @throws RuntimeException if Singly Linked List is Empty
     * @throws IllegalArgumentException if index is invalid
     */
    public void insertAt(int index, T value) {
        if (length == 0) {
            throw new RuntimeException("Empty List");
        }
        else if (index < 0) {
            throw new IllegalArgumentException("Index Must Be Greater Than Or Equal To 0");
        }
        else if (index == 0) 
            insert(value);
        else if (index == length-1) {
            append(value);
        }
        else {
            int count = 0;
            pointer = head;
            Node<T> pointer2 = head.next;
            while (count != index-1) {
                pointer = pointer.next;
                pointer2 = pointer2.next;
                count++;    
            }
            Node<T> temp = new Node(value, pointer2);
            pointer.next = temp;
            length++;
        }
    }

    /**
     * Replaces item at index with value
     * @param index
     * @param value
     * @throws IllegalArgumentException if index is invalid
     */
    public void replace(int index, T value) {
        if (index < 0) {
            throw new IllegalArgumentException("Index Must Be Greater Than Or Equal To 0");
        }
        int count = 0; 
        pointer = head;
        while (count != index) {
            pointer = pointer.next;
        }
        pointer.value = value;
    }

    /**
     * Clears the Singly Linked List of all Elements
     */
    public void clear() {
        while (head.next != null) {
            Node<T> temp = head.next;
            head = null;
            head = temp;
        }
        head = null;
        length = 0;
    }

    /**
     * Removes item at index
     * @param index
     * @throws RuntimeException if Singly Linked List is empty
     * @throws IllegalArgumentException if index is invalid
     */
    public void removeAt(int index) {
        if (length == 0) {
            throw new RuntimeException("Empty List");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Index Must Be Greater Than Or Equal To 0");
        }
        else if (index == 0) {
            pointer = head.next;
            head = null;
            head = pointer;
        }
        else if (index == length-1) {
            pointer = head.next;
            while (pointer.next.next != null) {
                pointer = pointer.next;
            }
            pointer.next = null;
        }
        else {
            int count = 0;
            pointer = head;
            Node<T> pointer2 = head.next;
            while (count!=index-1) {
                pointer = pointer.next;
                pointer2 = pointer2.next;
                count++;
            }
            pointer2 = pointer2.next;
            Node<T> temp = pointer.next;
            pointer.next = pointer2;
            temp = null;
        }
        length--;
    }
   
    /**
     * Removes the last item of the Singly Linked List
     * @throws RuntimeException if Singly Linked List is empty
     */
    public void pop() {
        if (length == 0) {
            throw new RuntimeException("Empty List");
        }
        removeAt(length-1);
    }

    /**
     * Removes the Head of the Singly Linked List
     * @throws RuntimeException if Singly Linked List is empty
     */
    public void removeHead() {
        if (length == 0) {
            throw new RuntimeException("Empty List");
        }
        removeAt(0);
    }

    /**
     * Removes the given value from the Singly Linked List
     * @param value
     * @throws RuntimeException if Singly Linked List is Empty
     * @throws IllegalArgumentException if value is not in the list
     */
    public void remove(T value) {
        if (length == 0) {
            throw new RuntimeException("Empty List");
        }
        if (!contains(value)) {
            throw new IllegalArgumentException("Value Not In List");
        }
        int index = 0;
        pointer = head;
        while (pointer.value != value) {
            pointer = pointer.next;
            index++;
        }
        removeAt(index);
    }

    /**
     * @return Length of the Singly Linked List
     */
    public int length() {
        return length;
    }

    /**
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * @return value of the head of the Singly Linked List
     */
    public T peekFirst() {
        return head.value;
    }

    /**
     * @return value of the tail of the Singly Linked List
     */
    public T peekLast() {
        pointer = head.next;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        return pointer.value;
    }

    /**
     * @param value
     * @return index of value in the Singly Linked List
     */
    public int indexOf(T value) {
        int index = 0;
        pointer = head;
        while (pointer.value != value) {
            pointer = pointer.next;
            index++;
            if (index == length) {
                return -1;
            }
        }
        return index;
    }

    /**
     * @param value
     * @return true if the Singly Linked List contains value, false otherwise
     */
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    /**
     * Creates String Representation of Singly Linked List
     */
    public String toString() {
        String result = ""; 
        pointer = head;
        if (pointer == null) {
            return "null";
        }
        while (pointer.next != null) {
            result += pointer.value + " --> ";
            pointer = pointer.next; 
        }
        result += pointer.value + " --> null";
        return result;
    }
}