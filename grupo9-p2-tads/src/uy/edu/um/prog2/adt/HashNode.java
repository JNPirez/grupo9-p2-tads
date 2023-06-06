package uy.edu.um.prog2.adt;

public class HashNode<K, V> {
    private K key;
    private V value;

    private HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey() {
        return key;
    } //Retorna la clave del nodo

    public void setKey(K key) {
        this.key = key;
    } //Establece la clave del nodo.

    public V getValue() {
        return value;
    } //Retorna el valor asociado al nodo

    public void setValue(V value) {
        this.value = value;
    } //Establece el valor asociado al nodo.

    public HashNode<K, V> getNext() {
        return next;
    } //Retorna el siguiente nodo enlazado

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    } //Establece el siguiente nodo enlazado
}