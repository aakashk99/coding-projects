/**
 * The Disjointset Class implements a generically typed Disjoint-Set, or Union-Find, Data Structure
 * The Implemented Disjoint-Set Data Structure uses Path Compression
 * This Path Compression allows for amortized linear time-complexities during union and find operations
 * 
 * @author Aakash Karlekar
 * @version 1.0
 * @since 2023-31-07
 */
import java.util.HashMap;
import java.util.ArrayList;

public class Disjointset<T> {
    private HashMap<Integer, ArrayList<Node<T>>> groups;
    private HashMap<Node<T>, Integer> nodes;
    private HashMap<T, Node<T>> values;

    /**
     * Private Class used to Instantiates the Nodes used in the Disjoint-Set
     */
    private class Node<T> { 
        private T value;
        private Node<T> parent;

        /**
         * Private constructor used to instantiate a Node with a given value and parent Node
         * @param value
         * @param parent
         */
        private Node(T value, Node<T> parent) {
            this.value = value;
            this.parent = parent;
        }

        /**
         * @return String representation of the Node
         */
        public String toString() {
            return ""+this.value;
        }
    } 

    /**
     * No-Argument Constructor that instantiates an empty Disjoint-Set
     */
    public Disjointset() {
        this.nodes = new HashMap<Node<T>, Integer>();
        this.groups = new HashMap<Integer, ArrayList<Node<T>>>();
        this.values = new HashMap<T, Node<T>>();
    }

    /**
     * Adds a Node with a given value to the Disjoint-Set
     * The Node by default is a Root Node and will point to Null until it is unified with a larger set.
     * @param value
     */
    public void addNode(T value) {
        for (T existingValue : this.values.keySet()) {
            if (value == existingValue)
                throw new IllegalArgumentException("Value already in Disjoint Set");
        }
        Node<T> node = new Node<T>(value, null);
        this.nodes.put(node, this.groups.size()); 
        ArrayList<Node<T>> group = new ArrayList<Node<T>>();
        group.add(node);
        this.groups.put(this.groups.size(), group);
        this.values.put(value, node);
    }

    /**
     * Unifies the groups associated with Node A and Node B
     * The smaller group will be merged into the larger group
     * If both groups are the same size, the group associated with Node B will be merged into the group associated with Node A
     * Path Compression is a part of the unification process: All Nodes involved in the unification process will point to the Root Node of the Resulting Group
     * @param A
     * @param B
     */
    public void unify(Node<T> A, Node<T> B) {
        if (this.nodes.get(A) == this.nodes.get(B))
            throw new RuntimeException("Nodes are already in same group");
        if (this.groups.get(this.nodes.get(A)).size() >= this.groups.get(this.nodes.get(B)).size()) {
            int bGroup = this.nodes.get(B); 
            int aGroup = this.nodes.get(A);
            Node<T> aRoot = null;
            if (A.parent == null) 
                aRoot = A;
            else 
                aRoot = A.parent;
            Node<T> bRoot = null;
            for (int i = this.groups.get(bGroup).size()-1; i>=0; i--) {
                if (this.groups.get(bGroup).get(i).parent != null) {
                    this.groups.get(aGroup).add(this.groups.get(bGroup).get(i));
                    this.nodes.put(this.groups.get(bGroup).get(i), aGroup);
                    this.groups.get(bGroup).get(i).parent = aRoot;
                    this.groups.get(bGroup).remove(this.groups.get(bGroup).get(i));
                }
                else {
                    bRoot = this.groups.get(bGroup).get(i);
                }
            }
            this.groups.remove(bGroup); 
            this.groups.get(aGroup).add(bRoot);
            this.nodes.put(bRoot, aGroup);
            bRoot.parent = aRoot; 
        }
        else {
            int bGroup = this.nodes.get(B); 
            int aGroup = this.nodes.get(A);
            Node<T> bRoot = null;
            if (B.parent == null) 
                bRoot = B;
            else 
                bRoot = B.parent;
            Node<T> aRoot = null;
            for (int i = this.groups.get(aGroup).size()-1; i>=0; i--) {
                if (this.groups.get(aGroup).get(i).parent != null) {
                    this.groups.get(bGroup).add(this.groups.get(aGroup).get(i));
                    this.nodes.put(this.groups.get(aGroup).get(i), bGroup);
                    this.groups.get(aGroup).get(i).parent = bRoot;
                    this.groups.get(aGroup).remove(this.groups.get(aGroup).get(i));
                }
                else {
                    aRoot = this.groups.get(aGroup).get(i);
                }
            }
            this.groups.remove(aGroup); 
            this.groups.get(bGroup).add(aRoot);
            this.nodes.put(aRoot, bGroup);
            aRoot.parent = bRoot; 
        }
    }

