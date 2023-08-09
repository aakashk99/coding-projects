/**
 * The Binarysearchtree Class implements a Generically Typed Binary Search Tree Data Structure
 * The objects used in the Binary Search Tree must be comparable to each other
 * The Binarysearchtree class does not support duplicate values
 * 
 * @author Aakash Karlekar
 * @version 1.0
 * @since 2023-09-08
 */
import java.util.ArrayList;

public class Binarysearchtree<T extends Comparable<T>> {
    private Node<T> root;
    private int size; 

    /**
     * Private Class used to Instantiate the Nodes of the Binary Search Tree
     * The type of value the Node stores must be comparable to other values of that type
     */
    private class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> parent;
        private Node<T> left;
        private Node<T> right;
        
        /**
         * Private Constructor used to instantiate a Node of a given value and parent, left, and right node
         * @param value
         * @param parent
         * @param left
         * @param right
         */
        private Node(T value, Node<T> parent, Node<T> left, Node<T> right) {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        /**
         * Private helper method that is used to compare the values of one Node to another node
         * @param other
         * @return 1 if the value of this node is greater than the value of the other node, 0 if the value of this node and the other node are equal, -1 if the value of this node is less than the value of the other node
         */
        private int compare(Node<T> other) {
            return this.value.compareTo(other.getValue());
        }

        /**
         * Private helper method that is used to acquire the value of a Node
         * @return the value of this node
         */
        private T getValue() {
            return this.value;
        }

        /**
         * Creates a String Representation of a Node
         */
        public String toString() {
            return "" + this.value;
        }
    }

    /**
     * No-Argument Constructor used to Instantiate a Binary Search Tree
     */
    public Binarysearchtree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * One-Argument Constructor used to Intantiate a Binary Search Tree with a root node of value
     * @param value
     */
    public Binarysearchtree(T value) {
        Node<T> root = new Node<T>(value, null, null, null);
        this.root = root;
        this.size = 1;
    }

    /**
     * Creates a Node with the specified value and inserts it into the Binary Search Tree while maintaining the Tree Invariant
     * @param value
     * @throws RuntimeException if the specified value is already found in the Binary Search Tree
     */
    public void addNode(T value) {
        if (this.findNode(value) != null)
            throw new RuntimeException("Value already found in Binary Search Tree");
        Node<T> node = new Node<T>(value, null, null, null);
        if (this.root == null) {
            this.root = node;
        }
        else {
            Node<T> pointer = this.root;
            while (pointer != null) {
                if (node.compare(pointer) > 0) {
                    if (pointer.right != null) 
                        pointer = pointer.right;
                    else {
                        pointer.right = node;
                        node.parent = pointer;
                        break;
                    }
                }
                else {
                    if (pointer.left != null) 
                        pointer = pointer.left;
                    else {
                        pointer.left = node;
                        node.parent = pointer;
                        break;
                    }
                }
            }
        }
        this.size++;
    }

    /**
     * Removes the Node with the specfied value from the Binary Search Tree while maintaining the Tree Invariant
     * @param value
     */
    public void removeNode(T value) {
        if (this.getSize() == 0)
            throw new RuntimeException("Binary Search Tree is Empty");
        Node<T> pointer = this.findNode(value);
        if (pointer == null) 
            throw new RuntimeException("Value not in Binary Search Tree");
        else {
            Node<T> pointer2;
            if (pointer.left != null && pointer.right != null) {
                pointer2 = pointer.left;
                while (pointer2.right != null) 
                    pointer2 = pointer2.right;
                pointer.value = pointer2.value;  
                if (pointer2.parent == pointer) {
                    pointer.left = null;
                    pointer2 = null; 
                }
                else {
                    if (pointer2.left != null) {
                        pointer2.parent.right = pointer2.left;
                        pointer2.left.parent = pointer2.parent;
                    }
                    else {
                        pointer2.parent.right = null;
                    }
                    pointer2 = null;
                }
            }
            else if (pointer.left != null) {
                pointer2 = pointer.left;
                if (pointer.parent != null) {
                    if (pointer.value.compareTo(pointer.parent.value) < 0) {
                        pointer.parent.left = pointer2;
                        pointer2.parent = pointer.parent; 
                    }
                    else {
                        pointer.parent.right = pointer2;
                        pointer2.parent = pointer.parent;
                    }
                }
                else {
                    this.root = pointer2;
                    this.root.parent = null;
                }
                pointer2 = null;
                pointer = null; 
            }
            else if (pointer.right != null) {
                pointer2 = pointer.right; 
                if (pointer.parent != null) {
                    if (pointer.value.compareTo(pointer.parent.value) < 0) {
                        pointer.parent.left = pointer2;
                        pointer2.parent = pointer.parent;
                    }
                    else {
                        pointer.parent.right = pointer2;
                        pointer2.parent = pointer.parent;
                    }
                }
                else {
                    this.root = pointer2;
                    this.root.parent = null;
                }
                pointer2 = null;
                pointer = null; 
            }
            else {
                if (pointer.parent != null) {
                    pointer2 = pointer.parent;
                    if (pointer2.value.compareTo(pointer.value) > 0) 
                        pointer2.left = null;
                    else 
                        pointer2.right = null;
                    pointer = null;
                }
                else {
                    this.root = null; 
                }
            }
            this.size--;
        }
    }

