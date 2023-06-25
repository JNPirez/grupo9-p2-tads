package uy.edu.um.prog2.adt.queue;

import uy.edu.um.prog2.adt.exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.linkedlist.Node;

public class Queue<T> implements MyQueue<T> {

    private Node<T> primero;
    private Node<T> ultimo;

    public Queue() {
        this.primero = null;
        this.ultimo = null;
    }

    @Override
    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);

        if (primero == null) {
            primero = newNode;
            ultimo = newNode;
        } else {
            ultimo.setNext(newNode);
            ultimo = newNode;
        }
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        T valorPrimero = primero.getValue();
        primero = primero.getNext();

        if (primero == null) {
            ultimo = null;
        }

        return valorPrimero;
    }

    @Override
    public boolean isEmpty() {
        return primero == null;
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
}
