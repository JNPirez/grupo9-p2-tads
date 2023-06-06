package uy.edu.um.prog2.adt.queue;

import uy.edu.um.prog2.adt.exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.linkedlist.Node;

public class Queue<T> implements MyQueue {

    private Node primero;

    public Queue() {
    }

    public Queue(Node primero) {

        this.primero = primero;

    }

    @Override
    public void enqueue(Object element) {

        if (primero == null){
            Node<Object> nodoAux = new Node(element);
            primero = nodoAux;
        }

        else {
            Node nodoAux = primero;
            while (nodoAux.getNext() != null){
                nodoAux = nodoAux.getNext();
            }
            Node<Object> nodoAux2 = new Node(element);
            nodoAux.setNext(nodoAux2);
        }
    }


    @Override
    public T dequeue() throws EmptyQueueException {

        T primeroEnQueue;

        if (primero != null) {
            primeroEnQueue = (T) primero.getValue();
            primero = primero.getNext();
        } else {
            throw new EmptyQueueException();
        }

        return primeroEnQueue;
    }

    @Override
    public int size() throws EmptyQueueException {

        int countSize = 0;

        while (!this.isEmpty()){
            countSize += 1;
            this.dequeue();
        }

        return countSize;
    }

    @Override
    public boolean isEmpty() {

        boolean isEmpty = false;
        if(primero == null){
            isEmpty = true;
        }

        return isEmpty;
    }

    public Node getPrimero() {
        return primero;
    }

    public void setPrimero(Node primero) {
        this.primero = primero;
    }
}
