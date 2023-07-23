/**
 * The Priorityqueue Class implements a functioning Priority Queue data structure
 * The Priority Queue has been implemented the heap data structure to allow for logarithmic sorting
 * 
 * @author Aakash Karlekar
 * @version 1.0
 * @since 2023-23-07
 */

import java.util.ArrayList;

public class Priorityqueue {
    private Node root;
    private ArrayList<Integer> list;

    /**
     * Private Class used to Instantiate the Nodes of the Priority Queue
     */
    private class Node {
        private Node parent;
        private int value;
        private Node left;
        private Node right;

        /**
         * Private constructor that constructs a Node with a value and a reference to its parent and child nodes
         * @param parent
         * @param value
         * @param left
         * @param right
         */
        private Node(Node parent, int value, Node left, Node right) {
            this.parent = parent;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * String representation of a Node
         */
        public String toString() {
            return "" + this.value;
        }
    }

    /**
     * No-Argument Constructor that instantiates an empty Priority Queue
     */
    public Priorityqueue() {
        this.root = null;
        this.list = new ArrayList<Integer>();
    }

    /**
     * One-Argument Constructor that instantiates a Priority Queue with value as its first Node
     * @param value
     */
    public Priorityqueue(int value) {
        this.root = new Node(null, value, null, null);
        this.list = new ArrayList<Integer>();
        this.list.add(value);
    }

    /**
     * Private method that returns a pointer that references a certain index within the Priority Queue
     * @param index
     * @return pointer with reference to the Node at the given index
     */
    private Node traverse(int index) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        while (index != 0) {
            if (index % 2 == 0) {
                path.add(1);
                index = (index-2)/2;
            }
            if (index % 2 == 1) {
                path.add(0);
                index = (index-1)/2;
            }
        }
        Node pointer = this.root;
        for (int i = path.size()-1; i >= 0; i--) {
            if (path.get(i) == 0 && pointer.left != null)
                pointer = pointer.left;
            if (path.get(i) == 1 && pointer.right != null)
                pointer = pointer.right;
        }
        return pointer;
    }

    /**
     * Private method used to bubble-up a Node in the Priority Queue if its value is less than its parent node's value
     * @param pointer
     * @param index
     */
    private void bubbleUp(Node pointer, int index) {
        while (index < this.list.size() && pointer.parent != null && pointer.value < pointer.parent.value) {
            int parentIndex;
            if (index % 2 == 0) 
                parentIndex = (index-2)/2;
            else
                parentIndex = (index-1)/2;
            int tempVal = this.list.get(index);
            this.list.set(index, this.list.get(parentIndex));
            this.list.set(parentIndex, tempVal);
            int parentVal = pointer.parent.value;
            pointer.parent.value = pointer.value;
            pointer.value = parentVal;
            if (parentIndex == 0) {
                pointer = pointer.parent; 
                int rootVal = this.root.value; 
                this.root.value = pointer.value; 
                pointer.value = rootVal;
                pointer = pointer.parent;
                break;
            }
            else {
                index = parentIndex;
                pointer = pointer.parent;
            }
        }
    }

    /**
     * Private method used to bubble-down a Node if its value is greater than its left child or right child's Value
     * If the right child is less than the left child, then the Node will be bubble-downed to the right, otherwise it will be bubble-downed to the left.
     * @param pointer
     * @param index
     */
    private void bubbleDown(Node pointer, int index) {
        while ((pointer.left != null && pointer.right != null) && (pointer.value > pointer.left.value || pointer.value > pointer.right.value)) {
            if (pointer.right.value < pointer.left.value) {
                int rightIndex = index*2+2;
                int temp = pointer.value;
                pointer.value = pointer.right.value;
                pointer.right.value = temp;
                pointer = pointer.right;
                this.list.set(index, pointer.parent.value);
                this.list.set(rightIndex, pointer.value);
                index = rightIndex;
                if (index >= this.list.size()) {
                    pointer = pointer.right; 
                    int rootVal = this.root.value; 
                    this.root.value = pointer.value; 
                    pointer.value = rootVal;
                    pointer = pointer.parent;
                    break;
                }
            }
            else {
                int leftIndex = index*2+1;
                int temp = pointer.value;
                pointer.value = pointer.left.value;
                pointer.left.value = temp;
                pointer = pointer.left;
                this.list.set(index, pointer.parent.value);
                this.list.set(leftIndex, pointer.value);
                index = leftIndex;
                if (index >= this.list.size()) {
                    pointer = pointer.left; 
                    int rootVal = this.root.value; 
                    this.root.value = pointer.value; 
                    pointer.value = rootVal;
                    pointer = pointer.parent;
                    break;
                }
            }
        }
    }

