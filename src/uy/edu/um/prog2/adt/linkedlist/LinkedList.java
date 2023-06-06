package uy.edu.um.prog2.adt.linkedlist;


public class LinkedList<T> implements MyLinkedList<T> {

    private Node<T> first;
    private Node<T>last;

    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void add(T value) {
        if (first == null) {
            first = new Node<T>(value);
        } else {
            Node<T> aux = new Node<T>(value);
            aux.setNext(first);
            first = aux;
        }
    }

    @Override
    public T get(int position) {
        T valueToReturn = null;
        int tempPosition = 0;
        Node<T> temp = this.first;

        // Se busca el nodo que corresponde con la posicion
        while (temp != null && tempPosition != position) {

            temp = temp.getNext();
            tempPosition++;

        }

        // si se encontro la posicion se retorna el valor
        // en caso que se haya llegado al final y no se llego a la posicion se retorna null
        if (tempPosition == position) {


            valueToReturn = temp.getValue();

        }

        return valueToReturn;
    }

    @Override
    public boolean contains(T value) {
        boolean contains = false;
        Node<T> temp = this.first;

        while (temp != null && !temp.getValue().equals(value)) {

            temp = temp.getNext();

        }

        if (temp != null) { // Si no se llego al final de la lista, se encontro el valor

            contains = true;

        }

        return contains;
    }

    @Override
    public void remove(T value) {
        Node<T> beforeSearchValue = null;
        Node<T> searchValue = this.first;

        // Busco el elemento a eliminar teniendo en cuenta mantener una referencia al elemento anterior
        while (searchValue != null && !searchValue.getValue().equals(value)) {

            beforeSearchValue = searchValue;
            searchValue = searchValue.getNext();

        }

        if (searchValue != null) { // si encontre el elemento a eliminar

            // Verifico si es el primer valor (caso borde) y no es el ultimo
            if (searchValue == this.first && searchValue != this.last) {

                Node<T> temp = this.first;
                this.first = this.first.getNext(); // salteo el primero

                temp.setNext(null); // quito referencia del elemento eliminado al siguiente.

                // Verifico si es el primer valor (caso borde) y no el primero
            } else if (searchValue == this.last && searchValue != this.first) {

                beforeSearchValue.setNext(null);
                this.last = beforeSearchValue;

                // Si es el primer valor y el ultimo (lista de un solo valor)
            } else if (searchValue == this.last && searchValue == this.first) {

                this.first = null;
                this.last = null;

            } else { // resto de los casos

                beforeSearchValue.setNext(searchValue.getNext());
                searchValue.setNext(null);

            }

        }

    }


    @Override
    public int size() {
        int size = 0;

        if (first == null) {
            return size;
        }

        Node<T> aux = first;
        size++;
        while (aux.getNext() != null) {
            aux = aux.getNext();
            size++;
        }


        return size;
    }

}
