package uy.edu.um.prog2.adt.binarysearchtree;
import uy.edu.um.prog2.adt.queue.MyQueue;
import uy.edu.um.prog2.adt.queue.Queue;
import uy.edu.um.prog2.adt.exceptions.*;

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
        if (this.root == null) {
            TreeNodeBST<K, T> newNode = new TreeNodeBST<K, T>(key, data);
            this.setRoot(newNode);
            return;
        }

        int compare = key.compareTo(root.getKey());

        if (compare == 0) {
            throw new InformacionInvalida();
        }

        if (compare < 0) {
            if (root.getLeftChild() == null) {
                TreeNodeBST<K, T> newNode = new TreeNodeBST<K, T>(key, data);
                root.setLeftChild(newNode);
            } else {
                MyBinarySearchTree<K, T> treeAux = new BinarySearchTree<K, T>(root.getLeftChild());
                treeAux.insert(key, data);
            }
        }

        if (compare > 0) {
            if (root.getRightChild() == null) {
                TreeNodeBST<K, T> newNode = new TreeNodeBST<K, T>(key, data);
                root.setRightChild(newNode);
            } else {
                MyBinarySearchTree<K, T> treeAux = new BinarySearchTree<K, T>(root.getRightChild());
                treeAux.insert(key, data);
            }
        }

    }

    @Override
    public void delete(K key) throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException();
        }

        TreeNodeBST<K, T> parent = null;
        TreeNodeBST<K, T> current = root;
        boolean isLeftChild = false;

        while (current != null) {
            int compare = key.compareTo(current.getKey());

            if (compare == 0) {
                break;
            }

            parent = current;

            if (compare < 0) {
                current = current.getLeftChild();
                isLeftChild = true;
            } else {
                current = current.getRightChild();
                isLeftChild = false;
            }
        }

        if (current == null) {
            return; // La clave no se encontró en el árbol
        }

        // Caso 1: El nodo a eliminar es una hoja
        if (current.getLeftChild() == null && current.getRightChild() == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
        }
        // Caso 2: El nodo a eliminar tiene un hijo
        else if (current.getRightChild() == null) {
            if (current == root) {
                root = current.getLeftChild();
            } else if (isLeftChild) {
                parent.setLeftChild(current.getLeftChild());
            } else {
                parent.setRightChild(current.getLeftChild());
            }
        } else if (current.getLeftChild() == null) {
            if (current == root) {
                root = current.getRightChild();
            } else if (isLeftChild) {
                parent.setLeftChild(current.getRightChild());
            } else {
                parent.setRightChild(current.getRightChild());
            }
        }
        // Caso 3: El nodo a eliminar tiene dos hijos
        else {
            TreeNodeBST<K, T> successorParent = current;
            TreeNodeBST<K, T> successor = current.getRightChild();

            while (successor.getLeftChild() != null) {
                successorParent = successor;
                successor = successor.getLeftChild();
            }

            current.setKey(successor.getKey());
            current.setData(successor.getData());

            if (successorParent.getLeftChild() == successor) {
                successorParent.setLeftChild(successor.getRightChild());
            } else {
                successorParent.setRightChild(successor.getRightChild());
            }
        }
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
            TreeNodeBST<K, T> sucesor = findSucesor(node.getRightChild());
            node.setKey(sucesor.getKey());
            node.setData(sucesor.getData());
            node.setRightChild(deleteSucesor(node.getRightChild(), sucesor));
        }

        return node;
    }


    private TreeNodeBST<K, T> findSucesor(TreeNodeBST<K, T> node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

    private TreeNodeBST<K, T> deleteSucesor(TreeNodeBST<K, T> parent, TreeNodeBST<K, T> sucesor) {
        if (sucesor.getRightChild() != null) {
            parent.setLeftChild(sucesor.getRightChild());
        } else {
            parent.setRightChild(null);
        }
        return parent;
    }

    public TreeNodeBST<K, T> getRoot() {
        return root;
    }

    public void setRoot(TreeNodeBST<K, T> root) {
        this.root = root;
    }

    public TreeNodeBST<K, T> getRightestLeaf() {
        TreeNodeBST<K, T> rightestLeaf = null;

        if (root.getRightChild() == null) {
            rightestLeaf = root;
            return rightestLeaf;
        } else {
            BinarySearchTree<K, T> treeAux = new BinarySearchTree<K, T>(root.getRightChild());
            rightestLeaf = treeAux.getRightestLeaf();
            if (rightestLeaf != null) {
                return rightestLeaf;
            }
        }

        return rightestLeaf;
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
        if (root.getKey() == null) {
            throw new EmptyTreeException();
        }

        MyQueue<K> listaRecorrida = new Queue<K>();

        if (root.getLeftChild() != null) {
            MyBinarySearchTree<K, T> treeAux = new BinarySearchTree<K, T>(root.getLeftChild());
            Queue<K> queueAux = treeAux.postOrder();
            while (!queueAux.isEmpty()) {
                listaRecorrida.enqueue(queueAux.dequeue());
            }
        }

        if (root.getRightChild() != null) {
            MyBinarySearchTree<K, T> treeAux = new BinarySearchTree<K, T>(root.getRightChild());
            Queue<K> queueAux = treeAux.postOrder();
            while (!queueAux.isEmpty()) {
                listaRecorrida.enqueue(queueAux.dequeue());
            }
        }

        listaRecorrida.enqueue(root.getKey());

        return (Queue<K>) listaRecorrida;
    }
}