    /**
     * Enqueues a value within the Priority Queue, inserting it as a Node in the sorted Heap 
     * @param value
     */
    public void enqueue(int value) {
        if (this.length() == 0) {
            this.root = new Node(null, value, null, null);
            this.list.add(value);
        }
        else {
            Node pointer = this.traverse(this.list.size()); 
            if (pointer.left == null) {
                pointer.left = new Node(pointer, value, null, null);
                pointer = pointer.left;
            }
            else {
                pointer.right = new Node(pointer, value, null, null);
                pointer = pointer.right;
            }
            this.list.add(value);
            int currentIndex = this.list.size()-1;
            this.bubbleUp(pointer, currentIndex);
        }
    }

    /**
     * Removes a given value from the Priority Queue
     * @param value
     * @throws RuntimeException if the Priority Queue is empty or the value is not found in the Priority Queue
     */
    public void remove(int value) {
        if (this.isEmpty())
            throw new RuntimeException("Queue is Empty");
        int index = -1;
        for (int i = this.list.size()-1; i >= 0; i--) {
            if (this.list.get(i) == value) {
                index = i;
                break;
            }
        }
        if (index == -1) 
            throw new RuntimeException("Value not in list");
        Node pointer = this.traverse(index);
        Node temp = this.traverse(this.list.size()-1);
        pointer.value = temp.value;
        this.list.set(index, temp.value);
        if (this.length() != 1) {
            if ((this.list.size()-1) % 2 == 0) {
                Node parent = traverse((this.list.size()-3)/2);
                parent.right = null;
            }
            else {
                Node parent = traverse((this.list.size()-2)/2);
                parent.left = null;
            }
        }
        temp = null;
        this.list.remove(this.list.size()-1);
        this.bubbleUp(pointer, index);
        this.bubbleDown(pointer, index);
        if (this.length() == 2) {
            if (this.root.value > this.root.left.value) {
                int tempVal = this.root.value;
                this.root.value = this.root.left.value;
                this.root.left.value = tempVal;
                this.list.set(0, this.root.value);
                this.list.set(1, this.root.left.value); 
            }
            else if (this.root.right != null && this.root.value > this.root.right.value) {
                int tempVal = this.root.value;
                this.root.value = this.root.right.value;
                this.root.right.value = tempVal;
                this.list.set(0, this.root.value);
                this.list.set(1, this.root.right.value); 
            }
        }
        if (this.length() == 0) {
            this.root = null; 
        }
    }

    /**
     * Removes the value located at the front of the Priority Queue
     * @return the Root of the Priority Queue
     * @throws RuntimeException if the Priority Queue is empty 
     */
    public Node poll() {
        if (this.isEmpty())
            throw new RuntimeException("Queue is Empty");
        int value  = this.root.value;
        Node temp = new Node(null, value, null, null); 
        this.remove(value);
        return temp;
    }

    /**
     * @return the length of the Priority Queue
     */
    public int length() {
        return this.list.size(); 
    }

    /**
     * Private method used to obtain a list representation of the Priority Queue. 
     * Used in the overriden equals method
     * @return
     */
    private ArrayList getList() {
        return this.list; 
    }

    /**
     * @return true if the Priority Queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.length() == 0; 
    }

    /**
     * Checks whether this Priority Queue is strictly equivalent to other Priority Queue
     * @param other
     * @return true if the Priority Queues are strictly equivalent, false otherwise
     */
    public boolean equals(Priorityqueue other) {
        if (this.length() != other.length())
            return false;
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i) != other.getList().get(i))
                return false;
        }
        return true;
    }

    /**
     * Creates a String Representation of the Priority Queue
     */
    public String toString() {
        String result = "";
        if (this.isEmpty())
            return "Empty"; 
        int levels = 0;
        int size = this.list.size();
        while (size > 0) {
            levels++;
            size /= 2;
        }
        int scale = 2;
        int level = 1;
        result += "Level "+ level+": ";
        level++;
        for (int i = 0; i < this.list.size(); i++) {
            result += this.list.get(i) + " ";
            if (i == scale-2 && level <= levels) {
                result += "\n";
                result += "Level " + level + ": ";
                scale*=2;
                level++;
            }
        }
        return result;
    }
}