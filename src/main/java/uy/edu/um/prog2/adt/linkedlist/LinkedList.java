package uy.edu.um.prog2.adt.linkedlist;


import uy.edu.um.prog2.adt.exceptions.NoSuchElementException;

import java.util.Iterator;

public class LinkedList<T extends Comparable<T>> implements MyLinkedList<T>, Iterator<String> ,Iterable<T>{
    private Node<T> first;
    private Node<T> last;

    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                try {
                    throw new NoSuchElementException();
                } catch (NoSuchElementException e) {
                    throw new RuntimeException(e);
                }
            }

            T value = current.getValue();
            current = current.getNext();
            return value;
        }
    }

    public void add(int index, T value) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size()) {
            add(value);
            return;
        }

        Node<T> newNode = new Node<>(value);

        if (index == 0) {
            newNode.setNext(first);
            first.setPrevious(newNode);
            first = newNode;
        } else {
            Node<T> current = getNode(index - 1);
            Node<T> next = current.getNext();

            current.setNext(newNode);
            newNode.setPrevious(current);
            newNode.setNext(next);
            next.setPrevious(newNode);
        }
    }

    @Override
    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (last == null) {
            // Si la lista está vacía, el nuevo nodo será tanto el primero como el último
            first = newNode;
            last = newNode;
        } else {
            // Si la lista no está vacía, se agrega el nuevo nodo después del último nodo actual
            last.setNext(newNode);
            last = newNode;
        }
    }

    @Override
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
        }
    }

    public T get(int position) {
        if (position < 0 || position >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = getNode(position);
        return node.getValue();
    }

    private Node<T> getNode(int position) {
        Node<T> current = first;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        return current;
    }

    public boolean contains(T value) {
        Node<T> current = first;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void remove(T value) {
        if (first == null) {
            return;
        }

        if (first.getValue().equals(value)) {
            if (first == last) { // Caso de lista con un solo nodo
                first = null;
                last = null;
            } else {
                first = first.getNext();
                first.setPrevious(null);
            }
            return;
        }

        Node<T> current = first.getNext();
        while (current != null) {
            if (current.getValue().equals(value)) {
                Node<T> previous = current.getPrevious();
                Node<T> next = current.getNext();
                previous.setNext(next);
                if (next != null) {
                    next.setPrevious(previous);
                } else {
                    last = previous;
                }
                return;
            }
            current = current.getNext();
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            first = first.getNext();
            if (first != null) {
                first.setPrevious(null);
            }
        } else {
            Node<T> current = getNode(index);
            Node<T> previous = current.getPrevious();
            Node<T> next = current.getNext();

            previous.setNext(next);

            if (next != null) {
                next.setPrevious(previous);
            }
        }
    }

    public int size() {
        int count = 0;
        Node<T> current = first;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }


    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return null;
    }

}
