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

    public Hashmapchaining() {
        this.array = new LinkedList[initCapacity];
        this.loadFactor = 0;
        this.size = 0;
        this.keys = new ArrayList<K>();
        this.values = new ArrayList<V>();
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % this.array.length;
    }

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

    public void clear() {
        for (int i = this.keys.size()-1; i>=0; i--) {
            this.remove(this.keys.get(i));
        }
    }

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

    public static void main(String[] args) {
        // Hashmapchaining<Integer, String> h1 = new Hashmapchaining<Integer, String>();
        // for (int i = 0; i < 32; i++) {
        //     h1.put(i, "Hey");
        // }
        // h1.put(16, "Bruh"); 
        // System.out.println(h1.size()); 
        // System.out.println(h1.get(2));
        // h1.put(2, "Ho");
        // System.out.println(h1.getValues());
        // h1.remove(1);
        // System.out.println(h1.get(2));
        // System.out.println(h1); 
        // System.out.println(h1.size());
        // // System.out.println(h1.getKeys());
        // // System.out.println(h1.getValues());
        // // int key = 92;
        // // System.out.println(h1.hash(key));
        // // System.out.println(h1.size());
        // // System.out.println(h1.isEmpty());
        // System.out.println(h1.getKeys());
        // System.out.println(h1.getValues());

        // Hashmapchaining<String, String> h2 = new Hashmapchaining<String, String>();
        // h2.put("Aakash", "Karlekar");
        // h2.put("Aayush", "Karlekar");
        // h2.put("Leland", "Heath");
        // h2.put("Durgesh", "Karlekar");
        // h2.put("Rashmi", "Samant"); 
        // h2.put("Rashmi", "Karlekar");
        // h2.put("Satish", "Samant");
        // System.out.println(h2);
        // System.out.println(h2.getKeys());
        // System.out.println(h2.getValues());
        // for (int i = h2.getKeys().size()-1; i >= 0; i--) {
        //     h2.remove(h2.getKeys().get(i));
        // }
        // h2.put("hey", "ho");
        // h2.remove("hey");
        // h2.clear();
        // System.out.println(h2);
        // System.out.println(h2.getKeys());
        // System.out.println(h2.getValues());
        // System.out.println(h2.size());

        // Hashmapchaining<Integer, Integer> h3 = new Hashmapchaining<Integer, Integer>();
        // for (int i = 0; i < 56; i++) {
        //     h3.put(i, i+2);
        // }
        // boolean worked = true; 
        // for (int i = 0; i < 56; i++) {
        //     if (h3.get(i) != i+2) {
        //         worked = false;
        //     }
        //     break;
        // }
        // System.out.println(worked);
        // System.out.println(h3); 
        // System.out.println(h3.getKeys());
        // System.out.println(h3.getValues());
        Hashmapchaining<Integer, Integer> h4 = new Hashmapchaining<Integer, Integer>();
        Integer num1 = 4;
        Integer num2 = 14;
        System.out.println(num1.hashCode());
        System.out.println(num2.hashCode());
        h4.put(4, 9);
        h4.put(14, 19);
        System.out.println(h4);
        System.out.println(h4.get(4));
        System.out.println(h4.get(14));
        h4.clear();
        int length = 1000;
        int size = 10;
        for (Integer i = 0; i < length; i++) {
            h4.put(i, 2*i.hashCode()%size); 
            if ((double) h4.size()/size >= 0.8) 
                size *= 2;
        }
        System.out.println(h4);
        System.out.println(h4.get(38));
        System.out.println(h4.get(198));
        System.out.println(h4.get(191));
    }
}