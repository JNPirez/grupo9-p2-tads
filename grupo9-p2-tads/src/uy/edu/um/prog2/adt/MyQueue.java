package uy.edu.um.prog2.adt;
import uy.edu.um.prog2.adt.exceptions.EmptyQueueException;
public interface MyQueue<T> {

    void enqueue (T element);

    T dequeue () throws EmptyQueueException;
    boolean isEmpty();

    int size() throws EmptyQueueException;
}