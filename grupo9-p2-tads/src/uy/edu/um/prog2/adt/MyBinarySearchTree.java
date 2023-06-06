package uy.edu.um.prog2.adt;
import uy.edu.um.prog2.adt.exceptions.*;



public interface MyBinarySearchTree<K extends Comparable<K>, T> {

    T find(K key) throws EmptyTreeException;
    void insert (K key, T data) throws InformacionInvalida, EmptyTreeException;
    void delete (K key) throws EmptyTreeException;
    Queue<K> inOrder() throws EmptyTreeException, EmptyQueueException;

    Queue<K> preOrder() throws EmptyTreeException, EmptyQueueException;

    Queue<K> postOrder() throws EmptyTreeException, EmptyQueueException;

}