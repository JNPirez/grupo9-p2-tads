package uy.edu.um.prog2.adt.stack;

import uy.edu.um.prog2.adt.exceptions.EmptyStackException;
import uy.edu.um.prog2.adt.linkedlist.Node;

public class Stack<T> implements MyStack<T> {

    private Node<T> top;

    public Stack() {
        this.top = null;
    }

    @Override
    public T top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return top.getValue();
    }

    @Override
    public void pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        top = top.getPrevious();
    }

    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.setPrevious(top);
        top = newNode;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void makeEmpty() {
        top = null;
    }
}