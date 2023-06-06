package uy.edu.um.prog2.adt.test.BinarySearchTree;

import org.junit.Test;
import uy.edu.um.prog2.adt.binarysearchtree.BinarySearchTree;
import uy.edu.um.prog2.adt.exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.exceptions.InformacionInvalida;
import uy.edu.um.prog2.adt.queue.Queue;

class MyBinarySearchTreeTest {
    @Test
    public void testOperations() {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();

        try {
            // Insertar elementos
            bst.insert(5, "Data5");
            bst.insert(3, "Data3");
            bst.insert(8, "Data8");
            bst.insert(2, "Data2");
            bst.insert(4, "Data4");
            bst.insert(7, "Data7");
            bst.insert(9, "Data9");

            // Buscar elementos
            System.out.println(bst.find(3)); // Output: Data3
            System.out.println(bst.find(7)); // Output: Data7
            System.out.println(bst.find(10)); // Output: null

            // Eliminar un elemento
            bst.delete(8);
            System.out.println(bst.find(8)); // Output: null

            // Recorrido inOrder
            Queue<Integer> inOrder = bst.inOrder();
            System.out.println("InOrder traversal:");
            while (!inOrder.isEmpty()) {
                System.out.print(inOrder.dequeue() + " ");
            }
            System.out.println();

            // Recorrido preOrder
            Queue<Integer> preOrder = bst.preOrder();
            System.out.println("PreOrder traversal:");
            while (!preOrder.isEmpty()) {
                System.out.print(preOrder.dequeue() + " ");
            }
            System.out.println();

            // Recorrido postOrder
            Queue<Integer> postOrder = bst.postOrder();
            System.out.println("PostOrder traversal:");
            while (!postOrder.isEmpty()) {
                System.out.print(postOrder.dequeue() + " ");
            }
            System.out.println();

        } catch (InformacionInvalida | EmptyTreeException | EmptyQueueException e) {
            e.printStackTrace();
        }
    }
}
