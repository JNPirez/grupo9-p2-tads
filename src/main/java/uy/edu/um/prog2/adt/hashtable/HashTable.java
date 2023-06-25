package uy.edu.um.prog2.adt.hashtable;

import uy.edu.um.prog2.adt.linkedlist.LinkedList;

public class HashTable<K extends Comparable<K>, V> implements MyHashTable<K, V> {
    private static final int DEFAULT_SIZE = 50000;
    HashLinkedList<K, V>[] buckets;
    private HashNode[] table;
    int size;

    public HashTable() {
        this(DEFAULT_SIZE);
        table = new HashNode[DEFAULT_SIZE];
    }

    public HashTable(int size) {
        this.size = size;
        buckets = (HashLinkedList<K, V>[]) new HashLinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new HashLinkedList<>();
        }
    }

    public int hashCode(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % size;
    }

    @Override
    public void put(K key, V value) {
        int code = hashCode(key);
        HashLinkedList<K, V> bucket = buckets[code];
        if (contains(key)) {
            HashNode<K, V> existingNode = bucket.getNode(key);
            existingNode.setValue(value); // Actualiza el valor existente
        } else {
            bucket.add(new HashNode<>(key, value)); // Agrega un nuevo nodo si la clave no existe
        }
    }

    @Override
    public boolean contains(K key) {
        int code = hashCode(key);
        HashLinkedList<K, V> bucket = buckets[code];
        HashNode<K, V> current = bucket.getFirst();
        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public V get(K key) {
        int code = hashCode(key);
        return buckets[code].get(key);
    }

    public void printHashTable() {
        for (int i = 0; i < size; i++) {
            System.out.print("Bucket " + i + ": ");
            HashLinkedList<K, V> bucket = buckets[i];
            bucket.print();
            System.out.println();
        }
    }

    @Override
    public void remove(K key) {
        int code = hashCode(key);
        HashNode<K, V> cleannode = buckets[code].getNode(key);
        buckets[code].remove(cleannode);
    }

    @Override
    public int size() {
        int count = 0;
        for (HashLinkedList<K, V> bucket : buckets) {
            count += bucket.size();
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public V getOrDefault(K key, V defaultValue) {
        int code = hashCode(key);
        HashLinkedList<K, V> bucket = buckets[code];
        HashNode<K, V> current = bucket.getFirst();
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return defaultValue;
    }

    @Override
    public LinkedList<K> keysToList() {
        LinkedList<K> keysList = new LinkedList<>();
        for (HashLinkedList<K, V> bucket : buckets) {
            HashNode<K, V> currentNode = bucket.getFirst();
            while (currentNode != null) {
                keysList.add(currentNode.getKey());
                currentNode = currentNode.getNext();
            }
        }
        return keysList;
    }





}

