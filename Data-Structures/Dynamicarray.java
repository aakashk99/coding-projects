/**
 * The Dynamicarray class implements a functioning Dynamic Array
 * 
 * @author Aakash Karlekar
 * @version 1.1
 * @since 1-25-23
 */

public class Dynamicarray {
    private int[] array;
    private int length;

    /**
     * Zero Argument Constructor for Dynamic Array
     * Instantiates an Integer Array with length 0
     */
    public Dynamicarray() {
        array = new int[0];
        length = 0;
    }

    /**
     * Single Argument Constructor for Dynamic Array
     * Instantiates a Dynamic array of length Length
     * @param Length
     * @throws IllegalArgumentException if Length less than 0
     */
    public Dynamicarray(int Length) {
        if (length < 0) {
            throw new IllegalArgumentException("Invalid Array Length");
        }
        array = new int[Length];
        length = Length;
    }

    /**
     * @return Length of Array
     */
    public int size() {
        return length;
    }

    /**
     * @return true if Length of Array is 0, false otherwise
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * @param index
     * @return Array Value at index
     * @throws RuntimeException if Array is Empty
     * @throws IllegalArgumentException If Index is less than 0
     */
    public int get(int index) {
        if (length == 0)
            throw new RuntimeException("Empty Array");
        if (index < 0) 
            throw new IllegalArgumentException("Invalid Index");
        return array[index];
    }

    /**
     * Clears the Entire Array
     */
    public void clear() {
        int[] temp = new int[0];
        array = temp;
        length = 0;
    }

    /**
     * Sets Array value at index to value
     * @param index
     * @param value
     * @throws RuntimeException if Array is Empty
     * @throws IllegalArgumentException if Index is less than 0
     */
    public void set(int index, int value) {
        if (length == 0)
            throw new RuntimeException("Empty Array");
        if (index < 0)
            throw new IllegalArgumentException("Invalid Index");
        array[index] = value;
    }

    /**
     * Removes Array value at index Index
     * @param Index
     * @throws RuntimeException if Array is Empty
     * @throws IllegalArgumentException if Index is less than 0
     */
    public void removeAt(int Index) {
        if (length == 0)
            throw new RuntimeException("Empty Array");
        if (Index < 0)
            throw new IllegalArgumentException("Invalid Index");
        int[] temp = new int[array.length-1];
        int skip = Index;
        for(int i = 0; i<skip; i++) {
            temp[i] = array[i];
        }
        for (int i = skip+1; i<array.length; i++) {
            temp[i-1] = array[i];
        }
        array = temp;
        length = array.length;
    }

    /**
     * Removes value from Array
     * @param value
     * @throws RuntimeException if value is not in Array
     * @throws RuntimeException if Array is Empty
     */
    public void remove(int value) {
        if (length == 0) 
            throw new RuntimeException("Empty Array");
        if (indexOf(value)==-1)
            throw new RuntimeException("Value is not in Array");
        int index = indexOf(value);
        removeAt(index);
    }

    /**
     * @param n
     * @return Index of value if it is in array, -1 otherwise
     */
    public int indexOf(int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value)
                return i;
        }
        return -1;
    }

    /**
     * Appends value to the end of the Array
     * @param value
     */
    public void append(int value) {
        if (array.length == length) {
            int[] oldArray = array;
            length++;
            array = new int[length];
            for (int i = 0; i<oldArray.length; i++) {
                array[i] = oldArray[i];
            }
            array[oldArray.length] = value;
        }
        else {
            array[length] = value;
            length++;
        }
    }

    /**
     * Creates String Representation of Dynamic Array
     */
    public String toString() {
        String output = "[";
        if (length == 0) 
            return "[]";
        for (int i = 0; i<array.length-1;i++) {
            output += array[i] + ", ";
        }
        output += array[array.length-1] + "]";
        return output;
    }
}