    /**
     * @param node
     * @return the group that node currently belongs to. 
     * @return -1, if the node is not found in the Disjoint-Set
     */
    public int find(Node<T> node) {
        if (this.nodes.get(node) != null) 
            return this.nodes.get(node);
        return -1;
    }

    /**
     * @param node1
     * @param node2
     * @return true if node1 is in the same group as node2, false otherwise
     */
    public boolean connected(Node<T> node1, Node<T> node2) {
        if (this.find(this.getNode(node1.value)) == this.find(this.getNode(node2.value)))  
            return true; 
        return false;
    }

    /**
     * @param group
     * @return the number of nodes that are a part of the specified group
     */
    public int groupSize(int group) {
        if (this.groups.get(group) == null)
            throw new RuntimeException("Group not found");
        return this.groups.get(group).size();
    }

    /**
     * Can be used to acquire a List of Nodes that are associated with a certain Group
     * @return Hashmap with Key = Group and Value = List of Nodes associated with that Group
     */
    public HashMap<Integer, ArrayList<Node<T>>> getGroups() {
        return this.groups; 
    }

    /**
     * Can be used to determine the group a particular node belongs to within the Disjoint-Set
     * @return Hashmap with Key = Node and Value = Group that the Node belongs to
     */
    public HashMap<Node<T>, Integer> getNodes() {
        return this.nodes; 
    }

    /**
     * Can be used to get a reference to the Node that corresponds to a particular value 
     * Especially useful for the unify and find methods where the input(s) must be a Node(s)
     * @return Hashmap with Key = Specified Value and Value = Node that corresponds with the particular value
     */
    public HashMap<T, Node<T>> getValues() {
        return this.values;
    }

    /**
     * Especially useful for the unify and find methods where the input(s) must be a Node(s) 
     * @param value
     * @return the Node associated with a particular value
     */
    public Node<T> getNode(T value) {
        return this.values.get(value);
    }

    /**
     * @return the total number of Nodes present in the Disjoint-Set
     */
    public int size() {
        return this.values.size();
    }

    /**
     * @return the total number of groups found within the Disjoint-Set
     */
    public int numGroups() {
        int count = 0;
        for (Integer group : this.groups.keySet())
            count++;
        return count;
    }

    /**
     * Note: I have defined equivalent to mean that the corresponding nodes between the two Disjoint-Sets all belong to the same group
     * @param other
     * @return true if this Disjoint-Set is equivalent to the other Disjoint-Set, false otherwise
     */
    public boolean equals(Disjointset<T> other) {
        if (this.size() != other.size() || this.groups.size() != other.getGroups().size())
            return false;
        for (Node<T> node : this.nodes.keySet()) {
            if (this.nodes.get(node) != other.getNodes().get(other.getNode(node.value))) 
                return false;
        }
        return true;
    }

    /**
     * Creates a String Representation of the Disjoint-Set
     * Each Group present in the Disjoint-Set is listed alongside with the Nodes that are a part of that Group
     */
    public String toString() {
        String result = "";
        for (Integer group : this.getGroups().keySet()) {
            result += "Group " + group + ": ";
            for (Node<T> node: this.getGroups().get(group)) {
                if (node.parent == null) 
                    result += node.value + " ";
            }
            for (Node<T> node : this.getGroups().get(group)) {
                if (node.parent != null)
                    result += node.value + " ";
            }
            result += "\n"; 
        }
        return result;
    }
}