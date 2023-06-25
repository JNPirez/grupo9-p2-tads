package uy.edu.um.prog2.adt.binarysearchtree;

import uy.edu.um.prog2.adt.exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.exceptions.InformacionInvalida;
import uy.edu.um.prog2.adt.queue.Queue;

public class BinarySearchTree<K extends Comparable<K>, T> implements MyBinarySearchTree<K, T> {

    private TreeNodeBST<K, T> root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(TreeNodeBST<K, T> root) {
        this.root = root;
    }

    @Override
    public T find(K key) throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException();
        }

        return findNode(root, key);
    }

    private T findNode(TreeNodeBST<K, T> node, K key) {
        if (node == null) {
            return null;
        }

        int compare = key.compareTo(node.getKey());

        if (compare == 0) {
            return node.getData();
        } else if (compare < 0) {
            return findNode(node.getLeftChild(), key);
        } else {
            return findNode(node.getRightChild(), key);
        }
    }

    public Queue<K> inOrder() throws EmptyTreeException, EmptyQueueException {
        if (root == null) {
            throw new EmptyTreeException();
        }

        Queue<K> listaRecorrida = new Queue<K>();
        inOrderTraversal(root, listaRecorrida);
        return listaRecorrida;
    }

    private void inOrderTraversal(TreeNodeBST<K, T> node, Queue<K> listaRecorrida) {
        if (node != null) {
            inOrderTraversal(node.getLeftChild(), listaRecorrida);
            listaRecorrida.enqueue(node.getKey());
            inOrderTraversal(node.getRightChild(), listaRecorrida);
        }
    }

    @Override
    public void insert(K key, T data) throws InformacionInvalida, EmptyTreeException {
        if (root == null) {
            TreeNodeBST<K, T> newNode = new TreeNodeBST<K, T>(key, data);
            root = newNode;
            return;
        }

        TreeNodeBST<K, T> current = root;
        TreeNodeBST<K, T> parent;

        while (true) {
            parent = current;

            if (key.compareTo(current.getKey()) == 0) {
                throw new InformacionInvalida();
            } else if (key.compareTo(current.getKey()) < 0) {
                current = current.getLeftChild();
                if (current == null) {
                    TreeNodeBST<K, T> newNode = new TreeNodeBST<K, T>(key, data);
                    parent.setLeftChild(newNode);
                    return;
                }
            } else {
                current = current.getRightChild();
                if (current == null) {
                    TreeNodeBST<K, T> newNode = new TreeNodeBST<K, T>(key, data);
                    parent.setRightChild(newNode);
                    return;
                }
            }
        }
    }

    @Override
    public void delete(K key) throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException();
        }

        root = deleteNode(root, key);
    }

    private TreeNodeBST<K, T> deleteNode(TreeNodeBST<K, T> node, K key) {
        if (node == null) {
            return null;
        }

        int compare = key.compareTo(node.getKey());

        if (compare < 0) {
            node.setLeftChild(deleteNode(node.getLeftChild(), key));
        } else if (compare > 0) {
            node.setRightChild(deleteNode(node.getRightChild(), key));
        } else {
            // Caso 1: El nodo no tiene hijos o solo tiene un hijo
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            // Caso 2: El nodo tiene dos hijos
            TreeNodeBST<K, T> successor = findSuccessor(node.getRightChild());
            node.setKey(successor.getKey());
            node.setData(successor.getData());
            node.setRightChild(deleteSuccessor(node.getRightChild(), successor));
        }

        return node;
    }

    private TreeNodeBST<K, T> findSuccessor(TreeNodeBST<K, T> node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

    private TreeNodeBST<K, T> deleteSuccessor(TreeNodeBST<K, T> parent, TreeNodeBST<K, T> successor) {
        if (successor.getRightChild() != null) {
            parent.setLeftChild(successor.getRightChild());
        } else {
            parent.setLeftChild(null);
        }
        return parent;
    }

    public TreeNodeBST<K, T> getRoot() {
        return root;
    }

    public void setRoot(TreeNodeBST<K, T> root) {
        this.root = root;
    }

    public TreeNodeBST<K, T> getRightmostLeaf() {
        if (root == null) {
            return null;
        }

        TreeNodeBST<K, T> current = root;
        while (current.getRightChild() != null) {
            current = current.getRightChild();
        }
        return current;
    }

    public Queue<K> preOrder() throws EmptyTreeException, EmptyQueueException {
        if (root == null) {
            throw new EmptyTreeException();
        }

        Queue<K> listaRecorrida = new Queue<K>();
        preOrderTraversal(root, listaRecorrida);
        return listaRecorrida;
    }

    private void preOrderTraversal(TreeNodeBST<K, T> node, Queue<K> listaRecorrida) {
        if (node != null) {
            listaRecorrida.enqueue(node.getKey());
            preOrderTraversal(node.getLeftChild(), listaRecorrida);
            preOrderTraversal(node.getRightChild(), listaRecorrida);
        }
    }

    @Override
    public Queue<K> postOrder() throws EmptyTreeException, EmptyQueueException {
        if (root == null) {
            throw new EmptyTreeException();
        }

        Queue<K> listaRecorrida = new Queue<K>();
        postOrderTraversal(root, listaRecorrida);
        return listaRecorrida;
    }

    private void postOrderTraversal(TreeNodeBST<K, T> node, Queue<K> listaRecorrida) {
        if (node != null) {
            postOrderTraversal(node.getLeftChild(), listaRecorrida);
            postOrderTraversal(node.getRightChild(), listaRecorrida);
            listaRecorrida.enqueue(node.getKey());
        }
    }
}

