package uy.edu.um.prog2.adt;

public interface MyHeap<T extends Comparable<T>> {
    void add(T element);
    T getAndRemove();
    int getSize();
    void printHeap();
}