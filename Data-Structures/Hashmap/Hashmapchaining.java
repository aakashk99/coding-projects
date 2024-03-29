/**
 * The Hashmapchaining Class implements a generically typed Hashmap
 * The implemented Hashmap uses Separate Chaining when resolving Hash Collisions
 * 
 * @author Aakash Karlekar
 * @version 1.0
 * @since 2023-24-08
 */
import java.util.LinkedList;
import java.util.ArrayList;

public class Hashmapchaining<K extends Comparable<K>, V extends Comparable<V>> {
    private static final int initCapacity = 10;
    private static final double maxLoadFactor = 0.80;
    private double loadFactor;
    private LinkedList<Pair<K,V>>[] array;
    private int size;
    private ArrayList<K> keys;
    private ArrayList<V> values; 

    /**
     * Private class used to store Keys and Values as a Pair Object
     */
    private class Pair<K, V> {
        private K key;
        private V value;
        
        /**
         * Private constructor to instantiate a Pair Object consisting of a given Key and Value 
         * @param key
         * @param value
         */
        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Private Method used to access the key of the Pair Object
         * @return Key
         */
        private K getKey() {
            return this.key;
        }

        /**
         * Private Method used to access the value of the Pair Object
         * @return Value
         */
        private V getValue() {
            return this.value;
        }

        /**
         * Method used to create a String implementation of the Pair
         */
        public String toString() {
            return "{" + this.key + ", " + this.value + "}";
        }
    }

    /**
     * No-Arg Constructor used to instantiate an Empty Hashmap
     */
    public Hashmapchaining() {
        this.array = new LinkedList[initCapacity];
        this.loadFactor = 0;
        this.size = 0;
        this.keys = new ArrayList<K>();
        this.values = new ArrayList<V>();
    }

    /**
     * Private helper method used to Hash a given key
     * @param key
     * @return Hash of the given Key
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % this.array.length;
    }

    /**
     * Private helper method used to refactor the Hashmap's internal array once it has reached its load capacity
     * This includes doubling the size of the current internal array and re-hashing the existing Key-Value Pairs into the new Array 
     */
    private void refactor() {
        ArrayList<Pair<K,V>> pairs = new ArrayList<Pair<K,V>>(); 
        for (LinkedList<Pair<K,V>> list : this.array) {
            if (list != null) {
                for (Pair<K,V> pair : list) {
                    pairs.add(pair);
                }
            }
        }
        LinkedList<Pair<K,V>>[] newArray = new LinkedList[this.array.length*2];
        this.array = newArray;
        this.keys.clear();
        this.values.clear();
        for (Pair<K,V> pair : pairs) {
            this.size--;
            this.put(pair.getKey(), pair.getValue());
        }
    }

    /**
     * Used to insert a Key-Value Pair into the Hashmap 
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int index = this.hash(key);
        Pair<K,V> pair = new Pair<K,V>(key, value);
        if (this.array[index] == null) {
            LinkedList<Pair<K,V>> list = new LinkedList<Pair<K,V>>();
            list.add(pair);
            this.array[index] = list;
            this.size++;
            this.keys.add(key);
            this.values.add(value);
        }
        else {
            boolean found = false;
            for (int i = 0; i < this.array[index].size(); i++) {
                if (this.array[index].get(i).getKey().compareTo(pair.getKey()) == 0) {
                    found = true;
                    if (this.array[index].get(i).getValue().compareTo(pair.getValue()) != 0) {
                        V prevValue = this.array[index].get(i).getValue();
                        this.array[index].set(i, pair);
                        this.values.remove(prevValue);
                        this.values.add(pair.getValue());
                        break;
                    }
                } 
            }
            if (!found) {
                this.array[index].add(pair);
                this.keys.add(pair.getKey());
                this.values.add(pair.getValue());
                this.size++;
            }
        }
        this.loadFactor = (double) this.size()/this.array.length;
        if (this.loadFactor >= Hashmapchaining.maxLoadFactor) {
            this.refactor();
        }
    }

    /**
     * Used to remove a given Key and its associated Value from the Array
     * @param key
     * @throws RuntimeException if the Hashmap is Empty
     * @throws RuntimeException if the key is not found in the Hashmap
     */
    public void remove(K key) {
        if (this.isEmpty()) 
            throw new RuntimeException("Hashmap is Empty");
        if (!this.find(key))
            throw new RuntimeException("Key not found in Hashmap");
        int index = this.hash(key);
        V value = null;
        for (int i = 0; i < this.array[index].size(); i++) {
            if (this.array[index].get(i).getKey().compareTo(key) == 0) {
                value = this.array[index].get(i).getValue();
                this.array[index].remove(i);
                break;
            }
        }
        this.keys.remove(key);
        this.values.remove(value);
        this.size--;
    }

    /**
     * Used to clear the Hashmap of all stored Key-Value Pairs 
     * @throws RuntimeException if the Hashmap is Empty
     */
    public void clear() {
        if (this.isEmpty()) {
            throw new RuntimeException("Hashmap is Empty");
        }
        for (int i = this.keys.size()-1; i>=0; i--) {
            this.remove(this.keys.get(i));
        }
    }

    /**
     * Used to access the Value associated with a given Key in the Hashmap 
     * @param key
     * @return Value associated with Key
     * @throws RuntimeException if Key is not found in the Hashmap
     */
    public V get(K key) {
        if (!this.find(key)) {
            throw new RuntimeException("Key not found in Hashmap");
        }
        int hash = this.hash(key);
        for (Pair<K,V> pair : this.array[hash]) {
            if (pair.getKey().compareTo(key) == 0) {
                return pair.getValue();
            }
        }
        return null;
    }

    /**
     * Private Helper method used to determine if a Key is stored in the Hashmap 
     * @param key
     * @return true if the Key is found in the Hashmap, false otherwise
     */
    private boolean find(K key) {
        boolean found = false;
        int hash = this.hash(key);
        try {
            for (Pair<K,V> pair : this.array[hash]) {
                if (pair.getKey().compareTo(key) == 0) {
                    found = true;
                    break;
                }
            }
        }
        catch (Exception e) {
            found = false;
        }
        return found; 
    }

    /**
     * Used to access a List of Keys stored within the Hashmap
     * @return List of Keys stored in the Hashmap
     */
    public ArrayList<K> getKeys() {
        return this.keys;
    }

    /**
     * Used to access a List of Values stored within the Hashmap
     * @return List of Values stored in the Hashmap
     */
    public ArrayList<V> getValues() {
        return this.values;
    }

    /**
     * Used to find out the size of the Hashmap
     * @return size of the Hasmap
     */
    public int size() {
        return this.size;
    }

    /**
     * Used to check if the Hashmap is Empty
     * @return true if the Hashmap is Empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Creates a String representation of the Hashmap
     */
    public String toString() {
        String result = "{";
        for (LinkedList<Pair<K,V>> list : this.array) {
            if (list != null) {
                for (Pair<K,V> pair : list) {
                    result += pair.getKey()+": "+pair.getValue()+", ";
                }
            }
        }
        result += "}";
        if (result.equals("{}")) {
            return result;
        }
        String output = "";
        for (int i = 0; i < result.length()-2; i++) {
            if (!result.substring(i, i+3).equals(", }")) {
                output+=result.substring(i, i+1);
            }
        }
        output += "}";
        return output;
    }
}