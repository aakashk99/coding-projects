public class Dynamicarray {
    private int[] array;
    private int length;

    public Dynamicarray() {
        array = new int[0];
        length = 0;
    }

    public Dynamicarray(int n) {
        array = new int[n];
        length = n;
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int get(int n) {
        return array[n];
    }

    public void clear() {
        int[] temp = new int[0];
        array = temp;
        length = 0;
    }

    public void set(int index, int value) {
        array[index] = value;
    }

    public void removeAt(int n) {
        int[] temp = new int[array.length-1];
        int skip = n;
        for(int i = 0; i<skip; i++) {
            temp[i] = array[i];
        }
        for (int i = skip+1; i<array.length; i++) {
            temp[i-1] = array[i];
        }
        array = temp;
        length = array.length;
    }

    public void remove(int n) {
        int index = indexOf(n);
        removeAt(index);
    }

    public int indexOf(int n) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == n)
                return i;
        }
        return -1;
    }

    public void append(int n) {
        if (array.length == length) {
            int[] oldArray = array;
            length++;
            array = new int[length];
            for (int i = 0; i<oldArray.length; i++) {
                array[i] = oldArray[i];
            }
            array[oldArray.length] = n;
        }
        else {
            array[length] = n;
            length++;
        }
    }

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
