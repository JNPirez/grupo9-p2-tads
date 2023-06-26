package uy.edu.um.prog2.adt.heap;

public class HeapNode <K, V extends Comparable<V>> implements Comparable<HeapNode<K,V>> {

    private K key;

    private V value;

    public HeapNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }


    public int compareTo(HeapNode<K,V> other) {
        // Compare the current object with the "other" object
        // based on the "value" field
        if (this.value.compareTo(other.getValue()) < 0) {
            return -1;  // Current object is smaller
        } else if (this.value.compareTo(other.getValue()) > 0) {
            return 1;   // Current object is larger
        } else {
            return 0;   // Objects are equal
        }
    }
}