    /**
     * Locates the Node with the specified value in the Binary Search Tree
     * @param value
     * @return the Node with specified value if the specified value is found in the Binary Search Tree, null otherwise
     */
    public Node<T> findNode(T value) {
        Node<T> pointer = this.root;
        while (pointer != null) {
            if (pointer.value == value) 
                return pointer;
            else if (value.compareTo(pointer.value) > 0) 
                pointer = pointer.right;
            else 
                pointer = pointer.left;
        }
        return null; 
    }

    /**
     * Checks whether the Binary Search Tree contains a specified value 
     * @param value
     * @return true if the Binary Search Tree contains the given value, false otherwise
     */
    public boolean contains(T value) {
        return this.findNode(value) != null;
    }

    /**
     * Used to acquire a reference to the root of the Binary Search Tree
     * @return a reference to the root of the Binary Search Tree
     */
    public Node<T> getRoot() {
        return this.root;
    }

    /**
     * Used to gauge the size of the Binary Search Tree
     * @return the size of the Binary Search Tree
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Used to check whether the Binary Search Tree is Empty
     * @return true if the Binary Search Tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.getSize() == 0;
    }
    
    /**
     * Private recursive method used to calculate the height of the Binary Search Tree
     * @param node
     * @return the height of the Binary Search Tree
     */
    private int height(Node<T> node) {
        if (this.isEmpty())
            return 0;
        if (node == null)
            return 1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * Public method used to acquire the height of the Binary Search Tree
     * @return the height of the Binary Search Tree
     */
    public int height() {
        return this.height(this.root);
    }
    
    /**
     * Private recursive method used to traverse the Binary Search Tree and print its values in ascending order 
     * @param node
     */
    private void inOrder(Node<T> node) {
        if (node == null) 
            return;
        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
    }

    /**
     * Public method used to Print the values of the Binary Search Tree in ascending order
     */
    public void inOrder() {
        this.inOrder(this.root);
        System.out.println();
    }

    /**
     * Used to Create a String Representation of the Binary Search Tree 
     * Prints each level of the Tree along with the Nodes that are present on that level
     */
    public String toString() {
        String result = "";
        if (this.getSize() == 0) 
            return result+"Empty";
        ArrayList<Node<T>> currentLevel = new ArrayList<Node<T>>(); 
        ArrayList<Node<T>> nextLevel = new ArrayList<Node<T>>(); 
        currentLevel.add(this.root);
        result += "Level 1: ";
        int level = 2;
        while (currentLevel.size() != 0) {
            if (currentLevel.get(0) != null) {
                nextLevel.add(currentLevel.get(0).left);
                nextLevel.add(currentLevel.get(0).right);
            }
            if (currentLevel.get(0) == null) 
                result += "null "; 
            else 
                result += currentLevel.get(0).value + " ";
            currentLevel.remove(0); 
            if (currentLevel.size() == 0 && nextLevel.size() != 0) {
                for (int i = 0; i < nextLevel.size(); i++)
                    currentLevel.add(nextLevel.get(i));
                for (int i = nextLevel.size()-1; i>=0; i--) 
                    nextLevel.remove(i);
                result += "\n";
                result += "Level " + level + ": ";
                level++;
            }
        }
        return result; 
    }
}