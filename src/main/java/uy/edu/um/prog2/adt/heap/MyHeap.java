package uy.edu.um.prog2.adt.heap;

public interface MyHeap<T extends Comparable<T>> {
    void add(T element);
    T getAndRemove();
    int getSize();
    void printHeap();
}