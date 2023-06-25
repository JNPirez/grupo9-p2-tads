package uy.edu.um.prog2.adt.linkedlist;

import java.util.Iterator;

public interface MyLinkedList<T> {

    void addLast(T value);

    void add(T value);

    T get(int position);

    boolean contains(T value);

    void remove(T value);

    int size();

    Iterator<T> iterator();




}
