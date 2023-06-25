package uy.edu.um.prog2.adt.binarysearchtree;

public class TreeNodeBST <K extends Comparable<K>, T> {
    private K key;
    private T data;
    private TreeNodeBST <K, T> leftChild;
    private TreeNodeBST <K, T> rightChild;

    public TreeNodeBST() {
    }

    public TreeNodeBST(K key, T data) {
        this.key = key;
        this.data = data;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNodeBST<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNodeBST<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNodeBST<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNodeBST<K, T> rightChild) {
        this.rightChild = rightChild;
    }
}