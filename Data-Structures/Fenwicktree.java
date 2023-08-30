/**
 * The Fenwicktree Class implements a Binary Indexed Tree
 * The Fenwick Tree can be used to calculate the running sum across two indices in an Array
 * 
 * @author Aakash Karlekar
 * @version 1.0
 * @since 2023-08-30
 */
public class Fenwicktree {
    private int[] array;

    /**
     * One-Argument constructor used to instantiate a Fenwick Tree
     * The constructor populates the Fenwick Tree in Linear time rather in Log-Linear time 
     * @param list
     */
    public Fenwicktree(int[] list) {
        this.array = new int[list.length + 1];
        for (int i = 0; i < list.length; i++) {
            this.array[i+1] = list[i];
        }
        for (int i = 1; i < this.array.length; i++) {
            int j = i + this.lsb(i);
            if (j < this.array.length) {
                this.array[j] += this.array[i];
            }
        }
    }

    /**
     * Private method used to calculate the Least Significant Bit of a given Integer
     * @param index
     * @return the Least Significant Bit of the specified index
     */
    private int lsb(int index) {
        return index & -index;
    }

    /**
     * Private method used to calculate the Prefix Sum for the given One-Based Index in the Fenwick Tree 
     * @param index
     * @return the Prefix Sum for a specified index in the Fenwick Tree
     */
    private int prefixSum(int index) {
        int sum = 0;
        while (index >= 1) {
            sum += this.array[index];
            index -= this.lsb(index);
        }
        return sum;
    }

    /**
     * Used to update the specified One-Based Index in the Fenwick Tree when there is a Point Update to the original array
     * Note: The updated index in the original corresponds to the value at index+1 in the Fenwick Tree
     * @param index
     * @param value
     * @throws IllegalArgumentException if the User tries to update index 0 of the Fenwick Tree
     */
    public void update(int index, int value) {
        if (index == 0) {
            throw new IllegalArgumentException("Cannot update Index 0 of the Fenwick Tree as the Tree is One-Based");
        }
        int difference = value - this.rangeQuery(index-1, index);
        while (index < this.array.length) {
            this.array[index] += difference;
            index += this.lsb(index);
        }
    }

    /**
     * Used to get the value stored at the specified One-Based index in the Fenwick Tree
     * @param index
     * @return the value stored at the specified index in the Fenwick Tree
     * @throws IllegalArgumentException if the User tries to access index 0 of the Fenwick Tree
     */
    public int get(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Could not access Index 0 because Fenwick Tree is One-Based");
        }
        return this.array[index];
    }

    /**
     * Returns the sum of the values on the range [start, end) in the original list
     * Note: The indicies are Zero-Based
     * @param start
     * @param end
     * @return the sum of the values on the range [start, end) in the original list 
     * @throws IllegalArgumentException if the ending index is larger than the length of the array
     * @throws IllegalArgumentException if the ending index is smaller than the starting index 
     */
    public int rangeQuery(int start, int end) {
        if (end >= this.array.length) {
            throw new IllegalArgumentException("Ending Index cannot be larger than Length of Array");
        }
        if (start > end) {
            throw new IllegalArgumentException("Ending Index cannot be smaller than Starting Index");
        }
        return this.prefixSum(end)-this.prefixSum(start);
    }

    /**
     * @return the size of the Fenwick Tree
     */
    public int size() {
        return this.array.length-1;
    }

    /**
     * Creates a String representation of the Fenwick Tree
     */
    public String toString() {
        String result = "[";
        for (int i = 0; i < this.array.length; i++) {
            if (i != 0) {
                result += this.array[i];
                if (i != this.array.length-1) {
                    result += ", ";
                }
            }
        }
        return result + "]";
    }
}