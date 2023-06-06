package uy.edu.um.prog2.adt.hashtable;

public interface MyHashTable<K, V> {
    void put(K key, V value);
    V get(K key);
    boolean contains(K key);
    void remove(K key);
    int size();
    boolean isEmpty();
}
