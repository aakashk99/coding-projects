/**
 * The Hashmapchaining Class implements a generically typed Hashmap
 * The implemented Hashmap uses Open Addressing to resolve Hash Collisions
 * 
 * @author Aakash Karlekar
 * @version 1.0
 * @since 2023-24-08
 */
import java.util.ArrayList;

public class Hashmapaddressing<K extends Comparable<K>, V extends Comparable<V>> {
    private static final int initCapacity = 10;
    private static final double maxLoadFactor = 0.80;
    private double loadFactor;
    private Pair<K,V>[]array;
    private int size;
    private int fullSlots;
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
    public Hashmapaddressing() {
        this.array = new Pair[initCapacity];
        this.loadFactor = 0;
        this.size = 0;
        this.fullSlots = 0;
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
     * Private method used to probe through the internal array once a Hash Collision is Detected
     * Uses a Linear Probing Method to Scan through the internal array for open slots
     * @param index
     * @return
     */
    private int probe(int index) {
        return index+1;
    }

    /**
     * Private helper method used to refactor the Hashmap's internal array once it has reached its load capacity
     * This includes doubling the size of the current internal array and re-hashing the existing Key-Value Pairs into the new Array 
     */
    private void refactor() {
        ArrayList<Pair<K,V>> pairs = new ArrayList<Pair<K,V>>();
        for (Pair<K,V> pair : this.array) {
            if (pair != null && pair.getKey() != null && pair.getValue() != null) {
                pairs.add(pair);
            }
        }
        Pair<K,V>[] newArray = new Pair[this.array.length*2];
        this.array = newArray;
        this.keys.clear();
        this.values.clear();
        for (Pair<K,V> pair : pairs) {
            this.put(pair.getKey(), pair.getValue());
            this.size--;
            this.fullSlots--;
        }
    }

    /**
     * Used to insert a Key-Value Pair into the Hashmap 
     * @param key
     * @param value
     * @throws IllegalArgumentException if the given Key or the given Value is null
     */
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or Value cannot be null");
        }
        Pair<K,V> newPair = new Pair<K,V>(key, value);
        if (this.find(key) >= 0) {
            this.values.remove(this.array[this.find(key)].getValue());
            this.array[this.find(key)] = newPair;
            this.values.add(value);
        }
        else {
            int index = this.hash(key);
            if (this.array[index] == null) {
                this.array[index] = newPair;
            }
            else if (this.array[index].getKey() == null && this.array[index].getValue() == null) {
                this.array[index] = newPair; 
            }
            else {
                int x = 1;
                while (this.array[(index+x)%this.array.length] != null) {
                    x = this.probe(x);
                }
                this.array[(index+x)%this.array.length] = newPair;
            }
            this.keys.add(key);
            this.values.add(value);
            this.size++;
            this.fullSlots++;
            this.loadFactor = (double) this.fullSlots/this.array.length;
            if (this.loadFactor >= Hashmapaddressing.maxLoadFactor) {
                this.refactor();
            }
        }
    }

    /**
     * Used to remove a given Key and its associated Value from the Array
     * Replaces the given key with a Tombstone which is not considered a Key-Value Pair in the Hashmap
     * @param key
     * @throws RuntimeException if the Hashmap is Empty
     * @throws RuntimeException if the key is not found in the Hashmap
     */
    public void remove(K key) {
        if (this.isEmpty()) {
            throw new RuntimeException("Hashmap is Empty");
        }
        if (this.find(key) < 0) {
            throw new RuntimeException("Key not found in Hashmap");
        }
        int index = this.find(key);
        Pair<K,V> tombstone = new Pair<K,V>(null, null);
        this.keys.remove(this.array[index].getKey());
        this.values.remove(this.array[index].getValue());
        this.array[index] = tombstone;
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
        ArrayList<K> allKeys = new ArrayList<K>();
        for (K key: this.keys) {
            allKeys.add(key);
        }
        for (K key: allKeys) {
            this.remove(key);
        }
    }

    /**
     * Used to access the Value associated with a given Key in the Hashmap 
     * @param key
     * @return Value associated with Key
     * @throws RuntimeException if Key is not found in the Hashmap
     */
    public V get(K key) {
        if (this.find(key) < 0) {
            throw new RuntimeException("Key not found in Hashmap");
        }
        return this.array[this.find(key)].getValue();
    }

    /**
     * Private Helper method used to determine where a Key is stored in the Hashmap 
     * @param key
     * @return the index of the given key in the internal array, -1 if the key is not found
     */
    private int find(K key) {
        int index = this.hash(key);
        while (this.array[index] != null) {
            if (this.array[index].getKey() != null && this.array[index].getValue() != null && this.array[index].getKey().compareTo(key) == 0) {
                return index;
            }
            index = probe(index) % this.array.length;
        }
        return -1;
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
        for (Pair<K,V> pair : this.array) {
            if (pair != null && pair.getKey() != null && pair.getValue() != null) {
                result += pair.getKey() + ": " + pair.getValue() + ", ";
            }
        }
        String output = ""; 
        for (int i = 0; i < result.length()-2; i++) {
            if (result.substring(i, i+3) != ", }") {
                output += result.substring(i, i+1); 
            }
        }
        output += "}";
        if (output.equals("}")) {
            return "{}";
        }
        return output; 
    }
}