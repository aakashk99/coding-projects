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

    private class Pair<K, V> {
        private K key;
        private V value;
        
        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private K getKey() {
            return this.key;
        }

        private V getValue() {
            return this.value;
        }

        public String toString() {
            return "{" + this.key + ", " + this.value + "}";
        }
    }

    public Hashmapaddressing() {
        this.array = new Pair[initCapacity];
        this.loadFactor = 0;
        this.size = 0;
        this.fullSlots = 0;
        this.keys = new ArrayList<K>();
        this.values = new ArrayList<V>();
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % this.array.length;
    }

    private int probe(int index) {
        return index+1;
    }

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

    public void put(K key, V value) {
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

    public void clear() {
        ArrayList<K> allKeys = new ArrayList<K>();
        for (K key: this.keys) {
            allKeys.add(key);
        }
        for (K key: allKeys) {
            this.remove(key);
        }
    }

    public V get(K key) {
        if (this.find(key) < 0) {
            throw new RuntimeException("Key not found in Hashmap");
        }
        return this.array[this.find(key)].getValue();
    }

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

    public ArrayList<K> getKeys() {
        return this.keys;
    }

    public ArrayList<V> getValues() {
        return this.values;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

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

    public static void main(String[] args) {
        Hashmapaddressing<Integer, Integer> h1 = new Hashmapaddressing<Integer, Integer>();
        // for (int i = 0; i < 50; i++) {
        //     Integer randKey = (int) (Math.random()*100);
        //     Integer randValue = (int) (Math.random()*100);
        //     h1.put(randKey, randValue); 
        // }
        // System.out.println(h1);
        // System.out.println(h1.size());
        // System.out.println(h1.getKeys());
        // System.out.println(h1.getValues());
        // for (Integer key : h1.getKeys()) {
        //     System.out.println(key + " - " + h1.find(key));
        // }
        for (Integer i = 0; i < 100; i++) {
            h1.put(i, i+1);
        }
        for (Integer i = 0; i < 50; i++) {
            h1.remove(i);
        }
        for (Integer i = 100; i < 256; i++) {
            h1.put(i, i+2);
        }
        h1.clear();
        System.out.println(h1);
        System.out.println(h1.size());
        System.out.println(h1.getKeys());
        System.out.println(h1.getValues());
    }
}