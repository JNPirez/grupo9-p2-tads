package uy.edu.um.prog2.adt.hashtable;

import uy.edu.um.prog2.adt.linkedlist.LinkedList;

public interface MyHashTable<K extends Comparable<K>, V> {
    void put(K key, V value);
    V get(K key);
    boolean contains(K key);
    void remove(K key);
    int size();
    boolean isEmpty();
    V getOrDefault(K key, V defaultValue);

    LinkedList<K> keysToList();
}
