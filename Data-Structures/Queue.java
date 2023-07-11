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

    public Queue() {
        this.list = new ArrayList<T>();
    }

    public Queue(T value) {
        this.list = new ArrayList<T>();
        this.list.add(value);
    }

    public void enqueue(T value) {
        this.list.add(value);
    }

    public T poll() {
        if (this.isEmpty()) {
            throw new RuntimeException("The Queue is currently Empty");
        }
        T value = this.list.get(0);
        this.list.remove(0);
        return value;
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    private ArrayList<T> getList() {
        return this.list;
    }

    public boolean equals(Queue<T> other) {
        if (this.list.size() != other.size())
            return false;
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i) != other.getList().get(i)) 
                return false;
        }
        return true; 
    }

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