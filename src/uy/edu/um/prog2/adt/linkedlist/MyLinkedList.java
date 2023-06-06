package uy.edu.um.prog2.adt.linkedlist;

public interface MyLinkedList<T> {

    void add(T value);

    T get(int position);

    boolean contains(T value);

    void remove(T value);

    int size();
}
