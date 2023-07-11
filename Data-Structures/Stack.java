/**
 * The Stack Class implements a functioning stack data structure
 * Note: To allow for generic typing, this stack has been implemented using a list.
 * This means that there will be no predefined Stack Capacity and no Stack Overflow Errors. 
 * 
 * @author Aakash Karlekar
 * @version 1.1
 * @since 2023-11-07
 */

import java.util.ArrayList;

public class Stack<T> {
    private ArrayList<T> list;

    /**
     * No-Argument constructor that instantiates an empty stack
     */
    public Stack() {
        this.list = new ArrayList<T>();
    } 

    /**
     * One-Argument constructor that instantiates a stack with value as its first element
     * @param value
     */
    public Stack(T value) {
        this.list = new ArrayList<T>();
        this.list.add(value);
    }

    /**
     * Pushes value onto the stack
     * @param value
     */
    public void push(T value) {
        this.list.add(value);
    }

    /**
     * Pops the most-recently inserted element onto the stack
     * @return the top-most value of the stack
     */
    public T pop() {
        if (this.isEmpty()) 
            throw new RuntimeException("Stack is empty");
        else {
            T value = this.list.get(this.list.size()-1);
            this.list.remove(this.list.size()-1);
            return value;
        }
    }

    /**
     * @return the size of the Stack
     */
    public int size() {
        return this.list.size();
    }

    /**
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.list.size() == 0;
    }

    /**
     * Helper method used to override the equals method
     * @return list representation of the Stack
     */
    private ArrayList<T> getList() {
        return this.list;
    }

    /**
     * @param other
     * @return true if this Stack is equivalent to the other Stack, false otherwise
     */
    public boolean equals(Stack<T> other) {
        if (this.list.size() != other.size())
            return false;
        for (int i = this.list.size()-1; i>=0; i--) {
            if (this.list.get(i) != other.getList().get(i))
                return false;
        }
        return true;
    }

    /**
     * Creates String representation of the Stack
     */
    public String toString() {
        if (this.isEmpty())
            return "Empty";
        String result = "";
        for (int i = this.list.size()-1; i >= 0; i--) {
            result += this.list.get(i) + "\n";
        }
        return result;
    }
}