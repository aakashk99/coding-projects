/**
 * The Suffixarray class implements a functioning Suffixarray
 * 
 * @author Aakash Karlekar
 * @version 1.0
 * @since 2023-30-8
 */
public class Suffixarray {
    private String string;
    private int[] array;

    /**
     * One-Arugment Constructor that takes in a String and outputs its corresponding Suffix Array
     * @param string
     */
    public Suffixarray(String string) {
        this.string = string;
        this.array = new int[this.string.length()];
        String[] substrings = new String[this.string.length()];
        for (int i = 0; i < string.length(); i++) {
            substrings[i] = this.string.substring(i, this.string.length());
        }
        String[] ordered = this.mergeSort(substrings);
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] = this.string.length() - ordered[i].length();
        }
    }

    /**
     * Private helper method used to Lexicographically Sort the possible suffixes of a given String
     * @param list
     * @return array of sorted suffixes
     */
    private String[] mergeSort(String[] list) {
        if (list.length == 0) {
            return list;
        }
        if (list.length == 1) {
            return list;
        }
        int half = list.length/2;
        String[] l1 = new String[half];
        String[] l2 = new String[list.length-half];
        for (int i = 0; i < half; i++) {
            l1[i] = list[i];
        }
        for (int i = half; i < list.length; i++) {
            l2[i-half] = list[i];
        }
        return this.merge(this.mergeSort(l1), this.mergeSort(l2));
    }

    /**
     * Private helper method used in the Merge Sort algorithm to Merge two lists in a sorted manner
     * @param l1
     * @param l2
     * @return sorted merge of l1 and l2
     */
    private String[] merge(String[] l1, String[] l2) {
        int l = 0;
        int r = 0;
        int index = 0;
        String[] result = new String[l1.length+l2.length];
        while (l < l1.length && r < l2.length) {
            if (l1[l].compareTo(l2[r]) <= 0) {
                result[index] = l1[l];
                l++;
            }
            else {
                result[index] = l2[r];
                r++;
            }
            index++;
        }
        if (l < r) {
            for (; l < l1.length; l++) {
                result[index] = l1[l];
                index++;
            }
        }
        else {
            for (; r < l2.length; r++) {
                result[index] = l2[r];
                index++;
            }
        }
        return result;
    }

    /**
     * Used to get a reference to the Suffix Array of the given String
     * @return Suffix Array
     */
    public int[] getArray() {
        return this.array;
    }

    /**
     * Creates a String representation of the Suffix Array as a Value - String Pair
     */
    public String toString() {
        String result = "["; 
        for (int i = 0; i < this.array.length-1; i++) {
            result += this.array[i] + " - " +this.string.substring(this.array[i], this.string.length()) + ", ";
        } 
        result += this.array[this.array.length-1] + " - " + this.string.substring(this.array[this.array.length-1], this.string.length()) + "]"; 
        return result;
    }

    public static void main(String[] args) {
        Suffixarray s = new Suffixarray("Cat");
        int[] array = s.getArray();
        for (int val : array) {
            System.out.print(val + " ");
        }
        System.out.println();
        System.out.println(s); 
    }
}