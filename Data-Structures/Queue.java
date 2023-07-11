/**
 * The Queue Class implements a functioning queue data structure
 * 
 * @author Aakash Karlekar
 * @version 1.0
 * @since 2023-11-07
 */

import java.util.ArrayList;

public class Queue<T> {
    private ArrayList<T> list;

    /**
     * No-Argument Constructor that instantiates an Empty Queue
     */
    public Queue() {
        this.list = new ArrayList<T>();
    }

    /**
     * One-Arguement Constructor that instantiates a Queue with value as its first element
     * @param value
     */
    public Queue(T value) {
        this.list = new ArrayList<T>();
        this.list.add(value);
    }

    /**
     * Adds value to the back of the queue
     * @param value
     */
    public void enqueue(T value) {
        this.list.add(value);
    }

    /**
     * Dequeues the value at the front of the Queue
     * @return the first value in the Queue
     */
    public T poll() {
        if (this.isEmpty()) {
            throw new RuntimeException("The Queue is currently Empty");
        }
        T value = this.list.get(0);
        this.list.remove(0);
        return value;
    }

    /**
     * @return the length of the Queue
     */
    public int size() {
        return this.list.size();
    }

    /**
     * @return true if the Queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Helper method used in the overrided equals method 
     * @return a list representation of the Queue
     */
    private ArrayList<T> getList() {
        return this.list;
    }

    /**
     * @param other
     * @return true if this Queue is equivalent to the other Queue, false otherwise
     */
    public boolean equals(Queue<T> other) {
        if (this.list.size() != other.size())
            return false;
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i) != other.getList().get(i)) 
                return false;
        }
        return true; 
    }

    /**
     * Creates a String representation of the Queue
     */
    public String toString() {
        if (this.isEmpty())
            return "Empty";
        String result = "";
        for (T value : this.list) {
            result += value + " ";
        }
        return result;
    }
}