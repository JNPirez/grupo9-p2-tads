package uy.edu.um.prog2.adt.queue;

import uy.edu.um.prog2.adt.exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.linkedlist.Node;

public class Queue<T> implements MyQueue<T> {

    private Node<T> primero;

    public Queue() {
    }

    @Override
    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);

        if (primero == null) {
            primero = newNode;
        } else {
            Node<T> nodoAux = primero;
            while (nodoAux.getNext() != null) {
                nodoAux = nodoAux.getNext();
            }
            nodoAux.setNext(newNode);
        }
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (primero == null) {
            throw new EmptyQueueException();
        }

        T primeroEnQueue = primero.getValue();
        primero = primero.getNext();

        return primeroEnQueue;
    }

    @Override
    public int size() {
        int countSize = 0;
        Node<T> nodoAux = primero;

        while (nodoAux != null) {
            countSize++;
            nodoAux = nodoAux.getNext();
        }

        return countSize;
    }

    @Override
    public boolean isEmpty() {
        return primero == null;
    }
}
