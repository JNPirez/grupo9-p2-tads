package uy.edu.um.prog2.adt.stack;

import uy.edu.um.prog2.adt.exceptions.EmptyStackException;
import uy.edu.um.prog2.adt.linkedlist.Node;

public class Stack<T> implements MyStack{

    private Node<T> top = null;
    public Stack() {
    }

    @Override
    public T top() throws EmptyStackException {

        if (top == null){throw new EmptyStackException();}

        return top.getValue();
    }

    @Override
    public void pop() throws EmptyStackException {

        if (top == null){ throw new EmptyStackException();}

        top = top.getPrevious();


    }

    @Override
    public void push(Object element) {
        Node<T> newNode = (Node<T>) new Node<>(element);
        newNode.setPrevious(top);
        top = newNode;
    }


    @Override
    public boolean isEmpty() {

        boolean empty = false;

        if (top == null){
            empty = true;
        }

        return empty;

    }

    @Override
    public void makeEmpty() {
        top = null;
    }

}