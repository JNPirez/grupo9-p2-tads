package uy.edu.um.prog2.adt.heap;

public class ArrayList<T> {
    private static final int initial = 10;
    private T[] array;
    private int size;


    public ArrayList() {
        array = (T[]) new Object[initial];
        size = 0;
    }

    public void add(T element) {
        if (size == array.length) {
            resizeArray();
        }
        array[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        T removedElement = array[index];

        // Desplazar los elementos hacia la izquierda para llenar el espacio vacío
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        // Actualizar el último elemento y reducir el tamaño
        array[size - 1] = null;
        size--;

        return removedElement;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray() {
        T[] newArray = (T[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

}
